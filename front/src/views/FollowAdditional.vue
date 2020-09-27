<template>
  <div class="container-base">
    <span class="title">내가 팔로우 하는 사람들</span>
    <FlexFollow :datas="data" />
  </div>
</template>

<script>
import FlexFollow from "@/components/common/FlexFollow.vue";
import axios from "axios";
import { mapGetters } from "vuex";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  components: {
    FlexFollow
  },
  data: function() {
    return {
      data: []
    };
  },
  created() {
    this.fetchFollowerData();
  },
  methods: {
    ...mapGetters({
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
    async fetchFollowerData() {
      await axios
        .get(`${SERVER_URL}/api/follow/list?email=${this.getEmail()}`)
        .then(res => {
          this.data = res.data;
          console.log(this.data);
        })
        .catch(err => console.log(err));
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";
@import "@/assets/common/Base.scss";
</style>
