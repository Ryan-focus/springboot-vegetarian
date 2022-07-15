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

const getAxios = function () {
  axios
    .get(`http://${url}/users/all`)
    .then((res) => {
      console.log(res);
      //獲取伺服器的回傳資料
      resData.value = res.data;
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
    name: "電子郵件",
    field: "email",
    sort: "",
  },
  {
    name: "密碼",
    field: "password",
    sort: "",
  },
  {
    name: "用戶名稱",
    field: "userName",
    sort: "",
  },
  {
    name: "狀態",
    field: "status",
    sort: "",
  },
  {
    name: "用戶頭貼",
    field: "userPic",
    sort: "",
  },
  {
    name: "註冊時間",
    field: "registerTime",
    sort: "",
  },
  {
    name: "最後登入時間",
    field: "lastLoginTime",
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

//Delete Order Fuction
function deleteRestaurant(number) {
  toast
    .fire({
      title: "確定要刪除嗎?",
      text: "刪除後不能返回",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "刪除資料",
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
          .delete(`http://${url}/users/${number}`)
          .then((res) => {
            //獲取伺服器的回傳資料
            console.log(res);

            getAxios();
            toast.fire("刪除成功!", "", "success");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("刪除失敗", "", "error");
      }
    });
}

function updateUserStatus(number) {
  toast
    .fire({
      title: "確定要變更使用者狀態嗎?",
      text: "",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "變更資料",
      cancelButtonText: "取消變更",

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
          .patch(`http://${url}/users/${number}`)
          .then((res) => {
            //獲取伺服器的回傳資料
            console.log(res);

            getAxios();
            toast.fire("變更成功!", "", "success");
          })
          .catch((error) => {
            console.log(error, "失敗");
          });
      } else if (result.dismiss === "cancel") {
        toast.fire("已取消", "", "error");
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
</style>

<template>
  <!-- Hero -->
  <BasePageHeading title="使用者管理" subtitle="現在可以查閱所有使用者資訊..">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/users/dashboard">
              <i class="fa fa-users"></i> 會員管理</a>
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-user"></i> 使用者管理
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="使用者後台資料" content-full>
      <Dataset v-slot="{ ds }" :ds-data="resData" :ds-sortby="sortBy" :ds-search-in="[
        'email',
        'password',
        'userName',
        'status',
        'userPic',
        'registerTime',
        'lastLoginTime'
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
                    <th scope="col" class="text-center">編號</th>
                    <th v-for="(th, index) in cols" :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`" @click="onSort($event, index)">
                      {{ th.name }} <i class="gg-select float-end"></i>
                    </th>
                    <th class="text-center" style="width: 100px">動作</th>
                  </tr>
                </thead>
                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row }">
                    <tr>
                      <th scope="row">{{ row.userId }}</th>
                      <td class="text-center" style="min-width: 130px">
                        {{ row.email }}
                      </td>
                      <td class="d-none d-md-table-cell fs-sm">
                        {{ row.password }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.userName }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 80px">
                        {{ row.status }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        <img :src="`data:image/png;base64,${row.userPic}`" style="height:80px" />
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 110px">
                        {{ row.registerTime }}
                      </td>
                      <td class="d-none d-sm-table-cell" style="min-width: 140px">
                        {{ row.lastLoginTime }}
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                          <button type="button" class="btn btn-sm btn-alt-secondary"
                            @click="updateUserStatus(row.userId)">
                            <i class="fa fa-fw fa-user-pen"></i>
                          </button>
                          <button type="button" class="btn btn-sm btn-alt-secondary"
                            @click="deleteRestaurant(row.userId)">
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
      </Dataset>
    </BaseBlock>
  </div>
  <!-- END Page Content -->
</template>
