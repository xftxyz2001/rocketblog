<template>
  <div v-if="isempty"><el-empty description="你还没有关注别人哦！" /></div>
  <el-card
    v-else
    v-for="guanzhu in guanzhus"
    :key="guanzhu.id"
    shadow="hover"
    style="height: 80px; overflow: hidden"
  >
    <el-row :gutter="20" @click="guanzhudetail(guanzhu.userid)">
      <el-col :span="3" style="text-align: center"
        ><div class="grid-content ep-bg-purple" />
        <img
          :src="guanzhu.avatar"
          alt=""
          style="width: 40px; height: 40px; border-radius: 20px"
      /></el-col>
      <el-col :span="16"
        ><div class="grid-content ep-bg-purple" />
        <el-row style="padding-top: 7px"
          ><span style="font-weight: 700">{{ guanzhu.username }}</span></el-row
        >
      </el-col>
    </el-row></el-card
  >
</template>

<script setup >
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const isempty = ref(false);
const guanzhus = ref([]);
axios.get("/user/followings").then((res) => {
  guanzhus.value = res.data.data.list;
  if (guanzhus.value.length == 0) {
    isempty.value = true;
  }
});
function guanzhudetail(userid) {
  router.push({
    name: "otherperson",
    params: { userid: userid },
  });
}
// function guanzhudetail(guanzhuid, guanzhuid) {
//   // var guanzhuid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.guanzhuid;
//   // var guanzhuid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.guanzhuid;
//   // console.log(e);
//   // const index = Array.from(e.target.parentNode).indexOf(e.target.parentNode.children);
//   // console.log(index);
//   // console.log(allconcern.value);
//   // const guanzhuid = allconcern.value[index].guanzhuid;
//   // const guanzhuid = allconcern.value[index].guanzhuId;
//   router.push({
//     name: "guanzhudetail",
//     params: { guanzhuid: guanzhuid, guanzhuid: guanzhuid },
//   });
//   // Bus.emit("clickguanzhu", { guanzhuid: guanzhuid, guanzhuid: guanzhuid });
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