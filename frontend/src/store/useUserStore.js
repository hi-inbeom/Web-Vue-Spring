import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const userdto = ref({
    userId: "",
    userPassword: "",
    userVerifyPassword: "",
    userName: "",
    userEmail: "",
  });

  function updateUserDto(value) {
    userdto.value = value;
  }

  function updateUserDtoField(field, value) {
    userdto.value[field] = value;
  }

  function resetUserDto() {
    userdto.value.userId = "",
    userdto.value.userPassword = "",
    userdto.value.userVerifyPassword = "",
    userdto.value.userName = "",
    userdto.value.userEmail = ""
  }

  return { userdto, updateUserDto, updateUserDtoField, resetUserDto };
});