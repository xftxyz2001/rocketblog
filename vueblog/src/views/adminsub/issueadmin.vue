<template>
  <el-header style="font-size: 25px">
    <el-link type="primary" :underline="false" href="https://github.com/xftxyz2001/rocketblog/issues"
      target="_blank">前往Github查看</el-link>
  </el-header>
  <div></div>
  <el-main>
    <el-scrollbar>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column class="idcolumn" prop="id" label="id" v-if="false" />
        <el-table-column prop="updated_at" label="更新日期" width="180" />
        <el-table-column prop="title" label="标题" width="300" />
        <el-table-column prop="body" label="内容摘要" width="600" />
        <el-table-column prop="state" label="状态" width="90" :filters="[
          { text: 'OPEN', value: 'open' },
          { text: 'CLOSED', value: 'closed' },
        ]" :filter-method="filterHandler">
          <template v-slot="scope">
            <el-tag v-if="scope.row.state == 'open'" type="success" size="small">OPEN</el-tag>
            <el-tag v-else type="danger" size="small">CLOSED</el-tag>
          </template>
        </el-table-column>

        <el-table-column fixed="right" label="选项" width="120">
          <template v-slot="scope">

            <el-button link type="primary" size="small" @click="look(scope.row.html_url)">查看</el-button>

          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
  </el-main>
</template>
<script setup>
import axios from "axios";
import { ref } from "vue";
const tableData = ref([]);
axios.get("/link", { params: { url: "https://api.github.com/repos/xftxyz2001/rocketblog/issues" } }).then((res) => {
  tableData.value = res.data;
});

function filterHandler(value, row, column) {
  const property = column["state"];
  return row[property] === value;
}

function look(html_url) {
  // 在新页面打开
  window.open(html_url);
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