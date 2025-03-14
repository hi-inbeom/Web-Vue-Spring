<template>
  <div class="top-area-wrapper">
    <div class="dropdown">
      <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        {{ selectedItem }}
      </button>
      <ul class="dropdown-menu">
        <li v-for="item in items" :key="item">
          <a class="dropdown-item" href="#" @click.prevent="updateQuery(item)"> {{ item }} </a>
        </li>
      </ul>
    </div>
    <router-link class="btn-wrapper" :to="`/board/write`">
      <div class="write-btn">작 성</div>
    </router-link>
  </div>
</template>
<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

const items = ['최신순', '조회순', '댓글순', '좋아요순'];
const orderMapping = {
  '최신순': 'recent',
  '조회순': 'views',
  '댓글순': 'replys',
  '좋아요순': 'likes'
};

const selectedItem = ref(items.find(item => orderMapping[item] === route.query.orderby) || '최신순');

const updateQuery = (item) => {
  selectedItem.value = item;
  router.push({ query: { ...route.query, orderby: orderMapping[item] } });
};

watch(() => route.query.orderby, (newOrder) => {
  selectedItem.value = items.find(item => orderMapping[item] === newOrder) || '최신순';
});
</script>
<style>

/* 정렬 영역 지정 */
.top-area-wrapper {
  display: flex;
  justify-content: space-between;
  width: 100%;
  padding: 10px 0 10px 0;
  position: sticky;
  top: 80px;
  background-color: white;
  z-index: 1000;
}

.top-area-wrapper .sort-btn {
  border-radius: 5px;
  padding: 3px 6px 3px 6px;
  background-color: rgba(128, 128, 128, 0.418);
  cursor: pointer;
}
.top-area-wrapper .sort-btn:hover {
  border-radius: 5px;
  padding: 3px 6px 3px 6px;
  background-color: rgba(30, 30, 30, 0.418);
  cursor: pointer;
}
.btn-wrapper {
  display: flex;
  width: 70px;
  background-color: rgba(128, 128, 128, 0.418);
  border-radius: 5px;
  justify-content: center;
  align-items: center;
  padding: 3px 6px 3px 6px;
  cursor: pointer;
  text-decoration: none;
  color: black;
}
.btn-wrapper:hover {
  border-radius: 5px;
  padding: 3px 6px 3px 6px;
  background-color: rgba(30, 30, 30, 0.418);
  cursor: pointer;
}

</style>