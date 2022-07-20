<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
import Swal from "sweetalert2";
import axios from "axios";
// 宣告的直放這裡
const url = "localhost:8088";
//用來取登入後的userId
var user = null;

if (window.localStorage.getItem("access-user") != null) {
    user = JSON.parse(window.localStorage.getItem("access-user"));
} else if (window.localStorage.getItem("access-admin") != null) {
    user = JSON.parse(window.localStorage.getItem("access-admin"));
}
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
                            未付款
                        </button>
                    </li>
                    <li class="nav-item">
                        <button type="button" class="nav-link" id="search-classic-tab" data-bs-toggle="tab"
                            data-bs-target="#search-classic" role="tab" aria-controls="search-classic"
                            aria-selected="false">
                            已出貨
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
                        <!-- Page Content -->
                        <div class="content content-boxed">
                            <!-- Invoice -->
                            <BaseBlock title="歷史訂單">
                                <template #options>
                                    <button type="button" class="btn-block-option" @click="printPage()">
                                        <i class="si si-printer me-1"></i> 點我列印
                                    </button>
                                </template>

                                <div class="p-sm-4 p-xl-7">
                                    <!-- Table -->
                                    <div class="table-responsive push" v-for="item in orderList" :key="item.orderId"
                                        :value='item.value' :label="item.label">
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
                                                    桃園市<br />
                                                    中壢區<br />
                                                    新生路421號<br />
                                                    {{ user.data.user.email }}
                                                </address>
                                            </div>
                                            <!-- END Client Info -->
                                        </div>
                                        <!-- END Invoice Info -->
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
                                            <tbody>
                                                <!-- 這邊開始是一格商品 -->
                                                <tr v-for="(arry, i) in item.orderItemList" :key="i" :value='item.value'
                                                    :label="item.label">
                                                    <td class="text-center">{{ i + 1 }}</td>
                                                    <td>
                                                        <p class="fw-semibold mb-1">{{ arry.productName }}</p>
                                                    </td>
                                                    <td class="text-center">
                                                        <span class="badge rounded-pill bg-primary">{{ arry.quantity
                                                        }}</span>
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
                                                    <td colspan="4"
                                                        class="fw-bold text-uppercase text-end bg-body-light">
                                                        訂單價格
                                                    </td>
                                                    <td class="fw-bold text-end bg-body-light">{{ item.payment }}</td>
                                                </tr>
                                            </tbody>
                                            <hr>
                                            <hr>
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
                    </div>
                    <!-- END Projects -->

                    <!-- 付款 -->
                    <div class="tab-pane fade fade-up" id="search-users" role="tabpanel"
                        aria-labelledby="search-users-tab">
                        <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                            aria-labelledby="search-projects-tab">
                            <!-- Page Content -->
                            <div class="content content-boxed">
                                <!-- Invoice -->
                                <BaseBlock title="歷史訂單">
                                    <template #options>
                                        <button type="button" class="btn-block-option" @click="printPage()">
                                            <i class="si si-printer me-1"></i> 點我列印
                                        </button>
                                    </template>

                                    <div class="p-sm-4 p-xl-7">
                                        <!-- Table -->
                                        <div class="table-responsive push" v-for="item in orderList" :key="item.orderId"
                                            :value='item.value' :label="item.label">
                                            <div v-if="item.status == '未付款'">
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
                                                            桃園市<br />
                                                            中壢區<br />
                                                            新生路421號<br />
                                                            {{ user.data.user.email }}
                                                        </address>
                                                    </div>
                                                    <!-- END Client Info -->
                                                </div>
                                                <!-- END Invoice Info -->
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
                                                    <tbody>
                                                        <!-- 這邊開始是一格商品 -->
                                                        <tr v-for="(arry, i) in item.orderItemList" :key="i"
                                                            :value='item.value' :label="item.label">
                                                            <td class="text-center">{{ i + 1 }}</td>
                                                            <td>
                                                                <p class="fw-semibold mb-1">{{ arry.productName }}</p>
                                                            </td>
                                                            <td class="text-center">
                                                                <span class="badge rounded-pill bg-primary">{{
                                                                        arry.quantity
                                                                }}</span>
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
                                                            <td colspan="4"
                                                                class="fw-bold text-uppercase text-end bg-body-light">
                                                                訂單價格
                                                            </td>
                                                            <td class="fw-bold text-end bg-body-light">{{ item.payment
                                                            }}
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                    <hr>
                                                    <hr>
                                                </table>
                                            </div>
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
                        </div>

                    </div>
                    <!-- END Users -->

                    <!-- 未收貨 -->
                    <div class="tab-pane fade fade-up" id="search-classic" role="tabpanel"
                        aria-labelledby="search-classic-tab">
                        <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                            aria-labelledby="search-projects-tab">
                            <!-- Page Content -->
                            <div class="content content-boxed">
                                <!-- Invoice -->
                                <BaseBlock title="歷史訂單">
                                    <template #options>
                                        <button type="button" class="btn-block-option" @click="printPage()">
                                            <i class="si si-printer me-1"></i> 點我列印
                                        </button>
                                    </template>

                                    <div class="p-sm-4 p-xl-7">
                                        <!-- Table -->
                                        <div class="table-responsive push" v-for="item in orderList" :key="item.orderId"
                                            :value='item.value' :label="item.label">
                                            <div v-if="item.status == '已出貨'">
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
                                                            桃園市<br />
                                                            中壢區<br />
                                                            新生路421號<br />
                                                            {{ user.data.user.email }}
                                                        </address>
                                                    </div>
                                                    <!-- END Client Info -->
                                                </div>
                                                <!-- END Invoice Info -->
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
                                                    <tbody>
                                                        <!-- 這邊開始是一格商品 -->
                                                        <tr v-for="(arry, i) in item.orderItemList" :key="i"
                                                            :value='item.value' :label="item.label">
                                                            <td class="text-center">{{ i + 1 }}</td>
                                                            <td>
                                                                <p class="fw-semibold mb-1">{{ arry.productName }}</p>
                                                            </td>
                                                            <td class="text-center">
                                                                <span class="badge rounded-pill bg-primary">{{
                                                                        arry.quantity
                                                                }}</span>
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
                                                            <td colspan="4"
                                                                class="fw-bold text-uppercase text-end bg-body-light">
                                                                訂單價格
                                                            </td>
                                                            <td class="fw-bold text-end bg-body-light">{{ item.payment
                                                            }}
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                    <hr>
                                                    <hr>
                                                </table>
                                            </div>
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
                        </div>

                    </div>
                    <!-- END 未收貨 -->

                    <!-- 完成 -->
                    <div class="tab-pane fade fade-up" id="search-photos" role="tabpanel"
                        aria-labelledby="search-photos-tab">
                        <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                            aria-labelledby="search-projects-tab">
                            <!-- Page Content -->
                            <div class="content content-boxed">
                                <!-- Invoice -->
                                <BaseBlock title="歷史訂單">
                                    <template #options>
                                        <button type="button" class="btn-block-option" @click="printPage()">
                                            <i class="si si-printer me-1"></i> 點我列印
                                        </button>
                                    </template>

                                    <div class="p-sm-4 p-xl-7">
                                        <!-- Table -->
                                        <div class="table-responsive push" v-for="item in orderList" :key="item.orderId"
                                            :value='item.value' :label="item.label">
                                            <div v-if="item.status == '訂單完成'">
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
                                                            桃園市<br />
                                                            中壢區<br />
                                                            新生路421號<br />
                                                            {{ user.data.user.email }}
                                                        </address>
                                                    </div>
                                                    <!-- END Client Info -->
                                                </div>
                                                <!-- END Invoice Info -->
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
                                                    <tbody>
                                                        <!-- 這邊開始是一格商品 -->
                                                        <tr v-for="(arry, i) in item.orderItemList" :key="i"
                                                            :value='item.value' :label="item.label">
                                                            <td class="text-center">{{ i + 1 }}</td>
                                                            <td>
                                                                <p class="fw-semibold mb-1">{{ arry.productName }}</p>
                                                            </td>
                                                            <td class="text-center">
                                                                <span class="badge rounded-pill bg-primary">{{
                                                                        arry.quantity
                                                                }}</span>
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
                                                            <td colspan="4"
                                                                class="fw-bold text-uppercase text-end bg-body-light">
                                                                訂單價格
                                                            </td>
                                                            <td class="fw-bold text-end bg-body-light">{{ item.payment
                                                            }}
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                    <hr>
                                                    <hr>
                                                </table>
                                            </div>
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
                        </div>
                    </div>
                    <!-- END Photos -->
                </div>
            </template>
        </BaseBlock>
        <!-- END Results -->
    </div>
</template>
