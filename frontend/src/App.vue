<template>
  <div id="app">
    <AppHeader class="app-header"/>
    <div class="app-main">
      <LeftBar/>
      <ContentArea/>
      <RightBar/>
    </div>
    <UserModal v-if="modalStore.activateModal==='user'"/>
    <SideAlert/>
  </div>
</template>

<script setup>
import AppHeader from '@/components/layout/AppHeader.vue';
import LeftBar from '@/components/layout/LeftBar.vue';
import RightBar from '@/components/layout/RightBar.vue';
import ContentArea from '@/components/layout/ContentArea.vue';

import UserModal from '@/components/user/UserModal.vue'
import SideAlert from './components/alert/SideAlert.vue';
import { useModalStore } from '@/store/useModalStore';
const modalStore = useModalStore();
</script>


<style>
html, body {
  height: 100vh;
  margin: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh;
  flex-direction: column;
  display: flex;
}

.app-main {
  display: flex;
  justify-content: space-between;
  flex-grow: 1;
}
</style>
