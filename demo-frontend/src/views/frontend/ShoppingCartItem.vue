<script setup>
import { useTemplateStore } from "@/stores/template";

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

const user = JSON.parse(window.localStorage.getItem("access-admin"));
const cartItemList = JSON.parse(window.localStorage.getItem("cartItem")).cartItemList;
console.log(cartItemList)


function removeCart() {
  localStorage.removeItem("cartItem")
  location.replace("http://localhost:8080/#/shopping")
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
                <th class="text-center" style="width: 90px">Qnt</th>
                <th class="text-end" style="width: 120px">Unit</th>
                <th class="text-end" style="width: 120px">Amount</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, i) in cartItemList" :key="i">
                <td class="text-center">{{ i + 1 }}</td>
                <td>
                  <p class="fw-semibold mb-1">{{ item.product.productName }}</p>
                  <img :src="item.product.productImage" alt="" width="50">
                </td>
                <td class="text-center">
                  <span class="badge rounded-pill bg-primary">{{ item.quantity }}</span>
                </td>
                <td class="text-end">NT. {{ item.product.productPrice }}</td>
                <td class="text-end">{{ item.product.productPrice * item.quantity }}</td>
              </tr>

              <tr>
                <td colspan="4" class="fw-semibold text-end">Subtotal</td>
                <td class="text-end">$27.500,00</td>
              </tr>
              <tr>
                <td colspan="4" class="fw-semibold text-end">Vat Rate</td>
                <td class="text-end">20%</td>
              </tr>
              <tr>
                <td colspan="4" class="fw-semibold text-end">Vat Due</td>
                <td class="text-end">$5.500,00</td>
              </tr>
              <tr>
                <td colspan="4" class="fw-bold text-uppercase text-end bg-body-light">
                  Total Due
                </td>
                <td class="fw-bold text-end bg-body-light">$33.000,00</td>
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
