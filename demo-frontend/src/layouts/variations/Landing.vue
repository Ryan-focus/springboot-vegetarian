<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";

import BaseLayout from "@/layouts/BaseLayout.vue";

import BaseNavigation from "@/components/BaseNavigation.vue";

// Grab menu navigation arrays
import menu from "@/data/menu";

// Helper variables for mobile navigation visibility
const mobileVisibleNavHoverCentered = ref(false);
// Main store
const store = useTemplateStore();

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
</script>

<template>
  <BaseLayout>
    <!-- Header Content Left -->
    <!-- Using the available v-slot, we can override the default Side Overlay content from layouts/partials/Header.vue -->
    <template #header-content-left>
      <!-- Logo -->

      <RouterLink
        :to="{ name: 'index' }"
        class="fw-bold fs-lg tracking-wider text-dual me-2"
      >
        <span class="smini-hide fs-3 tracking-wider">
          <img src="/assets/media/vegan/logo.png" alt="..." class="w-50" />
        </span>
      </RouterLink>

      <!-- END Logo -->
    </template>
    <!-- END Header Content Left -->

    <template #header-content-middle>
      <!-- Horizontal Navigation - Hover Centered -->
      <div class="p-3 push">
        <!-- Toggle Navigation -->
        <div class="d-lg-none">
          <button
            type="button"
            class="btn w-100 btn-alt-secondary d-flex justify-content-between align-items-center"
            @click="
              mobileVisibleNavHoverCentered = !mobileVisibleNavHoverCentered
            "
          >
            <i class="fa fa-bars"></i>
          </button>
        </div>
        <!-- END Toggle Navigation -->

        <!-- Navigation -->
        <div
          class="d-lg-block mt-2 mt-lg-0"
          :class="{
            'd-none': !mobileVisibleNavHoverCentered,
          }"
        >
          <BaseNavigation
            :nodes="menu.demo"
            horizontal
            horizontal-hover
            horizontal-center
          />
        </div>
      </div>
      <!-- END Navigation -->
    </template>
    <!-- Header Content Right -->
    <!-- Using the available v-slot, we can override the default Side Overlay content from layouts/partials/Header.vue -->
    <template #header-content-right>
      <!-- Options -->
      <div class="dropdown">
        <button
          type="button"
          class="btn btn-alt-secondary me-2"
          id="sidebar-themes-dropdown"
          data-bs-toggle="dropdown"
          data-bs-auto-close="outside"
          aria-haspopup="true"
          aria-expanded="false"
        >
          <i class="far fa-circle"></i>
        </button>
        <div
          class="dropdown-menu dropdown-menu-end fs-sm smini-hide border-0"
          aria-labelledby="sidebar-themes-dropdown"
        >
          <!-- Color Themes -->
          <!-- 對應首頁上方nav的切換按鈕 -->
          <button
            type="button"
            class="dropdown-item d-flex align-items-center justify-content-between fw-medium"
            @click.prevent="store.setColorTheme({ theme: '' })"
          >
            <span>Default</span>
            <i class="fa fa-circle text-default"></i>
          </button>
          <button
            type="buttbuttonn"
            class="dropdown-item d-flex align-items-center justify-content-between fw-medium"
            @click.prevent="store.setColorTheme({ theme: 'amethyst' })"
          >
            <span>Amethyst</span>
            <i class="fa fa-circle text-amethyst"></i>
          </button>
          <button
            type="button"
            class="dropdown-item d-flex align-items-center justify-content-between fw-medium"
            @click.prevent="store.setColorTheme({ theme: 'city' })"
          >
            <span>City</span>
            <i class="fa fa-circle text-city"></i>
          </button>
          <button
            type="button"
            class="dropdown-item d-flex align-items-center justify-content-between fw-medium"
            @click.prevent="store.setColorTheme({ theme: 'flat' })"
          >
            <span>Flat</span>
            <i class="fa fa-circle text-flat"></i>
          </button>
          <button
            type="button"
            class="dropdown-item d-flex align-items-center justify-content-between fw-medium"
            @click.prevent="store.setColorTheme({ theme: 'modern' })"
          >
            <span>Modern</span>
            <i class="fa fa-circle text-modern"></i>
          </button>
          <button
            type="button"
            class="dropdown-item d-flex align-items-center justify-content-between fw-medium"
            @click.prevent="store.setColorTheme({ theme: 'smooth' })"
          >
            <span>Smooth</span>
            <i class="fa fa-circle text-smooth"></i>
          </button>
          <!-- END Color Themes -->
        </div>
      </div>
      <!-- END Options -->

      <RouterLink
        :to="{ name: 'login' }"
        class="btn btn-warning"
        v-click-ripple
      >
        <i class="fa fa-fw fa-user opacity-50"></i>
      </RouterLink>
      <!-- Purchase Link -->
      <a class="btn btn-success" href="#" v-click-ripple>
        <i class="fa fa-fw fa-shopping-cart opacity-50"></i>
        <!-- <span class="d-none d-sm-inline-block ms-2">購物車</span> -->
      </a>
      <!-- END Purchase Link -->
    </template>
    <!-- END Header Content Right -->
  </BaseLayout>
</template>
