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
import ClassicEditor from "@ckeditor/ckeditor5-build-classic/build/ckeditor.js";

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
    forumTitle: null,
    forumContent: null,
    forumCategory: null,
    forumImage: null,
    forumCreateTime: null,
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

// Validation rules
const rules = computed(() => {
    return {
        forumTitle: {
            required,
            minLength: minLength(1),
        },
        forumContent: {
            required,
            decimal,
        },
        forumCategory: {
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

function addForum() {
    state.forumCategory = "健康"
    state.forumTitle = "介紹新加坡兵役系統中「素食兵」的飲食"
    state.forumContent = "<p>新加坡政府剛在過去的周末宣布，今年將慶祝國民服役55週年。</p> <p>身邊許多未服過兵役的本地和外國友人總是很好奇，筆者從小就吃素長大，在服兵役時會不會體力不足？能攝取足夠營養嗎？軍訓時要「上山下海越過叢林」，會不會跑到一半就沒力？</p> <p>其實，這些都是人們對吃素的刻板印象。</p> <div>作為「素食兵」的我最近回營受訓了一趟，帶大家一起看看戰備軍人回營受訓時，能吃到什麼樣的「素食好料」。據說，每名軍人的每一餐，花費約在6至7新元左右。</div> <p>基本款「三菜一飯」</p> <p>素食兵的餐食每天都會有三樣菜餚，午餐和晚餐的主食一般上都是白飯。</p> <div>早餐則可能會吃到米粉、炒麵或是包子和餡餅等素餡點心。</div> <p>看到這張「餐圖」，大家可能會覺得賣相不怎麼樣，但其實味道還算不錯。除了有蒸豆腐、炒豌豆來補充蛋白質，營中的素食菜單也常會出現一種俗稱「醃鴨」的古早素料。醃鴨屬麵筋類，照片中的醃鴨以酸酸甜甜的醬料烹煮，而且十分「多汁」，淋在白飯上搭配著吃，就不會感覺「乾巴巴」。</p>"
}

function createForum() {
    toast
        .fire({
            title: "確定要新增文章嗎?",
            text: "",
            icon: "warning",
            showCancelButton: true,
            customClass: {
                confirmButton: "btn btn-danger m-1",
                cancelButton: "btn btn-secondary m-1",
            },
            confirmButtonText: "新增資料",
            cancelButtonText: "繼續編輯",

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
                const forum = {
                    forumTitle: state.forumTitle,
                    forumContent: state.forumContent,
                    forumCategory: state.forumCategory,
                    forumCreateTime: state.forumCreateTime,
                    forumImage: image.value.imageUrl,
                };
                axios
                    .post("http://localhost:8088/forums", forum)
                    .then(() => {
                        window.location = "#/backend/forums/ForumsInfo";
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

    <BasePageHeading title="文章管理" subtitle="新增文章">
        <template #extra>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb breadcrumb-alt">
                    <li class="breadcrumb-item">
                        <a class="link-fx" href="#/backend/forums/dashboard">
                            <i class="fa fa-cart-shopping"></i> 文章管理</a>
                    </li>
                    <li class="breadcrumb-item" aria-current="page">
                        <a class="link-fx" href="#/backend/forums/forumInfo">
                            <i class="fa fa-leaf"></i> 文章管理</a>
                    </li>
                    <li class="breadcrumb-item" aria-current="page">
                        <i class="fa fa-leaf"></i> 新增文章
                    </li>
                </ol>
            </nav>
        </template>
    </BasePageHeading>
    <!-- END Hero -->

    <!-- Page Content -->
    <div class="content">
        <!-- Basic -->
        <form @submit.prevent="onSubmit" @submit="createForum" id="forms">
            <BaseBlock title="Validation Form" content-full>
                <div class="row push">
                    <div class="col-lg-4">
                        <p class="fs-sm text-muted">新增文章</p>
                    </div>
                    <div class="col-lg-8 col-xl-5">
                        <!-- 分類 -->
                        <div class="mb-4">
                            <label class="form-label" for="example-select">請選擇發布類別</label>
                            <select class="form-select" id="example-select" name="example-select" htmlEscape="false"
                                v-model="state.forumCategory">
                                <option value="環保">環保</option>
                                <option value="養身">養身</option>
                                <option value="公益">公益</option>
                                <option value="健康">健康</option>
                            </select>
                        </div>
                        <!-- 文章標題 -->
                        <div class="mb-4">
                            <label class="form-label" for="val-forumTitle">文章標題 <span
                                    class="text-danger">*</span></label>
                            <input type="text" id="val-forumTitle" class="form-control" :class="{
                                'is-invalid': v$.forumTitle.$errors.length,
                            }" v-model="state.forumTitle" @blur="v$.forumTitle.$touch" />
                            <div v-if="v$.forumTitle.$errors.length" class="invalid-feedback animated fadeIn">
                                請輸入標題
                            </div>
                        </div>
                        <div class="mb-4">
                            <label class="form-label" for="example-select">請輸入文章</label>
                            <ckeditor :editor="ClassicEditor" :config="editorConfig" v-model="state.forumContent" />
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


                        <div class="row items-push">
                            <div class="col-lg-7 offset-lg-4">
                                <button class="btn btn-alt-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </BaseBlock>
        </form>
        <button class="btn btn-alt-secondary" @click="addForum()">一鍵日誌</button>
        <!-- END Basic -->
    </div>
</template>
