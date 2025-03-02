import { defineStore } from "pinia";
import { ref } from "vue";

export const useTestStore = defineStore("test", () => {
    const testStatus = ref(true);

    function updateTestStatus() {
        testStatus.value = testStatus.value ? false : true;
        return testStatus.value;
    }

    return { testStatus, updateTestStatus };
})