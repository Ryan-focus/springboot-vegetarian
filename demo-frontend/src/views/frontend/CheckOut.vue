<script setup>
import { ref } from "vue";
import axios from "axios";

//sandBox 的金鑰
const token = ref();
const ClientId = 'AeUyriY-4yp17yjRVYJ8pQU8JCYE1KU4VA8PHxlMbkgVUE7asOuYBG8sqo1Aq99jneBeWJm57FHwxWy3'
const ClientSecreat = 'ELIidMH1ZGLqRl2465-wRqraK5CG2Msi6qxoZl1SY3tVMHERMiWYF4Z_IccPs48cIbkCQ9ZjH9RE0vSf'
// header的token
const config = ref(({
    headers: { 'Authorization': `` }
}));
const body = ref({

    "intent": "sale",
    "payer": {
        "payment_method": "paypal"
    },
    "transactions": [
        {
            "amount": {
                "total": "1000.00",
                "currency": "TWD"
            },
            "description": "Bill Id"
        }
    ],
    "redirect_urls": {
        "return_url": "https://example.com/return",
        "cancel_url": "https://example.com/cancel"
    }


})

function payment() {
    axios.post('https://api-m.sandbox.paypal.com/v1/payments/payment', body
        , config
    )
}

function getToken() {
    axios(
        {
            method: 'post',
            url: 'https://api.sandbox.paypal.com/v1/oauth2/token',
            data: 'grant_type=client_credentials', // => this is mandatory x-www-form-urlencoded. DO NOT USE json format for this
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded',// => needed to handle data parameter
                'Accept-Language': 'en_US',
            },
            auth: {
                username: ClientId,
                password: ClientSecreat
            },

        }).then
        ((res) => {
            console.log(JSON.stringify(res.data))
            config.value = res.data
            console.log(config)
        })
}
</script>
<template>
    <button @click="getToken()">按我token
    </button>
    <br>
    <button @click="payment()">按我結帳</button>

</template>
