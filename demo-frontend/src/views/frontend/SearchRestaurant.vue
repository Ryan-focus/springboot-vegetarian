<script setup>
import { useTemplateStore } from "@/stores/template";
import { ref } from "vue";
import axios from "axios";

// Main store
const store = useTemplateStore();

//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = ref(
  {
    limit: 10,
    offset: 0,
    restaurantAddress: null,
    restaurantCategory: null,
    restaurantType: null,
    restaurantBusinessHours: null,
    restaurantScore: null,
    search: null
  }
);
//接收的資料ref
const resData = ref();
// const restaurantTotal = ref();
// const restaurantList = ref();

// const singleRestaurant = ref(
//   {
//     // ref會自己抓值，這邊還要另外宣告圖片是因為:src會去抓路徑，沒有定義會變undefined當掉
//     //其他的值ref抓到後會自己帶入變成json
//     imageUrl: null,
//   }

// )

// 取得所有餐廳
const getAxios = function () {
  axios
    .get(`http://${url}/restaurants`, { params: urlParams.value })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      console.log(res.data);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
// function getSingle(restaurantNumber) {
//   axios
//     .get(`http://${url}/restaurants/${restaurantNumber}`)
//     .then((res) => {
//       singleRestaurant.value = res.data
//       console.log(singleRestaurant)
//     })


// }
// 執行Axios;
getAxios();

</script>



<template>

  <!-- 搜尋bar -->
  <form @submit.prevent>
    <div class="row">
      <div class="col-md-5 offset-md-3 content content-full text-center">
        <div class="mb-3">
          <div>
            <input type="text" placeholder="顯示上一步輸入的文字" class="jsx-488536546 autocomplete-input" value="" />
            <!-- <a></a> -->
            <input type="text" placeholder="搜尋地點" class="jsx-488536546 autocomplete-input" value="" />
            <button class="btn btn-info" tabindex="0" type="button">
              <i class="si si-magnifier"></i>
            </button>
            <!-- <a></a> -->

            <!-- 下拉式選單 -->
            <select aria-label="Default select example">
              <option selected>推薦</option>
              <option value="1">熱門餐廳</option>
              <option value="2">評分最高</option>
              <option value="3">離你最近</option>
            </select>

            <!-- <a></a> -->

            <!-- checkbox -->
            <input type="checkbox" value="營業中" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
              營業中
            </label>
            <!-- <a></a> -->
            <input type="checkbox" value="全素" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
              全素
            </label>
            <!-- <a></a> -->
            <input type="checkbox" value="蛋奶素" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
              蛋奶素
            </label>
            <!-- <a></a> -->
            <input type="checkbox" value="五辛素" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
              五辛素
            </label>
            <!-- <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                  <label class="form-check-label" for="flexCheckChecked">
                    Checked checkbox
                  </label> -->
          </div>

          <p class="space-x-1">
            <span>更多分類：</span>
            <a href="/#/searchRestaurant?restaurantCategory=中式">
              <span class="badge rounded-pill bg-black-50">中式</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=義式">
              <span class="badge rounded-pill bg-black-50">義式</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=麵食">
              <span class="badge rounded-pill bg-black-50">麵食</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=印度">
              <span class="badge rounded-pill bg-black-50">印度</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=美式">
              <span class="badge rounded-pill bg-black-50">美式</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=日式">
              <span class="badge rounded-pill bg-black-50">日式</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=簡餐">
              <span class="badge rounded-pill bg-black-50">簡餐</span></a>
            <a href="/#/searchRestaurant?restaurantCategory=自助餐">
              <span class="badge rounded-pill bg-black-50">自助餐</span></a>
          </p>
        </div>
      </div>
    </div>
  </form>

  <hr />

  <!-- Page Content -->
  <div class="content content-boxed">
    <div class="row col-md-12" v-for="item in resData" :key="item.restaurantNumber">
      <!-- Story -->
      <BaseBlock>
        <div class="row items-push">
          <div class="col-md-4 col-lg-5">
            <RouterLink :to="{}" class="img-link img-link-simple">
              <img class="img-fluid rounded" :src="`${item.imageUrl}`" alt="" />
            </RouterLink>
          </div>
          <div class="col-md-8 col-lg-7 d-md-flex align-items-center">
            <div>
              <h4 class="mb-1">
                <RouterLink :to="{ name: '' }" class="text-dark">{{ item.restaurantName }}</RouterLink>
              </h4>
              <div class="fs-sm fw-medium mb-3">
                <RouterLink :to="{ name: '' }">Megan Fuller</RouterLink>
                on July 16, 2021 · <span class="text-muted">10 min</span>
              </div>
              <p class="fs-sm text-muted">
                {{ item.restaurantBusinessHours }}
              </p>
            </div>
          </div>
        </div>
      </BaseBlock>
      <!-- END Story -->
    </div>

  </div>
  <!-- END Page Content -->

  <!-- Get Started -->
  <div class="bg-body-dark">
    <div class="content content-full">
      <div class="my-5 text-center">
        <h3 class="fw-bold mb-2">Do you like our stories?</h3>
        <h4 class="h5 fw-medium opacity-75">
          Sign up today and get access to over <strong>10,000</strong> inspiring
          stories!
        </h4>
        <a class="btn btn-primary px-4 py-2" href="javascript:void(0)">Get Started Today</a>
      </div>
    </div>
  </div>
  <!-- END Get Started -->

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