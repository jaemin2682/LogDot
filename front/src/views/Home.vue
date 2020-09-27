<template>
  <div class="container-base">
    <span
      class="title"
      v-if="getUserInfo().email !== '' && followerArticleData.length !== 0"
      >팔로우한 사람들의 최신 게시물</span
    >
    <FlexArticles :datas="followerArticleData" />
    <span class="title">추천 게시물</span>
    <FlexArticles :datas="articleData" />
    <infinite-loading
      slot="append"
      @infinite="infiniteHandler"
      force-use-infinite-wrapper=".el-table__body-wrapper"
    >
    </infinite-loading>
  </div>
</template>

<script>
import FlexArticles from "@/components/common/FlexArticles.vue";
import axios from "axios";
import { mapMutations, mapGetters } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  components: {
    FlexArticles,
    InfiniteLoading
  },
  created() {
    this.fetchFollowerArticles();
  },
  mounted() {
    this.paintBtn(document.querySelector("#btn-home"));
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    ...mapMutations({
      paintBtn: "navbarMini/paintBtn"
    }),
    fetchFollowerArticles() {
      if (this.getUserInfo().email) {
        axios
          .get(
            `${SERVER_URL}/api/main/followLatestHome?email=${
              this.getUserInfo().email
            }`
          )
          .then(res => {
            this.followerArticleData = res.data;
          })
          .catch(err => console.log(err));
      }
    },
    infiniteHandler($state) {
      axios
        .get(`${SERVER_URL}/api/main/recommend?`, {
          params: {
            email: this.getUserInfo().email,
            page: this.page
          }
        })
        .then(response => {
          if (response.data.length) {
            this.articleData = this.articleData.concat(response.data);
            $state.loaded();
            this.page += 1;
            if (this.articleData.length / 10 == 0) {
              $state.complete();
            }
            this.isFirstPage = false;
          } else if (this.isFirstPage) {
            this.infiniteHandlerTrending($state);
          } else {
            $state.complete();
          }
        })
        .catch(err => console.log(err));
    },
    infiniteHandlerTrending($state) {
      axios
        .get(
          `${this.$apiServer}/main/popular?email=${
            this.getUserInfo().email
          }&page=${this.page}`
        )
        .then(response => {
          if (response.data.length) {
            this.articleData = this.articleData.concat(response.data);
            $state.loaded();
            this.page += 1;
            if (this.articleData.length / 10 == 0) {
              $state.complete();
            }
          } else {
            $state.complete();
          }
        })
        .catch(err => console.log(err));
    }
  },
  data: function() {
    return {
      articleData: [],
      followerArticleData: [],
      page: 1,
      isFirstPage: true
    };
  }
};
</script>

<style scoped lang="scss">
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";
</style>
