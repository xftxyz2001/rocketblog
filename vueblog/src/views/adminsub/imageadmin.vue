<template>
  <el-header style="text-align: right; font-size: 12px">
    <el-upload
      class="upload-demo"
      :action="uploadUrl"
      :auto-upload="false"
      :on-change="handleChange"
      :headers="headers"
      :multiple="true"
      :limit="limit"
      :before-upload="beforeUpload"
      :on-exceed="handleExceed"
      :file-list="fileList"
      list-type="picture-card"
      :on-remove="handleRemove"
    >
    </el-upload>
    <el-button @click="uploadFiles">上传</el-button>
  </el-header>
  <div style="clear: both"></div>
  <el-main>
    <div
      v-infinite-scroll="load"
      class="infinite-list"
      style="overflow: auto"
      infinite-scroll-distance="1"
    >
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="缩略图" width="80">
          <template v-slot="scope">
            <img :src="'/images/' + scope.row.filePath + '/50/50'" alt="" />
          </template>
        </el-table-column>
        <el-table-column prop="filePath" label="文件名" width="600" />
        <el-table-column prop="fileSize" label="文件大小(KB)" width="200">
          <template v-slot="scope">
            {{ (scope.row.fileSize / 1024).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column
          prop="lastModifiedTime"
          label="最后修改时间"
          width="200"
        />
        <el-table-column fixed="right" label="选项" width="180">
          <template v-slot="scope">
            <!-- 下载 -->
            <el-button
              link
              type="primary"
              size="small"
              @click="download(scope.row.filePath)"
              >下载</el-button
            >
            <!-- 查看 -->
            <el-button
              link
              type="primary"
              size="small"
              @click="view(scope.row.filePath)"
              >查看</el-button
            >
            <!-- 删除 -->
            <el-button
              link
              type="danger"
              size="small"
              @click="delete_(scope.row.filePath)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-main>
</template>
<script setup>
import axios from "axios";
import { ElMessage } from "element-plus";
import { ref } from "vue";
const tableData = ref([]);
const fileList = ref([]); // 文件列表
const limit = ref(10); // 上传图片的数量限制
const uploadUrl = "/images/uploads"; // 后端接口地址
const headers = { "Content-Type": "multipart/form-data" }; // 请求头设置
const page = ref(1);
const pagesize = ref(5);
function load() {
  page.value++;
  axios
    .get("/admin/images?pageNum=" + page.value + "&pageSize=" + pagesize.value)
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        tableData.value.push(result.data.list[index]);
      }
    });
}
function getdata() {
  axios.get("/admin/images?pageNum=1&pageSize=5").then((res) => {
    var res = res.data;
    if (res.code == 0) {
      tableData.value = res.data;
    }
  });
}

getdata();

function download(filePath) {
  // 提示下载开始
  ElMessage.success("图片已开始下载");
  window.open("/images/download/" + filePath);
}

function view(filePath) {
  window.open("/images/" + filePath);
}

function delete_(filePath) {
  axios.delete("/admin/image/" + filePath).then((res) => {
    var res = res.data;
    if (res.code == 0) {
      ElMessage.success("删除成功");
      getdata();
    } else {
      ElMessage.error(res.message);
    }
  });
}

// 文件上传前的验证，返回false代表不上传
function beforeUpload(file) {
  const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
  const isLt2M = file.size / 1024 / 1024 < 2;

  if (!isJpgOrPng) {
    ElMessage.error("上传图片只能是 JPG/PNG 格式!");
  }
  if (!isLt2M) {
    ElMessage.error("上传图片大小不能超过 2MB!");
  }

  return isJpgOrPng && isLt2M;
}

// 文件数量超出限制的回调函数
function handleExceed(files, fl) {
  ElMessage.warning("上传图片数量不能超过10个");
}

// 文件列表变化时的回调函数
function handleChange(file, fl) {
  fileList.value = fl;
}

// 文件移除时的回调函数
function handleRemove(file, fl) {
  console.log(file, fl);
}

// 发送请求时，将参数名设置为files
function buildFormData() {
  const formData = new FormData();
  fileList.value.forEach((file, index) => {
    formData.append("files", file.raw);
  });
  return formData;
}

function uploadFiles() {
  const formData = buildFormData();
  axios
    .post(uploadUrl, formData, { headers: headers })
    .then((response) => {
      if (response.data.code == 0) {
        ElMessage.success("上传成功");
        getdata();
        // 上传成功后清空文件列表
        fileList.value = [];
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch((error) => {
      console.log(error);
    });
}
</script>
<script>
export default {
  name: "imageadmin",
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