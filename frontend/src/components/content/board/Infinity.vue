<template>
  <div>
    <ContentTop></ContentTop>
    <div class="board-area-wrapper">
      <div class="board-frame" v-for="(post, index) in posts" :key="index">
          <div class="board-top-frame">
            <span id="writer">{{ post.writer }}</span>
          </div>
          <div class="board-title-frame">
            <span id="title">{{ post.title }}</span>
          </div>
          <div class="board-content-frame">
            <div v-if="post.screenshot">{{ post.screenshot }}</div>
            <div v-if="!post.screenshot">{{ post.content }}</div>
          </div>
          <div class="board-bottom-frame">
            <div>스크랩</div>
            <div>좋아요</div>
            <div>댓글</div>
            <div>공유</div>
            <div style="margin-left:auto;">신고</div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import ContentTop from './ContentTop.vue';
export default {
  name: 'home-board-content',
  components: {
    ContentTop
  },
  data() {
    return {
      posts: [
        { writer:"b/Community01", title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { writer:"b/Community02",title: 'Post 2', content: 'This is the content of post 2' },
        { writer:"b/Community03",title: 'Post 3', content: 'This is the content of post 3' }
      ]
    };
  }
}
</script>

<style>
  .board-wrapper {
      justify-content: center;
      margin: 0 auto;
      padding-top: 25px;
      width: 700px;
}

/* 게시글 최상위 영역 */
.board-frame {
  margin-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
  text-align: left;
  border-bottom: 1px solid gray;
  cursor:pointer;
}

.board-frame:hover:hover {
  background-color: rgba(80, 145, 109, 0.192);
}

/* 게시글 내에 여백 부여 */
.board-frame div {
  margin-top: 10px;
  margin-bottom: 5px;
}

#writer:hover {
  cursor: pointer;
  color: purple;
}

#title {
  cursor:pointer;
}

.board-content-frame {
  max-height: 100px;
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
  border-bottom: 2px solid red;
  margin: 0px;
  padding: 0px;
  
}

.board-area-wrapper div {
  /* border: 1px solid #000; */
}
</style>
