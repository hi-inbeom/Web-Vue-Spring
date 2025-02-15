<template>
    <div class="modal-backdrop">
      <div class="modal-content">
        <button class="close-btn" @click="closeLoginModal">X</button>
        <div class="content-area">
            <h1>{{ handleViewTitle }}</h1>
            <p>
                절차를 진행함에 있어
                <a href="/doc/UserAgreement.html" 
                    target="_blank">이용약관</a>
                및
                <a href="/doc/PrivacyPolicy.html" 
                    target="_blank">개인정보 수집 및 이용</a>
                을 이해하고 동의하였음을 인정합니다.
            </p>
            <component
                :is="handleViewComponent"
                @switchRouter="handleSwitchRouter"
            ></component>
        </div>
      </div>
    </div>
</template>

<script>
import Login from './Login.vue';
import FindAccount from './FindAccount.vue';
import JoinProcess from './JoinProcess.vue';
import { mapGetters, mapActions } from 'vuex'

export default {
    name: 'LoginModal',
    components: {
        Login,
        FindAccount,
        JoinProcess
    },
    data: () => ({
        switchRouter: 0,
        viewComponentMap: {
            0: {component: 'Login', title: '로그인'},
            1: {component: 'FindAccount', title: '계정 찾기'},
            2: {component: 'JoinProcess', title: '회원 가입'}
        }
    }),
    computed: {
        ...mapGetters(['isModalVisible']),
        handleViewComponent() {
            return this.viewComponentMap[this.switchRouter].component;
        },
        handleViewTitle() {
            return this.viewComponentMap[this.switchRouter].title;
        }
    },
    methods: {
        ...mapActions(['updateModalVisibility']),
        closeLoginModal() {
            this.updateModalVisibility(false)
        },
        handleSwitchRouter(target) {
            this.switchRouter = target;
        }
    }
}
</script>

<style>
    .content-area h1 {
        text-align: left;
        margin-bottom: 5px;
    }
    .content-area p {
        text-align: left;
        margin-top: 0px;
    }
    .modal-backdrop {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        background-color: rgba(0, 0, 0, 0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 1000;
    }
    .modal-content {
        background: white;
        padding: 30px;
        border-radius: 8px;
        max-width: 600px;
    }

    .close-btn {
        float:right;
        background: none;
        border: none;
        font-size:25px;
        cursor:pointer;
    }
    .content-area {
        margin-left:35px;
        width:90%;
    }
</style>