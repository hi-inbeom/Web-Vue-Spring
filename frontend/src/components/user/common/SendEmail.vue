<template>
    <div class="join-input-box">
        <input id="userEmail"
                type="text"
                name="userEmail"
                placeholder="이메일"
                autocomplete="off"
                v-model="user.userEmail"
                @blur="handleWarning">
        <label for="userEmail">이메일</label>
        <span v-if="viewWarning" class="join-warning-text">{{ WarningText }}</span>
    </div>
    <div class="account-next-btn" @click="handleProcess">{{ BtnText }}</div>
</template>
<script>
import axios from 'axios';
import { mapActions } from 'vuex';

export default {
    props: {
        isJoin: Boolean
    },
    data: () => ({
        viewWarning: false,
        WarningText: "",
        user: {
            userEmail: ""
        }
    }),
    computed: {
        BtnText() {
            return this.isJoin ? 'J이메일 인증' : 'F이메일 인증';
        }
    },
    emits: ['handleJoinKey', 'handleFindKey'],
    methods: {
        ...mapActions(['updateUserDtoField','updateVerifyEmailCode']),
        async handleProcess() {
            try {
                const code = await axios.post("http://localhost:3000/user/sign/send-verify-code", this.user);
                this.viewWarning = false;
                console.log(code.data);
                this.updateVerifyEmailCode(code.data);
                this.updateUserDtoField({ field: 'userEmail', value: this.user.userEmail });
                this.$emit(this.isJoin ? 'handleJoinKey' : 'handleFindKey', 1);
            } catch (err) {
                this.WarningText = "존재하지 않는 이메일입니다.";
                this.viewWarning = true;
            }
        },
        handleWarning() {
            if (!(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.user.userEmail))) {
                this.viewWarning = true;
                this.WarningText = "올바른 이메일의 형태가 아닙니다.";
            }
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