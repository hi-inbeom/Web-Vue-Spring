<template>
    <div class="detail-container">
        <div @click=goBack class="detail-back-btn">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#FFFFFF"><path d="m313-440 224 224-57 56-320-320 320-320 57 56-224 224h487v80H313Z"/></svg>
        </div>
        <div class="detail-main-wrapper">
            <div class="detail-title">
                {{board.boardTitle}}
            </div>
            <div class="detail-content-wrapper">
                <div class="detail-content">
                    {{ board.boardContent }}
                </div>
                <div class="detail-img">
                    {{ board.boardImg || '이미지 구역' }}
                </div>
            </div>
            <div class="detail-sub-wrapper">
                <button>like</button>
            </div>
            <hr>
            <div class="detail-bottom-wrapper">
                <input class="reply-input-wrapper">
                <div class="reply-area-wrapper"></div>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from '@/routers';
import { onMounted, ref } from 'vue';
import { previousRoute } from "@/routers";
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();

let board = ref({
    idx: 0,
    boardTitle: "",
    boardContent: "",
    moddate: "",
    regdate: "",
    userId: ""
})

onMounted(async () => {
    const response = await axios.get(`http://localhost:3000/board/detail/${route.params.idx}`);
    board.value = response.data;
});

const goBack = () => {
    // 외부에서 직접 접속 시
    if (previousRoute.value === "/infinity" || previousRoute.value === "/list") {router.go(-1); return}
    if (previousRoute.value !== "/infinity" || previousRoute.value !== "/list") {
        if (window.location.pathname.indexOf("infinity") !== -1) {router.push({path: '/infinity'}); return}
        if (window.location.pathname.indexOf("list") !== -1) {router.push({path: '/list'}); return}
    }
}

</script>

<style scoped>
.detail-container {
    width: 100%;
    padding: 10px;
}

.detail-main-wrapper {
    position: relative;
    top: -40px;
}

.detail-back-btn {
    width: 35px;
    height: 35px;
    background-color: rgb(94,111,75);
    font-size: 20px;
    font-weight: bold;
    border: none;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    position: relative;
    left: -40px;
    top: 5px;
}

.detail-back-btn:hover {
    background-color: rgb(74, 87, 59);
}

.detail-title {
    font-size: 35px;
    font-weight: bold;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.detail-content-wrapper {
    margin-top: 5px;
    text-align: left;
    background-color: #ccffcc; /* 연한 초록 */
    gap:10px;
    display: grid;
    padding-bottom: 15px;
}

/* 본문 내용 */
.detail-content {
    background-color: #99ff99; /* 조금 더 진한 초록 */
    word-break:break-all;
}

/* 이미지 */
.detail-img {
    background-color: #66cc66; /* 짙은 초록 */
}

/* 하단 영역 */
.detail-bottom-wrapper {
    background-color: #ccccff; /* 연한 파랑 */
}

/* 좋아요 영역 */
.detail-sub-wrapper {
    display: flex;
    justify-content: left;
    background-color: #9999ff; /* 조금 더 진한 파랑 */
}

/* 댓글 영역 */
.detail-reply-wrapper {
    background-color: #ffcc99; /* 연한 주황 */
    padding: 10px;
}

/* 댓글 입력 */
.reply-input-wrapper {
    width: 100%;
    border-radius: 15px;
    padding-left: 10px;
    background-color: #ff9966; /* 조금 더 진한 주황 */
    margin-bottom: 10px;
}

/* 댓글 리스트 */
.reply-area-wrapper {
    background-color: #ff6633; /* 짙은 주황 */
    padding: 10px;
}
</style>
