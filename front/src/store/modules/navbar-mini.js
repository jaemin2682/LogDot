export default {
  namespaced: true,

  state: {
    btnObj: {
      home: "",
      follow: "",
      trending: "",
      history: "",
      library: ""
    },
    btnList: []
  },

  getters: {
    getBtnObj(state) {
      return state.btnObj;
    },
    getBtnList(state) {
      return state.btnList;
    }
  },
  mutations: {
    setIsLogin(state, isLogin) {
      state.isLogin = isLogin;
    },
    paintBtn(state, target) {
      state.btnList = document.querySelectorAll(".item .btn-icon");

      state.btnList.forEach(elem => {
        elem.querySelector("svg").classList.remove("btn-selected");
        elem.querySelector("div").classList.remove("btn-selected");
      });

      target.querySelector("svg").classList.add("btn-selected");
      target.querySelector("div").classList.add("btn-selected");
    }
  }
};
