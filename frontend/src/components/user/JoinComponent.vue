<template>
    <component :is="viewJoinComponent" :isJoin="true" @handleJoinKey="handleJoin"></component>
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
        joinKey: 0,
        joinMap: {
            0: {component: 'SendEmail'},
            1: {component: 'VerifyCode'},
            2: {component: 'UserDtoForm'}
        }
    }),
    computed: {
        viewJoinComponent() {
            return this.joinMap[this.joinKey].component;
        }
    },
    methods: {
        handleJoin(target) {
            this.joinKey = target;
        }
    }
}
</script>
<style>

</style>