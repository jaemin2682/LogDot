<template>
  <aside class="container-navbar-setting">
    <header></header>
    <main>
      <router-link tag="button" to="/setting/blog" class="btn-navitem"
        >블로그 설정</router-link
      >
      <router-link tag="button" to="/setting/account" class="btn-navitem"
        >계정 설정</router-link
      >
      <router-link tag="button" to="/setting/alarm" class="btn-navitem"
        >알림 설정</router-link
      >
      <button class="logout btn-navitem" @click="logout">로그아웃</button>
    </main>
  </aside>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  methods: {
    ...mapActions({
      Logout: "user/logout",
    }),
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
    }),
    logout() {
      this.Logout().then(() => {
        alert("로그아웃 되었습니다.");
        this.$router.push({ name: "Main" });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/user/setting/Navbar.scss";
</style>
