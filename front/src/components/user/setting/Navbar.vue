<template>
  <aside class="container-navbar-setting">
    <header>
      <div class="profile-image"></div>
      <div class="icon-and-name">
        <LevelIcon :score="getUserInfo().score" />
        <span class="name">{{ getUserInfo().nickname }}</span>
      </div>
      <div class="email">{{ getUserInfo().email }}</div>
    </header>
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
import LevelIcon from "@/components/user/LevelIcon.vue";

import { mapActions, mapGetters } from "vuex";

export default {
  components: {
    LevelIcon,
  },
  methods: {
    ...mapActions({
      Logout: "user/logout",
    }),
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
    }),
    logout() {
      if (confirm("로그아웃 하시겠습니까?")) {
        this.Logout().then(() => {
          location.href = "/";
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/user/setting/Navbar.scss";
</style>
