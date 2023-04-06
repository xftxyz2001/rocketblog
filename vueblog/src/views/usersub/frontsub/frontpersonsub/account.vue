<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span style="font-weight: 700">账号管理</span>
        <el-button class="button" text @click="editinfo">编辑</el-button>
      </div>
    </template>
    <div class="text item">
      <span style="margin-right: 20px">邮箱</span>
      <span style="margin-right: 20px">{{ userinfo.email }} </span
      ><el-button link text @click="editinfo" style="color: #246fdd"
        >编辑</el-button
      >
    </div>
    <div class="text item">
      <span style="margin-right: 30px">密码</span
      ><span
        ><el-button link text @click="editinfo" style="color: #246fdd"
          >修改密码</el-button
        ></span
      >
    </div>
    <div class="text item">
      <span style="margin-right: 30px">上次登录</span>{{ userinfo.lastLogin }}
    </div>
    <div class="text item">
      <span style="margin-right: 30px">账户注销</span
      ><el-button link text @click="editinfo" style="color: #246fdd"
        >立即注销</el-button
      >
    </div>
  </el-card>

  <el-dialog
    ref="editform"
    v-model="dialogFormVisible"
    title="编辑资料"
    style="width: 30%"
  >
    <el-form :model="form">
      <el-form-item label="头像" :label-width="formLabelWidth">
        <el-input v-model="form.image" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户昵称" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="form.sex" placeholder="Please select a zone">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="手机" :label-width="formLabelWidth">
        <el-input v-model="form.phone" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="formSubmit"> 提交 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
const dialogFormVisible = ref(false);
const userinfo = ref({});
// var form = {
//   username: "",
// };
const form = ref({});
axios.get("/user/info/detail").then((res) => {
  userinfo.value = res.data.data;
});
// function editinfo() {
//   dialogFormVisible.value = true;
//   form.value.username = userinfo.value.username;
//   form.value.avatar = userinfo.value.avatar;
//   form.value.phone = userinfo.value.phone;

//   //   修改用户信息
// }
function formSubmit() {}
</script>
<script>
export default {};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
}
.item {
  margin-bottom: 40px;
}
</style>