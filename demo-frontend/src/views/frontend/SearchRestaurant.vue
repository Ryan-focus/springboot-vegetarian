<script setup>
import { useTemplateStore } from "@/stores/template";
import { ref } from "vue";
import { useRouter } from 'vue-router';
import axios from "axios";
// Main store
const store = useTemplateStore();
//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = ref(
  {
    limit: 10,
    offset: 0,
    restaurantCategory: null,
    restaurantType: null,
    restaurantBusinessHours: null,
    restaurantScore: null,
    restaurantNumber: null
  }
);

//跳轉到詳細餐廳頁面
const router = useRouter({
  routes: [
    {
      path: "/searchRestaurant/details",
      name: "restaurant-details",
    }
  ]
});

//帶值跳轉
function restaurantDetail(prams) {
  urlParams.value.restaurantNumber = prams;
  axios
    .get(`http://${url}/restaurants/` + prams)
    .then((res) => {
      //獲取伺服器的回傳資料
      console.log(res.data);
      let dataArray = [res.data];
      console.log(dataArray);
      router.replace({
        name: "restaurant-details",
        params: {
          paramsData: JSON.stringify(dataArray)
        },
      });
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}


//取得條件(類別)
function searchCatagory(catagory) {
  urlParams.value.restaurantCategory = catagory;
  axios
    .get(`http://${url}/restaurantList`, { params: urlParams.value })
    .then((res) => {
      console.log(res.data.results);
      router.replace({
        name: "restaurantIndex",
        params: {
          paramsData: JSON.stringify(res.data.results)
        },
      });
    })
    .catch((err) => console.log(err));
}

var businessRestuarantID = [];

//取得驗證後商家數據，有登入在餐廳內的數據 
function getBusinessList() {
  axios.get(`http://${url}/business`)
    .then((res) => {
      for (let i = 0; i <= res.data.length - 1; i++) {
        if (res.data[i].restaurantNumber != 0) {
          businessRestuarantID.push(res.data[i].restaurantNumber);
        }
      }
    })
}

function reserveRestaurant(prams) {
  axios
    .get(`http://${url}/restaurants/${prams}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      console.log(res.data);
      sessionStorage.setItem("reserveOrder", JSON.stringify(res.data));
      router.replace({
        name: "restaurant-reserve"
      });
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

// 執行Axios;
// getAxios();
getBusinessList();
</script>
<script>
export default {
  name: "restaurantIndex",
  props: {
    paramsData: {
      type: String
    }
  },
};


</script>


<template>
  <!-- 搜尋bar -->
  <form @submit.prevent>
    <!-- <div class="row"> -->
    <div class=" row col-md-5 offset-md-3 content content-full text-center">
      <div class="mb-2">
        <div>
          <input type="text" placeholder="搜尋餐廳名稱" v-model="urlParams.searchName" @change="searchCatagory()" />
          <!-- <a></a> -->
          <input type="text" placeholder="搜尋地點" v-model="urlParams.searchAddress" @change="searchCatagory()" />
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
          <input type="radio" value="營業中" id="flexCheckDefault-1" v-model="urlParams.restaurantBusinessHours"
            @change="searchCatagory()">
          <label class="form-check-label me-2" for="flexCheckDefault-1">
            營業中
          </label>
          <!-- <a></a> -->
          <input type="radio" value="全素" id="flexCheckDefault-2" v-model="urlParams.restaurantType"
            @change="searchCatagory()">
          <label class="form-check-label me-2" for="flexCheckDefault-2">
            全素
          </label>
          <!-- <a></a> -->
          <input type="radio" value="全素_奶素_蛋素" id="flexCheckDefault-3" v-model="urlParams.restaurantType"
            @change="searchCatagory()">
          <label class="form-check-label me-2" for="flexCheckDefault-3">
            蛋奶素
          </label>
          <!-- <a></a> -->
          <input type="radio" value="全素_奶素_蛋素_含五辛" id="flexCheckDefault-4" v-model="urlParams.restaurantType"
            @change="searchCatagory()">
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
        <div v-for="item in JSON.parse(paramsData)" :key="item.restaurantNumber">
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
                  <button v-if="businessRestuarantID.indexOf(item.restaurantNumber) >= 0" type="button"
                    class="btn btn-outline-danger" @click.prevent="reserveRestaurant(item.restaurantNumber)"> <i
                      class="far fa-calendar-days me-2" />
                    前往訂位</button>
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
