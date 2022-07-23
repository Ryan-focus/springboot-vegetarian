<script setup>
import { useTemplateStore } from "@/stores/template";
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

// Vue Dataset, for more info and examples you can check out https://github.com/kouts/vue-dataset/tree/next
import {
  Dataset,
  DatasetItem,
  DatasetInfo,
  DatasetPager,
  DatasetSearch,
  DatasetShow,
} from "vue-dataset";


// Main store
const store = useTemplateStore();
//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = "warning";
//接收的資料ref
const resData = ref();

const resPostId = ref();
const resPostTitle = ref();
const resPostText = ref();
const resPostStatus = ref("待審核");

const router = useRouter();

const getAxios = function () {
  axios
    .get(`http://${url}/postStatusList`)
    //.get(`http://${url}/postStatusList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      console.log(res);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//執行Axios
getAxios();
// For Filters

//發布中文章(全素)
function Category1() {
  //send request to server
  axios
    .get(`http://${url}/postCategory1`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}
//發布中文章(蛋素)
function Category2() {
  //send request to server
  axios
    .get(`http://${url}/postCategory2`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}
//發布中文章(奶素)
function Category3() {
  //send request to server
  axios
    .get(`http://${url}/postCategory3`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}
//發布中文章(蛋奶素)
function Category4() {
  //send request to server
  axios
    .get(`http://${url}/postCategory4`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}
//發布中文章(植物五辛素)
function Category5() {
  //send request to server
  axios
    .get(`http://${url}/postCategory5`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

//跳到食記詳細頁面
function findPost(id) {
  //send request to server
  console.log(id);
  router.push({ name: "postPage", params: { postId: id } });
}
</script>
<style>
.card-text {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  white-space: normal;
}
</style>

<template>
  <!-- Hero -->
  <!-- Page Content -->
  <div class="content">
    <!-- 上方主題分類 -->

    <div class="row justify-content-center text-center">
      <div class="col-md-12 mb-4">
        <BaseBackground>
          <div class="col-xs-4">
            <button type="button" class="btn btn-alt-info" @click.prevent="getAxios">
              全部
            </button>
            <button type="button" class="btn btn-alt-info" @click.prevent="Category1()">
              全素
            </button>
            <button type="button" class="btn btn-alt-info" @click.prevent="Category2()">
              蛋素
            </button>
            <button type="button" class="btn btn-alt-info" @click.prevent="Category3()">
              奶素
            </button>
            <button type="button" class="btn btn-alt-info" @click.prevent="Category4()">
              蛋奶素
            </button>
            <button type="button" class="btn btn-alt-info" @click.prevent="Category5()">
              植物五辛素
            </button>
          </div>
        </BaseBackground>
      </div>
    </div>
    <!-- Modern Design -->

    <div class="row">
      <div class="container">
        <!-- Card deck -->
        <div class="card-deck row">
          <div class="col-xs-12 col-sm-6 col-md-4" v-for="item in resData" :key="item.postId">
            <!-- Card -->
            <div class="card">
              <!--Card image-->
              <div class="view overlay">
                <img :src="item.imgurl" class="card-img-top" alt="Card image cap" width="400" height="300" />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>
              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">{{ item.title }}</h4>
                <!--Text-->
                <div clss="card-box">
                  <div class="card-text" v-html="item.postedText"></div>
                </div>
                <br />
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->

                <button type="button" class="btn rounded-0 btn-outline-info" @click="findPost(item.postId)">
                  Read More
                </button>
              </div>
            </div>
            <!-- Card -->
            <br />
          </div>
        </div>
        <!-- Card deck -->

      </div>

    </div>

  </div>
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
  <!-- END Page Content -->
</template>
