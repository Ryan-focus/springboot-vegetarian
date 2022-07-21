<script setup>
import { ref, reactive, computed, onMounted } from "vue";
import Swal from "sweetalert2";

import axios from "axios";

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

//用reactive會無法及時反應所以用ref另外宣告


const getAxios = function () {
  axios
    .get(`http://${url}/forums`)
    .then((res) => {
      console.log(res);
      //獲取伺服器的回傳資料
      resData.value = res.data.results;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//執行Axios

getAxios();




// Helper variables
//在這邊去設定Table :th的欄位名稱
const cols = reactive([
  {
    name: "標題",
    field: "forumTitle",
    sort: "",
  },
  {
    name: "文章",
    field: "forumContent",
    sort: "",
  },
  {
    name: "文章分類",
    field: "forumCategory",
    sort: "",
  },
  {
    name: "文章圖片",
    field: "forumImage",
    sort: "",
  },
  {
    name: "發表時間",
    field: "forumCreateTime",
    sort: "",
  },
  {
    name: "更新時間",
    field: "forumUpdateTime",
    sort: "",
  },
]);

// Sort by functionality
const sortBy = computed(() => {
  return cols.reduce((acc, o) => {
    if (o.sort) {
      o.sort === "asc" ? acc.push(o.field) : acc.push("-" + o.field);
    }
    return acc;
  }, []);
});

// On sort th click
function onSort(event, i) {
  let toset;
  const sortEl = cols[i];

  if (!event.shiftKey) {
    cols.forEach((o) => {
      if (o.field !== sortEl.field) {
        o.sort = "";
      }
    });
  }

  if (!sortEl.sort) {
    toset = "asc";
  }

  if (sortEl.sort === "desc") {
    toset = event.shiftKey ? "" : "asc";
  }

  if (sortEl.sort === "asc") {
    toset = "desc";
  }

  sortEl.sort = toset;
}
/*
function updateForum(number) {
  axios
    .get(`http://${url}/forums/${number}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resForumId.value = res.data.resforumId;
      resForumTitle.value =res.data.resforumTitle;
      resForumContent.value = res.data.resforumContent;  
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}*/
// ---------------0721註解-----------------------------------------------------
function updateForum(number) {
  //send request to server

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
const image = ref({
  imageUrl: null,
});

//------------0721改------------------------
// function updateForum(number) {
//   toast
//     .fire({
//       title: "確定要更新嗎?",
//       text: "更新後不能返回",
//       icon: "warning",
//       showCancelButton: true,
//       customClass: {
//         confirmButton: "btn btn-danger m-1",
//         cancelButton: "btn btn-secondary m-1",
//       },
//       confirmButtonText: "更新資料",
//       cancelButtonText: "取消更新",

//       html: false,
//       preConfirm: () => {
//         return new Promise((resolve) => {
//           setTimeout(() => {
//             resolve();
//           }, 50);
//         });
//       },
//     })
//     .then((result) => {
//       //send request to server
//       if (result.value) {
//         const forum = {
//           forumId: this.forumId,
//           forumTitle: this.forumTitle,
//           forumContent: this.forumContent,
//           forumCategory: this.forumCategory,
//           restaurantType: this.restaurantType,
//           restaurantBusinessHours: this.restaurantBusinessHours,
//           restaurantScore: this.restaurantScore,
//           imageUrl: image.value.imageUrl,
//         };
//         //執行put方法

//         axios
//           .put(`http://${url}/forums/${number}`, forum)
//           .then(() => {
//             getAxios();
//             toast.fire("更新成功", "", "success");

//           })
//           .catch((error) => {
//             console.log(error, "失敗");
//           });
//       } else if (result.dismiss === "cancel") {
//         toast.fire("更新失敗", "", "error");
//       }
//     });
// }
//------------------------------------
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

// ----------------------------------0721註解---------------------
function sendForum(number, title, content, category,) {
  var data = {
    forumId: number,
    forumTitle: title,
    forumContent: content,
    forumCategory: category,
    forumImageUrl: image.value.imageUrl,
  };

  axios
    .put(`http://${url}/forums/${number}`, data)
    .then((res) => {
      console.log(res);

      getAxios();
      window.setTimeout(function () {
        location.reload();
      }, 100);


    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}


function deleteForum(number) {
  toast
    .fire({
      title: "確定要刪除這篇文章嗎?",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "刪除!",
      cancelButtonText: "取消!",

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
          .delete(`http://${url}/forums/${number}`)
          .then((res) => {
            //獲取伺服器的回傳資料
            console.log(res);

            getAxios();
            toast.fire("文章刪除成功", "");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("文章刪除失敗", "");
      }
    });



}








// Apply a few Bootstrap 5 optimizations
onMounted(() => {
  // Remove labels from
  document.querySelectorAll("#datasetLength label").forEach((el) => {
    el.remove();
  });

  // Replace select classes
  let selectLength = document.querySelector("#datasetLength select");

  selectLength.classList = "";
  selectLength.classList.add("form-select");
  selectLength.style.width = "80px";
});
// Helper function to show a photo
function showPhoto(index) {
  gallery.index = index;
  gallery.visible = true;
}
// Helper function to hide the lightbox
function handleHide() {
  gallery.visible = false;
}

</script>

<style lang="scss" scoped>
.gg-select {
  box-sizing: border-box;
  position: relative;
  display: block;
  transform: scale(1);
  width: 22px;
  height: 22px;
}

.gg-select::after,
.gg-select::before {
  content: "";
  display: block;
  box-sizing: border-box;
  position: absolute;
  width: 8px;
  height: 8px;
  left: 7px;
  transform: rotate(-45deg);
}

.gg-select::before {
  border-left: 2px solid;
  border-bottom: 2px solid;
  bottom: 4px;
  opacity: 0.3;
}

.gg-select::after {
  border-right: 2px solid;
  border-top: 2px solid;
  top: 4px;
  opacity: 0.3;
}

th.sort {
  cursor: pointer;
  user-select: none;

  &.asc {
    .gg-select::after {
      opacity: 1;
    }
  }

  &.desc {
    .gg-select::before {
      opacity: 1;
    }
  }
}

@import "sweetalert2/dist/sweetalert2.min.css";
</style>

<template>
  <!-- Hero -->
  <BasePageHeading title="網誌管理" subtitle="現在可以查閱所有網誌資訊..">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/forums/dashboard">
              <i class="fab fa-wpforms"></i> 網誌管理</a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="si si-book-open"></i> 文章管理
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="文章後台資料" content-full>
      <Dataset v-slot="{ ds }" :ds-data="resData" :ds-sortby="sortBy" :ds-search-in="[
        'forumTitle',
        'forumContent',
        'forumCategory',
        'imageUrl',
        'forumCreateTime',
        'forumUpdateTime'
      ]">

        <div class="row" :data-page-count="ds.dsPagecount">
          <div id="datasetLength" class="col-md-8 py-2">
            <DatasetShow />
          </div>
          <div class="col-md-4 py-2">
            <DatasetSearch ds-search-placeholder="請輸入關鍵字..." />
          </div>
        </div>
        <div class="col-sm-6 col-xl-4">
          <br />

          <a href="#/backend/forums/InsertForum">
            <button type="button" class="btn rounded-pill btn-outline-success">
              新增文章
            </button>
          </a>
        </div>
        <hr />


        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-vcenter">
                <thead>
                  <tr>
                    <th scope="col" class="text-center">編號</th>
                    <!-- <th scope="col" class="text-center">標題</th>  -->
                    <th v-for="(th, index) in cols" :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`" @click="onSort($event, index)">
                      {{ th.name }} <i class="gg-select float-end"></i>

                    </th>
                    <th class="text-center" style="width: 100px">操作</th>
                  </tr>
                </thead>
                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row }">
                    <tr style="width: 100px">
                      <th scope="row">{{ row.forumId }}</th>

                      <td class="d-none d-md-table-cell fs-sm" style="width: 200px">
                        {{ row.forumTitle }}
                      </td>

                      <td class="d-none d-sm-table-cell" style="overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 110px;">
                        <div v-html="row.forumContent" style="overflow:hidden">
                        </div>
                      </td>

                      <td class=" d-none d-sm-table-cell" style="min-width: 50px">
                        {{ row.forumCategory }}
                      </td>

                      <td class="d-none d-sm-table-cell fs-sm" style="min-width: 110px">
                        <div class="options-container">
                          <!-- 抓出路徑後要用這個方式塞進去才會變動態的 :src -->
                          <a href="javascript:void(0)" class="img-link img-link-zoom-in img-thumb img-lightbox"
                            @click="showPhoto(index)">
                            <img class="img-fluid" :src="row.forumImage" alt="Photo"
                              style="max-width:300px;width:100%" />
                          </a>

                        </div>
                      </td>





                      <td class="d-none d-sm-table-cell" style="min-width: 150px">
                        {{ row.forumCreateTime }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 150px">
                        {{ row.forumUpdateTime }}
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                          <button type="button" class="btn btn-sm btn-alt-secondary" data-bs-toggle="modal"
                            data-bs-target="#updateForum" @click.prevent="updateForum(row.forumId)">
                            <i class="fa fa-fw fa-pencil-alt"></i>
                          </button>
                          <button type="button" class="btn btn-sm btn-alt-secondary"
                            @click.prevent="deleteForum(row.forumId)">
                            <i class="fa fa-fw fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                  </template>
                </DatasetItem>
              </table>
            </div>
          </div>
        </div>
        <div class="d-flex flex-md-row flex-column justify-content-between align-items-center">
          <DatasetInfo class="py-3 fs-sm" />
          <DatasetPager class="flex-wrap py-3 fs-sm" />
        </div>

        <div class="modal fade" id="updateForum" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <form class="row g-3" id="forum">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">修改文章</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">

                  <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">編號</label><br />
                    <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                      rows="1" v-model="resForumId"></textarea>
                  </div>

                  <div class="mb-3">
                    <label class="form-label" for="example-select">文章分類</label>
                    <select class="form-select" id="example-select" name="example-select" v-model="resForumCategory">
                      <option selected>{{ forumCategory }}</option>
                      <option value="環保">環保</option>
                      <option value="養身">養身</option>
                      <option value="公益">公益</option>
                      <option value="健康">健康</option>
                    </select>
                  </div>

                  <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">標題</label>
                    <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                      rows="1" v-model="resForumTitle"></textarea>
                  </div>
                  <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">內文</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="12" style="resize: none"
                      v-model="resForumContent"></textarea>
                  </div>
                  <div>
                    <label class="form-label" for="val-stock">圖片 </label>
                    <input class="form-control" id="input" type="file" ref="myFile" @change="fileUpload()" />
                    <br />
                    <!-- 根據回傳值印出圖片 -->
                    <img :src="image.imageUrl" style="max-width:500px;width:100%" />
                    <br />
                  </div>




                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    取消
                  </button>
                  <button type="submit" class="btn btn-primary"
                    @click.prevent="sendForum(resForumId, resForumTitle, resForumContent, resForumCategory, resForumImage,)">送出</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </Dataset>
    </BaseBlock>
  </div>
  <!-- END Page Content -->
</template>

