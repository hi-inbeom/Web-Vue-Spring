import { createStore } from 'vuex'

export const store = createStore({
  state: {
    isLoggedIn: false,
    isModalVisible: false
  },
  getters: {
    isLoggedIn: state => state.isLoggedIn,
    isModalVisible: state => state.isModalVisible
  },
  mutations: {
    setIsLoggedIn(state, value) {
      state.isLoggedIn = value;
    },
    setModalVisible(state, value) {
      state.isModalVisible = value;
    }
  },
  actions: {
    updateLoginStatus({ commit }, value) {
      commit('setIsLoggedIn', value);
    },
    updateModalVisibility({ commit }, value) {
      commit('setModalVisible', value);
    }
  }
});

export default store;
