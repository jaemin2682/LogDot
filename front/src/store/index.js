import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import userStore from "@/store/modules/userStore";
import navbarMini from "@/store/modules/navbar-mini";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    user: userStore,
    navbarMini,
  },

  // plugins: [
  //   createPersistedState({
  //     paths: ["user"],
  //   }),
  // ],

  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      paths: ["user"],
    }),
  ],
});
