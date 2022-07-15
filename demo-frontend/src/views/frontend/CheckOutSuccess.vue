<script setup>
import { ref } from "vue";
import { useTemplateStore } from "@/stores/template";
import { useRoute } from "vue-router";
import axios from "axios";
console.log(useRoute().query.paymentId)
console.log(useRoute().query.PayerID)


axios.post(
    "http://localhost:8088/complete/payment", {
    "paymentId": useRoute().query.paymentId,
    "payerID": useRoute().query.PayerID,
}
).then(
    (res) => {
        console.log(res.data.payment)
        window.location.href = "http://localhost:8080/#/shopping/order", 5000
    }
)

// Main store
const store = useTemplateStore();
</script>

<template>
    <!-- Page Content -->
    <div class="hero-static d-flex align-items-center">
        <div class="w-100">
            <!-- Maintenance Section -->
            <div class="bg-body-extra-light">
                <div class="content content-full">
                    <div class="row justify-content-center">
                        <div class="col-md-8 col-lg-6 col-xl-4 py-6">
                            <!-- Header -->
                            <div class="text-center">
                                <p>
                                    <i class="fa fa-3x fa-cog fa-spin text-primary"></i>
                                </p>
                                <h1 class="h4 mb-1">付款成功，感謝購買</h1>
                                <h2 class="h6 fw-normal text-muted mb-3">
                                    即將跳轉頁面
                                </h2>
                            </div>
                            <!-- END Header -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- END Maintenance Section -->

            <!-- Footer -->
            <div class="fs-sm text-center text-muted py-3">
                <strong>{{ store.app.name + " " + store.app.version }}</strong> &copy;
                {{ store.app.copyright }}
            </div>
            <!-- END Footer -->
        </div>
    </div>
    <!-- END Page Content -->
</template>
