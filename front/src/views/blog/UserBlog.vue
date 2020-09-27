<template>
  <div class="container-blog">
    <header ref="blogBanner"></header>
    <main>
      <TagList
        :email="userEmail"
        @select-tag="onSelectTag"
        @select-all="onSelectAll"
        @click-article="clickArticle"
      />
      <div class="content">
        <div class="content-header">
          <div class="container-profile">
            <div class="blog-profile-image"></div>
            <div class="profile">
              <div class="name">
                <LevelIcon :score="userInfo.score" />
                <span>{{ userInfo.nickname }}</span>
              </div>
              <p class="blog-name">{{ blogInfo.name }}</p>
              <p class="blog-ex">{{ blogInfo.description }}</p>
              <p class="blog-follower">팔로워 {{ followersCnt }}명</p>
            </div>
          </div>
          <div class="container-btn-follow" v-if="showFollowBtn">
            <button class="btn-follow" @click="follow">
              {{ follow_text[isFollowing] }}
            </button>
          </div>
        </div>
        <div class="container-tabs">
          <button class="btn btn-article" @click="clickArticle">
            게시물
          </button>
          <button class="btn btn-Info" @click="clickInfo">정보</button>
        </div>
        <div class="content-body"></div>
        <FlexArticles
          :isStatic="true"
          :datas="articleData"
          v-if="showArticle"
        />
      </div>
    </main>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

import TagList from "@/components/blog/TagList.vue";
import LevelIcon from "@/components/user/LevelIcon.vue";
import FlexArticles from "@/components/common/FlexArticles.vue";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  components: {
    TagList,
    FlexArticles,
    LevelIcon,
  },
  data() {
    return {
      userEmail: this.$route.params.email,
      userInfo: [],
      articleData: [],
      followersCnt: null,
      showArticle: true,
      showInfo: false,
      showFollowBtn: null,
      isFollowing: 0,
      follow_text: ["팔로우", "팔로우 끊기"],
      blogInfo: [],
    };
  },
  methods: {
    ...mapGetters({
      getEmail: "user/getEmail",
      getUserInfo: "user/getUserInfo",
      getIsLogin: "user/getIsLogin",
    }),
    async fetchUserInfo() {
      try {
        const res = await axios.get(
          `${SERVER_URL}/api/auth/userInfo?email=${this.userEmail}`
        );
        this.userInfo = res.data;
      } catch (error) {
        console.log(error);
      }
      var profileImages = document.querySelectorAll(".blog-profile-image");
      if (this.userInfo.profile !== null) {
        profileImages.forEach((profileImage) => {
          profileImage.style.backgroundImage = `url('${this.userInfo.profile}')`;
        });
      }
    },
    clickArticle(event) {
      this.showArticle = true;
      this.showInfo = false;
      event.target.style.borderBottom = "3px solid #727272";
      document.querySelector(".btn-Info").style.borderBottom = "none";
    },
    clickInfo(event) {
      this.showInfo = true;
      this.showArticle = false;
      event.target.style.borderBottom = "3px solid #727272";
      document.querySelector(".btn-article").style.borderBottom = "none";
      this.document;
    },
    fetchArticles() {
      axios
        .get(`${SERVER_URL}/api/post/writer/${this.userEmail}`)
        .then((res) => {
          this.articleData = res.data;
        })
        .catch((err) => console.log(err));
    },
    fetchFollowersCnt() {
      axios
        .get(`${SERVER_URL}/api/follow/cnt/${this.userEmail}`)
        .then((res) => {
          this.followersCnt = res.data;
        })
        .catch((err) => console.log(err));
    },
    fetchBlogInfo() {
      axios
        .get(`${SERVER_URL}/api/blog?email=${this.userEmail}`)
        .then((res) => {
          this.blogInfo = res.data;
          this.$refs.blogBanner.style.backgroundImage = `url("${this.blogInfo.picture}")`;
        })
        .catch((err) => console.log(err));
    },
    onSelectTag(tag) {
      axios
        .get(
          `${SERVER_URL}/api/post/mycategory/postlist?email=${this.userEmail}&hashtag=${tag}`
        )
        .then((res) => {
          this.articleData = res.data;
        })
        .catch((err) => console.log(err));
    },
    onSelectAll() {
      this.fetchArticles();
    },
    async follow() {
      if (this.getIsLogin() === false) {
        alert(`로그인하여 ${this.userInfo.nickname}님의 글을 받아보세요!`);
        return;
      }
      if (this.isFollowing === 0) {
        await axios
          .post(
            `${SERVER_URL}/api/follow/add?followed=${
              this.userEmail
            }&follower=${this.getEmail()}`
          )
          .then(() => {
            this.fetchFollowersCnt();
            this.checkFollowing();
          })
          .catch((err) => console.log(err));
      } else {
        this.unfollow();
      }
    },
    async unfollow() {
      await axios
        .delete(
          `${SERVER_URL}/api/follow/del?followed=${
            this.userEmail
          }&follower=${this.getEmail()}`
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => console.log(err));
      this.fetchFollowersCnt();
      this.checkFollowing();
    },
    checkFollowing() {
      axios
        .get(
          `${SERVER_URL}/api/follow/isfollow?followed=${
            this.userEmail
          }&follower=${this.getEmail()}`
        )
        .then((res) => {
          console.log(res);
          this.isFollowing = res.data;
        })
        .catch((err) => console.log(err));
    },
  },
  created() {
    this.fetchUserInfo();
    this.fetchArticles();
    this.fetchFollowersCnt();
    this.fetchBlogInfo();
  },
  mounted() {
    if (this.userEmail === this.getEmail()) {
      this.showFollowBtn = false;
    } else {
      this.showFollowBtn = true;
    }
    if (this.getIsLogin()) {
      this.checkFollowing();
    }
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/common/Base.scss";

.container-blog {
  position: relative;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  align-content: stretch;
  margin-left: 72px;
  header {
    height: 320px;
    background-image: url(https://images.unsplash.com/photo-1589482736976-2cfd4400ae4f?ixlib=rb-1.2.1&auto=format&fit=crop&w=2614&q=80);
    background-position: center;
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }
}

main {
  display: flex;
  width: 80%;
  align-self: center;
  .content {
    position: relative;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    .content-header {
      position: relative;
      min-width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      padding: 24px;
      .container-profile {
        display: flex;
        flex-direction: row;
      }
      .profile {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        justify-content: space-between;
        margin: 0px 24px;
        .name {
          display: flex;
          margin-bottom: 5px;
          div {
            margin-right: 8px;
            font-weight: 1000;
          }
          span {
            line-height: 24px;
            margin-right: 8px;
            font-weight: 1000;
          }
          .level {
            color: gold;
          }
        }
        p {
          margin-bottom: 5px;
        }
        .blog-name {
          font-size: 1.5rem;
          font-weight: 800;
        }
        .blog-follower {
          font-weight: 600;
        }
      }
      .btn-follow {
        color: #1a7cff;
        height: 100%;
        margin-right: 8px;
        font-weight: 700;
        font-size: 1.5em;
      }
    }
    .container-tabs {
      margin: 12px;
      .btn {
        font-size: 1em;
        padding: 10px;
        border-radius: 0px;
        margin-right: 50px;
        &:focus {
          box-shadow: none;
        }
      }
      .btn-article {
        border-bottom: 3px solid #727272;
      }
    }
    .content-body {
    }
  }
}

.blog-profile-image {
  position: relative;
  background-image: url("https://cdns.iconmonstr.com/wp-content/assets/preview/2019/240/iconmonstr-school-28.png");
  background-position: center;
  background-size: cover;
  width: 150px;
  height: 150px;
  border-radius: 50%;
}

.container-article {
  position: relative;
  width: 100%;
}
</style>
