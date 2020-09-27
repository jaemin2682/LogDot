<template>
  <div class="container-module-comment">
    <span>댓글 {{ commentCnt }}개</span>
    <textarea
      class="input-comment"
      v-model="commentContent"
      maxlength="100"
      placeholder="댓글 입력하기"
    >
    </textarea>
    <div class="flex-end">
      <button @click="submitComment" :disabled="commentContent === ''">
        댓글 작성
      </button>
    </div>
    <CommentList :commentList="commentList" />
  </div>
</template>

<script>
import axios from "axios";
import CommentList from "./CommentList.vue";
import { mapGetters } from "vuex";

export default {
  props: ["postId"],
  data: function() {
    return {
      commentCnt: 0,
      commentContent: "",
      commentList: "",
    };
  },
  components: {
    CommentList,
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
    }),
    submitComment() {
      axios
        .post(`${this.$apiServer}/comment`, {
          content: this.commentContent,
          email: this.getUserInfo().email,
          postid: this.postId,
        })
        .then(() => {
          // alert("댓글이 등록됐습니다.");
          this.$emit("reRender");
        })
        .catch((error) => {
          console.log(error);
          // alert("로그인이 필요한 서비스입니다.");
          document.querySelector(".container-login").classList.remove("hide");
        });
    },
  },
  async created() {
    try {
      const res = await axios.get(
        `${this.$apiServer}/comment/allComment?email=${
          this.getUserInfo().email
        }&postid=${this.postId}`
      );
      this.commentCnt = res.data.length;
      this.commentList = res.data;
    } catch (error) {
      console.log(error);
    }
  },
};
</script>

<style lang="scss" scoped>
.container-module-comment {
  margin-top: 40px;
}
.input-comment {
  margin: 20px 0px;
  margin-bottom: 5px;
  width: 100%;
  height: 8rem;
  resize: none;
  border-color: rgb(211, 211, 211);
  border-radius: 5px;
  padding: 0.7rem;
  &::placeholder {
    color: rgb(197, 197, 197);
  }
}
.flex-end {
  display: flex;
  justify-content: flex-end;
  width: 100%;
  margin-bottom: 40px;
  button {
    color: dodgerblue;
    font-size: 0.8rem;
    margin-right: 8px;
    &:disabled {
      color: rgb(216, 216, 216);
    }
  }
}
</style>
