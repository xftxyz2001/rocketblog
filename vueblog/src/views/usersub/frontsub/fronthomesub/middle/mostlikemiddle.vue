<template>
  <div
    v-infinite-scroll="load"
    class="infinite-list"
    style="overflow: auto"
    infinite-scroll-distance="1"
  >
    <div style="width: 35%; margin: 0 auto; padding-left: 53px">
      <el-carousel
        indicator-position="outside"
        style="width: 100%; padding-top: 10px"
      >
        <el-carousel-item
          class="carousel-item"
          v-for="blog in hotblogs"
          :key="blog.id"
        >
          <img
            class="carousel-img"
            :src="blog.coverImage"
            alt=""
            srcset=""
            @click="clickImg(blog)"
          />
          <h3>{{ blog.blogTitle }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div
      style="width: 40%; margin: 0 auto; padding-top: 10px; padding-left: 50px"
    >
      <el-empty description="快去关注你感兴趣的人吧！" v-if="isempty" />
      <el-card
        v-else
        v-for="blog in allconcern"
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
const allconcern = ref([]);
const page = ref(1);
const pagesize = ref(5);
const hotblogs = ref([]);

axios.get("/blog/hot").then((res) => {
  hotblogs.value = res.data.data.list;
});

function clickImg(blog) {
  router.push({
    name: "blogdetail",
    params: { blogid: blog.blogId },
  });
}
// import bus from "@/utils/bus";
axios.get("/blog/hot/collect?pageNum=1&pageSize=5").then((res) => {
  allconcern.value = res.data.data.list;
});
const loading = ref(false);
const currentDate = new Date().toDateString();
const { Bus } = getCurrentInstance().appContext.config.globalProperties;

function clickblog(userid, blogid) {

  router.push({
    name: "blogdetail",
    params: { blogid: blogid },
  });

}
function load() {
  page.value++;
  axios
    .get(
      "/blog/hot/collect?pageNum=" + page.value + "&pageSize=" + pagesize.value
    )
    .then((res) => {
      var result = res.data;
      for (let index = 0; index < result.data.list.length; index++) {
        allconcern.value.push(result.data.list[index]);
      }
    });
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