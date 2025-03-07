<template>
    <div class="join-input-box">
        <input id="userEmail"
                type="text"
                name="userEmail"
                placeholder="이메일"
                autocomplete="off"
                v-model="user.userEmail"
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
const user = ref({
    userEmail : ""
});
const sendUserEmail = ref("");

// BtnText 계산된 값
const BtnText = computed(() => {
  return props.isJoin ? '계정생성' : '계정찾기';
});

// 이메일 중복 검사 및 코드 전송 함수
const sendVerifyCode = async () => {
    try {
        // 이메일 코드 전송
        if (testStore.testStatus) {
            EmailCode.value = "123123";
        } else {
            if (props.isJoin) {
                const response = await axios.post('http://localhost:3000/user/sign/send-verify-code', user.value);
                EmailCode.value = String(response.data);
            } else {
                console.log("????")
                const response = await axios.post('http://localhost:3000/user/sign/check-email', user.value);
                EmailCode.value = String(response.data);
            }
        }
        document.getElementById('userEmail').setAttribute('readonly', true);
        isViewWarning.value = false;
        isSendCode.value = true;
        sendUserEmail.value = user.value.userEmail;
        console.log(EmailCode.value)
    } catch (err) {
        handleErrorMessage(err.response?.data||err)
    }
};
// 인증번호 검증 함수
const checkVerifyCode = async () => {
    try {
        if (inputCode.value !== EmailCode.value) {
            throw new Error('코드가 일치하지 않습니다.');
        }
        if (sendUserEmail.value !== user.value.userEmail) {
            throw new Error('이메일이 변경되었습니다. 절차를 다시 진행해주세요.');
        }
        if (!testStore.testStatus) {
            await axios.post('http://localhost:3000/user/sign/verify-code', inputCode.value, {
            headers: {
                'Content-Type': 'text/plain'
                }
            });
        }
        userStore.updateUserDtoField('userEmail', user.value.userEmail );
        isViewWarning.value = false;
        if (props.isJoin) {
            emit('handleComponentKey', 4);
        } else {
            const response = await axios.post('http://localhost:3000/user/find', user.value);
            userStore.updateUserDto(response.data);
            console.log(response.data);
            emit('handleComponentKey', 3);
        }
    } catch (err) {
        handleErrorMessage(err.response?.data||err)
    }

};

const handleUserEmail = async () => {
    try {
        // 이메일 유효성 검사
        if (user.value.userEmail === "") {
            throw new Error('이메일을 입력해주세요.');
        }
        if (!(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(user.value.userEmail))) {
            throw new Error('올바른 이메일주소가 아닙니다.');
        }
        isViewWarning.value = false;
    } catch (err) {
        // err에 response 객체가 있을 경우, response.data를 사용하고,
        // 없을 경우에는 err 자체를 사용합니다.
        handleErrorMessage(err.response?.data||err)
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