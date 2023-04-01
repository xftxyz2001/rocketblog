<template>
  <el-header
    ><el-page-header @back="back">
      <template #content>
        <el-avatar
          :size="32"
          class="mr-3"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        />
      </template> </el-page-header
  ></el-header>
  <el-row>
    <el-col :span="5"
      ><div class="grid-content ep-bg-purple" />
      <el-card class="box-card" style="width: 100%">
        <template #header>
          <div class="card-header">
            <div>头像 <span>昵称</span></div>
            <el-button class="button" text>Operation button</el-button>
          </div>
          <el-row :gutter="15" style="width: 100%; text-align: center">
            <el-col :span="8"
              ><div class="grid-content ep-bg-purple" />
              关注</el-col
            >
            <el-col :span="8"
              >粉丝
              <div class="grid-content ep-bg-purple" />
            </el-col>
            <el-col :span="8"
              ><div class="grid-content ep-bg-purple" />
              博客</el-col
            >
          </el-row>
          <el-row :gutter="15" style="width: 100%; text-align: center">
            <el-col :span="8"
              ><div class="grid-content ep-bg-purple" />
              关注</el-col
            >
            <el-col :span="8"
              >粉丝
              <div class="grid-content ep-bg-purple" />
            </el-col>
            <el-col :span="8"
              ><div class="grid-content ep-bg-purple" />
              收藏</el-col
            >
          </el-row>
        </template>
        <div>
          <el-row :gutter="100">
            <el-col :span="10"
              ><div class="grid-content ep-bg-purple" />
              <el-button>Default</el-button>
            </el-col>
            <el-col :span="10"
              ><div class="grid-content ep-bg-purple-light" />
              <el-button type="primary">Primary</el-button></el-col
            >
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
      </el-card></el-col
    >
    <el-col :span="17"
      ><div class="grid-content ep-bg-purple-light" />
      <el-card class="box-card" style="width: 100%; margin-left: 30px">
        <template #header>
          <div class="card-header">
            <span>博客标题</span>
            <el-button class="button" text>Operation button</el-button>
          </div>
        </template>
        <div>博客内容</div>
        <el-divider border-style="dashed" />
        <el-row :gutter="10" style="width: 100%; text-align: center">
          <el-col :span="7"
            ><div class="grid-content ep-bg-purple" />
            <span
              class="iconfont"
              style="margin-right: 10px; cursor: pointer"
              @click="likethis"
              >&#xe600;</span
            >点赞
          </el-col>
          <el-col :span="7">
            <span
              class="iconfont"
              style="margin-right: 5px; cursor: pointer"
              @click="collectthis"
              >&#xe603;</span
            >收藏
            <div class="grid-content ep-bg-purple" />
          </el-col>
          <el-col :span="9"
            ><div class="grid-content ep-bg-purple" />
            评论</el-col
          >
        </el-row>
      </el-card></el-col
    >
  </el-row>
</template>
<script setup >
import { getCurrentInstance, ref } from "vue";
const { Bus } = getCurrentInstance().appContext.config.globalProperties;
const userdata = ref({});
Bus.on("clickblog", (data) => {
  //data.userid data.blogid 发送请求获取信息
});
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