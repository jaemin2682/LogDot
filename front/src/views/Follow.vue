<template>
  <div class="container-follow">
    <LimitedAccess v-if="!getIsLogin()" />
    <div class="container-base" v-if="getIsLogin()">
      <div class="container-title">
        <span class="title">내가 팔로우 하는 사람들</span>
        <router-link tag="a" to="/main/followadditional">더보기</router-link>
      </div>
      <FlexFollow :isWarp="false" :datas="data" />
      <span class="title">팔로우 한 사람들의 최신 게시물</span>
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
import FlexFollow from "@/components/common/FlexFollow.vue";
import FlexArticles from "@/components/common/FlexArticles.vue";
import LimitedAccess from "@/components/user/LimitedAccess.vue";
import InfiniteLoading from "vue-infinite-loading";
import { mapMutations, mapGetters } from "vuex";
import axios from "axios";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  components: {
    FlexFollow,
    FlexArticles,
    LimitedAccess,
    InfiniteLoading
  },
  data: function() {
    return {
      data: [],
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
    clickLike(e) {
      const likeIcon = e.currentTarget.querySelector("svg");
      if (likeIcon.classList.contains("selected")) {
        likeIcon.classList.remove("selected");
      } else {
        likeIcon.classList.add("selected");
      }
    },
    fetchFollowerData() {
      axios
        .get(`${SERVER_URL}/api/follow/list?email=${this.getEmail()}`)
        .then(res => {
          this.data = res.data.slice(0, 4);
          console.log(this.data);
        })
        .catch(err => console.log(err));
    },
    infiniteHandler($state) {
      axios
        .get(
          `${SERVER_URL}/api/main/followLatest?email=${this.getEmail()}&page=${
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
    this.paintBtn(document.querySelector("#btn-follow"));
  },
  created() {
    this.fetchFollowerData();
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";

.container-follow {
  overflow-x: hidden;
  .container-title {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
    a {
      color: dodgerblue;
      text-decoration: none;
      &:hover {
        opacity: 0.7;
      }
    }
  }
}
</style>
