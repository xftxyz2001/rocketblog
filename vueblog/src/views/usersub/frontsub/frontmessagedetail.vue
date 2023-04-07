<template>
  <div class="grid-content ep-bg-purple-light" />
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
      <div v-if="msg.useridFrom == myuserid" style="width: 100%; height: 80px">
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
  </el-card>
</template>
<script setup >
import axios from "axios";
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const myuserid = ref(null);
const msglist = ref([]);
axios.get("/user/i").then((res) => {
  let result = res.data;
  if (result.code == 0) {
    myuserid.value = res.data.userid;
    axios.get("/user/chat/detail/" + route.params.userid).then((res) => {
      let result = res.data;
      if (result.code == 0) {
        msglist.value.push(result.data.list.reverse());
        console.log(msglist.value);
      }
    });
  }
});
</script>
<script>
export default {};
</script>

<style>
</style>