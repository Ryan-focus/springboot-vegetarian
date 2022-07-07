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

const resForumId = ref();
const resForumTitle = ref();
const resForumContent = ref();

const getAxios = function () {
  axios
    .get(`http://${url}/forums`)
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


// Get example data
import users from "@/data/usersDataset.json";

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
     
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}

function sendForum(number, title, content) {
  var data = {
    forumId: number,
    forumTitle: title,
    forumContent: content,
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
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fab fa-wpforms"></i> 網誌管理</a
            >
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
      <Dataset
        v-slot="{ ds }"
        :ds-data="resData"
        :ds-sortby="sortBy"
        :ds-search-in="['forumTitle', 'forumContent', 'forumCreateTime', 'forumUpdateTime']"
      >
        <div class="row" :data-page-count="ds.dsPagecount">
          <div class="col-md-4 py-2">
            <DatasetSearch ds-search-placeholder="請輸入關鍵字..." />
          </div>
          <div id="datasetLength" class="col-md-8 py-2">
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
                    <th scope="col" class="text-center">編號</th>
                   <!-- <th scope="col" class="text-center">標題</th>  -->
                    <th
                      v-for="(th, index) in cols"
                      :key="th.field"
                      :class="['sort', th.sort] && `d-none d-sm-table-cell`"
                      @click="onSort($event, index)"
                    >
                      {{ th.name }} <i class="gg-select float-end"></i>
                    </th>
                    <th class="text-center" style="width: 100px">操作</th>
                  </tr>
                </thead>
                <DatasetItem tag="tbody" class="fs-sm">
                  <template #default="{ row }">
                    <tr style="width: 100px">
                      <th scope="row">{{ row.forumId }}</th>

                      <td class="d-none d-md-table-cell fs-sm" style="width: 85px">
                        {{ row.forumTitle }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.forumContent }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.forumCreateTime }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell"
                        style="min-width: 150px"
                      >
                        {{ row.forumUpdateTime }}
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            data-bs-toggle="modal"
                            data-bs-target="#updateForum"
                            @click.prevent="updateForum(row.forumId)"
                          >
                            <i class="fa fa-fw fa-pencil-alt"></i>
                          </button>
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            @click.prevent="deleteForum(row.forumId)"
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
          id="updateForum"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog modal-lg">
            <form class="row g-3" id="forum">
             <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">修改文章</h5>
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
                      >編號</label
                    ><br />
                    <textarea
                      type="textarea"
                      class="form-control"
                      id="exampleFormControlInput1"
                      style="resize: none"
                      
                      rows="1"
                      v-model="resForumId"
                    ></textarea>
                  </div>

                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label"
                    >標題</label
                  >
                  <textarea
                    type="textarea"
                    class="form-control"
                    id="exampleFormControlInput1"
                    style="resize: none"
                    
                    
                    rows="1"
                    v-model="resForumTitle"
                  ></textarea>
                </div>
                <div class="mb-3">
                  <label for="exampleFormControlTextarea1" class="form-label"
                    >內文</label
                  >
                  <textarea
                    class="form-control"
                    id="exampleFormControlTextarea1"
                    rows="12"
                    style="resize: none"
                    
                    
                    v-model="resForumContent"
                  ></textarea>
                </div>
               
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  取消
                </button>
                <button type="submit" class="btn btn-primary"
                @click.prevent="sendForum(resForumId, resForumTitle, resForumContent)"
                >送出</button>
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

