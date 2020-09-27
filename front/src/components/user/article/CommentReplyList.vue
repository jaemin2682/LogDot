<template>
  <div class="container-comment-reply-list">
    <template v-for="(data, idx) in replyList">
      <Comment
        @reload="reload"
        :isReply="true"
        :commentData="data"
        :key="idx"
      />
    </template>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  components: {
    Comment: () => import("./Comment.vue")
  },
  props: ["commentId"],
  data: function() {
    return {
      replyList: ""
    };
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    async getData() {
      try {
        const res = await axios.get(
          `${this.$apiServer}/comment/allReply/?email=${
            this.getUserInfo().email
          }&id=${this.commentId}&postid=${this.$route.query.id}`
        );
        this.replyList = res.data;
      } catch (error) {
        console.log(error);
      }
    },
    reload() {
      this.$emit("reloadReply");
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
.container-comment-reply-list {
  width: 100%;
  margin-bottom: 20px;
  background-color: #fafafa;
  border-radius: 5px;
}
</style>
