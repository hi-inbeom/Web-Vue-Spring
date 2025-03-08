<template>
  <div class="editor-wrapper">
    <div @click=goBack class="detail-back-btn">
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#FFFFFF"><path d="m313-440 224 224-57 56-320-320 320-320 57 56-224 224h487v80H313Z"/></svg>
    </div>
    <div class="editor-main-wrapper">
      <input
        type="text"
        v-model="board.boardTitle"
        class="board-title"
        id="board-title"
        placeholder="제목을 입력하세요"
        autocomplete="off"
      />

      <quill-editor
        ref="quillEditor"
        v-model:content="board.boardContent"
        :options="editorOptions"
        class="editor"
        content-type="html"
      />

      <div class="editor-button-wrapper">
        <div class="left-button">
          <button class="editor-button">게시글 저장</button>
        </div>
        <div class="right-button">
          <button class="editor-button" @click="submitBoard">저장</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { QuillEditor } from "@vueup/vue-quill";
import Quill from "quill";
import ImageUploader from "quill-image-uploader";
import ImageResize from "quill-image-resize-vue";
import "quill-image-uploader/dist/quill.imageUploader.min.css";
import { ImageDrop } from 'quill-image-drop-module';
import axios from "axios";

// Quill 모듈 등록
Quill.register('modules/imageDrop', ImageDrop);
Quill.register("modules/imageUploader", ImageUploader);
Quill.register("modules/imageResize", ImageResize);

const board = ref({
  boardTitle: "",
  boardContent: "",
  userId: "jaaa"
});

// 에디터 옵션 설정
const editorOptions = {
  modules: {
    toolbar: [
      [{ 'size': ['small', false, 'large', 'huge'] }],
      ["bold", "italic", "underline", 'strike', { 'color': [] }, { 'background': [] }],
      ['blockquote', 'code-block'],
      [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
      [{ 'align': [] }],
      [{ 'script': 'sub'}, { 'script': 'super' }],
      ["image"],
    ],
    imageResize: {
      displaySize: true, // 이미지 크기 표시
    },
    imageDrop: true, // 이미지 드래그 앤 드롭 기능 활성화
    // imageUploader: {
    //   upload: (file) => {
    //     return new Promise((resolve, reject) => {
    //       const formData = new FormData();
    //       formData.append("image", file);

    //       fetch("YOUR_IMAGE_UPLOAD_URL", {
    //         method: "POST",
    //         body: formData,
    //       })
    //         .then((response) => response.json())
    //         .then((result) => {
    //           resolve(result.imageUrl); // 업로드된 이미지 URL 반환
    //         })
    //         .catch((error) => {
    //           reject("Upload failed");
    //           console.error("Error:", error);
    //         });
    //     });
    //   },
    // },
  },
};

const submitBoard = async () => {
  await axios.post("http://localhost:3000/board/write/save", board.value)
}
</script>

<style scoped>
.editor-wrapper {
  margin-top: 12px;
}

.editor-main-wrapper {
  top:-30px;
  position: relative;
}
.detail-back-btn {
    width: 35px;
    height: 35px;
    background-color: rgb(94,111,75);
    font-size: 20px;
    font-weight: bold;
    border: none;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    position: relative;
    left: -40px;
    top: 5px;
}

.detail-back-btn:hover {
    background-color: rgb(74, 87, 59);
}


.board-title {
  width: 100%;
  padding: 5px 12px 5px 12px;
  font-size: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 5px;
}

::v-deep(.ql-container) {
  resize:vertical;
  overflow: auto;
}

::v-deep(.ql-editor) {
  min-height: 600px !important;
}

.editor-button-wrapper{
  margin-top:12px;
  display:flex;
  justify-content: space-between;
}
.left-button {
  left:0px;
}

.right-button {
  right:0px;

}

.editor-button {
  border-radius: 5px;
  border: none;
  font-size: large;
  background-color: rgb(94,111,75);
  color: white;
  padding:6px;
}
.editor-button:hover {
    background-color: rgb(74, 87, 59);
}

</style>