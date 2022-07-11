<script setup>
import { useTemplateStore } from "@/stores/template";

// Main store
const store = useTemplateStore();



</script>


<template>
  <div>
    <!-- Hero -->
    <div id="one-vue-hero" class="bg-body-extra-light">
      <BaseBackground image="/assets/media/vegan/index-bg.jpg" inner-class="bg-black-25">
        <div class="content content-full text-center">
          <div class="my-1"></div>
          <h1 class="h1 text-white mb-3"><b>想知道哪裡有素食 ?</b></h1>
          <h3>
            <span class="text-white-75"><b>從超過 50 家精選餐廳中，探索您不知道的熱門素食。</b></span>
          </h3>
        </div>
        <form @submit.prevent>
          <div class="row">
            <div class="col-md-5 offset-md-3 content content-full text-center">
              <div class="mb-2">
                <div>
                  <input type="search" placeholder="美食分類、餐廳" class="jsx-488536546 autocomplete-input" value="" />
                  <input type="search" placeholder="搜尋地點" class="jsx-488536546 autocomplete-input" value="" />
                  <button class="btn btn-info" tabindex="0" type="button">
                    <i class="si si-magnifier"></i>
                  </button>
                </div>
                <p class="space-x-1">
                  <span>搜尋:</span>
                  <a href="/#/searchRestaurant">
                    <span class="badge rounded-pill bg-black-50">中式</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">義式</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">麵食</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">印度</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">美式</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">日式</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">簡餐</span></a>
                  <a href="#">
                    <span class="badge rounded-pill bg-black-50">自助餐</span></a>
                </p>
              </div>
            </div>
          </div>
        </form>
        <div class="row">
          <div class="col-md-5 offset-md-4 justify-content-center in row">
            <div class="col-lg-4 mb-3 me-auto">
              <a href="">
                <img src="https://ifoodie.tw/assets/images/app_store_badge.png" alt="" class="img-fluid" /></a>
            </div>
            <div class="col-lg-4 mb-3 me-auto">
              <a href=""><img src="https://play.google.com/intl/en_us/badges/images/apps/zh-tw-play-badge.png" alt=""
                  class="img-fluid" /></a>
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
