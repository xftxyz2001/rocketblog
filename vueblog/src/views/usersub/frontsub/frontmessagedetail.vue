<template>
  <div class="grid-content ep-bg-purple-light" />
  <el-card shadow="never" style="height: 500px; overflow-y: auto">
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
    <div
      v-for="msg in msglist"
      :key="msg.chatId"
      style="width: 100%; overflow: hidden"
    >
      <div v-if="msg.useridFrom == myuserid" style="width: 100%">
        <div style="float: right">
          <img
            :src="msg.fromAvatar"
            alt=""
            style="width: 26px; border-radius: 13px"
          />
        </div>
        <div style="float: right; padding-top: 5px">
          <div>
            <div style="font-size: 12px; float: right">
              {{ msg.fromUsername }}
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
      <div v-else style="width: 100%">
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
                  max-width: 200px;
                  display: grid;
                  grid-template-rows: repeat(10, 10%);
                  word-break: break-all;
                  overflow: hidden;
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
  <div style="overflow: hidden">
    <el-input
      v-model.trim="textarea2"
      :autosize="{ minRows: 2, maxRows: 4 }"
      type="textarea"
      placeholder="请输入..."
      style="float: right"
    />
    <el-button
      type="primary"
      round
      style="float: right; margin-top: 7px"
      @click="send"
      >发送</el-button
    >
  </div>
</template>
<script setup >
import axios from "axios";
import { ref } from "vue";
import { useRoute } from "vue-router";

const textarea2 = ref("");
const route = useRoute();
// const myuserid = ref(null);
const myuserid = ref(null);
const msglist = ref([]);

/** websocket-start */
var websocket = null;

// 从cookie中获取token
function getToken() {
    if (document.cookie.length > 0) {
        var offset = document.cookie.indexOf("token=");
        if (offset != -1) {
            offset += "token=".length;
            var end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end))
        }
    }
}

// 判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://" + window.location.host + "/chat/" + getToken());
} else {
    alert("当前浏览器不支持WebSocket");
}

// 客户端接收消息时的回调方法
websocket.onmessage = function (event) {
  axios.get("/user/chat/sessions").then((res) => {
    var result = res.data;
    if (result.code == 0) {
      pageInfos.value = result.data.list;
      if (pageInfos.value.length == 0) {
        selectchat.value = true;
      } else {
        selectchat.value = false;
      }
    } else {
      console.log(result.message);
    }
  });
}
// 发送消息
function sendMessage(msgBody) {
    if (websocket.readyState != 1) {
        console.log("websocket连接没有建立成功！");
    } else {
        var obj = {
            "to": msgBody.to,
            "content": msgBody.content
        };

        websocket.send(JSON.stringify(obj));
    }
}

// 客户端接收消息时的回调方法
websocket.onmessage = function (event) {
  axios.get("/user/chat/detail/" + route.params.userid).then((res) => {
    var result = res.data;

    if (result.code == 0) {
      var resultdata = result.data.list.reverse();

      if (msglist.value.length == 0) {
        for (let index = 0; index < resultdata.length; index++) {
          if (resultdata[index].messageContent !== "")
            msglist.value.push(resultdata[index]);
        }
      } else {
        var lastmessagenum = msglist.value[msglist.value.length - 1].chatId;
        for (let index = 0; index < resultdata.length; index++) {
          if (lastmessagenum < resultdata[index].chatId) {
            if (resultdata[index].messageContent !== "")
              msglist.value.push(resultdata[index]);
          }
        }
      }
      console.log(msglist.value);
    }
  });
}

// setInterval(function () {
//   axios.get("/user/chat/detail/" + route.params.userid).then((res) => {
//     var result = res.data;

//     if (result.code == 0) {
//       var resultdata = result.data.list.reverse();

//       if (msglist.value.length == 0) {
//         for (let index = 0; index < resultdata.length; index++) {
//           if (resultdata[index].messageContent !== "")
//             msglist.value.push(resultdata[index]);
//         }
//       } else {
//         var lastmessagenum = msglist.value[msglist.value.length - 1].chatId;
//         for (let index = 0; index < resultdata.length; index++) {
//           if (lastmessagenum < resultdata[index].chatId) {
//             if (resultdata[index].messageContent !== "")
//               msglist.value.push(resultdata[index]);
//           }
//         }
//       }

//       // msglist.value = result.data.list.reverse();
//       console.log(msglist.value);
//     }
//   });
// }, 1000);

/** websocket-end */
axios.get("/user/i").then((res) => {
  var result = res.data;
  if (result.code == 0) {
    myuserid.value = result.data.userid;

    axios.get("/user/chat/detail/" + route.params.userid).then((res) => {
      var result = res.data;
      if (result.code == 0) {
        var resultdata = result.data.list.reverse();
        for (let index = 0; index < resultdata.length; index++) {
          if (resultdata[index].messageContent !== "")
            msglist.value.push(resultdata[index]);
        }
        // msglist.value = result.data.list.reverse();
        console.log(msglist.value);
      }
    });
  }
});
function send() {
  if (textarea2 !== "") {
    var sendmessage = { to: route.params.userid, content: textarea2.value };
    axios.post("user/chat", sendmessage).then((res) => {
      var result = res.data;
      if (result.code == 0) {
        textarea2.value = "";
      }
    });
    sendMessage(sendmessage);
  }
}
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
  width: 100%;
}
</style>