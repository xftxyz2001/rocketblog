<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4" style="position: relative">
      <FrontHomeLeft></FrontHomeLeft>

      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="10">
      <!-- 轮播图 -->
      <el-carousel indicator-position="outside" style="margin-top: 15px">
        <el-carousel-item class="carousel-item" v-for="blog in hotblogs">
          <img class="carousel-img" :src="blog.coverImage" alt="" srcset="" @click="clickImg(blog)" />
          <h3>{{ blog.blogTitle }}</h3>
        </el-carousel-item>
      </el-carousel>
      <router-view></router-view>
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4">1235
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4">
      <div class="grid-content ep-bg-purple" />
    </el-col>
  </el-row>
</template>

<script setup>
import router from "@/router";
import axios from "axios";
import { ref } from "vue";
const hotblogs = ref([]);

axios
  .get("/blog/hot")
  .then((res) => {
    hotblogs.value = res.data.data.list;
  });

function clickImg(blog) {
  router.push({ name: "blogdetail", params: { userid: blog.userid, blogid: blog.blogId } });
}
</script>

<script>
import FrontHomeLeft from "@/views/usersub/frontsub/fronthomesub/left.vue";
import FrontHomeAllConcernMiddle from "@/views/usersub/frontsub/fronthomesub/middle/allconcernmiddle.vue";
import FrontHomeFriendMiddle from "@/views/usersub/frontsub/fronthomesub/middle/friendmiddle.vue";
import FrontHomeLatestMiddle from "@/views/usersub/frontsub/fronthomesub/middle/latestmiddle.vue";
import FrontHomeMostLikeMiddle from "@/views/usersub/frontsub/fronthomesub/middle/mostlikemiddle.vue";
import { defineComponent } from "vue";
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
.carousel-item {
  width: 100%;
  height: 100%;
  background: white;
  display: flex;
  justify-content: center;
}

.carousel-img {
  max-width: 100%;
  max-height: 100%;
}
</style>