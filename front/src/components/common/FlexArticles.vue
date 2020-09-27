<template>
  <div class="container-article">
    <template v-for="(data, idx) in datas">
      <Article :isStatic="isStatic" :key="idx" v-bind:data="data" />
    </template>
  </div>
</template>

<script>
import Article from "@/components/common/Article.vue";

export default {
  components: { Article },
  props: {
    datas: {
      type: Array
    },
    isStatic: {
      type: Boolean,
      default: false
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/common/FlexArticles.scss";
</style>
