import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useBoardStore = defineStore('board', () => {
    const selectedBoard = ref({
        boardId : "",
        boardTitle : "",
        boardImg : "",
        boardContent : "",
        boardPagenation : "",
        fromBoard : ""
    })

    function updateInfinityBoard(value) {
        selectedBoard.value["boardId"] = value.boardId
        selectedBoard.value["boardTitle"] = value.boardTitle
        selectedBoard.value["boardImg"] = value.boardImg
        selectedBoard.value["boardContent"] = value.boardContent
        selectedBoard.value["boardPagenation"] = value.boardPagenation
        selectedBoard.value["fromBoard"] = "infinity"
    }
    
    function updateInfinityBoardField(field, value) {
        selectedBoard.value[field] = value
        selectedBoard.value["fromBoard"] = "infinity"
    }

    function getInfinityBoardField(field) {
        return selectedBoard.value[field]
    }

    function getSelectedBoard() {
        return selectedBoard.value
    }

    function getFromBoard() {
        return selectedBoard.value.fromBoard
    }

    return { selectedBoard, updateInfinityBoard, updateInfinityBoardField, getInfinityBoardField, getSelectedBoard, getFromBoard }
})