<template>
  <el-header><el-page-header @back="back">
      <template #content> </template> </el-page-header></el-header>
  <el-row>
    <el-col :span="5">
      <div class="grid-content ep-bg-purple" />
      <el-card class="box-card" style="width: 100%">
        <template #header>
          <div class="card-header">
            <div>
              <img :src="userdata.avatar" alt="" /><span>{{
                userdata.username
              }}</span>
            </div>
            <el-button class="button" text>Operation button</el-button>
          </div>
          <el-row :gutter="15" style="width: 100%; text-align: center">
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" />
              关注
            </el-col>
            <el-col :span="8">粉丝
              <div class="grid-content ep-bg-purple" />
            </el-col>
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" />
              博客
            </el-col>
          </el-row>
          <el-row :gutter="15" style="width: 100%; text-align: center">
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" />
              {{ userdata.followings }}
            </el-col>
            <el-col :span="8">{{ userdata.followers }}
              <div class="grid-content ep-bg-purple" />
            </el-col>
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" />
              {{ userdata.blogs }}
            </el-col>
          </el-row>
        </template>
        <div>
          <el-row :gutter="100">
            <el-col :span="10">
              <div class="grid-content ep-bg-purple" />
              <el-button>Default</el-button>
            </el-col>
            <el-col :span="10">
              <div class="grid-content ep-bg-purple-light" />
              <el-button type="primary">Primary</el-button>
            </el-col>
          </el-row>
        </div>
        <div class="bottom card-header">
          <div class="time" style="font-size: 10px"></div>
          <!-- <div>
            <span
              class="iconfont"
              style="margin-right: 10px; cursor: pointer"
              @click="likethis"
              >&#xe600;</span
            >
            <span
              class="iconfont"
              style="margin-right: 5px; cursor: pointer"
              @click="collectthis"
              >&#xe603;</span
            >
          </div> -->
        </div>
      </el-card>
    </el-col>
    <el-col :span="17">
      <div class="grid-content ep-bg-purple-light" />
      <el-card class="box-card" style="width: 100%; margin-left: 30px">
        <template #header>
          <div class="card-header">
            <span>{{ blogdata.blogTitle }}</span>
            <el-button class="button" text>Operation button</el-button>
          </div>
        </template>
        <div>{{ blogdata.blogContent }}</div>
        <el-divider border-style="dashed" />
        <el-row :gutter="10" style="width: 100%; text-align: center">
          <el-col :span="7">
            <div class="grid-content ep-bg-purple" />
            <span class="iconfont" style="margin-right: 10px; cursor: pointer" @click="likethis">&#xe600;</span>点赞 {{
              blogdata.likeCount }}
          </el-col>
          <el-col :span="7">
            <span class="iconfont" style="margin-right: 5px; cursor: pointer" @click="collectthis">&#xe603;</span>收藏 {{
              blogdata.bookmarkCount }}
            <div class="grid-content ep-bg-purple" />
          </el-col>
          <el-col :span="9">
            <div class="grid-content ep-bg-purple" />
            评论 {{ blogdata.commentCount }}
          </el-col>
          {{ comments }}
        </el-row>
      </el-card>
    </el-col>
  </el-row>
</template>
<script setup >
import axios from "axios";
import { getCurrentInstance, ref } from "vue";
import { useRoute } from "vue-router";
const { Bus } = getCurrentInstance().appContext.config.globalProperties;
const route = useRoute();

const userdata = ref({
  followers: "",
  followings: "",
  blogs: "",
  avatar: "",
  isFollowed: "",
  username: "",
});
const blogdata = ref({
  blogId: "",
  blogTitle: "",
  userid: "",
  username: "",
  avatar: "",
  blogStatus: "",
  createTime: "",
  updateTime: "",
  coverImage: "",
  likeCount: "",
  commentCount: "",
  bookmarkCount: "",
  blogContent: "",
});
const comments = ref([]);

console.log(route.params);

axios.get("http://8.130.81.23:8080/user/info/" + route.params.userid).then((res) => {
  userdata.value = res.data.data;
});

axios.get("http://8.130.81.23:8080/blog/detail/" + route.params.blogid).then((res) => {
  blogdata.value = res.data.data;
});

axios.get("http://8.130.81.23:8080/blog/comment/" + route.params.blogid).then((res) => {
  comments.value = res.data.data;
});

function likethis() {
  if (localStorage.getItem("token")) {
    axios.get("http://8.130.81.23:8080/blog/like/" + route.params.blogid).then((res) => {
      blogdata.value.likeCount = res.data.data;
    });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function collectthis() {
  if (localStorage.getItem("token")) {
    axios.get("http://8.130.81.23:8080/blog/collect/" + route.params.blogid).then((res) => {
      blogdata.value.likeCount = res.data.data;
    });
  } else {
    Bus.emit("collectneedlogin", {});
  }
}

// Bus.on("clickblog", (data) => {
//   console.log(data.userid);
//   axios.get("http://8.130.81.23:8080/user/info/" + data.userid).then((res) => {
//     userdata.value = res.data.data;
//     // console.log(userdata.value);
//   });
// });
</script>
<script>
export default {};
</script>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
}
</style>