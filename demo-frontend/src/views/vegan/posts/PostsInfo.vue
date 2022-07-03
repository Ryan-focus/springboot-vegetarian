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
// eslint-disable-next-line no-unused-vars
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
var resData = ref();

const resPostId = ref();
const resPostTitle = ref();
const resPostText = ref();

const statusClass = [];

const getAxios = function () {
  axios
    .get(`http://${url}/postIndex`)
    .then((res) => {
      // console.log(res);

      // 獲取伺服器的回傳資料;
      res.data.forEach((value) => {
        let status = value.postStatus;
        if (status === "待審核") {
          statusClass.push("warning");
        } else if (status === "通過") {
          statusClass.push("success");
        } else if (status === "未通過") {
          statusClass.push("danger");
        }
      });
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//執行Axios
getAxios();

// Get example data
//import users from "@/data/usersDataset.json";

// Helper variables
//在這邊去設定Table :th的欄位名稱
const cols = reactive([
  {
    name: "文章名稱",
    field: "title",
    sort: "",
  },
  {
    name: "文章內文",
    field: "postedText",
    sort: "",
  },
  {
    name: "發表日期",
    field: "postedDate",
    sort: "",
  },
  {
    name: "圖片預覽",
    field: "imgurl",
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
          .get(`http://${url}/deletePost/${number}`)
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

//審核文章
function auditPost(number) {
  //send request to server

  axios
    .get(`http://${url}/auditPost/${number}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resPostId.value = res.data.postId;
      resPostTitle.value = res.data.title;
      resPostText.value = res.data.postedText;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

//互動視窗modal
// var exampleModal = document.getElementById("exampleModal");
// exampleModal.addEventListener("show.bs.modal", function (event) {
//   // Button that triggered the modal
//   var button = event.relatedTarget;
//   // Extract info from data-bs-* attributes
//   var recipient = button.getAttribute("data-bs-whatever");
//   // If necessary, you could initiate an AJAX request here
//   // and then do the updating in a callback.
//   //
//   // Update the modal's content.
//   var modalTitle = exampleModal.querySelector(".modal-title");
//   var modalBodyInput = exampleModal.querySelector(".modal-body input");

//   modalTitle.textContent = "New message to " + recipient;
//   modalBodyInput.value = recipient;
// });

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
  <BasePageHeading title="文章管理" subtitle="現在可以查閱所有文章資訊..">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-pen-to-square"></i> 食記管理</a
            >
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-file-pen"></i> 文章管理
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="文章後台資料" content-full>
      <template #options>
        <div class="space-x-1">
          <div class="dropdown d-inline-block">
            <button
              type="button"
              class="btn btn-sm btn-alt-secondary"
              id="dropdown-recent-orders-filters"
              data-bs-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              <i class="fa fa-fw fa-flask"></i>
              篩選器
              <i class="fa fa-angle-down ms-1"></i>
            </button>
            <div
              class="dropdown-menu dropdown-menu-md dropdown-menu-end fs-sm"
              aria-labelledby="dropdown-recent-orders-filters"
            >
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href=""
                data-rel="notaudit"
              >
                待審核
                <span class="badge bg-primary rounded-pill">20</span>
              </a>
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href=""
                data-rel="pass"
              >
                通過審核
                <span class="badge bg-primary rounded-pill">72</span>
              </a>
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href=""
                data-rel="fail"
              >
                未通過審核
                <span class="badge bg-primary rounded-pill">890</span>
              </a>
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href=""
                data-rel="all"
              >
                全部
                <span class="badge bg-primary rounded-pill">997</span>
              </a>
            </div>
          </div>
        </div>
      </template>
      <Dataset
        v-slot="{ ds }"
        :ds-data="resData"
        :ds-sortby="sortBy"
        :ds-search-in="['postStatus', 'title', 'postedDate', 'postedText']"
      >
        <div class="row" :data-page-count="ds.dsPagecount">
          <div class="col-md-3 py-2">
            <DatasetSearch ds-search-placeholder="資料搜尋..." />
          </div>
          <div id="datasetLength" class="col-md-2 ms-auto py-2">
            <DatasetShow />
          </div>
        </div>

        <hr />
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-vcenter">
                <thead>
                  <tr>
                    <th scope="col" class="text-center" style="min-width: 55px">
                      編號
                    </th>
                    <th scope="col" class="d-none d-sm-table-cell">文章狀態</th>
                    <th
                      v-for="(th, index) in cols"
                      :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`"
                      @click.prevent="onSort($event, index)"
                    >
                      {{ th.name }} <i class="gg-select float-end"></i>
                    </th>
                    <th class="text-center" style="width: 100px">審核</th>
                  </tr>
                </thead>
                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row, rowIndex }">
                    <tr style="line-height: 5px">
                      <th scope="row">{{ row.postId }}</th>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 100px"
                      >
                        <span
                          :class="`fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-${statusClass[rowIndex]}-light text-${statusClass[rowIndex]}`"
                          id="combo"
                          >{{ row.postStatus }}</span
                        >
                      </td>
                      <td
                        class="text-center"
                        style="
                          overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 150px;
                        "
                      >
                        {{ row.title }}
                      </td>
                      <td
                        class="d-none d-md-table-cell fs-sm"
                        style="
                          overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 150px;
                        "
                      >
                        {{ row.postedText }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 180px"
                      >
                        {{ row.postedDate }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="
                          overflow: hidden;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 150px;
                        "
                      >
                        {{ row.imgurl }}
                      </td>

                      <td class="text-center">
                        <div class="btn-group">
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            data-bs-toggle="modal"
                            data-bs-target="#auditPost"
                            @click="auditPost(row.postId)"
                          >
                            <i class="fa fa-fw fa-pencil-alt"></i>
                          </button>
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            @click.prevent="deletePost(row.postId)"
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

        <div
          class="modal fade"
          id="auditPost"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">文章審核</h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label"
                    >文章標題</label
                  >
                  <textarea
                    type="textarea"
                    class="form-control"
                    id="exampleFormControlInput1"
                    style="resize: none"
                    disabled
                    readonly
                    rows="1"
                    v-model="resPostTitle"
                  ></textarea>
                </div>
                <div class="mb-3">
                  <label for="exampleFormControlTextarea1" class="form-label"
                    >文章內文</label
                  >
                  <textarea
                    class="form-control"
                    id="exampleFormControlTextarea1"
                    rows="12"
                    style="resize: none"
                    disabled
                    readonly
                    v-model="resPostText"
                  ></textarea>
                </div>
                <div class="auditselect">
                  <select
                    class="form-select form-select-lg mb-3"
                    aria-label=".form-select-lg example"
                  >
                    <option value="待審核" selected>待審核</option>
                    <option value="通過">通過</option>
                    <option value="未通過">未通過</option>
                  </select>
                </div>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  關閉
                </button>
                <button type="submit" class="btn btn-primary">送出審核</button>
              </div>
            </div>
          </div>
        </div>
      </Dataset>
    </BaseBlock>
  </div>
  <!-- END Page Content -->
</template>
