<script setup>
import { ref, reactive, computed, onMounted } from "vue";

// Vue Dataset, for more info and examples you can check out https://github.com/kouts/vue-dataset/tree/next
import Swal from "sweetalert2";
import axios from "axios";

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
const resImg = ref();

const getImg = function () {

  axios
    .get(`http://${url}/business`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resImg.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//取得全部的order
const getAxios = function () {

  axios
    .get(`http://${url}/pos`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data.results;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};

//執行Axios
getAxios();
getImg();

//取得單一筆訂單，number用來抓id
function getSingle() {
  axios
    .get(`http://${url}/pos?statusCategory=試用期7日`)
    .then((res) => {
      //獲取伺服器的回傳資料
      console.log(res);
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

// Helper variables
//在這邊去設定Table :th的欄位名稱
const cols = reactive([

  {
    name: "試用狀態",
    field: "validDate",
    sort: "",
  },
  {
    name: "實際到期日",
    field: "expiryDate",
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
//更新訂單功能
function updateOrder(number) {
  toast
    .fire({
      title: "確定要更新嗎?",
      // text: "更新後不能返回",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "審核通過",
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
        const order = {
          payment: this.payment,
          status: this.status,
        };
        //執行put方法
        axios
          .put(`http://localhost:8088/order/${number}`, order)
          .then(() => {
            console.log(order);
            getAxios();
            toast.fire("更新成功!", "", "success");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("更新失敗", "", "error");
      }
    });
}

//Delete Order Fuction
// function deleteRestaurant(number) {
//   toast
//     .fire({
//       title: "確定要刪除嗎?",
//       text: "刪除後不能返回",
//       icon: "warning",
//       showCancelButton: true,
//       customClass: {
//         confirmButton: "btn btn-danger m-1",
//         cancelButton: "btn btn-secondary m-1",
//       },
//       confirmButtonText: "刪除資料",
//       cancelButtonText: "取消刪除",

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
//         axios
//           .delete(`http://${url}/order/${number}`)
//           .then((res) => {
//             //獲取伺服器的回傳資料
//             console.log(res);

//             getAxios();
//             toast.fire("刪除成功!", "", "success");
//           })
//           .catch((error) => {
//             console.log(error, "失敗");
//           });
//       } else if (result.dismiss === "cancel") {
//         toast.fire("刪除失敗", "", "error");
//       }
//     });
// }

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
</style>

<template>
  <!-- Hero -->
  <BasePageHeading title="訂單總覽" subtitle="現在可以查看所有使用POS系統的商家">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-clock"></i> 預訂管理</a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fab fa-dashcube"></i> 訂單總覽
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="POS系統資料" content-full>
      <Dataset v-slot="{ ds }" :ds-data="resData" :ds-sortby="sortBy" :ds-search-in="[
        'validDate',
        'expiryDate',
      ]">
        <div class="row" :data-page-count="ds.dsPagecount">
          <div id="datasetLength" class="col-md-8 py-2">
            <DatasetShow />
          </div>
          <div class="col-md-4 py-2">
            <DatasetSearch ds-search-placeholder="資料搜尋..." />
          </div>
        </div>
        <hr />
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-vcenter">
                <thead>
                  <tr>
                    <th class="text-center" style="width: 100px">
                      <i class="far fa-user"></i>
                    </th>
                    <th class="text-center" style="width: 200px">
                      商家名稱
                    </th>
                    <!-- <th scope="col" class="text-center">POS編號</th> -->
                    <th v-for="(th, index) in cols" :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`" @click="onSort($event, index)">
                      {{ th.name }} <i class="gg-select float-end"></i>
                    </th>
                    <th class="text-center" style="width: 200px">
                      來客量
                    </th>
                    <th class="text-center" style="width: 200px">
                      營業額
                    </th>

                    <th class="text-center" style="width: 50px">操作</th>
                  </tr>
                </thead>
                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row }">
                    <tr>
                      <td class="text-center">
                        <img class="img-avatar img-avatar48"
                          :src="`/assets/media/business/${resImg[row.posBusinessList[0].businessId - 1].businessPic}.jpg`"
                          alt="business" />

                      </td>
                      <!-- <th scope="row">{{ row.posId }}</th> -->
                      <td class="d-none d-md-table-cell fs-sm">
                        {{ row.posBusinessList[0].businessName }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.validDate }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.expiryDate }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.posBusinessList[0].visitors }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.posBusinessList[0].turnOver }}
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                          <button type="button" class="btn btn-sm btn-alt-secondary" data-bs-toggle="modal"
                            data-bs-target="#updateProduct" @click="getSingle(row.orderId)">
                            <i class="fa fa-fw fa-pencil-alt"></i>
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

        <!-- 這邊以下是隱藏的更新表單，按下更新鈕之後會跳出來 -->
        <div class="modal fade" id="updateProduct" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <!-- 這邊是更新的標題 -->
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">修改商品</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <!-- 內文開始 -->
              <div class="modal-body">
                <!-- 商品名稱 -->
                <div class="mb-3">
                  <input type="hidden" class="form-control" id="exampleFormControlInput1" style="resize: none"
                    rows="1" />
                </div>

                <!-- 商品種類 -->
                <div class="mb-3">
                  <label class="form-label" for="example-select">更新訂單狀態</label>
                  <select class="form-select" id="example-select" name="example-select">
                    <option selected></option>
                    <option value="未付款">未付款</option>
                    <option value="已付款">已付款</option>
                    <option value="已出貨">已出貨</option>
                    <option value="訂單完成">訂單完成</option>
                  </select>
                </div>
              </div>
              <!-- 表單內文在這裡結束 -->
              <!-- 送出button -->
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                  取消
                </button>
                <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" @click="updateOrder(orderId)">
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
