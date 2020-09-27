<template>
  <div class="container-comment">
    <header>
      <div class="profile-img-box" @click="moveToBlog">
        <img :src="commentData.profile" alt />
      </div>
      <div class="info">
        <div class="user-info">
          <LevelIcon @click="moveToBlog" :score="commentData.score" />
          <span @click="moveToBlog">{{ commentData.nickname }}</span>
          <button
            class="btn-more"
            v-if="getUserInfo().email === commentData.email"
            @click="toggleMenu"
            ref="btnMore"
          >
            ⫶
          </button>
          <CommentMenu
            ref="commentMenu"
            :commentId="commentData.id"
            :btnMore="$refs.btnMore"
            @editStart="editStart"
          />
        </div>
        <span class="date">{{ commentData.createdate.split("T")[0] }}</span>
      </div>
    </header>
    <main ref="main"></main>
    <div class="edit-buttons" v-if="isEditing">
      <button @click="editCancel">취소</button>
      <button @click="editSubmit">확인</button>
    </div>
    <footer v-if="!isEditing">
      <button class="btn-like" @click="toggleLikeButton">
        <svg
          class="icon-like-comment"
          ref="likeIcon"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
        >
          <path
            d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z"
          />
        </svg>
      </button>
      <span class="like-count">{{ commentData.likes }}</span>
      <button class="btn-reply-write" @click="toggleReplyTextarea">
        답글
      </button>
      <div class="container-reply-toggle" v-show="isReplyTextareaShow">
        <div
          class="textarea"
          contenteditable="true"
          placeholder="답글을 입력하세요..."
          ref="textarea"
        ></div>
        <button class="btn-cancel" @click="toggleReplyTextarea">
          취소
        </button>
        <button class="btn-submit-reply" @click="submitReply">
          답글작성
        </button>
      </div>
    </footer>
    <template v-if="!isReply">
      <button
        class="btn-reply-toggle"
        v-if="commentData.replycount !== 0"
        @click="toggleReply"
      >
        <svg
          ref="replySvg"
          xmlns="http://www.w3.org/2000/svg"
          width="6"
          height="6"
          viewBox="0 0 24 24"
        >
          <path d="M24 22h-24l12-20z" />
        </svg>
        <span>답글 {{ commentData.replycount }}개 보기</span>
      </button>
      <ReplyList
        ref="replyList"
        :commentId="commentData.id"
        v-show="isReplyShow"
        :key="replyListKey"
        @reloadReply="reloadReply"
      />
    </template>
  </div>
</template>

<script>
import ReplyList from "./CommentReplyList.vue";
import CommentMenu from "./CommnetMenu";
import axios from "axios";
import { mapGetters } from "vuex";
import LevelIcon from "@/components/user/LevelIcon.vue";

export default {
  props: ["isReply", "commentData"],
  components: {
    ReplyList,
    CommentMenu,
    LevelIcon
  },
  data: function() {
    return {
      isReplyShow: false,
      isReplyTextareaShow: false,
      isReplyFirstCall: true,
      isLike: 0,
      replyContent: "",
      replyListKey: 0,
      isEditing: false,
      contentBeforeEdit: "",
      isMenuShow: false
    };
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
      getIsLogin: "user/getIsLogin"
    }),
    toggleMenu() {
      this.$refs.commentMenu.toggleMenu();
    },

    editStart() {
      const main = this.$refs.main;
      this.contentBeforeEdit = main.innerHTML;
      this.isEditing = true;
      main.setAttribute("contentEditable", "true");
      main.classList.add("editing");
      main.focus();
    },
    editSubmit() {
      const main = this.$refs.main;
      try {
        axios.put(`${this.$apiServer}/comment`, {
          content: main.innerHTML,
          id: this.commentData.id
        });
      } catch (error) {
        console.log(error);
      }
      this.isEditing = false;
      main.setAttribute("contentEditable", "false");
      main.classList.remove("editing");
    },
    editCancel() {
      const main = this.$refs.main;
      main.innerHTML = this.contentBeforeEdit;
      this.isEditing = false;
      main.setAttribute("contentEditable", "false");
      main.classList.remove("editing");
    },

    toggleReply() {
      this.isReplyShow = !this.isReplyShow;
      const svg = this.$refs.replySvg;
      if (this.isReplyShow) {
        if (this.isReplyFirstCall) {
          this.isReplyFirstCall = false;
          this.$refs.replyList.getData();
        }
        svg.classList.add("upper");
        svg.nextSibling.innerText = "답글 숨기기";
      } else {
        svg.classList.remove("upper");
        svg.nextSibling.innerText = `답글 ${this.commentData.replycount}개 보기`;
      }
    },
    toggleReplyTextarea() {
      this.isReplyTextareaShow = !this.isReplyTextareaShow;
      if (this.isReplyTextareaShow) {
        this.openReplyTextArea();
      } else {
        this.closeReplyTextArea();
      }
    },
    openReplyTextArea() {
      if (this.isReply) {
        this.$refs.textarea.innerHTML = `<a contenteditable="false" style="color: dodgerblue; margin-right:10px" href="/${this.commentData.email}">@${this.commentData.nickname} </a>`;
      }
    },
    closeReplyTextArea() {
      this.$refs.textarea.innerHTML = "";
    },
    setLikeBtn() {
      const likeIcon = this.$refs.likeIcon;
      if (this.isLike) {
        likeIcon.classList.add("fill-lightred");
      } else {
        likeIcon.classList.remove("fill-lightred");
      }
    },
    toggleLikeButton() {
      if (this.getIsLogin() === false) {
        alert("댓글이 마음에 드시나요? 로그인하여 의견을 알려주세요.");
        // document.querySelector(".container-login").classList.remove("hide");
        return;
      }
      this.isLike = !this.isLike;
      if (this.isLike) {
        axios.put(
          `${this.$apiServer}/comment/like?email=${
            this.getUserInfo().email
          }&id=${this.commentData.id}`,
          {}
        );
        this.commentData.likes++;
      } else {
        axios.put(
          `${this.$apiServer}/comment/unlike?email=${
            this.getUserInfo().email
          }&id=${this.commentData.id}`
        );
        this.commentData.likes--;
      }
      this.setLikeBtn();
    },
    async submitReply() {
      if (this.getIsLogin() === false) {
        document.querySelector(".container-login").classList.remove("hide");
        return;
      }
      try {
        let replyto = this.commentData.id;
        if (this.isReply) {
          replyto = this.commentData.replyto;
        }
        await axios.post(`${this.$apiServer}/comment`, {
          content: this.$refs.textarea.innerHTML,
          email: this.getUserInfo().email,
          postid: this.$route.query.id,
          replyto
        });
        this.reloadReply();
        if (this.isReply) {
          this.emitReload();
        }
      } catch (error) {
        console.log(error);
      }
      this.toggleReplyTextarea();
    },
    emitReload() {
      this.$emit("reload");
    },
    reloadReply() {
      this.isReplyFirstCall = true; // 리로드 해서 내용물이 없어져서 다시 불러와야함
      this.replyListKey++;
      this.$refs.textarea.innerHTML = "";
      this.commentData.replycount++;
      this.isReplyShow = false;
      if (!this.isReply) {
        const svg = this.$refs.replySvg;
        svg.classList.remove("upper");
        svg.nextSibling.innerText = `답글 ${this.commentData.replycount}개 보기`;
      }
    },
    moveToBlog() {
      this.$router.push({
        name: "Blog",
        params: { email: this.commentData.email }
      });
    }
  },
  mounted() {
    this.isLike = this.commentData.islike;
    this.setLikeBtn();
    this.$refs.main.innerHTML = this.commentData.content;
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/sass/comment.scss";
</style>
