<template>
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
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import router from "@/router";
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
</style>