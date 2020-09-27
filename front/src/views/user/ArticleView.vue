<template>
  <div class="container-article-view">
    <viewer :articleData="articleData"></viewer>
  </div>
</template>

<script>
import Viewer from "@/components/user/article/Viewer.vue";
export default {
  components: {
    Viewer
  },
  props: {
    articleData: {
      type: Object
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";

.container-article-view {
  min-width: 100vw;
  min-height: 100vh;
  padding: 60px;
  padding-top: 180px;
  background-color: white;
}
</style>
