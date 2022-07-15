<script setup>
import { ref, reactive, computed, onMounted } from "vue";
// Sweetalert2, for more info and examples, you can check out https://github.com/sweetalert2/sweetalert2
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

const restaurantNumber = ref();
const restaurantName = ref();
const restaurantTel = ref();
const restaurantAddress = ref();
const restaurantCategory = ref();
const restaurantType = ref();
const restaurantBusinessHours = ref();
const restaurantScore = ref();
const imageUrl = ref();

const image = ref({
  imageUrl: null,
});

//取得全部的資料
const getAxios = function () {
  axios
    .get(`http://${url}/restaurants`)
    .then((res) => {
      console.log(res);
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};


//更新餐廳的方法--取值
function getRestaurant(number) {
  axios
    .get(`http://${url}/restaurants/${number}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      restaurantNumber.value = res.data.restaurantNumber;
      restaurantName.value = res.data.restaurantName;
      restaurantTel.value = res.data.restaurantTel;
      restaurantAddress.value = res.data.restaurantAddress;
      restaurantCategory.value = res.data.restaurantCategory;
      restaurantType.value = res.data.restaurantType;
      restaurantBusinessHours.value = res.data.restaurantBusinessHours;
      restaurantScore.value = res.data.restaurantScore;
      image.value.imageUrl = res.data.imageUrl;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

//更新後送出餐廳表單
function updateRestaurant(number) {
  toast
    .fire({
      title: "確定要更新嗎?",
      text: "更新後不能返回",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "更新資料",
      cancelButtonText: "取消更新",

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
        const restaurant = {
          restaurantName: this.restaurantName,
          restaurantTel: this.restaurantTel,
          restaurantAddress: this.restaurantAddress,
          restaurantCategory: this.restaurantCategory,
          restaurantType: this.restaurantType,
          restaurantBusinessHours: this.restaurantBusinessHours,
          restaurantScore: this.restaurantScore,
          imageUrl: image.value.imageUrl,
        };
        //執行put方法

        axios
          .put(`http://${url}/restaurants/${number}`, restaurant)
          .then(() => {
            getAxios();
            toast.fire("更新成功", "", "success");

          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("更新失敗", "", "error");
      }
    });
}

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
getAxios();

// Helper variables
//在這邊去設定Table :th的欄位名稱
const cols = reactive([
  {
    name: "電話",
    field: "restaurantTel",
    sort: "",
  },
  {
    name: "地址",
    field: "restaurantAddress",
    sort: "",
  },
  {
    name: "類型",
    field: "restaurantCategory",
    sort: "",
  },
  {
    name: "素食種類",
    field: "restaurantType",
    sort: "",
  },
  {
    name: "營業時間",
    field: "restaurantBusinessHours",
    sort: "",
  },
  {
    name: "評分",
    field: "restaurantScore",
    sort: "",
  }, {
    name: "圖片",
    field: "imageUrl",
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

//刪除餐廳的方法
function deleteRestaurant(number) {
  toast
    .fire({
      title: "確定要刪除嗎?",
      text: "刪除之後就無法回復囉",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "確定刪除",
      cancelButtonText: "取消刪除",

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
          .delete(`http://${url}/restaurants/${number}`)
          .then((res) => {
            //獲取伺服器的回傳資料
            console.log(res);

            getAxios();
            toast.fire("刪除成功", "", "success");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("刪除失敗", "", "error");
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

// SweetAlert2
@import "sweetalert2/dist/sweetalert2.min.css";
</style>

<template>
  <!-- Hero -->
  <BasePageHeading title="餐廳資訊">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a> <i class="fa fa-burger"></i> 餐廳管理</a>
          </li>

          <li class="breadcrumb-item" aria-current="page">
            <a class="link-fx" href="#/backend/restaurants/restaurantinfo">
              <i class="fa fa-store"></i> 餐廳資訊</a>
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="餐廳後台資料" content-full>
      <Dataset v-slot="{ ds }" :ds-data="resData" :ds-sortby="sortBy" :ds-search-in="[
        'restaurantName',
        'restaurantTel',
        'restaurantAddress',
        'restaurantCategory',
        'restaurantType',
        'restaurantBusinessHours',
        'restaurantScore',
        'imageUrl'
      ]">
        <div class="row" :data-page-count="ds.dsPagecount">
          <div id="datasetLength" class="col-md-8 py-2">
            <DatasetShow />
          </div>
          <div class="col-md-4 py-2">
            <DatasetSearch ds-search-placeholder="搜尋：中式/日式/全素.." />
          </div>
        </div>
        <div class="col-sm-6 col-xl-4">
          <br />
          <a href="#/backend/restaurants/InsertRestaurant">
            <button type="button" class="btn rounded-pill btn-outline-success">
              新增餐廳
            </button>
          </a>
        </div>
        <br />

        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-vcenter">
                <thead>
                  <tr>
                    <th scope="col" class="text-center" style="min-width: 55px">
                      編號
                    </th>
                    <th scope="col" class="text-center" style="min-width: 100px">
                      名稱
                    </th>
                    <th v-for="(th, index) in cols" :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`" @click="onSort($event, index)">
                      {{ th.name }} <i class="gg-select float-end"></i>
                    </th>
                    <th class="text-center" style="width: 80px">動作</th>
                  </tr>
                </thead>

                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row }">
                    <tr>
                      <th scope="row">{{ row.restaurantNumber }}</th>
                      <td class="text-center" style="min-width: 80px">
                        {{ row.restaurantName }}
                      </td>
                      <td class="d-none d-md-table-cell fs-sm" style="
                          overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 80px;
                        ">
                        {{ row.restaurantTel }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="
                          overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 110px;
                        ">
                        {{ row.restaurantAddress }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 80px">
                        {{ row.restaurantCategory }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.restaurantType }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="
                          overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 110px;
                        ">
                        {{ row.restaurantBusinessHours }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 80px">
                        {{ row.restaurantScore }}
                      </td>
                      <!-- <td class="d-none d-sm-table-cell" style="min-width: 80px">
                        {{ row.imageUrl }}
                      </td> -->
                      <td class="d-none d-sm-table-cell fs-sm" style="min-width: 110px">
                        <div class="options-container">
                          <!-- 抓出路徑後要用這個方式塞進去才會變動態的 :src -->
                          <a href="javascript:void(0)" class="img-link img-link-zoom-in img-thumb img-lightbox"
                            @click="showPhoto(index)">
                            <img class="img-fluid" :src="row.imageUrl" alt="Photo" style="max-width:300px;width:100%" />
                          </a>
                        </div>
                      </td>

                      <td class="text-center">
                        <div class="btn-group">
                          <button type="button" class="btn btn-sm btn-alt-secondary" data-bs-toggle="modal"
                            data-bs-target="#updateRestaurant" @click="getRestaurant(row.restaurantNumber)">
                            <i class="fa fa-fw fa-pencil-alt"></i>
                          </button>
                          <button type="button" class="btn btn-sm btn-alt-secondary"
                            @click="deleteRestaurant(row.restaurantNumber)">
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
        <!-- 更新餐廳表單的樣式 -->
        <div class="modal fade" id="updateRestaurant" tabindex="-1" aria-labelledby="exampleModalLabel"
          aria-hidden="true">
          <div class="modal-dialog  modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">更新餐廳資料</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>

              <div class="modal-body">
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">編號</label><br />
                  <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                    disabled readonly rows="1" v-model="restaurantNumber"></textarea>
                </div>
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">餐廳名稱</label>
                  <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                    rows="1" v-model="restaurantName"></textarea>
                </div>
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">電話</label>
                  <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                    rows="1" v-model="restaurantTel"></textarea>
                </div>
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">地址</label>
                  <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                    rows="1" v-model="restaurantAddress"></textarea>
                </div>
                <div class="auditselect">
                  <label for="exampleFormControlInput1" class="form-label">餐廳類型</label>
                  <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                    v-model="restaurantCategory">
                    <option value="中式">中式</option>
                    <option value="義式">義式</option>
                    <option value="韓式">韓式</option>
                    <option value="日式">日式</option>
                    <option value="美式">美式</option>
                    <option value="印度">印度</option>
                    <option value="簡餐">簡餐</option>
                    <option value="麵食">麵食</option>
                    <option value="自助餐">自助餐</option>
                  </select>
                </div>
                <div class="auditselect">
                  <label for="exampleFormControlInput1" class="form-label">素食種類</label>
                  <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                    v-model="restaurantType">
                    <option value="全素">全素</option>
                    <option value="蛋素">蛋素</option>
                    <option value="奶素">奶素</option>
                    <option value="蛋奶素">蛋奶素</option>
                    <option value="五辛素">五辛素</option>
                  </select>
                </div>

                <div class="mb-3">
                  <label for="exampleFormControlTextarea1" class="form-label">營業時間</label>
                  <textarea type="textarea" class="form-control" id="exampleFormControlTextarea1" style="resize: none"
                    rows="8" v-model="restaurantBusinessHours"></textarea>
                </div>

                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">評分</label>
                  <textarea type="textarea" class="form-control" id="exampleFormControlInput1" style="resize: none"
                    rows="1" v-model="restaurantScore"></textarea>
                </div>

                <!-- 圖片 -->

                <div class="mb-4">
                  <label class="form-label" for="val-stock">圖片</label>
                  <input class="form-control" id="input" type="file" ref="myFile" @change="fileUpload()" />
                  <div class="mb-3">
                    <!-- 根據回傳值印出圖片 -->
                    <img :src="image.imageUrl" style="max-width:500px;width:100%" />
                  </div>
                </div>

              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                  關閉
                </button>
                <button type="submit" class="btn btn-primary" @click="updateRestaurant(restaurantNumber)"
                  data-bs-dismiss="modal">
                  送出
                </button>
              </div>
            </div>
          </div>
        </div>
      </Dataset>
    </BaseBlock>
  </div>
  <!-- END Page Content -->
</template>
