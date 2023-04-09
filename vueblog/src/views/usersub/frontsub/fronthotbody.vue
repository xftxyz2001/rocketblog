<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4">
      <Left></Left>
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="10" style="position: fixed"
      ><div style="position: fixed; width: 100%; top: 70px">
        <router-view></router-view>
      </div>
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4"
      ><img
        :src="ipsignimgsrc"
        alt=""
        srcset=""
        style="position: fixed; width: 23%; right: 50px; top: 80px"
      />
      <div class="grid-content ep-bg-purple" />
    </el-col>
    <el-col :span="4">
      <div class="grid-content ep-bg-purple" />
    </el-col>
  </el-row>
</template>

<script setup>
const ipsignimgsrc = ref("");

axios.get("/images/ipsign").then((res) => {
  ipsignimgsrc.value = res.data.data;
});
</script>

<script>
import Left from "@/views/usersub/frontsub/fronthotsub/left.vue";
import axios from "axios";
import { defineComponent, ref } from "vue";
export default defineComponent({
  name: "FrontHotBody",
  components: {
    Left,
  },
});
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>