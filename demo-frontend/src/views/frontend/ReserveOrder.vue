<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { useTemplateStore } from "@/stores/template";
// eslint-disable-next-line no-unused-vars
import { reactive, ref, onMounted } from "vue";

import { useLoading } from "vue3-loading-overlay";
//using Axios 
import axios from "axios";
// Vue Star Rating, for more info and examples you can check out https://github.com/craigh411/vue-StarRating
import StarRating from "vue-star-rating";
// useRoute 接值 ，做查詢 
import { useRoute } from "vue-router";
// Calendar
import Datepicker from '@vuepic/vue-datepicker';
import router from "../../router";
//接值
const loader = useLoading({
  loader: 'dots',
  color: '#CCDBE2'
});
const route = useRoute();
const restaurantNumber = route.params.restaurantId;
const businessId = route.params.businessId;
console.log("businessId=" + businessId);
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
  loader.show();
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
      loader.hide();
    });
}
reserveRestaurant();
const store = useTemplateStore();

//date 
const date = ref(new Date());
const startDate = ref(new Date());
const maxDate = ref(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 30));


//v-bind value 
const adult = ref(2);
const kid = ref(0);

const format = (d) => {
  const day = d.getDate();
  const month = d.getMonth() + 1;
  const days = d.getDay();
  const daysArray = ['週日', '週一', '週二', '週三', '週四', '週五', '週六'];

  return `${month}月${day}日 ${daysArray[days]}`;
}

// const dataFormat = (dataDate) => {
//   const day = dataDate.getDate();
//   const month = dataDate.getMonth() + 1;
//   const year = dataDate.getFullYear();

//   return `${year}-${month}-${day}`;
// }

//reserve 要儲存的資訊有5 restaurantNumber businessId人數 Adult 、 Child、 Date
const data = (sessionStorage.getItem('reserve')) ? JSON.parse(sessionStorage.getItem('reserve')) : {
  reserveItemList: []
};

function sendData() {
  sessionStorage.removeItem("reserve");
  data.reserveItemList.push(restaurantNumber);
  data.reserveItemList.push(businessId);
  data.reserveItemList.push(adult.value);
  data.reserveItemList.push(kid.value);
  data.reserveItemList.push(date.value);
  console.log("傳送到下一個頁面所儲存的資料有:" + data.reserveItemList);
  sessionStorage.setItem('reserve', JSON.stringify(data));
  router.push({
    name: "restaurant-reserve-check"
  });
}


</script>

<style lang="scss">
@import '@vuepic/vue-datepicker/src/VueDatePicker/style/main.scss';

.dp-custom-calendar {
  .dp__calendar_item {
    border: 1px solid var(--dp-border-color-hover);
  }
}
</style>

<template >
  <div class="row justify-content-center">
    <div class="col-md-12 p-4 mt-1 mx-auto" style="max-width:1140px; ">
      <!-- With Indicators -->
      <BaseBlock class="mb-1" content-full>
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
              aria-current="true" aria-label="Slide 1"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img :src="`${imageUrl}`" class=" d-block w-100 img-fluid" :alt="`${restaurantName}`" />
            </div>
          </div>
          <button class=" carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </button>
        </div>
      </BaseBlock>
    </div>
    <!-- END With Indicators -->
  </div>

  <!-- Page Content -->
  <div class="bg-body-extra-light">
    <div class="content content-boxed">
      <div class="text-left fs-sm push">
        <h1 class="mt-1 mb-0 p-0 fw-bold">{{ restaurantName }}</h1>
        <StarRating class="font-size-sm text-muted mb-2" v-model:rating="restaurantScore" :star-size="24"
          text-class="font-size-sm text-muted" :show-rating="false" active-color="#66CC00">
        </StarRating>

        <p class="font-size-sm text-muted mb-2">
          <span class="badge bg-info me-3">{{ restaurantCategory }}</span>
          <span class="badge bg-success me-3 mb-2">{{ restaurantType }}</span>
        </p>
        <h4 class="mt-3 fw-normal">{{ restaurantAddress }}</h4>
        <span>
          <i class="fa fa-phone me-3 h5 text-secondary board-black" /><a :href="`tel:${restaurantTel}`" target="_blank"
            class="text-warning h5 fw-normal me-4 link-fx">{{ restaurantTel }}</a>
        </span>
        <span>
          <i class="fa fa-map me-3 h5 text-secondary"></i> <a class="text-warning h5 fw-normal me-4 link-fx"
            target="_blank">查看地圖</a>
        </span>
      </div>

      <hr>

      <div class="row justify-content-left">
        <div class="col-sm-8">
          <!-- Story -->

          <div class="story" v-html="restaurantBusinessHours"></div>
          <!-- END Story -->

        </div>
      </div>

      <hr>

      <!-- 預訂餐廳主要功能區域-->
      <div id="book-now" class="row ">
        <div id="book-now-selector" class="row mb-5">
          <div id="dinner-field" class="row col-6">
            <p class="mt-1 fw-normal h4">用餐人數</p>
            <div class="col-6">
              <select class="selectpicker form-select" v-model="adult">
                <option disabled>請選擇用餐人數</option>
                <option value="1" data-testid="1位大人">1位大人</option>
                <option value="2" data-testid="2位大人" selected>2位大人</option>
                <option value="3" data-testid="3位大人">3位大人</option>
                <option value="4" data-testid="4位大人">4位大人</option>
                <option value="5" data-testid="5位大人">5位大人</option>
                <option value="6" data-testid="6位大人">6位大人</option>
                <option value="7" data-testid="7位大人">7位大人</option>
                <option value="8" data-testid="8位大人">8位大人</option>
                <option value="9" data-testid="9位大人">9位大人</option>
              </select>
            </div>
            <div class="col-6">
              <select class="selectpicker form-select" id="kid-picker" v-model="kid">
                <option value="0" data-testid="0位小孩">0位小孩</option>
                <option value="1" data-testid="1位小孩">1位小孩</option>
                <option value="2" data-testid="2位小孩">2位小孩</option>
                <option value="3" data-testid="3位小孩">3位小孩</option>
                <option value="4" data-testid="4位小孩">4位小孩</option>
                <option value="5" data-testid="5位小孩">5位小孩</option>
                <option value="6" data-testid="6位小孩">6位小孩</option>
                <option value="7" data-testid="7位小孩">7位小孩</option>
                <option value="8" data-testid="8位小孩">8位小孩</option>
                <option value="9" data-testid="9位小孩">9位小孩</option>
              </select>
            </div>
          </div>
          <div id="date-field" class="col-6">
            <p class="mt-1 fw-normal h4">用餐日期</p>
            <Datepicker id="date-picker" v-model="date" :minDate="new Date()" :enableTimePicker="false"
              :startDate="startDate" :format="format" :maxDate="maxDate" autoApply :closeOnAutoApply="false"
              hideOffsetDates position="left" :monthChangeOnArrows="false" calendarClassName="dp-custom-calendar" />
          </div>
        </div>
        <hr>

        <article>
          <p class="me-2 h6">如有訂位以外的需求，請撥打電話與我們聯繫
            <a :href="`tel:${restaurantTel}`">{{
                restaurantTel
            }}</a>
          </p>
        </article>
        <!-- <Datepicker aria-hidden="true" data-bs-target="#dateCalendar" :v-model="date" :ref="datepicker" hidden /> -->
      </div>
      <!-- 預訂餐廳主要功能區域結束-->


      <!-- 預訂餐廳fixed Bar 位置-->
      <div id="book-now-action-bar" class="row" style="
          background-color: rgb(255, 255, 255);
          box-shadow: rgba(145, 145, 145, 0.5) 0px 2px 12px 0px;
          position: fixed;
          bottom: 0px;
          left: 0px;
          right: 0px;
        ">
        <!-- 置底fixed Bar 顯示 Span -->
        <div aria-hidden="true" class="row justify-content-center mt-2" style="
              white-space: nowrap;
              overflow: auto hidden;
              display: flex;
              margin: 0px auto;
              width: 100%;
              height: 56px;
              -webkit-box-align: center;
              align-items: center;
          ">
          <span class="col-3 mt-1 mb-0 p-0 fw-bold h5">您已選擇預訂 {{ restaurantName }} : </span>
          <button class="btn btn-secondary col-1" disabled>{{ adult }} 大 , {{ kid }} 小</button>
          <!-- 箭頭符號 -->
          <div class="col-1">
            <div aria-hidden="true" class=""> <i class="si si-arrow-right "></i></div>
          </div>
          <button class="btn btn-secondary col-1" disabled>{{ format(date) }}</button>
          <hr>
        </div>
        <div class="row justify-content-center" style="max-width:1140px ;  min-height: 50px;">
          <button @click.prevent="sendData()" id="book-now-action-button"
            class="col-md-3 offset-md-5 justify-content-center me-5" style="
                    background-color: rgb(255, 133, 14);
                    border: none;
                    border-radius: 6px;
                    color: rgb(255, 255, 255);
                    text-align: center;
                    width: 100%;
                    padding-top: 0px;
                    padding-bottom: 0px;
                    outline: 0px;
                    cursor: pointer;
                ">
            <span>下一步，填寫聯絡資訊</span>
          </button>

        </div>
      </div>
      <!-- 預訂餐廳fixed置底Bar Layout結束-->

      <!-- 餐廳資訊 / 地圖 位置 電話-->
      <div id="branch-info" class="row ">
        <header display="flex" class="row col-12 mb-2 mt-2">
          <h4 class="mt-3 fw-bold h3">餐廳資訊</h4>
        </header>
        <div class="row">
          <div id="info-map" class="col-md-6 col-xl-3 me-2"> <iframe
              :src="`https://www.google.com/maps/embed/v1/place?key=AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0&q=${restaurantName}&language=zh-TW`"
              width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"></iframe>
          </div>
          <div id="info-body" class="col-md-5 offset-md-3 mt-3">
            <!-- 位置 開始-->
            <div class="mb-2 row col-12">
              <span class="col-6">
                <i class="fa fa-map me-2 h5 text-black"></i>
                <span class="text-black h5 fw-normal me-4 link-fx">位置</span>
              </span>

              <a class="fw-normal link-fx text-black h4 " target="_blank"
                :href="`https://www.google.com.tw/maps/place/${restaurantAddress}`">{{
                    restaurantAddress
                }}</a>
              <hr>
            </div>
            <!-- 位置結束 -->
            <!-- 連絡電話開始 -->
            <div class="mb-2 row col-12">
              <span class="col-6">
                <i class="fa fa-phone me-3 h5 text-secondary board-black"></i>
                <span class="text-black h5 fw-normal me-4 link-fx">連絡電話</span>
              </span>

              <a :href="`tel:${restaurantTel}`" target="_blank" class="fw-normal link-fx text-black h4 ">{{
                  restaurantTel
              }}</a>
              <hr>
            </div>

            <!-- 連絡電話結束 -->
            <!-- 素食類型 -->
            <div class="mb-2 row col-12">
              <span class="col-6">
                <i class="fab fa-pagelines me-2 h5 text-black"></i>
                <span class="text-black h5 fw-normal me-4 link-fx">餐廳類型</span>
              </span>

              <span class="fw-normal link-fx text-black h4 " target="_blank">
                <span class="badge bg-info me-3">{{
                    restaurantCategory
                }}</span>
                <span class="badge bg-success me-3 mb-2">{{ restaurantType }}</span></span>
              <hr>
            </div>
            <!-- 素食類型結束 -->
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- END Page Content -->

  <!-- Hero After -->
  <div id="one-vue-hero-after" class="bg-body-light">
    <div class="content content-full"></div>
  </div>
  <!-- END Hero After -->

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
