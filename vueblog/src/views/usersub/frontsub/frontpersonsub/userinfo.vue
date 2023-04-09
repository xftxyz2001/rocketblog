<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span style="font-weight: 700">基本信息</span>
        <el-button class="button" text @click="editinfo">编辑</el-button>
      </div>
    </template>
    <div class="text item">
      <span style="margin-right: 20px">头像</span
      ><img
        :src="userinfo.avatar"
        alt=""
        style="
          width: 40px;
          height: 40px;
          border-radius: 20px;
          vertical-align: middle;
        "
      />
    </div>
    <div class="text item">
      <span style="margin-right: 30px">用户昵称</span
      ><span>{{ userinfo.username }}</span>
    </div>
    <div class="text item">
      <span style="margin-right: 30px">性别</span>{{ userinfo.userSex }}
    </div>
    <div class="text item">
      <span style="margin-right: 30px"> 手机</span>{{ userinfo.phone }}
    </div>
    <div class="text item">
      <span style="margin-right: 30px">注册日期</span
      >{{ userinfo.userRegisterTime }}
    </div>
  </el-card>
  <el-dialog
    ref="editform"
    v-model="dialogFormVisible"
    title="编辑资料"
    style="width: 30%"
  >
    <el-form :model="form">
      <el-form-item
        label="头像"
        :label-width="formLabelWidth"
        style="margin-bottom: 40px"
      >
        <div
          style="
            width: 300px;
            height: 40px;
            border-radius: 20px;
            position: relative;
            padding-bottom: 40px;
            margin-right: 20px;
          "
        >
          <img
            :src="form.avatar"
            alt=""
            style="
              width: 40px;
              height: 40px;
              border-radius: 20px;
              position: fixed;
            "
          />
          <el-upload
            class="upload-demo"
            action="http://8.130.81.23:8080/images/upload"
            :on-change="handleChange"
            :on-success="handleSuccess"
            :before-upload="beforeUpload"
            :on-progress="uploadProcess"
            :on-exceed="handleExceed"
            :before-remove="beforeRemove"
            :file-list="fileList"
            :multiple="true"
            :limit="1"
            style="margin-left: 50px; margin-bottom: 40px"
          >
            <el-button type="primary">选择图片</el-button>
            <template #tip>
              <div class="el-upload__tip">只能上传jpg/png文件，且不超过2M</div>
            </template>
          </el-upload>
        </div>
      </el-form-item>
      <el-form-item label="用户昵称" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-select v-model="form.userSex" placeholder="Please select a zone">
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

import { ref, reactive } from "vue";
import { Plus } from "@element-plus/icons-vue";
import { ElMessage, ElMessageBox } from "element-plus";
import router from "@/router";

const fileList = reactive([]);
const dialogFormVisible = ref(false);
const userinfo = ref({});
const form = ref({});
const percent = ref(0);

// 上传前校验
const beforeUpload = (file) => {
  // console.log("上传前端校验", file);
  const isLt2M = file.size / 1024 / 1024 < 2;
  const type = file.type;
  if (!isLt2M) {
    ElMessage.success({
      message: "上传图片不能超过2MB哦!",
    });
    return false;
  }
  //   if(type.includes('jpg'))
};

//上传成功回调
const handleSuccess = (res, file) => {
  // console.log("handleSuccessres", res);
  // console.log("handleSuccessfile", file);
  if (res) {
    ElMessage.success({
      message: "上传成功!",
    });

    form.value.avatar = axios.defaults.baseURL + res.data;
    console.log(form.value.avatar);
  } else {
    ElMessage.error({
      message: "上传失败，请重新上传",
    });
  }
};

//上传文件
const handleChange = (file, fileList) => {
  // console.log("file---", file);
  // console.log("fileList---", fileList);
  // fileList = fileList.slice(-3);
};

// 上传时候的钩子
const uploadProcess = (event, file, fileList) => {
  // console.log(event.percent);
  percent.value = Math.floor(event.percent);
};

// 删除
const beforeRemove = (file, fileList) => {
  const result = new Promise((resolve, reject) => {
    ElMessageBox.confirm("此操作将删除该图片, 是否继续?", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(() => {
        let index = fileList.indexOf(file);
        fileList.splice(index, 1);
        // 请求删除接口
        form.value.avatar = userinfo.value.avatar;
      })
      .catch(() => {
        reject(false);
      });
  });
  return result;
};
const handleExceed = (files, uploadFiles) => {
  ElMessage.warning(`只能选择一张图片`);
};

// var form = {
//   username: "",
// };

axios.get("/user/i/detail").then((res) => {
  userinfo.value = res.data.data;
});
function editinfo() {
  form.value.username = userinfo.value.username;
  form.value.avatar = userinfo.value.avatar;
  form.value.phone = userinfo.value.phone;
  form.value.userSex = userinfo.value.userSex;
  dialogFormVisible.value = true;

  //   修改用户信息
}
function formSubmit() {
  axios.post("/user/update", form.value).then((res) => {
    var result = res.data;
    if (result.code == 0) {
      ElMessage.success({
        message: result.data,
      });
      dialogFormVisible.value = false;
      axios.get("/user/i/detail").then((res) => {
        userinfo.value = res.data.data;
      });
      window.location.reload();
    } else {
      ElMessage.error({
        message: result.message,
      });
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