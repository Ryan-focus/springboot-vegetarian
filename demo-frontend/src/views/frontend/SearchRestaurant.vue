<script setup>
import { useTemplateStore } from "@/stores/template";

// Main store
const store = useTemplateStore();



</script>



<template>
  <div>
    <!-- Hero -->
    <div id="one-vue-hero" class="bg-body-extra-light">
      <BaseBackground>

        <!-- 搜尋bar -->
        <form @submit.prevent>
          <div class="row">
            <div class="col-md-5 offset-md-3 content content-full text-center">
              <div class="mb-3">
                <div>

                  <input type="search" placeholder="顯示上一步輸入的文字" class="jsx-488536546 autocomplete-input" value="" />
                  <a>　</a>
                  <input type="search" placeholder="搜尋地點" class="jsx-488536546 autocomplete-input" value="" />
                  <button class="btn btn-info" tabindex="0" type="button">
                    <i class="si si-magnifier"></i>
                  </button>
                  <a>　</a>


                  <select aria-label="Default select example">
                    <option selected>推薦</option>
                    <option value="1">熱門餐廳</option>
                    <option value="2">評分最高</option>
                    <option value="3">離你最近</option>
                  </select>

                  <a>　</a>

                  <input type="checkbox" value="" id="flexCheckDefault">
                  <label class="form-check-label" for="flexCheckDefault">
                    營業中
                  </label>
                  <a>　</a>
                  <input type="checkbox" value="全素" id="flexCheckDefault">
                  <label class="form-check-label" for="flexCheckDefault">
                    全素
                  </label>
                  <a>　</a>
                  <input type="checkbox" value="蛋奶素" id="flexCheckDefault">
                  <label class="form-check-label" for="flexCheckDefault">
                    蛋奶素
                  </label>
                  <a>　</a>
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

        <!-- 顯示搜尋結果 -->
        <div class="container">
          <div class="row">
            <div class="justify-content-center in row">
              顯示搜尋結果

              <div class="contact row g-0 py-3">
                <div class="col-6 bg-secondary animated slidein">
                  表格
                </div>

                <div class="col-6 animated slidein">
                  <iframe crossorigin="anonymous"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1807.109428940167!2d121.57541290794208!3d25.06057022569894!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442ab13a340b6b5%3A0x14c3f84217cb6ab0!2zSUtFQSDlrpzlrrblrrblsYUg5YWn5rmW5bqX!5e0!3m2!1szh-TW!2stw!4v1624536578867!5m2!1szh-TW!2stw&amp;output=embed"
                    width="100%" height="400" style="border: 0px;">
                  </iframe>
                </div>

              </div>


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
