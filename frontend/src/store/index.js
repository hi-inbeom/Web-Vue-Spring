import { createStore } from 'vuex'

export const store = createStore({
  state: {
    isLoggedIn: false,
    isUserModalVisible: false,
    verifyEmailCode: "",
    userdto: {
      userId: "",
      userPassword: "",
      userVerifyPassword: "",
      userName: "",
      userEmail: ""
    },
    isBoardModalVisible: false
  },
  getters: {
    isLoggedIn: state => state.isLoggedIn,
    isUserModalVisible: state => state.isUserModalVisible,
    verifyEmailCode: state => state.verifyEmailCode,
    userdto: state => state.userdto,
    isBoardModalVisible: state => state.isBoardModalVisible
  },
  mutations: {
    setIsLoggedIn(state, value) {
      state.isLoggedIn = value;
    },
    setUserModalVisible(state, value) {
      state.isUserModalVisible = value;
    },
    setVerifyEmailCode(state, value) {
      state.verifyEmailCode = value;
    },
    setUserDto(state, value) {
      state.userdto = value;
    },
    setUserDtoField(state, { field, value }) {
      state.userdto[field] = value;
    },
    setBoardModalVisible(state, value) {
      state.isBoardModalVisible = value;
    }
  },
  actions: {
    updateLoginStatus({ commit }, value) {
      commit('setIsLoggedIn', value);
    },
    updateUserModalVisible({ commit }, value) {
      commit('setUserModalVisible', value);
    },
    updateVerifyEmailCode({ commit }, value) {
      commit('setVerifyEmailCode', value);
    },
    updateUserDto({ commit }, value) {
      commit('setUserDto', value);
    },
    updateUserDtoField({ commit }, value) {
      commit('setUserDtoField', value);
    },
    updateBoardModalVisible({commit}, value) {
      commit('setBoardModalVisible', value)
    }
  }
});

export default store;
