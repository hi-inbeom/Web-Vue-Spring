import { createStore } from 'vuex'

export const store = createStore({
  state: {
    isLoggedIn: false,
    verifyEmailCode: "",
    userdto: {
      userId: "",
      userPassword: "",
      userVerifyPassword: "",
      userName: "",
      userEmail: ""
    },
    activateModal: ""
  },
  getters: {
    isLoggedIn: state => state.isLoggedIn,
    verifyEmailCode: state => state.verifyEmailCode,
    userdto: state => state.userdto,
    activateModal: state => state.activateModal
  },
  mutations: {
    setIsLoggedIn(state, value) {
      state.isLoggedIn = value;
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
    setActivateModal(state, value) {
      state.activateModal = value;
    }
  },
  actions: {
    updateLoginStatus({ commit }, value) {
      commit('setIsLoggedIn', value);
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
    updateActivateModal({commit}, value) {
      commit('setActivateModal', value)
    }
  }
});

export default store;
