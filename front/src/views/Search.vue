<template>
  <div class="container-base">
    <span class="title"
      ><span class="keyword">{{
        keyword === undefined ? `#${tag}` : keyword
      }}</span
      >{{
        keyword === undefined ? "태그를 포함하는 게시글" : " 검색 결과"
      }}</span
    >
    <FlexArticles :datas="articleData" />
  </div>
</template>

<script>
import axios from "axios";
import FlexArticles from "@/components/common/FlexArticles.vue";
import { mapGetters } from "vuex";
const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  components: {
    FlexArticles
  },
  data: function() {
    return {
      articleData: [],
      keyword: this.$route.query.keyword,
      tag: this.$route.query.tag
    };
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    fetchWordResult() {
      axios
        .get(
          `${SERVER_URL}/api/post/search/${this.keyword}?email=${
            this.getUserInfo().email
          }`
        )
        .then(res => {
          this.articleData = res.data;
        })
        .catch(err => console.log(err));
    },
    fetchHashResult() {
      axios
        .get(
          `${SERVER_URL}/api/post/searchHash/${this.tag}?email=${
            this.getUserInfo().email
          }`
        )
        .then(res => {
          this.articleData = res.data;
        })
        .catch(err => console.log(err));
    }
  },
  created() {
    if (this.keyword !== undefined) {
      this.fetchWordResult();
    } else if (this.tag !== undefined) {
      this.fetchHashResult();
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";
.keyword {
  color: dodgerblue;
}
</style>
