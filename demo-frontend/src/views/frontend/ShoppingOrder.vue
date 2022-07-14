<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
import Swal from "sweetalert2";
import axios from "axios";
// 宣告的直放這裡
const url = "localhost:8088";
//用來取登入後的userId
const user = JSON.parse(window.localStorage.getItem("access-admin"));
const userId = user.data.user.userId
const orderList = ref()
const orderItem = ref();
const orderData = ref(
    {
        "limit": 10,
        "offset": 0,
        "total": null,
    }
);
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


// 功能放這裡
const getAxios = function () {
    axios
        .get(`http://${url}/${userId}/order`)
        .then((res) => {
            //獲取伺服器的回傳資料
            orderData.value = res.data;
            orderList.value = orderData.value.results
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
};
getAxios();
</script>

<template >

    <div class="content">
        <form @submit.prevent>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search.." />
                <span class="input-group-text">
                    <i class="fa fa-fw fa-search"></i>
                </span>
            </div>
        </form>
    </div>
    <!-- END Search -->

    <!-- Page Content -->
    <div class="content">
        <!-- Results -->

        <BaseBlock class="overflow-hidden">
            <template #content>
                <!-- 我是標頭 -->
                <ul class="nav nav-tabs nav-tabs-block" role="tablist">
                    <li class="nav-item">
                        <button type="button" class="nav-link active" id="search-projects-tab" data-bs-toggle="tab"
                            data-bs-target="#search-projects" role="tab" aria-controls="search-projects"
                            aria-selected="true">
                            全部
                        </button>
                    </li>
                    <li class="nav-item">
                        <button type="button" class="nav-link" id="search-users-tab" data-bs-toggle="tab"
                            data-bs-target="#search-users" role="tab" aria-controls="search-users"
                            aria-selected="false">
                            待付款
                        </button>
                    </li>
                    <li class="nav-item">
                        <button type="button" class="nav-link" id="search-classic-tab" data-bs-toggle="tab"
                            data-bs-target="#search-classic" role="tab" aria-controls="search-classic"
                            aria-selected="false">
                            待收貨
                        </button>
                    </li>
                    <li class="nav-item">
                        <button type="button" class="nav-link" id="search-photos-tab" data-bs-toggle="tab"
                            data-bs-target="#search-photos" role="tab" aria-controls="search-photos"
                            aria-selected="false">
                            完成
                        </button>
                    </li>
                </ul>
                <div class="block-content tab-content overflow-hidden">
                    <!-- 全部 -->
                    <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                        aria-labelledby="search-projects-tab">
                        <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                            總訂單數: <span class="text-primary fw-bold">{{ orderData.total }}</span>
                        </div>
                        <table class="table table-striped table-vcenter">
                            <!-- 欄位名稱 -->
                            <thead>
                                <tr>
                                    <th style="width: 50%">商品(點開看詳細資訊)</th>
                                    <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                        訂單總價
                                    </th>
                                    <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                        訂單日期
                                    </th>
                                    <th class="text-center" style="width: 20%"> 訂單狀態</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="item in orderList" :key="item.orderId" :value='item.value'
                                    :label="item.label">
                                    <td>
                                        <div v-for="arry in item.orderItemList" :key="arry.orderItemId"
                                            :value='item.value' :label="item.label">
                                            <div>商品ID{{ arry.productId }}商品數量{{ arry.quantity }}商品小計{{ arry.amount }}
                                            </div>
                                        </div>
                                    </td>
                                    <td class="d-none d-lg-table-cell text-center text-sm">

                                        {{ item.payment }}

                                    </td>
                                    <td class="d-none d-lg-table-cell font-size-xl text-center fw-semibold">
                                        {{ item.updateTime }}
                                    </td>
                                    <td class="font-size-xl text-center fw-semibold">
                                        <span
                                            class="fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success fs-sm">
                                            {{ item.status }}
                                        </span>
                                    </td>
                                </tr>
                                <!-- 到這裡是一格 -->
                            </tbody>
                            <!-- 這裡是分頁 -->
                        </table>
                    </div>
                    <!-- END Projects -->

                    <!-- 付款 -->
                    <div class="tab-pane fade fade-up" id="search-users" role="tabpanel"
                        aria-labelledby="search-users-tab">
                        <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                            aria-labelledby="search-projects-tab">
                            <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                                總訂單數: <span class="text-primary fw-bold">{{ orderData.total }}</span>
                            </div>
                            <table class="table table-striped table-vcenter">
                                <!-- 欄位名稱 -->
                                <thead>
                                    <tr>
                                        <th style="width: 50%">商品(點開看詳細資訊)</th>
                                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                            訂單總價
                                        </th>
                                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                            訂單日期
                                        </th>
                                        <th class="text-center" style="width: 20%"> 訂單狀態</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in orderList" :key="item.orderId" :value='item.value'
                                        :label="item.label">
                                        <td>
                                            <div v-for="arry in item.orderItemList" :key="arry.orderItemId"
                                                :value='item.value' :label="item.label">
                                                <div>商品ID{{ arry.productId }}商品數量{{ arry.quantity }}商品小計{{ arry.amount
                                                }}
                                                </div>
                                            </div>
                                        </td>
                                        <td class="d-none d-lg-table-cell text-center text-sm">

                                            {{ item.payment }}

                                        </td>
                                        <td class="d-none d-lg-table-cell font-size-xl text-center fw-semibold">
                                            {{ item.updateTime }}
                                        </td>
                                        <td class="font-size-xl text-center fw-semibold">
                                            <span
                                                class="fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success fs-sm">
                                                {{ item.status }}
                                            </span>
                                        </td>
                                    </tr>
                                    <!-- 到這裡是一格 -->
                                </tbody>
                                <!-- 這裡是分頁 -->
                            </table>
                        </div>

                    </div>
                    <!-- END Users -->

                    <!-- Classic -->
                    <div class="tab-pane fade fade-up" id="search-classic" role="tabpanel"
                        aria-labelledby="search-classic-tab">
                        <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                            aria-labelledby="search-projects-tab">
                            <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                                總訂單數: <span class="text-primary fw-bold">{{ orderData.total }}</span>
                            </div>
                            <table class="table table-striped table-vcenter">
                                <!-- 欄位名稱 -->
                                <thead>
                                    <tr>
                                        <th style="width: 50%">商品(點開看詳細資訊)</th>
                                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                            訂單總價
                                        </th>
                                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                            訂單日期
                                        </th>
                                        <th class="text-center" style="width: 20%"> 訂單狀態</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 第一層for 印出多個列所以寫在tr -->
                                    <tr v-for="item in orderList" :key="item.orderId" :value='item.value'
                                        :label="item.label">
                                        <td>
                                            <!-- 因為要把資料塞在同一個td裏面所以在td裡寫第二個div -->
                                            <div v-for="arry in item.orderItemList" :key="arry.orderItemId"
                                                :value='item.value' :label="item.label">
                                                <div>商品ID{{ arry.productId }}商品數量{{ arry.quantity }}商品小計{{ arry.amount
                                                }}
                                                </div>
                                            </div>
                                        </td>
                                        <td class="d-none d-lg-table-cell text-center text-sm">

                                            {{ item.payment }}

                                        </td>
                                        <td class="d-none d-lg-table-cell font-size-xl text-center fw-semibold">
                                            {{ item.updateTime }}
                                        </td>
                                        <td class="font-size-xl text-center fw-semibold">
                                            <span
                                                class="fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success fs-sm">
                                                {{ item.status }}
                                            </span>
                                        </td>
                                    </tr>
                                    <!-- 到這裡是一格 -->
                                </tbody>
                                <!-- 這裡是分頁 -->
                            </table>
                        </div>

                    </div>
                    <!-- END Classic -->

                    <!-- Photos -->
                    <div class="tab-pane fade fade-up" id="search-photos" role="tabpanel"
                        aria-labelledby="search-photos-tab">
                        <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                            aria-labelledby="search-projects-tab">
                            <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                                總訂單數: <span class="text-primary fw-bold">{{ orderData.total }}</span>
                            </div>
                            <table class="table table-striped table-vcenter">
                                <!-- 欄位名稱 -->
                                <thead>
                                    <tr>
                                        <th style="width: 50%">商品(點開看詳細資訊)</th>
                                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                            訂單總價
                                        </th>
                                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                            訂單日期
                                        </th>
                                        <th class="text-center" style="width: 20%"> 訂單狀態</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in orderList" :key="item.orderId" :value='item.value'
                                        :label="item.label">
                                        <td>
                                            <div v-for="arry in item.orderItemList" :key="arry.orderItemId"
                                                :value='item.value' :label="item.label">
                                                <div>商品ID{{ arry.productId }}商品數量{{ arry.quantity }}商品小計{{ arry.amount
                                                }}
                                                </div>
                                            </div>
                                        </td>
                                        <td class="d-none d-lg-table-cell text-center text-sm">

                                            {{ item.payment }}

                                        </td>
                                        <td class="d-none d-lg-table-cell font-size-xl text-center fw-semibold">
                                            {{ item.updateTime }}
                                        </td>
                                        <td class="font-size-xl text-center fw-semibold">
                                            <span
                                                class="fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success fs-sm">
                                                {{ item.status }}
                                            </span>
                                        </td>
                                    </tr>
                                    <!-- 到這裡是一格 -->
                                </tbody>
                                <!-- 這裡是分頁 -->
                            </table>
                        </div>
                    </div>
                    <!-- END Photos -->
                </div>
            </template>
        </BaseBlock>
        <!-- END Results -->
    </div>
    <!-- Page Content -->
    <div class="content content-boxed">
        <!-- Invoice -->
        <BaseBlock title="#INV0625">
            <template #options>
                <button type="button" class="btn-block-option" @click="printPage()">
                    <i class="si si-printer me-1"></i> Print Invoice
                </button>
            </template>

            <div class="p-sm-4 p-xl-7">
                <!-- Invoice Info -->
                <div class="row mb-4">
                    <!-- Company Info -->
                    <div class="col-6 fs-sm">
                        <p class="h3">Company</p>
                        <address>
                            Street Address<br />
                            State, City<br />
                            Region, Postal Code<br />
                            ltd@example.com
                        </address>
                    </div>
                    <!-- END Company Info -->

                    <!-- Client Info -->
                    <div class="col-6 text-end fs-sm">
                        <p class="h3">Client</p>
                        <address>
                            Street Address<br />
                            State, City<br />
                            Region, Postal Code<br />
                            ctr@example.com
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
                                <th>產品名稱</th>
                                <th class="text-center" style="width: 90px">數量</th>
                                <th class="text-end" style="width: 120px">單價</th>
                                <th class="text-end" style="width: 120px">小計</th>
                            </tr>
                        </thead>
                        <tbody v-for="item in orderList" :key="item.orderId" :value='item.value' :label="item.label">
                            <!-- 這邊開始是一格商品 -->
                            <tr v-for="arry in item.orderItemList" :key="arry.orderItemId" :value='item.value'
                                :label="item.label">
                                <td class="text-center">{{ arry.orderItemId }}</td>
                                <td>
                                    <p class="fw-semibold mb-1">{{ arry.productName }}</p>
                                </td>
                                <td class="text-center">
                                    <span class="badge rounded-pill bg-primary">{{ arry.quantity }}</span>
                                </td>
                                <td class="text-end">{{ arry.amount / arry.quantity }}</td>
                                <td class="text-end">{{ arry.amount }}</td>
                            </tr>

                            <!-- 這邊是總價 -->
                            <tr>
                                <td colspan="4" class="fw-semibold text-end">總價</td>
                                <td class="text-end">{{ item.payment }}</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="fw-semibold text-end">運費</td>
                                <td class="text-end">0</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="fw-semibold text-end">狀態</td>
                                <td class="text-end">{{ item.status }}</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="fw-bold text-uppercase text-end bg-body-light">
                                    訂單價格
                                </td>
                                <td class="fw-bold text-end bg-body-light">{{ item.payment }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- END Table -->

                <!-- Footer -->
                <p class="fs-sm text-muted text-center">
                    下次再來買
                </p>
                <!-- END Footer -->
            </div>
        </BaseBlock>
        <!-- END Invoice -->
    </div>
    <!-- END Page Content -->
</template>
