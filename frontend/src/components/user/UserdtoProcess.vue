<template>
    <div class="join-input-box">
        <input 
            id="userId"
            type="text"
            name="userId"
            v-model="userdto.userId"
            placeholder="아이디"
            autocomplete="off"
            @blur="handleIdWarning"
            :disabled="!props.isJoin">
        <label for="email">아이디</label>
        <span v-if="isIdWarning" class="join-warning-text"> {{commentWarnings.idWarning}} </span>
    </div>
    <div class="join-input-box">
        <input
            id="userName"
            type="text"
            name="userName"
            v-model="userdto.userName"
            placeholder="닉네임"
            autocomplete="off"
            @blur="handleNameWarning"
            :disabled="!props.isJoin">
        <label for="userName">닉네임</label>
        <span v-if="isNameWarning" class="join-warning-text"> {{commentWarnings.nameWarning}} </span>
    </div>
    <div class="join-input-box">
        <input
            id="userPassword"
            type="password"
            name="userPassword"
            v-model="userdto.userPassword"
            placeholder="비밀번호"
            autocomplete="off"
            @blur="handlePwdWarning"
        />
        <label for="userPassword">비밀번호-4글자 이상</label>
        <span v-if="isPwdWarning" class="join-warning-text">{{commentWarnings.pwdWarning}}</span>
    </div>
    <div class="join-input-box">
        <input
            id="userVerifyPassword"
            type="password"
            name="userVerifyPassword"
            v-model="userdto.userVerifyPassword"
            placeholder="비밀번호 확인"
            autocomplete="off"
            @blur="handleVerifyPwdWarning"
        />
        <label for="userVerifyPassword">비밀번호 확인</label>
        <span v-if="isVerifyPwdWarning" class="join-warning-text"> {{commentWarnings.verifyPwdWarning}} </span>
    </div>
    <div class="account-next-btn"
        @click="submitProcess"> {{ BtnText }} </div>
</template>

<script setup>
import { ref, computed, defineProps } from 'vue';
import { useUserStore } from '@/store/useUserStore';
import { useTestStore } from '@/store/useTestStore';
import { useModalStore } from '@/store/useModalStore';
import axios from 'axios';

const props = defineProps({
    isJoin: Boolean
});

const testStore = useTestStore();
const modalStore = useModalStore();
const userStore = useUserStore();
const userdto = userStore.userdto;

const isIdWarning = ref(false);
const isNameWarning = ref(false);
const isPwdWarning = ref(false);
const isVerifyPwdWarning = ref(false);
const commentWarnings = ref({
    idWarning: "",
    nameWarning: "",
    pwdWarning: "",
    verifyPwdWarning: ""
})

const BtnText = computed(() => props.isJoin ? '회원가입' : '계정 정보 수정');

// 회원가입 및 정보 수정 함수
const submitProcess = async () => {
    try {
        handleIdWarning();
        handleNameWarning();
        handlePwdWarning();
        handleVerifyPwdWarning();
        if (props.isJoin) {
            await axios.post("http://localhost:3000/user/sign/signup", userdto);
        } else {
            await axios.patch("http://localhost:3000/user/update", userdto);
        }
        modalStore.close();
        userStore.resetUserDto();
    } catch (err) {
        console.log('Error :', err.response?.data.message || err.message);
    }
};

// blur이벤트 아이디 입력 유무과 아이디 길이 확인
const handleIdWarning = async () => {
    try {
        if(userdto.userId === "") {
            throw new Error('아이디를 입력해주세요')
        }
        if(userdto.userId.length < 4) {
            throw new Error('아이디는 4글자 이상이어야 합니다.')
        }

        if(!testStore.testStatus) {
            await axios.post(
                "http://localhost:3000/user/sign/check-id",
                userdto.userId,
                // text/plain미설정 시  값 뒤에 = 혹은 ""가 발생
                { headers: { 'Content-Type': 'text/plain' } }
            );
        }

        isIdWarning.value = false;
    } catch (err) {
        commentWarnings.value.idWarning = err.response?.data.message || err.message || '서버와의 연결이 불안정한 상태입니다.';
        isIdWarning.value = true;
    }
};

// blur이벤트 입력 유무 확인
const handleNameWarning = async () => {
    try {
        if(userdto.userName === "") {
            throw new Error('닉네임을 입력해주세요.')
        }
        
        if(!testStore.testStatus) {
            await axios.post(
                "http://localhost:3000/user/sign/check-name",
                userdto.userName,
                { headers: { 'Content-Type': 'text/plain' } }
            );
        }
        isNameWarning.value = false;
    } catch (err) {
        commentWarnings.value.nameWarning = err.response?.data.message || err.message || '서버와의 연결이 불안정한 상태입니다.';
        isNameWarning.value = true;
    }
};

const handlePwdWarning = () => {
    if(userdto.userPassword==="") {
        commentWarnings.value.pwdWarning = '비밀번호를 입력해주세요.';
        isPwdWarning.value = true;
        return;
    } else {
        isPwdWarning.value = false;
    }
    if(userdto.userPassword.length < 4) {
        commentWarnings.value.pwdWarning = '비밀번호는 4글자 이상이어야 합니다.';
        isPwdWarning.value = true;
        return;
    } else {
        isPwdWarning.value = false;
    }
};

const handleVerifyPwdWarning = () => {
    if(userdto.userPassword !== userdto.userVerifyPassword) {
        commentWarnings.value.verifyPwdWarning = '비밀번호가 일치하지 않습니다.';
        isVerifyPwdWarning.value = true;
    } else {
        isVerifyPwdWarning.value = false;
    }
};
</script>


<style scoped>
    .join-input-box{
        position:relative;
        margin:10px 0;
    }
    .join-input-box > input{
        display:flex;
        background: rgb(218, 218, 218);
        border:none;
        border-radius: 15px;
        padding:16px 3px 8px 12px;
        font-size:14pt;
        width:100%;
    }
    .join-warning-text{
        color: red;
        font-size: 12pt;
        margin-top: 5px;
    }
    .account-next-btn{
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
</style>