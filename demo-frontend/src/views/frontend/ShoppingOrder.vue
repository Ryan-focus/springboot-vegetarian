<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
import Swal from "sweetalert2";
import axios from "axios";
// 宣告的直放這裡
const url = "localhost:8088";
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


// 功能放這裡
const getAxios = function () {
    axios
        .get(`http://${url}/${userId}/order`)
        .then((res) => {
            //獲取伺服器的回傳資料
            orderData.value = res.data;
            orderList.value = orderData.value.results
            console.log(orderData)
            console.log(orderList)
        })
        .catch((error) => {
            console.log(error, "失敗");
        });
};
getAxios();
</script>

<template >


    <div v-for="item in orderList" :key="item.orderId" :value='item.value' :label="item.label">
        <div>訂單總價：{{ item.payment }}</div>
        <div>訂單狀態：{{ item.status }}</div>
        <div>訂單產生時間：{{ item.updateTime }}</div>
        <div v-for="arry in item.orderItemList" :key="arry.orderItemId" :value='item.value' :label="item.label">
            <div>商品ID{{ arry.productId }}商品數量{{ arry.quantity }}商品小計{{ arry.amount }}</div>
            <div></div>

        </div>
    </div>



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
                                        訂單狀態
                                    </th>
                                    <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                                        訂單時間
                                    </th>
                                    <th class="text-center" style="width: 20%">訂單狀態</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <h4 class="h5 mt-3 mb-2">
                                            <a href="javascript:void(0)">Web Application Framework</a>
                                        </h4>
                                        <p class="d-none d-sm-block text-muted">
                                            Maecenas ultrices, justo vel imperdiet gravida, urna
                                            ligula hendrerit nibh, ac cursus nibh sapien in purus.
                                            Mauris tincidunt tincidunt turpis in porta.
                                        </p>
                                    </td>
                                    <td class="d-none d-lg-table-cell text-center text-sm">
                                        <span
                                            class="fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success fs-sm">

                                        </span>
                                    </td>
                                    <td class="d-none d-lg-table-cell font-size-xl text-center fw-semibold">
                                        1603
                                    </td>
                                    <td class="font-size-xl text-center fw-semibold">$ 35,287</td>
                                </tr>
                                <!-- 到這裡是一格 -->
                            </tbody>
                            <!-- 這裡是分頁 -->
                        </table>
                        <nav aria-label="Projects Search Navigation">
                            <ul class="pagination pagination-sm">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" tabindex="-1" aria-label="Previous">
                                        Prev
                                    </a>
                                </li>
                                <li class="page-item active">
                                    <a class="page-link" href="javascript:void(0)">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">2</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">3</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">4</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" aria-label="Next">
                                        Next
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- END Projects -->

                    <!-- Users -->
                    <div class="tab-pane fade fade-up" id="search-users" role="tabpanel"
                        aria-labelledby="search-users-tab">
                        <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                            <span class="text-primary fw-bold">192</span> results found for
                            <mark class="text-danger">client</mark>
                        </div>
                        <table class="table table-striped table-vcenter">
                            <thead>
                                <tr>
                                    <th class="text-center" style="width: 70px">
                                        <i class="si si-user"></i>
                                    </th>
                                    <th>Name</th>
                                    <th class="d-none d-sm-table-cell">Email</th>
                                    <th class="d-none d-lg-table-cell" style="width: 15%">
                                        Access
                                    </th>
                                    <th class="text-center" style="width: 80px">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar13.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Brian Cruz</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client1@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success">VIP</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar12.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Jack Estrada</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client2@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-warning-light text-warning">Trial</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar5.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Megan Fuller</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client3@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-danger-light text-danger">Disabled</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar16.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Justin Hunt</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client4@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-info-light text-info">Business</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar13.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Jack Greene</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client5@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success">VIP</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar3.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Helen Jacobs</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client6@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success">VIP</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar15.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Albert Ray</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client7@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-info-light text-info">Business</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar14.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Wayne Garcia</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client8@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success">VIP</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar13.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Jesse Fisher</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client9@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success">VIP</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <img class="img-avatar img-avatar48" src="/assets/media/avatars/avatar10.jpg"
                                            alt="" />
                                    </td>
                                    <td class="fw-semibold">
                                        <a href="javascript:void(0)">Wayne Garcia</a>
                                    </td>
                                    <td class="d-none d-sm-table-cell">client10@example.com</td>
                                    <td class="d-none d-lg-table-cell">
                                        <span
                                            class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success">VIP</span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-pencil-alt"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-alt-secondary">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <nav aria-label="Users Search Navigation">
                            <ul class="pagination pagination-sm">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" tabindex="-1" aria-label="Previous">
                                        Prev
                                    </a>
                                </li>
                                <li class="page-item active">
                                    <a class="page-link" href="javascript:void(0)">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">2</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">3</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">4</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" aria-label="Next">
                                        Next
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- END Users -->

                    <!-- Classic -->
                    <div class="tab-pane fade fade-up" id="search-classic" role="tabpanel"
                        aria-labelledby="search-classic-tab">
                        <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                            <span class="text-primary fw-bold">280</span> websites found for
                            <mark class="text-danger">OneUI</mark>
                        </div>
                        <div class="row py-3">
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="h5 mb-1">
                                    <a href="javascript:void(0)">OneUI - Bootstrap 5 UI Framework</a>
                                </h4>
                                <div class="fs-sm fw-medium text-success mb-1">
                                    https://pixelcave.com/
                                </div>
                                <p class="fs-sm text-muted">
                                    Donec lacinia venenatis metus at bibendum? In hac habitasse
                                    platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.
                                    Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam
                                    quis ligula elit.
                                </p>
                            </div>
                        </div>
                        <nav aria-label="Classic Search Navigation">
                            <ul class="pagination pagination-sm">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" tabindex="-1" aria-label="Previous">
                                        Prev
                                    </a>
                                </li>
                                <li class="page-item active">
                                    <a class="page-link" href="javascript:void(0)">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">2</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">3</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">4</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" aria-label="Next">
                                        Next
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- END Classic -->

                    <!-- Photos -->
                    <div class="tab-pane fade fade-up" id="search-photos" role="tabpanel"
                        aria-labelledby="search-photos-tab">
                        <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                            <span class="text-primary fw-bold">85</span> photos found for
                            <mark class="text-danger">wallpaper</mark>
                        </div>
                        <div class="row g-sm push">
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo1.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo2.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo6.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo21.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo22.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo9.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo23.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo24.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo25.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo27.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo28.jpg" alt="Example Photo" />
                            </div>
                            <div class="col-md-6 col-lg-4 col-xl-3 push">
                                <img class="img-fluid" src="/assets/media/photos/photo30.jpg" alt="Example Photo" />
                            </div>
                        </div>
                        <nav aria-label="Photos Search Navigation">
                            <ul class="pagination pagination-sm">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" tabindex="-1" aria-label="Previous">
                                        Prev
                                    </a>
                                </li>
                                <li class="page-item active">
                                    <a class="page-link" href="javascript:void(0)">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">2</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">3</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)">4</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="javascript:void(0)" aria-label="Next">
                                        Next
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- END Photos -->
                </div>
            </template>
        </BaseBlock>
        <!-- END Results -->
    </div>
    <!-- END Page Content -->
</template>
