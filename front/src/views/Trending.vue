<template>
  <div class="container-base">
    <div class="container-hot-tags">
      <span class="title">인기 태그</span>
      <WordCloud :data="words" :fontSizeMapper="fontSizeMapper" />
    </div>
    <div>
      <span class="title">인기 게시물</span>
      <FlexAricles :datas="articleData" />
      <infinite-loading
        slot="append"
        @infinite="infiniteHandler"
        force-use-infinite-wrapper=".el-table__body-wrapper"
      >
      </infinite-loading>
    </div>
  </div>
</template>

<script>
import WordCloud from "@/components/common/WordCloud.vue";
import FlexAricles from "@/components/common/FlexArticles.vue";
import InfiniteLoading from "vue-infinite-loading";
import { mapMutations, mapGetters } from "vuex";
import axios from "axios";

export default {
  components: {
    WordCloud,
    InfiniteLoading,
    FlexAricles
  },
  data() {
    return {
      words: [],
      fontSizeMapper: word => Math.log2(word.value) * 5,
      page: 1,
      articleData: []
    };
  },

  async created() {
    await this.initTagData(15);
  },
  mounted() {
    this.paintBtn(document.querySelector("#btn-trending"));
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    ...mapMutations({
      paintBtn: "navbarMini/paintBtn"
    }),
    clickLike(e) {
      const likeIcon = e.currentTarget.querySelector("svg");
      if (likeIcon.classList.contains("selected")) {
        likeIcon.classList.remove("selected");
      } else {
        likeIcon.classList.add("selected");
      }
    },
    infiniteHandler($state) {
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
    },
    async initTagData(limit) {
      try {
        const res = await axios.get(`${this.$apiServer}/main/popularTag`);
        const datas = res.data;
        let value = 100000;

        for (let i = 0; i < limit && datas.length; i++) {
          const word = {
            text: datas[i].tagname,
            value
          };
          value /= 2;
          this.words.push(word);
        }
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";
.container-hot-tags {
  display: flex;
  flex-direction: column;
  align-items: center;
  .title {
    align-self: start;
  }
}
</style>
