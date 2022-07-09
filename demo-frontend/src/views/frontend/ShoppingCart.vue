<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";
//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = ref(
  {
    category: null

  }
);
//接收的資料ref
const resData = ref();
const productsTotal = ref();
const productList = ref(
);

const getAxios = function () {
  axios
    .get(`http://${url}/products`, { params: urlParams.value })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      productsTotal.value = res.data.total;
      productList.value = res.data.results
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
// 執行Axios;
getAxios();
// For Filters


</script>
<template>
  <!-- Hero -->
  <div class="row">
    <BaseBlock content-full>
      <div id="carouselExampleIndicators" class="carousel slide carousel-fade">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
            aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
            aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
            aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img
              src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1656481480-j7s9rs73lm_m_1200x531_800x354_400x177.jpg"
              class="d-block w-100" alt="Carousel Image 1" />
          </div>
          <div class="carousel-item">
            <img
              src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1636528299-v1vyikczrn_m_1200x531_800x354_400x177.jpg"
              class="d-block w-100" alt="Carousel Image 1" />
          </div>
          <div class="carousel-item">
            <img
              src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1631241731-h1yuofsgjg_m_1200x531_800x354_400x177.jpg"
              class="d-block w-100" alt="Carousel Image 1" />
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
          data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
          data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </BaseBlock>
    <!-- END Hero -->
  </div>

  <div class="row">
    <BaseBlock>
      <img
        src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1551854036-no8pq90hxv.png"
        alt="" class="d-inline" />

      <img
        src="  https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1585213936-x05bo61xos_m.png"
        alt="" class="d-inline" />
      <img
        src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1551854126-u4jy8my8l6.png"
        alt="" class="d-inline" />
    </BaseBlock>
  </div>

  <div class="row">
    <BaseBlock>
      <img
        src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1613550618-sdpk0sytx1_m_1200x94_800x63_400x31.gif"
        alt="" class="d-block w-100" />
    </BaseBlock>
  </div>

  <div class="row">
    <BaseBlock>
      <img
        src="https://cms.cdn.91app.com/images/original/12557/481fb4bf-120e-42de-b3a6-9b2d0bfc55ef-1656313073-yymy6f1xps_m_1200x375_800x250_400x125.jpg"
        alt="" class="d-block w-100" />
    </BaseBlock>
  </div>

  <input type="text" v-model="urlParams.category">
  <button class="btn btn-alt-primary" @click="getAxios()">Submit</button>

  <!-- Page Content -->
  <div class="content">
    <!-- Modern Design -->
    <div class="row items-push">
      <!-- v-for放的位置要在卡片層，不然會跑版 -->
      <div v-for="item in productList" :key="item.productId" class="col-md-5  col-xl-3">
        <BaseBlock tag="a" href="javascript:void(0)" class="text-center" link-shadow>
          <template #content>
            <div class="card" style="width: 18rem">
              <img :src="item.productImage" class="card-img-top" alt="［素日子］好日子家常素肉圓 （不含醬料）（150g＊3顆）" />
              <div class="card-body">
                <h5 class="card-title">
                  {{ item.productName }}
                </h5>
                <p>${{ item.productPrice }}</p>
                <html>
                {{ item.description }}

                </html>
                <div class="block-content bg-body-light">
                  <div class="sc-fzXfOz jRhlbT">
                    <span class="d-inline"><i class="far fa-heart">收藏</i></span>
                    &ensp;&ensp;&ensp;&ensp;
                    <span class="d-inline"><i class="fa fa-cart-shopping">加入購物車</i></span>
                  </div>
                </div>
              </div>
            </div>
          </template>
        </BaseBlock>

      </div>
    </div>

  </div>
  <!-- END Page Content -->
</template>
