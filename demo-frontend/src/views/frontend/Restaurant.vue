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
            console.log("取得餐廳種類：" + res.data.restaurantCategory);
            const catagory = (res.data.restaurantCategory);
            console.log(catagory);
            let array = [];
            array.push(res.data);
            dataArray.value = array;

        })
        .catch((err) => console.log(err));
}
getRestaurant();

//取得單筆餐廳by catagory
const getByCatagory = function () {
    data.loading = true;
    axios
        .get(`http://${url}/restaurantList/restaurantCategory=${restaurantCategory}`)

        .then((res) => {
            ;
            console.log(res);
            console.log(res.data);
            let array = [];
            array.push(res.data);
            console.log(array);
            restaurantCategory.value = res.data.restaurantCategory;
            dataArray.value = array;

        })
        .catch((err) => console.log(err));
}
getByCatagory();

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
getAxios();

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

    <div class="container">
        <div class="row">
            <div class="col">
                <div v-for="item in dataArray" :key="item.restaurantNumber">

                    <BaseBlock>

                        <!-- 餐廳簡介開始 -->
                        <div class="container" style="margin-top: 1cm; padding-left: 0.5cm;">
                            <div class="row mb-3 p-3">
                                <!-- 左邊圖片 -->
                                <div class="col-md-5 mb-1">
                                    <div class="slide__main">

                                        <div class="col-md-4 col-lg-5">
                                            <RouterLink :to="{}" class="img-link img-link-simple">
                                                <img :src="`${item.imageUrl}`" alt="" width="400" />
                                            </RouterLink>
                                        </div>
                                    </div>
                                </div>
                                <!-- 右邊文字 -->
                                <div class="col-md-7 mb-1">
                                    <div>
                                        <h1 class="text-decoration-underline">{{ item.restaurantName }} </h1>
                                        <h3 style="color:#3498DB">{{ item.restaurantScore }} ★</h3>

                                        <h4>電話：{{ item.restaurantTel }}</h4>

                                        <div>
                                            <h4>地址：{{ item.restaurantAddress }}</h4>
                                        </div>
                                        <div>
                                            <h4>餐廳類型：{{ item.restaurantCategory }}</h4>
                                        </div>
                                        <div>
                                            <h4>素食種類：{{ item.restaurantType }}</h4>
                                        </div>
                                        <h4>營業時間：{{ item.restaurantBusinessHours }}</h4>
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
                        <!-- 餐廳簡介結束 -->

                        <!-- 下方google map  -->
                        <div class="container" style="margin-top: 1cm;">
                            <div class="row">
                                <div class="col">
                                    <!-- 地圖 -->
                                    <h3>餐廳位置</h3>
                                    <div id="info-map" class="col-md-4 col-lg-5">
                                        <iframe
                                            :src="`https://www.google.com/maps/embed/v1/place?key=AIzaSyBwhBQXDks6CAdcxO-1SoTU6wKttYcHLx0&q=${item.restaurantName}&language=zh-TW`"
                                            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                                            referrerpolicy="no-referrer-when-downgrade">
                                        </iframe>
                                    </div>
                                </div>
                                <!-- 表單 -->
                                <div class="col">
                                    <h3>聯絡我們</h3>
                                    <form>
                                        <div class="row g-3">
                                            <div class="col-md-6">
                                                <div class="form-floating">
                                                    <input type="text" class="form-control" id="name" placeholder="姓名">
                                                    <label for="name">姓名</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-floating">
                                                    <input type="email" class="form-control" id="email"
                                                        placeholder="電話">
                                                    <label for="email">電話</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating">
                                                    <input type="text" class="form-control" id="subject"
                                                        placeholder="信箱">
                                                    <label for="subject">信箱</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating">
                                                    <input type="text" class="form-control" id="subject"
                                                        placeholder="主旨">
                                                    <label for="subject">主旨</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating">
                                                    <textarea class="form-control" placeholder="請輸入留言" id="message"
                                                        style="height: 150px"></textarea>
                                                    <label for="message">留言</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button class="btn btn-primary w-100 py-3" type="submit">送出留言</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <hr />

                        <!-- 評論 -->
                        <div class="container" style="margin-top: 1cm;">
                            <h3>評論</h3>
                            <div class="row">
                                <div class="col">
                                    <div>
                                        <h2 style="color:#FFEA00">★★★★★</h2>
                                    </div>
                                    <a>
                                        <h5>
                                            飄香一甲子的這家老店，傳承的不只是素食的好手藝，更承襲了先人們念茲在茲的善念，因此多年來，始終堅持自行研發素食材料，為的就是希望每一位上門的客人，都可以在吃到美味的同時，也吃進了充足的營養。
                                        </h5>
                                        <p class="text-end" style="color:#3498DB">一貪吃鬼的美好時光</p>
                                    </a>
                                </div>

                                <div class="col">
                                    <div>
                                        <h2 style="color:#FFEA00">★★★★</h2>
                                    </div>
                                    <a>
                                        <h5 class="title is-5">環境清潔、服務親切，很棒的用餐環境，食物有特色，特別喜歡煨湯和握壽司</h5>
                                        <p class="text-end" style="color:#3498DB">一鍾建達</p>
                                    </a>
                                </div>

                                <div class="col">
                                    <div>
                                        <h2 style="color:#FFEA00">★★★★★</h2>
                                    </div>
                                    <a>
                                        <h5 class="title is-5">有名的金牌老店，歷史悠久的素食之家 應該七十年了，有很多素食老菜 老菜新作 創新料理 等等，生日壽宴聯誼聚餐
                                            商業套餐都很適合！</h5>
                                        <p class="text-end" style="color:#3498DB">一黃永毅</p>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </BaseBlock>
                </div>

                <!-- 猜你可能喜歡 -->
                <div class="container">
                    <h3 class="text-primary">猜你可能喜歡</h3>
                    <div>
                        <BaseBlock>

                            <div class="card-group">

                                <div class="row row-cols-3 row-cols-md-1 g-1" v-for="item in resData"
                                    :key="item.restaurantNumber">

                                    <div class="card mb-3" style="max-width: 400px;">
                                        <div class="row g-0">
                                            <div class="col-md-6">
                                                <img class="img-fluid rounded" :src="`${item.imageUrl}`" alt=""
                                                    width="300">
                                            </div>
                                            <div class="col-md-6">
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between">
                                                        <h5 class="card-title text-dark">{{ item.restaurantName }}</h5>

                                                        <p class="h6" style="color:#3498DB">
                                                            {{ item.restaurantScore }} ★
                                                        </p>

                                                    </div>
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
                                    </div>



                                </div>




                            </div>






                        </BaseBlock>

                    </div>
                </div>

            </div>

        </div>
    </div>

    <!-- 測試 -->
    <!-- 測試 -->




    <!-- Footer -->
    <footer id="page-footer" class="bg-body-light">
        <div class="content py-5">
            <div class="row fs-sm fw-medium">
                <div class="col-sm-6 order-sm-2 py-1 text-center text-sm-end"> 本網站僅作為 <i
                        class="fa fa-heart text-danger"></i>
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