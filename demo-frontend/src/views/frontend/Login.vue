<script setup>
import { reactive, computed } from "vue";
import { useRouter } from "vue-router";
import { useTemplateStore } from "@/stores/template";
import axios from "axios";

// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
import { required, minLength, url } from "@vuelidate/validators";

// Main store
const store = useTemplateStore();
const router = useRouter();

// Input state variables
const state = reactive({
  account: null,
  password: null,
});

// Validation rules
const rules = computed(() => {
  return {
    account: {
      required,
      // minLength: minLength(3),
    },
    password: {
      required,
      // minLength: minLength(5),
    },
  };
});

// Use vuelidate
const v$ = useVuelidate(rules, state);

// On form submission
async function onSubmit() {
  const result = await v$.value.$validate();

  if (!result) {
    // notify user form is invalid
    return;
  }

  // Go to dashboard
  // router.push({ name: "backend-pages-auth" });
}
</script>

<template>
  <!-- Page Content -->
  <div
    class="hero-static d-flex align-items-center"
    style="
      background-image: url(https://pixabay.com/get/gc402009db5c66ce787a3138502ec8263d7a7cb75275faca9c583be46f4c4f5a0a2bec18fbe8ee0d30f3ffc3f9132d6e8c78235dfc76c120d357893356f61fa51_1920.jpg);
    "
  >
    <div class="content">
      <div class="row justify-content-center push">
        <div class="col-md-8 col-lg-6 col-xl-4">
          <!-- Sign In Block -->
          <BaseBlock title="Sign In" class="mb-0">
            <template #options>
              <RouterLink
                :to="{ name: 'auth-reminder' }"
                class="btn-block-option fs-sm"
                >Forgot Password?</RouterLink
              >
              <RouterLink
                :to="{ name: 'auth-signup' }"
                class="btn-block-option"
              >
                <i class="fa fa-user-plus"></i>
              </RouterLink>
            </template>

            <div class="p-sm-3 px-lg-4 px-xxl-5 py-lg-5">
              <h1 class="h2 mb-1">OneUI</h1>
              <p class="fw-medium text-muted">Welcome, please login.</p>

              <!-- Sign In Form -->
              <form @submit.prevent="onSubmit" @submit="login">
                <div class="py-3">
                  <div class="mb-4">
                    <input
                      type="text"
                      class="form-control form-control-alt form-control-lg"
                      id="login-username"
                      name="login-username"
                      placeholder="account"
                      :class="{
                        'is-invalid': v$.account.$errors.length,
                      }"
                      v-model="state.account"
                      @blur="v$.account.$touch"
                    />
                    <div
                      v-if="v$.account.$errors.length"
                      class="invalid-feedback animated fadeIn"
                    >
                      Please enter your account
                    </div>
                  </div>
                  <div class="mb-4">
                    <input
                      type="password"
                      class="form-control form-control-alt form-control-lg"
                      id="login-password"
                      name="login-password"
                      placeholder="Password"
                      :class="{
                        'is-invalid': v$.password.$errors.length,
                      }"
                      v-model="state.password"
                      @blur="v$.password.$touch"
                    />
                    <div
                      v-if="v$.password.$errors.length"
                      class="invalid-feedback animated fadeIn"
                    >
                      Please enter your password
                    </div>
                  </div>
                  <div class="mb-4">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        value=""
                        id="login-remember"
                        name="login-remember"
                      />
                      <label class="form-check-label" for="login-remember"
                        >Remember Me</label
                      >
                    </div>
                  </div>
                </div>
                <div class="row mb-4">
                  <div class="col-md-6 col-xl-5">
                    <button type="submit" class="btn w-100 btn-alt-primary" @click="handleSubmit">
                      <i class="fa fa-fw fa-sign-in-alt me-1 opacity-50"></i>
                      Sign In
                    </button>
                  </div>
                </div>
              </form>
              <!-- END Sign In Form -->
            </div>
          </BaseBlock>
          <!-- END Sign In Block -->
        </div>

        <!-- Footer -->
        <div class="fs-sm text-center text-muted py-3">
          <strong>{{ store.app.name + " " + store.app.version }}</strong> &copy;
          {{ store.app.copyright }}
        </div>
        <!-- END Footer -->
      </div>
    </div>
    <!-- END Page Content -->
  </div>
</template>
<script>
export default {
  data(){
    return {
      state:{
        account:"",
        password:""
        }
    }
  },
methods: {
  login(){
    const user ={
      account: this.state.account,
      password:this.state.password
    }
    axios.post('http://localhost:8088/login', user).then(function(response){
      // let _this = this
    console.log(response.data)
    if(response.status === 200) {

      localStorage.setItem('access-admin', JSON.stringify(response.data))
      
      location.replace("http://localhost:8080/#/backend/dashboard"); //登入成功擋返回前頁回到登入頁
      alert("登入成功");
    } 
  }).catch((error) => {
    alert("登入失敗");
  })
  }
}
}
</script>
