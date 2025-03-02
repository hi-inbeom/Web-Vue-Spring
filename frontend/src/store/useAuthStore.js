import { defineStore } from "pinia";
import { ref } from "vue";

export const useAuthStore = defineStore("auth", () => {
  const isLoggedIn = ref(false);

  function updateLoginStatus(value) {
    isLoggedIn.value = value;
  }

  return { isLoggedIn, updateLoginStatus };
});
