<script setup>
import { reactive } from "vue";

// vue-easy-lightbox, for more info and examples you can check out https://onycat.com/vue-easy-lightbox/
import VueEasyLightbox from "vue-easy-lightbox";

// Reactive gallery state
const gallery = reactive({
  visible: false,
  index: 0,
  photos: [
    "/assets/media/photos/photo19@2x.jpg",
    "/assets/media/photos/photo12@2x.jpg",
    "/assets/media/photos/photo18@2x.jpg",
    "/assets/media/photos/photo16@2x.jpg",
    "/assets/media/photos/photo14@2x.jpg",
  ],
});

// Helper function to hide the lightbox
function handleHide() {
  gallery.visible = false;
}
</script>

<template>
  <VueEasyLightbox :visible="gallery.visible" :index="gallery.index" :imgs="gallery.photos" @hide="handleHide" />
  <div class="row justify-content-center">

    <div class="col-md-12 p-4 mt-1 mx-auto" style="max-width:1140px;">
      <!-- With Indicators -->
      <BaseBlock class="mb-1" content-full>
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
              aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
              aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
              aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"
              aria-label="Slide 4"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4"
              aria-label="Slide 5"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                src="https://inline.imgix.net/branch/-Lp_rdeeFue9DT5RqSlS:inline-live-1--Lp_rdihy1BAcnA19fes-a658eb13-81e8-4201-820b-f4bda42cd884_20220621SOUTHEAST-1140X456.jpg"
                class="d-block w-100 img-fluid" alt="Carousel Image 1" />
            </div>
            <div class="carousel-item">
              <img
                src="https://inline.imgix.net/branch/-Lp_rdeeFue9DT5RqSlS:inline-live-1--Lp_rdihy1BAcnA19fes-7f849d12-bc64-42a5-b65f-6f635e6635f0_20220707-%E9%98%B2%E8%A9%90%E9%A8%99-1140X456_INLINE.jpg"
                class="d-block w-100 img-fluid" alt="Carousel Image 1" />
            </div>
            <div class="carousel-item">
              <img
                src="https://inline.imgix.net/branch/-Lp_rdeeFue9DT5RqSlS:inline-live-1--Lp_rdihy1BAcnA19fes-298931b0-bdf8-4a6d-8bdf-7eefa02925f7_%E9%98%B2%E7%96%AB%E8%B3%87%E8%A8%8A_inline_1140X456-1.jpg"
                class="d-block w-100 img-fluid" alt="Carousel Image 1" />
            </div>
            <div class="carousel-item">
              <img
                src="https://inline.imgix.net/branch/-Lp_rdeeFue9DT5RqSlS:inline-live-1--Lp_rdihy1BAcnA19fes-9c6a7ed0-8433-41a9-a041-7b7caa2cc416_inline-banner-new.jpg"
                class="d-block w-100 img-fluid" alt="Carousel Image 1" />
            </div>
            <div class="carousel-item">
              <img
                src="https://inline.imgix.net/branch/-Lp_rdeeFue9DT5RqSlS:inline-live-1--Lp_rdihy1BAcnA19fes-86d1277f-38d9-467b-8955-f113d24334d3_DSC_4995.jpg"
                class="d-block w-100 img-fluid" alt="Carousel Image 1" style="width:1140px; height:456px" />
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
        <h1 class="mt-1 mb-0 p-0 fw-bold">典華豐FOOD海陸百匯</h1>
        <h4 class="mt-3 fw-normal">台北市中山區植福路8號1樓</h4>
        <span>
          <i class="fa fa-phone me-3 h5 text-secondary board-black" /><a href="tel:02-8502-05555" target="_blank"
            class="text-warning h5 fw-normal me-4 link-fx">02-8502-05555</a>
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
            <p>
              <a class="text-warning h5 fw-normal me-4 link-fx"
                href="https://sites.google.com/denwell.com/denfood/">豐FOOD海陸百匯全面落實防疫</a> <br>
              💖 我們用心防疫，您們安心用餐
            </p>
            <p>
              營業時間: <br>
              ⏲午餐用餐時間 11:30am 到 2:00pm <br>
              平日(每位) NT$999+10% <br>
              假日(每位) NT$1,199+10%
            </p>
            <p>⏲下午餐用餐時間 2:30pm 到 4:30pm <br>
              平日(每位) NT$699+10% <br>
              假日(每位) NT$799+10%</p>

            <p>⏲晚餐用餐時間 5:30pm 到 9:00pm(供餐到8:30) <br>
              平日(每位) NT$1,099+10% <br>
              假日(每位) NT$1,199+10%</p>

            <p>●孩童: <br>
              100~140CM每位半價+10% <br>
              80~100CM每位NT$150+10%。 <br>
              ●訂位人數請包含孩童人數，以符合用餐舒適度。 <br>
              ●座位的安排，依當日現場調配為主。 <br>
              ●指定桌號恕無法提供線上訂位，請洽餐廳服務人員訂位。 <br>
              ●特殊節慶假日無法提供線上訂位，請洽餐廳訂位。 <br>
              ●同一團體分開不同人名線上訂位，恕無法提供併桌需求。 <br>
              ●若使用餐券，使用規則以及餐價請依券上說明使用。 <br>
              ＊連續假期假日收費如下:2/28(一)【二二八連假】、4/4(一)~4/5(二)【清明連假】 <br>
              、4/30(六)~5/2(一)【勞動節連假】、6/3(五)~6/5(日)【端午連假】、 <br>
              9/9(五)~9/11(日)【中秋連假】 <br>
              10/8(六)~10/10(一)【國慶連假】
            </p>
            <p>
              ✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★ <br>
              豐FOOD七月壽星優惠😁 <br>
              5、6、7月壽星皆享優惠 <br>
              ✨兩人同行(含壽星)→價低者享8折 <br>
              ✨四人同行(含壽星)→１人享免費(以價低者為折扣優惠)
            </p>
            <p>※注意事項 <br>
              1.壽星優惠適用於生日當月平假日，惟官網公告之龍蝦之夜特殊價格之餐期不適用。</p>
            <ol start="2">
              <li>當月壽星本人不限小孩或成人及全餐期皆享有優惠，10%服務費以原價計算，請以現金或刷卡結帳，不可使用餐券，亦不可與其他優惠併用。 <br>
                3.含壽星在內的2人、4人同行，同行友人不限成人或孩童 <br>
                4.如使用4人同行優惠，以價低者為享折扣優惠者，有2位壽星，同行人數需達到8位，第四人皆可免費，依此類推。 <br>
                5.2人及4人同行優惠不可與餐券合併使用、超過同行優惠的人數才可使用餐券(例如第3、5、6、7人)。 <br>
                6.請於用餐前先訂位，並於入場時出示身分證件，依國曆生日為準，如未告知，結帳後恕不受理。 <br>
                7.豐FOOD保有優惠活動調整、解釋之權利。 <br>
                ✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★✧★</li>
            </ol>
          </article>
          <!-- END Story -->

        </div>
      </div>

      <hr>

      <!-- 預訂餐廳主要功能區域-->
      <div id="book-now">
        <div id="book-now-selectors">
          <select name="" id=""></select>
          <select name="" id=""></select>
        </div>
        <hr>
        <p>用餐時段</p>
        <p>*灰色表示該時間已客滿，可點選檢視其他可訂位日期</p>
        <div id="book-now-content"></div>
        <article>
          <p>如有訂位以外的需求，請撥打電話與我們聯繫 <br> <a href="tel:02-8502-0555">02-8502-0555</a></p>
        </article>
      </div>
      <!-- 預訂餐廳主要功能區域結束-->

      <!-- 預訂餐廳fixed Bar 位置-->
      <div id="book-now-action-bar">
        <div></div>
        <hr>
        <div>
          <button id="book-now-action-button">
            <div hidden>
              <div>
                <dir></dir>
              </div>
            </div>
            <span>下一步，填寫聯絡資訊</span>
          </button>
        </div>
      </div>
      <!-- 預訂餐廳fixed置底Bar Layout結束-->


      <!-- 餐廳資訊 / 地圖 位置 電話-->
      <div id="branch-info">
        <header>
          <h4>餐廳資訊</h4>
        </header>
        <div>
          <div id="info-map"> <iframe id="booking-google-embed-map" allowfullscreen src="" frameborder="0"></iframe>
          </div>
          <div id="info-body">
            <!-- 位置 開始-->
            <div>
              <div>
                <span>
                  <i class="fa fa-map me-2 h5 text-black" />
                  <a class="text-black h5 fw-normal me-4 link-fx">位置</a>
                </span>
              </div>
              <a class="fw-normal link-fx text-black h4" target="_blank"
                href="https://goo.gl/maps/qBo6y5XpfCyPVqk67">台北市中山區植福路8號1樓</a>
            </div>
          </div>
        </div>
      </div>


    </div>
  </div>
  <!-- END Page Content -->

  <!-- Get Started -->
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
  <!-- END Get Started -->
</template>
