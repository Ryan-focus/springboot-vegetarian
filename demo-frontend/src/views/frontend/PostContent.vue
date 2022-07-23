<script setup>
import { useTemplateStore } from "@/stores/template";
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref, inject, reactive } from "vue";
import axios from "axios";
import "bootstrap-icons/font/bootstrap-icons.css";
import { useRoute } from "vue-router";

// Vue Dataset, for more info and examples you can check out https://github.com/kouts/vue-dataset/tree/next
import {
  Dataset,
  DatasetItem,
  DatasetInfo,
  DatasetPager,
  DatasetSearch,
  DatasetShow,
} from "vue-dataset";
//import func from "../../../vue-temp/vue-editor-bridge";

// Main store
const store = useTemplateStore();
//預設傳值伺服器與[params]
const url = "localhost:8088";

//接收的資料ref
const resData = ref();

const resPostTitle = ref();
const resPostText = ref();
const resPostDate = ref();
const resPostImgurl = ref();
const resfavData = ref();
const reslikeData = ref();
const resLikeCount = ref();
var category = ref();
var categoryNumber = ref(null);

const resWriterName = ref();
const writerId = ref();
const resUserPic = ref();

const newData = ref();
const newfData = ref();

const likeData = ref();

const route = useRoute();
let postId = route.params.postId;

var uid = 0;
const user = JSON.parse(window.localStorage.getItem("access-user"));
if (user != null) {
  uid = user.data.user.userId;
}

const getAxios = function () {
  axios
    .get(`http://${url}/auditPost/${postId}`)
    //.get(`http://${url}/postStatusList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      resPostTitle.value = res.data.title;
      resPostText.value = res.data.postedText;
      resPostDate.value = res.data.postedDate;
      resPostImgurl.value = res.data.imgurl;
      resLikeCount.value = res.data.likeCount;
      category.value = res.data.postCategory;
      writerId.value = res.data.userId;
    })
    .then((res) => {
      var array = ["全素", "蛋素", "奶素", "蛋奶素", "植物五辛素"];
      //console.log("取全部LIST 之後 抓值 category=" + category.value);
      var count = 0;
      array.forEach((element) => {
        count++;
        if (category.value == element) {
          categoryNumber.value = count;
        }
      });
      //console.log("讀取完迴圈之後categoryNumber=" + categoryNumber.value);
      axios
        .get(`http://${url}/postCategory${JSON.parse(categoryNumber.value)}`)
        .then((res) => {
          //獲取伺服器的回傳資料
          newfData.value = res.data;
          //console.log("get素食種類 " + res);
        });
    })
    .then((res) => {
      axios.get(`http://${url}/users/${writerId.value}`).then((res) => {
        //獲取伺服器的回傳資料
        resWriterName.value = res.data.userName;
        resUserPic.value = res.data.userPic;
        console.log(res);
      });
    })
    .catch((error) => {
      console.log(error, "失敗");
    });

  axios
    .get(`http://${url}/postStatusList/`)
    .then((res) => {
      //獲取伺服器的回傳資料
      newData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });

  axios
    .get(`http://${url}/findlike/`)
    .then((res) => {
      //獲取伺服器的回傳資料
      likeData.value = res.data;

      // console.log(res);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });

  axios
    .get(`http://${url}/favtest/${postId}/${uid}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resfavData.value = res.data;
      console.log(res.data);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
  axios
    .get(`http://${url}/liketest/${postId}/${uid}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      reslikeData.value = res.data;
      console.log(res.data);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//執行Axios
getAxios();

function addfavpost() {
  if (uid == 0) {
    window.location.href = "http://localhost:8080/#/signin";
  } else {
    axios
      .post(`http://${url}/favtest/${postId}/${uid}`, {})
      .then((res) => {
        getAxios();
      })
      .catch((error) => {
        console.log(error, "失敗");
      });
  }
}

function addlikepost() {
  if (uid == 0) {
    window.location.href = "http://localhost:8080/#/signin";
  } else {
    axios
      .post(`http://${url}/liketest/${postId}/${uid}`, {})
      .then((res) => {
        getAxios();
      })
      .catch((error) => {
        console.log(error, "失敗");
      });
  }
}

function delfavpost() {
  axios
    .delete(`http://${url}/favtest/${postId}/${uid}`)
    .then((res) => {
      getAxios();
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

function dellikepost() {
  axios
    .delete(`http://${url}/liketest/${postId}/${uid}`)
    .then((res) => {
      getAxios();
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

function print() {
  window.print();
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

.text {
  white-space: pre-wrap;
  word-wrap: break-word;
}

u {
  background-color: white;
  color: black;
}

#newPostSide,
#newfPostSide {
  width: 100px;
}

.textend {
  height: 100px;
}

.like {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
}
</style>

<template>
  <!-- Hero -->
  <!-- Page Content -->
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-8" id="print">
          <BaseBlock title=" " header-class="bg-flat-light" themed>
            <h3>{{ resPostTitle }}</h3>
            <button type="button" class="btn rounded-pill btn btn-outline-info me-1 mb-3">
              <i class="bi bi-twitter"></i>
            </button>
            <button type="button" class="btn rounded-pill btn btn-outline-info me-1 mb-3">
              <i class="bi bi-facebook"></i>
            </button>
            <button type="button" class="btn rounded-pill btn btn-outline-info me-1 mb-3" @click="print()">
              <i class="bi bi-printer"></i>
            </button>
            <button type="button" class="btn rounded-pill btn btn-outline-info me-1 mb-3">
              <i class="bi bi-link-45deg"></i>
            </button>

            <button type="button" class="btn rounded-pill btn btn-alt-warning me-1 mb-3 float-end" @click="addfavpost()"
              v-if="!resfavData">
              <i class="bi bi-bookmark-star"></i>
              收藏文章
            </button>
            <button type="button" class="btn rounded-pill btn btn-alt-secondary me-1 mb-3 float-end"
              @click="delfavpost()" v-if="resfavData">
              <i class="bi bi-bookmark-star"></i>
              已收藏文章
            </button>

            <span>{{ resPostDate }}</span>
            <button type="button" class="btn rounded-pill btn btn-alt-warning me-1 mb-3 float-end"
              @click="addlikepost()" v-if="!reslikeData">
              <i class="bi bi-hand-thumbs-up"></i>
              讚
            </button>
            <button type="button" class="btn rounded-pill btn btn-alt-secondary me-1 mb-3 float-end" v-if="reslikeData"
              @click="dellikepost()">
              <i class="bi bi-hand-thumbs-up"></i>
              已按讚
            </button>

            <div class="row">
              <div class="col-md-8">
                <img :src="resPostImgurl" class="img-fluid img-thumbnail" alt="..." />
              </div>
              <ul class="nav-items fs-sm col-md-4">
                <li>
                  <a class="d-flex py-2" href="javascript:void(0)">
                    <div class="
                        flex-shrink-0
                        me-3
                        ms-2
                        overlay-container overlay-bottom
                      ">
                      <img :src="`data:image/png;base64,${resUserPic}`" class="img-avatar img-avatar48" />
                    </div>
                    <div class="flex-grow-1">
                      <div class="fw-semibold" style="font-size: 18px">
                        {{ resWriterName }}
                      </div>
                    </div>
                  </a>
                  <br />
                </li>
                <div class="float-end" v-if="resLikeCount > 0">
                  <u>有{{ resLikeCount }}人覺得這很讚<i class="bi bi-hand-thumbs-up"></i></u>
                </div>
                <div class="float-end" v-else>
                  <mark>趕快來當第一個按讚的人<i class="bi bi-hand-thumbs-up"></i></mark>
                </div>
              </ul>

              <h5 class="page-header">
                <hr SIZE="5px" />
              </h5>

              <div class="text" v-html="resPostText"></div>
              <div class="textend"></div>
            </div>
          </BaseBlock>
          <BaseBlock title="最多人喜歡" header-class="bg-flat-light" themed>
            <div v-if="likeData" class="row">
              <div v-for="(item, index) in likeData.slice(0, 3)" :key="index" class="col-md-4">
                <a class="" :href="'/#/postContent/' + item.postId">
                  <div class="
                      flex-shrink-0
                      me-3
                      ms-2
                      overlay-container overlay-bottom
                      col-md-12
                    ">
                    <img :src="item.imgurl" class="img-thumbnail" alt="..." />
                  </div>
                  <br />
                  <div id="like" class="flex-grow-1 col-md-12">
                    <div class="like">{{ item.title }}</div>
                    <div class="fw-normal text-muted">
                      <br />
                    </div>
                  </div>
                </a>
              </div>
            </div>
          </BaseBlock>
        </div>

        <div class="col-md-4">
          <BaseBlock title="按讚排行榜" header-class="bg-flat-light" themed>
            <ul class="nav-items fs-sm" v-if="likeData">
              <li v-for="(item, index) in likeData.slice(0, 5)" :key="index">
                <a class="d-flex py-2" :href="'/#/postContent/' + item.postId">
                  <div class="col-md-1">
                    {{ index + 1 }}
                  </div>
                  <div class="
                      flex-shrink-0
                      me-3
                      ms-2
                      overlay-container overlay-bottom
                      col-md-4
                    ">
                    <img :src="item.imgurl" class="img-thumbnail" alt="..." />
                  </div>

                  <div id="newPostSide" class="flex-grow-1 col-md-8">
                    <div class="newPostSide">{{ item.title }}</div>
                    <div class="fw-normal text-muted"></div>
                  </div>
                </a>
              </li>
            </ul>
          </BaseBlock>
          <BaseBlock title="最新分享" header-class="bg-flat-light" themed>
            <ul class="nav-items fs-sm" v-if="newData">
              <li v-for="(item, index) in newData.slice(0, 5)" :key="index">
                <a class="d-flex py-2" :href="'/#/postContent/' + item.postId">
                  <div class="
                      flex-shrink-0
                      me-3
                      ms-2
                      overlay-container overlay-bottom
                      col-md-4
                    ">
                    <img :src="item.imgurl" class="img-thumbnail" alt="..." />
                  </div>
                  <div id="newPostSide" class="flex-grow-1 col-md-8">
                    <div class="newPostSide">{{ item.title }}</div>
                    <div class="fw-normal text-muted"></div>
                  </div>
                </a>
              </li>
            </ul>
          </BaseBlock>
          <BaseBlock title="你可能也會喜歡" header-class="bg-flat-light" themed>
            <ul class="nav-items fs-sm">
              <li v-for="(item, index) in newfData" :key="index">
                <a class="d-flex py-2" :href="'/#/postContent/' + item.postId">
                  <div class="
                      flex-shrink-0
                      me-3
                      ms-2
                      overlay-container overlay-bottom
                      col-md-4
                    ">
                    <img :src="item.imgurl" class="img-thumbnail" alt="..." />
                  </div>
                  <div class="flex-grow-1 col-md-8" id="newfPostSide">
                    <div class="newPostSide">{{ item.title }}</div>
                    <div class="fw-normal text-muted"></div>
                  </div>
                </a>
              </li>
            </ul>
          </BaseBlock>
        </div>
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
