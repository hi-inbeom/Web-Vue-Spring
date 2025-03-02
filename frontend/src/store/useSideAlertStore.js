import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useSideAlertStore = defineStore('sideAlert', () => {
    const alerts = ref([]);
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
    
    const closeAlert = (index) => {
      alerts.value.splice(index, 1);
    };

    return {alerts, showAlert};
});
