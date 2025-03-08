<template>
  <div>
    <div class="board-area-wrapper">
      <ContentTop/>
        <router-link
          class="board-wrapper"
          v-for="(board, index) in boards"
          :key="index"
          :to="`/infinity/detail/${board.idx}`"
          :selectedBoard="board"
        >
          <hr class="board-liner"/>
          <div class="board-frame" @click="viewBoardDetail(board)">
            <span style="display: none;">{{ board.boardId }}</span>
            <div class="board-title-frame">
              <span id="title">{{ board.boardTitle }}</span>
            </div>
            <div class="board-content-frame">
              <div id="screenshot" v-if="board.boardImg">{{ board.boardImg }}</div>
              <div id="content" v-if="!board.boardImg">{{ removeHtmlTags(board.boardContent) }}</div>
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

let boards = ref([
  { idx: 0, boardTitle: "첫번째 게시글", boardContent: "첫번째 게시글 내용", moddate: "2020-05-14 04:13:55", regdate: "2020-07-22 18:22:02", userId: "" },
  { idx: 1, boardTitle: "두번째 게시글", boardContent: "두번째 게시글 내용", moddate: "", regdate: "", userId: "" },
  { idx: 2, boardTitle: "세번째 게시글", boardContent: "세번째 게시글 내용", moddate: "2021-03-12 10:45:20", regdate: "2021-03-13 11:05:22", userId: "" },
  { idx: 3, boardTitle: "네번째 게시글", boardContent: "네번째 게시글 내용", moddate: "2021-04-05 09:30:00", regdate: "2021-04-06 15:45:33", userId: "" },
  { idx: 4, boardTitle: "다섯번째 게시글", boardContent: "다섯번째 게시글 내용", moddate: "2021-06-20 13:55:44", regdate: "2021-06-21 16:11:11", userId: "" },
  { idx: 5, boardTitle: "여섯번째 게시글", boardContent: "여섯번째 게시글 내용", moddate: "2021-08-01 08:22:30", regdate: "2021-08-02 10:25:15", userId: "" },
  { idx: 6, boardTitle: "일곱번째 게시글", boardContent: "일곱번째 게시글 내용", moddate: "2021-09-10 14:10:10", regdate: "2021-09-11 17:05:45", userId: "" },
  { idx: 7, boardTitle: "여덟번째 게시글", boardContent: "여덟번째 게시글 내용", moddate: "2021-10-05 16:33:40", regdate: "2021-10-06 19:22:11", userId: "" },
  { idx: 8, boardTitle: "아홉번째 게시글", boardContent: "아홉번째 게시글 내용", moddate: "2021-11-15 11:12:00", regdate: "2021-11-16 12:00:22", userId: "" },
  { idx: 9, boardTitle: "열번째 게시글", boardContent: "열번째 게시글 내용", moddate: "2021-12-01 17:55:22", regdate: "2021-12-02 18:00:33", userId: "" }
]);

function viewBoardDetail(boards) {
  boardStore.updateInfinityBoard(boards);
}

onMounted(async () => {
  try {
    if (testStore.testStatus) {throw("테스트 진행 중으로 서버 연결이 차단되었습니다.")}
    const response = await axios.post("http://localhost:3000/board/");
    boards.value = response.data;
  } catch (error) {
    console.error("에러 발생:", error);
  } 
});

function removeHtmlTags(boardContent) {
  const doc = new DOMParser().parseFromString(boardContent,"text/html")
  return doc.body.textContent || doc.body.innerHTML;
}


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
