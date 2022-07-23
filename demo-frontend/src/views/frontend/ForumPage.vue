<script setup>
import { ref, reactive, computed, onMounted } from "vue";
import Swal from "sweetalert2";
import { useRoute } from "vue-router";
import axios from "axios";
// 已經宣告但從未使用過的Value (請勿刪除)
import { useTemplateStore } from "@/stores/template";
// Vue Dataset, for more info and examples you can check out https://github.com/kouts/vue-dataset/tree/next
import {
    Dataset,
    DatasetItem,
    DatasetInfo,
    DatasetPager,
    DatasetSearch,
    DatasetShow,
} from "vue-dataset";

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


// Main store
const store = useTemplateStore();
//預設傳值伺服器與[params]
const url = "localhost:8088";
//接收的資料ref
const resData = ref();
const forumCategory = ref();
const resForumId = ref();
const resForumTitle = ref();
const resForumContent = ref();
const resForumCategory = ref();
const resForumImage = ref();
const route = useRoute();
let forumId = route.params.forumId;

//用reactive會無法及時反應所以用ref另外宣告
const image = ref({
    imageUrl: null,
});

const getAxios = function () {
    axios
        .get(`http://${url}/forums/${forumId}`)
        .then((res) => {

            //獲取伺服器的回傳資料res.data.欄位名稱
            resData.value = res.data;
            resForumId.value = res.data.forumId;
            resForumTitle.value = res.data.forumTitle;
            resForumContent.value = res.data.forumContent;
            resForumCategory.value = res.data.forumCategory;
            resForumImage.value = res.data.forumImage;
            console.log(res);
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
};
//執行Axios

getAxios();

function showForum() {
    axios
        .get(`http://${url}/forums/${number}`)
        .then((res) => {
            //獲取伺服器的回傳資料
            console.log(res);
            resForumId.value = res.data.forumId;
            resForumTitle.value = res.data.forumTitle;
            resForumContent.value = res.data.forumContent;
            resForumCategory.value = res.data.forumCategory;
            resForumImage.value = res.data.imageUrl;
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
}

</script>
<template>
    <div id="one-vue-hero" class="bg-body-extra-light">

        <div class="content content-full text-center">
            <!-- <div class="my-1"> -->
            <h1 class="h1 text-white mb-3"><b><a href="#/forumIndex"> <img
                            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTC9ySsr0z9yrEKhnlg5Tns5vt6vqiGP8dsA&usqp=CAU"
                            alt=""></a></b></h1>
            <h3>
                <!-- <span class="text-white-75"><b>從超過 50 家精選餐廳中，探索您不知道的熱門素食。</b></span> -->
            </h3>

            <div class="row">
                <BaseBlock class="overflow-hidden">
                    <template #content>
                        <ul class="nav nav-tabs nav-tabs-block" role="tablist">
                            <li class="list-group-item list-group-item-success" style="width: auto;">
                                <button type="button" class="btn btn-outline-success btn-block" id="search-projects-tab"
                                    data-bs-toggle="tab" data-bs-target="#search-projects" role="tab"
                                    aria-controls="search-projects" aria-selected="true"
                                    style="float:left;width: 1176px;">
                                    {{ resForumTitle }}
                                </button>
                            </li>
                        </ul>
                    </template>
                </BaseBlock>
            </div>


            <!-- Page Content -->
            <div class="content">
                <BaseBlock class="text-center">
                    <tr>
                        <td class="col-md-6 col-lg-4 col-xl-3">
                            <div class="img1">
                                <a :title="`${resForumTitle}`">
                                    <img class="img-fluid" :src="`${resForumImage}`" :alt="`${resForumTitle}`" />
                                </a>
                            </div>
                            <p class="d-none d-sm-block text-muted">
                            <p v-html="resForumContent"></p>

                            <!-- {{ resForumContent }} -->
                            </p>
                        </td>
                    </tr>
                </BaseBlock>
            </div>
        </div>
    </div>
    <!-- END Page Content -->
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

<style>
.img1 {
    width: 200px;
    height: 200px;
    display: block;
    margin: auto;
}

.img-fluid {
    width: 100%;
    height: 100%;
}
</style>
