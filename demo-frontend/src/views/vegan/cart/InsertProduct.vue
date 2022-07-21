<script setup>
import { ref, reactive, computed, toRefs } from "vue";

// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
import Swal from "sweetalert2";
import {
  required,
  minLength,
  between,
  email,
  decimal,
  integer,
  url,
  sameAs,
} from "@vuelidate/validators";
// CKEditor 5, for more info and examples you can check out https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/frameworks/vuejs-v3.html
import CKEditor from "@ckeditor/ckeditor5-vue";

import axios from "axios";
import path from "path";
// You can import one of the following CKEditor variation (only one at a time)
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";

//import InlineEditor from '@ckeditor/ckeditor5-build-inline'
//import BalloonEditor from '@ckeditor/ckeditor5-build-balloon'
//import BalloonBlockEditor from '@ckeditor/ckeditor5-build-balloon-block'

// CKEditor 5 variables
let ckeditor = CKEditor.component;

const editorData = ref("<p>請在這邊輸入對商品的詳細描述</p>");
const editorConfig = ref({});

// Set default properties
let toast = Swal.mixin({
  buttonsStyling: false,
  target: "#page-container",
  customClass: {
    confirmButton: "btn btn-success m-1",
    cancelButton: "btn btn-danger m-1",
    input: "form-control",
  },
});

//將下面的參數綁到一個reactive物件裡面
// Input state variables
const state = reactive({
  productName: null,
  productPrice: null,
  stock: null,
  category: null,
  veganCategory: null,
  description: null,
  productImage: null,
});

//用reactive會無法及時反應所以用ref另外宣告
const image = ref({
  imageUrl: null,
});

//檔案上傳方法，寫入後端後會吐回加入UUID之名稱，再回傳data寫入ref()裏
function fileUpload() {
  var files = document.getElementById("input").files;
  var params = new FormData();
  params.append("file", files[0]);
  console.log(params.get("file"));
  axios.post("http://localhost:8088/fileUpload", params).then((res) => {
    image.value = res.data;
    //印出路徑
    console.log(image);
  });
}
function addProduct() {
  state.productName = "蔬脆棒棒腿 (全素 270g/共6支)"
  state.productPrice = "180"
  state.category = "生鮮"
  state.veganCategory = "全素"
  state.stock = "20"
  state.description = "<p>外皮炸得金黃酥脆，內層保有軟嫩口感<br>100%全素產線，安心享用不擔心。<br>❙ 會員首購現折100元</p>"
}

// Validation rules
const rules = computed(() => {
  return {
    productName: {
      required,
      minLength: minLength(1),
    },
    productPrice: {
      required,
      decimal,
    },
    stock: {
      required,
      decimal,
    },
  };
});

// Use vuelidate
const v$ = useVuelidate(rules, state);

// On form submission
async function onSubmit() {
  const result = await v$.value.$validate();

  if (!result) {
    // notify user form is invalid
    return;
  }

  // perform async actions
}
function createProduct() {
  toast
    .fire({
      title: "確定要新增嗎?",
      text: "新增後將跳轉至商品列表",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "新增資料",
      cancelButtonText: "繼續填寫",

      html: false,
      preConfirm: () => {
        return new Promise((resolve) => {
          setTimeout(() => {
            resolve();
          }, 50);
        });
      },
    })
    .then((result) => {
      //send request to server
      if (result.value) {
        const product = {
          productName: state.productName,
          category: state.category,
          veganCategory: state.veganCategory,
          productPrice: state.productPrice,
          stock: state.stock,
          description: state.description,
          productImage: image.value.imageUrl,
        };
        axios
          .post("http://localhost:8088/products", product)
          .then(() => {
            window.location = "#/backend/cart/productInfo";
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("返回輸入", "", "error");
      }
    });
}
</script>

<template>
  <!-- Hero -->

  <BasePageHeading title="商品管理" subtitle="填寫資料新增商品">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-cart-shopping"></i> 購物車管理</a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <a class="link-fx" href="#/backend/cart/productInfo">
              <i class="fa fa-leaf"></i> 商品管理</a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-leaf"></i> 新增商品
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <!-- Basic -->
    <form @submit.prevent="onSubmit" @submit="createProduct" id="forms">
      <BaseBlock title="Validation Form" content-full>
        <div class="row push">
          <div class="col-lg-4">
            <p class="fs-sm text-muted">請盡量填寫完整資訊</p>
          </div>
          <div class="col-lg-8 col-xl-5">
            <!-- 商品名稱開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-productName">商品名稱 <span class="text-danger">*</span></label>
              <input type="text" id="val-productName" class="form-control" :class="{
                'is-invalid': v$.productName.$errors.length,
              }" v-model="state.productName" @blur="v$.productName.$touch" placeholder="請輸入產品名稱" />
              <div v-if="v$.productName.$errors.length" class="invalid-feedback animated fadeIn">
                請輸入商品名稱
              </div>
            </div>

            <!-- 商品分類開始 -->
            <div class="mb-4">
              <label class="form-label" for="example-select">選擇商品分類</label>
              <select class="form-select" id="example-select" name="example-select" v-model="state.category">
                <option selected>按我選擇</option>
                <option value="生鮮">生鮮</option>
                <option value="食品">食品</option>
                <option value="寵物">寵物</option>
                <option value="居家百貨">居家百貨</option>
                <option value="飾品">飾品</option>
                <option value="保健">保健</option>
                <option value="書籍影音">書籍影音</option>
                <option value="美妝保養">美妝保養</option>
                <option value="量販批發">量販批發</option>
              </select>
            </div>
            <!-- 素食種類開始 -->
            <div class="mb-4">
              <label class="form-label" for="example-select">選擇素食種類</label>
              <select class="form-select" id="example-select" name="example-select" v-model="state.veganCategory">
                <option selected>按我選擇</option>
                <option value="全素">全素</option>
                <option value="蛋素">蛋素</option>
                <option value="奶素">奶素</option>
                <option value="蛋奶素">蛋奶素</option>
                <option value="五辛素">五辛素</option>
                <option value="VEGAN">VEGAN</option>
                <option value="書籍影音">書籍影音</option>
                <option value="美妝保養">美妝保養</option>
                <option value="量販批發">量販批發</option>
              </select>
            </div>

            <!-- 價格開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-productPrice">台幣 (NTD) <span class="text-danger">*</span></label>
              <input type="text" id="val-productPrice" class="form-control" :class="{
                'is-invalid': v$.productPrice.$errors.length,
              }" v-model="state.productPrice" @blur="v$.productPrice.$touch" placeholder="200" />
              <div v-if="v$.productPrice.$errors.length" class="invalid-feedback animated fadeIn">
                請輸入數字!
              </div>
            </div>

            <!-- 庫存開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-stock">庫存 <span class="text-danger">*</span></label>
              <input type="text" id="val-stock" class="form-control" :class="{
                'is-invalid': v$.stock.$errors.length,
              }" v-model="state.stock" @blur="v$.stock.$touch" placeholder="200" />
              <div v-if="v$.stock.$errors.length" class="invalid-feedback animated fadeIn">
                請輸入數字!
              </div>
            </div>
            <!-- 圖片開始 -->
            <!-- 選擇玩圖片後會自動寫入資料庫並帶回正確網址 -->
            <div>
              <label class="form-label" for="val-stock">圖片 </label>
              <input class="form-control" id="input" type="file" ref="myFile" @change="fileUpload()" />
              <br />
              <!-- 根據回傳值印出圖片 -->
              <img :src="image.imageUrl" style="max-width:500px;width:100%" />
              <br />
            </div>

            <!-- 產品CK editor -->
            <div class="mb-4">
              <label class="form-label" for="example-select">產品細節描述</label>
              <ckeditor :editor="ClassicEditor" :config="editorConfig" v-model="state.description" />
            </div>

            <!-- 一鍵新增 -->

            <div class="row items-push">
              <div class="col-lg-7 offset-lg-4">
                <button class="btn btn-alt-primary">Submit</button>
              </div>
            </div>
          </div>
        </div>

      </BaseBlock>
    </form>
    <button class="btn btn-alt-secondary" @click="addProduct()">一鍵商品</button>
    <!-- END Basic -->
  </div>
</template>
