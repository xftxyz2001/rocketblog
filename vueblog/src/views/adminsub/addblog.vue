<template>
  <div style="width: 100%; height: 700px; overflow: scroll">
    <div class="common-layout">
      <el-container>
        <el-header><el-page-header @back="back">
            <template #content>
              <el-avatar :size="32" class="mr-3" :src="userdata.avatar" />
            </template>
            <template #extra>
              <div class="flex items-center">
                <!-- <el-button @click="save">保存</el-button> -->
                <el-button type="primary" class="ml-2" @click="submit">发布</el-button>
              </div>
            </template>
          </el-page-header></el-header>

        <el-main style="margin-bottom: 30px">
          <el-row style="margin-bottom: 10px">
            <sapn style="font-weight: 700; font-size: 20px; margin-right: 20px">标题</sapn>
            <el-input style="width: 60%" v-model="blogTitle"></el-input>
            <span>作者：</span>
            <el-input v-model="author.userid" placeholder="" @blur="getauthor" />
            <el-avatar :size="32" class="mr-3" :src="author.avatar" />
          </el-row>
          <el-row style="margin-bottom: 10px">封面图片（可选）
            <el-upload class="avatar-uploader" action="/images/upload" :on-preview="handlePreview"
              :on-remove="handleRemove" :before-remove="beforeRemove" multiple :limit="1" :on-exceed="handleExceed"
              :on-success="handleAvatarSuccess" :file-list="fileList">
              <img v-if="coverImage" :src="coverImage" class="avatar" /><el-icon v-else class="avatar-uploader-icon">
                <Plus />
              </el-icon> </el-upload></el-row>
          <QuillEditor v-model:content="content" :options="editorOption" contentType="html" theme="snow"
            style="min-height: 400px" />
        </el-main>
      </el-container>
    </div>
  </div>
</template>
<script setup>
// import axios from "axios";
import router from "@/router";
import quillTool from "@/utils/quillTool";
import { Quill, QuillEditor } from "@vueup/vue-quill";
import axios from "axios";
import { ElMessage } from "element-plus";
import { ImageExtend, QuillWatch } from "quill-image-extend-module";
import ImageResize from "quill-image-resize-module";
import "quill/dist/quill.bubble.css";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import { ref } from "vue";
const userdata = ref({});
const author = ref({});

axios.get("/user/i").then((res) => {
  userdata.value = res.data.data;
});

function getauthor(){
  axios.get("/user/"+author.value.userid).then((res) => {
    if (res.data == 0){
      author.value = res.data.data;
    }
  });
}

function back() {
  router.push({ name: "blogadmin" });
}
</script>
<script>
// 工具栏配置
const toolbarOptions = [
  ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
  ["blockquote", "code-block"], // 引用
  [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表
  [{ script: "sub" }, { script: "super" }], // 上标/下标
  [{ indent: "-1" }, { indent: "+1" }], // 缩进
  [{ direction: "rtl" }], // 文本方向
  [{ size: ["small", false, "large", "huge"] }], // 字体大小
  [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
  [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
  [{ font: [] }], // 字体种类
  [{ align: [] }], // 对齐方式
  ["clean"], // 清除文本格式
  // ["link", "image", "video"], // 链接、图片、视频
  ["image"], // 链接、图片、视频
];
Quill.register("modules/ImageResize", ImageResize);
Quill.register(quillTool, true);
Quill.register("modules/ImageExtend", ImageExtend);
export default {
  components: { QuillEditor },
  props: ["model"],
  data() {
    return {
      blogTitle: "",
      content: "",
      coverImage: "",
      editorOption: {
        theme: "snow",
        placeholder: "请输入",
        modules: {
          ImageExtend: {
            name: "file", // 参数名
            action: "/images/upload", // 服务器地址，如果为空则采用base64插入图片
            headers: (xhr) => {
              // 设置请求头参数（选填）
            },
            response: (res) => {
              console.log(res);
              return "" + res.data;
            },

            size: 8, // 图片不能超过8M
            sizeError: () => {
              this.$message.error("粘贴图片大小不能超过8MB!");
            },
          },
          toolbar: {
            container: toolbarOptions,
            handlers: {
              image: function (value) {
                QuillWatch.emit(this.quill.id);
              },
              // link: function (value) {
              //   if (value) {
              //     var href = prompt("请输入链接地址：");
              //     this.quill.format("link", href);
              //   } else {
              //     this.quill.format("link", false);
              //   }
              // },
              // video: function (value) {
              //   if (value) {
              //     var href = prompt("请输入视频链接：");
              //     this.quill.format("video", href);
              //   } else {
              //     this.quill.format("video", false);
              //   }
              // },
            },
          },
          ImageResize: {
            displayStyles: {
              backgroundColor: "black",
              border: "none",
              color: "white",
            },
            modules: ["Resize", "DisplaySize", "Toolbar"],
          },
        },
      },
    };
  },
  methods: {
    save() {
      var blogdata = {
        blogStatus: 0,
        coverImage: this.coverImage,
        blogTitle: this.blogTitle,
        blogContent: this.content,
      };

      axios.post("/blog/publish", blogdata).then((res) => {
        ElMessage({
          showClose: true,
          message: "保存成功",
          type: "success",
        });
        this.blogTitle = "";
        this.content = "";
        router.push({ name: "hotlatest" });
      });
    },
    submit() {
      var blogdata = {
        coverImage: this.coverImage,
        blogTitle: this.blogTitle,
        blogContent: this.content,
        userid: author.value.userid,
      };

      axios.post("/admin/blog", blogdata).then((res) => {
        ElMessage({
          showClose: true,
          message: "发表成功",
          type: "success",
        });
        this.blogTitle = "";
        this.content = "";
        router.push({ name: "hotlatest" });
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.coverImage = "";
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handleAvatarSuccess(res) {
      console.log(res);
      this.coverImage = res.data;
    },
  },
};
</script>
 
<style scoped>
.ql-container {
  height: 300px;
  line-height: normal;
  width: auto;
}

span.ql-size {
  max-width: 80px !important;
}

.ql-tooltip[data-mode="link"]::before {
  content: "请输入链接地址:";
}

.ql-tooltip.ql-editing a.ql-action::after {
  border-right: 0px;
  content: "保存";
  padding-right: 0px;
}

.ql-tooltip[data-mode="video"] {
  left: 0 !important;
}

.ql-tooltip[data-mode="video"]::before {
  content: "请输入视频地址:";
}

.ql-picker.ql-size .ql-picker-label::before,
.ql-picker.ql-size .ql-picker-item::before {
  content: "14px";
}

.ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
.ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
  content: "10px";
}

.ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
.ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
  content: "18px";
}

.ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
.ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
  content: "32px";
}

.ql-picker.ql-header .ql-picker-label::before,
.ql-picker.ql-header .ql-picker-item::before {
  content: "文本";
}

.ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}

.ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "标题2";
}

.ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "标题3";
}

.ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "标题4";
}

.ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "标题5";
}

.ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "标题6";
}

.ql-picker.ql-font .ql-picker-label::before,
.ql-picker.ql-font .ql-picker-item::before {
  content: "标准字体";
}

.ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
.ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
  content: "衬线字体";
}

.ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
.ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
  content: "等宽字体";
}

.avatar-uploader .avatar {
  width: 150px;
  height: 150px;
  display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 150px;
  height: 150px;
  text-align: center;
}
</style>