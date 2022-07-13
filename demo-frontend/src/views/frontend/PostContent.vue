<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
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

//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = "warning";
//接收的資料ref
const resData = ref();

const resPostId = ref();
const resPostTitle = ref();
const resPostText = ref();
const resPostDate = ref();
const resPostImgurl = ref();
const resfavData = ref();
const reslikeData = ref();
const resLikeCount = ref();
const resPostStatus = ref("待審核");

const route = useRoute();
const user = JSON.parse(window.localStorage.getItem("access-admin"));
const userId = user.data.user.userId
const userName = user.data.user.userName
let postId = route.params.postId;

const getAxios = function () {
  axios
    .get(`http://${url}/auditPost/${postId}`)
    //.get(`http://${url}/postStatusList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      resPostId.value = res.data.postId;
      resPostTitle.value = res.data.title;
      resPostText.value = res.data.postedText;
      resPostDate.value = res.data.postedDate;
      resPostImgurl.value = res.data.imgurl;
      resLikeCount.value = res.data.likeCount;
      console.log(res);
     
    })
    .catch((error) => {
      console.log(error, "失敗");
    });

    if(userId == null){
      //window.location.href = "http://localhost:8080/#/signin";
    }else{
    axios
    .get(`http://${url}/favtest/${postId}/${userId}`)
    //.get(`http://${url}/postStatusList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
       resfavData.value = res.data;
      console.log(res.data);
     
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
    axios
    .get(`http://${url}/liketest/${postId}/${userId}`)
    //.get(`http://${url}/postStatusList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
       reslikeData.value = res.data;
      console.log(res.data);
     
    })
    .catch((error) => {
      console.log(error, "失敗");
    });

  }
};
//執行Axios
getAxios();

function addfavpost(){

  if(userId == null){
    window.location.href = "http://localhost:8080/#/signin";
  }else{
  axios
    .post(`http://${url}/favtest/${postId}/${userId}`,{
    })
    .then((res) => {
      getAxios();
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
  }
}

function addlikepost(){

  if(userId == null){
    window.location.href = "http://localhost:8080/#/signin";
  }else{
  axios
    .post(`http://${url}/liketest/${postId}/${userId}`,{
    })
    .then((res) => {
      getAxios();
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
  }
}

function delfavpost(){
  axios
    .delete(`http://${url}/favtest/${postId}/${userId}`)
    .then((res) => {
      getAxios();
    })
    .catch((error) => {
      console.log(error, "失敗");
    });


}

function dellikepost(){
  axios
    .delete(`http://${url}/liketest/${postId}/${userId}`)
    .then((res) => {
      getAxios();
    })
    .catch((error) => {
      console.log(error, "失敗");
    });


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
  color: #83cfdd;
}
</style>

<template>
  <!-- Hero -->
  <!-- Page Content -->
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-8">
          <BaseBlock title=" " header-class="bg-flat-light" themed>
            <h3>{{ resPostTitle }}</h3>
            <button
              type="button"
              class="btn rounded-pill btn btn-outline-info me-1 mb-3"
            >
              <i class="bi bi-twitter"></i>
            </button>
            <button
              type="button"
              class="btn rounded-pill btn btn-outline-info me-1 mb-3"
            >
              <i class="bi bi-facebook"></i>
            </button>
            <button
              type="button"
              class="btn rounded-pill btn btn-outline-info me-1 mb-3"
            >
              <i class="bi bi-printer"></i>
            </button>
            <button
              type="button"
              class="btn rounded-pill btn btn-outline-info me-1 mb-3"
            >
              <i class="bi bi-link-45deg"></i>
            </button>
            <button
              type="button"
              class="btn rounded-pill btn btn-alt-warning me-1 mb-3 float-end"
              @click="addfavpost()"
              v-if="!resfavData"
            >
              <i class="bi bi-bookmark-star"></i>
              收藏文章
            </button>
            <button
              type="button"
              class="btn rounded-pill btn btn-alt-secondary me-1 mb-3 float-end"
              @click="delfavpost()"
              v-if="resfavData"
            >
              <i class="bi bi-bookmark-star"></i>
              已收藏文章
            </button>
            
            <span>{{ resPostDate }}</span>
            <button
              type="button"
              class="btn rounded-pill btn btn-alt-warning me-1 mb-3 float-end"
              @click="addlikepost()"
              v-if="!reslikeData"
            >
              <i class="bi bi-hand-thumbs-up"></i>
              讚
            </button>
            <button
              type="button"
              class="btn rounded-pill btn btn-alt-secondary me-1 mb-3 float-end"
              v-if="reslikeData"
              @click="dellikepost()"
            >
              <i class="bi bi-hand-thumbs-up"></i>
              已按讚
            </button>

            <div class="row">
              <div class="col-md-8">
                 <img :src="resPostImgurl"
                  class="img-fluid img-thumbnail"
                  alt="..."
                />
              </div>
              <ul class="nav-items fs-sm col-md-4">
                <li>
                  <a class="d-flex py-2" href="javascript:void(0)">
                    <div
                      class="flex-shrink-0 me-3 ms-2 overlay-container overlay-bottom"
                    >
                      <img
                        class="img-avatar img-avatar48"
                        src="/assets/media/avatars/avatar6.jpg"
                        alt=""
                      />
                    </div>
                    <div class="flex-grow-1">
                      <div class="fw-semibold">Laura Carr</div>
                      <div class="fw-normal text-muted">Copywriter</div>
                    </div>
                  </a>
                  <br>
                </li>
                 <div class =float-end  v-if="resLikeCount>0">
                  <u>有{{resLikeCount}}人覺得這很讚<i class="bi bi-hand-thumbs-up"></i></u>
                  </div>
                  <div class =float-end v-else>
                  <mark>趕快來當第一個按讚的人<i class="bi bi-hand-thumbs-up"></i></mark>
                  </div>
              </ul>
              

              <h5 class="page-header"><hr SIZE="5px" /></h5>

              <div class="text" v-html="resPostText"></div>
            </div>
          </BaseBlock>
        </div>

        <div class="col-md-4">
          <BaseBlock title="最新分享" header-class="bg-flat-light" themed>
            <ul class="nav-items fs-sm">
              <li>
                <a class="d-flex py-2" href="javascript:void(0)">
                  <div
                    class="flex-shrink-0 me-3 ms-2 overlay-container overlay-bottom col-md-4"
                  >
                    <img
                      src="https://cdn.barnimages.com/wp-content/uploads/2021/10/20211001-barnimages-8-740x493@2x.jpg"
                      class="img-thumbnail"
                      alt="..."
                    />
                  </div>
                  <div class="flex-grow-1 col-md-8">
                    <div class="fw-semibold">炸蝦</div>
                    <div class="fw-normal text-muted">好吃</div>
                  </div>
                </a>
              </li>
              <li>
                <a class="d-flex py-2" href="javascript:void(0)">
                  <div
                    class="flex-shrink-0 me-3 ms-2 overlay-container overlay-bottom col-md-4"
                  >
                    <img
                      src="https://cdn.barnimages.com/wp-content/uploads/2021/10/20211001-barnimages-8-740x493@2x.jpg"
                      class="img-thumbnail"
                      alt="..."
                    />
                  </div>
                  <div class="flex-grow-1 col-md-8">
                    <div class="fw-semibold">炸蝦</div>
                    <div class="fw-normal text-muted">好吃</div>
                  </div>
                </a>
              </li>
            </ul>
          </BaseBlock>
          <BaseBlock title="你可能也會喜歡" header-class="bg-flat-light" themed>
            <ul class="nav-items fs-sm">
              <li>
                <a class="d-flex py-2" href="javascript:void(0)">
                  <div
                    class="flex-shrink-0 me-3 ms-2 overlay-container overlay-bottom col-md-4"
                  >
                    <img
                      src="https://cdn.barnimages.com/wp-content/uploads/2021/10/20211001-barnimages-8-740x493@2x.jpg"
                      class="img-thumbnail"
                      alt="..."
                    />
                  </div>
                  <div class="flex-grow-1 col-md-8">
                    <div class="fw-semibold">炸蝦</div>
                    <div class="fw-normal text-muted">好吃</div>
                  </div>
                </a>
              </li>
              <li>
                <a class="d-flex py-2" href="javascript:void(0)">
                  <div
                    class="flex-shrink-0 me-3 ms-2 overlay-container overlay-bottom col-md-4"
                  >
                    <img
                      src="https://cdn.barnimages.com/wp-content/uploads/2021/10/20211001-barnimages-8-740x493@2x.jpg"
                      class="img-thumbnail"
                      alt="..."
                    />
                  </div>
                  <div class="flex-grow-1 col-md-8">
                    <div class="fw-semibold">炸蝦</div>
                    <div class="fw-normal text-muted">好吃</div>
                  </div>
                </a>
              </li>
            </ul>
          </BaseBlock>
        </div>
      </div>
    </div>
  </div>

  <!-- END Page Content -->
</template>
