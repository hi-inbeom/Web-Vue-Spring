<template>
  <div id="app">
    <AppHeader/>
    <div class="main-layout">
      <LeftBar/>
      <div class="router-view-container">
        <!-- <RouterView/> -->
      </div>
      <RightBar/>
    </div>
  </div>
</template>

<script>
import AppHeader from '@/views/AppHeader.vue'
import LeftBar from '@/views/LeftBar.vue'
import RightBar from '@/views/RightBar.vue'
// import { RouterView } from "vue-router";

export default {
  name: 'App',
  components: {
    AppHeader,
    LeftBar,
    RightBar
    // RouterView
  },
  data: () => ({ })
}
</script>

<style>
html, body {
  height: 100%;
  margin: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.main-layout {
  display: flex;
  flex: 1;
  justify-content: space-between;
}
</style>
