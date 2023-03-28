<template>
  <el-row :gutter="20">
    <el-col :span="4"> <div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="4" style="position: relative"
      ><FrontHomeLeft></FrontHomeLeft>

      <div class="grid-content ep-bg-purple"
    /></el-col>
    <el-col :span="10">
      <!-- 轮播图 -->
      <el-carousel indicator-position="outside" style="margin-top: 15px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 text="2xl" justify="center">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
      <router-view></router-view>
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
import FrontHomeLeft from "@/views/usersub/frontsub/fronthomesub/left.vue";
import FrontHomeAllConcernMiddle from "@/views/usersub/frontsub/fronthomesub/middle/allconcernmiddle.vue";
import FrontHomeFriendMiddle from "@/views/usersub/frontsub/fronthomesub/middle/friendmiddle.vue";
import FrontHomeLatestMiddle from "@/views/usersub/frontsub/fronthomesub/middle/latestmiddle.vue";
import FrontHomeMostLikeMiddle from "@/views/usersub/frontsub/fronthomesub/middle/mostlikemiddle.vue";
export default defineComponent({
  name: "FrontHomeBody",
  components: {
    FrontHomeLeft,
    FrontHomeAllConcernMiddle,
    FrontHomeFriendMiddle,
    FrontHomeLatestMiddle,
    FrontHomeMostLikeMiddle,
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
.el-carousel__item h3 {
  display: flex;
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.el-carousel__container {
  z-index: -100;
}
</style>