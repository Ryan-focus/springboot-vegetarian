<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { reactive, ref } from "vue";
import axios from "axios";

// vue-chart-3, for more info and examples you can check out https://vue-chart-3.netlify.app/ and http://www.chartjs.org/docs/ -->
import { BarChart } from "vue-chart-3";
import { Chart, registerables } from "chart.js";

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
  labels: ["週一", "週二", "週三", "週四", "週五", "週六", "週日"],
  datasets: [
    {
      label: "本週",
      fill: true,
      backgroundColor: "rgba(100, 116, 139, .7)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(100, 116, 139, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(100, 116, 139, 1)",
      data: [2, 2, 2, 1, 3, 2, 1],
    },
    {
      label: "上週",
      fill: true,
      backgroundColor: "rgba(100, 116, 139, .15)",
      borderColor: "transparent",
      pointBackgroundColor: "rgba(100, 116, 139, 1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(100, 116, 139, 1)",
      data: [1, 1, 1, 3, 1, 1, 4],
    },
  ],
});

//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = "warning";
//接收的資料ref
const resData = ref();
const restaurantsTotal = ref();
const newRestaurant = ref();
const total = ref();

//restaurantsTotal
const getAxios = function () {
  axios
    .get(`http://${url}/restaurantList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      restaurantsTotal.value = res.data.total;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
getAxios();

//newRestaurant
const getRestaurant = function () {
  axios
    .get(`http://${url}/restaurantList`, { params: { status: urlParams } })
    .then((res) => {
      //獲取伺服器的回傳資料
      resData.value = res.data;
      newRestaurant.value = res.data.total;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
getRestaurant();

//取得合作商家
const getPass = function () {
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
getPass();

</script>

<template>
  <!-- Hero -->
  <div class="content">
    <div
      class="d-flex flex-column flex-md-row justify-content-md-between align-items-md-center py-2 text-center text-md-start">
      <div class="flex-grow-1 mb-1 mb-md-0">
        <h1 class="h3 fw-bold mb-2">數據分析</h1>
        <h2 class="h6 fw-medium fw-medium text-muted mb-0">
          歡迎 管理員
          <RouterLink :to="{ name: 'backend-pages-generic-profile' }" class="fw-semibold">{{ admin.data.user.userName }}
          </RouterLink>
        </h2>
      </div>
      <div class="mt-3 mt-md-0 ms-md-3 space-x-1">
        <!-- <a
          href="javascript:void(0)"
          class="btn btn-sm btn-alt-secondary space-x-1"
        >
          <i class="fa fa-cogs opacity-50"></i>
          <span>設定</span>
        </a> -->
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
                <dt class="fs-3 fw-bold">{{ restaurantsTotal }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  現有餐廳總數
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-gem fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="#/backend/restaurants/restaurantinfo">
                <span>詳細</span>
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
                <dt class="fs-3 fw-bold">{{ total }}</dt>
                <dd class="fs-sm fw-medium fs-sm fw-medium text-muted mb-0">
                  合作商家
                </dd>
              </dl>
              <div class="item item-rounded-lg bg-body-light">
                <i class="far fa-user-circle fs-3 text-primary"></i>
              </div>
            </div>
            <div class="bg-body-light rounded-bottom">
              <a class="block-content block-content-full block-content-sm fs-sm fw-medium d-flex align-items-center justify-content-between"
                href="javascript:void(0)">
                <a href="/#/backend/reserve/reserveinfo">詳細</a>
                <i class="fa fa-arrow-alt-circle-right ms-1 opacity-25 fs-base"></i>
              </a>
            </div>
          </template>
        </BaseBlock>
        <!-- END New Customers -->
      </div>
    </div>
    <!-- END Overview -->

    <!-- 餐廳總數成長表 -->
    <div class="row">
      <div class="col-xl-12 col-xxl-9 d-flex flex-column">
        <!-- Earnings Summary -->
        <BaseBlock title="餐廳總數成長表" class="flex-grow-1 d-flex flex-column">
          <!-- <template #options>
            <button type="button" class="btn-block-option">
              <i class="si si-settings"></i>
            </button>
          </template> -->

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
                    <dd class="fs-sm fw-medium text-muted mb-0">週成長</dd>
                  </dl>
                </div>
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold d-inline-flex align-items-center space-x-2">
                      <i class="fa fa-caret-up fs-base text-success"></i>
                      <span>3.8%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">月成長</dd>
                  </dl>
                </div>
                <div class="col-sm-4">
                  <dl class="mb-0">
                    <dt class="fs-3 fw-bold d-inline-flex align-items-center space-x-2">
                      <i class="fa fa-caret-down fs-base text-danger"></i>
                      <span>1.7%</span>
                    </dt>
                    <dd class="fs-sm fw-medium text-muted mb-0">年成長</dd>
                  </dl>
                </div>
              </div>
            </div>
          </template>
        </BaseBlock>
        <!-- END Earnings Summary -->
      </div>
    </div>
    <!-- END Statistics -->


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
