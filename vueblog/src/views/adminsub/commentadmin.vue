<template>
  <el-header style="text-align: right; font-size: 12px">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="博客id">
        <el-input v-model="formInline.blogId" placeholder="请输入博客id" style="width: 200px" />
      </el-form-item>

      <el-form-item label="用户id">
        <el-input v-model="formInline.userid" placeholder="请输入用户id" style="width: 200px" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="selectComment">查找</el-button>
      </el-form-item>
    </el-form>
  </el-header>
  <div></div>
  <el-main>
    <el-scrollbar>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column fixed prop="createtime" label="创建日期" width="180" />
        <el-table-column class="idcolumn" prop="commentId" label="id" v-if="false" />
        <el-table-column prop="commentContent" label="评论内容" width="720" />
        <el-table-column prop="blogId" label="博客id" width="90">
          <template v-slot="scope">
            <div @click="goblog(scope.row.userid, scope.row.blogId)">{{ scope.row.blogId }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="userid" label="用户id" width="90">
          <template v-slot="scope">
            <div @click="gouser(scope.row.userid)">{{ scope.row.userid }}</div>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="选项" width="120">
          <template v-slot="scope">
            <el-button link type="danger" size="small" @click="deletecomment(scope.row.commentId)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
  </el-main>
</template>
<script setup>
import router from "@/router";
import axios from "axios";
import { ref } from "vue";
const formLabelWidth = "140px";
const formInline = ref({});
const tableData = ref([]);
axios.get("/admin/comments").then((res) => {
  tableData.value = res.data.data.list;
});
function selectComment() {
  var req = {
    blogId: formInline.value.blogId,
    userid: formInline.value.userid,
  };
  axios.post("/admin/search/comment", req).then((res) => {
    tableData.value = res.data.data.list;
  });
}
function goblog(userid, blogId) {
  router.push({ name: "blogdetail", params: { blogid: blogId } });
}
function gouser(userid) {
  router.push({ name: "otherperson", params: { userid: userid } });
}
function deletecomment(commentid) {
  axios.delete("/admin/comment/" + commentid).then((res) => {
    console.log("删除成功，刷新页面");
  });
}
</script>
<script>
export default {
  name: "commentadmin",
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