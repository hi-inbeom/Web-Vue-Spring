<template>
  <div id="app">
    <AppHeader/>
    <div class="main-layout">
      <LeftBar/>
      <!-- router-view로 SPA적용할 구역 -->
      <RightBar/>
    </div>
  </div>
</template>

<script>
import AppHeader from '@/components/layout/AppHeader.vue'
import LeftBar from '@/components/layout/LeftBar.vue'
import RightBar from '@/components/layout/RightBar.vue'

export default {
  name: 'App',
  components: {
    AppHeader,
    LeftBar,
    RightBar
  }
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
