<template>
    <router-view v-if="isRouterAlive" />
</template>

<style lang="scss">
// Main Stylesheet
@import "@/assets/scss/main";

// All color themes are included and available by default
// Feel free to comment out any of them if you won't use them in your project
@import "@/assets/scss/oneui/themes/amethyst";
@import "@/assets/scss/oneui/themes/city";
@import "@/assets/scss/oneui/themes/flat";
@import "@/assets/scss/oneui/themes/modern";
@import "@/assets/scss/oneui/themes/smooth";
</style>
<script>

import { defineComponent, ref, nextTick, provide } from 'vue';

export default defineComponent({
    setup() {

        const isRouterAlive = ref(true);

        const reload = () => {

            isRouterAlive.value = false;

            nextTick(() => {

                isRouterAlive.value = true;

            });

        };

        provide("reload", reload);

        return {
            isRouterAlive,

        };

    }

})

</script>
