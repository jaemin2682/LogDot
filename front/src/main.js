import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueCryptojs from "vue-cryptojs";
import "@babel/polyfill";
import VueSocialSharing from "vue-social-sharing";

Vue.use(VueSocialSharing);
Vue.use(VueCryptojs);

Vue.config.productionTip = false;
Vue.prototype.$apiServer = "http://i3a604.p.ssafy.io:8081/api";

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
