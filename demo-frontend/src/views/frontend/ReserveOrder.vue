<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { useTemplateStore } from "@/stores/template";
// eslint-disable-next-line no-unused-vars
import { reactive, ref, computed, onMounted } from "vue";
// Vue Star Rating, for more info and examples you can check out https://github.com/craigh411/vue-StarRating
import StarRating from "vue-star-rating";
// Calendar
import Datepicker from '@vuepic/vue-datepicker';
const store = useTemplateStore();
const date = ref(new Date());
const startDate = ref(new Date());
const maxDate = ref(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate() + 30));

const format = (date) => {
  const day = date.getDate();
  const month = date.getMonth() + 1;
  const days = date.getDay();
  const daysArray = ['週日', '週一', '週二', '週三', '週四', '週五', '週六'];

  return `${month}月${day}日 ${daysArray[days]}`;
}

// const allowedDates = computed(() => {
//   return [
//     new Date(),
//     new Date().getMonth() + 1
//   ];
// });
//預設傳值伺服器與[params]
const reserveOrder = JSON.parse(window.sessionStorage.getItem("reserveOrder"));
// const businessID = business.data.business.businessId;
// const restaurantApply = JSON.parse(window.localStorage.getItem("restaurantApply" + businessID));
// const busineesUUID = business.data.business.uuid;
//接收的資料ref
//當日統計
//取得全部人數
//取得全部組數

// ref會自己抓值，這邊還要另外宣告圖片是因為:src會去抓路徑，沒有定義會變undefined當掉
//其他的值ref抓到後會自己帶入變成json

</script>

<style lang="scss">
@import '@vuepic/vue-datepicker/src/VueDatePicker/style/main.scss';

.dp-custom-calendar {
  .dp__calendar_item {
    border: 1px solid var(--dp-border-color-hover);
  }
}
</style>


<template>
  <div class="row justify-content-center">
    <div class="col-md-12 p-4 mt-1 mx-auto" style="max-width:1140px;  ">
      <!-- With Indicators -->
      <BaseBlock class="mb-1" content-full>
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
              aria-current="true" aria-label="Slide 1"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img :src="`${reserveOrder.imageUrl}`" class="d-block w-100 img-fluid"
                :alt="`${reserveOrder.restaurantName}`" />
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
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
        <h1 class="mt-1 mb-0 p-0 fw-bold">{{ reserveOrder.restaurantName }}</h1>
        <StarRating class="font-size-sm text-muted mb-2" v-model:rating="reserveOrder.restaurantScore" :star-size="24"
          text-class="font-size-sm text-muted" :show-rating="false" active-color="#66CC00">
        </StarRating>

        <p class="font-size-sm text-muted mb-2">
          <span class="badge bg-info me-3">{{ reserveOrder.restaurantCategory }}</span>
          <span class="badge bg-success me-3 mb-2">{{ reserveOrder.restaurantType }}</span>
        </p>
        <h4 class="mt-3 fw-normal">{{ reserveOrder.restaurantAddress }}</h4>
        <span>
          <i class="fa fa-phone me-3 h5 text-secondary board-black" /><a href="tel:02-8502-05555" target="_blank"
            class="text-warning h5 fw-normal me-4 link-fx">{{ reserveOrder.restaurantTel }}</a>
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
          <article class="story">
            {{ reserveOrder.restaurantBusinessHours }}
          </article>
          <!-- END Story -->

        </div>
      </div>

      <hr>

      <!-- 預訂餐廳主要功能區域-->
      <div id="book-now" class="row">
        <div id="book-now-selector" class="row">
          <div id="dinner-field" class="row col-6">
            <p class="mt-1 fw-normal h4">用餐人數</p>
            <div class="col-6">
              <select class="selectpicker form-select">
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
              <select class="selectpicker form-select">
                <option value="0" data-testid="0位小孩">0位小孩</option>
                <option value="1" data-testid="1位小孩">1位小孩</option>
                <option value="2" data-testid="2位小孩">2位小孩</option>
                <option value="3" data-testid="3位小孩">3位小孩</option>
                <option value="4" data-testid="4位小孩">4位小孩</option>
                <option value="5" data-testid="5位小孩">5位小孩</option>
                <option value="6" data-testid="6位小孩">6位小孩</option>
                <option value="7" data-testid="7位小孩">7位小孩</option>
              </select>
            </div>
          </div>
          <div id="date-field" class="col-6">
            <p class="mt-1 fw-normal h4">用餐日期</p>
            <Datepicker v-model="date" :minDate="new Date()" :enableTimePicker="false" :startDate="startDate"
              :format="format" :maxDate="maxDate" autoApply :closeOnAutoApply="false" hideOffsetDates position="left"
              :monthChangeOnArrows="false" calendarClassName="dp-custom-calendar" />
            <!-- <button type="button" class="form-select" id="dropdown-default-outline-primary"
              data-bs-target="#dateCalendar" aria-haspopup="true" aria-expanded="false"
              @click.prevent="yourCustomMethod">
              {{ date }}
            </button> -->
            <!-- <div class="dropdown-menu fs-sm" aria-labelledby="dropdown-default-outline-primary"></div> -->
            <!-- <div id="date-picker" class="form-select" data-cy="date-picker" aria-expanded="false">date</div> -->
          </div>
        </div>
        <hr>

        <article>
          <p>如有訂位以外的需求，請撥打電話與我們聯繫 <br>
            <a href="tel:{{`${reserveOrder.restaurantTel}`}}">{{
                reserveOrder.restaurantTel
            }}</a>
          </p>
        </article>
        <!-- <Datepicker aria-hidden="true" data-bs-target="#dateCalendar" :v-model="date" :ref="datepicker" hidden /> -->
      </div>
      <!-- 預訂餐廳主要功能區域結束-->


      <!-- 預訂餐廳fixed Bar 位置-->
      <div id="book-now-action-bar">
        <div></div>
        <hr>
        <div>
          <button id="book-now-action-button">
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
          <div id="info-map" class="col-7 me-3"> <iframe
              :src="`https://www.google.com/maps/embed/v1/place?key=AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0&q=${reserveOrder.restaurantName}&language=zh-TW`"
              width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"></iframe>
          </div>
          <div id="info-body" class="col-4">
            <!-- 位置 開始-->
            <div>
              <div>
                <span>
                  <i class="fa fa-map me-2 h5 text-black" />
                  <a class="text-black h5 fw-normal me-4 link-fx">位置</a>
                </span>
              </div>
              <a class="fw-normal link-fx text-black h4" target="_blank"
                :href="`https://www.google.com.tw/maps/place/${reserveOrder.restaurantAddress}`">{{
                    reserveOrder.restaurantAddress
                }}</a>
            </div>
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
