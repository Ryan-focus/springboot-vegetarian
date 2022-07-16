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
        searchName: null,
        searchAddress: null
    }
);
//接收的資料ref
const resData = ref();
const restaurantTotal = ref();
const restaurantList = ref();


// 取得所有餐廳
const getAxios = function () {
    axios
        .get(`http://${url}/restaurants`, { params: urlParams.value })
        .then((res) => {
            //獲取伺服器的回傳資料
            resData.value = res.data;
            restaurantTotal.value = res.data.total;
            restaurantList.value = res.data.results
            console.log(res.data);
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
};

//取得個別
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


<!-- 內容由此開始 -->
<template>


    <!-- 左邊 -->

    <div class="container">
        <div class="row">
            <div class="col">
                <div v-for="item in resData" :key="item.restaurantNumber">
                    <BaseBlock>
                        <div class="row items-push">

                            <!-- 右邊的字 -->
                            <div class="col-md-12 col-lg-7 d-md-flex align-items-center">

                            </div>
                        </div>

                        <!-- 上面的餐廳簡介 -->
                        <div class="container">
                            <div class="row mb-3 p-3">
                                <div class="col-md-6 mb-3">
                                    <div class="slide__main">
                                        <div class="col-md-4 col-lg-5">
                                            <RouterLink :to="{}" class="img-link img-link-simple">
                                                <img :src="`${item.imageUrl}`" alt="" width="400" height="400" />
                                            </RouterLink>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-3">

                                    <div class="">
                                        <h1 class="text-dark">{{ item.restaurantName }} </h1>
                                        <h3 style="color:#3498DB">{{ item.restaurantScore }} ★</h3>

                                        <p>電話：{{ item.restaurantTel }}</p>

                                        <div>
                                            <p>地址：{{ item.restaurantAddress }}</p>
                                        </div>
                                        <div>
                                            <p>餐廳類型：{{ item.restaurantCategory }}</p>
                                        </div>
                                        <div>
                                            <p>素食種類：{{ item.restaurantType }}</p>
                                        </div>
                                        <p>營業時間：{{ item.restaurantBusinessHours }}</p>
                                        <p style="color: grey;size: 1cm;" />

                                        <button type="button" class="btn btn-outline-primary">收藏</button>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />

                        <!-- 評論 -->
                        <div class="container" style="background-color:antiquewhite">
                            <section>
                                <h4 class="text-primary"> 評論 </h4>
                                <p>一份保證十顆

                                    紅酒一杯</p>
                            </section>

                        </div>
                        <hr />

                        <!-- 食記 -->
                        <div class="container" style="background-color:antiquewhite">
                            <section>
                                <h4 class="text-primary"> 食記 </h4>
                                <p>一份保證十顆

                                    紅酒一杯</p>
                            </section>

                        </div>
                        <hr />

                    </BaseBlock>
                </div>
            </div>
            <!-- 右邊 google map  -->

        </div>
    </div>


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