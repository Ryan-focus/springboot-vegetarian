<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";

//預設傳值伺服器與[params]
const url = "localhost:8088";
//接收的資料ref
const resData = ref();
const total = ref();
const passTotal = ref();
const testTotal = ref();
const rejectTotal = ref();

//取得全部的order
const getAxios = function () {
  axios
    .get(`http://${url}/pos`)
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      total.value = res.data.total;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//取得開通中的商家
const getPass = function () {
  axios
    .get(`http://${url}/pos`, {
      params: {
        statusCategory: "開通中",
      },
    })
    .then((res) => {
      //獲取伺服器的回傳資料
      passTotal.value = res.data.total;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//取得試用期中的商家
const testUsing = function () {
  axios
    .get(`http://${url}/pos`, {
      params: {
        statusCategory: "試用期",
      },
    }).then((res) => {
      //獲取伺服器的回傳資料
      testTotal.value = res.data.total;
    })
};
//取得未開通的商家
const getReject = function () {
  axios
    .get(`http://${url}/pos`, {
      params: {
        statusCategory: "未開通",
      },
    })
    .then((res) => {
      //獲取伺服器的回傳資料
      rejectTotal.value = res.data.total;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//執行Axios
getAxios();
getPass();
getReject();
testUsing();
</script>

<template>
  <!-- Hero -->

  <div class="content">
    <div
      class="d-flex flex-column flex-md-row justify-content-md-between align-items-md-center py-2 text-center text-md-start">
      <div class="flex-grow-1 mb-1 mb-md-0">
        <h1 class="h3 fw-bold mb-2">預訂分析</h1>
        <h2 class="h6 fw-medium fw-medium text-muted mb-0">
          歡迎 管理員
          <RouterLink :to="{ name: 'backend-pages-generic-profile' }" class="fw-semibold">{{ admin.data.user.userName }}
          </RouterLink>
        </h2>
      </div>
      <div class="mt-3 mt-md-0 ms-md-3 space-x-1">
        <a href="javascript:void(0)" class="btn btn-sm btn-alt-secondary space-x-1">
          <i class="fa fa-cogs opacity-50"></i>
          <span>設定</span>
        </a>
        <div class="dropdown d-inline-block">
          <button type="button" class="btn btn-sm btn-alt-secondary space-x-1" id="dropdown-analytics-overview"
            data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-calendar-alt opacity-50"></i>
            <span>全部時間</span>
            <i class="fa fa-fw fa-angle-down"></i>
          </button>
          <div class="dropdown-menu dropdown-menu-end fs-sm" aria-labelledby="dropdown-analytics-overview">
            <a class="dropdown-item fw-medium" href="javascript:void(0)">最近30天</a>
            <a class="dropdown-item fw-medium" href="javascript:void(0)">最近一個月</a>
            <a class="dropdown-item fw-medium" href="javascript:void(0)">最近三個月</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item fw-medium" href="javascript:void(0)">今年</a>
            <a class="dropdown-item fw-medium" href="javascript:void(0)">去年</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
              href="javascript:void(0)">
              <span>全部時間</span>
              <i class="fa fa-check"></i>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <!-- Overview -->
    <div class="row items-push">
      <div class="col-sm-6 col-xxl-3">
        <!-- 待處理訂單 全部合作商家 -->
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ total }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  全部合作商家
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-gem fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看全部合作商家</span>
                <i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </a>
            </div>
          </template>
        </BaseBlock>
        <!-- END Pending Orders -->
      </div>
      <div class="col-sm-6 col-xxl-3">
        <!-- New Customers -->
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ passTotal }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  預訂功能開通中的商家
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-user-circle fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看預訂功能開通中的商家</span>
                <i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </a>
            </div>
          </template>
        </BaseBlock>
        <!-- END New Customers -->
      </div>
      <div class="col-sm-6 col-xxl-3">
        <!-- Messages -->
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ testTotal }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  在試用期中的商家
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-paper-plane fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看在試用期中的商家</span>
                <i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </a>
            </div>
          </template>
        </BaseBlock>
        <!-- END Messages -->
      </div>
      <div class="col-sm-6 col-xxl-3">
        <!-- Conversion Rate -->
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ rejectTotal }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  待開通的商家
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="fa fa-chart-bar fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看待開通的商家</span>
                <i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </a>
            </div>
          </template>
        </BaseBlock>
        <!-- END Conversion Rate-->
      </div>
    </div>
    <!-- END Overview -->

    <!-- Statistics -->
    <div class="row">
      <!-- <div class="col-xl-8 col-xxl-9 d-flex flex-column"> -->
      <!-- Earnings Summary -->
      <!-- <BaseBlock
          title="Earnings Summary"
          class="flex-grow-1 d-flex flex-column"
        >
          <template #options>
            <button type="button" class="btn-block-option">
              <i class="si si-settings"></i>
            </button>
          </template>

          <template #content>
            <div
              class="block-content block-content-full flex-grow-1 d-flex align-items-center"
            >
              <BarChart
                :chart-data="earningsData"
                :options="earningsOptions"
                class="w-100"
              />
            </div>
            <div class="block-content bg-body-light">
              <div class="row items-push text-center w-100">
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt
                      class="fs-3 fw-bold d-inline-flex align-items-center space-x-2"
                    >
                      <i class="fa fa-caret-up fs-base text-success"></i>
                      <span>2.5%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      Customer Growth
                    </dd>
                  </dl>
                </div>
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt
                      class="fs-3 fw-bold d-inline-flex align-items-center space-x-2"
                    >
                      <i class="fa fa-caret-up fs-base text-success"></i>
                      <span>3.8%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">Page Views</dd>
                  </dl>
                </div>
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt
                      class="fs-3 fw-bold d-inline-flex align-items-center space-x-2"
                    >
                      <i class="fa fa-caret-down fs-base text-danger"></i>
                      <span>1.7%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      New Products
                    </dd>
                  </dl>
                </div>
              </div>
            </div>
          </template>
        </BaseBlock> -->
      <!-- END Earnings Summary -->
      <!-- </div> -->
      <!-- <div class="col-xl-4 col-xxl-3 d-flex flex-column"> -->
      <!-- Last 2 Weeks -->
      <!-- <div class="row items-push flex-grow-1">
          <div class="col-md-6 col-xl-12">
            <BaseBlock class="d-flex flex-column h-100 mb-0">
              <template #content>
                <div
                  class="block-content flex-grow-1 d-flex justify-content-between"
                >
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold">570</dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      Total Orders
                    </dd>
                  </dl>
                  <div>
                    <div
                      class="d-inline-block px-2 py-1 rounded-3 fs-xs fw-semibold text-danger bg-danger-light"
                    >
                      <i class="fa fa-caret-down me-1"></i>
                      2.2%
                    </div>
                  </div>
                </div>
                <div class="block-content p-1 text-center overflow-hidden">
                  <LineChart
                    :chart-data="totalOrdersData"
                    :options="totalOrdersOptions"
                    style="height: 90px"
                  />
                </div>
              </template>
            </BaseBlock>
          </div>
          <div class="col-md-6 col-xl-12">
            <BaseBlock class="d-flex flex-column h-100 mb-0">
              <template #content>
                <div
                  class="block-content flex-grow-1 d-flex justify-content-between"
                >
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold">$5,234.21</dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      Total Earnings
                    </dd>
                  </dl>
                  <div>
                    <div
                      class="d-inline-block px-2 py-1 rounded-3 fs-xs fw-semibold text-success bg-success-light"
                    >
                      <i class="fa fa-caret-up me-1"></i>
                      4.2%
                    </div>
                  </div>
                </div>
                <div class="block-content p-1 text-center overflow-hidden">
                  <LineChart
                    :chart-data="totalEarningsData"
                    :options="totalEarningsOptions"
                    style="height: 90px"
                  />
                </div>
              </template>
            </BaseBlock>
          </div>
          <div class="col-xl-12">
            <BaseBlock class="d-flex flex-column h-100 mb-0">
              <template #content>
                <div
                  class="block-content flex-grow-1 d-flex justify-content-between"
                >
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold">264</dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      New Customers
                    </dd>
                  </dl>
                  <div>
                    <div
                      class="d-inline-block px-2 py-1 rounded-3 fs-xs fw-semibold text-success bg-success-light"
                    >
                      <i class="fa fa-caret-up me-1"></i>
                      9.3%
                    </div>
                  </div>
                </div>
                <div class="block-content p-1 text-center overflow-hidden"> -->
      <!-- New Customers Chart Container -->
      <!-- <LineChart
                    :chart-data="newCustomersData"
                    :options="newCustomersOptions"
                    style="height: 90px"
                  />
                </div>
              </template>
            </BaseBlock> -->
      <!-- </div> -->
      <!-- </div> -->
      <!-- END Last 2 Weeks -->
      <!-- </div> -->
    </div>
    <!-- END Statistics -->

    <!-- Recent Orders -->
    <!-- <BaseBlock title="最新商家審核申請">
      <template #options>
        <div class="space-x-1">
          <button
            type="button"
            class="btn btn-sm btn-alt-secondary"
            @click="
              () => {
                orderSearch = !orderSearch;
              }
            "
          >
            <i class="fa fa-search"></i>
          </button>
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
                href="javascript:void(0)"
              >
                未審核
                <span class="badge bg-primary rounded-pill">20</span>
              </a>
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)"
              >
                審核中
                <span class="badge bg-primary rounded-pill">72</span>
              </a>
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)"
              >
                已完成
                <span class="badge bg-primary rounded-pill">890</span>
              </a>
              <a
                class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)"
              >
                全部
                <span class="badge bg-primary rounded-pill">997</span>
              </a>
            </div>
          </div>
        </div>
      </template>

      <template #content>
        <div
          v-if="orderSearch"
          id="one-dashboard-search-orders"
          class="block-content border-bottom"
        >-->
    <!-- Search Form -->
    <!-- <form @sumit.prevent>
            <div class="push">
              <div class="input-group">
                <input
                  type="text"
                  class="form-control form-control-alt"
                  id="one-ecom-orders-search"
                  name="one-ecom-orders-search"
                  placeholder="搜尋所有訂單.."
                />
                <span class="input-group-text bg-body border-0">
                  <i class="fa fa-search"></i>
                </span>
              </div>
            </div>
          </form> -->
    <!-- END Search Form -->
    <!-- </div>
        <div class="block-content block-content-full"> -->
    <!-- Recent Orders Table -->
    <!-- <div class="table-responsive">
            <table class="table table-hover table-vcenter">
              <thead>
                <tr>
                  <th>訂單編號</th>
                  <th class="d-none d-xl-table-cell">消費者</th>
                  <th>處理狀態</th>
                  <th class="d-none d-sm-table-cell text-center">利潤</th>
                  <th class="d-none d-sm-table-cell text-end">訂單建立時間</th>
                  <th class="d-none d-sm-table-cell text-end">消費金額</th>
                </tr>
              </thead>
              <tbody class="fs-sm">
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00925
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Marie Duncan</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">Photographer</p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success"
                      >Completed</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 8%"
                        aria-valuenow="8"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">8%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    7 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$786,81</strong>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00924
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Jack Estrada</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">Photographer</p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-info-light text-info"
                      >Active</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 6%"
                        aria-valuenow="6"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">6%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    26 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$1184,20</strong>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00923
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Megan Fuller</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">Web developer</p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success"
                      >Completed</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 25%"
                        aria-valuenow="25"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">25%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    19 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$2379,44</strong>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00922
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Lisa Jenkins</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">
                      Application Manager
                    </p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-warning-light text-warning"
                      >Pending</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 18%"
                        aria-valuenow="18"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">18%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    13 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$458,52</strong>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00921
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Brian Stevens</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">Photographer</p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success"
                      >Completed</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 10%"
                        aria-valuenow="10"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">10%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    4 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$476,82</strong>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00920
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Jesse Fisher</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">Digital Nomad</p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-warning-light text-warning"
                      >Pending</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 23%"
                        aria-valuenow="23"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">23%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    23 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$1939,58</strong>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      ORD.00919
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">Premium</p>
                  </td>
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)"
                      >Carol Ray</a
                    >
                    <p class="fs-sm fw-medium text-muted mb-0">Web developer</p>
                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-info-light text-info"
                      >Active</span
                    >
                  </td>
                  <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 14%"
                        aria-valuenow="14"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">14%</p>
                  </td>
                  <td
                    class="d-none d-sm-table-cell fw-semibold text-muted text-end"
                  >
                    15 min ago
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>$2200,10</strong>
                  </td>
                </tr>
              </tbody>
            </table>
          </div> -->
    <!-- END Recent Orders Table -->
    <!-- </div> -->
    <!-- <div class="block-content block-content-full bg-body-light"> -->
    <!-- Pagination -->
    <!-- <nav aria-label="Photos Search Navigation">
            <ul class="pagination pagination-sm justify-content-end mb-0">
              <li class="page-item">
                <a
                  class="page-link"
                  href="javascript:void(0)"
                  tabindex="-1"
                  aria-label="Previous"
                >
                  上一頁
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
                <a
                  class="page-link"
                  href="javascript:void(0)"
                  aria-label="Next"
                >
                  下一頁
                </a>
              </li>
            </ul>
          </nav> -->
    <!-- END Pagination -->
    <!-- </div>
      </template>
    </BaseBlock> -->
    <!-- END Recent Orders -->
  </div>
  <!-- END Page Content -->
</template>
<script>
export default {
  data() {
    return {
      admin: "",
    };
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem("access-admin"));
  },
};
</script>
