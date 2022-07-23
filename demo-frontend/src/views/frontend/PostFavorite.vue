<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";
import "bootstrap-icons/font/bootstrap-icons.css";
import { useRoute } from "vue-router";
import "sweetalert2/dist/sweetalert2.min.css";
import Swal from "sweetalert2";
import { useRouter } from "vue-router";

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
const resMyData = ref();
const resMyDataNoAudit = ref();
const resMyDataNoPass = ref();

const resPostCategory = ref();

const route = useRoute();
const router = useRouter();

var uid = 0;
const user = JSON.parse(window.localStorage.getItem("access-user"));
if (user != null) {
  uid = user.data.user.userId;
  //userName = user.data.user.userName;
}

let postId = route.params.postId;

let toast = Swal.mixin({
  buttonsStyling: false,
  target: "#page-container",
  customClass: {
    confirmButton: "btn btn-success m-1",
    cancelButton: "btn btn-danger m-1",
    input: "form-control",
  },
});

const getAxios = function () {
  //發布中文章
  axios
    .get(`http://${url}/showPost/${uid}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resMyData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });

  //待審核文章
  axios
    .get(`http://${url}/showPostNoAudit/${uid}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resMyDataNoAudit.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    })

    .then((res) => {
      //未通過文章
      axios
        .get(`http://${url}/showPostNoPass/${uid}`)
        .then((res) => {
          //獲取伺服器的回傳資料
          resMyDataNoPass.value = res.data;
        })
        .catch((error) => {
          console.log(error, "失敗");
        });
    })
    .then((res) => {
      //收藏文章
      axios
        .get(`http://${url}/favoritePost/${uid}`)
        .then((res) => {
          //獲取伺服器的回傳資料
          resData.value = res.data;
          resPostCategory.value = res.data.postCategory;
        })
        .catch((error) => {
          console.log(error, "失敗");
        });
    });
};

//執行Axios
getAxios();

//Delete Restaurant Fuction
function deletePost(number) {
  toast
    .fire({
      title: "確定要刪除嗎?",
      text: "刪除之後這筆資料就消失囉~!",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "刪除",
      cancelButtonText: "取消",

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
        axios
          .get(`http://${url}/deletePost/${number}`)
          .then((res) => {
            //獲取伺服器的回傳資料
            console.log(res);

            getAxios();
            toast.fire("刪除成功", "", "success");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      }
    });
}

function findPost(id) {
  //send request to server
  console.log(id);
  router.push({ name: "postEditFront", params: { postId: id } });
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

#newPostSide,
#newfPostSide {
  width: 100px;
}

#posttext {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  white-space: normal;
}

.box {
  display: flex;
}

.date1 {
  align-items: center;
  height: 2em;
  line-height: 2em;
}
</style>

<template>
  <!-- Hero -->
  <!-- Page Content -->
  <div class="content">
    <div class="container-fluid">
      <div>
        <router-link to="./postCreate">
          <button type="button" class="btn btn-info">發表文章</button>
        </router-link>
      </div>
      <br />
      <div class="row">
        <div class="col-lg-12">
          <!-- Vertical Block Tabs Default Style -->
          <BaseBlock class="row g-0">
            <template #content>
              <ul class="nav nav-tabs nav-tabs-block flex-md-column col-md-2" role="tablist">
                <li class="nav-item d-md-flex flex-md-column">
                  <button class="nav-link text-md-start active" id="btabs-vertical-home-tab" data-bs-toggle="tab"
                    data-bs-target="#btabs-vertical-home" role="tab" aria-controls="btabs-vertical-home"
                    aria-selected="true">
                    <i class="
                        bi bi-check2-circle
                        opacity-50
                        me-1
                        d-none d-sm-inline-block
                      "></i>
                    發布中文章
                  </button>
                </li>

                <li class="nav-item d-md-flex flex-md-column">
                  <button class="nav-link text-md-start" id="btabs-vertical-noAudit-tab" data-bs-toggle="tab"
                    data-bs-target="#btabs-vertical-noAudit" role="tab" aria-controls="btabs-vertical-noAudit"
                    aria-selected="false">
                    <i class="
                        bi bi-clipboard-check
                        opacity-50
                        me-1
                        d-none d-sm-inline-block
                      "></i>
                    待審核文章
                  </button>
                </li>
                <li class="nav-item d-md-flex flex-md-column">
                  <button class="nav-link text-md-start" id="btabs-vertical-noPass-tab" data-bs-toggle="tab"
                    data-bs-target="#btabs-vertical-noPass" role="tab" aria-controls="btabs-vertical-noPass"
                    aria-selected="false">
                    <i class="
                        bi bi-x-circle
                        opacity-50
                        me-1
                        d-none d-sm-inline-block
                      "></i>
                    未通過文章
                  </button>
                </li>
                <li class="nav-item d-md-flex flex-md-column">
                  <button class="nav-link text-md-start" id="btabs-vertical-profile-tab" data-bs-toggle="tab"
                    data-bs-target="#btabs-vertical-profile" role="tab" aria-controls="btabs-vertical-profile"
                    aria-selected="false">
                    <i class="
                        bi bi-bookmark-star
                        opacity-50
                        me-1
                        d-none d-sm-inline-block
                      "></i>
                    我的收藏文章
                  </button>
                </li>
              </ul>
              <div class="tab-content col-md-9">
                <!-- 發布中文章資料 -->
                <div class="block-content tab-pane active" id="btabs-vertical-home" role="tabpanel"
                  aria-labelledby="btabs-vertical-home-tab">
                  <ul class="nav-items fs-sm">
                    <li v-for="item in resMyData" :key="item">
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
                        <div id="newPostSide" class="flex-grow-1 col-md-12">
                          <div class="newPostSide">
                            <h6>{{ item.title }}</h6>
                          </div>
                          <div class="fw-normal text-muted" id="posttext">
                            <div v-html="item.postedText"></div>
                          </div>
                          <br />
                        </div>
                      </a>
                      <div class="box">
                        <div id="editButton" class="flex-grow-3 col-md-8">
                          <button type="button" class="btn btn-sm btn-outline-success" @click="findPost(item.postId)">
                            編輯文章
                          </button>
                          <button type="button" class="btn btn-sm btn-outline-warning" @click="deletePost(item.postId)">
                            刪除文章
                          </button>
                        </div>
                        <div class="date1">
                          發布日期：
                          {{ item.postedDate }}
                        </div>
                      </div>
                      <hr />
                    </li>
                  </ul>
                </div>
                <!-- 待審核文章資料 -->
                <div class="block-content tab-pane" id="btabs-vertical-noAudit" role="tabpanel"
                  aria-labelledby="btabs-vertical-noAudit-tab">
                  <ul class="nav-items fs-sm">
                    <li v-for="item in resMyDataNoAudit" :key="item">
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
                        <div id="newPostSide" class="flex-grow-1 col-md-12">
                          <div class="newPostSide">
                            <h6>{{ item.title }}</h6>
                          </div>
                          <div class="fw-normal text-muted" id="posttext">
                            <div v-html="item.postedText"></div>
                          </div>
                          <br />
                        </div>
                      </a>
                      <div class="box">
                        <div id="editButton" class="flex-grow-3 col-md-8">
                          <button type="button" class="btn btn-sm btn-outline-success" @click="findPost(item.postId)">
                            編輯文章
                          </button>
                          <button type="button" class="btn btn-sm btn-outline-warning" @click="deletePost(item.postId)">
                            刪除文章
                          </button>
                        </div>
                        <div class="date1">
                          發布日期：
                          {{ item.postedDate }}
                        </div>
                      </div>
                      <hr />
                    </li>
                  </ul>
                </div>
                <!-- 未通過文章資料 -->
                <div class="block-content tab-pane" id="btabs-vertical-noPass" role="tabpanel"
                  aria-labelledby="btabs-vertical-noPass-tab">
                  <ul class="nav-items fs-sm">
                    <li v-for="item in resMyDataNoPass" :key="item">
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
                        <div id="newPostSide" class="flex-grow-1 col-md-12">
                          <div class="newPostSide">
                            <h6>{{ item.title }}</h6>
                          </div>
                          <div class="fw-normal text-muted" id="posttext">
                            <div v-html="item.postedText"></div>
                          </div>
                          <br />
                        </div>
                      </a>
                      <div class="box">
                        <div id="editButton" class="flex-grow-3 col-md-8">
                          <button type="button" class="btn btn-sm btn-outline-success" @click="findPost(item.postId)">
                            編輯文章
                          </button>
                          <button type="button" class="btn btn-sm btn-outline-warning" @click="deletePost(item.postId)">
                            刪除文章
                          </button>
                        </div>
                        <div class="date1">
                          發布日期：
                          {{ item.postedDate }}
                        </div>
                      </div>
                      <hr />
                    </li>
                  </ul>
                </div>
                <!-- 收藏文章資料 -->
                <div class="block-content tab-pane" id="btabs-vertical-profile" role="tabpanel"
                  aria-labelledby="btabs-vertical-profile-tab">
                  <ul class="nav-items fs-sm">
                    <li v-for="item in resData" :key="item.postId">
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
                        <div id="newPostSide" class="flex-grow-1 col-md-12">
                          <div class="newPostSide">
                            <h6>{{ item.title }}</h6>
                          </div>
                          <div class="fw-normal text-muted" id="posttext">
                            <div v-html="item.postedText"></div>
                          </div>
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </template>
          </BaseBlock>
          <!-- END Vertical Block Tabs Default Style -->
        </div>
      </div>
    </div>
  </div>

  <!-- END Page Content -->
</template>
