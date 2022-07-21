<script setup>
import { useTemplateStore } from "@/stores/template";
import { ref } from "vue";
import { useRoute, useRouter } from 'vue-router';
import axios from "axios";
import { GoogleMap, Marker, MarkerCluster } from 'vue3-google-map'


//預設傳值伺服器與[params]
const url = "localhost:8088";
// Main store
const store = useTemplateStore();
const router = useRouter();
//接值
const route = useRoute();

const urlParams = ref(
  {
    limit: 10,
    offset: 0,
    restaurantCategory: null,
    restaurantType: null,
    restaurantName: null,
    restaurantAddress: null,
    restaurantNumber: null,
  }
);

console.log(urlParams.value);

const restaurantCategory = route.params.restaurantCategory;
const dataArray = ref();

console.log(route.params);

// 取得條件(類別)
const searchCatagory = function (catagory) {
  if (catagory != null) {
    axios
      .get(`http://${url}/restaurantList?restaurantCategory=` + catagory)
      .then((res) => {
        dataArray.value = res.data.results;

      })
      .catch((err) => console.log(err));
  } else {
    axios
      .get(`http://${url}/restaurantList?restaurantCategory=${restaurantCategory}`)
      .then((res) => {
        dataArray.value = res.data.results;

      })
      .catch((err) => console.log(err));
  }
}
searchCatagory();

// 取得條件(素食種類)
const searchType = function (type) {

  axios
    .get(`http://${url}/restaurantList?restaurantType=` + type)
    .then((res) => {
      dataArray.value = res.data.results;

    })
    .catch((err) => console.log(err));
}
searchType();

// 取得條件(地址)
const searchAddress = function () {

  axios
    .get(`http://${url}/restaurantList`, { params: urlParams.value })
    .then((res) => {
      dataArray.value = res.data.results;

    })
    .catch((err) => console.log(err, "失敗"));
}

searchAddress();

// 取得條件(餐廳名稱)
const searchName = function () {

  axios
    .get(`http://${url}/restaurantList`, { params: urlParams.value })
    .then((res) => {
      console.log(res);
      console.log(res.data);
      console.log(res.data.results);

      dataArray.value = res.data.results;

    })
    .catch((err) => console.log(err, "失敗"));
}

searchName();


//Google map

const center = { lat: 24.9852355, lng: 121.2199863 }

const locations = [
  { lat: 24.9852355, lng: 121.2199863 },
  { lat: -31.56391, lng: 147.154312 },
  { lat: -33.718234, lng: 150.363181 },
  { lat: -33.727111, lng: 150.371124 },
  { lat: -33.848588, lng: 151.209834 },
  { lat: -33.851702, lng: 151.216968 },
  { lat: -34.671264, lng: 150.863657 },
  { lat: -35.304724, lng: 148.662905 },
  { lat: -36.817685, lng: 175.699196 },
  { lat: -36.828611, lng: 175.790222 },
  { lat: -37.75, lng: 145.116667 },
  { lat: -37.759859, lng: 145.128708 },
  { lat: -37.765015, lng: 145.133858 },
  { lat: -37.770104, lng: 145.143299 },
  { lat: -37.7737, lng: 145.145187 },
  { lat: -37.774785, lng: 145.137978 },
  { lat: -37.819616, lng: 144.968119 },
  { lat: -38.330766, lng: 144.695692 },
  { lat: -39.927193, lng: 175.053218 },
  { lat: -41.330162, lng: 174.865694 },
  { lat: -42.734358, lng: 147.439506 },
  { lat: -42.734358, lng: 147.501315 },
  { lat: -42.735258, lng: 147.438 },
  { lat: -43.999792, lng: 170.463352 },
]




//帶值restaurantNumber到detail頁
function restaurantDetail(restaurantNumber) {
  urlParams.value.restaurantNumber = restaurantNumber;

  router.push({
    name: "restaurant-details",
    params: {
      restaurantNumber: urlParams.value.restaurantNumber,
    }
  });
}
var businessRestuarantID = [];
var businessID = [];

//取得驗證後商家數據，有登入在餐廳內的數據 
function getBusinessList() {
  axios.get(`http://${url}/business`)
    .then((res) => {

      for (let i = 0; i <= res.data.length - 1; i++) {
        if (res.data[i].restaurantNumber != 0) {
          console.log(res.data[i]);
          businessRestuarantID.push(res.data[i].restaurantNumber);
          businessID.push(res.data[i].businessId);
        }
      }
    })
}

//google map Geolocation 
// 先確認使用者裝置能不能抓地點
function getlocation() {
  if (navigator.geolocation) {
    // 跟使用者拿所在位置的權限
    navigator.geolocation.getCurrentPosition(success, error);
  } else {
    alert('Sorry, 你的裝置不支援地理位置功能。')
  }
}
// 使用者不提供權限，或是發生其它錯誤
function error() {
  alert('無法取得你的位置');
}
// 使用者允許抓目前位置，回傳經緯度
function success(position) {
  console.log(position.coords.latitude, position.coords.longitude);
}


getBusinessList();
</script>

<template>
  <!-- 搜尋bar -->
  <form @submit.prevent>
    <!-- <div class="row"> -->
    <div class=" row col-md-5 offset-md-3 content content-full text-center">
      <div class="mb-2">
        <div>
          <input type="text" placeholder="搜尋餐廳名稱" v-model="urlParams.restaurantName" @keyup="searchName()" />
          <!-- <a></a> -->
          <input type="text" placeholder="搜尋地點" v-model="urlParams.restaurantAddress" @keyup="searchAddress()" />
          <button class="btn btn-info" tabindex="0" type="button">
            <i class="si si-magnifier"></i>
          </button>
          <!-- <a></a> -->

          <!-- 下拉式選單 -->
          <select aria-label="Default select example">
            <option selected>推薦</option>
            <option value="1">熱門餐廳</option>
            <option value="2">評分最高</option>
            <option value="3" @click="getlocation()">離你最近</option>
          </select>

          <!-- <a></a> -->

          <!-- checkbox -->
          <input type="checkbox" value="營業中" id="flexCheckDefault-1" v-model="urlParams.restaurantBusinessHours"
            @change="searchCatagory()">
          <label class="form-check-label me-2" for="flexCheckDefault-1">
            營業中
          </label>
          <!-- <a></a> -->
          <input type="radio" value="全素" id="flexCheckDefault-2" v-model="urlParams.restaurantType"
            @change="searchType('全素')">
          <label class="form-check-label me-2" for="flexCheckDefault-2">
            全素
          </label>
          <!-- <a></a> -->
          <input type="radio" value="全素_奶素_蛋素" id="flexCheckDefault-3" v-model="urlParams.restaurantType"
            @change="searchType('全素_奶素_蛋素')">
          <label class="form-check-label me-2" for="flexCheckDefault-3">
            蛋奶素
          </label>
          <!-- <a></a> -->
          <input type="radio" value="全素_奶素_蛋素_含五辛" id="flexCheckDefault-4" v-model="urlParams.restaurantType"
            @change="searchType('全素_奶素_蛋素_含五辛')">
          <label class="form-check-label me-2" for="flexCheckDefault-4">
            五辛素
          </label>
          <!-- <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                  <label class="form-check-label" for="flexCheckChecked">
                    Checked checkbox
                  </label> -->
        </div>
        <p class="space-x-3">
          <span>更多分類：</span>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('中式')"> 中式</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('義式')"> 義式</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('麵食')"> 麵食</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('印度')"> 印度</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('美式')"> 美式</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('日式')"> 日式</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('簡餐')"> 簡餐</button>
          <button class="badge rounded-pill bg-black-50" @click.prevent="searchCatagory('自助餐')"> 自助餐</button>
        </p>

      </div>
    </div>
    <!-- </div> -->
  </form>
  <hr />

  <!-- Page Content -->
  <!-- 左邊 -->

  <div class="container">
    <div class="row">
      <div class="col">
        <div v-for="item in dataArray" :key="item.restaurantNumber">
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
                  <div class="d-flex justify-content-between">
                    <h3 class="card-title text-dark">{{ item.restaurantName }}</h3>
                    <div>
                      <div class="badge rounded-pill bg-secondary h5">{{ item.restaurantCategory }}</div>
                    </div>
                  </div>

                  <!-- 評分 -->
                  <h4 style="color:#3498DB">{{ item.restaurantScore }} ★</h4>

                  <!-- 營業時間 -->
                  <p style="color: grey;size: 1cm;">
                    {{ item.restaurantBusinessHours }}
                  </p>

                  <!-- 收藏 -->
                  <!-- <button type="button" class="btn btn-outline-primary me-3">收藏</button> -->
                  <!-- 詳細 -->
                  <button type="button" class="btn btn-outline-primary me-3"
                    @click.prevent="restaurantDetail(item.restaurantNumber)">詳細</button>
                  <!-- 前往訂位-->
                  <a v-if="businessRestuarantID.indexOf(item.restaurantNumber) >= 0"
                    :href="`/#/searchRestaurant/reserve/${item.restaurantNumber}/${businessID[businessRestuarantID.indexOf(item.restaurantNumber)]}`"
                    type="button" class="btn btn-outline-danger"><i class="far fa-calendar-days me-2" />前往訂位</a>
                </div>
              </div>
            </div>
          </BaseBlock>
        </div>


      </div>
      <!-- 右邊 google map  -->
      <div class="col">
        <div>
          <!-- 地圖 -->
          <div id="info-map" class="">
            <!-- <iframe v-for="item in dataArray" :key="item.restaurantNumber"
              :src="`https://www.google.com/maps/embed/v1/place?key=AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0&q=${item.restaurantName}&language=zh-TW`"
              width="700" height="250" style="border:0;" allowfullscreen="" loading="lazy"
              referrerpolicy="no-referrer-when-downgrade">
            </iframe> -->
            <GoogleMap api-key="AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0" style="width: 100%; height: 500px"
              :center="center" :zoom="18">
              <MarkerCluster>
                <Marker v-for="(location, i) in locations" :options="{ position: location }" :key="i" />
              </MarkerCluster>
            </GoogleMap>

          </div>
        </div>
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
