<script setup>
import { ref } from "vue";
import axios from "axios";
const sum = ref()
function payment() {
    axios.post(
        "http://localhost:8088/paypal/payment?sum=" + sum.value
    ).then(
        (res) => {
            console.log(res)
        }
    )
}
</script>
<template>
    <br>
    <input type="text" v-model="sum">
    <button @click="payment()">按我結帳</button>

</template>
