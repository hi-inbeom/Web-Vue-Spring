<template>
    <div>
      <div class="board-area-wrapper">
        <ContentTop/>
        <div class="container mt-4">
        <!-- 게시글 목록 -->
        <div v-for="board in paginatedBoards" :key="board.idx" class="list-group mb-3">
          <router-link
            class="list-group-item list-group-item-action"
            :to="`/infinity/detail/${board.idx}`"
            @click="viewBoardDetail(board)"
          >
            <h5 class="mb-1">{{ board.boardTitle }}</h5>
            <p class="mb-1">{{ removeHtmlTags(board.boardContent).slice(0, 100) }}...</p>
          </router-link>
        </div>

        <!-- 페이지네이션 -->
        <nav>
          <ul class="pagination justify-content-center">
            <li class="page-item" :class="{ disabled: currentPage === 1 }">
              <button class="page-link" @click="changePage(currentPage - 1)">이전</button>
            </li>
            <li v-for="page in totalPages" :key="page" class="page-item" :class="{ active: currentPage === page }">
              <button class="page-link" @click="changePage(page)">{{ page }}</button>
            </li>
            <li class="page-item" :class="{ disabled: currentPage === totalPages }">
              <button class="page-link" @click="changePage(currentPage + 1)">다음</button>
            </li>
          </ul>
        </nav>
      </div>
      </div>
    </div>
  </template>
  
<script setup>
import { onMounted, ref, computed, watch } from 'vue';
import { useBoardStore } from '@/store/useBoardStore';
import ContentTop from '@/components/content/board/ContentTop.vue';
import axios from 'axios'
import { useRoute, useRouter } from 'vue-router';
  
const boardStore = useBoardStore();
const route = useRoute();
const router = useRouter();
const loading = ref(false);
const boards = ref([]);

const currentPage = ref(Number(route.query.page) || 1);
let itemsPerPage = ref(10);
let totalPages = computed(() => Math.ceil(boards.value.length / itemsPerPage.value));

const fetchBoards = async () => {
  loading.value = true;
  try {
    const response = await axios.get('http://localhost:3000/board/', {params:route.query});
    boards.value = response.data;
  } catch (error) {
    console.error('게시글을 불러오는 중 오류 발생:', error);
  } finally {
    loading.value = false;
  }
}

onMounted (fetchBoards);
watch(() => route.query.orderby, fetchBoards);

const paginatedBoards = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return boards.value.slice(start, end);
});

const changePage = (page) => {
  if (page > 0 && page <= totalPages.value) {
    router.push({ query: { page } });
  }
};

function viewBoardDetail(board) {
  boardStore.updateInfinityBoard(board);
}

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
