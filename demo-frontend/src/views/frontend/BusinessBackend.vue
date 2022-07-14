<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
// eslint-disable-next-line no-unused-vars
import { reactive, ref } from "vue";
import axios from "axios";
import moment from 'moment';

// vue-chart-3, for more info and examples you can check out https://vue-chart-3.netlify.app/ and http://www.chartjs.org/docs/ -->
// import { LineChart, BarChart } from "vue-chart-3";
// import { Chart, registerables } from "chart.js";

// Chart.register(...registerables);

// Set Global Chart.js configuration
// Chart.defaults.color = "#818d96";
// Chart.defaults.scale.grid.lineWidth = 0;
// Chart.defaults.scale.beginAtZero = true;
// Chart.defaults.datasets.bar.maxBarThickness = 45;
// Chart.defaults.elements.bar.borderRadius = 4;
// Chart.defaults.elements.bar.borderSkipped = false;
// Chart.defaults.elements.point.radius = 0;
// Chart.defaults.elements.point.hoverRadius = 0;
// Chart.defaults.plugins.tooltip.radius = 3;
// Chart.defaults.plugins.legend.labels.boxWidth = 10;

// Helper variables
const orderSearch = ref(false);

// Chart Earnings data
// const earningsData = reactive({
//   labels: ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"],
//   datasets: [
//     {
//       label: "This Week",
//       fill: true,
//       backgroundColor: "rgba(100, 116, 139, .7)",
//       borderColor: "transparent",
//       pointBackgroundColor: "rgba(100, 116, 139, 1)",
//       pointBorderColor: "#fff",
//       pointHoverBackgroundColor: "#fff",
//       pointHoverBorderColor: "rgba(100, 116, 139, 1)",
//       data: [716, 628, 1056, 560, 956, 890, 790],
//     },
//     {
//       label: "Last Week",
//       fill: true,
//       backgroundColor: "rgba(100, 116, 139, .15)",
//       borderColor: "transparent",
//       pointBackgroundColor: "rgba(100, 116, 139, 1)",
//       pointBorderColor: "#fff",
//       pointHoverBackgroundColor: "#fff",
//       pointHoverBorderColor: "rgba(100, 116, 139, 1)",
//       data: [1160, 923, 1052, 1300, 880, 926, 963],
//     },
//   ],
// });

// Chart Earnings options
// const earningsOptions = reactive({
//   scales: {
//     x: {
//       display: false,
//       grid: {
//         drawBorder: false,
//       },
//     },
//     y: {
//       display: false,
//       grid: {
//         drawBorder: false,
//       },
//     },
//   },
//   interaction: {
//     intersect: false,
//   },
//   plugins: {
//     legend: {
//       labels: {
//         boxHeight: 10,
//         font: {
//           size: 14,
//         },
//       },
//     },
//     tooltip: {
//       callbacks: {
//         label: function (context) {
//           return context.dataset.label + ": $" + context.parsed.y;
//         },
//       },
//     },
//   },
// });

// // Chart Total Orders data
// const totalOrdersData = reactive({
//   labels: [
//     "MON",
//     "TUE",
//     "WED",
//     "THU",
//     "FRI",
//     "SAT",
//     "SUN",
//     "MON",
//     "TUE",
//     "WED",
//     "THU",
//     "FRI",
//     "SAT",
//     "SUN",
//   ],
//   datasets: [
//     {
//       label: "Total Orders",
//       fill: true,
//       backgroundColor: "rgba(220, 38, 38, .15)",
//       borderColor: "transparent",
//       pointBackgroundColor: "rgba(220, 38, 38, 1)",
//       pointBorderColor: "#fff",
//       pointHoverBackgroundColor: "#fff",
//       pointHoverBorderColor: "rgba(220, 38, 38, 1)",
//       data: [33, 29, 32, 37, 38, 30, 34, 28, 43, 45, 26, 45, 49, 39],
//     },
//   ],
// });

// // Chart Total Orders options
// const totalOrdersOptions = reactive({
//   maintainAspectRatio: false,
//   tension: 0.4,
//   scales: {
//     x: {
//       display: false,
//     },
//     y: {
//       display: false,
//     },
//   },
//   interaction: {
//     intersect: false,
//   },
//   plugins: {
//     legend: {
//       display: false,
//     },
//     tooltip: {
//       callbacks: {
//         label: function (context) {
//           return " " + context.parsed.y + " Orders";
//         },
//       },
//     },
//   },
// });

// // Chart Total Earnings data
// const totalEarningsData = reactive({
//   labels: [
//     "MON",
//     "TUE",
//     "WED",
//     "THU",
//     "FRI",
//     "SAT",
//     "SUN",
//     "MON",
//     "TUE",
//     "WED",
//     "THU",
//     "FRI",
//     "SAT",
//     "SUN",
//   ],
//   datasets: [
//     {
//       label: "Total Earnings",
//       fill: true,
//       backgroundColor: "rgba(101, 163, 13, .15)",
//       borderColor: "transparent",
//       pointBackgroundColor: "rgba(101, 163, 13, 1)",
//       pointBorderColor: "#fff",
//       pointHoverBackgroundColor: "#fff",
//       pointHoverBorderColor: "rgba(101, 163, 13, 1)",
//       data: [
//         716, 1185, 750, 1365, 956, 890, 1200, 968, 1158, 1025, 920, 1190, 720,
//         1352,
//       ],
//     },
//   ],
// });

// // Chart Total Earnings options
// const totalEarningsOptions = reactive({
//   maintainAspectRatio: false,
//   tension: 0.4,
//   scales: {
//     x: {
//       display: false,
//     },
//     y: {
//       display: false,
//     },
//   },
//   interaction: {
//     intersect: false,
//   },
//   plugins: {
//     legend: {
//       display: false,
//     },
//     tooltip: {
//       callbacks: {
//         label: function (context) {
//           return " $" + context.parsed.y;
//         },
//       },
//     },
//   },
// });

// Chart New Customers data
// const newCustomersData = reactive({
//   labels: [
//     "MON",
//     "TUE",
//     "WED",
//     "THU",
//     "FRI",
//     "SAT",
//     "SUN",
//     "MON",
//     "TUE",
//     "WED",
//     "THU",
//     "FRI",
//     "SAT",
//     "SUN",
//   ],
//   datasets: [
//     {
//       label: "Total Orders",
//       fill: true,
//       backgroundColor: "rgba(101, 163, 13, .15)",
//       borderColor: "transparent",
//       pointBackgroundColor: "rgba(101, 163, 13, 1)",
//       pointBorderColor: "#fff",
//       pointHoverBackgroundColor: "#fff",
//       pointHoverBorderColor: "rgba(101, 163, 13, 1)",
//       data: [25, 15, 36, 14, 29, 19, 36, 41, 28, 26, 29, 33, 23, 41],
//     },
//   ],
// });

// Chart New Customers options
// const newCustomersOptions = reactive({
//   maintainAspectRatio: false,
//   tension: 0.4,
//   scales: {
//     x: {
//       display: false,
//     },
//     y: {
//       display: false,
//     },
//   },
//   interaction: {
//     intersect: false,
//   },
//   plugins: {
//     legend: {
//       display: false,
//     },
//     tooltip: {
//       callbacks: {
//         label: function (context) {
//           return " " + context.parsed.y + " Customers";
//         },
//       },
//     },
//   },
// });

//預設傳值伺服器與[params]
const business = JSON.parse(window.localStorage.getItem("access-business"));
const businessID = business.data.business.businessId;
const url = "localhost:8088";
//接收的資料ref
//當日統計
//取得全部人數
//取得全部組數
const CountTotal = ref(); //全部人數 
const CountGroup = ref(); //全部組數

const resData = ref();

const getReserveList = function () {
  axios.get(`http://${url}/${businessID}/reserves`)
    .then((res) => {
      console.log(res);
      var total = 0;
      var people = 0;
      for (let i = 0; i <= res.data.length - 1; i++) {
        people += res.data[i].adult + res.data[i].baby + res.data[i].child;
        total++;
      }
      CountTotal.value = people; // 取得總人數
      CountGroup.value = total; // 取得組數
      resData.value = res.data;
    })

}

getReserveList();

</script>

<template>
  <!-- Hero -->
  <div class="content">
    <div
      class="d-flex flex-column flex-md-row justify-content-md-between align-items-md-center py-2 text-center text-md-start">
      <div class="flex-grow-1 mb-1 mb-md-0">
        <h1 class="h3 fw-bold mb-2">商家系統</h1>
        <h2 class="h6 fw-medium fw-medium text-muted mb-0">
          歡迎 合作商家 -

          <RouterLink :to="{ name: 'business-backend-profile' }" class="fw-semibold">
            <img class="rounded-circle" :src="`/assets/media/business/${business.data.business.businessPic}.jpg`"
              alt="Header Avatar" style="width: 21px" />

            {{
                business.data.business.businessName
            }}
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
        <!-- 待處理訂單 Pending Orders  :to 購物車模板-->
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ CountTotal }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  全部預訂餐廳人數
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-gem fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <RouterLink :to="{ name: 'backend-restaurants-restaurant-info' }"
                class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between">
                <span>查看全部預訂訂單</span><i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </RouterLink>
            </div>
          </template>
        </BaseBlock>
        <!-- END Pending Orders -->
      </div>
      <div class="col-sm-6 col-xxl-3">
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ CountGroup }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  全部預訂餐廳組數
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-gem fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <RouterLink :to="{ name: 'backend-restaurants-restaurant-info' }"
                class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between">
                <span>查看全部預訂訂單</span><i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </RouterLink>
            </div>
          </template>
        </BaseBlock>
        <!-- END Pending Orders -->
      </div>
    </div>
    <!-- END Overview -->

    <!-- Recent Orders -->
    <BaseBlock title="最新訂單">
      <template #options>
        <div class="space-x-1">
          <button type="button" class="btn btn-sm btn-alt-secondary" @click="
            () => {
              orderSearch = !orderSearch;
            }
          ">
            <i class="fa fa-search"></i>
          </button>
          <div class="dropdown d-inline-block">
            <button type="button" class="btn btn-sm btn-alt-secondary" id="dropdown-recent-orders-filters"
              data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-fw fa-flask"></i>
              篩選器
              <i class="fa fa-angle-down ms-1"></i>
            </button>
            <div class="dropdown-menu dropdown-menu-md dropdown-menu-end fs-sm"
              aria-labelledby="dropdown-recent-orders-filters">
              <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                未審核
                <span class="badge bg-primary rounded-pill">20</span>
              </a>
              <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                審核中
                <span class="badge bg-primary rounded-pill">72</span>
              </a>
              <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                已完成
                <span class="badge bg-primary rounded-pill">890</span>
              </a>
              <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                全部
                <span class="badge bg-primary rounded-pill">997</span>
              </a>
            </div>
          </div>
        </div>
      </template>

      <template #content>
        <div v-if="orderSearch" id="one-dashboard-search-orders" class="block-content border-bottom">
          <!-- Search Form -->
          <form @sumit.prevent>
            <div class="push">
              <div class="input-group">
                <input type="text" class="form-control form-control-alt" id="one-ecom-orders-search"
                  name="one-ecom-orders-search" placeholder="搜尋所有訂單.." />
                <span class="input-group-text bg-body border-0">
                  <i class="fa fa-search"></i>
                </span>
              </div>
            </div>
          </form>
          <!-- END Search Form -->
        </div>
        <div class="block-content block-content-full">
          <!-- Recent Orders Table -->
          <div class="table-responsive">
            <table class="table table-hover table-vcenter">
              <thead>
                <tr>
                  <!-- <th>訂單編號</th> -->
                  <th class="d-none d-sm-table-cell fw-semibold text-muted text-end">消費者</th>
                  <th class="d-none d-sm-table-cell fw-semibold text-muted text-end">預計前往人數</th>
                  <th class="d-none d-sm-table-cell fw-semibold text-muted text-end">預訂時間</th>
                  <th class="d-none d-sm-table-cell fw-semibold text-muted text-end">訂單建立時間</th>
                  <th class="d-none d-sm-table-cell text-center">處理狀態</th>
                </tr>
              </thead>
              <tbody class="fs-sm">
                <tr v-for="(row, index)  in resData" :key="index">
                  <!-- <td>
                    <a class="fw-semibold" href="javascript:void(0)">
                      {{ row.reserveId }}
                    </a>
                    <p class="fs-sm fw-medium text-muted mb-0">一般客</p>
                  </td> -->
                  <td class="d-none d-xl-table-cell">
                    <a class="fw-semibold" href="javascript:void(0)">{{ row.reserveName }}
                      <p class="fs-sm fw-medium text-muted mb-0"> {{ row.reservePhone }}
                      </p>
                    </a>
                  </td>


                  <!-- <td class="d-none d-sm-table-cell">
                    <div class="progress mb-1" style="height: 5px">
                      <div class="progress-bar bg-success" role="progressbar" style="width: 8%" aria-valuenow="8"
                        aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <p class="fs-xs fw-semibold mb-0">8%</p>
                  </td> -->
                  <td class="d-none d-sm-table-cell fw-semibold text-muted text-end">
                    <i class="fa fa-user-group me-3">{{ row.adult }}</i>
                    <i class="fa fa-children me-3">{{ row.child }}</i>
                    <i class="fa fa-baby-carriage me-3">{{ row.baby }}</i>
                  </td>
                  <td class="d-none d-sm-table-cell text-end">
                    <strong>{{ moment(row.reserveTime).format("MM/D(dd)") }}</strong>
                  </td>
                  <td class="d-none d-sm-table-cell fw-semibold text-muted text-end">
                    <strong>{{ moment(row.reserveDateTime).startOf().fromNow() }}</strong>

                  </td>
                  <td>
                    <span
                      class="fs-xs fw-semibold d-inline-block py-1 px-3 rounded-pill bg-danger-light text-danger">尚未通知</span>
                  </td>
                </tr>

              </tbody>
            </table>
          </div>
          <!-- END Recent Orders Table -->
        </div>
        <div class="block-content block-content-full bg-body-light">
          <!-- Pagination -->
          <nav aria-label="Photos Search Navigation">
            <ul class="pagination pagination-sm justify-content-end mb-0">
              <li class="page-item">
                <a class="page-link" href="javascript:void(0)" tabindex="-1" aria-label="Previous">
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
                <a class="page-link" href="javascript:void(0)" aria-label="Next">
                  下一頁
                </a>
              </li>
            </ul>
          </nav>
          <!-- END Pagination -->
        </div>
      </template>
    </BaseBlock>
    <!-- END Recent Orders -->
  </div>
  <!-- END Page Content -->
</template>

