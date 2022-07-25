/* eslint-disable vue/multi-word-component-names */
import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import axios from "axios";
import VueAxios from "vue-axios";
import "sweetalert2/dist/sweetalert2.min.css";

// You can use the following starter router instead of the default one as a clean starting point
// import router from "./router/starter";
import router from "./router";

// Template components
import BaseBlock from "@/components/BaseBlock.vue";
import BaseBackground from "@/components/BaseBackground.vue";
import BasePageHeading from "@/components/BasePageHeading.vue";
import LoadingVue from "@/components/LoadingVue.vue";
import WebSocket from "@/WebSocket/WebSocket.vue";

//全域註冊 Loader
// 讀取動畫套件
// Import component
import { useLoading } from "vue3-loading-overlay";

//import Vue- datePicker
import Datepicker from "@vuepic/vue-datepicker";
// import "@vuepic/vue-datepicker/dist/main.css";
// Template directives
import clickRipple from "@/directives/clickRipple";

//引入 google map
import { GoogleMap } from "vue3-google-map";

// Bootstrap framework
import * as bootstrap from "bootstrap";
window.bootstrap = bootstrap;

// moment.js setting Zh-TW
import moment from "moment";

moment.defineLocale("zh-tw", {
  months:
    "一月_二月_三月_四月_五月_六月_七月_八月_九月_十月_十一月_十二月".split(
      "_"
    ),
  monthsShort: "1月_2月_3月_4月_5月_6月_7月_8月_9月_10月_11月_12月".split("_"),
  weekdays: "星期日_星期一_星期二_星期三_星期四_星期五_星期六".split("_"),
  weekdaysShort: "週日_週一_週二_週三_週四_週五_週六".split("_"),
  weekdaysMin: "日_一_二_三_四_五_六".split("_"),
  longDateFormat: {
    LT: "HH:mm",
    LTS: "HH:mm:ss",
    L: "YYYY/MM/DD",
    LL: "YYYY年M月D日",
    LLL: "YYYY年M月D日 HH:mm",
    LLLL: "YYYY年M月D日dddd HH:mm",
    l: "YYYY/M/D",
    ll: "YYYY年M月D日",
    lll: "YYYY年M月D日 HH:mm",
    llll: "YYYY年M月D日dddd HH:mm",
  },
  meridiemParse: /凌晨|早上|上午|中午|下午|晚上/,
  meridiemHour: function (hour, meridiem) {
    if (hour === 12) {
      hour = 0;
    }
    if (meridiem === "凌晨" || meridiem === "早上" || meridiem === "上午") {
      return hour;
    } else if (meridiem === "中午") {
      return hour >= 11 ? hour : hour + 12;
    } else if (meridiem === "下午" || meridiem === "晚上") {
      return hour + 12;
    }
  },
  meridiem: function (hour, minute) {
    var hm = hour * 100 + minute;
    if (hm < 600) {
      return "凌晨";
    } else if (hm < 900) {
      return "早上";
    } else if (hm < 1130) {
      return "上午";
    } else if (hm < 1230) {
      return "中午";
    } else if (hm < 1800) {
      return "下午";
    } else {
      return "晚上";
    }
  },
  calendar: {
    sameDay: "[今天] LT",
    nextDay: "[明天] LT",
    nextWeek: "[下]dddd LT",
    lastDay: "[昨天] LT",
    lastWeek: "[上]dddd LT",
    sameElse: "L",
  },
  dayOfMonthOrdinalParse: /\d{1,2}(日|月|週)/,
  ordinal: function (number, period) {
    switch (period) {
      case "d":
      case "D":
      case "DDD":
        return number + "日";
      case "M":
        return number + "月";
      case "w":
      case "W":
        return number + "週";
      default:
        return number;
    }
  },
  relativeTime: {
    future: "%s後",
    past: "%s前",
    s: "幾秒",
    ss: "%d 秒",
    m: "1 分鐘",
    mm: "%d 分鐘",
    h: "1 小時",
    hh: "%d 小時",
    d: "1 天",
    dd: "%d 天",
    M: "1 個月",
    MM: "%d 個月",
    y: "1 年",
    yy: "%d 年",
  },
});
// Craft new application
const app = createApp(App);

//Axios
// axios.defaults.baseURL = config.api.url
axios.defaults.withCredentials = false;
axios.defaults.timeout = 30000;
axios.defaults.headers.post["Content-Type"] = "application/json";

// VueLoading.__defaults;

// Register global components
app.component("BaseBlock", BaseBlock);
app.component("BaseBackground", BaseBackground);
app.component("BasePageHeading", BasePageHeading);
app.component("LoadingVue", LoadingVue);
//Register WebSocket
app.component("WebSocket", WebSocket);

// Register global directives
app.directive("click-ripple", clickRipple);

app.component("Datepicker", Datepicker);

//using Loading
app.use(useLoading);

//Using Google Maps
app.component("GoogleMap", GoogleMap);

//using Axios
app.use(VueAxios, axios);

//using Moment
app.use(moment);
// Use Pinia and Vue Router
app.use(createPinia());
app.use(router);

// ..and finally mount it!
app.mount("#app");
