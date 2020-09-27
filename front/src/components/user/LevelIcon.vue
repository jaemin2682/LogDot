<template>
  <div class="level-icon">
    <template v-if="score < 200">🥚</template>
    <template v-else-if="score < 1000">🌱</template>
    <template v-else-if="score < 3000">🍃</template>
    <template v-else-if="score < 6000">🍀</template>
    <template v-else-if="score < 10000">🌼</template>
    <template v-else>🍍</template>
  </div>
</template>

<script>
export default {
  props: ["score"],
};
</script>

<style scoped lang="scss">
.level-icon {
  font-size: 20px;
  margin: 0;
}
</style>
