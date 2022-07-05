<style>
.ck-editor__editable {
  min-height: 400px;
}
</style>
<script setup>
import { ref, reactive, computed, onBeforeUnmount } from "vue";

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
import Dropzone from "dropzone";

// Dropzone variables
const dropzone = ref(null);

// Init Dropzone when content is loaded
// onMounted(() => {
//   dropzone.value = new Dropzone("#dropzoneForm", {
//     url: "https://httpbin.org/post",
//   });

//   dropzone.value.on("addedfile", (file) => {
//     console.log(`File added: ${file.name}`);
//   });
// });

// Detroy dropzone instance before leaving the page
// onBeforeUnmount(() => {
//   dropzone.value.destroy();
// });

// Dropzone + Custom overrides
// @import "dropzone/dist/dropzone.css";
// @import "@/assets/scss/vendor/dropzone";

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
              <i class="fa fa-leaf">分類</i></a
            >
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
      <div class="col-lg-8">
        <!-- Basic -->
        <form @submit.prevent="onSubmit">
          <BaseBlock title=" " content-full>
            <div class="row push">
              <div class="col-lg-12">
                <p class="fs-sm text-muted">請盡量填寫完整資訊</p>
              </div>
              <div class="col-lg-4 col-xl-12">
                <!-- 商品名稱開始 -->
                <div class="mb-4">
                  <label class="form-label" for="val-username"
                    >文章標題 <span class="text-danger">*</span></label
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
                  />
                  <div
                    v-if="v$.username.$errors.length"
                    class="invalid-feedback animated fadeIn"
                  >
                    請輸入文章標題
                  </div>
                </div>

                <!-- 素食種類開始 -->
                <div class="mb-4">
                  <label class="form-label" for="example-select"
                    >選擇文章分類</label
                  >
                  <select
                    class="form-select"
                    id="example-select"
                    name="example-select"
                  >
                    <option selected>請選擇</option>
                    <option value="全素">全素</option>
                    <option value="蛋素">蛋素</option>
                    <option value="奶素">奶素</option>
                    <option value="蛋奶素">蛋奶素</option>
                    <option value="五辛素">五辛素</option>
                  </select>
                </div>

                <!-- 圖片上傳開始-->
                <div class="row push">
                  <div class="col-lg-12 col-xl-12 overflow-hidden">
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
                <!-- Example -->
                <h2 class="content-heading border-bottom mb-4 pb-2">
                  Asynchronous File Uploads
                </h2>
                <div class="row push">
                  <div class="col-lg-4">
                    <p class="fs-sm text-muted">
                      Drag and drop sections for your file uploads
                    </p>
                  </div>
                  <div class="col-lg-8 col-xl-6">
                    <form id="dropzoneForm" class="dropzone"></form>
                  </div>
                </div>
                <!-- END Example -->
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
          <!-- END Basic -->
        </form>
      </div>
      <div class="col-md-4">
        <BaseBlock title="Title" subtitle="Subtitle" :rounded="false" bordered>
          <p>Bordered block with header background..</p>
        </BaseBlock>
      </div>
    </div>
  </div>
</template>
