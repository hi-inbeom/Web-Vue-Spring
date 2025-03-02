<template>
    <div class="join-input-box">
        <input id="userEmail"
                type="text"
                name="userEmail"
                placeholder="이메일"
                autocomplete="off"
                v-model="userEmail"
                @blur="handleUserEmail">
        <label for="userEmail">이메일</label>
    </div>
    <div v-if="isSendCode" class="join-input-box">
        <input id="email-code"
                type="text"
                placeholder="인증번호"
                v-model="inputCode"
                autocomplete="off">
        <label for="email-code">인증번호</label>
    </div>
    <span v-if="isViewWarning" class="join-warning-text">{{ WarningText }} <br> </span>
    <span v-if="testStore.testStatus && isSendCode" class="join-warning-text">{{ EmailCode }}</span>
    <div v-if="!isSendCode" class="account-next-btn" @click="sendVerifyCode"> 인증번호 전송 </div>
    <div v-if="isSendCode" class="account-next-btn" @click="checkVerifyCode">{{ BtnText }}</div>

</template>

<script setup>
import { ref, defineProps, computed, defineEmits } from 'vue';
import { useTestStore } from '@/store/useTestStore';
import { useUserStore } from '@/store/useUserStore';
import axios from 'axios';

// props로 받은 값
const props = defineProps({
  isJoin: Boolean
});
const emit = defineEmits([
    'handleComponentKey'
]);

const testStore = useTestStore();
const userStore = useUserStore();

// 데이터 변수들
const isViewWarning = ref(false);
const isSendCode = ref(false);
const WarningText = ref('');
let EmailCode = ref('');
const inputCode = ref("");
const userEmail = ref("");
const sendUserEmail = ref("");

// BtnText 계산된 값
const BtnText = computed(() => {
  return props.isJoin ? '계정생성' : '계정찾기';
});

// 이메일 중복 검사 및 코드 전송 함수
const sendVerifyCode = async () => {
    try {
        handleUserEmail();
        // 이메일 코드 전송
        if (testStore.testStatus) {
            EmailCode.value = "123123";
        } else {
            EmailCode.value = await axios.post('http://localhost:3000/user/sign/send-verify-code', userEmail.value);
        }
        if (props.isJoin) {
            document.getElementById('userEmail').setAttribute('readonly', true);
        }
        isViewWarning.value = false;
        isSendCode.value = true;
        sendUserEmail.value = userEmail.value;
    } catch (err) {
        handleErrorMessage(err)
    }
};
// 인증번호 검증 함수
const checkVerifyCode = async () => {
    try {
        handleUserEmail();
        if (inputCode.value !== EmailCode.value) {
            throw new Error('코드가 일치하지 않습니다.');
        }
        if (sendUserEmail.value !== userEmail.value) {
            throw new Error('이메일이 변경되었습니다. 절차를 다시 진행해주세요.');
        }
        if (!testStore.testStatus) {
            await axios.post('http://localhost:3000/user/sign/verify-code', inputCode.value);
        }
        userStore.updateUserDtoField('userEmail', userEmail.value );
        isViewWarning.value = false;
    } catch (err) {
        handleErrorMessage(err)
        return;
    }

    if (props.isJoin) {
        emit('handleComponentKey', 4);
    } else {
        emit('handleComponentKey', 3);
    }
};

const handleUserEmail = async () => {
    try {
        // 이메일 유효성 검사
        if (userEmail.value === "") {
            throw new Error('이메일을 입력해주세요.');
        }
        if (!(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(userEmail.value))) {
            throw new Error('올바른 이메일의 형태가 아닙니다.');
        }
        // 중복 검사
        if (!testStore.testStatus) {
            if (props.isJoin) {
                await axios.post('http://localhost:3000/user/sign/email-duplicate-check', userEmail.value);
                // throw new Error('이미 가입된 이메일입니다.');
            } else {
                await axios.post('http://localhost:3000/user/sign/email-duplicate-check', userEmail.value);
                // throw new Error('가입되지 않은 이메일입니다.');
            }
        }
    } catch (err) {
        handleErrorMessage(err)
    }
}

const handleErrorMessage = (err) => {
    WarningText.value = err.message || '서버와의 연결이 불안정한 상태입니다.';
    isViewWarning.value = true;
}
</script>

<style scoped>
    .join-input-box{
        position: relative;
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
    .join-input-box > input:read-only {
        background: rgb(184, 184, 184);
        color: rgb(71, 71, 71);
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