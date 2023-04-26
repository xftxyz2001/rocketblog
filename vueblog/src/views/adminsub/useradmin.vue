<template>
  <el-header style="text-align: right; font-size: 12px">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="昵称">
        <el-input v-model="formInline.name" placeholder="请输入昵称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="selectUser">昵称查找</el-button>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="formInline.email" placeholder="请输入邮箱" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="selectUser">邮箱查找</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" plain class="adduser" @click="adduser">
      添加用户</el-button
    >
  </el-header>
  <div></div>
  <el-main>
    <div
      v-infinite-scroll="load"
      class="infinite-list"
      style="overflow: auto"
      infinite-scroll-distance="1"
    >
      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          fixed
          prop="userRegisterTime"
          label="注册日期"
          width="160"
        />
        <el-table-column class="idcolumn" prop="userid" label="id" />

        <el-table-column prop="username" label="昵称" width="120" />
        <el-table-column prop="userSex" label="性别" width="90" />
        <el-table-column prop="email" label="邮箱" width="200" />
        <el-table-column prop="phone" label="手机" width="130" />
        <el-table-column prop="password" label="密码" width="150">
          <template v-slot="scope">
            <div @click="toggleShow(scope.row)">
              {{ showPassword(scope.row) }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="avatar" label="头像" width="90">
          <!-- 展示头像 -->
          <template v-slot="scope">
            <el-avatar
              style="width: 50px; height: 50px"
              :src="scope.row.avatar"
            ></el-avatar>
          </template>
        </el-table-column>
        <el-table-column prop="lastLogin" label="上次登陆日期" width="200" />

        <el-table-column fixed="right" label="选项" width="150">
          <template v-slot="scope">
            <el-button
              link
              type="primary"
              size="small"
              @click="lookclick(scope.row.userid)"
              >查看</el-button
            >
            <!-- <el-button link type="warning" size="small" @click="editclick(scope.row.userid)">编辑</el-button> -->
            <el-button
              link
              type="danger"
              size="small"
              @click="deleteclick(scope.row.userid)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-main>
  <!-- 添加表单 -->
  <el-dialog ref="editform" v-model="addFormVisible" title="Shipping address">
    <el-form :model="addform">
      <!-- <el-form-item label="id" :label-width="formLabelWidth">
        <el-input v-model="addform.userid" autocomplete="off" />
      </el-form-item> -->
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="addform.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="addform.userSex" placeholder="请选择">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱" :label-width="formLabelWidth">
        <el-input v-model="addform.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="手机" :label-width="formLabelWidth">
        <el-input v-model="addform.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="addform.password" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addformSubmit">添加</el-button>
      </span>
    </template>
  </el-dialog>
  <!-- /添加表单 -->
  <!-- 编辑表单 -->
  <el-dialog
    ref="editform"
    v-model="dialogFormVisible"
    title="Shipping address"
  >
    <el-form :model="form">
      <el-form-item label="id" :label-width="formLabelWidth">
        <!-- 不可编辑 -->
        <el-input v-model="form.userid" autocomplete="off" disabled />
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="form.userSex" placeholder="请选择">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱" :label-width="formLabelWidth">
        <el-input v-model="form.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="手机" :label-width="formLabelWidth">
        <el-input v-model="form.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="头像" :label-width="formLabelWidth">
        <el-input v-model="form.image" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="formSubmit"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- /编辑表单 -->
</template>
<script setup >
import { ref } from "vue";

const dialogFormVisible = ref(false);
const addFormVisible = ref(false);
const formLabelWidth = "140px";

const formInline = ref({
  name: "",
  email: "",
});
const tableData = ref([]);

const form = ref({});
const addform = ref({});
const page = ref(1);
const pagesize = ref(5);
function getUsers() {
  axios.get("/admin/users?pageNum=1&pageSize=5").then((res) => {
    tableData.value = res.data.data.list;
  });
}

getUsers();
function load() {
  page.value++;
  axios
    .get("/admin/users?pageNum=" + page.value + "&pageSize=" + pagesize.value)
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        tableData.value.push(result.data.list[index]);
      }
    });
}
function toggleShow(row) {
  row.show = !row.show;
}
function showPassword(row) {
  // 如果row.show为true，就显示密码，否则就显示**点击查看密码**
  if (row.show) {
    return row.password;
  } else {
    return "**点击查看密码**";
  }
}

function deleteclick(id) {
  axios.delete("/admin/user/" + id).then((res) => {
    var res = res.data;
    if (res.code == 0) {
      // 提示成功
      ElMessage.success(res.msg);
    }
    getUsers();
  });
}

function adduser() {
  addFormVisible.value = true;
}

function formSubmit() {
  dialogFormVisible.value = false;
}

function addformSubmit() {
  addFormVisible.value = false;
  axios.post("/admin/user", addform.value).then(() => {
    getUsers();
  });
}

function selectUser() {
  formInline.value.name = formInline.value.name.trim();
  formInline.value.email = formInline.value.email.trim();
  if (formInline.value.name === "" && formInline.value.email === "") {
    getUsers();
    return;
  }
  axios
    .post("/admin/user/s", {
      email: formInline.value.email,
      username: formInline.value.name,
    })
    .then((res) => {
      tableData.value = res.data.data.list;
    });
}

function lookclick(userid) {
  router.push({ name: "otherperson", params: { userid: userid } });
}
</script>
<script>
import router from "@/router";
import axios from "axios";
import { ElMessage } from "element-plus";

export default {
  name: "useradmin",
  data() {},

  methods: {
    editclick(userid) {
      console.log("edit" + userid);
    },

    deleteclick(userid) {
      console.log("delete" + userid);
    },
  },
};
</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
  overflow: hidden;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

.text-large {
  margin-left: 10px;
  height: 60px;
  display: inline-block;
  line-height: 60px;
  font-size: 25px;
  font-family: 华文行楷, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu;
}

.el-header {
  overflow: hiden;
}

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.demo-form-inline {
  display: inline-block;
  padding-top: 15px;
  position: absolute;
  left: 20px;
}

.adduser {
  position: absolute;
  right: 10px;
  top: 13px;
}

.el-table_1_column_1 {
  visibility: hidden;
  display: none;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
.infinite-list {
  height: 100%;
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
</style>