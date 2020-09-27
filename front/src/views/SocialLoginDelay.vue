!<template>
  <div></div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      email: this.$route.params.email,
    };
  },
  methods: {
    ...mapActions({
      socialLogin: "user/socialLogin",
      fetchUserInfo: "user/fetchUserInfo",
    }),
    async login() {
      await this.socialLogin(this.email);
      await this.fetchUserInfo(this.email);
      this.$router.push({ name: "Main" });
    },
  },
  created() {
    this.login();
  },
};
</script>

<style></style>
