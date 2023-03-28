<template>
  <div class="common-layout">
    <el-container>
      <el-header style="background-color: #409eff">
        <div>
          <text class="text-large font-600 mr-3"> 博客后台管理系统 </text>
          <div class="quite">
            <el-link type="danger" style="color: #606266">退出登录</el-link>
          </div>
          <div class="fenge"></div>
          <div class="profile"></div>
          <span class="name">叶谦</span>
        </div>
      </el-header>
      <div style="height: 2px; background-color: #337ecc"></div>
      <el-container class="layout-container-demo" style="height: 500px">
        <el-aside width="200px">
          <el-scrollbar>
            <el-menu>
              <router-link to="/admin/useradmin">
                <el-menu-item index="1"
                  ><el-icon><UserFilled /></el-icon>用户管理</el-menu-item
                >
              </router-link>
              <router-link to="/admin/blogadmin">
                <el-menu-item index="2"
                  ><el-icon><Collection /></el-icon>博客管理</el-menu-item
                >
              </router-link>
              <el-menu-item index="3"
                ><el-icon><message /></el-icon>Navigator One</el-menu-item
              >
            </el-menu>
          </el-scrollbar>
        </el-aside>

        <el-container>
          <el-header style="text-align: right; font-size: 12px; display: none">
            <div class="toolbar">
              <el-dropdown>
                <el-icon style="margin-right: 8px; margin-top: 1px"
                  ><setting
                /></el-icon>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item>View</el-dropdown-item>
                    <el-dropdown-item>Add</el-dropdown-item>
                    <el-dropdown-item>Delete</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
              <span>Tom</span>
            </div>
          </el-header>
          <router-view></router-view>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>



<script>
import { Menu as IconMenu, Message, Setting } from "@element-plus/icons-vue";
import "./adminsub/blogadmin.vue";
import "./adminsub/useradmin.vue";

export default {
  name: "admin",
  data() {
    return {};
  },
};
</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
.text-large {
  margin-left: 10px;
  height: 60px;
  display: inline-block;
  line-height: 60px;
  font-size: 25px;
  font-family: 华文行楷, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu;
}
.el-header {
  overflow: hiden;
  position: relative;
}
.profile {
  display: inline-block;
  position: absolute;
  right: 70px;
  top: 15px;
  background-color: pink;
  width: 30px;
  height: 30px;
  border-radius: 15px;
}
.el-link {
  margin-right: 8px;
}
.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}
.quite {
  display: inline;
  position: absolute;
  right: 110px;
  height: 60px;
  line-height: 60px;
}
.name {
  position: absolute;
  right: 35px;
  height: 60px;
  line-height: 60px;
  font-size: 13px;
}
.fenge {
  position: absolute;
  display: inline-block;
  border-left: 1px rgb(85, 88, 91) solid;
  height: 17px;
  top: 22px;
  right: 110px;
}
</style>