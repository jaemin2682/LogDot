<template>
  <div>
    <LimitedAccess v-if="!getIsLogin()" />
    <div class="container-base" v-if="getIsLogin()">
      <span class="title">내가 좋아하는 글</span>
      <FlexArticles :datas="articleData" />
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
import FlexArticles from "@/components/common/FlexArticles.vue";
import LimitedAccess from "@/components/user/LimitedAccess.vue";
import InfiniteLoading from "vue-infinite-loading";
import axios from "axios";

import { mapMutations, mapGetters } from "vuex";

export default {
  components: {
    FlexArticles,
    LimitedAccess,
    InfiniteLoading
  },
  data: function() {
    return {
      articleData: [],
      page:1
    };
  },
  methods: {
    ...mapMutations({
      paintBtn: "navbarMini/paintBtn"
    }),
    ...mapGetters({
      getIsLogin: "user/getIsLogin",
      getUserInfo: "user/getUserInfo"
    }),
    infiniteHandler($state) {
      axios
        .get(
          `${this.$apiServer}/main/goodList?email=${
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
  async created() {
    try {
      const res = await axios.get(
        `${this.$apiServer}/main/goodList?email=${this.getUserInfo().email}`
      );
      this.articleData = res.data;
    } catch (error) {
      console.log(error);
    }

    console.dir(this.articleData);
  },
  mounted() {
    this.paintBtn(document.querySelector("#btn-history"));
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";
</style>
