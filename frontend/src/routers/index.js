import { createRouter, createWebHistory } from "vue-router";
import { ref } from "vue";
import InfinityBoard from "@/components/content/board/InfinityBoard.vue";
import BoardDetail from "@/components/content/board/BoardDetail.vue";
import MainContent from "@/components/content/MainContent.vue";
import BoardWrite from "@/components/content/board/BoardWrite.vue";

const routes = [
  {
    path: "/",
    name: "MainContent",
    component: MainContent,
  },
  {
    path: "/infinity",
    name: "InfinityBoard",
    component: InfinityBoard,
  },
  {
    path: "/infinity/detail/:boardId",
    name: "InfinityBoardDetail",
    component: BoardDetail,
  },
  {
    path: "/board/write",
    name: "BoardWrite",
    component: BoardWrite,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 이전 경로 저장
const previousRoute = ref(null);
router.beforeEach((to, from, next) => {
  previousRoute.value = from.fullPath || null;
  next();
});

export default router;
export {previousRoute};
