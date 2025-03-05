<!-- <template>
    <div class="board-write-container">
        <div @click=goBack class="board-write-back-btn">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#FFFFFF"><path d="m313-440 224 224-57 56-320-320 320-320 57 56-224 224h487v80H313Z"/></svg>
        </div>
        <div class="board-write-main-wrapper">
            <div class="board-write-title">
                글 작성
            </div>
            <div class="board-write-content-wrapper">
                <ckeditor :editor="editor" v-model="editorData" :config="editorConfig" />
                <div>작성</div>
            </div>
        </div>
    </div>
</template>
<script setup>
import { useBoardStore } from '@/store/useBoardStore';
import router from '@/routers';
import { ClassicEditor } from 'ckeditor5';
import { Ckeditor } from '@ckeditor/ckeditor5-vue';


const boardStore = useBoardStore();

const goBack = () => {
    if (!boardStore.getFromBoard()) {
        router.push('/');
    } else {
        router.go(-1);
    }
}

</script>
<style>
.board-write-container {
    width: 100%;
    padding: 10px;
}

.board-write-main-wrapper {
    position: relative;
    top: -40px;
}

.board-write-back-btn {
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

.board-write-back-btn:hover {
    background-color: rgb(74, 87, 59);
}

.board-write-title {
    text-align: left;
    font-size: 35px;
    font-weight: bold;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.board-write-content-wrapper {
    margin-top: 5px;
    text-align: left;
    background-color: #ccffcc; /* 연한 초록 */
    gap:10px;
    display: grid;
    padding-bottom: 15px;
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
</style> -->