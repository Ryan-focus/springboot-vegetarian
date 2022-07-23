<script setup>
import { useTemplateStore } from "@/stores/template";

// Main store
const store = useTemplateStore();
</script>

<template>
  <!-- Footer -->
  <footer id="page-footer">
    <div class="bg-body-light">
      <div class="content py-3">
        <div class="row fs-sm">
          <div class="col-sm-6 order-sm-2 py-1 text-center text-sm-end"> 本網站僅作為 <i class="fa fa-heart text-danger"></i>
            <a class="fw-semibold" href="https://www.ispan.com.tw/" target="_blank">資展國際</a>專題使用
          </div>
          <div class="col-sm-6 order-sm-1 py-1 text-center text-sm-start"><a class="fw-semibold"
              href="https://github.com/Ryan-focus/springboot-vegetarian"> EEIT45 - 跨域JAVA班 - 第一組 </a> © {{
                  store.app.copyright
              }}</div>
        </div>
      </div>
    </div>
  </footer>
  <!-- END Footer -->
</template>
