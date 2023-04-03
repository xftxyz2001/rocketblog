<template>
  <el-header style="padding: 0; margin: 0"><Header></Header></el-header>

  <el-row style="padding-top: 80px; margin-left: 30px">
    <el-col :span="5">
      <div class="grid-content ep-bg-purple" />
      <el-card class="box-card" style="width: 100%">
        <template #header>
          <div class="card-header">
            <div style="margin-bottom: 20px">
              <div
                style="
                  overflow: hidden;
                  width: 30px;
                  display: inline-block;
                  vertical-align: bottom;
                  border-radius: 15px;
                "
              >
                <img :src="userdata.avatar" alt="" style="width: 30px" />
              </div>
              <span style="font-size: 5px">{{ userdata.username }}</span>
            </div>
          </div>
          <el-row :gutter="15" style="width: 100%; text-align: center">
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" />
              关注
            </el-col>
            <el-col :span="8"
              >粉丝
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
            <el-col :span="8"
              >{{ userdata.followers }}
              <div class="grid-content ep-bg-purple" />
            </el-col>
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" />
              {{ userdata.blogs }}
            </el-col>
          </el-row>
        </template>
        <div v-if="isme">
          <el-row :gutter="100">
            <el-col :span="10" :offset="2">
              <div class="grid-content ep-bg-purple" />
              <el-button>私信</el-button>
            </el-col>
            <el-col :span="10">
              <div class="grid-content ep-bg-purple-light" />
              <el-button v-if="userdata.followed" @click="dontfollowthis"
                >已关注</el-button
              >
              <el-button v-else type="primary" @click="followthis"
                >关注</el-button
              >
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
          </div>
        </template>
        <div v-html="blogdata.blogContent"></div>
        <el-divider border-style="dashed" />
        <el-row :gutter="10" style="width: 100%; text-align: center">
          <el-col v-if="blogdata.like" :span="7">
            <div class="grid-content ep-bg-purple" />
            <span
              class="iconfont"
              style="margin-right: 10px; cursor: pointer"
              @click="dontlikethis"
              >&#xe60f;</span
            >点赞 {{ blogdata.likeCount }}
          </el-col>
          <el-col v-else :span="7">
            <div class="grid-content ep-bg-purple" />
            <span
              class="iconfont"
              style="margin-right: 10px; cursor: pointer; color: black"
              @click="likethis"
              >&#xe600;</span
            >点赞{{ blogdata.likeCount }}
          </el-col>
          <el-col v-if="blogdata.collect" :span="7">
            <span
              class="iconfont"
              style="margin-right: 5px; cursor: pointer"
              @click="dontcollectthis"
              >&#xe630;</span
            >收藏 {{ blogdata.bookmarkCount }}
            <div class="grid-content ep-bg-purple" />
          </el-col>
          <el-col v-else :span="7">
            <span
              class="iconfont"
              style="margin-right: 5px; cursor: pointer"
              @click="collectthis"
            >
              &#xe603; </span
            >收藏 {{ blogdata.bookmarkCount }}
            <div class="grid-content ep-bg-purple" />
          </el-col>

          <el-col :span="9">
            <div class="grid-content ep-bg-purple" />
            评论 {{ blogdata.commentCount }}
          </el-col>
          <el-input
            v-model.trim="commenttext"
            maxlength="100"
            placeholder="请文明评论"
            show-word-limit
            type="textarea"
            style="margin: 20px 0 0 5px"
          />
          <el-row style="margin: 10px 0 0 0; position: relative; width: 100%"
            ><el-button
              type="primary"
              style="position: absolute; right: 0px"
              @click="commentthis"
              >发表</el-button
            ></el-row
          >
        </el-row>
        <div style="height: 30px"></div>
        <el-divider border-style="dashed" />
        <div
          v-for="comment in comments"
          :key="comment.commentId"
          style="padding: 10px 0 0 10px"
        >
          <div style="">
            <img
              :src="comment.avatar"
              alt=""
              style="width: 30px; border-radius: 15px; vertical-align: bottom"
            />
            <div style="display: inline-block; padding: 0 0 0 7px">
              <div>{{ comment.username }}</div>
              <div style="font-size: 5px">{{ comment.createtime }}</div>
            </div>
          </div>
          <el-row style="display: inline-block; padding: 5px 0 0 45px">{{
            comment.commentContent
          }}</el-row>
        </div>

        <el-divider border-style="dashed" />
      </el-card>
    </el-col>
  </el-row>
</template>
<script setup >
import { ElMessage } from "element-plus";
import axios from "axios";
import { getCurrentInstance, ref } from "vue";
import { useRoute } from "vue-router";
const { Bus } = getCurrentInstance().appContext.config.globalProperties;
const route = useRoute();
const commenttext = ref("");
const userdata = ref({
  followers: "",
  followings: "",
  blogs: "",
  avatar: "",
  followed: "",
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
  like: "",
  collect: "",
});
const comments = ref([]);
const isme = ref(false);
console.log(route.params);

axios
  .get("http://8.130.81.23:8080/user/info/" + route.params.userid)
  .then((res) => {
    userdata.value = res.data.data;
    axios.get("http://8.130.81.23:8080/user/info").then((res) => {
      if (userdata.value.username != res.data.data.username) {
        isme.value = true;
      }
    });
  });
// axios.get("http://8.130.81.23:8080/user/info").then((res) => {
//   if (userdata.value.username == res.data.data.username) {
//     isme.value = true;
//   }
// });

axios
  .get("http://8.130.81.23:8080/blog/detail/" + route.params.blogid)
  .then((res) => {
    blogdata.value = res.data.data;
  });

axios
  .get("http://8.130.81.23:8080/blog/comment/" + route.params.blogid)
  .then((res) => {
    comments.value = res.data.data;
  });
function commentthis() {
  //发布评论
  // comments.value.unshift(commenttext.value);
  // console.log(comments.value);
  // console.log(commenttext.value);

  if (localStorage.getItem("token")) {
    var commentdata = {
      blogId: route.params.blogid,
      commentContent: commenttext.value,
    };
    axios
      .post("http://8.130.81.23:8080/blog/comment", commentdata)
      .then((res) => {
        if (res.data.code == "200") {
          ElMessage({
            showClose: true,
            message: "发表成功",
            type: "success",
          });

          comments.value.unshift(res.data.data);

          commenttext.value = "";
        }
      });
  } else {
    Bus.emit("commentneedlogin", {});
  }
}
function dontfollowthis() {
  if (localStorage.getItem("token")) {
    axios
      .delete("http://8.130.81.23:8080/user/follow/" + route.params.userid)
      .then((res) => {
        userdata.value.followed = false;
      });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function followthis() {
  if (localStorage.getItem("token")) {
    axios
      .get("http://8.130.81.23:8080/user/follow/" + route.params.userid)
      .then((res) => {
        userdata.value.followed = true;
      });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function likethis() {
  if (localStorage.getItem("token")) {
    axios
      .get("http://8.130.81.23:8080/blog/like/" + route.params.blogid)
      .then((res) => {
        blogdata.value.like = true;
        blogdata.value.likeCount = res.data.data;
      });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function collectthis() {
  if (localStorage.getItem("token")) {
    axios
      .get("http://8.130.81.23:8080/blog/collect/" + route.params.blogid)
      .then((res) => {
        blogdata.value.collect = true;
        blogdata.value.bookmarkCount = res.data.data;
      });
  } else {
    Bus.emit("collectneedlogin", {});
  }
}
function dontcollectthis() {
  console.log(blogdata.value.collect);
  if (localStorage.getItem("token")) {
    axios
      .delete("http://8.130.81.23:8080/blog/collect/" + route.params.blogid)
      .then((res) => {
        blogdata.value.collect = false;
        blogdata.value.bookmarkCount = res.data.data;
      });
  } else {
    Bus.emit("collectneedlogin", {});
  }
}
function dontlikethis() {
  if (localStorage.getItem("token")) {
    axios
      .delete("http://8.130.81.23:8080/blog/like/" + route.params.blogid)
      .then((res) => {
        blogdata.value.like = false;
        blogdata.value.likeCount = res.data.data;
      });
  } else {
    Bus.emit("likeneedlogin", {});
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
import Header from "@/views/usersub/header.vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "blogdetail",
  components: { Header },
});
</script>

<style scoped>
/* * {
  padding: 0;
  margin: 0;
} */
.affix-container {
  text-align: center;
  height: 50px;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
}
.el-header {
  position: fixed;
  z-index: 100;
  width: 100%;
  height: 60px;
  margin-top: -7px;
  margin-left: -8px;

  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.15), 0 6px 20px 0 rgba(0, 0, 0, 0.1);
}
.el-main {
  position: absolute;
  left: 0px;
  right: 0;
  top: 60px;
  bottom: 0;
  background-color: #f1f2f5;
  height: 2000px;
}
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