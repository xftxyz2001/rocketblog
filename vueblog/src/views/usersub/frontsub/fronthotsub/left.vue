<template style="absolute">
  <div class="nav" ref="left">
    <h3 class="mb-2" style="margin-left: 22px">热门</h3>
    <el-menu
      active-text-color="#ffd04b"
      background-color="#fff"
      class="el-menu-vertical-demo"
      text-color="#000"
      @open="handleOpen"
      @close="handleClose"
      style="
        padding: 0;
        --el-menu-border-color: #fff;
        position: sticky;
        top: 10px;
      "
    >
      <router-link :to="{ name: 'hotlatest' }"
        ><el-menu-item index="1" style="border-top: 1px #c8c9cc solid">
          <span class="iconfont" style="margin-right: 5px">&#xe61e;</span>
          <span>最新发布</span>
        </el-menu-item></router-link
      >
      <router-link :to="{ name: 'hotmostlike' }">
        <el-menu-item index="2">
          <span class="iconfont" style="margin-right: 5px">&#xe60f;</span>
          <span>点赞最多</span>
        </el-menu-item></router-link
      >
    </el-menu>
  </div>
</template>

<script setup >
</script>
<script>
export default {
  name: "left",
};
</script>

<style scoped>
.nav {
  position: fixed;
  z-index: 1;
  top: 70px;
  background-color: #fff;
  width: 200px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.05), 0 6px 20px 0 rgba(0, 0, 0, 0.05);
}
.el-menu-item {
  --el-menu-hover-bg-color: #0a49e8;
  --el-menu-active-color: #e3c455;
}
</style>