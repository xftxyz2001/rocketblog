<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false" @select="handleSelect"
    background-color="#002EA6" text-color="#fff" active-text-color="#ffE78F">
    <el-menu-item index="0" style="margin-left: 30px; margin-right: 30px">
      <img src="../../assets/logo.png" style="width: 80px; margin-top: 5px" />
    </el-menu-item>
    <div class="flex-grow" />

    <div class="demo-input-size" style="padding-top: 12px; padding-right: 12px; padding-left: 12px">
      <el-autocomplete class="w-50 m-2" v-model="keyword" :fetch-suggestions="querySearch" placeholder="请输入搜索内容"
        :trigger-on-focus="false"
        style=" width: 230px; border-radius: 20px; overflow: hidden; --el-input-focus-border-color: none; --el-input-bg-color: #fbfaf6;">
        <template #default="{ item }">
          <div class="demo-input-suggestion-item" @click="clickItem(item)">
            <div class="demo-input-suggestion-item__content">
              <div class="demo-input-suggestion-item__title">
                <span>{{ item.blogTitle }} 作者：{{ item.username }}</span>
              </div>
              <div class="demo-input-suggestion-item__description">
                <span>{{ item.blogSummary }}</span>
              </div>
            </div>
          </div>
        </template>
        <template #prefix>
          <i class="el-icon-search"></i>
        </template>
      </el-autocomplete>
    </div>
    <div>
      <el-menu-item index="1" style="padding: 0 30px; margin-left: 20px" @click="tohome">
        <el-icon>
          <HomeFilled />
        </el-icon>首页
      </el-menu-item>
    </div>

    <router-link :to="{ name: 'hotlatest' }">
      <el-menu-item index="2" style="padding: 0 30px"><span class="iconfont"
          style="margin-right: 5px">&#xe602;</span>热门</el-menu-item></router-link>
    <div>
      <el-menu-item index="3" style="padding: 0 30px" @click="tomessage">
        <el-icon>
          <ChatDotRound />
        </el-icon>消息
      </el-menu-item>
    </div>

    <div>
      <el-menu-item index="4" style="padding: 0 30px" @click="toperson">
        <el-icon>
          <UserFilled />
        </el-icon>主页
      </el-menu-item>
    </div>
    <div>
      <el-menu-item index="5" style="padding: 0 30px; margin-right: 70px" @click="editbutton">
        <el-icon>
          <Plus />
        </el-icon>发布
      </el-menu-item>
    </div>

    <!-- 登陆成功图标 -->
    <div v-if="loginsuccess" style="padding-top: 12px">
      <!-- <el-avatar
        :size="32"
        class="mr-3"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        style=""
      /> -->
      <el-popover :width="250"
        popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;">
        <template #reference>
          <el-avatar :size="32" :src="userinfo.avatar" @mouseenter="getuserinfo" />
        </template>
        <template #default>
          <div class="demo-rich-conent" style="display: flex; gap: 16px; flex-direction: column">
            <div style="text-align: center">
              <p class="demo-rich-content__name" style="margin: 0; font-weight: 700; font-size: 20px">
                {{ userinfo.username }}
              </p>
              <!-- <p
                class="demo-rich-content__mention"
                style="margin: 0; font-size: 14px; color: var(--el-color-info)"
              >
                @element-plus
              </p> -->
            </div>
            <el-row gutter="4" justify="center">
              <el-button link @click="router.push({ name: 'personguanzhu' })"><el-col>
                  <div class="grid-content ep-bg-purple" />
                  <el-col><el-row justify="center">
                      <h3>关注</h3>
                    </el-row>
                    <el-row justify="center" style="margin-top: -25px">
                      <h5>{{ userinfo.followings }}</h5>
                    </el-row>
                  </el-col>
                </el-col></el-button>

              <el-button link @click="router.push({ name: 'personfensi' })"><el-col>
                  <div class="grid-content ep-bg-purple" />
                  <el-col><el-row justify="center">
                      <h3>粉丝</h3>
                    </el-row>
                    <el-row justify="center" style="margin-top: -25px">
                      <h5>{{ userinfo.followers }}</h5>
                    </el-row>
                  </el-col>
                </el-col></el-button>
              <el-button link @click="router.push({ name: 'personblog' })"><el-col>
                  <div class="grid-content ep-bg-purple" />
                  <el-col><el-row justify="center">
                      <h3>博客</h3>
                    </el-row>
                    <el-row justify="center" style="margin-top: -25px">
                      <h5>{{ userinfo.blogs }}</h5>
                    </el-row>
                  </el-col>
                </el-col></el-button>
            </el-row>
            <el-button text style="margin-top: -20px" @click="logout">
              <span class="iconfont" style="margin-right: 5px">&#xe60d;</span>退出登录</el-button>
          </div>
        </template>
      </el-popover>
      <!-- margin: 12px 5px 0px 20px; -->
      <!-- margin: 0px 5px 10px 5px; -->
    </div>
    <!-- 登录按钮 -->
    <div v-else style="padding-top: 12px">
      <el-button round @click="loginbutton(loginformRef)">登录</el-button>
      <!-- <el-button link style="color: #fff">注册</el-button> -->
    </div>
    <!-- 登陆表单 -->
    <el-dialog ref="editform" v-model="loginVisible" title="用户登录" style="width: 400px">
      <div style="border-bottom: 1px #000 solid;width: 80px;margin: -30px 0 20px 0;"></div>
      <el-form :model="loginform" :rules="loginrules" ref="loginformRef">
        <el-form-item :label-width="formLabelWidth" prop="email">
          <el-input v-model="loginform.email" autocomplete="off" prefix-icon="Message" placeholder="邮箱" />
        </el-form-item>

        <el-form-item :label-width="formLabelWidth" prop="password">
          <el-input v-model="loginform.password" type="password" placeholder="密码" show-password prefix-icon="Key" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer" style="position: absolute; left: 20px; bottom: 5px">
          <el-button type="primary" @click="Submitloginform(loginformRef)" style="width: 360px; display: block">
            登录
          </el-button>
          <span style="font-size: 10px; display: inline-block; margin-top: 5px">还没有账号？<el-button link
              style="color: #79bbff; font-size: 10px" @click="changetoregister(registerformRef)">立即注册</el-button></span>
        </span>
      </template>
    </el-dialog>
    <!-- 注册表单 -->
    <el-dialog ref="editform" v-model="registerVisible" title="用户注册" style="width: 400px">
      <div style="border-bottom: 1px #000 solid;width: 80px;margin: -30px 0 20px 0;"></div>
      <el-form :model="registerform" :rules="registerrules" ref="registerformRef">
        <el-form-item :label-width="formLabelWidth" prop="name">
          <el-input v-model="registerform.name" autocomplete="off" prefix-icon="UserFilled" placeholder="昵称" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="email">
          <el-input v-model="registerform.email" autocomplete="off" prefix-icon="Message" placeholder="邮箱" />
        </el-form-item>

        <el-form-item :label-width="formLabelWidth" prop="password">
          <el-input v-model="registerform.password" type="password" placeholder="密码" show-password prefix-icon="Key"
            autocomplete="off" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="confirmpassword">
          <el-input v-model="registerform.confirmpassword" autocomplete="off" placeholder="确认密码" prefix-icon="Key"
            type="password" show-password />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="vertify">
          <el-input v-model="registerform.vertify" autocomplete="off" placeholder="验证码" prefix-icon="Unlock"
            style="width: 50%; margin-right: 12px" />
          <el-button @click="getverify($event, registerformRef)">获取验证码</el-button>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer" style="position: absolute; left: 20px; bottom: 5px">
          <el-button type="primary" @click="submitregisterForm(registerformRef)" style="width: 360px; display: block">
            注册
          </el-button>
          <span style="font-size: 10px; display: inline-block; margin-top: 5px">已经有账号？<el-button link
              style="color: #79bbff; font-size: 10px" @click="changetologin(loginformRef)">立即登录</el-button></span>
        </span>
      </template>
    </el-dialog>

    <span class="text-large font-600 mr-3" style="margin: 19px 5px 0px 35px">
      <a href="https://github.com/xftxyz2001/rocketblog/issues/new"
        style="position: absolute;display: inline-block;margin: -3px 0 0 4px;color: #fff;"><span class="iconfont"
          style="margin-right: 5px">&#xe738;</span>反馈</a>
      <router-link :to="{ name: 'useradmin' }" v-if="isSuperuser">
        <div style="position: absolute;display: inline-block;right: 50px;color: #fff;">
          后台管理<el-icon style="position: fixed; top: 22px; right: 28px">
            <Tools />
          </el-icon>
        </div>
      </router-link>
    </span>
  </el-menu>
</template>
<script setup>
import { useRouter } from "vue-router";
// import bus from "@/utils/bus";
import { ElMessage } from "element-plus";
import { getCurrentInstance } from "vue";
const userinfo = ref({
  followers: "",
  followings: "",
  blogs: "",
  avatar: "",
  username: "",
});
const router = useRouter();
const loginsuccess = ref(false);
const loginVisible = ref(false);
const registerVisible = ref(false);
const registerformRef = ref(null);
const loginformRef = ref(null);

// 尝试获取用户信息
getuserinfo();

const searchResult = ref([]);

async function querySearch(queryString, cb) {
  try {
    await axios
      .get("/blog/search", { params: { keyword: queryString } })
      .then((res) => {
        var result = res.data;
        if (result.code == 0) {
          searchResult.value = result.data.list;
          console.log(searchResult.value);
          cb(searchResult.value);
        }
      });
  } catch (error) {
    console.error(error);
  }
}

function clickItem(blog) {
  router.push({
    name: "blogdetail",
    params: { blogid: blog.blogId },
  });
}

const validatePass = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请输入密码！"));
  } else {
    if (
      // /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,16}$/g.test(
      /^[A-Za-z0-9._~!@#$^&*]{8,16}$/.test(value)
    )
      callback();
    else {
      callback(
        // new Error("请输入包含英文字母大小写、数字和特殊符号的 8-16 位组合")
        new Error("密码长度应为8-16位的A-Za-z0-9._~!@#$^&*")
      );
    }

    if (registerform.confirmpassword !== "") {
      if (!registerformRef.value) return;
      registerformRef.value.validateField("confirmpassword", () => null);
    }
    callback();
  }
};
const validatePass2 = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次输入密码！"));
  } else if (value !== registerform.password) {
    callback(new Error("两次密码不匹配！"));
  } else {
    callback();
  }
};
const loginrules = reactive({
  email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
});
const registerrules = reactive({
  password: [
    { validator: validatePass, trigger: "blur" },
    {
      min: 8,
      max: 16,
      message: "请输入8~16位密码",
      trigger: ["blur", "change"],
    },
  ],
  confirmpassword: [{ validator: validatePass2, trigger: "blur" }],
  email: [
    { required: true, message: "请输入邮箱", trigger: "blur" },
    {
      type: "email",
      message: "请输入正确的邮箱",
      trigger: ["blur", "change"],
    },
  ],
  name: [
    { required: true, message: "请输入昵称", trigger: "blur" },
    {
      min: 3,
      max: 20,
      message: "请输入3~20位昵称",
      trigger: ["blur", "change"],
    },
  ],
});
// const rulerigesterFormRef = ref < FormInstance > registerformRef;
// const ruleloginFormRef = ref < FormInstance > loginformRef;
const loginform = reactive({
  name: "",
  email: "",

  password: "",
});
const registerform = reactive({
  name: "",
  email: "",
  confirmpassword: "",
  password: "",
  vertify: "",
});

const isSuperuser = ref(false);

const Submitloginform = (formEl) => {
  if (!formEl) return;
  else {
    formEl.validate((valid) => {
      if (valid) {
        var logindata = {
          email: loginform.email.trim(),
          password: loginform.password.trim(),
        };
        axios.post("/user/login", logindata).then((res) => {
          var result = res.data;
          if (result.code == 0) {
            // result.data is the data you want
            ElMessage({
              message: "登录成功！",
              type: "success",
            });
            loginVisible.value = false;
            loginsuccess.value = true;
            userinfo.value = result.data;
            judgesuper();
          } else {
            // result.message is the error message
            ElMessage.error(result.message);
          }
        });
      } else {
        return false;
      }
    });
  }
};
const submitregisterForm = (formEl) => {
  if (!formEl) return;
  else {
    formEl.validate((valid) => {
      if (valid) {
        if (registerform.vertify.trim() == "") {
          ElMessage({
            message: "请输入验证码",
            type: "warning",
          });
        } else {
          var registerdata = {
            name: registerform.name.trim(),
            email: registerform.email.trim(),
            password: registerform.password.trim(),
            vertify: registerform.vertify.trim(),
          };

          axios.post("/user/register", registerdata).then((res) => {
            // if (res.data == "验证码错误") {
            //   ElMessage.error("验证码错误！");
            // } else {
            //   ElMessage({
            //     message: "注册成功！",
            //     type: "success",
            //   });
            //   changetologin(loginformRef);
            // }
            var result = res.data;
            if (result.code == 0) {
              // result.data is the data you want
              ElMessage({
                message: "注册成功！",
                type: "success",
              });
              changetologin(loginformRef);
            } else {
              // result.message is the error message
              ElMessage.error(result.message);
            }
          });
        }
      } else {
        return false;
      }
    });
  }
};
const loginbutton = (formE1) => {
  loginVisible.value = true;

  if (!formE1) return;
  else formE1.resetFields();
};

const keyword = ref("");
const changetoregister = (formE1) => {
  loginVisible.value = false;

  registerVisible.value = true;
  if (!formE1) return;
  else formE1.resetFields();
};
const changetologin = (formE1) => {
  loginVisible.value = true;

  registerVisible.value = false;
  if (!formE1) return;
  else formE1.resetFields();
};
const flag = ref(true);
//获取验证码
function getverify(e, formE1) {
  if (!formE1) return;
  else {
    //如果输入邮箱的格式正确
    if (
      /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/.test(
        registerform.email
      )
    ) {
      if (flag.value) {
        axios.get("/user/code/" + registerform.email, true).then((res) => {
          var result = res.data;
          if (result.code == 0) {
            // result.data is the data you want
            ElMessage({
              message: result.data,
              type: "success",
            });
          } else {
            // result.message is the error message
            ElMessage.error(result.message);
          }
        });
        flag.value = false;
        let i = 60;
        e.target.innerHTML = `${i}秒后重新获取`;
        let timerId = setInterval(function () {
          i--;
          if (i >= 10) e.target.innerHTML = `${i}秒后重新获取`;
          else if (i > 0) {
            e.target.innerHTML = `0${i}秒后重新获取`;
          } else if (i == 0) {
            clearInterval(timerId);
            e.target.innerHTML = `重新获取`;
            flag.value = true;
          }
        }, 1000);
      }
    }
  }
}

function logout() {
  axios.get("/user/logout").then((res) => {
    loginsuccess.value = false;
    router.push({ name: "hotlatest" });
    // localStorage.removeItem("token");
    // localStorage.removeItem("token.email");
    // localStorage.removeItem("token.password");
    isSuperuser.value = false;
  });
}

function judgesuper() {
  axios.get("/user/i/detail").then((res) => {
    var rd = res.data;
    if (rd.code == 0) {
      // result.data is the data you want
      if (rd.data.isSuperuser == 1) {
        isSuperuser.value = true;
      } else {
        isSuperuser.value = false;
      }
    } else {
      // result.message is the error message
      ElMessage.error(rd.message);
    }
  });
}

function getuserinfo() {
  axios.get("/user/i").then((res) => {
    if (res.data.code == 0) {
      userinfo.value = res.data.data;
      loginsuccess.value = true;
      judgesuper();
    } else {
      loginsuccess.value = false;
    }
  });
}

function editbutton() {
  axios.get("/user/i").then((res) => {
    if (res.data.code == 0) {
      router.push({ name: "editblog" });
    } else {
      loginVisible.value = true;
      loginform.email = "";
      loginform.password = "";
    }
  });
}

function tohome() {
  axios.get("/user/i").then((res) => {
    if (res.data.code == 0) {
      router.push({ name: "allconcern" });
    } else {
      loginVisible.value = true;
      loginform.email = "";
      loginform.password = "";
    }
  });
}

function tomessage() {
  axios.get("/user/i").then((res) => {
    if (res.data.code == 0) {
      router.push({ name: "message" });
    } else {
      loginVisible.value = true;
      loginform.email = "";
      loginform.password = "";
    }
  });
}

function toperson() {
  axios.get("/user/i").then((res) => {
    if (res.data.code == 0) {
      router.push({ name: "personuserinfo" });
    } else {
      loginVisible.value = true;
      loginform.email = "";
      loginform.password = "";
    }
  });
}

/** websocket-start */
var websocket = null;

// 从cookie中获取token
function getToken() {
  if (document.cookie.length > 0) {
    var offset = document.cookie.indexOf("token=");
    if (offset != -1) {
      offset += "token=".length;
      var end = document.cookie.indexOf(";", offset);
      if (end == -1) end = document.cookie.length;
      return unescape(document.cookie.substring(offset, end));
    }
  }
}

// 判断当前浏览器是否支持WebSocket
if ("WebSocket" in window) {
  websocket = new WebSocket(
    "ws://" + window.location.host + "/message/" + getToken()
  );
} else {
  alert("当前浏览器不支持WebSocket");
}

// 客户端接收消息时的回调方法
websocket.onmessage = function (event) {
  ElMessage.success(event.data);
};
/** websocket-end */

const { Bus } = getCurrentInstance().appContext.config.globalProperties;
Bus.on("followneedlogin", () => {
  loginVisible.value = true;
  loginform.email = "";
  loginform.password = "";
});
Bus.on("likeneedlogin", () => {
  loginVisible.value = true;
  loginform.email = "";
  loginform.password = "";
});
Bus.on("collectneedlogin", () => {
  loginVisible.value = true;
  loginform.email = "";
  loginform.password = "";
});
Bus.on("commentneedlogin", () => {
  loginVisible.value = true;
  loginform.email = "";
  loginform.password = "";
});
Bus.on("deleteaccount", () => {
  loginsuccess.value = false;
});
</script>
<script>
import { defineComponent, reactive } from "vue";
export default defineComponent({
  name: "Header",
});
import axios from "axios";
import { ref } from "vue";
</script>

<style scoped>
.flex-grow {
  flex-grow: 0;
}

.el-dialog_footer {
  padding: var(--el-dialog-padding-primary);
  padding-top: 10px;
  text-align: center;
  box-sizing: border-box;
}

.router-link-active {
  text-decoration: none;
  color: #ffe78f;
}

a {
  text-decoration: none;
  color: #ffe78f;
}

.el-menu--horizontal>.el-menu-item.is-active {
  border-bottom: 0;
  color: #fff !important;
}
</style>
