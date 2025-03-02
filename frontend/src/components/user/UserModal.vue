<template>
    <div class="user-modal-backdrop">
      <div class="user-modal-content">
        <button class="close-btn" @click="close">X</button>
        <div class="content-area">
            <h1>{{ modalTitle }}</h1>
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
                :isJoin="viewIsJoin"
                @handleComponentKey="handleKey"
            ></component>
        </div>
      </div>
    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useModalStore } from "@/store/useModalStore";
import EmailVerifyProcess from './EmailVerifyProcess.vue';
import UserdtoProcess from './UserdtoProcess.vue';
import LoginProcess from "./LoginProcess.vue";

// 상태 변수
const componentKey = ref(0);
const modalStore = useModalStore();

// 컴포넌트 매핑
const componentMap = {
  0: { component: LoginProcess, title: "로그인" },
  1: { component: EmailVerifyProcess, title: "계정찾기", isJoin: false },
  2: { component: EmailVerifyProcess, title: "회원가입", isJoin: true },
  3: { component: UserdtoProcess, title: "계정찾기", isJoin: false  },
  4: { component: UserdtoProcess, title: "회원가입", isJoin: true }
};

// 현재 보여줄 컴포넌트, 제목, 회원가입 여부 파악
const viewComponent = computed(() => componentMap[componentKey.value].component);
const modalTitle = computed(() => componentMap[componentKey.value].title);
const viewIsJoin = computed(() => componentMap[componentKey.value].isJoin);

// 키 변경 함수
const handleKey = (target) => {
  componentKey.value = target;
};

// 닫기 함수
const close = () => {
  modalStore.close();
};
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