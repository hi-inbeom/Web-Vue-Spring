<template>
  <div class="side-alert-wrapper">
    <!-- 여러 알림을 동적으로 렌더링 -->
    <div 
      v-for="(alert, index) in alerts" 
      :key="index" 
      class="side-alert" 
      :class="alert.type"
    >
      <p>{{ alert.message }}</p>
      <button @click="closeAlert(index)">Close</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      alerts: [], // 알림을 저장할 배열
    };
  },
  methods: {
    // 새로운 알림을 추가하는 함수
    showAlert(message, type = 'info') {
      this.alerts.push({ message, type });

      // 3초 후 자동으로 알림을 닫기
      setTimeout(() => {
        this.closeAlert(0); // 첫 번째 알림을 닫기
      }, 3000);
    },
    
    // 알림을 닫는 함수
    closeAlert(index) {
      this.alerts.splice(index, 1); // 해당 인덱스의 알림을 삭제
    },
  },
};
</script>

<style scoped>
.side-alert-wrapper {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
}

.side-alert {
  padding: 1rem;
  border-radius: 5px;
  color: white;
  margin-bottom: 10px;
}

.side-alert.success {
  background-color: green;
}

.side-alert.error {
  background-color: red;
}

.side-alert.info {
  background-color: blue;
}
</style>
