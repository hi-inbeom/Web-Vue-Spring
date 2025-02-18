<template>
  <div>
    <ContentTop></ContentTop>
    <div class="board-wrapper" v-for="(post, index) in posts" :key="index">
      <div class="board-frame">
        <div class="board-title-frame">
          <span id="title">{{ post.title }}</span>
        </div>
        <div class="board-content-frame">
          <div id="screenshot" v-if="post.screenshot">{{ post.screenshot }}</div>
          <div id="content" v-if="!post.screenshot">{{ post.content }}</div>
        </div>
        <div class="board-bottom-frame">
          <div>좋아요</div>
          <div>댓글</div>
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
      board: [{
        idx: 0,
        userName: '',
        title: '',
        screenshot: '',
        content: '',
        regdate: '',
        view: 0,
        like: 0,
        reply: 0
        }
      ],
      reply: [{
        userName: '',
        content: ''
      }],
      posts: [
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
        { title: 'Post 1', screenshot: 'lol', content: 'This is the content of post 1' },
        { title: 'Post 2', content: 'This is the content of post 2' },
        { title: 'Post 3', content: 'This is the content of post 3' },
      ]
    };
  }
}
</script>

<style>
/* 게시글 최상위 영역 */
.board-wrapper {
  padding-bottom: 3px;
  margin-bottom: 8px;
  text-align: left;
  border-bottom: 1px solid gray;
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
  border-bottom: 2px solid red;
  margin: 0px;
  padding: 0px;
  
}

.board-area-wrapper div {
  /* border: 1px solid #000; */
}
</style>
