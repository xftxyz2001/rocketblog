<template>
  <el-row>
    <el-col :span="3" :offset="4"
      ><div class="grid-content ep-bg-purple" />
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span style="font-weight: 700">消息</span>
          </div>
        </template>
        <div
          v-for="pageInfo in pageInfos"
          :key="pageInfo.userid"
          class="text item messagelist"
        >
          <el-row @click="chatdetail(pageInfo.userid)" style="cursor: pointer"
            ><el-col :span="5"
              ><img
                :src="pageInfo.avatar"
                alt=""
                style="width: 36px; border-radius: 18px"
            /></el-col>
            <el-col :span="11" :offset="2"
              ><el-row style="font-weight: 700">{{ pageInfo.username }}</el-row>
              <el-row style="width: 150%; height: 20px; margin-top: 3px"
                ><div
                  style="
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    width: 60%;
                    display: inline-block;
                    font-size: 13px;
                  "
                >
                  {{ pageInfo.lastMsg }}
                </div>
                <div style="display: inline-block">
                  <el-badge :value="pageInfo.msgNum" class="item" />
                </div> </el-row></el-col
          ></el-row>
        </div> </el-card
    ></el-col>
    <el-col :span="10" :offset="1"
      ><div class="grid-content ep-bg-purple-light" />
      <el-card shadow="never" style="height: 500px">
        <!-- <el-row
          v-for="msg in msglist"
          :key="msg.chatId"
          style="width: 100%; position: relative"
          ><div v-if="msg.useridFrom == myuserid" style="width: 100%">
            <el-row style="width: 100%"
              ><el-col
                :span="5"
                style="position: absolute; right: 5px; height: 20px"
                >1</el-col
              >
              <el-col
                :span="5"
                style="position: absolute; right: 3px; height: 20px"
              >
                2</el-col
              >
            </el-row>
          </div>
          <div v-else style="position: absolute; left: 5px">3</div>
        </el-row> -->
        <div v-for="msg in msglist" :key="msg.chatId" style="width: 100%">
          <div
            v-if="msg.useridFrom == myuserid"
            style="width: 100%; height: 80px"
          >
            <div style="float: right">
              <img
                :src="msg.toAvatar"
                alt=""
                style="width: 26px; border-radius: 13px"
              />
            </div>
            <div style="float: right; padding-top: 5px">
              <div>
                <div style="font-size: 12px; float: right">
                  {{ msg.toUsername }}
                </div>
              </div>
              <div>
                <div>
                  <div
                    style="
                      float: right;
                      border: 1px #e4e7ed solid;
                      border-radius: 5px;
                      padding: 5px;
                      margin-top: 1px;

                      display: grid;
                      grid-template-rows: repeat(10, 10%);
                    "
                  >
                    {{ msg.messageContent }}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div v-else style="width: 100%; height: 80px">
            <div style="float: left">
              <img
                :src="msg.fromAvatar"
                alt=""
                style="width: 26px; border-radius: 13px"
              />
            </div>
            <div style="float: left; padding-top: 5px">
              <div>
                <div style="font-size: 12px; float: left">
                  {{ msg.fromUsername }}
                </div>
              </div>
              <div>
                <div>
                  <div
                    style="
                      float: left;
                      border: 1px #e4e7ed solid;
                      border-radius: 5px;
                      padding: 5px;
                      margin-top: 1px;

                      display: grid;
                      grid-template-rows: repeat(10, 10%);
                    "
                  >
                    {{ msg.messageContent }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-card></el-col
    >
  </el-row>
</template>

<script setup>
const myuserid = ref(105);
const pageInfos = ref([
  {
    userid: 100,
    username: "通晓宇宙",
    avatar: "https://avatars.githubusercontent.com/u/22099765",
    msgNum: 1,
    lastMsg:
      "川澄 舞，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。",
    lastTime: "2021-12-28T16:00:00.000+00:00",
  },
]);
const msglist = ref([]);

axios.get("/user/chat/sessions").then((res) => {
  var result = res.data.data.list;
  if (result.code == 0) {
    pageInfos.value = result.data.data.list;
  } else {
    console.log(result.message);
  }
});
function chatdetail(userid) {
  axios.get("/user/chat/detail/" + userid).then((res) => {
    if (res.code == 0) {
      msglist.value.push(res.data.data.list.reverse());
    }
  });
}
</script>

<script>
import axios from "axios";
import { ref } from "vue";
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
}
</style>