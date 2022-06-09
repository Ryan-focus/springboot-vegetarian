import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
//匯入登入頁面元件
import Login from '@/views/login.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
     //新增登入頁面路由
     {
   
   
      path:'/login',
      name: 'Login',
      component: Login
    }
  ]
})
