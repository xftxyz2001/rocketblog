<template>
  <div
    v-infinite-scroll="load"
    class="infinite-list"
    style="overflow: auto; height: 400px; margin-top: 10px"
    infinite-scroll-distance="1"
  >
    <div v-if="isempty"><el-empty description="你还没有收藏哦！" /></div>
    <el-card
      v-else
      v-for="collect in collects"
      :key="collect.id"
      shadow="hover"
      style="height: 110px; overflow: hidden"
    >
      <el-row
        :gutter="20"
        @click="collectdetail(collect.userid, collect.blogId)"
      >
        <el-col :span="3" style="text-align: center"
          ><div class="grid-content ep-bg-purple" />
          <img
            :src="collect.coverImage"
            alt=""
            style="width: 70px; height: 70px"
        /></el-col>
        <el-col :span="16"
          ><div class="grid-content ep-bg-purple" />
          <el-row
            ><span style="font-weight: 700">{{
              collect.collectTitle
            }}</span></el-row
          >
          <el-row><div v-html="collect.collectSummary"></div></el-row
          ><el-row style="font-size: 8px">{{
            collect.updateTime
          }}</el-row></el-col
        >
      </el-row></el-card
    >
  </div>
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import router from "@/router";
const isempty = ref(false);
const collects = ref([]);
const page = ref(1);
const pagesize = ref(5);
axios.get("/blog/collects").then((res) => {
  collects.value = res.data.data.list;
  if (collects.value.length == 0) {
    isempty.value = true;
  }
});
function load() {
  page.value++;
  axios
    .get(
      "/blog/collects" +
        "?pageNum=" +
        page.value +
        "&pageSize=" +
        pagesize.value
    )
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        collects.value.push(result.data.list[index]);
      }
    });
}
function collectdetail(userid, blogid) {
  // var userid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.userid;
  // var collectid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.collectid;
  // console.log(e);
  // const index = Array.from(e.target.parentNode).indexOf(e.target.parentNode.children);
  // console.log(index);
  // console.log(allconcern.value);
  // const userid = allconcern.value[index].userid;
  // const collectid = allconcern.value[index].collectId;
  router.push({
    name: "blogdetail",
    params: { blogid: blogid },
  });

}
</script>
<script>
export default {};
</script>

<style>
.infinite-list {
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
.infinite-list ::-webkit-scrollbar {
  display: none;
}
</style>