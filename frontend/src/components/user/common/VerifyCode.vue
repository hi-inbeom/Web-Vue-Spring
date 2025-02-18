<template>
    <div class="join-input-box">
        <input id="email-code"
                type="text"
                placeholder="인증번호"
                v-model="inputCode"
                autocomplete="off">
        <label for="email-code">인증번호</label>
    </div>
    <span class="join-warning-text">{{ emailCode }}</span> <!-- 임시 테스트용 코드 -->
    <span v-if="viewWarning" class="join-warning-text">인증번호가 다릅니다.</span>
    <div class="account-next-btn" @click="handleProcess">{{ BtnText }}</div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import axios from 'axios';

export default {
    data: () => ({
        viewWarning: false,
        emailCode: "", // 임시 테스트용 코드
        inputCode: ""
    }),
    props: {
        isJoin: Boolean
    },
    computed: {
        ...mapGetters(['verifyEmailCode']),
        BtnText() {
            return this.isJoin ? '계정생성' : '계정찾기';
        }
    },
    emits: ['handleJoinKey', 'handleFindKey'],
    methods: {
        ...mapActions(['updateUserDto']),
        async handleProcess() {
            const req = await axios.post('http://localhost:3000/user/sign/verify-code', this.inputCode);
            if (req) {
                if (!this.isJoin) {
                    this.updateUserDto(req.data);
                }
                this.viewWarning = false;
                this.$emit(this.isJoin ? 'handleJoinKey' : 'handleFindKey', 2);
            } else {
                this.viewWarning = true;
            }
        },
    },
    mounted() {
        this.emailCode = this.verifyEmailCode; // 임시 테스트용 코드
    }
}
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