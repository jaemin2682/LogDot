<template>
  <div class="container-setting">
    <Navbar v-if="getIsLogin()" />
    <router-view v-if="getIsLogin()"></router-view>
    <LimitedAccess v-if="!getIsLogin()" />
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

import Navbar from "@/components/user/setting/Navbar.vue";
import LimitedAccess from "@/components/user/LimitedAccess.vue";
export default {
  components: {
    Navbar,
    LimitedAccess
  },
  methods: {
    ...mapActions({
      fetchUserInfo: "user/fetchUserInfo"
    }),
    ...mapGetters({
      getEmail: "user/getEmail",
      getUserInfo: "user/getUserInfo",
      getIsLogin: "user/getIsLogin"
    })
  },
  created() {
    if (!this.getIsLogin()) {
      document.querySelector(".container-requirelogin").remove("hide");
    }
  },
  mounted() {
    this.profileURL = this.getUserInfo().profile;
    if (this.profileURL) {
      this.$el.querySelector(".profile-image").style.backgroundImage = `url('${
        this.getUserInfo().profile
      }')`;
    }
  }
};
</script>

<style lang="scss" scoped>
.container-setting {
  min-height: 100vh;
  padding-top: 56px;
  display: flex;
}
</style>
