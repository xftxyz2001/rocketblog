<template>
    {{ pageInfo.list }}
</template>

<script setup>
const pageInfo = ref({});
axios.get("/user/chat/sessions").then((res) => {
    var result = res.data;
    if (result.code == 0) {
        var pageInfo = result.data;
        console.log(pageInfo.list);
    } else {

    }
});
</script>

<script>
import axios from "axios";
import { ref } from "vue";
export default {};
</script>

<style></style>