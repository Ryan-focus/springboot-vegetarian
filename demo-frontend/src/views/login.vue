<template>
  <body id="login-page">
    <el-form class="login-container" label-position="left" label-width="0px">
      <h3 class="login_title">系統登入</h3>
      <el-form-item>
        <el-input
          type="text"
          v-model="loginForm.loginEmail"
          auto-complete="off"
          placeholder="Email"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="password"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%; border: none"
          @click="login"
          >登入</el-button
        >
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
import { userLogin } from "@/api/user";
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        loginEmail: "",
        password: "",
      },
      responseResult: [],
    };
  },
  methods: {
    login() {
      var _this = this;
      userLogin({
        loginEmail: this.loginForm.loginEmail,
        password: this.loginForm.password,
      }).then((resp) => {
        let code=resp.data.code;
        if(code===200){
          let data=resp.data.data;
          let token=data.token;
          let user=data.user;
          //儲存token
          _this.$store.commit('SET_TOKEN', token);
          //儲存user，優雅一點的做法是token和user分開獲取
          _this.$store.commit('SET_USER', user);
          console.log(_this.$store.state.token);
          var path = this.$route.query.redirect
          this.$router.replace({
   path: path === '/' || path === undefined ? '/' : path})
        }
      });
    },
  },
};
</script>

<style scoped>
#login-page {
  background: url("../assets/img/background.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body {
  padding: 0;
  margin: 0px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
