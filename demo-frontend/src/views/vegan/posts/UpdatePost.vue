<style>
.ck-editor__editable {
  min-height: 400px;
}
</style>
<script setup>
import { ref, reactive, computed, onBeforeUnmount } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
import {
  required,
  minLength,
  between,
  email,
  decimal,
  integer,
  sameAs,
} from "@vuelidate/validators";
// CKEditor 5, for more info and examples you can check out https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/frameworks/vuejs-v3.html
import CKEditor from "@ckeditor/ckeditor5-vue";

// You can import one of the following CKEditor variation (only one at a time)
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
//import InlineEditor from '@ckeditor/ckeditor5-build-inline'
//import BalloonEditor from '@ckeditor/ckeditor5-build-balloon'
//import BalloonBlockEditor from '@ckeditor/ckeditor5-build-balloon-block'
import Dropzone from "dropzone";

//預設傳值伺服器與
const url = "localhost:8088";
//接收的資料ref
var resData = ref();

const resPostId = ref();
const resPostTitle = ref();
const resPostText = ref();
const resPostStatus = ref("待審核");

// CKEditor 5 variables
let ckeditor = CKEditor.component;

const editorData = ref("<p>Hello CKEditor5!</p>");
const editorConfig = ref({});

// Input state variables
const state = reactive({
  title: null,
  postedText: null,
  category: null,
});

// Example options for select
const options = reactive([
  { value: null, text: "請選擇" },
  { value: "全素", text: "全素" },
  { value: "蛋素", text: "蛋素" },
  { value: "奶素", text: "奶素" },
  { value: "蛋奶素", text: "蛋奶素" },
  { value: "五辛素", text: "五辛素" },
]);

// Validation rules
const rules = computed(() => {
  return {
    title: {
      required,
      minLength: minLength(1),
    },
    category: {
      required,
    },
    email: {
      required,
      email,
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

function sendPost(title, postedText) {
  //   const imagefile = document.querySelector("#example-file-input").files[0]
  // let forms = new FormData();
  // forms.append("title", title);
  // forms.append("postedText", postedText);
  // forms.append("imgurl", imagefile);
  // let config = {
  //   headers: {
  //     "Content-Type": "multipart/form-data",
  //   },
  // };

  axios
    .postForm(`http://${url}/PostNew`, {
      title: title,
      postImage: document.querySelector("#example-file-input").files[0],
      postedText: postedText,
    })
    .then((res) => {
      console.log(res);
      Swal.fire({
        // title: "Auto close alert!",
        text: "發表成功",
        timer: 500,
        icon: "success",
      });
      window.setTimeout(function () {
        window.location.href = "http://localhost:8080/#/post";
      }, 1000);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}
</script>

<template>
  <!-- Hero -->

  <BasePageHeading title="發表食記" subtitle="測試看看">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-cart-shopping">分類</i>
            </a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <a class="link-fx" href="#/backend/cart/productInfo">
              <i class="fa fa-leaf">分類</i></a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-leaf">分類</i>
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <div class="row">
      <div class="col-lg-9">
        <!-- Basic -->
        <form @submit.prevent="onSubmit" method="post" enctype="multipart/form-data">
          <BaseBlock title=" " content-full>
            <div class="row push">
              <div class="col-lg-12">
                <p class="fs-sm text-muted"></p>
              </div>
              <div class="col-lg-4 col-xl-12">
                <!-- 商品名稱開始 -->
                <div class="mb-4">
                  <label class="form-label" for="val-username">文章標題 <span class="text-danger">*</span></label>
                  <input type="text" id="val-username" class="form-control" :class="{
                    'is-invalid': v$.title.$errors.length,
                  }" v-model="state.title" @blur="v$.title.$touch" />
                  <div v-if="v$.title.$errors.length" class="invalid-feedback animated fadeIn">
                    請輸入文章標題
                  </div>
                </div>

                <!-- 素食種類開始 -->
                <div class="mb-4">
                  <label class="form-label" for="example-select">文章分類</label>
                  <select class="form-select" id="example-select" name="example-select" required :class="{
                    'is-invalid': v$.category.$errors.length,
                  }" @blur="v$.category.$touch" v-model="state.category">
                    <option v-for="(option, index) in options" :value="option.value" :key="`option-${index}`">
                      {{ option.text }}
                    </option>
                  </select>
                  <div v-if="v$.category.$errors.length" class="invalid-feedback animated fadeIn">
                    請選擇文章分類!
                  </div>
                </div>

                <!-- 圖片上傳開始-->
                <div class="row push">
                  <div class="col-lg-12 col-xl-12 overflow-hidden">
                    <div class="mb-4">
                      <label class="form-label" for="example-file-input">圖片上傳（一張）</label>
                      <input class="form-control" type="file" id="example-file-input" name="file" />
                    </div>
                  </div>
                </div>

                <!-- 產品CK editor -->
                <div class="mb-4">
                  <label class="form-label" for="example-select">文章內文</label>
                  <ckeditor :editor="ClassicEditor" :config="editorConfig" v-model="resPostText" />
                </div>
                <div class="row items-push">
                  <div class="col-lg-6 offset-lg-5">
                    <button type="submit" class="btn btn-alt-primary" @click="sendPost(state.title, resPostText)">
                      送出文章
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </BaseBlock>
          <!-- END Basic -->
        </form>
      </div>
    </div>
  </div>
</template>
