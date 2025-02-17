import { createRouter, createWebHistory } from 'vue-router';
import Infinity from '@/components/content/board/Infinity.vue';
import MainContent from '@/components/content/MainContent.vue';

const routes = [
  {
    path: '/',
    name: 'MainContent',
    component: MainContent
  },
  {
    path: '/infinity',
    name: 'Infinity',
    component: Infinity
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
