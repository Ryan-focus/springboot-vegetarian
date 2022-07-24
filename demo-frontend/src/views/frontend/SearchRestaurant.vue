<script setup>
import { useTemplateStore } from "@/stores/template";
import { ref } from "vue";
import { useRoute, useRouter } from 'vue-router';
import axios from "axios";
import { GoogleMap, Marker, MarkerCluster } from 'vue3-google-map'
import { useLoading } from 'vue3-loading-overlay';

const loader = useLoading({
  loader: 'dots',
  color: '#CCDBE2'
});
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

const restaurantCategory = route.params.restaurantCategory;
const dataArray = ref();


/**LatLng(parseFloat(trader.geo.lat),parseFloat(trader.geo.lon) */
//Google map
const locations = ref([]);

const center = { lat: parseFloat(null), lng: parseFloat(null) };
const location = { lat: parseFloat(null), lng: parseFloat(null) };

var centerLat, centerLng;
//取得使用者初始位置 ， 會詢問使用者願不願意提供位置
function initMap() {
  navigator.geolocation.watchPosition((position) => {
    centerLat = position.coords.latitude;
    centerLng = position.coords.longitude;
    // 初始化地圖
    //預設是聖德基督學院
    if (centerLat == null) {
      centerLat = 24.9852355;
    }
    if (centerLng == null) {
      centerLng = 121.2199863;
    }
    center.lat = centerLat;
    center.lng = centerLng;
  });
}
initMap();

locations.value.push(center);

function calllatlng(address) {
  axios
    .get(`https://maps.googleapis.com/maps/api/geocode/json?address=${address}&key=AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0`)
    .then((res) => {
      JSON.parse(JSON.stringify(res))
      // console.log(res.data);
      if (res.status === 200) {
        location.lat = res.data.results[0].geometry.location.lat;
        location.lng = res.data.results[0].geometry.location.lng;
        locations.value.push(location);

      }
    })
    .catch((err) => {
      console.log(err);
    });
}


// 取得條件(類別)
const searchCatagory = function (catagory) {
  loader.show();
  if (catagory != null) {
    axios
      .get(`http://${url}/restaurantList?restaurantCategory=` + catagory)
      .then((res) => {

        console.log(res.data);
        dataArray.value = res.data.results;
        locations.value = [];
        for (let i = 0; i <= res.data.results.length - 1; i++) {
          calllatlng(res.data.results[i].restaurantAddress);
        }

        setTimeout(() => {
          loader.hide();
        }, 300)
      })
      .catch((err) => {
        setTimeout(() => {
          loader.hide();
        }, 300)
        console.log(err);
      });
  } else {
    axios
      .get(`http://${url}/restaurantList?restaurantCategory=${restaurantCategory}`)
      .then((res) => {
        loader.hide;
        dataArray.value = res.data.results;
        for (let i = 0; i <= res.data.results.length - 1; i++) {
          calllatlng(res.data.results[i].restaurantAddress);
        }
        setTimeout(() => {
          loader.hide();
        }, 300)
      })
      .catch((err) => {
        setTimeout(() => {
          loader.hide();
        }, 300)
        console.log(err);

      }
      );
  }
}
if (restaurantCategory != null) {
  searchCatagory(restaurantCategory);
}


// 取得條件(素食種類)
const searchType = function (type) {

  axios
    .get(`http://${url}/restaurantList?restaurantType=` + type)
    .then((res) => {
      // dataArray.value = res.data.results;
      dataArray.value = res.data.results;
      locations.value = [];
      for (let i = 0; i <= res.data.results.length - 1; i++) {
        calllatlng(res.data.results[i].restaurantAddress);
      }

    })
    .catch((err) => {

      console.log(err);
    });
}


// 取得條件(地址)
const searchAddress = function () {

  axios
    .get(`http://${url}/restaurantList`, { params: urlParams.value })
    .then((res) => {
      dataArray.value = res.data.results;
      locations.value = [];
      for (let i = 0; i <= res.data.results.length - 1; i++) {
        calllatlng(res.data.results[i].restaurantAddress);
      }

    })
    .catch((err) => {
      console.log(err, "失敗");

    });
}

searchAddress();

// 取得條件(餐廳名稱)
const searchName = function () {

  axios
    .get(`http://${url}/restaurantList`, { params: urlParams.value })
    .then((res) => {
      dataArray.value = res.data.results;
      locations.value = [];
      for (let i = 0; i <= res.data.results.length - 1; i++) {
        calllatlng(res.data.results[i].restaurantAddress);
      }

    })
    .catch((err) => {
      console.log(err, "失敗");

    });
}




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
          businessRestuarantID.push(res.data[i].restaurantNumber);
          businessID.push(res.data[i].businessId);
        }
      }

    })
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
          <!-- <select aria-label="Default select example">
            <option selected>推薦</option>
            <option value="1">熱門餐廳</option>
            <option value="2">評分最高</option>
            <option value="3" @click="getlocation()">離你最近</option>
          </select> -->

          <!-- <a></a> -->

          <!-- checkbox -->
          <!-- <input type="checkbox" value="營業中" id="flexCheckDefault-1" v-model="urlParams.restaurantBusinessHours"
            @change="searchCatagory()">
          <label class="form-check-label me-2" for="flexCheckDefault-1">
            營業中
          </label> -->
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
                  <!-- <p style="color: grey;size: 1cm;">
                    {{ item.restaurantBusinessHours }}
                  </p> -->

                  <div v-html="item.restaurantBusinessHours" display="inline"></div>

                  <!-- 收藏 -->
                  <!-- <button type="button" class="btn btn-outline-primary me-3">收藏</button> -->
                  <!-- 詳細 -->
                  <button type="button" class="btn btn-outline-primary me-3" style="margin-top:0.5cm"
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
            <GoogleMap api-key="AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0" style="width: 100%; height: 500px"
              :center="center" :zoom="10">
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
        <div class="col-sm-6 order-sm-2 py-1 text-center text-sm-end"> 本網站僅作為 <i class="fa fa-heart text-danger"></i>
          <a class="fw-semibold" href="https://www.ispan.com.tw/" target="_blank">資展國際</a>專題使用
        </div>
        <div class="col-sm-6 order-sm-1 py-1 text-center text-sm-start"><a class="fw-semibold"
            href="https://github.com/Ryan-focus/springboot-vegetarian"> EEIT45 - 跨域JAVA班 - 第一組 </a> © {{
                store.app.copyright
            }}</div>
      </div>
    </div>
  </footer>
  <!-- END Footer -->
</template>
