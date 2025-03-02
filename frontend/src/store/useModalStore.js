import { defineStore } from "pinia";
import { ref } from "vue";

export const useModalStore = defineStore("modal", () => {
  const activateModal = ref("");

  function updateActivateModal(value) {
    activateModal.value = value;
  }

  function close() {
    activateModal.value = "";
  }

  return { activateModal, updateActivateModal, close };
});
