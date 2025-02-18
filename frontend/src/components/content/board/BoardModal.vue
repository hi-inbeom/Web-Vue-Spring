<template>
    <div class="modal-backdrop">
      <div class="modal-frame">
        <div class="modal-title-area">
            <h1>{{ modalTitle }}</h1>
            <button class="close-btn" @click="closeBoardModal">X</button>
        </div>
        <div class="modal-content-area">
            <div class="board-title-wrapper">
                <input
                    id="board-title-id"
                    type="text"
                    placeholder="제목"
                    autocomplete="off"
                    v-model="board.title">
                <label for="board-title-id">제목</label>
            </div>
            <div class="board-content-wrapper">
                <quill-editor
                class="board-editor"
                ref="myTextEditor"
                :disabled="false"
                :value="board.content"
                :options="editorOption"
                />
            </div>
        </div>
        <div class="modal-btn-wrapper">
            <div>작성</div>
        </div>
      </div>
    </div>
</template>
<script>
import {mapActions} from 'vuex';
import { quillEditor } from 'vue3-quill'

export default {
    components: {quillEditor},
    props: {
        modalTitle: String
    },
    methods: {
        ...mapActions(['updateBoardModalVisible']),
        closeBoardModal() {
            this.updateBoardModalVisible(false);
        }
    },
    data: () => ({
        board: {
            title: "",
            content: ""
        },
        editorOption: {
            placeholder: "내용",
            modules: {
            toolbar: [
                ["bold", "italic", "underline", "strike","blockquote", "code-block",{ color: [] }, { background: [] },{ align: [] },"link", "image", "video"]
            ]
            },
        },
    })
}

</script>
<style>
    .modal-backdrop {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        background-color: rgba(0, 0, 0, 0.5) !important;
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 1000;
    }

    .modal-frame {
        background: white;
        padding: 30px 30px 20px 30px;
        border-radius: 8px;
        min-width: 700px;
        min-height: 500px;

        @media (max-width: 700px) {
            height: 100%;
            width: 100%;
        }
    }

    .modal-title-area {
        display: flex;
        justify-content: space-between;
    }

    .modal-title-area h1 {
        margin-top: 0px;
        margin-bottom: 15px;
    }

    .modal-title-area .close-btn {
        float:right;
        background: none;
        border: none;
        font-size:25px;
        cursor:pointer;
    }

    .modal-content-area {
        width:100%;
    }

    .board-title-wrapper {
        position:relative;
        align-items: center;
        width: 100%;
        margin-bottom: 15px;
    }

    .board-title-wrapper input {
        display:flex; 
        border: 1px solid black;
        border-radius: 15px;
        font-size:15pt;
        width:100%;
        padding:16px 3px 8px 12px;
    }

    /* Editor CSS */
    .board-content-wrapper {
        border-radius: 15px;
        border: 1px solid black;
    }

    .ql-toolbar {
        display:flex;
        justify-content: left;
    }
    .ql-toolbar.ql-snow {
        border: none;
    }
    .ql-container {
        height: 500px;
    }
    .ql-container.ql-snow {
        border: none;
    }
    .ql-editor {
        max-height: 700px;
        overflow-y: auto;
        border: none;
        font-size:15px;
        font-weight: 500;
    }

    /* Modal 버튼 */
    .modal-btn-wrapper {
        display:flex;
        justify-content: right;
        margin-top: 10px;
    }
    .modal-btn-wrapper div {
        border: 1px solid black;
        font-weight: 800;
        cursor:pointer;
        border-radius: 15px;
        padding: 5px 13px 5px 13px;

        @media (max-width: 700px) {
            height: 100%;
            width: 100%;
            font-size: 20px;
            letter-spacing: 15px;
        }
    }
</style>