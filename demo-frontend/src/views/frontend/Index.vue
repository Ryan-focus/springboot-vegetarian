<script setup>
import { useTemplateStore } from "@/stores/template";

// Main store
const store = useTemplateStore();
</script>

<template>
  <div>
    <!-- Hero -->
    <div id="one-vue-hero" class="bg-body-extra-light">
      <BaseBackground
        image="https://pixabay.com/get/gc9b236f4127bbf809ac7ee12fbb2c55859f5e8b7d7d1cc27fa9a25d0dac1b92eba28c5d45a7947dacba955334d1d0741dfbc02cdc9c89c24e337aeb5c246688e_1920.jpg"
        inner-class="bg-white-25"
      >
        <div class="content content-full text-center">
          <div class="my-3"></div>
          <h1 class="h1 text-white mb-0"><b>想知道哪裡有素食 ?</b></h1>
          <span class="text-white-75"
            ><b>從超過 50 家精選餐廳中，探索您不知道的熱門素食。</b></span
          >
        </div>
        <form @submit.prevent>
          <div class="row">
            <div class="col-md-5 offset-md-3 content content-full text-center">
              <div class="mb-3">
                <div>
                  <input
                    type="search"
                    placeholder="美食分類、餐廳"
                    class="jsx-488536546 autocomplete-input"
                    value=""
                  />
                  <input
                    type="search"
                    placeholder="搜尋地點"
                    class="jsx-488536546 autocomplete-input"
                    value=""
                  />
                  <button class="btn btn-info" tabindex="0" type="button">
                    <i class="si si-magnifier"></i>
                  </button>
                </div>
                <p class="space-x-1">
                  <span>搜尋:</span>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">火鍋</span></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50"
                      >早午餐</span
                    ></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">屍體</span></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">厭世</span></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">地獄</span></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50"
                      >吃到死</span
                    ></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50"
                      >飽到吐</span
                    ></a
                  >
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">Gary</span></a
                  >
                </p>
              </div>
            </div>
          </div>
        </form>
        <div class="row">
          <div class="col-md-5 offset-md-4 justify-content-center in row">
            <div class="col-lg-4 mb-3 me-auto">
              <a href="">
                <img
                  src="https://ifoodie.tw/assets/images/app_store_badge.png"
                  alt=""
                  class="img-fluid"
              /></a>
            </div>
            <div class="col-lg-4 mb-3 me-auto">
              <a href=""
                ><img
                  src="https://play.google.com/intl/en_us/badges/images/apps/zh-tw-play-badge.png"
                  alt=""
                  class="img-fluid"
              /></a>
            </div>
          </div>
        </div>
      </BaseBackground>
    </div>
    <!-- END Hero -->

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
  </div>
</template>
