<template>
  <div class="container-setting">
    <Navbar />
    <UserBlog />
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

import Navbar from "@/components/common/Navbar-mini.vue";
import UserBlog from "@/views/blog/UserBlog.vue";
export default {
  components: {
    Navbar,
    UserBlog
  },
  methods: {
    ...mapActions({}),
    ...mapGetters({})
  },
  watch: {
    "$route.params.email": function() {
      this.$router.go();
    }
  }
};
</script>

<style lang="scss" scoped>
.container-setting {
  min-height: 100vh;
  padding-top: 56px;
  display: flex;
  background-color: #fafafa;
}
</style>
