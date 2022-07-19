<script setup>
import { useTemplateStore } from "@/stores/template";
import { ref, reactive } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";

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
const router = useRouter();
const route = useRoute();
const resData = ref();
const restaurantName = ref();
const restaurantNumber = route.params.restaurantNumber;
const restaurantTel = ref();
const restaurantAddress = ref();
const restaurantCategory = ref();
const restaurantType = ref();
const restaurantBusinessHours = ref();
const restaurantScore = ref();
const imageUrl = ref();
const saveData = ref();
const dataArray = ref();
const data = reactive({
    loading: false,
});

var uid = 0;
const user = JSON.parse(window.localStorage.getItem("access-user"));
if (user != null) {
    uid = user.data.user.userId;
}
// const itemData = JSON.parse(route.params.paramsData);
// var restaurantNumber = itemData[0].restaurantNumber;


//取得單筆餐廳by restaurantNumber
const getRestaurant = function () {
    data.loading = true;
    axios
        .get(`http://${url}/restaurants/${restaurantNumber}`)
        .then((res) => {
            console.log(res);
            console.log(res.data);
            let array = [];
            array.push(res.data);

            dataArray.value = array;

        })
        .catch((err) => console.log(err));
}

getRestaurant();


//確認用戶是否已收藏該筆餐廳資料
axios
    .get(`http://${url}/saveRestaurant/${restaurantNumber}/${uid}`)
    .then((res) => {
        saveData.value = res.data;
        console.log(res.data);

    })
    .catch((error) => {
        console.log(error, "失敗");
    });


// 取得所有餐廳
const getAxios = function () {
    axios
        .get(`http://${url}/restaurants`, { params: urlParams.value })
        .then((res) => {
            resData.value = res.data;
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
};


// 執行Axios;
getAxios();




//加入收藏
function addsaveRestaurant() {
    if (uid == 0) {
        window.location.href = "http://localhost:8080/#/signin";
    } else {
        axios
            .post(`http://${url}/saveRestaurant/${restaurantNumber}/${uid}`, {})
            .then((res) => {
                getAxios();
            })
            .catch((error) => {
                console.log(error, "失敗");
            });
    }
}

//取消收藏
function delsaveRestaurant() {
    axios
        .delete(`http://${url}/saveRestaurant/${restaurantNumber}/${uid}`)
        .then((res) => {
            getAxios();
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
}

</script>

<script>
export default {
    name: "restaurant-details",
    props: {
        paramsData: {
            type: String
        }
    },
};


</script>



<!-- 內容由此開始 -->
<template>

    <!-- 左邊 -->

    <div class="container">
        <div class="row">
            <div class="col">
                <div v-for="item in dataArray" :key="item.restaurantNumber">

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

                                        <button type="button" class="btn btn-outline-primary"
                                            @click="addsaveRestaurant()" v-if="!saveData">收藏</button>

                                        <button type="button" class="btn btn-outline-primary"
                                            @click="delsaveRestaurant()" v-if="saveData">已收藏</button>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />


                        <!-- google map  -->
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <!-- 地圖 -->
                                    <div id="info-map" class="col-md-4 col-lg-5">
                                        <iframe
                                            :src="`https://www.google.com/maps/embed/v1/place?key=AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0&q=${item.restaurantName}&language=zh-TW`"
                                            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                                            referrerpolicy="no-referrer-when-downgrade">
                                        </iframe>
                                    </div>
                                    <!-- 表單 -->
                                    <div class="col-md-12 col-lg-7 d-md-flex align-items-center">
                                        你好
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
                <!-- 猜你可能喜歡 -->
                <div class="container" style="background-color:antiquewhite">
                    <h4 class="text-primary">猜你可能喜歡</h4>
                    <div v-for="item in resData" :key="item.restaurantNumber">
                        <BaseBlock>
                            <div class="row items-push">
                                <!-- 圖片 -->
                                <div class="col-md-1 col-lg-5">
                                    <RouterLink :to="{}" class="img-link img-link-simple">
                                        <img class="img-fluid rounded" :src="`${item.imageUrl}`" alt="" width="200" />
                                    </RouterLink>
                                </div>
                                <!-- 右邊的字 -->
                                <div class="col-md-4 col-lg-5 d-md-flex align-items-center">
                                    <div>
                                        <!-- 餐廳名稱 -->
                                        <div class="d-flex justify-content-between">
                                            <h3 class="card-title text-dark">{{ item.restaurantName }}</h3>
                                            <div>
                                                <div class="badge rounded-pill bg-secondary h5">{{
                                                        item.restaurantCategory
                                                }}</div>
                                            </div>
                                        </div>

                                        <!-- 評分 -->
                                        <h4 style="color:#3498DB">{{ item.restaurantScore }} ★</h4>

                                        <!-- 營業時間 -->
                                        <p style="color: grey;size: 1cm;">
                                            {{ item.restaurantBusinessHours }}
                                        </p>

                                        <!-- 詳細 -->
                                        <button type="button" class="btn btn-outline-primary me-3"
                                            @click.prevent="restaurantDetail(item.restaurantNumber)">詳細</button>

                                    </div>
                                </div>
                            </div>
                        </BaseBlock>

                    </div>
                </div>





            </div>

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