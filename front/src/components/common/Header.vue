<template>
  <div>
    <div class="header-container">
      <div class="header-item header-front">
        <div class="btn-navbar-toggle" v-if="$route.meta.header !== 0">
          <button @click="showNavBar">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              viewBox="0 0 26 26"
            >
              <path
                d="M1,17.8v2.4h24v-2.4H1z M1,11.8v2.4h24v-2.4H1z M1,5.8v2.4h24V5.8H1z"
              />
            </svg>
          </button>
        </div>
        <router-link to="/">
          <div class="logo">
            <svg
              id="logo-img"
              data-name="logo-img"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 46 47.18"
            >
              <path
                class="cls-1"
                d="M14.78,34.74c-1.1-1.19-.09-4.32.74-5.42a27.54,27.54,0,0,1,4.22-5,72.83,72.83,0,0,0,11.48-14,41.55,41.55,0,0,0,4-8.36c.09-.18.09-.46.37-.55C33.15,12.24,25.53,24.27,14.78,34.74Z"
                transform="translate(-2 -1.41)"
              />
              <path
                class="cls-1"
                d="M7.35,45.3l39.75-.09c1.2,0,1.2,1.19,0,1.19s-40.4-.64-40.4-.64Z"
                transform="translate(-2 -1.41)"
              />
              <path
                class="cls-1"
                d="M37.65,12.34,35.54,13.9l2-1a23.92,23.92,0,0,1-.92,3.3c-1.29,3.77-2.48,8.08-5.79,12.67-.37.46-.64.92-.92,1.29l-4.86,2.11,3.85-.83a10.75,10.75,0,0,1-3.85,3.13c-3,1.47-3.86.18-6.89,1.19C12.86,37.4,12.49,42,4.32,47.32c-1.38,1-2.48,1.47-2.3,1.19C9.46,41.63,33.24,19.68,35.54,2.42a16.83,16.83,0,0,1,2.29,8.08C37.74,11.14,37.74,11.69,37.65,12.34Z"
                transform="translate(-2 -1.41)"
              />
            </svg>
            <svg
              id="logo-text"
              data-name="logo-img"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 92 47"
            >
              <polygon
                points="15.25 46.99 0 46.99 0 0 4.28 0 4.28 43.34 15.25 43.34 15.25 46.99 15.25 46.99"
              />
              <path
                d="M45.25,45.15a3.28,3.28,0,0,1-.94,2.4,3.48,3.48,0,0,1-2.4.94H28.43a3.22,3.22,0,0,1-3.34-3.34V4.84A3.34,3.34,0,0,1,26,2.44a3.48,3.48,0,0,1,2.4-.94H41.8a3.28,3.28,0,0,1,2.4.94,2.91,2.91,0,0,1,.94,2.4l.11,40.31Zm-4.18-.31V5.16H29.27V44.84Z"
                transform="translate(-4 -1.5)"
              />
              <path
                d="M72.3,45.15a3.32,3.32,0,0,1-.94,2.4,3.49,3.49,0,0,1-2.41.94H56.42a3.1,3.1,0,0,1-3.34-3V5A3.32,3.32,0,0,1,54,2.54a3.53,3.53,0,0,1,2.4-.94H69a3.36,3.36,0,0,1,2.41.94A3.36,3.36,0,0,1,72.3,5v11H68V5.26H57.15V44.94H68V28.65H62.9V25.1h9.4Z"
                transform="translate(-4 -1.5)"
              />
              <polygon
                points="92 46.99 87.31 46.99 87.31 41.77 92 41.77 92 46.99 92 46.99"
              />
            </svg>
          </div>
        </router-link>
      </div>

      <div class="header-item header-center" v-if="$route.meta.header !== 0">
        <form class="container-main-search" @submit.prevent="search">
          <input
            type="search"
            class="input-main-search"
            v-model="searchWord"
            @keyup.enter="search"
          />
          <button type="submit" class="btn-main-search desktop">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              viewBox="0 0 26 26"
            >
              <path
                d="M18.1,16H17l-0.3-0.3c1.3-1.6,2.1-3.5,2.1-5.8c0-5-4-9-9-9S1,4.9,1,9.9s4,9,9,9c2.2,0,4.3-0.8,5.8-2.1l0.3,0.3v1.1l6.9,6.9
	L25,23L18.1,16z M10,16c-3.4,0-6.2-2.7-6.2-6.2S6.4,3.5,10,3.5s6.2,2.7,6.2,6.2S13.3,16,10,16z"
              />
            </svg>
          </button>
        </form>
      </div>

      <div class="header-item header-end" v-if="$route.meta.header !== 0">
        <button class="btn-main-search mobile">
          <div class="icon-main-search">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              viewBox="0 0 26 26"
            >
              <path
                d="M18.1,16H17l-0.3-0.3c1.3-1.6,2.1-3.5,2.1-5.8c0-5-4-9-9-9S1,4.9,1,9.9s4,9,9,9c2.2,0,4.3-0.8,5.8-2.1l0.3,0.3v1.1l6.9,6.9
	L25,23L18.1,16z M10,16c-3.4,0-6.2-2.7-6.2-6.2S6.4,3.5,10,3.5s6.2,2.7,6.2,6.2S13.3,16,10,16z"
              />
            </svg>
          </div>
        </button>
        <button class="btn-login" @click="openLogin" v-if="!getIsLogin()">
          LOG . IN
        </button>
        <router-link
          tag="button"
          class="btn-newpost"
          to="/article"
          v-if="getIsLogin()"
          >새 글쓰기</router-link
        >
        <div class="container">
          <a
            class="badge-num"
            v-if="notifications.length > 0 && getIsLogin()"
            >{{ notifications.length }}</a
          >
        </div>
        <div>
          <button
            class="notification-icon"
            @click="openNotification"
            v-if="getIsLogin()"
          >
            <svg
              version="1.1"
              id="notification_icon"
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              x="0px"
              y="0px"
              viewBox="0 0 26 26"
              style="enable-background:new 0 0 26 26;"
              xml:space="preserve"
            >
              <path
                d="M6.2,23.7c-0.2,0.1-0.4,0.1-0.6,0.1c0.3,0,0.6-0.1,0.9-0.2l5.3-2L6.2,23.7z"
              />
              <path
                d="M17.8,22.2c-0.1,1.3-0.9,2.3-2.1,2.7c-1.1,0.4-2.4,0-3.1-0.9c-0.2-0.3-0.2-0.4,0.1-0.6l4.6-1.8
	C17.6,21.4,17.8,21.6,17.8,22.2z"
              />
              <path
                d="M22.6,17.4c-2.2,0.8-4.4,1.7-6.6,2.5l-3.3,1.3l9.6-3.6c0.3-0.1,0.6-0.2,0.8-0.4C23,17.3,22.8,17.4,22.6,17.4z"
              />
              <path
                d="M22.6,14.4c-1.2-0.6-2.1-1.6-2.6-2.8c0-0.1-0.1-0.2-0.1-0.2c-0.4-1-0.8-2-1.1-3c-1-3.3-4-5.6-7.4-5.8c-0.3,0-0.6,0-1,0h-0.1
	c-0.2,0-0.3-0.1-0.5-0.3C9.5,1.6,9,1.3,8.4,1.1L8.2,1C8.1,1,8,1,7.9,1H7.6L7.3,1.1C7.1,1.1,7,1.1,6.9,1.2L6.6,1.4
	c-0.1,0-0.2,0.1-0.2,0.2L6.2,1.7C5.7,2.3,5.4,3.1,5.6,3.8c0,0.2,0,0.4-0.1,0.5l0,0L5.4,4.4C5.2,4.6,5,4.8,4.8,5.1
	C2.3,7.5,1.6,11.2,3,14.3c0.4,1,0.8,2,1.1,3c0,0.1,0.1,0.2,0.1,0.3c0.4,1.2,0.4,2.6,0,3.8c-0.4,0.8-0.1,1.8,0.7,2.2
	c0.2,0.1,0.4,0.1,0.6,0.2h0.1c0.2,0,0.4-0.1,0.6-0.1l5.7-2.1l0.9-0.3l3.3-1.3c2.2-0.8,4.4-1.7,6.6-2.5c0.2-0.1,0.3-0.2,0.5-0.3
	l0.1-0.1c0.6-0.7,0.5-1.7-0.2-2.3C22.9,14.6,22.8,14.5,22.6,14.4z M5.2,12.9c-0.2,0-0.3-0.1-0.3-0.3c-0.1-0.3-0.1-0.5-0.1-0.8
	c0-0.2-0.1-0.6,0.2-0.6c0.4,0,0.3,0.4,0.3,0.6c0,0.2,0.1,0.5,0.1,0.7C5.4,12.7,5.4,12.8,5.2,12.9z M7.8,6.3C7.4,6.6,7,7,6.6,7.5
	c-0.4,0.6-0.8,1.2-1,1.9c0,0.1-0.1,0.2-0.1,0.3c0,0.2-0.2,0.3-0.3,0.2c0,0,0,0,0,0C5,9.9,5,9.7,5,9.5l0.1-0.3C5.3,8.4,5.7,7.7,6.2,7
	C6.6,6.6,7,6.2,7.4,5.8c0.2-0.1,0.4-0.4,0.6-0.1C8.3,6,8,6.1,7.8,6.3z"
              />
            </svg>
          </button>
        </div>
        <div>
          <div class="profile-image" v-if="getIsLogin()">
            <img :src="getUserInfo().profile" />
            <button
              class="banner-image-edit"
              @click="$refs.navbarUserInfo.toggle()"
            ></button>
          </div>
          <NavbarUserInfo ref="navbarUserInfo" />
        </div>
      </div>
    </div>
    <Navbar />
    <Login ref="loginModal" />
    <Join ref="joinModal" />
    <Repassword />
    <EmailModal />
    <ConditionsModal />
    <AgreementModal />
    <UnregisterModal />
    <ProfilePicModal />
    <NotificationModal :notifications="notifications" />
    <LevelAbout />
  </div>
</template>

<script>
import Navbar from "@/components/common/Navbar.vue";
import Login from "@/components/user/Login.vue";
import Join from "@/components/user/Join.vue";
import Repassword from "@/components/user/Repassword.vue";
import EmailModal from "@/components/user/EmailModal.vue";
import ConditionsModal from "@/components/user/ConditionsModal.vue";
import AgreementModal from "@/components/user/AgreementModal.vue";
import UnregisterModal from "@/components/user/setting/UnregisterModal.vue";
import ProfilePicModal from "@/components/user/setting/ProfilePicModal.vue";
import NotificationModal from "@/components/notification/notification.vue";
import NavbarUserInfo from "@/components/common/NavbarUserInfo.vue";
import LevelAbout from "@/components/user/setting/LevelAbout.vue";

import { mapActions, mapGetters } from "vuex";

export default {
  name: "Header",
  components: {
    Navbar,
    Login,
    Join,
    Repassword,
    EmailModal,
    ConditionsModal,
    AgreementModal,
    UnregisterModal,
    ProfilePicModal,
    NotificationModal,
    NavbarUserInfo,
    LevelAbout,
  },
  data() {
    return {
      searchWord: "",
      eventSource: null,
      notifications: [],
    };
  },
  methods: {
    ...mapActions({
      Logout: "user/logout",
      fetchUserInfo: "user/fetchUserInfo",
    }),
    ...mapGetters({
      getIsLogin: "user/getIsLogin",
      getUserInfo: "user/getUserInfo",
      getEmail: "user/getEmail",
    }),
    showNavBar() {
      const navbar = document.querySelector(".navbar");
      const background = document.querySelector(".navbar-background");
      const body = document.querySelector("body");

      body.classList.add("hide-scroll");
      navbar.setAttribute("style", "left: 0px");
      background.classList.remove("hide");
    },
    openLogin() {
      document.querySelector(".container-login").classList.remove("hide");
    },

    logout() {
      this.Logout();
    },
    search() {
      const keyword = this.searchWord;
      if (keyword.charAt(0) === "#") {
        this.tagSearch(keyword.substring(1));
      } else {
        this.keywordSearch(keyword);
      }
    },
    keywordSearch(keyword) {
      this.$router.push({
        name: "Search",
        query: { keyword },
      });
      if (this.$route.name === "Search") {
        this.$router.go();
      }
    },
    tagSearch(tag) {
      this.$router.push({
        name: "Search",
        query: { tag },
      });
      if (this.$route.name === "Search") {
        this.$router.go();
      }
    },
    moveToProfile() {
      this.$router.push({ name: "Blog", params: { email: this.getEmail() } });
    },
    openNotification() {
      document
        .querySelector(".container-notification")
        .classList.remove("hide");
    },
    async setupStream() {
      // console.log("==> 이벤트 소스 수행");
      this.eventSource = await new EventSource(
        "http://i3a604.p.ssafy.io:8081/api/notification/push?email=" +
          this.getEmail(),
        { withCredentials: true }
      );
      // this.eventSource.onopen = function(e) {
      // console.log("이벤트 소스 오픈");
      // console.log(e);
      // };
      var instance = this;
      this.eventSource.onmessage = function(e) {
        // console.log("이벤트 소스 메시지 도착");
        instance.notifications = JSON.parse(e.data);
      };
      // this.eventSource.onerror = function(e) {
      // console.log("이벤트 소스 에러");
      // console.log(e);
      // };
    },
    unSetupStream() {
      if (this.eventSource === null) {
        return;
      }
      // console.log("==> 이벤트 소스 종료");
      this.eventSource.close();
    },
  },
  created() {
    this.fetchUserInfo(this.getEmail());
  },
  mounted() {
    // var profileImages = document.querySelectorAll(".profile-image");
    // profileImages.forEach((profileImage) => {
    //   console.log("count");
    //   profileImage.style.backgroundImage = `url('${
    //     this.getUserInfo().profile
    //   }')`;
    // });
    this.setupStream();
  },
  beforeDestroy() {
    this.unSetupStream();
  },
};
</script>

<style scoped lang="scss">
@import "@/assets/_variables.scss";

button:hover {
  opacity: 0.7;
}

.header-container {
  position: fixed;
  display: flex;
  align-items: center;
  width: 100vw;
  height: 56px;
  background-color: white;
  z-index: 10;
}
.header-front,
.header-end {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  margin: 0px 20px;
}

// front

.btn-navbar-toggle {
  display: none;
}

.logo {
  margin: 0px 24px;
  display: flex;
  #logo-img {
    fill: #65b1fb;
    height: 24px;
  }

  #logo-text {
    width: 72px;
    height: 24px;
  }
}
// center

.header-center {
  display: none;
}

/** end */
.header-end {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: auto;
  margin-left: auto;
  margin-right: 30px;
  svg {
    width: 24px;
  }
}

.btn-login {
  // padding: 4px 16px;
  border: none;
  border-radius: 5%;
  color: #65b1fb;
  font-weight: 600;
  margin: 0 10px;
}

.user-profile {
  border-radius: 50%;
}

.btn-main-search.mobile {
  background: rgba(0, 0, 0, 0);
  width: 30px;
  height: 30px;
  border: none;
  margin-left: auto;
  svg {
    fill: rgb(150, 150, 150);
  }
}

.profile-icon:hover {
  opacity: 0.7;
}

@media (min-width: $threeArticles) {
  .header-container {
    position: fixed;
    display: flex;
    align-items: center;
    justify-content: space-between;
    top: 0;
    left: 0;
    padding: 0 23px;
  }

  /** front */
  .header-front {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 180px;
    margin-right: auto;
    margin-left: 0;
  }
  .btn-navbar-toggle {
    width: 26px;
    height: 26px;
    display: block;
    button {
      width: 26px;
      height: 26px;
    }
    svg {
      width: 22px;
      height: 22px;
      fill: #555555;
    }
  }

  /* center */
  .header-center {
    display: flex;
    margin: 0px 20px;
    flex: 0 1 30%;
    width: 300px;
  }
  .container-main-search {
    display: flex;
    flex: 1 1 auto;
    align-items: baseline;
    width: 100%;
    color: rgb(162, 163, 163);
  }
  .icon-main-search {
    width: 20px;
    height: 25px;
  }
  .btn-main-search.desktop {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    border: none;
    margin-left: auto;
    svg {
      width: 24px;
      height: 24px;
      fill: #999999;
    }
  }

  .btn-main-search {
    img {
      width: 16px;
      height: 16px;
    }
  }

  .mobile {
    display: none;
  }
  .input-main-search {
    flex: 1 1 20%;
    background: white;
    width: 640px;
    height: 30px;
    border: none;
    border-bottom: 1px solid #dddddd;
    &:focus {
      outline: none !important;
      box-shadow: 0 0 4px rgb(137, 197, 199);
    }
  }
}
.btn-newpost {
  border-radius: 25px;
  color: #727272;
  border: 1px solid #727272;
  padding: 5px 20px;
  margin-right: 24px;
}

.notification-icon {
  margin-top: 5px;
  width: 24px;
  height: 24px;
  margin-right: 24px;
  fill: #727272;
}

.profile-image {
  // position: relative;
  display: flex;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  &:hover {
    opacity: 0.7;
  }
  button {
    position: absolute;
    width: 36px;
    height: 36px;
    font-size: 1.4rem;
    color: rgba(0, 0, 0, 0);
    background-color: rgba(0, 0, 0, 0);
    border-radius: 50%;
  }
  img {
    // position: absolute;
    border-radius: 50%;
  }
}

.container {
  position: relative;
  -webkit-perspective: 1000;
  -webkit-backface-visibility: hidden;
}
.badge-num {
  box-sizing: border-box;
  font-family: "Trebuchet MS", sans-serif;
  background: #ff6d6d;
  cursor: default;
  border-radius: 50%;
  color: #fff;
  font-size: 0.8rem;
  height: 20px;
  width: 20px;
  line-height: 1.2rem;
  top: -20px;
  right: -30px;
  border: 1px solid #fff;
  position: absolute;
  text-align: center;
  box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.2);
  -webkit-animation: pulse 1.5s 1;
  animation: pulse 1.5s 1;
}
.badge-num:after {
  content: "";
  position: absolute;
  top: -2px;
  left: -2px;
  border: 2px solid rgba(255, 0, 0, 0.5);
  opacity: 0;
  border-radius: 50%;
  width: 100%;
  height: 100%;
  animation: sonar 1.5s 1;
}
@keyframes sonar {
  0% {
    transform: scale(0.9);
    opacity: 1;
  }
  100% {
    transform: scale(2);
    opacity: 0;
  }
}
@keyframes pulse {
  0% {
    transform: scale(1);
  }
  20% {
    transform: scale(1.4);
  }
  50% {
    transform: scale(0.9);
  }
  80% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}
</style>
