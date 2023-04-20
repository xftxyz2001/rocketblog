<template>
    <el-header style="text-align: right; font-size: 12px">

        <el-upload
            class="upload-demo"
            action="/images/uploads"
            :on-change="handleChange"
            :on-success="handleSuccess"
            :before-upload="beforeUpload"
            :on-progress="uploadProcess"
            :on-exceed="handleExceed"
            :before-remove="beforeRemove"
            :file-list="fileList"
            :multiple="true"
            :data="{ 'files': fileList }"
            id = "upload"
            style="margin-left: 50px; margin-bottom: 40px"
          >
            <el-button type="primary">选择图片</el-button>
            <template #tip>
              <div class="el-upload__tip">只能上传jpg/png文件，且不超过2M</div>
            </template>
          </el-upload>

    </el-header>
    <div></div>
    <el-main>
        <el-scrollbar>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column label="缩略图" width="80">
                    <template v-slot="scope">
                        <img :src="'/images/' + scope.row.filePath + '/50/50'" alt="" />
                    </template>
                </el-table-column>>
                <el-table-column prop="filePath" label="文件名" width="600" />
                <el-table-column prop="fileSize" label="文件大小(KB)" width="200">
                    <template v-slot="scope">
                        {{ (scope.row.fileSize / 1024).toFixed(2) }}
                    </template>
                </el-table-column>
                <el-table-column prop="lastModifiedTime" label="最后修改时间" width="200" />
                <el-table-column fixed="right" label="选项" width="180">
                    <template v-slot="scope">
                        <!-- 下载 -->
                        <el-button link type="primary" size="small" @click="download(scope.row.filePath)">下载</el-button>
                        <!-- 查看 -->
                        <el-button link type="primary" size="small" @click="view(scope.row.filePath)">查看</el-button>
                        <!-- 删除 -->
                        <el-button link type="danger" size="small" @click="delete_(scope.row.filePath)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-scrollbar>
    </el-main>
</template>
<script setup>
import axios from "axios";
import { ElMessage } from "element-plus";
import { ref } from "vue";
const tableData = ref([]);
const fileList = ref([]);

function getdata() {
    axios.get("/admin/images").then((res) => {
        var res = res.data;
        if (res.code == 0) {
            tableData.value = res.data;
        }
    });
}

getdata();

function download(filePath) {
    // 提示下载开始
    ElMessage.success("图片已开始下载");
    window.open("/images/download/" + filePath);
};

function view(filePath) {
    window.open("/images/" + filePath);
};

function delete_(filePath) {
    axios.delete("/admin/image/" + filePath).then((res) => {
        var res = res.data;
        if (res.code == 0) {
            ElMessage.success("删除成功");
            getdata();
        } else {
            ElMessage.error(res.message);
        }
    });
};

function beforeUpload(file) {
    fileList.push(file);
    return true;
};

function handleSuccess(response, file, fileList) {
    ElMessage.success(response)
    console.log(response);
};

</script>
<script>
export default {
    name: "imageadmin",
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
}

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

.demo-form-inline {
    display: inline-block;
    padding-top: 15px;
    position: absolute;
    left: 20px;
}

.adduser {
    position: absolute;
    right: 10px;
    top: 13px;
}

.el-table_1_column_1 {
    visibility: hidden;
    display: none;
}

.el-button--text {
    margin-right: 15px;
}

.el-select {
    width: 300px;
}

.el-input {
    width: 300px;
}

.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>