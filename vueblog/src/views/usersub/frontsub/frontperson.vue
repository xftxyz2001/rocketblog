<template>
  <div class="common-layout">
    <el-container style="position: relative">
      <el-header
        style="
          overflow: hidden;
          height: 100px;
          position: relative;
          padding-top: 90px;
          text-align: center;
          background-color: #fff3d0;
        "
        ><img
          src="../../../assets/header.gif"
          alt=""
          style="height: 500px; position: absolute; top: -208px"
      /></el-header>
      <el-main
        ><el-row gutter="10">
          <el-col :span="1"><div class="grid-content ep-bg-purple" /></el-col>
          <el-col :span="22"
            ><div class="grid-content ep-bg-purple" />
            <div class="common-layout">
              <el-container style="width: 100%">
                <el-header style="width: 100%; height: 150px"
                  ><el-card class="box-card" style="height: 150px">
                    <!-- <template #header>
                      <div class="card-header" style="width: 100%">
                        <span>Card name</span>
                      </div>
                    </template> -->
                    <div>
                      <el-row :gutter="20">
                        <el-col :span="3"
                          ><div class="grid-content ep-bg-purple" />
                          <img
                            :src="userdata.avatar"
                            alt=""
                            style="
                              width: 120px;
                              height: 120px;
                              border-radius: 75px;
                            "
                        /></el-col>
                        <el-col :span="20"
                          ><div class="grid-content ep-bg-purple" />
                          <el-row style="font-size: 20px; font-weight: 600"
                            >昵称：{{ userdata.username }}</el-row
                          >
                          <el-row gutter="20" justify="left">
                            <el-button link @click="router.push({ name: 'personguanzhu' })"
                              ><el-col>
                                <div class="grid-content ep-bg-purple" />
                                <el-col
                                  ><el-row justify="center"
                                    ><h3>关注</h3></el-row
                                  >
                                  <el-row
                                    justify="center"
                                    style="margin-top: -25px"
                                    ><h5>{{ userdata.followings }}</h5></el-row
                                  >
                                </el-col>
                              </el-col></el-button
                            >

                            <el-button link @click="router.push({ name: 'personfensi' })"
                              ><el-col>
                                <div class="grid-content ep-bg-purple" />
                                <el-col
                                  ><el-row justify="center"
                                    ><h3>粉丝</h3></el-row
                                  >
                                  <el-row
                                    justify="center"
                                    style="margin-top: -25px"
                                    ><h5>{{ userdata.followers }}</h5></el-row
                                  >
                                </el-col>
                              </el-col></el-button
                            >
                            <el-button link @click="router.push({ name: 'personblog' })"
                              ><el-col>
                                <div class="grid-content ep-bg-purple" />
                                <el-col
                                  ><el-row justify="center"
                                    ><h3>博客</h3></el-row
                                  >
                                  <el-row
                                    justify="center"
                                    style="margin-top: -25px"
                                    ><h5>{{ userdata.blogs }}</h5></el-row
                                  >
                                </el-col>
                              </el-col></el-button
                            >
                          </el-row>
                        </el-col>
                      </el-row>
                    </div>
                  </el-card></el-header
                >
                <el-container>
                  <div class="nav" ref="left" style="margin: 20px 0 0 20px">
                    <h3 class="mb-2" style="margin-left: 22px">我的</h3>
                    <el-menu
                      active-text-color="#ffd04b"
                      background-color="#fff"
                      class="el-menu-vertical-demo"
                      text-color="#000"
                      @open="handleOpen"
                      @close="handleClose"
                      style="--el-menu-border-color: #fff"
                    >
                      <router-link :to="{ name: 'personuserinfo' }"
                        ><el-menu-item
                          index="1"
                          style="border-top: 1px #c8c9cc solid"
                        >
                          <span class="iconfont" style="margin-right: 5px"
                            >&#xe8bc;</span
                          >
                          <span>个人资料</span>
                        </el-menu-item></router-link
                      >
                      <router-link :to="{ name: 'personaccount' }">
                        <el-menu-item index="2">
                          <span class="iconfont" style="margin-right: 5px"
                            >&#xe60f;</span
                          >
                          <span>账号管理</span>
                        </el-menu-item></router-link
                      >
                      <router-link :to="{ name: 'personblog' }">
                        <el-menu-item index="3">
                          <span class="iconfont" style="margin-right: 5px"
                            >&#xe60f;</span
                          >
                          <span>博客</span>
                        </el-menu-item></router-link
                      >
                      <router-link :to="{ name: 'personguanzhu ' }">
                        <el-menu-item index="4">
                          <span class="iconfont" style="margin-right: 5px"
                            >&#xe60f;</span
                          >
                          <span>关注</span>
                        </el-menu-item></router-link
                      >
                      <router-link :to="{ name: 'personfensi' }">
                        <el-menu-item index="5">
                          <span class="iconfont" style="margin-right: 5px"
                            >&#xe60f;</span
                          >
                          <span>粉丝</span>
                        </el-menu-item></router-link
                      >
                      <router-link :to="{ name: 'personcollect' }">
                        <el-menu-item index="6">
                          <span class="iconfont" style="margin-right: 5px"
                            >&#xe60f;</span
                          >
                          <span>收藏</span>
                        </el-menu-item></router-link
                      >
                    </el-menu>
                  </div>
                  <el-main><router-view></router-view></el-main>
                </el-container>
              </el-container></div
          ></el-col>
          <el-col :span="1"
            ><div class="grid-content ep-bg-purple"
          /></el-col> </el-row
      ></el-main>
    </el-container>
  </div>
</template>
<script setup >
//   "followers": 1,
//         "followings": 2,
//         "blogs": 6,
//         "avatar": "https://sm.ms/image/Fipzym7aUWfK4R9",
//         "username": "测试"
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const userdata = ref({});
axios.get("http://8.130.81.23:8080/user/info").then((res) => {
  userdata.value = res.data.data;
});
</script>
<script>
export default {};
</script>

<style>
.nav {
  background-color: #fff;
  width: 200px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.05), 0 6px 20px 0 rgba(0, 0, 0, 0.05);
}
.el-menu-item {
  --el-menu-hover-bg-color: #0a49e8;
  --el-menu-active-color: #e3c455;
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
  width: 100%;
}
</style>