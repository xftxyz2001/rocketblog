<template>
    <el-header style="text-align: right; font-size: 12px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="标题">
          <el-input
            v-model="formInline.blogTitle"
            placeholder="请输入标题"
            style="width: 200px"
          />
        </el-form-item>
  
        <el-form-item label="用户id">
          <el-input
            v-model="formInline.userid"
            placeholder="请输入用户id"
            style="width: 200px"
          />
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-select
            v-model="formInline.blogStatus"
            placeholder="Please select a zone"
            style="width: 180px"
          >
            <el-option label="草稿" value="草稿" />
            <el-option label="已发布" value="已发布" />
          </el-select>
        </el-form-item>
  
        <el-form-item>
          <el-button type="primary" @click="selectBlog">查找</el-button>
        </el-form-item>
      </el-form>
      <el-button type="primary" plain class="adduser" @click="addblog">
        添加博客</el-button
      >
    </el-header>
    <div></div>
    <el-main>
      <el-scrollbar>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column fixed prop="createTime" label="创建日期" width="160" />
          <el-table-column
            class="idcolumn"
            prop="blogId"
            label="id"
            v-if="false"
          />
          <el-table-column prop="blogTitle" label="标题" width="180" />
          <el-table-column prop="userid" label="用户id" width="70" />
          <el-table-column prop="blogStatus" label="状态" width="90">
            <template v-slot="scope">
              <el-tag
                v-if="scope.row.blogStatus == 0"
                type="success"
                disable-transitions
                >草稿</el-tag
              >
              <el-tag
                v-else-if="scope.row.blogStatus == 1"
                type="success"
                disable-transitions
                >已发布</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="更新时间" width="160" />
          <el-table-column prop="coverImage" label="封面图片" width="150">
            <template v-slot="scope">
              <el-image
                style="width: 100px; height: 100px"
                :src="scope.row.coverImage"
                :fit="fit"
              ></el-image>
            </template>
          </el-table-column>
          <el-table-column prop="blogSummary" label="摘要" width="300" />
  
          <el-table-column fixed="right" label="选项" width="120">
            <template v-slot="scope">
  
            <el-button
                link
                type="primary"
                size="small"
                @click="editblog(scope.row.blogId)"
                >编辑</el-button
              >
              <el-button
                link
                type="primary"
                size="small"
                @click="deleteblog(scope.row.blogId)"
                >删除</el-button
              >
  
            </template>
          </el-table-column>
        </el-table>
      </el-scrollbar>
    </el-main>
  </template>
  <script setup>
  import { reactive, ref } from "vue";
  import axios from "axios";
  const formLabelWidth = "140px";
  const formInline = ref({});
  const tableData = ref([]);
  axios.get("/admin/blogs").then((res) => {
    tableData.value = res.data.data.list;
  });
  function selectBlog() {
    var blogstatus;
    switch (formInline.value.blogStatus) {
      case "草稿":
        blogstatus = 0;
        break;
      case "已发布":
        blogstatus = 1;
        break;
    }
  
    var req = {
      blogTitle: formInline.value.blogTitle,
      userid: formInline.value.userid,
      blogStatus: blogstatus,
    };
  
    axios({
      method: "POST",
      url: "/admin/search/blog",
      Headers: { "Content-Type": "application/json" },
      data: req,
    }).then((res) => {
      tableData.value = res.data;
      for (let index = 0; index < tableData.value.length; index++) {
        console.log(res.data[index].blogStatus);
        switch (res.data[index].blogStatus) {
          case 0:
            tableData.value[index].blogStatus = "草稿";
            break;
          case 1:
            tableData.value[index].blogStatus = "已发布";
            break;
          default:
            tableData.value[index].blogStatus = "未知";
        }
      }
    });
  }
  
  function editblog(blogid) {
    console.log("editblog" + blogid);
    // axios.put("/admin/blog/" + blogid);
  }
  function deleteblog(blogid) {
    console.log("deleteblog" + blogid);
    // axios.delete("/admin/blog/" + blogid);
  }
  </script>
  <script>
  export default {
    name: "issueadmin",
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