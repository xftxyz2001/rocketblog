<template>
  <el-main>
    <el-scrollbar>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="option" label="操作" width="500" />
        <el-table-column fixed="right" label="选项" width="120">
          <template v-slot="scope">
            <el-button link type="primary" size="small" @click="go(scope.row.url)">前往</el-button>
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

axios.get("/api/admin/system").then((res) => {
  tableData.value = res.data.data;
});

</script>
<script>
export default {
  name: "systemadmin",
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