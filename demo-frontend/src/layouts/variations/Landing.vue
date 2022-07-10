<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
import { useRouter } from "vue-router";
import BaseLayout from "@/layouts/BaseLayout.vue";

import BaseNavigation from "@/components/BaseNavigation.vue";

import Swal from "sweetalert2";

// Grab menu navigation arrays
import menu from "@/data/menu";

// Helper variables for mobile navigation visibility
const mobileVisibleNavHoverCentered = ref(false);
// Main store
const store = useTemplateStore();
const router = useRouter();
// Set default elements for this layout
store.setLayout({
  header: true,
  sidebar: false,
  sideOverlay: false,
  footer: false,
});

// Set various template options for this layout variation
store.headerStyle({ mode: "light" });
store.mainContent({ mode: "boxed" });

//登出
function logOut() {
  // this.admin = null;
  store.getStates({ admin: "", business: "", user: "" });
  localStorage.removeItem("access-admin");
  localStorage.removeItem("access-business");
  localStorage.removeItem("access-user");
  location.replace("http://localhost:8080/#/"); //登出後防止返回上頁
  Swal.fire({
    title: "登出",
    text: "您已登出",
    timer: 1000,
    icon: "Info"
  });
  window.setTimeout(function () {
    router.push({ name: "index" });
  }, 1000);
}
</script>


<template>
  <BaseLayout>
    <!-- Header Content Left -->
    <!-- Using the available v-slot, we can override the default Side Overlay content from layouts/partials/Header.vue -->
    <template #header-content-left>
      <!-- Logo -->

      <div>
        <RouterLink :to="{ name: 'index' }" class="fw-bold fs-lg tracking-wider text-dual me-2">
          <span class="smini-hide fs-3 tracking-wider">
            <img src="/assets/media/vegan/logo.png" alt="logo" class="w-50" />
          </span>
        </RouterLink>
      </div>


      <!-- END Logo -->
    </template>
    <!-- END Header Content Left -->

    <template #header-content-middle>
      <!-- Horizontal Navigation - Hover Centered -->
      <div class="p-3 push">
        <!-- Toggle Navigation -->
        <!-- <div class="d-lg-none">
          <button
            type="button"
            class="btn w-100 btn-alt-secondary d-flex justify-content-between align-items-center"
            @click="
              mobileVisibleNavHoverCentered = !mobileVisibleNavHoverCentered
            "
          >
            <i class="fa fa-bars"></i>
          </button>
        </div> -->
        <!-- END Toggle Navigation -->

        <!-- Navigation -->
        <div class="d-lg-block mt-2 mt-lg-0" :class="{
          'd-none': !mobileVisibleNavHoverCentered,
        }">
          <BaseNavigation :nodes="menu.demo" horizontal horizontal-hover horizontal-center />
        </div>
      </div>
      <!-- END Navigation -->
    </template>
    <!-- Header Content Right -->
    <!-- Using the available v-slot, we can override the default Side Overlay content from layouts/partials/Header.vue -->
    <template #header-content-right>


      <div v-if="admin">
        <div class="dropdown">
          <label class="form-check-label me-2" for="example-radio-block1">管理員:</label>
          <button type="button" class="btn btn-alt-secondary me-2" id="sidebar-themes-dropdown"
            data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-user opacity-50"></i>{{ admin.data }}
          </button>
          <div class="dropdown-menu dropdown-menu-end fs-sm smini-hide border-0"
            aria-labelledby="sidebar-themes-dropdown">

            <RouterLink @click="logOut()" :to="{ name: '' }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">登出</span>
            </RouterLink>
          </div>
        </div>
      </div>
      <div v-else-if="business">
        <div class="dropdown">
          <label class="form-check-label me-2" for="example-radio-block1">商家用戶</label>
          <button type="button" class="btn btn-alt-secondary me-2" id="sidebar-themes-dropdown"
            data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-user opacity-50"></i>{{ business.data.business.principalName }}
          </button>
          <div class="dropdown-menu dropdown-menu-end fs-sm smini-hide border-0"
            aria-labelledby="sidebar-themes-dropdown">

            <RouterLink @click="logOut()" :to="{ name: '' }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">登出</span>
            </RouterLink>
          </div>
        </div>
      </div>
      <div v-else-if="user">
        <div class="dropdown">
          <label class="form-check-label me-2" for="example-radio-block1">商家用戶</label>
          <button type="button" class="btn btn-alt-secondary me-2" id="sidebar-themes-dropdown"
            data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-user opacity-50"></i>{{ user.data }}
          </button>
          <div class="dropdown-menu dropdown-menu-end fs-sm smini-hide border-0"
            aria-labelledby="sidebar-themes-dropdown">

            <RouterLink @click="logOut()" :to="{ name: '' }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">登出</span>
            </RouterLink>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="me-2">
          <RouterLink :to="{ name: 'login' }" class="btn btn-light" v-click-ripple>
            <i class="fa fa-fw fa-user opacity-50"></i>
          </RouterLink>
        </div>
      </div>
      <!-- Purchase Link -->
      <div class="me-2">
        <RouterLink :to="{ name: 'shoppingCart' }" class="btn btn-light" v-click-ripple>
          <i class="fa fa-fw fa-shopping-cart opacity-50"></i>
        </RouterLink>
      </div>
      <!-- END Purchase Link -->
    </template>
    <!-- END Header Content Right -->
  </BaseLayout>
</template>
<script>

export default {
  data() {
    return {
      admin: "",
      business: "",
      user: ""
    };
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem("access-admin"));
    this.business = JSON.parse(window.localStorage.getItem("access-business"));
  },
  persist: {
    enabled: true,
    strategies: [
      {
        storage: localStorage,
        paths: ['admin', 'business', "user"]
      }
    ]
  }
};

</script>
