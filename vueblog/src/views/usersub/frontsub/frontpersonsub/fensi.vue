<template>
  <div
    v-infinite-scroll="load"
    class="infinite-list"
    style="overflow: auto; height: 400px; margin-top: 10px"
    infinite-scroll-distance="1"
  >
    <div v-if="isempty"><el-empty description="还没有人关注你哦！" /></div>
    <el-card
      v-else
      v-for="fensi in fensis"
      :key="fensi.id"
      shadow="hover"
      style="height: 80px; overflow: hidden"
    >
      <el-row :gutter="20" @click="fensidetail(fensi.userid)">
        <el-col :span="3" style="text-align: center"
          ><div class="grid-content ep-bg-purple" />
          <img
            :src="fensi.avatar"
            alt=""
            style="width: 40px; height: 40px; border-radius: 20px"
        /></el-col>
        <el-col :span="16"
          ><div class="grid-content ep-bg-purple" />
          <el-row style="padding-top: 7px"
            ><span style="font-weight: 700">{{ fensi.username }}</span></el-row
          >
        </el-col>
      </el-row></el-card
    >
  </div>
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import router from "@/router";
const page = ref(1);
const pagesize = ref(5);
const isempty = ref(false);
const fensis = ref([]);
axios.get("/user/followers").then((res) => {
  fensis.value = res.data.data.list;
  if (fensis.value.length == 0) {
    isempty.value = true;
  }
});
function fensidetail(userid) {
  router.push({
    name: "otherperson",
    params: { userid: userid },
  });
}
function load() {
  page.value++;
  axios
    .get(
      "/user/followers" +
        "?pageNum=" +
        page.value +
        "&pageSize=" +
        pagesize.value
    )
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        fensis.value.push(result.data.list[index]);
      }
    });
}
// function fensidetail(fensiid, fensiid) {
//   // var fensiid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.fensiid;
//   // var fensiid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.fensiid;
//   // console.log(e);
//   // const index = Array.from(e.target.parentNode).indexOf(e.target.parentNode.children);
//   // console.log(index);
//   // console.log(allconcern.value);
//   // const fensiid = allconcern.value[index].fensiid;
//   // const fensiid = allconcern.value[index].fensiId;
//   router.push({
//     name: "fensidetail",
//     params: { fensiid: fensiid, fensiid: fensiid },
//   });
//   // Bus.emit("clickfensi", { fensiid: fensiid, fensiid: fensiid });
//   // console.log(
//   //  e.target.parentElement.parentElement.parentElement.parentElement.dataset
//   //  );
// }
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