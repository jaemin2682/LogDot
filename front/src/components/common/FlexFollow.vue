<template>
  <div class="container-follow">
    <template v-for="(data, idx) in datas">
      <Follow :key="idx" v-bind:data="data" />
    </template>
  </div>
</template>

<script>
import Follow from "@/components/common/Follow.vue";

export default {
  components: { Follow },
  props: {
    datas: {
      type: Array
    },
    isWarp: {
      type: Boolean,
      default: true
    }
  },
  mounted() {
    const container = document.querySelector(".container-follow");
    if (this.isWarp) {
      container.classList.remove("nowrap");
    } else {
      container.classList.add("nowrap");
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
body {
  overflow-x: hidden;
}
.container-follow {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
}
.nowrap {
  flex-wrap: nowrap;
}
</style>
