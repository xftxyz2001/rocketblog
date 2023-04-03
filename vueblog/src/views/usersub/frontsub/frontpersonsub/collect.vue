<template>
  <div v-if="isempty"><el-empty description="快去发一条博客吧！" /></div>
  <el-card
    v-else
    v-for="collect in collects"
    :key="collect.id"
    shadow="hover"
    style="height: 100px; overflow: hidden"
  >
    <el-row :gutter="20">
      <el-col :span="3" style="text-align: center"
        ><div class="grid-content ep-bg-purple" />
        <img :src="collect.coverImage" alt="" style="width=100px"
      /></el-col>
      <el-col :span="16"
        ><div class="grid-content ep-bg-purple" />
        <el-row
          ><span style="font-weight: 700">{{
            collect.collectTitle
          }}</span></el-row
        >
        <el-row><div v-html="collect.collectSummary"></div></el-row
      ></el-col> </el-row
  ></el-card>
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
const isempty = ref(false);
const collects = ref([]);
axios.get("http://8.130.81.23:8080/blog/collects").then((res) => {
  collects.value = res.data.data.list;
  if (collects.value.length == 0) {
    isempty.value = true;
  }
});
</script>
<script>
export default {};
</script>

<style>
</style>