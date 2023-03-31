<template>
  <el-row :gutter="20">
    <el-col :span="4"> <div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="4"
      ><Left></Left>
      <div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :span="10"
      ><router-view></router-view>
      <div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :span="4"
      >1235
      <div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :span="4"> <div class="grid-content ep-bg-purple" /></el-col>
  </el-row>
</template>

<script>
import { defineComponent } from "vue";
import { Search } from "@element-plus/icons-vue";
import Left from "@/views/usersub/frontsub/fronthotsub/left.vue";
export default defineComponent({
  name: "FrontHotBody",
  components: {
    Left,
  },
});

import { ref } from "vue";
</script>

<style scoped>
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
</style>