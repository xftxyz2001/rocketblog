<template>
  <div v-if="isempty"><el-empty description="你还没有收藏哦！" /></div>
  <el-card
    v-else
    v-for="collect in collects"
    :key="collect.id"
    shadow="hover"
    style="height: 110px; overflow: hidden"
  >
    <el-row :gutter="20" @click="collectdetail(collect.userid, collect.blogId)">
      <el-col :span="3" style="text-align: center"
        ><div class="grid-content ep-bg-purple" />
        <img :src="collect.coverImage" alt="" style="width: 70px; height: 70px"
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
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import router from "@/router";
const isempty = ref(false);
const collects = ref([]);
axios.get("/blog/collects").then((res) => {
  collects.value = res.data.data.list;
  if (collects.value.length == 0) {
    isempty.value = true;
  }
});
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
    params: { userid: userid, blogid: blogid },
  });
  // Bus.emit("clickcollect", { userid: userid, collectid: collectid });
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