<template style="absolute">
  <div class="nav" ref="left">
    <h3 class="mb-2" style="margin-left: 22px">首页</h3>
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
      <router-link :to="{ name: 'allconcern' }"
        ><el-menu-item index="1" style="border-top: 1px #c8c9cc solid">
          <span class="iconfont" style="margin-right: 5px">&#xe8bc;</span>
          <span>全部关注</span>
        </el-menu-item></router-link
      >
      <router-link :to="{ name: 'mostlike' }">
        <el-menu-item index="2">
          <span class="iconfont" style="margin-right: 5px">&#xe60f;</span>
          <span>点赞最多</span>
        </el-menu-item></router-link
      >
      <router-link :to="{ name: 'latest' }">
        <el-menu-item index="3">
          <span class="iconfont" style="margin-right: 5px">&#xe61e;</span>
          <span>最新发布</span>
        </el-menu-item>
      </router-link>
      <router-link :to="{ name: 'friend' }">
        <el-menu-item index="4">
          <span class="iconfont" style="margin-right: 5px">&#xe601;</span>
          <span>好友发布</span>
        </el-menu-item>
      </router-link>
    </el-menu>
  </div>
</template>

<script setup >
</script>
<script>
</script>

<style scoped>
.nav {
  position: fixed;
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