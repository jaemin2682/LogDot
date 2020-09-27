<template>
  <div id="app">
    <Header ref="header" />
    <router-view />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";

export default {
  name: "App",
  components: {
    Header
  }
};
</script>

<style lang="scss">
@import "@/assets/default.scss";
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
@import "@/assets/_variables.scss";
#app {
  font-family: "Noto Sans KR", Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  overflow-x: hidden;
  background-color: #fafafa;
}

button {
  border: none;
  background-color: rgba(0, 0, 0, 0);
  padding: 0px;
  &:hover {
    cursor: pointer;
  }
}
button:focus {
  outline: none;
}
.hide-scroll {
  overflow-y: hidden;
}
</style>
