<template>
    <div class="header-wrapper">
      <header>
        <router-link to="/" id="logo-wrapper">
          <img src="@/assets/logo.webp" alt="이미지" />
          <div>나무</div>
        </router-link>
        <div id="searchbar-wrapper">
          <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e8eaed"><path d="M784-120 532-372q-30 24-69 38t-83 14q-109 0-184.5-75.5T120-580q0-109 75.5-184.5T380-840q109 0 184.5 75.5T640-580q0 44-14 83t-38 69l252 252-56 56ZM380-400q75 0 127.5-52.5T560-580q0-75-52.5-127.5T380-760q-75 0-127.5 52.5T200-580q0 75 52.5 127.5T380-400Z"/></svg>
          <input type="text" placeholder="Search" />
        </div>
        <div class="LogButton" id="LoginButton" @click="openLoginModal" v-if="!isLoggedIn">
          Log In
        </div>
        <div class="LogButton" id="LogoutButton" @click="logout" v-if="isLoggedIn">
          Log Out
        </div>
        <Modal v-if="isModalVisible"/>
      </header>
    </div>
  </template>

<script>
import Modal from '@/components/user/Modal.vue'
import { mapGetters, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'AppHeader',
  components: { Modal },
  computed: {
    ...mapGetters(['isLoggedIn', 'isModalVisible'])
  },
  methods: {
    ...mapActions(['updateLoginStatus', 'updateModalVisibility']),
    async logout() {
      try {
        await axios.post("http://localhost:3000/user/logout");
        this.updateLoginStatus(false);
      } catch (err) {
        console.log('Error :',err.message);
      }
    },
    openLoginModal() {
      this.updateModalVisibility(true);
    }
  }
}
</script>

<style>

.header-wrapper {
  height: 80px;
  border-bottom: 1px solid rgb(169, 169, 169);
  padding: 0px 15px;
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
