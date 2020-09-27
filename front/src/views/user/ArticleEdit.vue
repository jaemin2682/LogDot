<template>
  <div class="container-article-edit">
    <editor ref="editor"></editor>
  </div>
</template>

<script>
import Editor from "@/components/user/article/Editor.vue";
export default {
  components: {
    Editor
  },
  methods: {
    done() {
      this.$refs.editor.openSummaryModal();
    }
  },
  mounted() {
    const header = document.querySelector(".header-container");
    header.setAttribute("style", "display: none;");
  },
  beforeDestroy() {
    const header = document.querySelector(".header-container");
    header.setAttribute("style", "display: flex;");
  }
};
</script>

<style lang="scss" scoped>
.container-article-edit {
  min-width: 100vw;
  height: 100%;
  padding-top: 0px;
  background-color: #f1f4f7;
}
input {
  padding: 10px;
}
.front {
  margin-left: 10px;
}
.btn {
  display: inline-flex;
  align-items: center;
  background-color: rgba(0, 0, 0, 0);
  height: 30px;
  border: 2px solid rgb(194, 194, 194);
  color: rgb(167, 165, 165);
  font-size: 0.8em;
  padding: 0px 20px;
  border-radius: 25px;
  margin-right: 20px;
}
</style>
