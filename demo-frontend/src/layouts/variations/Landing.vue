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
// 取狀態
const admin = JSON.parse(window.localStorage.getItem("access-admin"));
const business = JSON.parse(window.localStorage.getItem("access-business"));

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
    timer: 500,
    icon: "info"
  });
  window.setTimeout(function () {
    // router.push({ name: "index" });
    router.go(0)
  }, 200);
}
</script>


<template>
  <BaseLayout>
    <!-- Header Content Left -->
    <!-- Using the available v-slot, we can override the default Side Overlay content from layouts/partials/Header.vue -->
    <template #header-content-left>
      <!-- Logo -->

      <RouterLink :to="{ name: 'index' }" class="fw-bold fs-lg tracking-wider text-dual ">
        <span class="smini-hide fs-3 tracking-wider">
          <img src="/assets/media/vegan/logo.png" alt="logo" class="w-50" />
        </span>
      </RouterLink>
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
          <BaseNavigation v-if="admin" :nodes="menu.admin" horizontal horizontal-hover horizontal-center />
          <BaseNavigation v-else-if="business" :nodes="menu.business" horizontal horizontal-hover horizontal-center />
          <BaseNavigation v-else-if="user" :nodes="menu.user" horizontal horizontal-hover horizontal-center />
          <BaseNavigation v-else :nodes="menu.demo" horizontal horizontal-hover horizontal-center />
        </div>
      </div>
      <!-- END Navigation -->
    </template>
    <!-- Header Content Right -->
    <!-- Using the available v-slot, we can override the default Side Overlay content from layouts/partials/Header.vue -->
    <template #header-content-right>


      <div v-if="admin">
        <div class="dropdown">
          <!-- <label class="form-check-label me-2" for="example-radio-block1">
            <span class="badge bg-danger rounded-pill">管理員</span></label> -->
          <button type="button" class="btn btn-alt-secondary me-2" id="page-header-user-dropdown"
            data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="badge bg-danger rounded-pill">管理員</span>
            <!-- <img class="rounded-circle" src="/assets/media/avatars/avatar10.jpg" alt="Header Avatar"
              style="width: 21px" /> -->
            <span class="d-none d-sm-inline-block ms-2">{{
                admin.data.user.userName
            }}</span>
            <i class="fa fa-fw fa-angle-down d-none d-sm-inline-block opacity-50 ms-1 mt-1"></i>
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
          <button type="button" class="btn btn-alt-secondary me-2" id="page-header-user-dropdown"
            data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <!-- <img class="rounded-circle" src="/assets/media/avatars/avatar10.jpg" alt="Header Avatar"
              style="width: 21px" /> -->
            <span class="badge bg-secondary rounded-pill">商家</span>
            <span class="d-none d-sm-inline-block ms-2">{{ business.data.business.businessName }}</span>
            <i class="fa fa-fw fa-angle-down d-none d-sm-inline-block opacity-50 ms-1 mt-1"></i>
          </button>
          <div class="dropdown-menu dropdown-menu-end fs-sm smini-hide border-0"
            aria-labelledby="sidebar-themes-dropdown">
            <RouterLink :to="{ name: 'business-backend-dashboard' }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">商家後台</span>
              <i class="si si-logout  fa-angle-down d-none d-sm-inline-block opacity-50 ms-1 mt-1s"></i>
            </RouterLink>
            <RouterLink @click="logOut()" :to="{ name: '' }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">登出</span>
              <i class="si si-logout  fa-angle-down d-none d-sm-inline-block opacity-50 ms-1 mt-1s"></i>
            </RouterLink>
          </div>
        </div>
      </div>
      <div v-else-if="user">
        <div class="dropdown">
          <!-- <label class="form-check-label me-2" for="example-radio-block1"><span
              class="badge bg-warning rounded-pill">會員</span></label> -->
          <button type="button" class="btn btn-alt-secondary me-2" id="page-header-user-dropdown"
            data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="badge bg-warning rounded-pill">會員</span>
            <!-- <img class="rounded-circle" src="/assets/media/avatars/avatar10.jpg" alt="Header Avatar"
              style="width: 21px" /> -->
            <span class="d-none d-sm-inline-block ms-2">{{ user.data.user.userName }}</span>
            <i class="fa fa-fw fa-angle-down d-none d-sm-inline-block opacity-50 ms-1 mt-1"></i>
          </button>
          <div class="dropdown-menu dropdown-menu-end fs-sm smini-hide border-0"
            aria-labelledby="sidebar-themes-dropdown">
            <RouterLink @click="logOut()" :to="{ name: '' }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">會員後台</span>
            </RouterLink>
            <RouterLink @click="logOut()" :to="{ name: index }"
              class="dropdown-item d-flex align-items-center justify-content-between">
              <span class="fs-sm fw-medium">登出</span>
              <i class="si si-logout  fa-angle-down d-none d-sm-inline-block opacity-50 ms-1 mt-1s"></i>
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