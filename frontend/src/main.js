import { createApp } from 'vue';
import App from './App.vue';
import routes from './routes';
import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/json'

const app = createApp(App);
app.use(routes);
app.mount('#app');
