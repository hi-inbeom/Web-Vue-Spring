<template>
    <div class="area-wrapper">
        <router-view></router-view>
        <button @click="handleAlert">알림창 실험</button>
        <SideAlert ref="sideAlert" />
    </div>
</template>
<script setup>
import { ref } from 'vue';
import SideAlert from '../alert/SideAlert.vue';


const sideAlert = ref(null);

function handleAlert() {
  sideAlert.value.showAlert("hello?", "success");
}
</script>
<style>
  .area-wrapper {
    margin: 0 auto;
    padding-top: 25px;
    width: 700px;
  }
</style>