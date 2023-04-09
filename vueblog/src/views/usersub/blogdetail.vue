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
                <img
                  :src="userdata.avatar"
                  alt=""
                  style="width: 30px"
                  @click="gotootherperson(userdata.userid)"
                />
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
              <el-button>修改</el-button>
            </el-col>
            <el-col :span="10">
              <div class="grid-content ep-bg-purple-light" />
              <el-button @click="deletethis">删除</el-button>
            </el-col>
          </el-row>
        </div>
        <div v-else>
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

          <el-col :span="7">
            <span class="iconfont" style="margin-right: 5px; cursor: pointer">
              &#xe6ad;
            </span>
            评论 {{ blogdata.commentCount }}
            <div class="grid-content ep-bg-purple" />
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
          v-infinite-scroll="load"
          class="infinite-list"
          style="overflow: auto"
          infinite-scroll-distance="1"
        >
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
        </div>
        <el-divider border-style="dashed" />
      </el-card>
    </el-col>
  </el-row>
</template>
<script setup >
import axios from "axios";
import { ElMessage } from "element-plus";
import { getCurrentInstance, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
const { Bus } = getCurrentInstance().appContext.config.globalProperties;
const route = useRoute();
const router = useRouter();
const commenttext = ref("");
const page = ref(1);
const pagesize = ref(5);
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
function gotootherperson(userid) {
  router.push({
    name: "otherperson",
    params: { userid: userid },
  });
}
// axios
//   .get("/user/info/" + route.params.userid)
//   .then((res) => {
//     userdata.value = res.data.data;
//     axios.get("/user/i").then((res) => {
//       if (userdata.value.username != res.data.data.username) {
//         isme.value = true;
//       }
//     });
//   });
// axios.get("/user/i").then((res) => {
//   if (userdata.value.username == res.data.data.username) {
//     isme.value = true;
//   }
// });

// axios
//   .get("/blog/detail/" + route.params.blogid)
//   .then((res) => {
//     blogdata.value = res.data.data;
//   });

// axios
//   .get("/blog/comment/" + route.params.blogid)
//   .then((res) => {
//     comments.value = res.data.data;
//   });
function load() {
  page.value++;
  axios
    .get(
      "/blog/comment/" +
        route.params.blogid +
        "?pageNum=" +
        page.value +
        "&pageSize=" +
        pagesize.value
    )
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        comments.value.push(result.data.list[index]);
      }
    });
}
// 请求博客详情
axios.get("/blog/detail/" + route.params.blogid).then((res) => {
  var resultBlogDetail = res.data;
  if (resultBlogDetail.code == 0) {
    blogdata.value = resultBlogDetail.data;
    // 请求评论
    axios.get("/blog/comment/" + route.params.blogid).then((res) => {
      var resultComment = res.data;
      if (resultComment.code == 0) {
        // 评论分页数据
        var commentPage = resultComment.data;
        comments.value = commentPage.list;
      } else {
        // 获取评论失败
        ElMessage({
          showClose: true,
          message: resultComment.message,
        });
      }
    });

    // 请求作者信息
    axios.get("/user/info/" + blogdata.value.userid).then((res) => {
      var resultUserInfo = res.data;
      if (resultUserInfo.code == 0) {
        userdata.value = resultUserInfo.data;
        // 判断是否是自己
        axios.get("/user/i").then((res) => {
          var resultMyInfo = res.data;
          if (resultMyInfo.code == 0) {
            if (userdata.value.userid == resultMyInfo.data.userid) {
              isme.value = true;
            }
          } else {
            // 没登陆或获取个人信息失败
            // ElMessage({
            //   showClose: true,
            //   message: resultMyInfo.message,
            // });
          }
        });
      } else {
        // 获取作者信息失败
        ElMessage({
          showClose: true,
          message: resultUserInfo.message,
        });
      }
    });
  } else {
    // 获取博客详情失败
    ElMessage({
      showClose: true,
      message: resultBlogDetail.message,
    });
  }
});

function checkTokenInCookie() {
  var cookies = document.cookie;
  return cookies.indexOf("token=") != -1;
}

function commentthis() {
  //发布评论
  // comments.value.unshift(commenttext.value);
  // console.log(comments.value);
  // console.log(commenttext.value);

  if (checkTokenInCookie()) {
    var commentdata = {
      blogId: route.params.blogid,
      commentContent: commenttext.value,
    };
    axios.post("/blog/comment", commentdata).then((res) => {
      if (res.data.code == 0) {
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
  if (checkTokenInCookie()) {
    axios.delete("/user/follow/" + route.params.userid).then((res) => {
      userdata.value.followed = false;
    });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function followthis() {
  if (checkTokenInCookie()) {
    axios.get("/user/follow/" + route.params.userid).then((res) => {
      userdata.value.followed = true;
    });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function likethis() {
  if (checkTokenInCookie()) {
    axios.get("/blog/like/" + route.params.blogid).then((res) => {
      blogdata.value.like = true;
      blogdata.value.likeCount = res.data.data;
    });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
function collectthis() {
  if (checkTokenInCookie()) {
    axios.get("/blog/collect/" + route.params.blogid).then((res) => {
      blogdata.value.collect = true;
      blogdata.value.bookmarkCount = res.data.data;
    });
  } else {
    Bus.emit("collectneedlogin", {});
  }
}
function dontcollectthis() {
  console.log(blogdata.value.collect);
  if (checkTokenInCookie()) {
    axios.delete("/blog/collect/" + route.params.blogid).then((res) => {
      blogdata.value.collect = false;
      blogdata.value.bookmarkCount = res.data.data;
    });
  } else {
    Bus.emit("collectneedlogin", {});
  }
}
function dontlikethis() {
  if (checkTokenInCookie()) {
    axios.delete("/blog/like/" + route.params.blogid).then((res) => {
      blogdata.value.like = false;
      blogdata.value.likeCount = res.data.data;
    });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
// 删除博客
function deletethis() {
  if (checkTokenInCookie()) {
    axios.delete("/blog/delete/" + route.params.blogid).then((res) => {
      if (res.data.code == 0) {
        ElMessage({
          showClose: true,
          message: "删除成功",
          type: "success",
        });
        router.push({ name: "hotlatest" });
      }
    });
  } else {
    Bus.emit("likeneedlogin", {});
  }
}
// Bus.on("clickblog", (data) => {
//   console.log(data.userid);
//   axios.get("/user/info/" + data.userid).then((res) => {
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
.infinite-list {
  height: 250px;
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