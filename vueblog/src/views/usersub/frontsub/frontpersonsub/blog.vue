<template>
  <div v-if="isempty"><el-empty description="快去发一条博客吧！" /></div>
  <el-card
    v-else
    v-for="blog in blogs"
    :key="blog.id"
    shadow="hover"
    style="height: 110px; overflow: hidden"
  >
    <el-row :gutter="20" @click="blogdetail(blog.userid, blog.blogId)">
      <el-col :span="3" style="text-align: center"
        ><div class="grid-content ep-bg-purple" />
        <img :src="blog.coverImage" alt="" style="width: 70px; height: 70px"
      /></el-col>
      <el-col :span="16"
        ><div class="grid-content ep-bg-purple" />
        <el-row
          ><span style="font-weight: 700">{{ blog.blogTitle }}</span></el-row
        >
        <el-row><div v-html="blog.blogSummary"></div></el-row
        ><el-row style="font-size: 8px">{{ blog.updateTime }}</el-row></el-col
      >
    </el-row></el-card
  >
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import router from "@/router";
const isempty = ref(false);
const blogs = ref([]);
axios.get("http://8.130.81.23:8080/blog/my").then((res) => {
  blogs.value = res.data.data.list;
  if (blogs.value.length == 0) {
    isempty.value = true;
  }
});
function blogdetail(userid, blogid) {
  // var userid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.userid;
  // var blogid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.blogid;
  // console.log(e);
  // const index = Array.from(e.target.parentNode).indexOf(e.target.parentNode.children);
  // console.log(index);
  // console.log(allconcern.value);
  // const userid = allconcern.value[index].userid;
  // const blogid = allconcern.value[index].blogId;
  router.push({
    name: "blogdetail",
    params: { userid: userid, blogid: blogid },
  });
  // Bus.emit("clickblog", { userid: userid, blogid: blogid });
  // console.log(
  //  e.target.parentElement.parentElement.parentElement.parentElement.dataset
  //  );
}
</script>
<script>
export default {};
</script>

<style>
</style>