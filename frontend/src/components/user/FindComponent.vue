<template>
    <component :is="viewFindComponent" :isJoin="false" @handleFindKey="handleFind"></component>
</template>
<script>
import SendEmail from './common/SendEmail.vue';
import VerifyCode from './common/VerifyCode.vue';
import UserDtoForm from './common/UserDtoForm.vue';

export default {
    components: {
        SendEmail,
        VerifyCode,
        UserDtoForm
    },
    data: () => ({
        findKey: 0,
        findMap: {
            0: {component: 'SendEmail'},
            1: {component: 'VerifyCode'},
            2: {component: 'UserDtoForm'}
        }
    }),
    computed: {
        viewFindComponent() {
            return this.findMap[this.findKey].component;
        }
    },
    methods: {
        handleFind(target) {
            this.findKey = target;
        }
    }
}
</script>
<style>
    
</style>