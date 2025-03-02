<template>
  <div class="alert-container">
    <div
      v-for="(alert, index) in alerts"
      :key="index"
      :class="['alert', alert.type]"
      role="alert"
      class="alert-content"
    >
      <span>{{ alert.message }} </span>
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  </div>
</template>

<script setup>
import { useSideAlertStore } from '@/store/useSideAlertStore';
import { storeToRefs } from 'pinia';

const SideAlertStore = useSideAlertStore();
const { alerts } = storeToRefs(SideAlertStore);

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
