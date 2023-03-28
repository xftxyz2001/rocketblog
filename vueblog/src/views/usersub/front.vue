<template>
  <div class="common-layout">
    <el-container>
      <el-header><Header></Header></el-header>
      <el-main><router-view></router-view></el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>


<script>
import { Search } from "@element-plus/icons-vue";
import Header from "@/views/usersub/header.vue";
import FrontBody from "@/views/usersub/frontsub/fronthomebody.vue";
import FrontHotBody from "@/views/usersub/frontsub/fronthotbody.vue";
import { defineComponent } from "vue";
import fronthotbodyVue from "./frontsub/fronthotbody.vue";
export default defineComponent({
  name: "front",
  components: { Header, FrontBody, FrontHotBody },
});
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
.affix-container {
  text-align: center;
  height: 50px;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
}
.el-header {
  position: fixed;
  z-index: 100;
  width: 100%;
  height: 60px;
  margin-top: -7px;
  margin-left: -8px;

  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.15), 0 6px 20px 0 rgba(0, 0, 0, 0.1);
}
.el-main {
  position: absolute;
  left: 0px;
  right: 0;
  top: 60px;
  bottom: 0;
  background-color: #f1f2f5;
  height: 2000px;
}
</style>