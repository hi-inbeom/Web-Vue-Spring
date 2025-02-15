import { createRouter, createWebHistory } from 'vue-router';
// import AppHeader from '../components/common/AppHeader.vue';

const routes = [
  {
    // path: '/',
    // component: () => import("@/views/Home.vue"),
    // children: [
    //   {
    //     path: "",
    //     name: "home-board-content",
    //     component: () => import("@/components/layout/BoardList.vue")
    //   }
    // ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
