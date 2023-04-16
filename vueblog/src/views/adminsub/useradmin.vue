<template>
  <el-header style="text-align: right; font-size: 12px">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="昵称">
        <el-input v-model="formInline.name" placeholder="请输入昵称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="selectByName">昵称查找</el-button>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="formInline.email" placeholder="请输入邮箱" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="selectByEmail">邮箱查找</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" plain class="adduser" @click="adduser">
      添加用户</el-button
    >
  </el-header>
  <div></div>
  <el-main>
    <el-scrollbar>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          fixed
          prop="userRegisterTime"
          label="注册日期"
          width="150"
        />
        <el-table-column
          class="idcolumn"
          prop="userid"
          label="id"
          v-if="false"
        />
        <el-table-column prop="username" label="昵称" width="120" />
        <el-table-column prop="userSex" label="性别" width="120" />
        <el-table-column prop="email" label="邮箱" width="150" />
        <el-table-column prop="phone" label="手机" width="150" />
        <el-table-column prop="password" label="密码" width="150" />
        <el-table-column prop="avatar" label="头像" width="120" />
        <el-table-column prop="lastLogin" label="上次登陆日期" width="200" />

        <el-table-column fixed="right" label="选项" width="120">
          <template v-slot="scope">
            <!-- <el-button @click="change(scope.row.ID)">修改</el-button> -->
            <!-- <el-button type="danger" @click="del(scope.row.ID)">删除</el-button> -->

            <el-button
              link
              type="primary"
              size="small"
              @click="editclick(scope.row.userid)"
              >编辑</el-button
            >
            <el-button
              link
              type="primary"
              size="small"
              @click="deleteclick(scope.row.userid)"
              >删除</el-button
            >

            <!-- <el-button text @click="dialogFormVisible = true">
    open a Form nested Dialog
  </el-button> -->
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
  </el-main>
  <!-- 添加表单 -->
  <el-dialog ref="editform" v-model="addFormVisible" title="Shipping address">
    <el-form :model="addform">
      <el-form-item label="id" :label-width="formLabelWidth">
        <el-input v-model="addform.userid" autocomplete="off" />
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="addform.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="addform.userSex" placeholder="Please select a zone">
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
      <el-form-item label="头像" :label-width="formLabelWidth">
        <el-input v-model="addform.image" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addformSubmit"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 编辑表单 -->
  <el-dialog
    ref="editform"
    v-model="dialogFormVisible"
    title="Shipping address"
  >
    <el-form :model="form">
      <el-form-item label="id" :label-width="formLabelWidth">
        <el-input v-model="form.userid" autocomplete="off" />
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="form.userSex" placeholder="Please select a zone">
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
</template>
<script setup >
import { reactive, ref } from "vue";

const dialogFormVisible = ref(false);
const addFormVisible = ref(false);
const formLabelWidth = "140px";
const ins = getCurrentInstance();
console.log(1);
axios.get("/admin/users").then((res) => {
  tableData.value = res.data.data.list;
  // for (let index = 0; index < tableData.value.length; index++) {
  //   switch (res.data[index].userSex) {
  //     case "0":
  //       tableData.value[index].userSex = "女";
  //       break;
  //     case "1":
  //       tableData.value[index].userSex = "男";
  //       break;
  //     default:
  //       tableData.value[index].userSex = "未知";
  //   }
  // }
  // if (res.data.userSex == "0") tableData.value.userSex = "男";
  // else tableData.value.userSex = "女";
});
const formInline = ref({
  name: "",
  email: "",
});
const tableData = ref([]);

const form = ref({
  userRegisterTime: "",
  userid: "",
  username: "",
  userSex: "",
  email: "",
  phone: "",
  image: "",
  //delivery: false,

  lastLogin: "",
});
const addform = ref({
  userRegisterTime: "",
  userid: "",
  username: "",
  userSex: "",
  email: "",
  phone: "",
  image: "",
  password: "",
  //delivery: false,

  lastLogin: "",
});
function editclick(id) {
  dialogFormVisible.value = true;
  axios.get("/admin/user/" + id).then((res) => {
    form.value = res.data;
    // switch (res.data.userSex) {
    //   case "0":
    //     form.value.userSex = "女";
    //     break;
    //   case "1":
    //     form.value.userSex = "男";
    //     break;
    //   default:
    //     form.value.userSex = "未知";
    // }
  });
}
function deleteclick(id) {
  axios
    .delete("/admin/user/" + id)
    .then((res) => {
      console.log(res);
    })
    .then(() => {
      axios.get("/admin/users").then((res) => {
        tableData.value = res.data;
        // for (let index = 0; index < tableData.value.length; index++) {
        //   switch (res.data[index].userSex) {
        //     case "0":
        //       tableData.value[index].userSex = "女";
        //       break;
        //     case "1":
        //       tableData.value[index].userSex = "男";
        //       break;
        //     default:
        //       tableData.value[index].userSex = "未知";
        //   }
        // }
        // if (res.data.userSex == "0") tableData.value.userSex = "男";
        // else tableData.value.userSex = "女";
      });
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
  var sub = addform.value;
  // if (sub.userSex == "男") sub.userSex = "1";
  // else if (sub.userSex == "女") sub.userSex = "0";
  // else sub.userSex = "";
  axios.post("/admin/user", addform.value).then(() => {
    axios.get("/admin/users").then((res) => {
      tableData.value = res.data;
      // for (let index = 0; index < tableData.value.length; index++) {
      //   switch (res.data[index].userSex) {
      //     case "0":
      //       tableData.value[index].userSex = "女";
      //       break;
      //     case "1":
      //       tableData.value[index].userSex = "男";
      //       break;
      //     default:
      //       tableData.value[index].userSex = "未知";
      //   }
      // }
    });
  });
}

function selectByName() {
  // console.log(formInline.value.name.trim() === "");
  formInline.value.name = formInline.value.name.trim();
  if (formInline.value.name === "") {
    axios.get("/admin/users").then((res) => {
      tableData.value = res.data;
      // for (let index = 0; index < tableData.value.length; index++) {
      //   switch (res.data[index].userSex) {
      //     case "0":
      //       tableData.value[index].userSex = "女";
      //       break;
      //     case "1":
      //       tableData.value[index].userSex = "男";
      //       break;
      //     default:
      //       tableData.value[index].userSex = "未知";
      //   }
      // }
    });
    return;
  }
  axios.get("/admin/search/username/" + formInline.value.name).then((res) => {
    tableData.value = res.data;
    // for (let index = 0; index < tableData.value.length; index++) {
    //   switch (res.data[index].userSex) {
    //     case "0":
    //       tableData.value[index].userSex = "女";
    //       break;
    //     case "1":
    //       tableData.value[index].userSex = "男";
    //       break;
    //     default:
    //       tableData.value[index].userSex = "未知";
    //   }
    // }
  });
}
function selectByEmail() {
  formInline.value.email = formInline.value.email.trim();
  if (formInline.value.email === "") {
    axios.get("/admin/users").then((res) => {
      tableData.value = res.data;
      // for (let index = 0; index < tableData.value.length; index++) {
      //   switch (res.data[index].userSex) {
      //     case "0":
      //       tableData.value[index].userSex = "女";
      //       break;
      //     case "1":
      //       tableData.value[index].userSex = "男";
      //       break;
      //     default:
      //       tableData.value[index].userSex = "未知";
      //   }
      // }
    });
    return;
  }
  axios.get("/admin/search/email/" + formInline.value.email).then((res) => {
    tableData.value = res.data;
    // for (let index = 0; index < tableData.value.length; index++) {
    //   switch (res.data[index].userSex) {
    //     case "0":
    //       tableData.value[index].userSex = "女";
    //       break;
    //     case "1":
    //       tableData.value[index].userSex = "男";
    //       break;
    //     default:
    //       tableData.value[index].userSex = "未知";
    //   }
    // }
  });
}
</script>
<script>
import {
  onMounted,
  onUpdated,
  onRenderTriggered,
  getCurrentInstance,
  ref,
  reactive,
} from "vue";

import axios from "axios";
import { axiosinstance } from "@/main";

export default {
  name: "useradmin",
  data() {},

  methods: {
    selectByName() {
      if (this.formInline.name === "") return;
      //通过昵称查找用户表单
    },

    editclick(id) {
      console.log(id);
    },
  },

  // setup(props) {
  //   const ins = getCurrentInstance();
  //   onMounted(() => {
  //     console.log("cew");
  //     axios.get("/admin/users").then((res) => {
  //       console.log("cew");
  //       ins.data.tableData = res.data;
  //     });
  //     console.log("gvsre");
  //     // axios({
  //     //   url: "/admin/users",
  //     //   method: "GET",
  //     // }).then((res) => {
  //     //   console.log(res);
  //     // });
  //   });
  // },

  // created() {
  //   console.log("cwae");
  //   const data = axios.create({
  //     baseURL: "http://43.139.75.129:8601/user/getList",
  //   });
  //   console.log(data);
  // },
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
</style>