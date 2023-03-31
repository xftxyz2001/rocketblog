<template>
  <!-- 所有关注日志展示 -->
  hot最新
  <el-card v-for="o in 4" :key="o" class="box-card">
    <template #header>
      <div class="card-header">
        <div>
          <el-avatar
            :size="32"
            class="mr-3"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            style="margin: 12px 5px 0px 0px"
          />
          <span
            class="text-large font-600 mr-3"
            style="
              display: inline-block;
              overflow: hidden;
              margin: -3px 5px 3px 5px;
            "
          >
            返回的title
          </span>
        </div>

        <el-button class="button" text style="color: #24acf2"
          ><el-icon style="margin: 0 2px 1.5px 0"><Plus /></el-icon
          >关注</el-button
        >
      </div>
      <span style="font-size: 5px; margin-left: 5px">作者：</span>
    </template>

    <div>
      <label style="margin-right: 16px">Switch Loading</label>
      <el-switch v-model="loading" />
    </div>
    <el-skeleton style="width: 100%" :loading="loading" animated>
      <template #default>
        <div style="padding: 14px">
          <el-row>
            <el-col :span="6"
              ><div class="grid-content ep-bg-purple" />
              返回的封面图片</el-col
            >
            <el-col :span="12"
              ><div class="grid-content ep-bg-purple-light" />
              <div style="width: 100%">返回的数据</div></el-col
            >
          </el-row>
        </div>
        <div class="bottom card-header">
          <div class="time" style="font-size: 10px">返回的时间</div>
          <div>
            <span class="iconfont" style="margin-right: 10px">&#xe600;</span>
            <span class="iconfont" style="margin-right: 5px">&#xe603;</span>
          </div>
        </div>
      </template>
    </el-skeleton>
  </el-card>
</template>

<script setup>
import { ref } from "vue";

const loading = ref(true);
const currentDate = new Date().toDateString();
</script>
<script>
export default {};
</script>


<style scoped>
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
  margin-bottom: 10px;
  /* vertical-align:baseline; */
}
</style>