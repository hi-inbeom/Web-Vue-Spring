import { createApp } from 'vue';
import App from './App.vue';
import routers from './routers';
import axios from 'axios';
import store from './store';

axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.withCredentials = true;
// 요청에 쿠키를 추가하는 것
// 세션 ID는 쿠키형태로 오기 때문에 넣어야 서버의 세션과 통신 가능

createApp(App)
    .use(routers)
    .use(store)
    .mount('#app');