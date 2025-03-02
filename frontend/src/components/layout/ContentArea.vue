<template>
  <div class="area-wrapper">
      <router-view></router-view>
      <button @click="handleAlert">알림창 실험</button>
      <br> <br>
      <button @click="handleTestMode">기능 테스트</button>
      <br>
      <div>{{ testStore.testStatus }}</div>
  </div>
</template>

<script setup>
import { useTestStore } from '@/store/useTestStore';
import { useSideAlertStore } from '@/store/useSideAlertStore';

const sideAlertStore = useSideAlertStore();
const testStore = useTestStore();

// handleAlert 함수: 알림창을 띄우는 함수
function handleAlert() {
  sideAlertStore.showAlert("hello?", "success");
}

// handleTestMode 함수: 테스트 모드의 상태를 반전시키고 알림을 띄우는 함수
function handleTestMode() {
  testStore.updateTestStatus();  // 상태를 반전시킴
  const message = testStore.testStatus ? "테스트 모드가 활성화되었습니다." : "테스트 모드가 비활성화되었습니다.";
  sideAlertStore.showAlert(message, "success");
}
</script>

<style>
  .area-wrapper {
    margin: 0 auto;
    padding-top: 25px;
    width: 700px;
  }
</style>