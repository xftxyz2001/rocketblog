<template>
  <h1>火箭博客</h1>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
    background-color="#002EA6"
    text-color="#fff"
    active-text-color="#ffE78F"
  >
    <el-menu-item index="0" style="margin-left: 30px; margin-right: 30px"
      ><img src="../../assets/logo.png" style="width: 80px; margin-top: 5px"
    /></el-menu-item>
    <div class="flex-grow" />

    <div
      class="demo-input-size"
      style="padding-top: 12px; padding-right: 12px; padding-left: 12px"
    >
      <el-input
        v-model="input1"
        class="w-50 m-2"
        placeholder="Please Input"
        suffix-icon="Search"
        style="
          width: 230px;
          border-radius: 20px;
          overflow: hidden;
          --el-input-focus-border-color: none;
          --el-input-bg-color: #fbfaf6;
        "
      />
    </div>
    <router-link to="/user/front/home"
      ><el-menu-item index="1" style="padding: 0 40px; margin-left: 60px"
        ><el-icon><HomeFilled /></el-icon>首页</el-menu-item
      ></router-link
    >
    <router-link to="/user/front/hot">
      <el-menu-item index="2" style="padding: 0 40px"
        >热门</el-menu-item
      ></router-link
    >

    <el-menu-item index="3" style="padding: 0 40px"
      ><el-icon><ChatDotRound /></el-icon>消息</el-menu-item
    >
    <el-menu-item index="4" style="padding: 0 40px"
      ><el-icon><UserFilled /></el-icon>主页</el-menu-item
    >
    <el-sub-menu index="5" style="padding: 0 25px">
      <template #title
        ><el-icon><Plus /></el-icon>发布</template
      >
      <el-menu-item index="5-1"
        ><el-icon><CirclePlus /></el-icon>发布专题</el-menu-item
      >
      <el-menu-item index="5-2"
        ><el-icon><CirclePlus /></el-icon>发布日志</el-menu-item
      >
    </el-sub-menu>
    <!-- 登录按钮 -->
    <div v-if="true" style="padding-top: 12px">
      <el-button round @click="loginbutton(loginformRef)">登录</el-button>
      <!-- <el-button link style="color: #fff">注册</el-button> -->
    </div>
    <!-- 登陆表单 -->
    <el-dialog
      ref="editform"
      v-model="loginVisible"
      title="用户登录"
      style="width: 400px"
    >
      <div
        style="
          border-bottom: 1px #000 solid;
          width: 80px;
          margin: -30px 0 20px 0;
        "
      ></div>
      <el-form :model="loginform" ref="loginformRef">
        <el-form-item :label-width="formLabelWidth" prop="email">
          <el-input
            v-model="loginform.email"
            autocomplete="off"
            prefix-icon="Message"
            placeholder="邮箱"
          />
        </el-form-item>

        <el-form-item :label-width="formLabelWidth" prop="password">
          <el-input
            v-model="loginform.password"
            autocomplete="off"
            placeholder="密码"
            prefix-icon="Key"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span
          class="dialog-footer"
          style="position: absolute; left: 20px; bottom: 5px"
        >
          <el-button
            type="primary"
            @click="loginformSubmit"
            style="width: 360px; display: block"
          >
            登录
          </el-button>
          <span style="font-size: 10px; display: inline-block; margin-top: 5px"
            >还没有账号？<el-button
              link
              style="color: #79bbff; font-size: 10px"
              @click="changetoregister(registerformRef)"
              >立即注册</el-button
            ></span
          >
        </span>
      </template>
    </el-dialog>
    <!-- 注册表单 -->
    <el-dialog
      ref="editform"
      v-model="registerVisible"
      title="用户注册"
      style="width: 400px"
    >
      <div
        style="
          border-bottom: 1px #000 solid;
          width: 80px;
          margin: -30px 0 20px 0;
        "
      ></div>
      <el-form :model="registerform" :rules="rules" ref="registerformRef">
        <el-form-item :label-width="formLabelWidth" prop="name">
          <el-input
            v-model="registerform.name"
            autocomplete="off"
            prefix-icon="UserFilled"
            placeholder="昵称"
          />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="email">
          <el-input
            v-model="registerform.email"
            autocomplete="off"
            prefix-icon="Message"
            placeholder="邮箱"
          />
        </el-form-item>

        <el-form-item :label-width="formLabelWidth" prop="password">
          <el-input
            v-model="registerform.password"
            autocomplete="off"
            placeholder="密码"
            prefix-icon="Key"
            type="password"
          />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="confirmpassword">
          <el-input
            v-model="registerform.confirmpassword"
            autocomplete="off"
            placeholder="确认密码"
            prefix-icon="Key"
            type="password"
          />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="vertify">
          <el-input
            v-model="registerform.vertify"
            autocomplete="off"
            placeholder="验证码"
            prefix-icon="Unlock"
            style="width: 50%; margin-right: 12px"
          />
          <el-button @click="getverify(registerformRef)">获取验证码</el-button>
        </el-form-item>
      </el-form>
      <template #footer>
        <span
          class="dialog-footer"
          style="position: absolute; left: 20px; bottom: 5px"
        >
          <el-button
            type="primary"
            @click="submitregisterForm(registerformRef)"
            style="width: 360px; display: block"
          >
            注册
          </el-button>
          <span style="font-size: 10px; display: inline-block; margin-top: 5px"
            >已经有账号？<el-button
              link
              style="color: #79bbff; font-size: 10px"
              @click="changetologin(loginformRef)"
              >立即登录</el-button
            ></span
          >
        </span>
      </template>
    </el-dialog>
    <!-- 登陆成功图标 -->
    <div v-if="false" style="padding-top: 12px">
      <el-avatar
        :size="32"
        class="mr-3"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        style="margin: 0px 2px 0px 0px; vertical-align: middle"
      />
      <!-- margin: 12px 5px 0px 20px; -->
      <!-- margin: 0px 5px 10px 5px; -->
      <span class="text-large font-600 mr-3" style="color: #fff"> Title </span>
    </div>
    <span class="text-large font-600 mr-3" style="margin: 19px 5px 0px 35px">
      <el-icon style="color: #fff"><Star /></el-icon
      ><span
        style="
          position: absolute;
          display: inline-block;
          margin: -3px 0 0 4px;
          color: #fff;
        "
        >收藏</span
      >
    </span>
  </el-menu>
</template>
<script setup >
import { ElMessage } from "element-plus";
import { FormInstance, FormRules } from "element-plus";
const loginVisible = ref(false);
const registerVisible = ref(false);
const registerformRef = ref(null);
const loginformRef = ref(null);
const validatePass = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请输入密码！"));
  } else {
    if (
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,16}$/g.test(
        value
      )
    )
      callback();
    else {
      callback(
        new Error("请输入包含英文字母大小写、数字和特殊符号的 8-16 位组合")
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
const rules = reactive({
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
const rulerigesterFormRef = ref < FormInstance > registerformRef;
const ruleloginFormRef = ref < FormInstance > loginformRef;
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
const submitregisterForm = (formEl) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      if (registerform.vertify == "") {
        ElMessage({
          message: "请输入验证码",
          type: "warning",
        });
      } else {
        var registerdata = {
          name: registerform.name,
          email: registerform.email,
          password: registerform.password,
          vertify: registerform.vertify,
        };

        console.log(axios.defaults.withCredentials);
        axios
          .post("http://8.130.81.23:8080/user/register", registerdata, true)
          .then((res) => {
            console.log(res);
          });
      }
    } else {
      console.log("error submit!");
      return false;
    }
  });
};
const loginbutton = (formE1) => {
  loginVisible.value = "true";

  if (!formE1) return;
  else formE1.resetFields();
};

const input1 = ref("");
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
//获取验证码
function getverify(formE1) {
  if (!formE1) return;
  else {
    //如果输入邮箱的格式正确
    if (
      /^([a-z]|[0-9])+((\.|\-|_)?([a-z]|[0-9])+)*@[a-z]+(\-?[a-z]+)*(\.[a-z]+(\-?[a-z]+)*)+$/.test(
        registerform.email
      )
    ) {
      axios.get(
        "http://8.130.81.23:8080/user/code/" + registerform.email,
        true
      );
    }
  }
}
</script>
<script >
import { FormInstance, FormRules } from "element-plus";
import { defineComponent, reactive } from "vue";
import { Search } from "@element-plus/icons-vue";
export default defineComponent({
  name: "Header",
});
import { ref } from "vue";
import axios from "axios";
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
</style>