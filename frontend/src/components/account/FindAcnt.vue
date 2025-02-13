<template>
    <div class="join-input-box">
        <input id="userEmail"
                type="text"
                name="userEmail"
                placeholder="이메일"
                autocomplete="off"
                v-model="user.userEmail">
        <label for="userEmail">이메일</label>
        <span v-if="joinWarning" class="join-warning-text">올바른 이메일의 형태가 아닙니다.</span>
    </div>
    <div class="account-next-btn" @click="handleFirstProcess">이메일 인증</div>
</template>
<script>
import axios from 'axios';

export default {
    data: () => ({
        user: {
            userEmail: ""
        }
    }),
    methods: {
        async handleFirstProcess() {
            try {
                await axios.post("http://localhost:3000/user/sign/verify-email", this.user);
                this.joinWarning = false;
                this.joinProcess = 1;
                this.verifyCode = Math.floor(Math.random() * 10000).toString().padStart(4, '0');
            } catch (err) {
                this.joinWarning = true;
            }
        }
    }
}
</script>
<style>
    
</style>