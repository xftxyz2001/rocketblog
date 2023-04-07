import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import mitt from 'mitt'



import '@/assets/icons/iconfont.css'
import '@/assets/icons/iconfont.js';

axios.defaults.withCredentials = true;
// axios.defaults.baseURL = "http://8.130.81.23:8080";
axios.defaults.baseURL = window.location.origin;
/*
axios.get("/api").then((res) => {
  var result = res.data;
  if (result.code == 0) {
    // result.data is the data you want
  } else {
    // result.message is the error message
  }
});
*/


const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.config.globalProperties.Bus = mitt()
app.mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}


