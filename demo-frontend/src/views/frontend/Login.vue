<script setup>
import { reactive, computed } from "vue";
import { useRouter } from "vue-router";
import { useTemplateStore } from "@/stores/template";
import Swal from "sweetalert2";
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
      background-image: url('/assets/media/photos/login_bg.jpg');
      background-size: cover;
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-position: center;
    "
  >
    <div class="content">
      <div class="row justify-content-center push">
        <div class="col-md-8 col-lg-6 col-xl-4">
          <!-- Sign In Block -->
          <BaseBlock title="登入" class="mb-0">
            <template #options>
              <RouterLink
                :to="{ name: 'auth-reminder' }"
                class="btn-block-option fs-sm"
                ><b>忘記密碼?</b></RouterLink
              >
              <RouterLink
                :to="{ name: 'auth-signup' }"
                class="btn-block-option"
              >
                <i class="fa fa-user-plus"></i>
              </RouterLink>
            </template>

            <div class="p-sm-3 px-lg-4 px-xxl-5 py-lg-5">
              <h1 class="h2 mb-1">登入「愛蔬網」</h1>
              <p class="fw-medium text-muted">愛蔬網是有279個用戶的美食平台</p>
              <p>點擊下方按鈕登入以繼續</p>
              <p>登入後即代表您已閱讀並且</p>
              同意<a>服務條款</a>及<a>隱私政策</a>

              <!-- Sign In Form -->
              <form @submit.prevent="onSubmit" @submit="login">
                <div class="py-3">
                  <div class="mb-4">
                    <input
                      type="text"
                      class="form-control form-control-alt form-control-lg"
                      id="login-username"
                      name="login-username"
                      placeholder="E-mail"
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
                      請輸入你的帳號
                    </div>
                  </div>
                  <div class="mb-4">
                    <input
                      type="password"
                      class="form-control form-control-alt form-control-lg"
                      id="login-password"
                      name="login-password"
                      placeholder="密碼"
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
                      請輸入你的密碼
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
                        ><b>記住我</b></label
                      >
                    </div>
                  </div>
                </div>
                <div class="row mb-4">
                  <div class="col-md-6 col-xl-5">
                    <button
                      type="submit"
                      class="btn w-100 btn-alt-primary"
                      @click="handleSubmit"
                    >
                      <i class="fa fa-fw fa-sign-in-alt me-1 opacity-50"></i>
                      <b>登入</b>
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
  data() {
    return {
      state: {
        account: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      const user = {
        account: this.state.account,
        password: this.state.password,
      };
      axios
        .post("http://localhost:8088/login", user)
        .then(function (response) {
          // let _this = this
          console.log(response.data);
          if (response.status === 200) {
            localStorage.setItem("access-admin", JSON.stringify(response.data));

            location.replace("http://localhost:8080/#/backend/dashboard"); //登入成功擋返回前頁回到登入頁
            Swal.fire("登入成功 ~", "｡:.ﾟヽ(*´∀`)ﾉﾟ.:｡", "success");
          }
        })
        .catch((error) => {
          Swal.fire("登入失敗!", "(〒︿〒)", "error");
        });
    },
  },
};
</script>
