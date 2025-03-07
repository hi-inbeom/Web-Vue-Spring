<template lang="">
    <div>
        <div class="login-input-box">
            <input id="userId" type="text" v-model=userdto.userId name="userId" placeholder="아이디" autocomplete="off">
            <label for="userId">아이디</label>
        </div>

        <div class="login-input-box">
            <input id="userPassword" type="password" v-model=userdto.userPassword name="userPassword" placeholder="비밀번호" autocomplete="off">
            <label for="userPassword">비밀번호</label>
        </div>
        <div class="help-box">
            <div class="notice-login" @click="$emit('handleComponentKey', 1)"> 계정찾기 </div>
            <div class="notice-login" @click="$emit('handleComponentKey', 2)"> 회원가입 </div>
        </div>
        <span v-if="isViewWarning" class="login-warning-text">{{ WarningText }} <br> </span>
        <input class="account-submit-btn" type="submit" @click="handleLogin()" value="로그인">
    </div>
</template>

<script setup>
import { useModalStore } from '@/store/useModalStore';
import { useTestStore } from '@/store/useTestStore';
import { useUserStore } from '@/store/useUserStore';
import { useAuthStore } from '@/store/useAuthStore';
import { ref } from 'vue';
import axios from 'axios';

const modalStore = useModalStore();
const testStore = useTestStore();
const authStore = useAuthStore();
const userStore = useUserStore();
const userdto = userStore.userdto;

const isViewWarning = ref(false);
const WarningText = ref('');

// 로그인 처리 함수
const handleLogin = async () => {
  try {
    if (userdto.userId === "" || userdto.userPassword === "") {
        throw new Error('아이디와 비밀번호를 입력해주세요.');
    }
    if (!testStore.testStatus) {
        await axios.post("http://localhost:3000/user/login", userdto);
    }
    authStore.updateLoginStatus(true);
    modalStore.close();
  } catch (err) {
    WarningText.value = err.response.data.message || err.message || '서버와의 연결이 불안정한 상태입니다.';
    isViewWarning.value = true;
  }
};
</script>

<style>
    .login-input-box{
        position:relative;
        margin:10px 0;
    }
    .login-input-box > input{
        display:flex;
        background: rgb(218, 218, 218);
        border:none;
        border-radius: 15px;
        padding:16px 3px 8px 12px;
        font-size:14pt;
        width: 100%;
    }
    input::placeholder{
        color:transparent;
    }
    input:placeholder-shown + label{
        color:#aaa;
        font-size:12pt;
        top:15px;
        left:15px;
    }
    input:focus + label, label{
        color:#8aa1a1;
        font-size:10pt;
        pointer-events: none;
        position: absolute;
        left:12px;
        top:0px;
        transition: all 0.2s ease ;
        -webkit-transition: all 0.2s ease;
        -moz-transition: all 0.2s ease;
        -o-transition: all 0.2s ease;
    }
    input:focus, input:not(:placeholder-shown){
        border-bottom: solid 1px #8aa1a1;
        outline:none;
    }
    .help-box{
        display:flex;
        justify-content: space-between;
        margin-top:10px;
        color:rgba(0,0,0,0.35);
        font-size:14px;
        width:100%;
    }
    .help-box .notice-login{
        cursor: pointer;
    }
    .account-submit-btn{
        display: block;
        background: rgb(94,111,75);
        color:white;
        border:none;
        border-radius: 15px;
        padding:12px;
        width:100%;
        margin-top:10px;
        font-size:18pt;
        font-weight: bold;
        cursor:pointer;
    }
    .login-warning-text{
        color: red;
        font-size: 12pt;
        margin-top: 5px;
    }
</style>