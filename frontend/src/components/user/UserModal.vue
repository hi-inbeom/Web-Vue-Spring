<template>
    <div class="user-modal-backdrop">
      <div class="user-modal-content">
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
                :is="viewComponent"
                @handleComponentKey="handleKey"
            ></component>
        </div>
      </div>
    </div>
</template>

<script>
import BottomComponent from './BottomComponent.vue';
import FindComponent from './FindComponent.vue';
import JoinComponent from './JoinComponent.vue';
import { mapActions } from 'vuex'

export default {
    components: {
        BottomComponent,
        FindComponent,
        JoinComponent
    },
    data: () => ({
        componentKey: 0,
        componentMap: {
            0: {component: 'BottomComponent', title: '로그인'},
            1: {component: 'FindComponent', title: '계정 찾기'},
            2: {component: 'JoinComponent', title: '회원 가입'}
        }
    }),
    computed: {
        viewComponent() {
            return this.componentMap[this.componentKey].component;
        },
        handleViewTitle() {
            return this.componentMap[this.componentKey].title;
        }
    },
    methods: {
        ...mapActions(['updateUserModalVisible']),
        closeLoginModal() {
            this.updateUserModalVisible(false)
        },
        handleKey(target) {
            this.componentKey = target;
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
    .user-modal-backdrop {
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
    .user-modal-content {
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