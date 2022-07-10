<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";
//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = ref(
  {
    limit: 15,
    offset: 0,
    category: null,
    veganCategory: null,
    search: null
  }
);
//接收的資料ref
const resData = ref();
const productsTotal = ref();
const productList = ref();
const singleProduct = ref(
  {
    // ref會自己抓值，這邊還要另外宣告圖片是因為:src會去抓路徑，沒有定義會變undefined當掉
    //其他的值ref抓到後會自己帶入變成json
    productImage: null,
  }

)

const getAxios = function () {
  console.log(urlParams)
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

function getSingle(productId) {
  axios
    .get(`http://${url}/products/${productId}`)
    .then((res) => {
      singleProduct.value = res.data
      console.log(singleProduct)
    })


}
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
  <!-- 這邊是查詢功能放的地方 -->

  <!-- 這邊是下拉式選單 -->
  <!-- 素種類選單 -->
  <div class="row items-push mb-4">
    <div class="col-sm-6 col-xl-4">
      <div class="dropdown">
        <button type="button" class="btn btn-alt-info dropdown-toggle" id="dropdown-default-alt-info"
          data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <a v-if="urlParams.veganCategory == null">
            素種類
          </a>
          {{ urlParams.veganCategory }}
        </button>
        <div class="dropdown-menu fs-sm" aria-labelledby="dropdown-default-alt-info">

          <!-- 這邊開始是一格會打勾的radio -->
          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block1" name="example-radio-block" value="全素"
              v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block1">
              <span class="fs-4 fw-semibold">全素</span>
            </label>
          </div>
          <!-- 這裡結束一格-->

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block2" name="example-radio-block" value="蛋素"
              v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block2">
              <span class="fs-4 fw-semibold">蛋素</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block3" name="example-radio-block" value="奶素"
              v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block3">
              <span class="fs-4 fw-semibold">奶素</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block4" name="example-radio-block"
              value="蛋奶素" v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block4">
              <span class="fs-4 fw-semibold">蛋奶素</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block5" name="example-radio-block"
              value="五辛素" v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block5">
              <span class="fs-4 fw-semibold">五辛素</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block6" name="example-radio-block"
              value="VEGAN" v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block6">
              <span class="fs-4 fw-semibold">VEGAN</span>
            </label>
          </div>
          <!-- <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="example-radio-block7" name="example-radio-block"
              value="null" v-model="urlParams.veganCategory" @change="getAxios()" />
            <label class="form-check-label" for="example-radio-block7">
              <span class="fs-4 fw-semibold">全選</span>
            </label>
          </div> -->
        </div>
      </div>
    </div>
    <!--    種類選單 -->
    <div class="col-sm-6 col-xl-4">
      <div class="dropdown">
        <button type="button" class="btn btn-alt-info dropdown-toggle" id="dropdown-default-alt-info"
          data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <a v-if="urlParams.category == null">
            商品種類
          </a>
          {{ urlParams.category }}
        </button>
        <div class="dropdown-menu fs-sm" aria-labelledby="dropdown-default-alt-info">

          <!-- 這邊開始是一格會打勾的radio -->
          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category1" name="category" value="生鮮"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category1">
              <span class="fs-4 fw-semibold">生鮮</span>
            </label>
          </div>
          <!-- 這裡結束一格-->

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category2" name="category" value="食品"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category2">
              <span class="fs-4 fw-semibold">食品</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category3" name="category" value="寵物"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category3">
              <span class="fs-4 fw-semibold">寵物</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category4" name="category" value="居家百貨"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category4">
              <span class="fs-4 fw-semibold">居家百貨</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category5" name="category" value="飾品"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category5">
              <span class="fs-4 fw-semibold">飾品</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category6" name="category" value="保健"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category6">
              <span class="fs-4 fw-semibold">保健</span>
            </label>
          </div>
          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category7" name="category" value="書籍影音"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category7">
              <span class="fs-4 fw-semibold">書籍影音</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category8" name="category" value="美妝保養"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category8">
              <span class="fs-4 fw-semibold">美妝保養</span>
            </label>
          </div>

          <div class="form-check form-block">
            <input type="radio" class="form-check-input" id="category9" name="category" value="量販批發"
              v-model="urlParams.category" @change="getAxios()" />
            <label class="form-check-label" for="category9">
              <span class="fs-4 fw-semibold">量販批發</span>
            </label>
          </div>

        </div>
      </div>
    </div>
  </div>

  <!-- 這邊是商品頁 -->
  <!-- Page Content -->
  <div class="content">
    <!-- Modern Design -->
    <div class="row items-push">
      <!-- v-for放的位置要在卡片層，不然會跑版 -->
      <div v-for="item in productList" :key="item.productId" class="col-md-5  col-xl-3">
        <BaseBlock tag="a" href="javascript:void(0)" class="text-center" link-shadow>
          <template #content>
            <!-- 這邊是卡片種類 -->
            <div class="card" style="width: 18rem">

              <!-- 我是圖片 裡面方法顯示單一商品 -->
              <img :src="item.productImage" class="card-img-top" alt="Photo" data-bs-toggle="modal"
                data-bs-target="#showProduct" @click="getSingle(item.productId)" />

              <!-- 這邊是商品名稱 裡面塞的方法是顯示單一個商品 -->
              <div class="card-body">
                <h5 class="card-title" data-bs-toggle="modal" data-bs-target="#showProduct"
                  @click="getSingle(item.productId)">
                  {{ item.productName }}
                </h5>

                <div class="row text-white fw-semibold">
                  <div class="col-3">
                    <p class="badge bg-info">{{ item.productCategory }}</p>
                  </div>

                  <div class="col-3">
                    <p class="badge bg-success">{{ item.veganCategory
                    }}</p>
                  </div>
                </div>

                <!-- 這邊是價格跟假的優惠 -->
                <div class="mb-3">

                  <s>{{ item.productPrice + 70 }}</s>
                  <h3 class="text-danger">{{ item.productPrice }}</h3>
                </div>

                <!-- 庫存 -->
                <!-- 判斷庫存量給予相對應的標籤並將值放進去 -->
                <div class="mb-4">
                  <div v-if="item.stock > 100">
                    <span class="badge bg-success"><i class="fa fa-check"></i> 供貨正常{{ item.stock
                    }}</span>
                  </div>
                  <div v-else-if="item.stock > 20">
                    <span class="badge bg-info"><i class="fa fa-exclamation-circle"></i> 手刀搶購{{ item.stock
                    }}</span>
                  </div>
                  <div v-else-if="item.stock > 0">
                    <span class="badge bg-warning"><i class="fa fa-exclamation-circle"></i> 最後倒數{{ item.stock
                    }}</span>
                  </div>
                  <div v-else>
                    <span class="badge bg-danger"><i class="fa fa-times-circle"></i> 已無商品</span>
                  </div>
                  <!-- 購物車按鈕 -->
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn 
btn-outline-danger" data-bs-dismiss="modal">
                    <i class="far fa-heart">收藏</i>
                  </button>
                  <button type="submit" class="btn btn-outline-primary" data-bs-dismiss="modal">
                    <i class="fa fa-cart-shopping">加入購物車</i>
                  </button>
                </div>
              </div>
            </div>
          </template>
        </BaseBlock>

      </div>
    </div>

  </div>
  <!-- END Page Content -->


  <!-- 這邊以下是隱藏的更新表單，按下更新鈕之後會跳出來 -->
  <div class="modal fade" id="showProduct" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <!-- 這邊是更新的標題 -->
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">商品詳細資訊</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <!-- 內文開始 -->
        <div class="modal-body">
          <!-- 商品名稱 -->
          <div class="mb-3">
            <h5>{{ singleProduct.productName }}</h5>
          </div>

          <!-- 圖片 -->
          <div class="mb-4">
            <label class="form-label" for="val-stock"> </label>
            <div class="mb-3">
              <!-- 根據回傳值印出圖片 -->
              <img :src="singleProduct.productImage" style="max-width:500px;width:100%" />
            </div>
          </div>

          <!-- 商品種類  素食種類標籤-->

          <div class="row text-white fw-semibold">
            <div class="col-1">
              <p class="badge bg-info">{{ singleProduct.productCategory }}</p>
            </div>

            <div class="col-1">
              <p class="badge bg-success">{{ singleProduct.veganCategory }}</p>
            </div>
          </div>

          <!-- 價格 -->
          <div class="mb-3">

            <s>{{ singleProduct.productPrice + 70 }}</s>
            <h3 class="text-danger">{{ singleProduct.productPrice }}</h3>

          </div>


          <!-- 庫存 -->
          <!-- 判斷庫存量給予相對應的標籤並將值放進去 -->
          <div class="mb-4">
            <div v-if="singleProduct.stock > 100">
              <span class="badge bg-success"><i class="fa fa-check"></i> 供貨正常{{ singleProduct.stock
              }}</span>
            </div>
            <div v-else-if="singleProduct.stock > 20">
              <span class="badge bg-info"><i class="fa fa-exclamation-circle"></i> 手刀搶購{{ singleProduct.stock
              }}</span>
            </div>
            <div v-else-if="singleProduct.stock > 0">
              <span class="badge bg-warning"><i class="fa fa-exclamation-circle"></i> 最後倒數{{ singleProduct.stock
              }}</span>
            </div>
            <div v-else>
              <span class="badge bg-danger"><i class="fa fa-times-circle"></i> 已無商品</span>
            </div>
          </div>
          <!-- 下拉顯示選單 -->
          <BaseBlock :title="`  商品詳細描述 `" btn-option-content>
            <template #subtitle>
              這裡可以放副標題
            </template>
            <div class="row items-push-2x text-center">
              這裡會顯示商品的詳細描述
              {{ singleProduct.description }}
            </div>
          </BaseBlock>

        </div>
        <!-- 表單內文在這裡結束 -->
        <!-- 送出button -->
        <div class="modal-footer">
          <button type="button" class="btn 
btn-outline-danger" data-bs-dismiss="modal">
            <i class="far fa-heart">收藏</i>
          </button>
          &ensp;&ensp;&ensp;
          <button type="submit" class="btn btn-outline-primary" data-bs-dismiss="modal">
            <i class="fa fa-cart-shopping">加入購物車</i>
          </button>

        </div>
      </div>
    </div>
  </div>
</template>
