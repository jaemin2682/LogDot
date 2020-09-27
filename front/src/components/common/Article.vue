<template>
  <div ref="article" class="article">
    <button @click="readArticle" class="btn-read">
      <div ref="headerArticle" class="header-article">
        <img :src="data.picture" alt v-if="data.picture !== ''" />
        <span :title="data.title" v-if="data.picture === ''">{{
          data.title
        }}</span>
      </div>
      <div class="body-article">
        <h5 :title="data.title">{{ data.title }}</h5>
        <div class="description">{{ data.summary }}</div>
      </div>
    </button>
    <div class="footer-article">
      <img class="img-profile" :src="data.profile" v-if="data.profile" />
      <svg
        v-else
        xmlns="http://www.w3.org/2000/svg"
        xmlns:xlink="http://www.w3.org/1999/xlink"
        viewBox="0 0 26 26"
      >
        <path
          d="M13,1C6.4,1,1,6.4,1,13s5.4,12,12,12s12-5.4,12-12S19.6,1,13,1z M13,4.6c2,0,3.6,1.6,3.6,3.6S15,11.8,13,11.8
	s-3.6-1.6-3.6-3.6S11,4.6,13,4.6z M13,21.6c-3,0-5.7-1.5-7.2-3.9c0-2.4,4.8-3.7,7.2-3.7c2.4,0,7.2,1.3,7.2,3.7
	C18.7,20.1,16,21.6,13,21.6z"
        />
      </svg>
      <LevelIcon :score="data.score" />
      <div class="nickname" @click="moveToBlog()">{{ data.nickname }}</div>
      <button class="like" @click="toggleLikeBtn">
        <svg
          class="icon-like"
          :class="{ 'fill-red': isLike }"
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
        >
          <path
            d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z"
          />
        </svg>
        <span class="like-count">{{ like }}</span>
      </button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
import LevelIcon from "@/components/user/LevelIcon.vue";

export default {
  components: {
    LevelIcon,
  },
  props: {
    data: {
      type: Object,
    },
    isStatic: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      isLike: false,
      like: 0,
    };
  },
  mounted() {
    this.isLike = this.data.isgood;
    this.like = this.data.good;

    if (this.isStatic) {
      this.$refs.article.classList.add("static");
    }

    if (this.data.picture === "") {
      const thumbnail = this.$refs.headerArticle;
      const R = Math.random() * 155 + 100;
      const G = Math.random() * 155 + 100;
      const B = Math.random() * 155 + 100;
      thumbnail.setAttribute(
        "style",
        `background-color: rgb(${R}, ${G}, ${B});`
      );
    }
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
      getIsLogin: "user/getIsLogin",
    }),
    readArticle() {
      this.$router.push({
        name: "ArticleView",
        query: { id: this.data.id },
      });
    },
    moveToBlog() {
      this.$router.push({ name: "Blog", params: { email: this.data.writer } });
      window.scroll(0, 0);
    },
    toggleLikeBtn() {
      if (this.getIsLogin() === false) {
        alert("게시물이 마음에 드시나요? 로그인하여 의견을 알려주세요.");
        return;
      }
      this.isLike = !this.isLike;
      if (this.isLike) {
        axios.put(
          `${this.$apiServer}/post/good?email=${this.getUserInfo().email}&id=${
            this.data.id
          }`,
          {}
        );
        this.like++;
      } else {
        axios
          .put(
            `${this.$apiServer}/post/ungood?email=${
              this.getUserInfo().email
            }&id=${this.data.id}`
          )
          .then(() => {
            // console.log(res);
          })
          .catch((err) => console.log(err));
        this.like--;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/common/Article.scss";
</style>
