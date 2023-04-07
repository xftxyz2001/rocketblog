<template>
  <el-row :gutter="30">
    <el-col :span="3" :offset="4"
      ><div class="grid-content ep-bg-purple" />
      11</el-col
    >
    <el-col :span="8" :offset="1"
      ><div class="grid-content ep-bg-purple-light" />
      2</el-col
    >
  </el-row>
</template>

<script setup>
const pageInfo = ref({});
axios.get("/user/chat/sessions").then((res) => {
  var result = res.data;
  if (result.code == 0) {
    pageInfo.value = result.data;
  } else {
    console.log(result.message);
  }
});
</script>

<script>
import axios from "axios";
import { ref } from "vue";
export default {};
</script>

<style></style>