<template>
  <div class="common-layout" style="margin-top: 60px">
    <el-container style="position: relative">
      <el-main><el-row gutter="10">
          <el-col :span="1">
            <div class="grid-content ep-bg-purple" />
          </el-col>
          <el-col :span="22">
            <div class="grid-content ep-bg-purple" />
            <div class="common-layout">
              <el-container style="width: 100%">
                <el-header style="width: 100%; height: 150px"><el-card class="box-card" style="height: 150px">
                    <!-- <template #header>
                      <div class="card-header" style="width: 100%">
                        <span>Card name</span>
                      </div>
                    </template> -->
                    <div>
                      <el-row :gutter="20">
                        <el-col :span="3">
                          <div class="grid-content ep-bg-purple" />
                          <img :src="userdata.avatar" alt="" style="width: 120px;height: 120px;border-radius: 75px;" />
                        </el-col>
                        <el-col :span="20">
                          <div class="grid-content ep-bg-purple" />
                          <el-row style="font-size: 20px; font-weight: 600">昵称：{{ userdata.username }}</el-row>
                          <el-row gutter="20" justify="left">
                            <el-button link><el-col>
                                <div class="grid-content ep-bg-purple" />
                                <el-col><el-row justify="center">
                                    <h3>关注</h3>
                                  </el-row>
                                  <el-row justify="center" style="margin-top: -25px">
                                    <h5>{{ userdata.followings }}</h5>
                                  </el-row>
                                </el-col>
                              </el-col></el-button>

                            <el-button link><el-col>
                                <div class="grid-content ep-bg-purple" />
                                <el-col><el-row justify="center">
                                    <h3>粉丝</h3>
                                  </el-row>
                                  <el-row justify="center" style="margin-top: -25px">
                                    <h5>{{ userdata.followers }}</h5>
                                  </el-row>
                                </el-col>
                              </el-col></el-button>
                            <el-button link><el-col>
                                <div class="grid-content ep-bg-purple" />
                                <el-col><el-row justify="center">
                                    <h3>博客</h3>
                                  </el-row>
                                  <el-row justify="center" style="margin-top: -25px">
                                    <h5>{{ userdata.blogs }}</h5>
                                  </el-row>
                                </el-col>
                              </el-col></el-button>
                            <el-col :span="1" :offset="14" style="margin-top: 10px">
                              <div class="grid-content ep-bg-purple" />
                              <el-button @click="chatwith">私信</el-button>
                            </el-col>
                            <el-col :span="1" :offset="1" style="margin-top: 10px">
                              <div class="grid-content ep-bg-purple-light" />
                              <el-button v-if="userdata.followed" @click="dontfollowthis">已关注</el-button>
                              <el-button v-else type="primary" @click="followthis">关注</el-button>
                            </el-col>
                          </el-row>
                        </el-col>
                      </el-row>
                    </div>
                  </el-card></el-header>
                <el-container>
                  <div class="nav" ref="left" style="margin: 20px 0 0 20px">
                    <h3 class="mb-2" style="margin-left: 22px">TA的</h3>
                    <el-menu active-text-color="#ffd04b" background-color="#fff" class="el-menu-vertical-demo"
                      text-color="#000" @open="handleOpen" @close="handleClose" style="--el-menu-border-color: #fff">
                      <router-link :to="{ name: 'otherpersonblogs', params: { userid: userdata.userid } }">
                        <el-menu-item index="1" style="border-top: 1px #c8c9cc solid">
                          <span class="iconfont" style="margin-right: 5px">&#xe8bc;</span>
                          <span>TA的博客</span>
                        </el-menu-item>
                      </router-link>
                    </el-menu>
                  </div>
                  <el-main><router-view></router-view></el-main>
                </el-container>
              </el-container>
            </div>
          </el-col>
          <el-col :span="1">
            <div class="grid-content ep-bg-purple" />
          </el-col> </el-row></el-main>
    </el-container>
  </div>
</template>
<script setup >
import axios from "axios";
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const userdata = ref({});

axios.get("/user/info/" + route.params.userid).then((res) => {
  var resultUserInfo = res.data;
  if (resultUserInfo.code == 0) {
    userdata.value = resultUserInfo.data;
  }
});

function dontfollowthis() {
  axios.delete("/user/follow/" + route.params.userid).then((res) => {
    if (res.data.code == 0) {
      userdata.value.followed = false;
    } else if (res.data.code == 100) {
      Bus.emit("likeneedlogin", {});
    }
  });
}

function followthis() {
  axios.get("/user/follow/" + route.params.userid).then((res) => {
    if (res.data.code == 0) {
      userdata.value.followed = true;
    } else if (res.data.code == 100) {
      Bus.emit("likeneedlogin", {});
    }
  });
}

function chatwith() {
  axios.get("/chat/session/" + route.params.userid).then((res) => {
    var result = res.data;
    if (result.code == 400) {
      var sendmessage = { to: route.params.userid, content: "" };
      axios.post("/chat/s", sendmessage).then((res) => {
        router.push({
          name: "messagedetail",
          params: { userid: route.params.userid },
        });
      });
    } else {
      router.push({
        name: "messagedetail",
        params: { userid: route.params.userid },
      });
    }
  });
}
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