<template>
    <div class="join-input-box">
        <input v-if="this.isJoin" id="userId"
                type="text"
                name="userId"
                v-model="user.userId"
                placeholder="아이디"
                autocomplete="off"
                @blur="handleIdWarning">
        <input v-if="!this.isJoin" id="userId"
                type="text"
                name="userId"
                v-model="user.userId"
                :disabled="true">
        <label for="email">아이디</label>
        <span v-if="idWarning" class="join-warning-text"> {{commentIdwarning}} </span>
    </div>
    <div class="join-input-box">
        <input v-if="this.isJoin" id="userName"
                type="text"
                name="userName"
                v-model="user.userName"
                placeholder="닉네임"
                autocomplete="off"
                @blur="handleNameWarning">
        <input v-if="!this.isJoin" id="userName"
                type="text"
                name="userName"
                v-model="user.userName"
                :disabled="true">
        <label for="userName">닉네임</label>
        <span v-if="nameWarning" class="join-warning-text"> {{commontNameWarning}} </span>
    </div>
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
    <div class="account-next-btn"
        @click="handleLastProcess"> {{ BtnText }} </div>
</template>
<script>
import axios from 'axios';
import { mapActions, mapGetters } from 'vuex';

export default {
    props: {
        isJoin: Boolean
    },
    data: () => ({
        idWarning: false,
        commentIdwarning: "",
        nameWarning: false,
        commontNameWarning: "",
        checkLengthWarning: false,
        passwordVerifyWarning: false,
        user: {
            userId:"",
            userName:"",
            userPassword:"",
            userVerifyPassword:"",
            userEmail:""
        }
    }),
    computed: {
        ...mapGetters(['userdto']),
        BtnText() {
            return this.isJoin ? '회원가입' : '계정 정보 수정';
        }
    },
    mounted() {
        // userdto에서 userEmail 값을 받아와서 user의 userEmail을 초기화
        this.user.userEmail = this.userdto.userEmail;
        if (this.userdto.userId) {
            this.user = this.userdto;
            this.user.userPassword = "";
        }
    },
    methods: {
        
        ...mapActions(['updateUserModalvisible']),
        async handleLastProcess() {
            if (this.isJoin) {
                try {
                    await axios.post("http://localhost:3000/user/sign/signup", this.user);
                    this.updateUserModalvisible(false);
                } catch (err) {
                    console.log('Error :',err.message);
                }
            } else {
                try {
                    await axios.patch("http://localhost:3000/user/update", this.user);
                    this.updateUserModalvisible(false);
                } catch (err) {
                    console.log('Error :',err.message);
                }
            }
        },
        async handleIdWarning() {
            if (this.user.userId.length < 4) {
                this.commentIdwarning = "아이디는 4글자 이상이어야 합니다.";
                this.idWarning = true;
            } else {
                try {
                    await axios.post(
                        "http://localhost:3000/user/sign/check-id",
                        this.user.userId,
                        {headers: {'Content-Type': 'text/plain'}}
                    )
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
                    await axios.post(
                        "http://localhost:3000/user/sign/check-name",
                        this.user.userName,
                        {headers: {'Content-Type': 'text/plain'}} // text/plain미설정 시  값 뒤에 = 혹은 ""가 발생
                    )
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