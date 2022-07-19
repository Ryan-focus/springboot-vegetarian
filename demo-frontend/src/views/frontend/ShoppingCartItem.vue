<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
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
//取得localstorage
const user = JSON.parse(window.localStorage.getItem("access-user"));
//檢查localstorage裡面是否有東西，沒有設定為null不然直接抓會報錯
var cartItemList = null
if (window.localStorage.getItem("cartItem") != null) {
  cartItemList = JSON.parse(window.localStorage.getItem("cartItem")).cartItemList;
}
const userId = JSON.stringify(user.data.user.userId)
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
  axios
    .post(`http://localhost:8088/2/order`, {
      "buyItemList": checkOutItemArray
    })
    .then((res) => {
      console.log(res.data)
    }).catch((error) => {
      console.log(error, "失敗");
    });
}

// 結帳功能
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
              Street Address<br />
              State, City<br />
              Region, Postal Code<br />
              ctr@example.com
            </address>
            <button @click="removeCart()">清除購物車</button>
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
                <th>Product</th>
                <th class="text-center" style="width: 90px">數量</th>
                <th class="text-end" style="width: 120px">單價</th>
                <th class="text-end" style="width: 120px">小計</th>
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
                  <span class="badge rounded-pill bg-primary">
                    {{ item.quantity }}
                  </span>
                </td>
                <td class="text-end">NT. {{ item.product.productPrice }}</td>
                <td class="text-end">{{ item.product.productPrice * item.quantity }}</td>
              </tr>

              <tr>
                <td colspan="4" class="fw-semibold text-end">稅
                </td>
                <td class="text-end">０</td>
              </tr>
              <tr>
                <td colspan="4" class="fw-semibold text-end">運費</td>
                <td class="text-end">0</td>
              </tr>
              <tr>
                <td colspan="4" class="fw-bold text-uppercase text-end bg-body-light">
                  總價
                </td>
                <td class="fw-bold text-end bg-body-light">{{ countTotal() }}</td>
              </tr>
              <tr>
                <td colspan="4" class="fw-bold text-uppercase text-end bg-body-light">
                </td>
                <td class="fw-bold text-end bg-body-light">
                  <button type="button" class="btn btn-outline-danger">
                    <i class="fal fa-dollar-sign" @click="checkOut(), payment(), removeCart()">結帳</i>
                  </button>
                </td>
              </tr>

            </tbody>

          </table>
        </div>
        <!-- END Table -->

        <!-- Footer -->
        <p class="fs-sm text-muted text-center">
          Thank you very much for doing business with us. We look forward to
          working with you again!
        </p>
        <!-- END Footer -->
      </div>
    </BaseBlock>
    <!-- END Invoice -->
  </div>
  <!-- END Page Content -->
</template>
