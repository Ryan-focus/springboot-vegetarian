<script setup>
import { ref, reactive, computed } from "vue";

// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
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

// You can import one of the following CKEditor variation (only one at a time)
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
//import InlineEditor from '@ckeditor/ckeditor5-build-inline'
//import BalloonEditor from '@ckeditor/ckeditor5-build-balloon'
//import BalloonBlockEditor from '@ckeditor/ckeditor5-build-balloon-block'
// import EasyImage from '@ckeditor/ckeditor5-easy-image/src/easyimage';
// import Image from '@ckeditor/ckeditor5-image/src/image';
// import ImageUpload from '@ckeditor/ckeditor5-image/src/imageupload';

// CKEditor 5 variables
let ckeditor = CKEditor.component;

const editorData = ref("<p>Hello CKEditor5!</p>");
const editorConfig = ref({});

// Example options for select
const options = reactive([
  { value: null, text: "Please select" },
  { value: "html", text: "HTML" },
  { value: "css", text: "CSS" },
  { value: "javascript", text: "JavaScript" },
  { value: "angular", text: "Angular" },
  { value: "react", text: "React" },
  { value: "vuejs", text: "Vue.js" },
  { value: "ruby", text: "Ruby" },
  { value: "php", text: "PHP" },
  { value: "asp", text: "ASP.NET" },
  { value: "python", text: "Python" },
  { value: "mysql", text: "MySQL" },
]);

// Input state variables
const state = reactive({
  username: null,
  email: null,
  password: null,
  confirmPassword: null,
  suggestions: null,
  skill: null,
  currency: null,
  website: null,
  digits: null,
  number: null,
  range: null,
  terms: null,
});

// Validation rules
const rules = computed(() => {
  return {
    username: {
      required,
      minLength: minLength(1),
    },
    email: {
      required,
      email,
    },
    password: {
      required,
      minLength: minLength(5),
    },
    confirmPassword: {
      required,
      sameAs: sameAs(state.password),
    },
    suggestions: {
      required,
      minLength: minLength(3),
    },
    skill: {
      required,
    },
    currency: {
      required,
      decimal,
    },
    website: {
      required,
      url,
    },
    digits: {
      required,
      integer,
    },
    number: {
      required,
      decimal,
    },
    range: {
      required,
      between: between(1, 5),
    },
    terms: {
      sameAs: sameAs(true),
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

// UploadAdapter: function(loader) {
//     this.loader = loader
//     loader.file.then(response)
    
//     this.upload = () => {
//     const body = new FormData();
//     body.append('file', this.loader.file);

//     return fetch('http://localhost:8000/api/imageUpload', {
//     headers: {
//     'Content-Type': "multipart/form-data; charset=utf-8; boundary=" + Math.random().toString().substr(2),
//     'Accept': 'application/json'
//            },
//     body: body,
//     method: 'post'
//             })
//     .then(response => response.json())
//     .then(downloadUrl => {
//           return {

//         default: downloadUrl
//         }
//     })
//     .catch(error => {
//         console.log(error);
//     });
//         }
//     this.abort = () => {
//           console.log('Abort upload.')
//     }

// }

</script>



<template>
  <!-- Hero -->

  <BasePageHeading title="商品管理" subtitle="填寫資料新增商品">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-cart-shopping"></i> 購物車管理</a
            >
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <a class="link-fx" href="#/backend/cart/productInfo">
              <i class="fa fa-leaf"></i> 商品管理</a
            >
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
    <form @submit.prevent="onSubmit">
      <BaseBlock title="Validation Form" content-full>
        <div class="row push">
          <div class="col-lg-4">
            <p class="fs-sm text-muted">請盡量填寫完整資訊</p>
          </div>
          <div class="col-lg-8 col-xl-5">
            <!-- 商品名稱開始 -->
            <div class="mb-4">
              <label class="form-label" for="val-username"
                >商品名稱 <span class="text-danger">*</span></label
              >
              <input
                type="text"
                id="val-username"
                class="form-control"
                :class="{
                  'is-invalid': v$.username.$errors.length,
                }"
                v-model="state.username"
                @blur="v$.username.$touch"
                placeholder="Enter a username.."
              />
              <div
                v-if="v$.username.$errors.length"
                class="invalid-feedback animated fadeIn"
              >
                請輸入商品名稱
              </div>
            </div>
            <!-- 商品分類開始 -->
            <div class="mb-4">
              <label class="form-label" for="example-select"
                >選擇商品分類</label
              >
              <select
                class="form-select"
                id="example-select"
                name="example-select"
              >
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
              <label class="form-label" for="example-select"
                >選擇素食種類</label
              >
              <select
                class="form-select"
                id="example-select"
                name="example-select"
              >
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
              <label class="form-label" for="val-currency"
                >台幣 (NTD) <span class="text-danger">*</span></label
              >
              <input
                type="text"
                id="val-currency"
                class="form-control"
                :class="{
                  'is-invalid': v$.currency.$errors.length,
                }"
                v-model="state.currency"
                @blur="v$.currency.$touch"
                placeholder="200"
              />
              <div
                v-if="v$.currency.$errors.length"
                class="invalid-feedback animated fadeIn"
              >
                請輸入數字!
              </div>
            </div>
            <!-- 圖片上傳開始-->
            <div class="row push">
              <div class="col-lg-8 col-xl-5 overflow-hidden">
                <div class="mb-4">
                  <label class="form-label" for="example-file-input"
                    >圖片上傳（一張）</label
                  >
                  <input
                    class="form-control"
                    type="file"
                    id="example-file-input"
                  />
                </div>
              </div>
            </div>
            <!-- 產品CK editor -->
            <div class="mb-4">
              <label class="form-label" for="example-select"
                >產品細節描述</label
              >
              <ckeditor
                :editor="ClassicEditor"
                :config="editorConfig"
                :upload-adapter="UploadAdapter"
                v-model="editorData"
              />
            </div>
            <div class="row items-push">
              <div class="col-lg-7 offset-lg-4">
                <button type="submit" class="btn btn-alt-primary">
                  Submit
                </button>
              </div>
            </div>
          </div>
        </div>
      </BaseBlock>
    </form>
    <!-- END Basic -->
  </div>
</template>
