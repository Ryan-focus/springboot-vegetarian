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

// CKEditor 5, for more info and examples you can check out https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/frameworks/vuejs-v3.html
import CKEditor from "@ckeditor/ckeditor5-vue";

// You can import one of the following CKEditor variation (only one at a time)
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import path from "path";
//import InlineEditor from '@ckeditor/ckeditor5-build-inline'
//import BalloonEditor from '@ckeditor/ckeditor5-build-balloon'
//import BalloonBlockEditor from '@ckeditor/ckeditor5-build-balloon-block'

// CKEditor 5 variables
let ckeditor = CKEditor.component;

const editorData = ref("<p>請在這邊輸入對商品的詳細描述</p>");
const editorConfig = ref({});
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
const productId = ref();
const resData = ref();
const productName = ref();
const productCategory = ref();
const veganCategory = ref();
const productPrice = ref();
const productImage = ref();
const description = ref();
const stock = ref();

//取得全部的資料
const getAxios = function () {
  axios
    .get(`http://${url}/products/all`)
    .then((res) => {
      console.log(res);
      //獲取伺服器的回傳資料
      resData.value = res.data;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
function getSingle(number) {
  //send request to server

  axios
    .get(`http://${url}/products/${number}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      console.log(res);
      productId.value = res.data.productId;
      productName.value = res.data.productName;
      productCategory.value = res.data.productCategory;
      veganCategory.value = res.data.veganCategory;
      productPrice.value = res.data.productPrice;
      stock.value = res.data.stock;
      description.value = res.data.description;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
}
//執行Axios
getAxios();
// Helper variables
//在這邊去設定Table :th的欄位名稱
const cols = reactive([
  {
    name: "產品名稱",
    field: "productName",
    sort: "",
  },
  {
    name: "產品種類",
    field: "productCategory",
    sort: "",
  },
  {
    name: "素食種類",
    field: "veganCategory",
    sort: "",
  },
  {
    name: "價格",
    field: "price",
    sort: "",
  },
  {
    name: "產品圖片",
    field: "imageUrl",
    sort: "",
  },

  {
    name: "庫存",
    field: "stock",
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
          .delete(`http://${url}/products/${number}`)
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

// 更新商品
function updateForum(number) {
  //send request to server

  axios
    .get(`http://${url}/products/${number}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      console.log(res);
      productId.value = res.data.productId;
      productName.value = res.data.productName;
      productCategory.value = res.data.productCategory;
      veganCategory.value = res.data.veganCategory;
      productPrice.value = res.data.productPrice;
      productImage.value = res.data.productImage;
      stock.value = res.data.stock;
      description.value = res.data.description;
    })
    .catch((error) => {
      console.log(error, "失敗");
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
  <BasePageHeading title="商品管理" subtitle="可以檢視現在所有訂單">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="#/backend/cart/dashboard">
              <i class="fa fa-cart-shopping"></i> 購物車管理</a
            >
          </li>
          <li class="breadcrumb-item" aria-current="page">
            <i class="fa fa-leaf"></i> 商品管理
          </li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <BaseBlock title="訂單後台資料" content-full>
      <Dataset
        v-slot="{ ds }"
        :ds-data="resData"
        :ds-sortby="sortBy"
        :ds-search-in="[
          'productName',
          'productCategory',
          'veganCategory',
          'price',
          'imageUrl',
          'description',
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
        <div class="col-sm-6 col-xl-4">
          <br />
          <a href="#/backend/cart/InsertProduct">
            <button type="button" class="btn rounded-pill btn-outline-success">
              新增商品
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
                      <th scope="row">{{ row.productId }}</th>
                      <td class="text-center" style="min-width: 150px">
                        {{ row.productName }}
                      </td>
                      <td
                        class="d-none d-md-table-cell fs-sm"
                        style="min-width: 110px"
                      >
                        {{ row.productCategory }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell fs-sm"
                        style="min-width: 110px"
                      >
                        {{ row.veganCategory }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell fs-sm"
                        style="min-width: 110px"
                      >
                        {{ row.productPrice }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell fs-sm"
                        style="min-width: 110px"
                      >
                        {{ row.image }}
                      </td>
                      <td
                        class="d-none d-sm-table-cell fs-sm"
                        style="min-width: 110px"
                      >
                        {{ row.stock }}
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            data-bs-toggle="modal"
                            data-bs-target="#updateProduct"
                            @click="getSingle(row.productId)"
                          >
                            <!-- 上面的按鈕先取的單一個商品的資訊，綁定到最上面的CONST,下面vmodel在做顯示 -->
                            <i class="fa fa-fw fa-pencil-alt"></i>
                          </button>
                          <button
                            type="button"
                            class="btn btn-sm btn-alt-secondary"
                            @click="deleteRestaurant(row.productId)"
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
          id="updateProduct"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">修改商品</h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <!-- 內文開始 -->
              <div class="modal-body">
                <!-- 商品名稱 -->
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label"
                    >商品名稱</label
                  >
                  <textarea
                    type="textarea"
                    class="form-control"
                    id="exampleFormControlInput1"
                    style="resize: none"
                    rows="1"
                    v-model="productName"
                  ></textarea>
                </div>

                <!-- 商品種類 -->
                <div class="mb-3">
                  <label class="form-label" for="example-select"
                    >選擇商品分類</label
                  >
                  <select
                    class="form-select"
                    id="example-select"
                    name="example-select"
                    v-model="productCategory"
                  >
                    <option selected>{{ productCategory }}</option>
                    <option value="生鮮">生鮮</option>
                    <option value="食品">食品</option>
                    <option value="寵物">寵物</option>
                    <option value="居家百貨">居家百貨</option>
                    <option value="飾品">飾品</option>
                    <option value="保健">保健</option>
                    <option value="書籍影音">書籍影音</option>
                    <option value="美妝保養">美妝保養</option>
                    <option value="量販批發">量販批發</option>
                  </select>
                </div>

                <!-- 素食種類 -->
                <div class="mb-3">
                  <label class="form-label" for="example-select"
                    >選擇素食種類</label
                  >
                  <select
                    class="form-select"
                    id="example-select"
                    name="example-select"
                    v-model="veganCategory"
                  >
                    <option selected>{{ veganCategory }}</option>
                    <option value="全素">全素</option>
                    <option value="蛋素">蛋素</option>
                    <option value="奶素">奶素</option>
                    <option value="蛋奶素">蛋奶素</option>
                    <option value="五辛素">五辛素</option>
                    <option value="VEGAN">VEGAN</option>
                    <option value="書籍影音">書籍影音</option>
                    <option value="美妝保養">美妝保養</option>
                    <option value="量販批發">量販批發</option>
                  </select>
                </div>

                <!-- 價格 -->
                <div class="mb-4">
                  <label class="form-label" for="example-ltf-email">價格</label>
                  <input
                    type="number"
                    class="form-control"
                    id="productPrice"
                    name="productPrice"
                    v-model="productPrice"
                  />
                </div>

                <!-- 庫存 -->
                <div class="mb-4">
                  <label class="form-label" for="example-ltf-email">庫存</label>
                  <input
                    type="stock"
                    class="form-control"
                    id="stock"
                    name="stock"
                    v-model="stock"
                  />
                </div>

                <div class="mb-4">
                  <label class="form-label" for="example-select"
                    >產品細節描述</label
                  >
                  <ckeditor
                    :editor="ClassicEditor"
                    :config="editorConfig"
                    v-model="description"
                  />
                </div>
              </div>
              <!-- 表單內文在這裡結束 -->
              <!-- 送出button -->
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  取消
                </button>
                <button
                  type="submit"
                  class="btn btn-primary"
                  @click="updateProduct(productId)"
                >
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
<script>
export default {
  data() {
    let toast = Swal.mixin({
      buttonsStyling: false,
      target: "#page-container",
      customClass: {
        confirmButton: "btn btn-success m-1",
        cancelButton: "btn btn-danger m-1",
        input: "form-control",
      },
    });
    return {
      productName: null,
      productCategory: null,
      veganCategory: null,
      productPrice: null,
      stock: null,
      description: null,
    };
  },
  methods: {
    updateProduct(number) {
      const product = {
        productName: this.productName,
        category: this.productCategory,
        veganCategory: this.veganCategory,
        productPrice: this.productPrice,
        stock: this.stock,
        description: this.description,
      };
      axios
        .put(`http://localhost:8088/products/${number}`, product)
        .then(() => {
          console.log(product);
          window.location.reload();
        })
        // .then(() => {
        //   axios
        //     .get(`http://localhost:8088/products`)
        //     .then((res) => {
        //       //獲取伺服器的回傳資料
        //       console.log(res);
        //       productId.value = res.data.productId;
        //       productName.value = res.data.productName;
        //       productCategory.value = res.data.productCategory;
        //       veganCategory.value = res.data.veganCategory;
        //       productPrice.value = res.data.productPrice;
        //       stock.value = res.data.stock;
        //       description.value = res.data.description;
        //     })
        //     .catch((error) => {
        //       console.log(error, "失敗");
        //     });
        // })
        .catch((error) => {
          console.log(error, "失敗");
        });
    },
  },
};
</script>
