<template>
  <el-backtop :right="100" :bottom="100" />

  <router-view></router-view>
</template>

<script>
import { Search } from "@element-plus/icons-vue";
import EditBlog from "@/views/usersub/editblog.vue";

import { defineComponent } from "vue";
export default defineComponent({
  name: "user",
  components: {
    EditBlog,
  },
});
</script>

<style scoped>
</style>