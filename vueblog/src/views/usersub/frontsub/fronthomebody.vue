<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4" style="position: relative">
      <FrontHomeLeft></FrontHomeLeft>

      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="10" style="position: fixed">
      <!-- 轮播图 -->

      <div style="position: fixed; width: 100%; top: 50px">
        <div style="height: 15px"></div>
        <router-view></router-view>
      </div>
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4"
      >1235
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

axios.get("/blog/hot").then((res) => {
  hotblogs.value = res.data.data.list;
});

function clickImg(blog) {
  router.push({
    name: "blogdetail",
    params: { userid: blog.userid, blogid: blog.blogId },
  });
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
</style>