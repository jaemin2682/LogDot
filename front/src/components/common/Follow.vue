<template>
  <div class="profile">
    <div class="left">
      <button class="btn-profile" @click="$router.push(`/${data.email}`)">
        <img :src="data.profile" alt />
      </button>
    </div>
    <div class="right">
      <div class="name">{{ data.nickname }}</div>
      <button class="blog-name" v-if="data.blogname" @click="$router.push(`/${data.email}`)">{{ data.blogname }}</button>
      <div class="blog-name blog-name-empty" v-if="!data.blogname">
        이름 없음
      </div>
      <div class="footer">
        <button class="btn-alarm" v-if="isAlarm" @click="offNotification">
          <img src="@/assets/notification_icon.svg" alt="알림 ON" />
        </button>
         <button class="btn-alarm" v-if="!isAlarm" @click="onNotification">
          <img src="@/assets/unnotification_icon.svg" alt="알림 OFF" />
        </button>
        <button class="btn-follow" v-if="isFollowing" @click="unfollow">
          팔로우 취소
        </button>
        <button class="btn-follow" v-if="!isFollowing" @click="follow">
          팔로우
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  props: {
    data: {
      type: Object
    }
  },
  data() {
    return {
      isAlarm : true,
      isFollowing: true
    };
  },
  methods: {
    ...mapGetters({
      getEmail: "user/getEmail"
    }),
    unfollow() {
      this.isFollowing = !this.isFollowing;
      axios.delete(
        `${SERVER_URL}/api/follow/del?followed=${
          this.data.email
        }&follower=${this.getEmail()}`
      );
    },
    follow() {
      this.isFollowing = !this.isFollowing;
      axios.post(
        `${SERVER_URL}/api/follow/add?followed=${
          this.data.email
        }&follower=${this.getEmail()}`
      );
    },
    onNotification(){
      this.isAlarm =!this.isAlarm;
      axios.delete(`${SERVER_URL}/api/notification/on?actionuser=${this.data.email}&targetuser=${this.getEmail()}`,
      { withCredentials: true });
    },
    offNotification(){
      this.isAlarm =!this.isAlarm;
      axios.get(`${SERVER_URL}/api/notification/off?actionuser=${this.data.email}&targetuser=${this.getEmail()}`,
      { withCredentials: true });
    }
  },
  created(){
    this.isAlarm = this.data.notification;
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/common/Profile.scss";

</style>
