<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span style="font-weight: 700">账号管理</span>
      </div>
    </template>
    <div class="text item">
      <span style="margin-right: 20px">邮箱</span>
      <span style="margin-right: 20px">{{ userinfo.email }} </span
      ><el-button link text @click="editemail" style="color: #246fdd"
        >修改邮箱</el-button
      >
    </div>
    <div class="text item">
      <span style="margin-right: 30px">密码</span
      ><span
        ><el-button link text @click="editpassword" style="color: #246fdd"
          >修改密码</el-button
        ></span
      >
    </div>
    <div class="text item">
      <span style="margin-right: 30px">上次登录</span>{{ userinfo.lastLogin }}
    </div>
    <div class="text item">
      <span style="margin-right: 30px">账户注销</span
      ><el-button
        link
        text
        @click="deleteaccountDialogVisible = true"
        style="color: #246fdd"
        >立即注销</el-button
      >
    </div>
  </el-card>

  <el-dialog
    ref="editform"
    v-model="emaildialogFormVisible"
    title="修改邮箱"
    style="width: 30%"
  >
    <el-form :model="editemailform">
      <el-form-item label="新邮箱" :label-width="formLabelWidth">
        <el-input v-model.trim="editemailform.email" autocomplete="off" />
      </el-form-item>

      <el-form-item label="验证码" :label-width="formLabelWidth">
        <el-input
          v-model.trim="editemailform.vertify"
          autocomplete="off"
          placeholder="验证码"
          prefix-icon="Unlock"
          style="width: 50%; margin-right: 12px"
        />
        <el-button @click="getverify">获取验证码</el-button>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="emaildialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editemailformSubmit">
          提交
        </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog
    ref="editform"
    v-model="passworddialogFormVisible"
    title="修改密码"
    style="width: 30%"
  >
    <el-form :model="editpasswordform">
      <el-form-item label="旧密码" :label-width="formLabelWidth">
        <el-input
          v-model.trim="editpasswordform.password"
          autocomplete="off"
          type="password"
        />
      </el-form-item>
      <el-form-item label="新密码" :label-width="formLabelWidth">
        <el-input
          v-model.trim="editpasswordform.newPassword"
          autocomplete="off"
          type="password"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="passworddialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editpasswordformSubmit">
          提交
        </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog
    v-model="deleteaccountDialogVisible"
    title="提示"
    width="30%"
    center
  >
    <span style="display: inline-block; width: 100%; text-align: center">
      确定要注销账户吗？
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="deleteaccountDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmdelete"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";
const emaildialogFormVisible = ref(false);
const passworddialogFormVisible = ref(false);
const deleteaccountDialogVisible = ref(false);
const userinfo = ref({});
// var form = {
//   username: "",
// };
const editemailform = ref({});
const editpasswordform = ref({});
axios.get("/user/i/detail").then((res) => {
  userinfo.value = res.data.data;
});
// function editinfo() {
//   dialogFormVisible.value = true;
//   form.value.username = userinfo.value.username;
//   form.value.avatar = userinfo.value.avatar;
//   form.value.phone = userinfo.value.phone;

//   //   修改用户信息
// }
function editemail() {
  editemailform.value.email = "";
  editemailform.value.vertify = "";
  emaildialogFormVisible.value = true;
}
function editpassword() {
  editpasswordform.value.password = "";
  editpasswordform.value.newPassword = "";

  passworddialogFormVisible.value = true;
}
function getverify() {
  console.log(editemailform.value.email);
  if (!editemailform.value.email) {
    ElMessage({
      showClose: true,
      message: "请输入邮箱",
      type: "error",
    });
  } else {
    if (
      /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/.test(
        editemailform.value.email
      )
    ) {
      axios.get("/user/code/" + editemailform.value.email, true);
    } else {
      ElMessage({
        showClose: true,
        message: "请输入正确的邮箱",
        type: "error",
      });
    }
  }
}
function editemailformSubmit() {
  if (!editemailform.value.email || !editemailform.value.vertify) {
    ElMessage({
      showClose: true,
      message: "邮箱和验证码不能为空",
      type: "error",
    });
  } else {
    axios.post("/user/update/email", editemailform.value).then((res) => {
      var result = res.data;
      if (result.code == 101) {
        ElMessage({
          showClose: true,
          message: "验证码错误",
          type: "error",
        });
      } else if (result.code == 0) {
        ElMessage({
          showClose: true,
          message: "修改成功",
          type: "success",
        });
        emaildialogFormVisible.value = false;
        window.location.reload();
      }
    });
  }
}
function editpasswordformSubmit() {
  if (!editpasswordform.value.password || !editpasswordform.value.newPassword) {
    ElMessage({
      showClose: true,
      message: "新密码和旧密码不能为空",
      type: "error",
    });
  } else if (
    !/^[A-Za-z0-9._~!@#$^&*]{8,16}$/.test(editpasswordform.value.newPassword)
  ) {
    ElMessage({
      showClose: true,
      message: "新密码应为包含英文字母大小写、数字和特殊符号的 8-16 位组合",
      type: "error",
    });
  } else {
    axios.post("/user/update/password", editpasswordform.value).then((res) => {
      var result = res.data;
      if (result.code == 103) {
        ElMessage({
          showClose: true,
          message: "旧密码错误",
          type: "error",
        });
      } else if (result.code == 0) {
        ElMessage({
          showClose: true,
          message: "修改成功",
          type: "success",
        });
        passworddialogFormVisible.value = false;
        window.location.reload();
      }
    });
  }
}
function confirmdelete() {
  axios.delete("/user/delete").then((res) => {
    var result = res.data;
    if (result.code == 0) {
      ElMessage({
        showClose: true,
        message: result.data,
        type: "success",
      });
      router.push({ name: "hotlatest" });
    }
  });
}
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