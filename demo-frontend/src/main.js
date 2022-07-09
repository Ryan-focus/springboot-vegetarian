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

// Template directives
import clickRipple from "@/directives/clickRipple";

// Bootstrap framework
import * as bootstrap from "bootstrap";
window.bootstrap = bootstrap;

// Craft new application
const app = createApp(App);

//Axios
// axios.defaults.baseURL = config.api.url
axios.defaults.withCredentials = false;
axios.defaults.timeout = 5000;
axios.defaults.headers.post["Content-Type"] = "application/json";

// Register global components
app.component("BaseBlock", BaseBlock);
app.component("BaseBackground", BaseBackground);
app.component("BasePageHeading", BasePageHeading);

// Register global directives
app.directive("click-ripple", clickRipple);

//using Axios
app.use(VueAxios, axios);

// Use Pinia and Vue Router
app.use(createPinia());
app.use(router);

// ..and finally mount it!
app.mount("#app");
