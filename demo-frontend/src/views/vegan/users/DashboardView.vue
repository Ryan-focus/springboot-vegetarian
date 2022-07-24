<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { reactive, ref } from "vue";

// vue-chart-3, for more info and examples you can check out https://vue-chart-3.netlify.app/ and http://www.chartjs.org/docs/ -->
import { LineChart, BarChart } from "vue-chart-3";
import { Chart, registerables } from "chart.js";

import axios from "axios";

Chart.register(...registerables);

// Set Global Chart.js configuration
Chart.defaults.color = "#818d96";
Chart.defaults.scale.grid.lineWidth = 0;
Chart.defaults.scale.beginAtZero = true;
Chart.defaults.datasets.bar.maxBarThickness = 45;
Chart.defaults.elements.bar.borderRadius = 4;
Chart.defaults.elements.bar.borderSkipped = false;
Chart.defaults.elements.point.radius = 0;
Chart.defaults.elements.point.hoverRadius = 0;
Chart.defaults.plugins.tooltip.radius = 3;
Chart.defaults.plugins.legend.labels.boxWidth = 10;

// Helper variables
const orderSearch = ref(false);

// Chart Earnings data
const earningsData = reactive({
  labels: ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"],
  datasets: [
    {
      label: "本月",
      fill: true,
      backgroundColor: "rgba(100, 116, 139, .7)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(100, 116, 139, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(100, 116, 139, 1)",
      data: [716, 628, 1056, 560, 956, 890, 790],
    },
    {
      label: "上月",
      fill: true,
      backgroundColor: "rgba(100, 116, 139, .15)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(100, 116, 139, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(100, 116, 139, 1)",
      data: [1160, 923, 1052, 1300, 880, 926, 963],
    },
  ],
});

// Chart Earnings options
const earningsOptions = reactive({
  scales: {
    x: {
      display: false,
      grid: {
        drawBorder: false,
      },
    },
    y: {
      display: false,
      grid: {
        drawBorder: false,
      },
    },
  },
  interaction: {
    intersect: false,
  },
  plugins: {
    legend: {
      labels: {
        boxHeight: 10,
        font: {
          size: 14,
        },
      },
    },
    tooltip: {
      callbacks: {
        label: function (context) {
          return context.dataset.label + ": $" + context.parsed.y;
        },
      },
    },
  },
});

// Chart Total Orders data
const totalOrdersData = reactive({
  labels: [
    "MON",
    "TUE",
    "WED",
    "THU",
    "FRI",
    "SAT",
    "SUN",
    "MON",
    "TUE",
    "WED",
    "THU",
    "FRI",
    "SAT",
    "SUN",
  ],
  datasets: [
    {
      label: "Total Orders",
      fill: true,
      backgroundColor: "rgba(220, 38, 38, .15)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(220, 38, 38, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(220, 38, 38, 1)",
      data: [33, 29, 32, 37, 38, 30, 34, 28, 43, 45, 26, 45, 49, 39],
    },
  ],
});

// Chart Total Orders options
const totalOrdersOptions = reactive({
  maintainAspectRatio: false,
  tension: 0.4,
  scales: {
    x: {
      display: false,
    },
    y: {
      display: false,
    },
  },
  interaction: {
    intersect: false,
  },
  plugins: {
    legend: {
      display: false,
    },
    tooltip: {
      callbacks: {
        label: function (context) {
          return " " + context.parsed.y + " Orders";
        },
      },
    },
  },
});

// Chart Total Earnings data
const totalEarningsData = reactive({
  labels: [
    "MON",
    "TUE",
    "WED",
    "THU",
    "FRI",
    "SAT",
    "SUN",
    "MON",
    "TUE",
    "WED",
    "THU",
    "FRI",
    "SAT",
    "SUN",
  ],
  datasets: [
    {
      label: "Total Earnings",
      fill: true,
      backgroundColor: "rgba(101, 163, 13, .15)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(101, 163, 13, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(101, 163, 13, 1)",
      data: [
        716, 1185, 750, 1365, 956, 890, 1200, 968, 1158, 1025, 920, 1190, 720,
        1352,
      ],
    },
  ],
});

// Chart Total Earnings options
const totalEarningsOptions = reactive({
  maintainAspectRatio: false,
  tension: 0.4,
  scales: {
    x: {
      display: false,
    },
    y: {
      display: false,
    },
  },
  interaction: {
    intersect: false,
  },
  plugins: {
    legend: {
      display: false,
    },
    tooltip: {
      callbacks: {
        label: function (context) {
          return " $" + context.parsed.y;
        },
      },
    },
  },
});

// Chart New Customers data
const newCustomersData = reactive({
  labels: [
    "MON",
    "TUE",
    "WED",
    "THU",
    "FRI",
    "SAT",
    "SUN",
    "MON",
    "TUE",
    "WED",
    "THU",
    "FRI",
    "SAT",
    "SUN",
  ],
  datasets: [
    {
      label: "Total Orders",
      fill: true,
      backgroundColor: "rgba(101, 163, 13, .15)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(101, 163, 13, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(101, 163, 13, 1)",
      data: [25, 15, 36, 14, 29, 19, 36, 41, 28, 26, 29, 33, 23, 41],
    },
  ],
});

// Chart New Customers options
const newCustomersOptions = reactive({
  maintainAspectRatio: false,
  tension: 0.4,
  scales: {
    x: {
      display: false,
    },
    y: {
      display: false,
    },
  },
  interaction: {
    intersect: false,
  },
  plugins: {
    legend: {
      display: false,
    },
    tooltip: {
      callbacks: {
        label: function (context) {
          return " " + context.parsed.y + " Customers";
        },
      },
    },
  },
});

const countUser = ref();
const countRegister = ref();
const countLogin = ref();
const countPercentLogin = ref();

async function getData() {

  const { data: res1 } = await axios.get("http://localhost:8088/user/countUser");
  countUser.value = res1;

  const { data: res2 } = await axios.get("http://localhost:8088/user/countRegister");
  countRegister.value = res2;

  const { data: res3 } = await axios.get("http://localhost:8088/user/countLogin");
  countLogin.value = res3;

  const { data: res4 } = await axios.get("http://localhost:8088/user/countPercentLogin");
  countPercentLogin.value = res4 * 100 + '%';

}

getData();

</script>

<template>
  <!-- Hero -->
  <div class="content">
    <div
      class="d-flex flex-column flex-md-row justify-content-md-between align-items-md-center py-2 text-center text-md-start">
      <div class="flex-grow-1 mb-1 mb-md-0">
        <h1 class="h3 fw-bold mb-2">使用者管理</h1>
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
        <!-- 待處理訂單 Pending Orders  :to 購物車模板-->
        <BaseBlock class="d-flex flex-column h-100 mb-0">
          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex justify-content-between align-items-center">
              <dl class="mb-0">
                <dt class="fs-3 fw-bold">{{ countUser }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  總會員數
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <!-- <i class="far fa-gem fs-3 text-primary"></i> -->
                <i class="far fa-user-circle fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="http://localhost:8080/#/backend/users/userInfo">
                <span>查看全部會員</span>
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
                <dt class="fs-3 fw-bold">{{ countPercentLogin }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  活躍會員(%)<br>(1個月內有登入/總)
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <!-- <i class="far fa-user-circle fs-3 text-primary"></i> -->
                <i class="fa fa-rocket fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看活躍會員</span>
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
                <dt class="fs-3 fw-bold">{{ countRegister }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  新註冊會員數<br>(計3個月內)
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <!-- <i class="far fa-paper-plane fs-3 text-primary"></i> -->
                <i class="fa fa-feather-pointed fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看新註冊會員</span>
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
                <dt class="fs-3 fw-bold">{{ countLogin }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  登入會員數<br>(計3個月內)
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <!-- <i class="fa fa-chart-bar fs-3 text-primary"></i> -->
                <i class="far fa-paper-plane fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <span>查看持續登入會員</span>
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
      <div class="col-xl-8 col-xxl-9 d-flex flex-column">
        <!-- Earnings Summary -->
        <BaseBlock title="會員資料統計" class="flex-grow-1 d-flex flex-column">
          <template #options>
            <button type="button" class="btn-block-option">
              <i class="si si-settings"></i>
            </button>
          </template>

          <template #content>
            <div class="block-content block-content-full flex-grow-1 d-flex align-items-center">
              <BarChart :chart-data="earningsData" :options="earningsOptions" class="w-100" />
            </div>
            <div class="block-content bg-body-light">
              <div class="row items-push text-center w-100">
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold d-inline-flex align-items-center space-x-2">
                      <i class="fa fa-caret-up fs-base text-success"></i>
                      <span>2.5%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      新註冊會員
                    </dd>
                  </dl>
                </div>
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold d-inline-flex align-items-center space-x-2">
                      <i class="fa fa-caret-up fs-base text-success"></i>
                      <span>3.8%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">登入會員數</dd>
                  </dl>
                </div>
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold d-inline-flex align-items-center space-x-2">
                      <i class="fa fa-caret-down fs-base text-danger"></i>
                      <span>1.7%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      活躍會員
                    </dd>
                  </dl>
                </div>
              </div>
            </div>
          </template>
        </BaseBlock>
        <!-- END Earnings Summary -->
      </div>
      <div class="col-xl-4 col-xxl-3 d-flex flex-column">
        <!-- Last 2 Weeks -->
        <div class="row items-push flex-grow-1">
          <div class="col-md-6 col-xl-12">
            <BaseBlock class="d-flex flex-column h-100 mb-0">
              <template #content>
                <div class="block-content flex-grow-1 d-flex justify-content-between">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold"></dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      維持登入會員
                    </dd>
                  </dl>
                  <div>
                    <div class="d-inline-block px-2 py-1 rounded-3 fs-xs fw-semibold text-danger bg-danger-light">
                      <i class="fa fa-caret-down me-1"></i>
                      2.2%
                    </div>
                  </div>
                </div>
                <div class="block-content p-1 text-center overflow-hidden">
                  <LineChart :chart-data="totalOrdersData" :options="totalOrdersOptions" style="height: 90px" />
                </div>
              </template>
            </BaseBlock>
          </div>
          <div class="col-md-6 col-xl-12">
            <BaseBlock class="d-flex flex-column h-100 mb-0">
              <template #content>
                <div class="block-content flex-grow-1 d-flex justify-content-between">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold"></dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      活躍會員(%)
                    </dd>
                  </dl>
                  <div>
                    <div class="d-inline-block px-2 py-1 rounded-3 fs-xs fw-semibold text-success bg-success-light">
                      <i class="fa fa-caret-up me-1"></i>
                      4.2%
                    </div>
                  </div>
                </div>
                <div class="block-content p-1 text-center overflow-hidden">
                  <LineChart :chart-data="totalEarningsData" :options="totalEarningsOptions" style="height: 90px" />
                </div>
              </template>
            </BaseBlock>
          </div>
          <div class="col-xl-12">
            <BaseBlock class="d-flex flex-column h-100 mb-0">
              <template #content>
                <div class="block-content flex-grow-1 d-flex justify-content-between">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold"></dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">
                      新註冊會員
                    </dd>
                  </dl>
                  <div>
                    <div class="d-inline-block px-2 py-1 rounded-3 fs-xs fw-semibold text-success bg-success-light">
                      <i class="fa fa-caret-up me-1"></i>
                      9.3%
                    </div>
                  </div>
                </div>
                <div class="block-content p-1 text-center overflow-hidden">
                  <!-- New Customers Chart Container -->
                  <LineChart :chart-data="newCustomersData" :options="newCustomersOptions" style="height: 90px" />
                </div>
              </template>
            </BaseBlock>
          </div>
        </div>
        <!-- END Last 2 Weeks -->
      </div>
    </div>
    <!-- END Statistics -->

    <!-- Recent Orders -->

    <!-- END Recent Orders -->
  </div>
  <!-- END Page Content -->
</template>
<script>
export default {
  data() {
    return {
      admin: "",
      count: undefined
    };
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem("access-admin"));
  },
  methods: {
    async getData() {
      const { data: res } = await axios.get("http://localhost:8088/user/countUser")
      console.log(res)
    }
  }
};
</script>
