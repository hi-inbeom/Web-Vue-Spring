<template>
    <div class="top-area-wrapper">
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          {{ selectedItem }}
        </button>
        <ul class="dropdown-menu">
          <li v-for="item in items" :key="item">
            <a class="dropdown-item" href="#" @click.prevent="handleSortByOptions(item)"> {{ item }} </a>
          </li>
        </ul>
      </div>
      <div class="btn-wrapper">
        <div class="write-btn" @click="openBoardModal">작 성</div>
      </div>
    </div>
    <div class="area-liner"></div>
    <BoardModal v-if=isBoardModalVisible :modalTitle="title"></BoardModal>
</template>
<script>
import BoardModal from './BoardModal.vue';
import {mapGetters, mapActions} from 'vuex';

export default {
  components: {BoardModal},
  computed: {
    ...mapGetters(['isBoardModalVisible'])
  },
  data() {
    return {
      title: '',
      selectedItem: this.$route.query.sort || '최신순',
      items: ['최신순', '조회순', '댓글순', '좋아요순']
    };
  },
  watch: {
    '$route.query.sort'(newSort) {
      if (newSort) this.selectedItem = newSort
    }
  },
  methods: {
    ...mapActions(['updateBoardModalVisible']),
    openBoardModal() {
      this.updateBoardModalVisible(true);
      this.title = '글 쓰기';
    },
    handleSortByOptions(item) {
      this.selectedItem = item;
      this.$router.push({ query: {sort:item}})
    }
  }
}
</script>
<style>

/* 정렬 영역 지정 */
.top-area-wrapper {
  display: flex;
  justify-content: space-between;
  width: 100%;
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
}
.btn-wrapper:hover {
  border-radius: 5px;
  padding: 3px 6px 3px 6px;
  background-color: rgba(30, 30, 30, 0.418);
  cursor: pointer;
}

/* 정렬과 본 글 나누는 줄 */
.area-liner {
  width: 100%;
  border-bottom: 1px solid gray;
  top: 50%;
  margin-top: 8px;
  margin-bottom: 8px;
}

</style>