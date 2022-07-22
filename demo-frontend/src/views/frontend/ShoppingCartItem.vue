<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
import { useRoute } from "vue-router";
import axios from "axios";
import Swal from "sweetalert2";

// Main store
const store = useTemplateStore();

// Print Page
function printPage() {
  // Get current sidebar visibility
  let sidebarVisibility = store.settings.sidebarVisibleDesktop;

  // Close the sidebar
  store.sidebar({ mode: "close" });

  // Print the page
  window.print();

  // Restore previous sidebar visibility
  if (sidebarVisibility) {
    store.sidebar({ mode: "open" });
  }
}

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


//取得localstorage
var user = null;

//判斷登入者權限
if (window.localStorage.getItem("access-user") != null) {
  user = JSON.parse(window.localStorage.getItem("access-user"));
} else if (window.localStorage.getItem("access-admin") != null) {
  user = JSON.parse(window.localStorage.getItem("access-admin"));
}
const userId = JSON.stringify(user.data.user.userId)
//檢查localstorage裡面是否有東西，沒有設定為null不然直接抓會報錯
var cartItemList = null
if (window.localStorage.getItem("cartItem") != null) {
  cartItemList = JSON.parse(window.localStorage.getItem("cartItem")).cartItemList;
}

// 清空localstorage
function removeCart() {
  localStorage.removeItem("cartItem")
  window.location.reload(Swal.fire(
    {
      title: "已清空購物車",
      text: "",
      timer: 1500,
      icon: "success"
    }
  ))
}

//加總功能
var total = 0;
function countTotal() {

  for (var i in this.cartItemList) {
    total += parseInt(this.cartItemList[i].quantity * this.cartItemList[i].product.productPrice)
  }
  return total
}

//刪除一筆localstorage裡面的值
//先取出index在用splice做刪除值
function deleteItem(i) {
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


        cartItemList.splice(i, 1);
        //把cartItem前面的宣告加回去
        const cartItemList1 = { cartItemList: cartItemList }
        localStorage.setItem("cartItem", JSON.stringify(cartItemList1))
        window.location.reload()
      } else if (result.dismiss === "cancel") {
        toast.fire("刪除失敗", "", "error");
      }
    });
}

// 更新數量
function increaseQuantity(i) {
  cartItemList[i].quantity++
  const cartItemList1 = { cartItemList: cartItemList }
  localStorage.setItem("cartItem", JSON.stringify(cartItemList1))
  window.location.reload()
}

function decreaseQuantity(i) {

  cartItemList[i].quantity--
  const cartItemList1 = { cartItemList: cartItemList }
  localStorage.setItem("cartItem", JSON.stringify(cartItemList1))
  window.location.reload()
}

// 寫入訂單功能
//用foreach+push把想要的值推進array中
var checkOutItemArray = []
for (var i in cartItemList) {
  checkOutItemArray.push(
    {
      productId: cartItemList[i].product.productId,
      quantity: cartItemList[i].quantity
    }
  )
}


//將更新好的array寫進來做結帳動作
function checkOut() {
  toast
    .fire({
      title: "確定商品是否正確？",
      text: "",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "前往付款",
      cancelButtonText: "再看一下",

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
        Swal.fire(
          {
            title: "即將跳轉至結帳頁面",
            text: "",
            timer: 3000,
            icon: "success"
          }).then(
            axios
              .post(`http://localhost:8088/${userId}/order`, {
                "buyItemList": checkOutItemArray
              })
              .then((res) => {
                console.log(res.data)
                payment()
              }).catch((error) => {
                console.log(error, "失敗");
              })
          )


      } else if (result.dismiss === "cancel") {
        toast.fire("歡迎繼續選購", "", "info");
      }
    });

}
// 付款功能
function payment() {
  axios.post(
    "http://localhost:8088/paypal/payment?sum=" + total
  ).then(
    (res) => {
      // 抓出返回值得跳轉頁面網址
      if (res.data.redirect_url != null) {
        window.location = res.data.redirect_url
      }
    }
  ).catch((error) => {
    console.log(error, "失敗");
  });
}



</script>

<template>
  <div v-if="cartItemList != null && cartItemList.length > 0">
    <!-- Hero -->
    <BasePageHeading title="購物車商品" subtitle="數量有限要買要快" class="d-print-none">
      <template #extra>

      </template>
    </BasePageHeading>
    <!-- END Hero -->

    <!-- Page Content -->
    <div class="content content-boxed">
      <!-- Invoice -->
      <BaseBlock title="商品列表">
        <template #options>
          <button type="button" class="btn-block-option" @click="printPage()">
            <i class="si si-printer me-1"></i> 列印清單
          </button>
        </template>

        <div class="p-sm-4 p-xl-7">
          <!-- Invoice Info -->
          <div class="row mb-4">
            <!-- Company Info -->
            <div class="col-6 fs-sm">
            </div>
            <!-- END Company Info -->

            <!-- Client Info -->
            <div class="col-6 text-end fs-sm">
              <!-- 使用者名稱 -->
              <p class="h3">{{ user.data.user.userName }}</p>
              <address>
                桃園市<br />
                中壢區<br />
                新生路421號<br />
                {{ user.data.user.email }}
              </address>
            </div>
            <!-- END Client Info -->
          </div>
          <!-- END Invoice Info -->

          <!-- Table -->
          <div class="table-responsive push">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th class="text-center" style="width: 60px"></th>
                  <th>商品</th>
                  <th class="text-center" style="width:200px">數量</th>
                  <th class="text-end" style="width: 120px">單價</th>
                  <th class="text-end" style="width: 120px">小計</th>
                  <th class="text-end" style="width: 120px">刪除</th>
                </tr>
              </thead>
              <tbody>
                <!-- 印出商品從這裡開始 -->
                <tr v-for="(item, i) in cartItemList" :key="i">
                  <td class="text-center">{{ i + 1 }}</td>
                  <td>
                    <p class="fw-semibold mb-1">{{ item.product.productName }}</p>
                    <img :src="item.product.productImage" alt="" width="50">
                  </td>
                  <td class="text-center">
                    <button type="button" class="btn btn-sm btn-Info me-1 mb-3" @click="increaseQuantity(i)">
                      <i class="fa fa-circle-plus"></i>
                    </button>
                    &nbsp;{{ item.quantity }}&nbsp;
                    <button type="button" class="btn btn-sm btn-Info me-1 mb-3" @click="decreaseQuantity(i)">
                      <i class="fa fa-circle-minus"></i>
                    </button>
                  </td>
                  <td class="text-end">NT. {{ item.product.productPrice }}

                  </td>
                  <td class="text-end">{{ item.product.productPrice * item.quantity }}</td>

                  <td class="text-end"> <button type="button" class="btn rounded-pill btn-alt-danger me-1 mb-3"
                      @click="deleteItem(i)">
                      <i class="fa fa-fw fa-times me-1"></i> 移除
                    </button></td>
                </tr>
                <tr>
                  <td colspan="5" class="fw-semibold text-end">稅
                  </td>
                  <td class="text-end">０</td>
                </tr>
                <tr>
                  <td colspan="5" class="fw-semibold text-end">運費</td>
                  <td class="text-end">0</td>
                </tr>
                <tr>
                  <td colspan="5" class="fw-bold text-uppercase text-end bg-body-light">
                    總價
                  </td>
                  <td class="fw-bold text-end bg-body-light">{{ countTotal() }}</td>
                </tr>
                <tr>
                  <td colspan="5" class="fw-bold text-uppercase text-end bg-body-light">
                  </td>
                  <td class="fw-bold text-end bg-body-light">
                    <button type="button" class="btn btn-outline-primary">
                      <i class="fa-brands fa-paypal" @click="checkOut()"> 結帳</i>
                    </button>
                  </td>
                </tr>

              </tbody>

            </table>

            <!-- END Table -->
          </div>


          <!-- Footer -->
          <p class="fs-sm text-muted text-center">
          </p>
          <!-- END Footer -->
        </div>
      </BaseBlock>
      <!-- END Invoice -->
    </div>
  </div>
  <div v-else>
    <!-- Page Content -->
    <div class="hero-static d-flex align-items-center">
      <div class="w-100">
        <!-- Maintenance Section -->
        <div class="bg-body-extra-light">
          <div class="content content-full">
            <div class="row justify-content-center">
              <div class="col-md-8 col-lg-6 col-xl-4 py-6">
                <!-- Header -->
                <div class="text-center">
                  <p>
                    <i class="fa fa-3x fa-seedling fa-spin text-success"></i>
                  </p>
                  <h1 class="h4 mb-1">購物車裡面空空如也</h1>
                  <h2 class="h4 fw-normal text-muted mb-3">
                    <a href="http://localhost:8080/#/shopping">點我選購商品</a>
                  </h2>
                </div>
                <!-- END Header -->
              </div>
            </div>
          </div>
        </div>
        <!-- END Maintenance Section -->

        <!-- Footer -->
        <div class="fs-sm text-center text-muted py-3">
          <strong>{{ store.app.name + " " + store.app.version }}</strong> &copy;
          {{ store.app.copyright }}
        </div>
        <!-- END Footer -->
      </div>
    </div>
    <!-- END Page Content -->
  </div>
  <!-- END Page Content -->
</template>
