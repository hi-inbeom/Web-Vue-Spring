<template>
  <div>
    <div class="board-area-wrapper">
      <ContentTop/>
      <router-link
        class="board-wrapper"
        v-for="(boarddto, index) in boarddtos"
        :key="index"
        :to="`/infinity/detail/${boarddto.idx}`"
        >
        <hr class="board-liner"/>
        <div class="board-frame" @click="viewBoardDetail(boarddto)">
          <span style="display: none;">{{ boarddto.boardId }}</span>
          <div class="board-title-frame">
            <span id="title">{{ boarddto.boardTitle }}</span>
          </div>
          <div class="board-content-frame">
            <div id="screenshot" v-if="boarddto.boardImg">{{ boarddto.boardImg }}</div>
            <div id="content" v-if="!boarddto.boardImg">{{ boarddto.boardContent }}</div>
          </div>
          <div class="board-bottom-frame">
            <div>좋아요</div>
            <div>댓글</div>
          </div>
        </div>
      </router-link>
      <span>등록된 게시글이 더 이상 없습니다.</span>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useBoardStore } from '@/store/useBoardStore';
import { useTestStore } from '@/store/useTestStore';
import ContentTop from '@/components/content/board/ContentTop.vue';
import axios from 'axios'

const boardStore = useBoardStore();
const testStore = useTestStore();

let boarddtos = ref([
  {idx:0, boardTitle:"", boardContent:"", moddate:"", regdate:"", userId:"" }
]
);

function viewBoardDetail(boarddto) {
  boardStore.updateInfinityBoard(boarddto);
}

onMounted(async () => {
  try {
    if (testStore.testStatus) {throw("테스트 진행 중으로 서버 연결이 차단되었습니다.")}
    const response = await axios.post("http://localhost:3000/board/");
    boarddtos.value = response.data;
    console.log(boarddtos.value[0]);
  } catch (error) {
    console.error("에러 발생:", error);
  } 
});


</script>

<style scoped>
/* 게시글 최상위 영역 */
.board-wrapper {
  text-align: left;
  text-decoration: none;
  color: black;
}

.board-frame {
  cursor:pointer;
  padding: 0px 6px 6px 6px;
}

.board-frame:hover {
  border-radius: 5px;
  background-color: rgba(80, 145, 109, 0.192);
}

/* 게시글 내에 여백 부여 */
.board-title-frame #title {
  font-weight: 700;
  font-size: 18px;
}

.board-content-frame {
  max-height: 200px;
  margin: 5px 0px 5px 0px;
}

.board-content-frame #content {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
}

.board-bottom-frame {
  display:flex;
  gap: 10px;
}

.board-bottom-frame div {
  border-radius: 5px;
  padding: 3px 6px 3px 6px;
  background-color: rgba(128, 128, 128, 0.418);
}

.board-liner {
  margin: 8px 0px 8px 0px;
}
</style>
