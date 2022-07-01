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
const urlParams = "warning";
//接收的資料ref
const resData = ref();

const getAxios = function () {
  axios
    .get(`http://${url}/restaurants`, { params: { status: urlParams } })
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

// Helper variables
//在這邊去設定Table :th的欄位名稱
const cols = reactive([
  {
    name: "餐廳名稱",
    field: "restaurantName",
    sort: "",
  },
  {
    name: "餐廳電話",
    field: "restaurantTel",
    sort: "",
  },
  {
    name: "餐廳地址",
    field: "restaurantAddress",
    sort: "",
  },
  {
    name: "餐廳類型",
    field: "restaurantCategory",
    sort: "",
  },
  {
    name: "素食類型",
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
  },
  {
    name: "照片",
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

//Delete Restaurant Fuction
function deleteRestaurant(number) {
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
      confirmButtonText: "幹掉他!",
      cancelButtonText: "拯救他 !",

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
            toast.fire("他被殺死了!", "你殺掉了一個人 ! 殺人犯 !", "success");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("她活下來了", "你取消了他 ! 他安全了 :)", "error");
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
  <BasePageHeading title="餐廳管理" subtitle="現在可以查閱所有餐廳資訊..">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-burger"></i> 餐廳管理</a
            >
          </li>

          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-leaf"></i> 餐廳資訊
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="餐廳後台資料" content-full>
      <Dataset
        v-slot="{ ds }"
        :ds-data="resData"
        :ds-sortby="sortBy"
        :ds-search-in="[
          'restaurantName',
          'restaurantTel',
          'restaurantAddress',
          'restaurantCategory',
          'restaurantType',
          'restaurantBusinessHours',
          'restaurantScore',
        ]"
      >
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
                    <th scope="col" class="text-center">編號</th>
                    <!-- <th scope="col" class="text-center">餐廳名稱</th> -->
                    <th
                      v-for="(th, index) in cols"
                      :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`"
                      @click="onSort($event, index)"
                    >
                      {{ th.name }} <i class="gg-select float-end"></i>
                    </th>
                    <th class="text-center" style="width: 100px">動作</th>
                  </tr>
                </thead>
                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row }">
                    <tr>
                      <th scope="row">{{ row.restaurantNumber }}</th>
                      <td class="text-center" style="min-width: 150px">
                        {{ row.restaurantName }}
                      </td>
                      <td class="d-none d-md-table-cell fs-sm">
                        {{ row.restaurantTel }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.restaurantAddress }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.restaurantCategory }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.restaurantType }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.restaurantBusinessHours }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.restaurantScore }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.imageUrl }}
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                          >
                            <i class="fa fa-fw fa-pencil-alt"></i>
                          </button>
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            @click="deleteRestaurant(row.restaurantNumber)"
                          >
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
        <div
          class="d-flex flex-md-row flex-column justify-content-between align-items-center"
        >
          <DatasetInfo class="py-3 fs-sm" />
          <DatasetPager class="flex-wrap py-3 fs-sm" />
        </div>
      </Dataset>
    </BaseBlock>
  </div>
  <!-- END Page Content -->
</template>
