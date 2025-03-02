<template>
  <div class="alert-container">
    <div
      v-for="(alert, index) in alerts"
      :key="index"
      :class="['alert', alert.type]"
      role="alert"
    >
      {{ alert.message }}
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, defineExpose } from "vue";

// 상태 변수
const alerts = ref([]);
// 알림 표시 함수
const showAlert = (message, type = "fail") => {
  if (type === "info") {
    type = "alert alert-primary alert-dismissible fade show";
  }
  if (type === "success") {
    type = "alert alert-success alert-dismissible fade show";
  }
  if (type === "fail") {
    type = "alert alert-danger alert-dismissible fade show";
  }
  alerts.value.push({ message, type });

  setTimeout(() => {
    closeAlert(0);
  }, 3000);
};

// 알림 닫기 함수
const closeAlert = (index) => {
  alerts.value.splice(index, 1);
};

// defineExpose는 Vue 3에서 script setup에서 자동으로 사용할 수 있습니다.
// 별도로 import할 필요는 없습니다.
defineExpose({
  showAlert,
});
</script>

<style scoped>
.alert-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  z-index: 9999;
}


.close-btn {
  background: none;
  border: none;
  color: white;
  font-size: 16px;
  cursor: pointer;
  padding: 0 10px;
}
</style>
