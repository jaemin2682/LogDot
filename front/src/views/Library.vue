<template>
  <div>
    <LimitedAccess v-if="!getIsLogin()" />
    <div class="container-base" v-if="getIsLogin()">
      <div>
        <span class="title">보관함</span>
        <FlexArticles :datas="articleData" />
        <infinite-loading
          slot="append"
          @infinite="infiniteHandler"
          force-use-infinite-wrapper=".el-table__body-wrapper"
        >
        </infinite-loading>
      </div>
    </div>
  </div>
</template>

<script>
import FlexArticles from "@/components/common/FlexArticles.vue";
import LimitedAccess from "@/components/user/LimitedAccess.vue";
import InfiniteLoading from "vue-infinite-loading";

import { mapMutations, mapGetters } from "vuex";

import axios from "axios";

export default {
  components: {
    FlexArticles,
    LimitedAccess,
    InfiniteLoading
  },
  data: function() {
    return {
      articleData: [],
      page: 1
    };
  },
  methods: {
    ...mapMutations({
      paintBtn: "navbarMini/paintBtn"
    }),
    ...mapGetters({
      getIsLogin: "user/getIsLogin",
      getEmail: "user/getEmail"
    }),
    infiniteHandler($state) {
      axios
        .get(
          `${this.$apiServer}/main/storageList?email=${this.getEmail()}&page=${
            this.page
          }`
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
  mounted() {
    this.paintBtn(document.querySelector("#btn-library"));
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";
</style>
