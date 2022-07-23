<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
// eslint-disable-next-line no-unused-vars
import { reactive, ref, computed } from "vue";
import useVuelidate from "@vuelidate/core";
import { required, between } from "@vuelidate/validators";
import axios from "axios";
import moment from 'moment';
import Swal from "sweetalert2";
// CKEditor 5, for more info and examples you can check out https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/frameworks/vuejs-v3.html
import CKEditor from "@ckeditor/ckeditor5-vue";

// You can import one of the following CKEditor variation (only one at a time)
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { useRouter } from "vue-router";



// CKEditor 5 variables
let ckeditor = CKEditor.component;

const editorConfig = ref({});


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

const router = useRouter();

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
const business = JSON.parse(window.sessionStorage.getItem("access-business"));
const businessID = business.data.business.businessId;
const restaurantApply = JSON.parse(window.localStorage.getItem("restaurantApply" + businessID));
const busineesUUID = business.data.business.uuid;

const url = "localhost:8088";
//接收的資料ref
//當日統計
//取得全部人數
//取得全部組數
const CountTotal = ref(); //全部人數 
const CountGroup = ref(); //全部組數

const resData = ref();
var powUUID = ref();
const getReserveList = function () {
  axios.get(`http://${url}/${businessID}/reserves`)
    .then((res) => {
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

const getPos = function () {
  axios.get(`http://${url}/pos/business/${businessID}`)
    .then((res) => {
      if (res.data.uuid != null || res.data.uuid != undefined) {
        powUUID.value = res.data.uuid;
      }
    })
}

// Input state variables
const state = reactive({
  restaurantName: null,
  restaurantTel: null,
  restaurantAddress: null,
  restaurantCategory: null,
  restaurantType: null,
  restaurantBusinessHours: null,
  restaurantScore: null,
  imageUrl: null,
});

const image = ref({
  imageUrl: null,
});



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

//檔案上傳方法，寫入後端後會吐回加入UUID之名稱，再回傳data寫入ref()裏
function fileUpload() {
  var files = document.getElementById("input").files;
  var params = new FormData();
  params.append("file", files[0]);
  axios.post("http://localhost:8088/fileUpload", params).then((res) => {
    image.value = res.data;
  });
}

// Validation rules
const rules = computed(() => {
  return {
    restaurantName: {
      required
    },
    restaurantTel: {
      required
    },
    restaurantAddress: {
      required
    },
    restaurantBusinessHours: {
      required
    },
    restaurantScore: {
      required,
      between: between(0, 5),
    },
  };
});

// Use vuelidate
const v$ = useVuelidate(rules, state);

// On form submission
async function onSubmit() {
  const result = await v$.value.$validate();

  if (!result) {
    // notify user form is invalid
    return;
  }

  // perform async actions
}

function createRestaurant() {
  toast
    .fire({
      title: "資料是否都確認完畢?",
      icon: "warning",
      showCancelButton: true,
      customClass: {
        confirmButton: "btn btn-danger m-1",
        cancelButton: "btn btn-secondary m-1",
      },
      confirmButtonText: "送出",
      cancelButtonText: "取消",
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
      const restaurant = {
        restaurantName: state.restaurantName,
        restaurantTel: state.restaurantTel,
        restaurantAddress: state.restaurantAddress,
        restaurantCategory: state.restaurantCategory,
        restaurantType: state.restaurantType,
        restaurantBusinessHours: state.restaurantBusinessHours,
        restaurantScore: state.restaurantScore,
        imageUrl: image.value.imageUrl,
      };

      //send request to server
      if (result.value) {
        var posData = {
          validDate: '未開通',
          expiryDate: new Date(),
          UUID: null
        }
        axios.post(`http://${url}/business/${businessID}/pos`, posData)
          .then((res) => { console.log(res) })
        localStorage.setItem("restaurantApply" + businessID, JSON.stringify(restaurant));
        toast.fire({
          title: "已為您送出，請等待審核",
          timer: 800,
          icon: "success"
        });
      } else if (result.dismiss === "cancel") {
        toast.fire({
          title: "已取消",
          timer: 800,
          icon: "error"
        });
      }
      window.setTimeout(function () {
        // router.push({ name: "index" });
        router.go(0)
      }, 100);
    });


  // axios.post("http://localhost:8088/restaurants", restaurant).then(() => {
  //   window.location = "#/backend/restaurants/restaurantInfo";
  // });
}

getReserveList();
getPos();

if (business.data.business.businessPic == null) {
  business.data.business.businessPic = "avatar";
}

//CK Editors 一鍵輸入
var content = `
營業時間 <br>
固定店休日：每週二<br>
週一至週日 12:00~14:30 / 18:00~22:30<br>
</p>
`;


function addform() {
  state.restaurantName = "小心上癮素食麻辣火鍋";
  state.restaurantTel = " 02-2763-1096";
  state.restaurantAddress = "台北市松山區南京東路五段61-3號";
  state.restaurantCategory = "中式";
  state.restaurantType = "五辛素";
  state.restaurantBusinessHours = content;
  state.restaurantScore = "4.2";
}


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
              style="width: 21px" />

            {{
                business.data.business.businessName
            }}
          </RouterLink>
        </h2>
      </div>
      <!-- <div class="mt-3 mt-md-0 ms-md-3 space-x-1">
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
      </div> -->
    </div>
  </div>
  <!-- END Hero -->

  <!-- Page Content -->
  <div v-if="busineesUUID === powUUID" class="content">
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
                  <th class=" d-sm-table-cell fw-semibold text-muted text-end">消費者</th>
                  <th class=" d-sm-table-cell fw-semibold text-muted text-end">預計前往人數</th>
                  <th class=" d-sm-table-cell fw-semibold text-muted text-end">預訂餐廳時間</th>
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
                  <td class="d-xl-table-cell">
                    <a class="fw-semibold" :href="`tel:${row.reservePhone}`">{{ row.reserveName }}
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
                  <td class=" d-sm-table-cell fw-semibold text-muted text-end">
                    <i class="fa fa-user-group me-3"> {{ row.adult }}</i>
                    <i class="fa fa-children me-3"> {{ row.child }}</i>
                    <i class="fa fa-baby-carriage me-3"> {{ row.baby }}</i>
                  </td>
                  <td class=" d-sm-table-cell text-end">
                    <strong>{{ moment(row.reserveDateTime).format("MM/D(dd)") }}</strong>
                  </td>
                  <td class="d-none d-sm-table-cell fw-semibold text-muted text-end">
                    <strong>{{ moment(row.reserveTime).startOf().fromNow() }}</strong>
                  </td>


                  <td class="">
                    <span
                      class=" fs-xs fw-semibold d-inline-block py-2 px-5 rounded-pill bg-danger-light text-danger">尚未通知</span>
                  </td>
                </tr>

              </tbody>
            </table>
          </div>
          <!-- END Recent Orders Table -->
        </div>
        <!-- <div class="block-content block-content-full bg-body-light"> -->
        <!-- Pagination -->
        <!-- <nav aria-label="Photos Search Navigation">
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
          </nav> -->
        <!-- END Pagination -->
        <!-- </div> -->
      </template>
    </BaseBlock>
    <!-- END Recent Orders -->
  </div>
  <!-- END Page Content -->

  <div v-else-if="restaurantApply === null" class="content">
    <div class="row justify-content-center push">
      <span class="col-md-8 col-lg-6 col-xl-4">
        <BaseBlock title="服務說明" class="h-25 mb-5">
          <p class="lead">
            您好，請接續填寫您的詳細餐廳資訊。<br />
            送交後會為您審核結果。
          </p>
        </BaseBlock>
        <BaseBlock title="瞭解基本規範" class="h-70 mb-2">
          <p class="lead">
            為確保 <strong>愛蔬網</strong> 上的商家資訊品質優良，請遵循這套專為當地商家制定的規範。<br />
            按照這些規範行事有助於避免一些常見問題。<br />
            <br />
            為充分提高商家檔案管理成效：<br />
            <br />
            商家名稱必須與商家透過招牌、文具和其他品牌行銷方式宣傳的實際名稱相同，維持一致的形象。<br />
            確認地址和/或服務範圍正確無誤。<br />
            選擇用來說明整體核心業務的類別時寧少勿多，精確為上。<br />
            每間商家只能有一個商家檔案，否則商家資訊可能無法在 Google 地圖和 <strong>愛蔬網</strong> 搜尋上正常顯示。<br />
            品牌、機構、藝術工作者和其他純網路業務不能使用商家檔案。
          </p>
        </BaseBlock>
      </span>
      <span class="col-md-8 col-lg-6 col-xl-7">
        <form @submit.prevent="onSubmit" @submit="createRestaurant" id="forms">
          <BaseBlock title="新增餐廳表單" content-full>
            <div class="row push">
              <div class="col-lg-2">
                <p class="fs-sm text-muted">請填寫完整資訊</p>
              </div>
              <div class="col-lg-8 col-xl-10">
                <!-- 餐廳名稱開始 -->
                <div class="mb-4">
                  <label class="form-label" for="val-restaurantName">餐廳名稱<span class="text-danger">*</span></label>
                  <input type="text" id="val-restaurantName" class="form-control" :class="{
                    'is-invalid': v$.restaurantName.$errors.length,
                  }" v-model="state.restaurantName" @blur="v$.restaurantName.$touch" placeholder="請輸入餐廳名稱" />
                  <div v-if="v$.restaurantName.$errors.length" class="invalid-feedback animated fadeIn">
                    請輸入餐廳名稱
                  </div>
                </div>
                <!-- 電話開始 -->
                <div class="mb-4">
                  <label class="form-label" for="val-restaurantTel">電話<span class="text-danger">*</span></label>
                  <input type="text" id="val-restaurantTel" class="form-control" :class="{
                    'is-invalid': v$.restaurantTel.$errors.length,
                  }" placeholder="02-23448743" v-model="state.restaurantTel" @blur="v$.restaurantTel.$touch" />
                  <div v-if="v$.restaurantTel.$errors.length" class="invalid-feedback animated fadeIn">
                    請輸入數字
                  </div>
                </div>
                <!-- 地址開始 -->
                <div class="mb-4">
                  <label class="form-label" for="val-restaurantAddress">地址<span class="text-danger">*</span></label>
                  <input type="text" id="val-restaurantAddress" class="form-control" :class="{
                    'is-invalid': v$.restaurantAddress.$errors.length,
                  }" placeholder="桃園市中壢區.." v-model="state.restaurantAddress" @blur="v$.restaurantAddress.$touch" />
                  <div v-if="v$.restaurantAddress.$errors.length" class="invalid-feedback animated fadeIn">
                    請輸入完整地址
                  </div>
                </div>
                <!-- 餐廳類型開始 -->
                <div class="mb-4">
                  <label class="form-label" for="example-select">選擇餐廳類型</label>
                  <select class="form-select" id="example-select" name="example-select"
                    v-model="state.restaurantCategory">
                    <option selected disabled value="">請選擇</option>
                    <option value="中式">中式</option>
                    <option value="義式">義式</option>
                    <option value="韓式">韓式</option>
                    <option value="日式">日式</option>
                    <option value="美式">美式</option>
                    <option value="印度">印度</option>
                    <option value="簡餐">簡餐</option>
                    <option value="麵食">麵食</option>
                    <option value="自助餐">自助餐</option>
                  </select>
                </div>
                <!-- 素食種類開始 -->
                <div class="mb-4">
                  <label class="form-label" for="example-select">選擇素食種類</label>
                  <select class="form-select" id="example-select" name="example-select" v-model="state.restaurantType">
                    <option selected disabled value="">請選擇</option>
                    <option value="全素">全素</option>
                    <option value="蛋素">蛋素</option>
                    <option value="奶素">奶素</option>
                    <option value="蛋奶素">蛋奶素</option>
                    <option value="五辛素">五辛素</option>
                  </select>
                </div>
                <!-- 營業時間 -->
                <div class="mb-4">
                  <!-- <label class="form-label" for="example-select">營業時間及注意事項<span class="text-danger">*</span></label>
                  <input type="textarea" id="val-restaurantBusinessHours" class="form-control" :class="{
                    'is-invalid': v$.restaurantBusinessHours.$errors.length,
                  }" v-model="state.restaurantBusinessHours" @blur="v$.restaurantBusinessHours.$touch" />
                  <div v-if="v$.restaurantBusinessHours.$errors.length" class="invalid-feedback animated fadeIn">
                    此項為必填
                  </div> -->
                  <BaseBlock content-full>
                    <ckeditor :editor="ClassicEditor" :config="editorConfig" :class="{
                      'is-invalid': v$.restaurantBusinessHours.$errors.length,
                    }" v-model="state.restaurantBusinessHours" @blur="v$.restaurantBusinessHours.$touch" />
                    <div v-if="v$.restaurantBusinessHours.$errors.length" class="invalid-feedback animated fadeIn">
                      此項為必填
                    </div>
                  </BaseBlock>
                </div>
                <!-- 評分開始 -->
                <div class="mb-4">
                  <label class="form-label" for="val-restaurantScore">評分<span class="text-danger">*</span></label>
                  <input type="text" id="val-restaurantScore" class="form-control" :class="{
                    'is-invalid': v$.restaurantScore.$errors.length,
                  }" placeholder="0.0-5.0" v-model="state.restaurantScore" @blur="v$.restaurantScore.$touch" />
                  <div v-if="v$.restaurantScore.between" class="invalid-feedback animated fadeIn">
                    必須輸入0-5的數字
                  </div>

                </div>
                <!-- 圖片上傳開始-->
                <div class="mb-4">
                  <label class="form-label" for="val-stock">圖片 </label>
                  <input class="form-control mb-4" id=" input" type="file" ref="myFile" @change="fileUpload()" />
                  <br />


                  <!-- 根據回傳值印出圖片 -->
                  <img :src="image.imageUrl" style="max-width:500px;width:100%" />
                  <br />
                </div>
                <div class="row mb-4">
                  <div class="col-md-6 col-xl-5">
                    <button type="submit" class="btn btn-alt-primary">送出</button>
                  </div>
                  <div class="col-md-6 col-xl-7">
                    <button type="button" class="btn w-100 btn-alt-warning" @click="addform">
                      <i class="fa fa-fw fa-fill me-1 opacity-50"></i> 一鍵輸入
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </BaseBlock>
        </form>
      </span>
    </div>
  </div>

  <div v-else class="content">
    <!-- Page Content -->
    <div class="content content-boxed">
      <!-- Invoice -->
      <BaseBlock>

        <div class="p-sm-4 p-xl-7">
          <!-- Invoice Info -->
          <div class="row mb-4">
            <!-- Company Info -->
            <div class="col-6 fs-sm">
              <p class="h3"> {{
                  business.data.business.businessName
              }}</p>
              <address>
                負責人 :
                {{
                    business.data.business.principalName
                }}<br />
              </address>
            </div>
            <!-- END Company Info -->

            <!-- Client Info -->
            <div class="col-6 text-end fs-sm">
              <p class="h3">您的申請已送出</p>
            </div>
            <!-- END Client Info -->
          </div>
          <!-- END Invoice Info -->

          <!-- Footer -->
          <p class="fs-md text-muted text-center bg-gray">
            請耐心等候，我們即將為您開通權限。
          </p>
          <!-- END Footer -->
        </div>
      </BaseBlock>
      <!-- END Invoice -->
    </div>
    <!-- END Page Content -->
  </div>
</template>

