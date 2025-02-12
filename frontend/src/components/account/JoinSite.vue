<template lang="">
    <!-- 이메일 입력 -->
    <div v-if="joinProcess==0" class="processEmailWrapper">
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
        <div class="account-next-btn" @click="handleFirstProcess">이메일 인증
        </div>
    </div>
    <!-- 이메일 검증 -->
    <div v-if="joinProcess==1" class="processVerifyCodeWrapper">
        <div class="join-input-box">
            <input id="email-code"
                    type="text"
                    placeholder="인증번호"
                    v-model="inputCode"
                    autocomplete="off">
            <label for="email-code">인증번호</label>
        </div>
        <span class="join-warning-text">{{ verifyCode }}</span> <!-- TEST CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
        <span v-if="joinWarning" class="join-warning-text">인증번호가 다릅니다.</span>
        <div class="account-next-btn"
            @click="handleSecondProcess">계정생성
        </div>
    </div>
    <!-- 계정 생성 -->
    <div v-if="joinProcess==2" class="processJoinWrapper">
        <!-- 입력값-아이디 -->
        <div class="join-input-box">
            <input id="userId"
                    type="text"
                    name="userId"
                    v-model="user.userId"
                    placeholder="아이디"
                    autocomplete="off"
                    @blur="handleIdWarning">
            <label for="email">아이디</label>
            <span v-if="idWarning" class="join-warning-text"> {{commentIdwarning}} </span>
        </div>
        <div class="join-input-box">
            <input id="userName"
                    type="text"
                    name="userName"
                    v-model="user.userName"
                    placeholder="닉네임"
                    autocomplete="off"
                    @blur="handleNameWarning">
            <label for="userName">닉네임</label>
            <span v-if="nameWarning" class="join-warning-text"> {{commontNameWarning}} </span>
        </div>
        <!-- 입력값-비밀번호 -->
        <div class="join-input-box">
            <input
                id="userPassword"
                type="password"
                name="userPassword"
                v-model="user.userPassword"
                placeholder="비밀번호"
                autocomplete="off"
                @blur="handlePwdLength"
            />
            <label for="userPassword">비밀번호-4글자 이상</label>
            <span v-if="checkLengthWarning" class="join-warning-text">비밀번호는 4글자 이상이어야 합니다.</span>
        </div>
        <div class="join-input-box">
            <input
                id="userVerifyPassword"
                type="password"
                name="userVerifyPassword"
                v-model="user.userVerifyPassword"
                @blur="handlePwdMatch"
                placeholder="비밀번호 확인"
                autocomplete="off"
            />
            <label for="userVerifyPassword">비밀번호 확인</label>
            <span v-if="passwordVerifyWarning" class="join-warning-text">비밀번호가 일치하지 않습니다.</span>
        </div>
        <!-- 가입 요청 -->
        <div class="account-next-btn"
            @click="handleLastProcess"> 회원가입 </div>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    name: 'JoinSite',
    data: () => ({
        joinProcess: 0,
        joinWarning: false,
        verifyCode: "",
        inputCode: "",
        idWarning: false,
        commentIdwarning: "",
        commontNameWarning: "",
        nameWarning: false,
        checkLengthWarning: false,
        passwordVerifyWarning: false,
        user: {
            userId: "",
            userPassword: "",
            userVerifyPassword: "",
            userName: "",
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
        },
        async handleSecondProcess() {
            // try {
            //     await axios.post("http://localhost:3000/user/sign/check-code", this.verifyCode)
            //     this.joinWarning = false;
            // } catch (err) {
            //     this.joinWarning = true;
            // }
            if (this.verifyCode === this.inputCode) {
                this.joinProcess = 2;
                this.joinWarning = false;
            } else {
                this.joinWarning = true;
            }
        },
        async handleLastProcess() {
            // DB연결하여 아이디 중복 체크
            try {
                await axios.post("http://localhost:3000/user/sign/signup", this.user);
                this.$emit('callCloseModal');
            } catch (err) {
                console.log('Error :',err.message);
                // 모달창으로 에러를 보여줘야 될 듯
            }
        },
        async handleIdWarning() {
            if (this.user.userId.length < 4) {
                this.commentIdwarning = "아이디는 4글자 이상이어야 합니다.";
            } else {
                try {
                    await axios.post("http://localhost:3000/user/sign/check-id", this.user.userId)
                    this.idWarning = false;
                } catch (err) {
                    this.commentIdwarning = "사용 중인 아이디입니다.";
                    this.idWarning = true;
                }
            }
        },
        async handleNameWarning() {
            if (!this.user.userName) {
                this.commontNameWarning = "닉네임을 입력해주세요.";
            } else {
                try {
                    await axios.post("http://localhost:3000/user/sign/check-name", this.user.userName)
                    this.nameWarning = false;
                } catch (err) {
                    this.commontNameWarning = "사용 중인 닉네임입니다.";
                    this.nameWarning = true;
                }
            }
        },
        handlePwdLength() {
            this.checkLengthWarning = this.user.userPassword.length < 4;
        },
        handlePwdMatch() {
            this.passwordVerifyWarning = this.user.userPassword !== this.user.userVerifyPassword;
        }
    }
}
</script>
<style>
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
        width:95%;
    }
    .join-warning-text{
        color: red;
        font-size: 12pt;
        margin-top: 5px;
    }
    .account-disable-btn{
        background-color: #cccccc;
        color: #888888;
        border: 1px solid #cccccc;
        cursor: not-allowed;
    }
    .account-next-btn{
        display: block;
        background: rgb(94,111,75);
        color:white;
        border:none;
        border-radius: 15px;
        padding:12px;
        width:93%;
        margin-top:10px;
        font-size:18pt;
        font-weight: bold;
        cursor:pointer;
    }
</style>