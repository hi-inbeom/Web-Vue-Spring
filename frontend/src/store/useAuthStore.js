import { defineStore } from "pinia";
import { ref } from "vue";

export const useAuthStore = defineStore("auth", () => {
  // 상태 (ref 사용)
  const isLoggedIn = ref(false);
  const verifyEmailCode = ref("");

  // 액션
  function updateLoginStatus(value) {
    isLoggedIn.value = value;
  }

  function updateVerifyEmailCode(value) {
    verifyEmailCode.value = value;
  }

  return { isLoggedIn, verifyEmailCode, updateLoginStatus, updateVerifyEmailCode };
});
