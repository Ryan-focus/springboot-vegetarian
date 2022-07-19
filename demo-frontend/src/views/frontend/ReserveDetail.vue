<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { useTemplateStore } from "@/stores/template";
import { reactive, computed, ref } from "vue";
import { useRouter } from "vue-router";

// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
import {
  required,
  minLength,
  between,
  email,
  decimal,
  integer,
  url,
  sameAs,
} from "@vuelidate/validators";
//using Axios 
import axios from "axios";
// useRoute 接值 ，做查詢 
import { useRoute } from "vue-router";
//接值
const route = useRoute();
const data = reactive({
  loading: false,
});
const restaurantNumber = route.params.restaurantId;
console.log("restaurantNumber=" + restaurantNumber);
const reserveOrder = ref();
const restaurantName = ref();
const restaurantTel = ref();
const restaurantAddress = ref();
const restaurantCategory = ref();
const restaurantType = ref();
const restaurantBusinessHours = ref();
const restaurantScore = ref();
const imageUrl = ref();

const reserveRestaurant = function () {
  data.loading = true;
  axios
    .get(`http://localhost:8088/restaurants/${restaurantNumber}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      reserveOrder.value = res.data;
      restaurantName.value = res.data.restaurantName;
      restaurantTel.value = res.data.restaurantTel;
      restaurantAddress.value = res.data.restaurantAddress;
      restaurantCategory.value = res.data.restaurantCategory;
      restaurantType.value = res.data.restaurantType;
      restaurantBusinessHours.value = res.data.restaurantBusinessHours;
      restaurantScore.value = res.data.restaurantScore;
      imageUrl.value = res.data.imageUrl;
    })
    .catch((error) => {
      console.log(error, "失敗");
    }).finally(() => {
      data.loading = false;
    });
}
reserveRestaurant();
const store = useTemplateStore();
const router = useRouter();

// Input state variables
const state = reactive({
  username: null,
  email: null,
  password: null,
  confirmPassword: null,
  suggestions: null,
  skill: null,
  currency: null,
  website: null,
  digits: null,
  number: null,
  range: null,
  terms: null,
});


// Validation rules
const rules = computed(() => {
  return {
    username: {
      required,
      minLength: minLength(3),
    },
    email: {
      required,
      email,
    },
    password: {
      required,
      minLength: minLength(5),
    },
    confirmPassword: {
      required,
      sameAs: sameAs(state.password),
    },
    suggestions: {
      required,
      minLength: minLength(3),
    },
    skill: {
      required,
    },
    currency: {
      required,
      decimal,
    },
    website: {
      required,
      url,
    },
    digits: {
      required,
      integer,
    },
    number: {
      required,
      decimal,
    },
    range: {
      required,
      between: between(1, 5),
    },
    terms: {
      sameAs: sameAs(true),
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

  // perform async actions
}

//v-bind value 
const adult = ref(2);
const kid = ref(0);

const format = (date) => {
  const day = date.getDate();
  const month = date.getMonth() + 1;
  const days = date.getDay();
  const daysArray = ['週日', '週一', '週二', '週三', '週四', '週五', '週六'];

  return `${month}月${day}日 ${daysArray[days]}`;
}

//接收的資料ref
//當日統計
//取得全部人數
//取得全部組數

// ref會自己抓值，這邊還要另外宣告圖片是因為:src會去抓路徑，沒有定義會變undefined當掉
//其他的值ref抓到後會自己帶入變成json


</script>
<template >
  <!-- Hero -->
  <BasePageHeading class="row">
    <div class="col-md-4 offset-md-6">
      <h1 class="mt-1 mb-0 p-0 fw-bold"> 確認訂位與填寫聯絡資訊</h1>
    </div>
  </BasePageHeading>
  <!-- END Hero -->
  <div class="content">
    <div class="row">
      <div class="col-lg-6">
        <div class="block-rounded block row g-0">
          <section class="">
            <BaseBlock class="mb-2" content-full>
              <img
                src="https://inline.imgix.net/branch/-Lp_rdeeFue9DT5RqSlS:inline-live-1--Lp_rdihy1BAcnA19fes-a658eb13-81e8-4201-820b-f4bda42cd884_20220621SOUTHEAST-1140X456.jpg"
                class=" d-block w-100 img-fluid" alt="" />
              <ul class="nav nav-pills flex-column push">
                <li class="nav-item my-1">
                  <span class="nav-link d-flex justify-content-between align-items-center">
                    <i class="fa fa-location-dot fa-inbox me-1 opacity-50"> </i>
                    <h4 class="mt-3 fw-normal">典華豐FOOD海陸百匯</h4>
                  </span>
                </li>

                <li class="nav-item my-1">
                  <span class="nav-link d-flex justify-content-between align-items-center">
                    <i class="fa fa-user fa-inbox me-1 opacity-50"> </i>
                    <h5 class="mt-3 fw-normal">2 大 3 小</h5>
                  </span>
                </li>
                <li class="nav-item my-1">
                  <span class="nav-link d-flex justify-content-between align-items-center">
                    <i class="fa fa-calendar-days fa-inbox me-1 opacity-50"> </i>
                    <h5 class="mt-3 fw-normal">2022年7月20日 週三</h5>
                  </span>
                </li>
              </ul>
            </BaseBlock>
          </section>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="block-rounded block row flex-md-row-reverse g-0">
          <form @submit.prevent>
            <BaseBlock>

              <div class="row justify-content-center py-sm-3 py-md-5">
                <div class="col-sm-10 col-md-8">
                  <div class="mb-4">
                    <label class="form-label" for="block-form5-username">Username</label>
                    <input type="text" class="form-control" id="block-form5-username" name="block-form5-username"
                      placeholder="Enter your username.." />
                  </div>
                  <div class="mb-4">
                    <label class="form-label" for="block-form5-password">Password</label>
                    <input type="password" class="form-control" id="block-form5-password" name="block-form5-password"
                      placeholder="Enter your password.." />
                  </div>
                  <div class="mb-4">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" value="" id="block-form5-remember-me"
                        name="block-form5-remember-me" />
                      <label class="form-check-label" for="block-form5-remember-me">Remember Me?</label>
                    </div>
                  </div>
                </div>
              </div>
            </BaseBlock>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!-- Footer -->
  <footer id="page-footer" class="bg-body-light">
    <div class="content py-5">
      <div class="row fs-sm fw-medium">
        <div class="col-sm-6 order-sm-2 py-1 text-center text-sm-end">
          <!-- Crafted with -->
          <!-- <i class="fa fa-heart text-danger"></i> by -->
          <!-- <a class="fw-semibold" href="https://1.envato.market/ydb"
              ></a -->
        </div>
        <div class="col-sm-6 order-sm-1 py-1 text-center text-sm-start">
          <a class="fw-semibold" href="/">{{
              store.app.name + " " + store.app.version
          }}</a>
          &copy; {{ store.app.copyright }}
        </div>
      </div>
    </div>
  </footer>
  <!-- END Footer -->
</template>
