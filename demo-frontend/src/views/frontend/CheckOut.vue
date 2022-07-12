<script setup>
import { ref } from "vue";
import axios from "axios";
const sum = ref()
const url = ref()
function payment() {
    axios.post(
        "http://localhost:8088/paypal/payment?sum=" + sum.value
    ).then(
        (res) => {
            // 抓出返回值得跳轉頁面網址
            if (res.data.redirect_url != null) {
                window.location = res.data.redirect_url
            }
        }
    )
}
</script>
<template>
    <br>
    <input type="text" v-model="sum">
    <button @click="payment()">按我結帳</button>

</template>
