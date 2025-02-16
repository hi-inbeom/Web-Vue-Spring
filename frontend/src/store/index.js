import { createStore } from 'vuex'

export const store = createStore({
  state: {
    isLoggedIn: false,
    isModalVisible: false,
    verifyEmailCode: "",
    userdto: {
      userId: "",
      userPassword: "",
      userVerifyPassword: "",
      userName: "",
      userEmail: ""
    }
  },
  getters: {
    isLoggedIn: state => state.isLoggedIn,
    isModalVisible: state => state.isModalVisible,
    verifyEmailCode: state => state.verifyEmailCode,
    userdto: state => state.userdto
  },
  mutations: {
    setIsLoggedIn(state, value) {
      state.isLoggedIn = value;
    },
    setModalVisible(state, value) {
      state.isModalVisible = value;
    },
    setVerifyEmailCode(state, value) {
      state.verifyEmailCode = value;
    },
    setUserDto(state, value) {
      state.userdto = value;
    },
    setUserDtoField(state, { field, value }) {
      state.userdto[field] = value;
    }
  },
  actions: {
    updateLoginStatus({ commit }, value) {
      commit('setIsLoggedIn', value);
    },
    updateModalVisibility({ commit }, value) {
      commit('setModalVisible', value);
    },
    updateVerifyEmailCode({ commit }, value) {
      commit('setVerifyEmailCode', value);
    },
    updateUserDto({ commit }, value) {
      commit('setUserDto', value);
    },
    updateUserDtoField({ commit }, value) {
      commit('setUserDtoField', value);
    }
  }
});

export default store;
