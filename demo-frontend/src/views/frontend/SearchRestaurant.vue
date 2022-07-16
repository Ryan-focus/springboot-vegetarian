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
    searchName: null,
    searchAddress: null
  }
);
//接收的資料ref
// const resData = ref();
const restaurantTotal = ref();
const restaurantList = ref();
const singleRestaurant = ref();
// const resData2 = ref();

// 取得所有餐廳
const getAxios = function () {
  axios
    .get(`http://${url}/restaurants`, { params: urlParams.value })
    .then((res) => {
      //獲取伺服器的回傳資料
      // resData.value = res.data;
      restaurantTotal.value = res.data.total;
      restaurantList.value = res.data.results
      console.log(res.data);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};

//取得個別
function getSingle(restaurantNumber) {
  axios
    .get(`http://${url}/restaurants/${restaurantNumber}`)
    .then((res) => {
      singleRestaurant.value = res.data
      console.log(singleRestaurant)
    })


}

//取得條件(類別)
function getByCategory(restaurantCategory) {
  axios
    .get(`http://${url}/restaurantList?restaurantCategory=${restaurantCategory}`)
    .then((res) => {
      // resData.value = res.data.results
      // console.log(resData)
    })

}


// 執行Axios;
// getAxios();
</script>
<script>
export default {
  props: {
    resData: {
      type: String
    }
  },
  computed: {
    resDataParse() {
      return JSON.parse(this.resData);
    },
  },
};

</script>



<template>
  {{ resData }}
  <!-- 搜尋bar -->
  <form @submit.prevent>
    <div class="row">
      <div class="col-md-5 offset-md-3 content content-full text-center">
        <div class="mb-3">
          <div>
            <input type="text" placeholder="搜尋餐廳名稱" class="jsx-488536546 autocomplete-input" value="" />
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
            <input type="radio" value="營業中" id="flexCheckDefault" v-model="urlParams.restaurantBusinessHours"
              @change="getAxios()">
            <label class="form-check-label" for="flexCheckDefault">
              營業中
            </label>
            <!-- <a></a> -->
            <input type="radio" value="全素" id="flexCheckDefault" v-model="urlParams.restaurantType"
              @change="getAxios()">
            <label class="form-check-label" for="flexCheckDefault">
              全素
            </label>
            <!-- <a></a> -->
            <input type="radio" value="蛋奶素" id="flexCheckDefault" v-model="urlParams.restaurantType"
              @change="getAxios()">
            <label class="form-check-label" for="flexCheckDefault">
              蛋奶素
            </label>
            <!-- <a></a> -->
            <input type="radio" value="五辛素" id="flexCheckDefault" v-model="urlParams.restaurantType"
              @change="getAxios()">
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
            <a href="/#/searchRestaurant/details">
              <span class="badge rounded-pill bg-black-50">中式</span></a>
            <button class="badge rounded-pill bg-black-50" @click="getByCategory('義式')">義式</button>
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


          <!-- 這邊是下拉式選單 -->

          <div class="col-sm-2 col-xl-1">
            <div class="dropdown">
              <button type="button" class="btn btn-alt-info dropdown-toggle" id="dropdown-default-alt-info"
                data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <!-- 一開始會是null值顯示不出文字，用v-if判斷 -->
                <a v-if="urlParams.restaurantCategory == null">
                  餐廳分類
                </a>
                {{ urlParams.restaurantCategory }}
              </button>
              <div class="dropdown-menu fs-sm" aria-labelledby="dropdown-default-alt-info">

                <!-- 這邊開始是一格會打勾的radio -->
                <div class="form-check form-block">
                  <input type="radio" class="form-check-input" id="example-radio-block1" name="example-radio-block"
                    value="義式" v-model="urlParams.restaurantCategory" @change="getByCategory('義式')" />
                  <label class="form-check-label" for="example-radio-block1">
                    <span class="fs-4 fw-semibold">義式</span>
                  </label>
                </div>
                <!-- 這裡結束一格-->
                <div class="form-check form-block">
                  <input type="radio" class="form-check-input" id="example-radio-block2" name="example-radio-block"
                    value="麵食" v-model="urlParams.restaurantCategory" @change="getByCategory('麵食')" />
                  <label class="form-check-label" for="example-radio-block2">
                    <span class="fs-4 fw-semibold">麵食</span>
                  </label>
                </div>

              </div>
            </div>
          </div>





        </div>
      </div>
    </div>
  </form>
  <hr />

  <!-- Page Content -->
  <!-- 左邊 -->

  <div class="container">
    <div class="row">
      <div class="col">
        <div v-for="item in resData" :key="item.restaurantNumber">
          <BaseBlock>
            <div class="row items-push">
              <!-- 圖片 -->
              <div class="col-md-4 col-lg-5">
                <RouterLink :to="{}" class="img-link img-link-simple">
                  <img class="img-fluid rounded" :src="`${item.imageUrl}`" alt="" width="200" />
                </RouterLink>
              </div>
              <!-- 右邊的字 -->
              <div class="col-md-12 col-lg-7 d-md-flex align-items-center">
                <div>
                  <!-- 餐廳名稱 -->

                  <h3 class="text-dark">{{ item.restaurantName }} </h3>

                  <!-- 評分 -->
                  <h4 style="color:#3498DB">{{ item.restaurantScore }} ★</h4>

                  <!-- 營業時間 -->
                  <p style="color: grey;size: 1cm;">
                    {{ item.restaurantBusinessHours }}
                  </p>
                  <!-- 收藏 -->
                  <button type="button" class="btn btn-outline-primary">收藏</button>
                  <!-- 詳細 -->
                  <button type="button" class="btn btn-outline-primary"
                    onclick="location.href='/#/searchRestaurant/details'">詳細</button>
                </div>
              </div>
            </div>

          </BaseBlock>
        </div>


      </div>
      <!-- 右邊 google map  -->
      <div class="col">
        <iframe
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3616.437657692995!2d121.21998631423737!3d24.985240346399994!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x34682183e7b783c3%3A0xf0ebfba2069b6158!2z6IGW5b635Z-6552j5a246Zmi!5e0!3m2!1szh-TW!2stw!4v1657885211036!5m2!1szh-TW!2stw"
          width="600" height="600" style="border:0;" allowfullscreen="true" loading="lazy"
          referrerpolicy="no-referrer-when-downgrade"></iframe>
      </div>
    </div>
  </div>

  <!-- END Page Content -->


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