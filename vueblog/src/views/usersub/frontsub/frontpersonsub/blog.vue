<template>
  <div v-if="isempty"><el-empty description="快去发一条博客吧！" /></div>
  <el-card
    v-else
    v-for="blog in blogs"
    :key="blog.id"
    shadow="hover"
    style="height: 100px; overflow: hidden"
  >
    <el-row :gutter="20">
      <el-col :span="3" style="text-align: center"
        ><div class="grid-content ep-bg-purple" />
        <img :src="blog.coverImage" alt="" style="width=100px"
      /></el-col>
      <el-col :span="16"
        ><div class="grid-content ep-bg-purple" />
        <el-row
          ><span style="font-weight: 700">{{ blog.blogTitle }}</span></el-row
        >
        <el-row><div v-html="blog.blogSummary"></div></el-row
      ></el-col> </el-row
  ></el-card>
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
const isempty = ref(false);
const blogs = ref([{ blogTitle: "qeq" }]);
// axios.get("http://8.130.81.23:8080/blog/my").then((res) => {
//   blogs.value = res.data.data.list;
//   if (blogs.value.length == 0) {
//     isempty.value = true;
//   }
// });
</script>
<script>
export default {};
</script>

<style>
</style>