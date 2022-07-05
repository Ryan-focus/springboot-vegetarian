<script setup>
import { ref, reactive, computed, toRefs } from "vue";

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

import axios from "axios";

// Input state variables
const state = reactive({
  restaurantName: null,
  restaurantTel: null,
  restaurantAddress: null,
  restaurantBusinessHours: null,
  restaurantScore: null,
});

// Validation rules
const rules = computed(() => {
  return {
    restaurantName: {
      required,
      minLength: minLength(1),
    },
    restaurantTel: {
      required,
      minLength: minLength(1),
    },
    restaurantAddress: {
      required,
      minLength: minLength(1),
    },
    restaurantBusinessHours: {
      required,
      minLength: minLength(1),
    },
    restaurantScore: {
      required,
      between: between(0, 5),
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
</script>

<template>
  <!-- Hero -->

  <BasePageHeading title="餐廳管理" subtitle="更新餐廳資料">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/restaurants/dashboard">
              <i class="fa fa-burger"></i> 餐廳管理</a
            >
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <a class="link-fx" href="#/backend/restaurants/restaurantInfo">
              <i class="fa fa-store"></i> 餐廳資訊</a
            >
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-leaf"></i> 更新餐廳
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <!-- Basic -->
    <form @submit.prevent="onSubmit" @submit="updateRestaurant" id="forms">
      <BaseBlock title="更新餐廳表單" content-full>
        <div class="row push">
          <div class="col-lg-4">
            <p class="fs-sm text-muted">請填寫完整資訊</p>
          </div>
          <div class="col-lg-8 col-xl-5">
            <!-- 餐廳名稱開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-restaurantName"
                >餐廳名稱</label
              >
              <input
                type="text"
                id="val-restaurantName"
                class="form-control"
                v-model="restaurantName"
              />
            </div>
            <!-- 電話開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-restaurantTel">電話</label>
              <input
                type="text"
                id="val-restaurantTel"
                class="form-control"
                placeholder="02-23448743"
                v-model="restaurantTel"
              />
            </div>
            <!-- 地址開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-restaurantAddress">地址</label>
              <input
                type="text"
                id="val-restaurantAddress"
                class="form-control"
                placeholder="桃園市中壢區.."
                v-model="restaurantAddress"
              />
            </div>
            <!-- 餐廳類型開始 -->
            <div class="mb-4">
              <label class="form-label" for="example-select"
                >選擇餐廳類型</label
              >
              <select
                class="form-select"
                id="example-select"
                name="example-select"
                v-model="restaurantCategory"
              >
                <option selected>按我選擇</option>
                <option value="中式">中式</option>
                <option value="義式">義式</option>
                <option value="韓式">韓式</option>
                <option value="日式">日式</option>
                <option value="美式">美式</option>
                <option value="印度">印度</option>
                <option value="簡餐">簡餐</option>
                <option value="麵食">麵食</option>
                <option value="自助餐">自助餐</option>
              </select>
            </div>
            <!-- 素食種類開始 -->
            <div class="mb-4">
              <label class="form-label" for="example-select"
                >選擇素食種類</label
              >
              <select
                class="form-select"
                id="example-select"
                name="example-select"
                v-model="restaurantType"
              >
                <option selected>按我選擇</option>
                <option value="全素">全素</option>
                <option value="蛋素">蛋素</option>
                <option value="奶素">奶素</option>
                <option value="蛋奶素">蛋奶素</option>
                <option value="五辛素">五辛素</option>
              </select>
            </div>
            <!-- 營業時間 -->
            <div class="mb-4">
              <label class="form-label" for="example-select">營業時間</label>
              <input
                type="textarea"
                id="val-restaurantBusinessHours"
                class="form-control"
                v-model="restaurantBusinessHours"
              />
            </div>
            <!-- 評分開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-restaurantScore">評分</label>
              <input
                type="text"
                id="val-restaurantScore"
                class="form-control"
                placeholder="0.0-5.0"
                v-model="restaurantScore"
              />
            </div>
            <!-- 圖片上傳開始-->
            <!-- <div class="row push">
              <div class="col-lg-8 col-xl-5 overflow-hidden">
                <div class="mb-4">
                  <label class="form-label" for="example-file-input"
                    >圖片上傳</label
                  >
                  <input
                    class="form-control"
                    type="file"
                    id="example-file-input"
                  />
                </div>
              </div>
            </div> -->

            <div class="row items-push">
              <div class="col-lg-7 offset-lg-4">
                <button type="submit" class="btn btn-alt-primary">送出</button>
              </div>
            </div>
          </div>
        </div>
      </BaseBlock>
    </form>
    <!-- END Basic -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      restaurantName: null,
      restaurantTel: null,
      restaurantAddress: null,
      restaurantCategory: null,
      restaurantType: null,
      restaurantBusinessHours: null,
      restaurantScore: null,
    };
  },
  methods: {
    createRestaurant() {
      const restaurant = {
        restaurantName: this.restaurantName,
        restaurantTel: this.restaurantTel,
        restaurantAddress: this.restaurantAddress,
        restaurantCategory: this.restaurantCategory,
        restaurantType: this.restaurantType,
        restaurantBusinessHours: this.restaurantBusinessHours,
        restaurantScore: this.restaurantScore,
      };
      axios.post("http://localhost:8088/restaurants", restaurant).then(() => {
        this.$router.replace("/backend/restaurants/restaurantInfo");
      });
    },
  },
};
</script>
