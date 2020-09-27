<template>
  <div class="container-alarm">
    <div class="item">
      <span class="title">내 글 좋아요</span>
      <form action="">
        <div class="container-radio">
          <input
            id="none"
            type="radio"
            name="article-like"
            value="0"
            v-model="selected[0]"
          /><label for="none">해제</label>
        </div>
        <div class="container-radio">
          <input
            id="all"
            type="radio"
            name="article-like"
            value="1"
            v-model="selected[0]"
          /><label for="all">설정</label>
        </div>
      </form>
      <span class="example">" "님이 회원님의 게시물을 좋아합니다.</span>
    </div>
    <div class="item">
      <span class="title">새로운 댓글</span>
      <form action="">
        <div class="container-radio">
          <input
            id="none"
            type="radio"
            name="article-like"
            value="0"
            v-model="selected[1]"
          /><label for="none">해제</label>
        </div>
        <div class="container-radio">
          <input
            id="all"
            type="radio"
            name="article-like"
            value="1"
            v-model="selected[1]"
          /><label for="all">설정</label>
        </div>
      </form>
      <span class="example">" "님이 회원님의 게시물에 댓글을 남겼습니다.</span>
    </div>
    <div class="item">
      <span class="title">내 댓글 좋아요</span>
      <form action="">
        <div class="container-radio">
          <input
            id="none"
            type="radio"
            name="article-like"
            value="0"
            v-model="selected[2]"
          /><label for="none">해제</label>
        </div>
        <div class="container-radio">
          <input
            id="all"
            type="radio"
            name="article-like"
            value="1"
            v-model="selected[2]"
          /><label for="all">설정</label>
        </div>
      </form>
      <span class="example">" "님이 회원님의 댓글을 좋아합니다.</span>
    </div>
    <div class="item">
      <span class="title">팔로우 알림</span>
      <form action="">
        <div class="container-radio">
          <input
            id="none"
            type="radio"
            name="article-like"
            value="0"
            v-model="selected[4]"
          /><label for="none">해제</label>
        </div>
        <div class="container-radio">
          <input
            id="all"
            type="radio"
            name="article-like"
            value="1"
            v-model="selected[4]"
          /><label for="all">설정</label>
        </div>
      </form>
      <span class="example">" "님이 회원님을 팔로우합니다.</span>
    </div>
    <div class="item">
      <span class="title">팔로워의 새 글 알림</span>
      <form action="">
        <div class="container-radio">
          <input
            id="none"
            type="radio"
            name="article-like"
            value="0"
            v-model="selected[5]"
          /><label for="none">해제</label>
        </div>
        <div class="container-radio">
          <input
            id="all"
            type="radio"
            name="article-like"
            value="1"
            v-model="selected[5]"
          /><label for="all">설정</label>
        </div>
      </form>
      <span class="example"
        >" "님의 블로그에 새로운 게시물이 등록되었습니다.</span
      >
    </div>
    <div class="item">
      <span class="title">언급</span>
      <form action="">
        <div class="container-radio">
          <input
            id="none"
            type="radio"
            name="article-like"
            value="0"
            v-model="selected[3]"
          /><label for="none">해제</label>
        </div>
        <div class="container-radio">
          <input
            id="all"
            type="radio"
            name="article-like"
            value="1"
            v-model="selected[3]"
          /><label for="all">설정</label>
        </div>
      </form>
      <span class="example">" "님이 댓글에서 회원님을 언급했습니다.</span>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  data: function() {
    return {
      selected: []
    };
  },
  methods: {
    ...mapActions({
      fetchUserInfo: "user/fetchUserInfo"
    }),
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
      getEmail: "user/getEmail"
    }),
    setting() {
      var notification = this.getUserInfo().notification;

      this.selected[0] = notification.charAt(5);
      this.selected[1] = notification.charAt(4);
      this.selected[2] = notification.charAt(3);
      this.selected[3] = notification.charAt(2);
      this.selected[4] = notification.charAt(1);
      this.selected[5] = notification.charAt(0);
    }
  },
  created() {
    this.fetchUserInfo(this.getEmail()).then(res => console.log(res));
    this.setting();
  },
  watch: {
    selected: function() {
      var notification =
        this.selected[5] +
        this.selected[4] +
        this.selected[3] +
        this.selected[2] +
        this.selected[1] +
        this.selected[0];
      console.log(notification);

      axios
        .get(`${SERVER_URL}/api/auth/notificationSetting`, {
          params: {
            email: this.getEmail(),
            notification: notification
          }
        })
        .then(res => {
          console.log(res);
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.container-alarm {
  display: flex;
  background: #fff;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  padding: 24px;
}
.item {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  padding-bottom: 15px;
  margin-bottom: 25px;
  border-bottom: 1px solid rgb(201, 201, 201);
}
.title {
  font-size: 1.4rem;
  font-weight: 900;
  margin-bottom: 20px;
}
.example {
  color: rgb(163, 163, 163);
  font-size: 0.8rem;
}
input[type="radio"] {
  margin-right: 10px;
  margin-bottom: 8px;
}
form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
</style>
