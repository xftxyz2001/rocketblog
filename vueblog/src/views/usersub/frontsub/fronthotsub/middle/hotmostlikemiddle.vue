<template>
  <div
    v-infinite-scroll="load"
    class="infinite-list"
    style="overflow: auto"
    infinite-scroll-distance="1"
  >
    <div style="width: 40%; margin: 0 auto; padding-left: 40px">
      <el-card
        v-for="blog in mostlike"
        :key="blog.blogId"
        :data-blogid="blog.blogId"
        :data-userid="blog.userid"
        class="box-card"
      >
        <template #header>
          <div class="card-header">
            <div>
              <el-avatar
                :size="32"
                class="mr-3"
                :src="blog.avatar"
                style="margin: 12px 5px 0px 0px"
              />
              <span
                class="text-large font-600 mr-3"
                style="
                  display: inline-block;
                  overflow: hidden;
                  margin: -3px 5px 3px 5px;
                "
              >
                {{ blog.blogTitle }}
              </span>
            </div>

            <!-- <el-button
          class="button"
          text
          style="color: #24acf2"
          @click="followthis"
          ><el-icon style="margin: 0 2px 1.5px 0"><Plus /></el-icon
          >关注</el-button
        > -->
          </div>
          <span style="font-size: 5px; margin-left: 5px"
            >作者：{{ blog.username }}</span
          >
        </template>

        <el-skeleton style="width: 100%" :loading="loading" animated>
          <template #default>
            <div
              style="padding: 14px; cursor: pointer"
              @click="clickblog(blog.userid, blog.blogId)"
            >
              <el-row>
                <el-col :span="6">
                  <div class="grid-content ep-bg-purple" />
                  <img
                    :src="blog.coverImage"
                    alt=""
                    style="width: 100px; height: 100px"
                    v-if="blog.coverImage != ''"
                  />
                </el-col>
                <el-col :span="12">
                  <div class="grid-content ep-bg-purple-light" />
                  <div
                    style="width: 100%; height: 100px; overflow: hidden"
                    v-html="blog.blogSummary"
                  ></div>
                </el-col>
              </el-row>
            </div>
            <div class="bottom card-header">
              <div class="time" style="font-size: 10px">
                {{ blog.updateTime }}
              </div>
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
          </template>
        </el-skeleton>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import axios from "axios";
import { getCurrentInstance } from "vue";
import { ref } from "vue";
const router = useRouter();
const mostlike = ref([]);
const page = ref(1);
const pagesize = ref(5);
// import bus from "@/utils/bus";
axios.get("/blog/hot/like").then((res) => {
  mostlike.value = res.data.data.list;
});
function load() {
  page.value++;
  axios
    .get("/blog/hot/like?pageNum=" + page.value + "&pageSize=" + pagesize.value)
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        mostlike.value.push(result.data.list[index]);
      }
    });
}
const loading = ref(false);
const currentDate = new Date().toDateString();
const { Bus } = getCurrentInstance().appContext.config.globalProperties;
// function followthis() {
//   if (checkTokenInCookie()) {
//     //关注
//   } else {
//     Bus.emit("followneedlogin", {});
//   }
// }
// function likethis() {
//   if (checkTokenInCookie()) {
//     //点赞
//   } else {
//     Bus.emit("likeneedlogin", {});
//   }
// }
// function collectthis() {
//   if (checkTokenInCookie()) {
//     //收藏
//   } else {
//     Bus.emit("collectneedlogin", {});
//   }
// }
function clickblog(userid, blogid) {
  // var userid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.userid;
  // var blogid = e.target.parentElement.parentElement.parentElement.parentElement.dataset.blogid;
  // console.log(e);
  // const index = Array.from(e.target.parentNode).indexOf(e.target.parentNode.children);
  // console.log(index);
  // console.log(mostlike.value);
  // const userid = mostlike.value[index].userid;
  // const blogid = mostlike.value[index].blogId;
  router.push({
    name: "blogdetail",
    params: { userid: userid, blogid: blogid },
  });
  // Bus.emit("clickblog", { userid: userid, blogid: blogid });
  // console.log(
  //  e.target.parentElement.parentElement.parentElement.parentElement.dataset
  //  );
}
</script>
<script>
export default {};
</script>


<style scoped>
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
  width: 100%;
  margin-bottom: 10px;
  /* vertical-align:baseline; */
}
.infinite-list {
  height: calc(100vh);
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
</style>