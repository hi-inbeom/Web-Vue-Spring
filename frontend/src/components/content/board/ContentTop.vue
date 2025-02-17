<template>
    <div class="top-area-wrapper">
      <div class="sort-btn">sort by</div>
      <div class="write-btn" @click="openBoardModal">write</div>
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
  data: () => ({
    title: ""
  }),
  methods: {
    ...mapActions(['updateBoardModalVisible']),
    openBoardModal() {
      this.updateBoardModalVisible(true);
      this.title = '글 쓰기';
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
.top-area-wrapper .write-btn {
  border-radius: 5px;
  padding: 3px 6px 3px 6px;
  background-color: rgba(128, 128, 128, 0.418);
  cursor: pointer;
}
.top-area-wrapper .write-btn:hover {
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
  margin-top: 16px;
  margin-bottom: 16px;
}

</style>