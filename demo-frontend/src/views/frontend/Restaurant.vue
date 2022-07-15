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
        search: null
    }
);
//接收的資料ref
const resData = ref();
const restaurantTotal = ref();
const restaurantList = ref();
const singleRestaurant = ref(
    {
        // ref會自己抓值，這邊還要另外宣告圖片是因為:src會去抓路徑，沒有定義會變undefined當掉
        //其他的值ref抓到後會自己帶入變成json
        imageUrl: null,
    }

)

// 取得所有餐廳
const getAxios = function () {
    console.log(urlParams)
    axios
        .get(`http://${url}/restaurants`, { params: urlParams.value })
        .then((res) => {
            //獲取伺服器的回傳資料
            resData.value = res.data;
            restaurantTotal.value = res.data.total;
            restaurantList.value = res.data.results
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
};

function getSingle(restaurantNumber) {
    axios
        .get(`http://${url}/restaurants/${restaurantNumber}`)
        .then((res) => {
            singleRestaurant.value = res.data
            console.log(singleRestaurant)
        })


}
// 執行Axios;
getAxios();

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

                                    <input type="text" placeholder="顯示上一步輸入的文字" class="jsx-488536546 autocomplete-input"
                                        value="" />
                                    <a>　</a>
                                    <input type="text" placeholder="搜尋地點" class="jsx-488536546 autocomplete-input"
                                        value="" />
                                    <button class="btn btn-info" tabindex="0" type="button">
                                        <i class="si si-magnifier"></i>
                                    </button>
                                    <a>　</a>

                                    <!-- 下拉式選單 -->
                                    <select aria-label="Default select example">
                                        <option selected>推薦</option>
                                        <option value="1">熱門餐廳</option>
                                        <option value="2">評分最高</option>
                                        <option value="3">離你最近</option>
                                    </select>

                                    <a>　</a>

                                    <!-- checkbox -->
                                    <input type="checkbox" value="營業中" id="flexCheckDefault">
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

                            <!-- 卡片在此 -->
                            <div class="container py-5" data-v-5c6a0766="" v-for="item in restaurants"
                                :key="item.restaurantNumber">
                                <div class="row" data-v-5c6a0766="">


                                    <div class="col-md-9" data-v-5c6a0766="">
                                        <div class="row" data-v-5c6a0766="">
                                            <div class="col-lg-4 col-md-6 mb-3" data-v-5c6a0766="">
                                                <div class="card h-100 position-relative" role="button"
                                                    data-v-5c6a0766="">
                                                    <div class="card__Favorite" data-v-5c6a0766=""><i
                                                            class="bi bi-heart-fill" data-v-5c6a0766=""></i></div>
                                                    <!-- 餐廳圖片 -->
                                                    <div class="card__imgWrap" data-v-5c6a0766="">
                                                        <div class="card__img bg-cover" data-v-5c6a0766=""
                                                            style="height: 250px; background-image: url();">
                                                            {{ item.imageUrl }}
                                                        </div>
                                                    </div>

                                                    <!-- 卡片開始 -->
                                                    <div class="card-body" data-v-5c6a0766="">
                                                        <div class="d-flex justify-content-between" data-v-5c6a0766="">
                                                            <h5 class="card-title" data-v-5c6a0766="">
                                                                {{ item.restaurantName }}</h5>
                                                            <div data-v-5c6a0766="">
                                                                <div class="badge rounded-pill bg-secondary h5"
                                                                    data-v-5c6a0766="">
                                                                    {{ item.restaurantType }}</div>
                                                            </div>
                                                        </div>
                                                        <div class="d-flex justify-content-between" data-v-5c6a0766="">
                                                            <span class="text-primary h5 ms-auto" data-v-5c6a0766="">{{
                                                                item.restaurantScore
                                                                }}★</span>
                                                        </div>
                                                        <div class="d-flex justify-content-center" data-v-5c6a0766="">
                                                            <button class="btn btn-outline-primary me-3" type="button"
                                                                data-v-5c6a0766="">
                                                                <!----> 收藏
                                                            </button>
                                                            <button class="btn btn-outline-primary me-3" type="button"
                                                                data-v-5c6a0766="">
                                                                <!----> 詳細
                                                            </button>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- 換頁 -->
                                            <nav data-v-5c6a0766="">
                                                <ul class="pagination justify-content-center">
                                                    <li class="page-item disabled"><a class="page-link" href="#"
                                                            tabindex="-1" aria-disabled="true"><span
                                                                aria-hidden="true">«</span></a></li>
                                                    <li class="active page-item"><a class="page-link" href="#">1</a>
                                                    </li>
                                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                    <li class="page-item"><a class="page-link" href="#"><span
                                                                aria-hidden="true">»</span></a></li>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>
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