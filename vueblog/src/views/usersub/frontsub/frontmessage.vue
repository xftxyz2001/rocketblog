<template>
  <el-row style="margin-top: 60px">
    <el-col :span="3" :offset="4"
      ><div class="grid-content ep-bg-purple" />
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span style="font-weight: 700">消息</span>
          </div>
        </template>
        <div style="overflow-y: scroll;max-height:650px">
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
                ><el-row style="font-weight: 700">{{
                  pageInfo.username
                }}</el-row>
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
                    <el-badge
                      v-if="pageInfo.msgNum !== 0"
                      :value="pageInfo.msgNum"
                      class="item"
                    />
                  </div> </el-row></el-col
            ></el-row>
          </div></div></el-card
    ></el-col>
    <el-col :span="10" :offset="1">
      <el-empty v-if="selectchat" description="请开始一个对话" /><router-view
        :key="$route.fullPath"
      ></router-view
    ></el-col>
  </el-row>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
// const myuserid = ref(105);
const pageInfos = ref([{}]);
const route = useRoute();
const selectchat = ref(false);
// const msglist = ref([]);

const router = useRouter();
setInterval(function () {
  axios.get("/user/chat/sessions").then((res) => {
    var result = res.data;
    if (result.code == 0) {
      pageInfos.value = result.data.list;
      if (pageInfos.value.length == 0) {
        selectchat.value = true;
      } else {
        // router.push({
        //   name: "messagedetail",
        //   params: { userid: pageInfos.value[0].userid },
        // });
        selectchat.value = false;
      }
    } else {
      console.log(result.message);
    }
  });
}, 1000);
axios.get("/user/chat/sessions").then((res) => {
  var result = res.data;
  if (result.code == 0) {
    pageInfos.value = result.data.list;
    if (pageInfos.value.length == 0) {
      selectchat.value = true;
    } else {
      router.push({
        name: "messagedetail",
        params: { userid: pageInfos.value[0].userid },
      });
      selectchat.value = false;
    }
  } else {
    console.log(result.message);
  }
});
function chatdetail(userid) {
  //   axios.get("/user/chat/detail/" + userid).then((res) => {
  //     var result = res.data;
  //     if (result.code == 0) {
  //       msglist.value.push(result.data.list.reverse());
  //       console.log(msglist.value);
  //     }
  //   });
  selectchat.value = false;
  router.push({
    name: "messagedetail",
    params: { userid: userid },
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
}
</style>