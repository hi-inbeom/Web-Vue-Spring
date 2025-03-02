<template>
  <div class="header-wrapper">
    <header>
      <router-link to="/" id="logo-wrapper">
        <img src="@/assets/logo.webp" alt="이미지" />
        <div>나무</div>
      </router-link>
      <div id="searchbar-wrapper">
        <input type="text" placeholder="Search" />
      </div>
      <div class="LogButton" id="LoginButton" @click="openModal" v-if="authStore.isLoggedIn === false">
        Log In
      </div>
      <div class="LogButton" id="LogoutButton" @click="logout" v-if="authStore.isLoggedIn === true">
        Log Out
      </div>
    </header>
  </div>
</template>

<script setup>
import axios from 'axios';
import { useModalStore } from '@/store/useModalStore';
import { useAuthStore } from '@/store/useAuthStore';

const modalStore = useModalStore();
const authStore = useAuthStore();

const logout = async () => {
  try {
    await axios.post("http://localhost:3000/user/logout");
    authStore.updateIsLoggedIn(false);
  } catch (err) {
    console.log('Error :', err.message);
  }
};

const openModal = () => {
  modalStore.updateActivateModal('user');
};
</script>


<style scoped>
.header-wrapper {
  min-height: 80px;
  height: 80px;
  position: sticky;
  top: 0;
  background-color: white;
  border-bottom: 1px solid rgb(169, 169, 169);
  padding: 0 15px;
}
header {
    display: flex;
    justify-content: space-between;
    align-items: center; 
    height: 100%;
    position: relative;
    padding: 0 15px;
}
body {
    margin: 0;
}

#logo-wrapper {
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 10px;
    flex-shrink: 0;
    text-decoration: none;
}
#logo-wrapper img {
    width: 40px;
    height: 40px;
}
#logo-wrapper div {
    font-size: 40px;
    font-weight: bold;
    color: gray;
}

#searchbar-wrapper {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    gap: 5px;
    max-width: 500px;
    width: 100%;
    border: 1px solid #ccc;
    border-radius: 20px;
    padding : 0 10px;
}
#searchbar-wrapper input {
    flex: 1;
    height: 30px;
    padding: 5px 5px;
    margin-right:10px;
    border: none;
    outline: none;
}

.LogButton {
    padding: 8px 16px;
    background-color: rgb(94,111,75);
    color: white;
    border: none;
    border-radius: 20px;
    cursor: pointer;
}

.LogButton:hover {
    background-color: rgb(74, 87, 59);
}
</style>
