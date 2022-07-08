<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";

// Vue Dataset, for more info and examples you can check out https://github.com/kouts/vue-dataset/tree/next
import {
  Dataset,
  DatasetItem,
  DatasetInfo,
  DatasetPager,
  DatasetSearch,
  DatasetShow,
} from "vue-dataset";

//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = "warning";
//接收的資料ref
const resData = ref();

const resPostId = ref();
const resPostTitle = ref();
const resPostText = ref();
const resPostStatus = ref("待審核");

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
      <div class="col-md-3 mb-4">
        <BaseBackground>
          <div class="d-grid gap-2 d-md-flex">
            <button type="button" class="btn btn-alt-info">Info</button>
            <button type="button" class="btn btn-alt-info">Left</button>
            <button type="button" class="btn btn-alt-info">Middle</button>
            <button type="button" class="btn btn-alt-info">Right</button>
          </div>
        </BaseBackground>
      </div>
    </div>
    <!-- Modern Design -->

    <div class="row">
      <div class="container">
        <!-- Card deck -->
        <div class="card-deck row">
          <div
            class="col-xs-12 col-sm-6 col-md-4"
            v-for="item in resData"
            :key="item.postId"
          >
            <!-- Card -->
            <div class="card">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/14.jpg"
                  alt="Card image cap"
                />
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
                  <p class="card-text">
                    {{ item.postedText }}
                  </p>
                </div>
                <br />
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn rounded-0 btn-outline-info">
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

  <!-- END Page Content -->
</template>
