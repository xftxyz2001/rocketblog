<template>
  <!-- 点赞最多日志展示 -->
  点赞
  <el-card v-for="o in 4" :key="o" class="box-card">
    <template #header>
      <div class="card-header">
        <div>
          <el-avatar
            :size="32"
            class="mr-3"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            style="margin: 12px 5px 0px 20px"
          />
          <span
            class="text-large font-600 mr-3"
            style="
              display: inline-block;
              overflow: hidden;
              margin: -3px 5px 3px 0px;
            "
          >
            Title
          </span>
        </div>
        <el-button class="button" text style="color: #24acf2"
          ><el-icon style="margin: 0 2px 1.5px 0"><Plus /></el-icon
          >关注</el-button
        >
      </div>
    </template>
    <div v-for="o in 4" :key="o" class="text item">{{ "List item " + o }}</div>
  </el-card>
</template>


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