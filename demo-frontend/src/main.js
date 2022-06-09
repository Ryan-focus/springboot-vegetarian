// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false


var axios = require('axios')
// 全域性註冊，之後可在其他元件中通過 this.$axios 傳送資料
Vue.prototype.$axios = axios

// 設定反向代理，前端請求預設傳送到 http://localhost:8888/api
axios.defaults.baseURL = 'http://localhost:8088/api'
/* eslint-disable no-new */

Vue.use(ElementUI)

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
