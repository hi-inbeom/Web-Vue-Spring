import { createStore} from 'vuex'

export const store = createStore({
    state: {
      isLoggedIn: false
    },
    mutations: {
      login(state) {
        state.isLoggedIn = true;
      },
      logout(state) {
        state.isLoggedIn = false;
      }
    },
    actions: {
      login({ commit }) {
        commit('login');
      },
      logout({ commit }) {
        commit('logout');
      }
    }
  });