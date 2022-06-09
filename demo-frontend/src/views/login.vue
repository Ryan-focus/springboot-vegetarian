<template>
  <body id="login-page">
    <el-form class="login-container" label-position="left" label-width="0px">
      <h3 class="login_title">使用者登入</h3>
      <el-form-item>
        <el-input
          type="text"
          v-model="loginForm.loginEmail"
          auto-complete="off"
          placeholder="E-mail"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密碼"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%;  border: none" 
          @click="login"
          >請進入</el-button
        >
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
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
   login () {
   
   
        this.$axios
          .post('/login', {
            loginEmail: this.loginForm.loginEmail,
            password: this.loginForm.password
          })
          .then(successResponse => {
            console.log("already success"); 
            console.log(successResponse.status);
   
            if (successResponse.status === 200) {
   
   
              this.$router.replace({
   
   path: '/index'})
            }
          })
          .catch(failResponse => {
            alert("登入失敗");
   
          })
      }
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
  margin: 0;
}
body {
   
   
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
