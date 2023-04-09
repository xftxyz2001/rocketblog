<template>
  <el-upload
    list-type="picture-card"
    :action="'http://xxx.xxx.xxx.xxx:8118/upload?pid=' + product_id"
    :on-change="handleChange"
    :on-success="handleSuccess"
    :before-upload="beforeUpload"
    :on-progress="uploadProcess"
    :before-remove="beforeRemove"
    :file-list="fileList"
    :multiple="true"
    class="mb20"
  >
    <el-icon class="avatar-uploader-icon">
      <Plus />
    </el-icon>
    <template #tip>
      <div class="el-upload__tip">只能上传jpg/png文件，且不超过2M</div>
    </template>
  </el-upload>
</template>
<script setup>
import { ref, reactive } from "vue";
import { Plus } from "@element-plus/icons-vue";
import { ElMessage, ElMessageBox } from "element-plus";
const props = defineProps({
  product_id: Number,
});

const fileList = reactive([]);

const percent = ref(0);

// 上传前校验
const beforeUpload = (file) => {
  console.log("上传前端校验", file);
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
  console.log("handleSuccessres", res);
  console.log("handleSuccessfile", file);
  if (res) {
    ElMessage.success({
      message: "上传成功!",
    });
  } else {
    ElMessage.error({
      message: "上传失败，请重新上传",
    });
  }
};

//上传文件
const handleChange = (file, fileList) => {
  console.log("file---", file);
  console.log("fileList---", fileList);
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
        let index = fileList.indexOf(file)
        fileList.splice(index,1)
        // 请求删除接口
      })
      .catch(() => {
        reject(false);
      });
  });
  return result;
};
</script>

<style lang="less" scoped></style>
