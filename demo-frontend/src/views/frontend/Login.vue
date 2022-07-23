<script setup>
import { reactive, computed, inject } from "vue";
import { useRouter } from "vue-router";
import { useTemplateStore } from "@/stores/template";
import Swal from "sweetalert2";
import axios from "axios";


// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
import { required } from "@vuelidate/validators";

// Main store
const store = useTemplateStore();
const router = useRouter();
const renovate = inject("reload");


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
    },
    password: {
      required,
    },
  };
});

// Use vuelidate
const v$ = useVuelidate(rules, state);

// On form submission
async function onSubmit() {
  const result = await v$.value.$validate();
  const user = {
    account: state.account,
    password: state.password,
  };

  if (!result) {
    // notify user form is invalid
    return;
  }

  axios
    .post("http://localhost:8088/login", user)
    .then(function (response) {

      if (response.status === 200) {
        // Swal.fire("登入成功 ~", "｡:.ﾟヽ(*´∀`)ﾉﾟ.:｡", "success");
        Swal.fire({
          title: "登入成功 ~",
          text: "｡:.ﾟヽ(*´∀`)ﾉﾟ.:｡",
          timer: 1000,
          icon: "success"
        });
        if (response.data.data.user != null && response.data.data.user.userId == 1) {
          localStorage.setItem("access-admin", JSON.stringify(response.data));
          // store.getStates({ admin: response.data });
          location.replace("http://localhost:8080/#/backend/dashboard");
        } else if (response.data.data.user != null) {
          localStorage.setItem("access-user", JSON.stringify(response.data));
          // store.getStates({ admin: response.data });
          location.replace("http://localhost:8080/#/");
          renovate();
        } else if (response.data.data.business != null) {
          sessionStorage.setItem("access-business", JSON.stringify(response.data));
          console.log(response);
          router.replace({ path: '/business/backend' });
        }
      }
    })
    .catch(function (error) {
      if (error.response.status === 401) {
        Swal.fire("登入失敗,帳號異常", "∑(￣□￣;)", "error");
      } else if (error.response.status === 400) {
        Swal.fire("登入失敗,帳號或密碼錯誤", "(〒︿〒)", "error");
      } else {
        console.log(error.code); // 印錯誤狀態代碼
        console.log(error.message); // 印錯誤內容
      }
    });

  // Go to dashboard
  // router.push({ name: "backend-pages-auth" });
}

function addUser() {
  state.account = 'hold10sec8763@gmail.com';
  state.password = '10Sec8763';
}

function addUser2() {
  state.account = 'nsdk8853@gmail.com';
  state.password = 'Aa111111';
}

function addAdmin() {
  state.account = 'a1kgkms11@gmail.com';
  state.password = 'Aa222222';
}

function showPassword() {
  var x = document.getElementById("login-password");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

function addform() {
  state.account = 'carefuladdicted@gmail.com';
  state.password = '10x65q76d53';
}
</script>

<template>
  <!-- Page Content -->
  <div class="hero-static d-flex align-items-center" style="
      background-image: url('/assets/media/photos/login_bg.jpg');
      background-size: cover;
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-position: center;
    ">
    <div class="content">
      <div class="row justify-content-center push">
        <div class="col-md-8 col-lg-6 col-xl-4">
          <!-- Sign In Block -->
          <BaseBlock title="登入" class="mb-0">
            <template #options>
              <RouterLink :to="{ name: '' }" class="btn-block-option fs-sm" @click="forgetPassword"><b>忘記密碼?</b>
              </RouterLink>
              <RouterLink :to="{ name: 'userRegister' }" class="btn-block-option">
                <i class="fa fa-user-plus"></i>
              </RouterLink>
            </template>

            <div class="p-sm-3 px-lg-4 px-xxl-5 py-lg-5">
              <h1 class="h2 mb-1">登入「愛蔬網」</h1>
              <p class="fw-medium text-muted">愛蔬網是有279個用戶的美食平台</p>
              <p>點擊下方按鈕登入以繼續</p>
              <!-- <p>登入後即代表您已閱讀並且</p>
              同意<a href="#">服務條款</a>及<a href="#">隱私政策</a> -->

              <!-- Sign In Form -->
              <form @submit.prevent="onSubmit">
                <div class="py-3">
                  <div class="mb-4">
                    <input type="text" class="form-control form-control-alt form-control-lg" id="login-username"
                      name="login-username" placeholder="E-mail" :class="{
                        'is-invalid': v$.account.$errors.length,
                      }" v-model="state.account" @blur="v$.account.$touch" autocomplete="username" />
                    <div v-if="v$.account.$errors.length" class="invalid-feedback animated fadeIn">
                      請輸入你的帳號
                    </div>
                  </div>
                  <div class="mb-4">
                    <input type="password" class="form-control form-control-alt form-control-lg" id="login-password"
                      name="login-password" placeholder="密碼" :class="{
                        'is-invalid': v$.password.$errors.length,
                      }" v-model="state.password" @blur="v$.password.$touch" autocomplete="current-password" />
                    <div v-if="v$.password.$errors.length" class="invalid-feedback animated fadeIn">
                      請輸入你的密碼
                    </div>
                  </div>
                  <div class="mb-4">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" value="" id="login-remember"
                        name="login-remember" />
                      <label class="form-check-label" for="login-remember"><b>記住我</b></label>
                    </div>
                    <br>
                    <div>
                      <input class="form-check-input" type="checkbox" @click="showPassword()">&nbsp;&nbsp;<b>顯示密碼</b>
                    </div>
                  </div>
                </div>
                <div class="row mb-4">
                  <div class="col-md-6 col-xl-5">
                    <button type="submit" class="btn w-100 btn-alt-primary" @click="handleSubmit">
                      <i class="fa fa-fw fa-sign-in-alt me-1 opacity-50"></i>
                      <b>登入</b>
                    </button>
                  </div>

                  <div class="col-md-6 col-xl-5">
                    <RouterLink :to="{ name: 'userRegister' }" class="btn w-100 btn-alt-primary">
                      <i class="fa fa-fw fa-sign-in-alt me-1 opacity-50"></i>
                      <b>註冊</b>
                    </RouterLink>
                  </div>
                </div>
                <div class="row mb-4">
                  <div class="col-md-6 col-xl-5">
                    <button type="button" class="btn w-100 btn-alt-success" @click="addUser">
                      <i class="fa fa-fw fa-plus me-1 opacity-50"></i>會員
                    </button>
                  </div>
                  <div class="col-md-6 col-xl-6">
                    <button type="button" class="btn w-100 btn-alt-success" @click="addAdmin">
                      <i class="fa fa-fw fa-plus me-1 opacity-50"></i>管理員
                    </button>
                  </div>
                </div>
                <div class="row mb-4">
                  <div class="col-md-6 col-xl-6">
                    <button type="button" class="btn w-100 btn-alt-success" @click="addUser2">
                      <i class="fa fa-fw fa-plus me-1 opacity-50"></i>會員2
                    </button>
                  </div>
                  <div class="col-md-6 col-xl-6">
                    <button type="button" class="btn w-100 btn-alt-warning" @click="addform">
                      <i class="fa fa-fw fa-store me-1 opacity-50"></i> 商家
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
import { useLoading } from "vue3-loading-overlay";
const loader = useLoading({
  loader: 'dots',
  color: '#CCDBE2'
});
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
    forgetPassword() {
      const user = {
        account: this.state.account,
        password: this.state.password,
      };
      var email = document.getElementById("login-username").value;

      Swal.fire({
        title: "忘記密碼?",
        html: `將發送密碼信至 ${email} <br> 原本密碼將被覆蓋  `,
        showCancelButton: true,
        confirmButtonText: "確認",
        cancelButtonText: '取消',
        showLoaderOnConfirm: true,
        allowOutsideClick: true,

        preConfirm: async () => {
          loader.show();
          return axios.post("http://localhost:8088/user/sendMail", user)
            .then(response => {
              console.log(response.status);
              console.log(response.data);
              if (response.status === 200) {
                loader.hide();
                Swal.fire(`密碼信已寄出,請前往${email}查看`, "༼ つ ◕_◕ ༽つ", "success");
                return response.data;
              }
            })
            .catch(async () => {
              return axios.post("http://localhost:8088/business/sendMail", user)
                .then(response => {
                  if (response.status === 200) {
                    loader.hide();
                    Swal.fire({
                      title: "密碼信已寄出",
                      text: `請前往${email}查看`,
                      timer: 1000,
                      icon: "success"
                    });
                    return response.data;
                  }
                })
            }).catch((error) => {
              if (error.response.status === 400) {
                loader.hide();
                Swal.fire("請確認帳號輸入是否正確", "／人◕ ‿崩‿ ◕人＼", "error");
              } else {
                console.log(error.response.status)
                console.log(error.response.data.error)
              }
            })
        }
      })
    }
  },
};
// 加下面3行防止使用鍵盤(指alt + 鍵盤左鍵等)、滑鼠手勢等方式返回前頁,點連結前往的有些不能擋
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
  history.pushState(null, null, document.URL);
});
</script>
