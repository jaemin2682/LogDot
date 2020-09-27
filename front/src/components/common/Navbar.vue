<template>
  <div class="navbar">
    <div class="navbar-background hide" @click="closeNavbar"></div>
    <div class="navbar-container">
      <div class="navbar-header">
        <div class="navbar-header-front">
          <div class="btn-navbar-toggle">
            <button @click="closeNavbar">
              <svg
                class="icon-toggle"
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
            <div @click="closeNavbar">
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
            </div>
          </router-link>
        </div>
      </div>
      <hr style="width:0px; margin-top:0px" />
      <div class="navbar-body">
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <path
                  d="M10.6,23.2V16h4.8v7.2h6v-9.6H25L13,2.8L1,13.6h3.6v9.6H10.6z"
                />
              </svg>
              <span>홈</span>
            </div>
          </router-link>
        </div>
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/main/follow">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <g>
                  <polygon points="7.7,10.8 6.4,10.9 4.2,9 8.6,8.7 	" />
                  <polygon points="19.8,15.8 21,21.3 17.7,19.3 17.4,17.9 	" />
                  <polygon
                    points="25,9 22.3,11.4 15.1,10.8 12.9,5.6 14.6,1.5 17.5,8.4 	"
                  />
                  <polygon
                    points="19.4,14.3 16.1,17.2 16.4,18.5 17.8,24.5 11.4,20.6 5,24.5 6.7,17.2 1,12.3 7.3,11.7 8.5,11.6 9.8,8.6 11.4,4.7 
		12.3,6.9 14.3,11.6 21.3,12.2 21.8,12.3 	"
                  />
                </g>
              </svg>
              <span>팔로우</span>
            </div>
          </router-link>
        </div>
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/main/trending">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <path
                  d="M9.7,25c0,0-0.1,0-0.1,0c-0.8-0.3-1.6-0.6-2.3-1.1c-1.6-1.1-2.8-2.6-3.3-4.5c-0.3-1.5-0.2-3,0.2-4.5
	c0.4-1.7,1.3-3.1,2.4-4.4c0.9-1.1,1.9-2,2.7-3.1c0.6-0.7,1.1-1.5,1.4-2.4c0.4-1.1,0.3-2.3,0-3.4c-0.1-0.5,0.1-0.7,0.6-0.6
	c1.2,0.2,2.2,0.7,3.1,1.5c1.3,1,2.1,2.3,2.1,4c0,0.8,0,1.7,0,2.5c0,0.4,0,0.7,0.2,1.1c0.1,0.2,0.2,0.4,0.4,0.4
	c0.4,0.1,1.1-0.1,1.2-0.7c0.1-0.4,0.1-0.8,0.1-1.2c0-0.1,0-0.3,0-0.4c0.1-0.3,0.4-0.4,0.7-0.2c0.1,0.1,0.1,0.1,0.2,0.2
	c0.8,1.2,1.5,2.5,2,3.9c0.6,1.6,0.9,3.2,0.9,4.9c0,0.8,0,1.7-0.2,2.5c-0.8,2.7-2.7,4.3-5.2,5.3c-0.2,0.1-0.4,0-0.5-0.1
	c-0.1-0.2-0.1-0.4,0.1-0.5c0.2-0.2,0.3-0.4,0.5-0.6c1.2-1.6,1.1-3.3-0.4-4.7c-0.4-0.3-0.7-0.7-1.1-1c-0.8-0.8-1.4-1.8-1.7-2.9
	c0-0.2-0.1-0.4-0.1-0.6c-0.1,0-0.1,0.1-0.2,0.2c-0.3,0.4-0.5,0.8-0.8,1.2c-0.7,0.9-1.5,1.7-2.2,2.5c-0.9,1.1-1.2,2.4-1,3.9
	c0.1,0.7,0.4,1.4,0.8,2C10.2,24.6,10.1,24.8,9.7,25z"
                />
              </svg>
              <span>인기</span>
            </div>
          </router-link>
        </div>
        <hr />
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/main/history">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <path
                  d="M14.7,2.7C9,2.7,4.4,7.3,4.4,13H1l4.4,4.4l0.1,0.2l4.6-4.6H6.7c0-4.4,3.6-8,8-8s8,3.6,8,8s-3.6,8-8,8
	c-2.2,0-4.2-0.9-5.6-2.4l-1.6,1.6c1.9,1.9,4.4,3,7.3,3C20.4,23.3,25,18.7,25,13S20.4,2.7,14.7,2.7z M13.6,8.4v5.7l4.9,2.9l0.8-1.4
	l-4-2.4V8.4H13.6z"
                />
              </svg>
              <span>방문 기록</span>
            </div>
          </router-link>
        </div>
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/main/library">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <g>
                  <g>
                    <path
                      d="M10.3,3.2v1.7c-1-0.4-2.1-0.6-3.2-0.6C4.9,4.3,2.7,5.1,1,6.8l4.1,4.1h1.4v1.4c1.1,1.1,2.4,1.6,3.8,1.7v2.8H6.6v3.7
			c0,1.4,1.1,2.5,2.5,2.5h12.3c2,0,3.7-1.6,3.7-3.7v-16H10.3z M8.9,11V8.4H6.1L4.8,7.1c0.7-0.3,1.5-0.4,2.2-0.4
			c1.6,0,3.2,0.6,4.3,1.8l1.7,1.7l-0.2,0.2c-0.6,0.6-1.5,1-2.4,1C10,11.5,9.4,11.3,8.9,11z M22.5,19.1c0,0.7-0.6,1.2-1.2,1.2
			c-0.7,0-1.2-0.6-1.2-1.2v-2.5h-7.4v-3.2c0.7-0.3,1.4-0.7,1.9-1.3l0.2-0.2l3.5,3.5h1.7v-1.7l-7.4-7.3V5.6h9.8
			C22.5,5.6,22.5,19.1,22.5,19.1z"
                    />
                  </g>
                </g>
              </svg>
              <span>보관함</span>
            </div>
          </router-link>
        </div>
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/main/likelist">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <path
                  d="M13,24l-1.7-1.6C5.1,16.8,1,13.1,1,8.6C1,4.9,3.9,2,7.6,2c2.1,0,4.1,1,5.4,2.5C14.3,3,16.3,2,18.4,2C22.1,2,25,4.9,25,8.6
	c0,4.5-4.1,8.2-10.3,13.8L13,24z"
                />
              </svg>
              <span>좋아요 표시한 게시물</span>
            </div>
          </router-link>
        </div>
        <hr />
        <div class="follow-list">
          <span>팔로우</span>
          <div
            class="navbar-item"
            v-for="(following, idx) in followings"
            :key="idx"
            v-show="idx < followShowLimit"
          >
            <button @click="moveToFollowingProfile(following.email)">
              <img :src="following.profile" alt />
              <span>{{ following.nickname }}</span>
            </button>
          </div>

          <div class="navbar-item">
            <button
              class="btn-toggle"
              @click="collapseFollowList"
              v-show="followings.length > constfollowShowLimit"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                viewBox="0 0 24 24"
                class="arrow-down"
              >
                <path
                  d="M0 7.33l2.829-2.83 9.175 9.339 9.167-9.339 2.829 2.83-11.996 12.17z"
                />
              </svg>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                viewBox="0 0 24 24"
                class="arrow-up collapsed"
              >
                <path
                  d="M0 16.67l2.829 2.83 9.175-9.339 9.167 9.339 2.829-2.83-11.996-12.17z"
                />
              </svg>
              <span>더보기</span>
            </button>
          </div>
        </div>
        <hr />
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/setting">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 0 26 26"
              >
                <path
                  d="M22.2,14.2c0.1-0.4,0.1-0.8,0.1-1.2c0-0.4,0-0.8-0.1-1.2l2.6-2C25,9.6,25.1,9.2,24.9,9l-2.5-4.3c-0.2-0.3-0.5-0.4-0.8-0.3
	l-3.1,1.2c-0.6-0.5-1.3-0.9-2.1-1.2l-0.5-3.3c-0.1-0.3-0.3-0.5-0.6-0.5h-4.9c-0.3,0-0.6,0.2-0.6,0.5L9.5,4.5C8.7,4.8,8,5.2,7.4,5.7
	L4.3,4.4C4,4.3,3.7,4.4,3.6,4.7L1.1,9C0.9,9.2,1,9.6,1.3,9.8l2.6,2c-0.1,0.4-0.1,0.8-0.1,1.2s0,0.8,0.1,1.2l-2.6,2
	C1,16.4,0.9,16.8,1.1,17l2.5,4.3c0.2,0.3,0.5,0.4,0.8,0.3l3.1-1.2c0.6,0.5,1.3,0.9,2.1,1.2l0.5,3.3c0.1,0.3,0.3,0.5,0.6,0.5h4.9
	c0.3,0,0.6-0.2,0.6-0.5l0.5-3.3c0.8-0.3,1.5-0.7,2.1-1.2l3.1,1.2c0.3,0.1,0.6,0,0.8-0.3l2.5-4.3c0.2-0.3,0.1-0.6-0.2-0.8L22.2,14.2z
	 M13,17.6c-2.5,0-4.6-2.1-4.6-4.6s2.1-4.6,4.6-4.6s4.6,2.1,4.6,4.6S15.5,17.6,13,17.6z"
                />
              </svg>

              <span>설정</span>
            </div>
          </router-link>
        </div>
        <div class="navbar-item" @click="closeNavbar">
          <router-link tag="button" to="/main/About">
            <div>
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                <path
                  d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
                />
              </svg>
              <span>정보</span>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  props: ["showNavbar"],
  data: function() {
    return {
      isCollapsed: true,
      followings: [],
      followShowLimit: 5,
      constfollowShowLimit: 5
    };
  },
  methods: {
    ...mapGetters({
      getEmail: "user/getEmail"
    }),
    closeNavbar() {
      const navbar = document.querySelector(".navbar");
      const background = document.querySelector(".navbar-background");
      const body = document.querySelector("body");

      background.classList.add("hide");
      navbar.setAttribute("style", "left: -256px");
      body.classList.remove("hide-scroll");
    },
    collapseFollowList() {
      if (this.followShowLimit === this.constfollowShowLimit) {
        this.followShowLimit = this.followings.length;
      } else {
        this.followShowLimit = this.constfollowShowLimit;
      }
      const btnToggle = document.querySelector(".btn-toggle");
      const spanToggle = btnToggle.querySelector("span");
      const arrowUp = btnToggle.querySelector(".arrow-up");
      const arrowDown = btnToggle.querySelector(".arrow-down");

      if (this.isCollapsed) {
        this.isCollapsed = false;
        spanToggle.innerText = "간략히 보기";
        arrowUp.classList.remove("collapsed");
        arrowDown.classList.add("collapsed");
      } else {
        this.isCollapsed = true;
        spanToggle.innerText = "더보기";
        arrowUp.classList.add("collapsed");
        arrowDown.classList.remove("collapsed");
      }
    },
    async fetchFollowList() {
      if (this.getEmail() === "") return;

      let result = await axios.get(
        `${SERVER_URL}/api/follow/navilist?email=${this.getEmail()}`
      );
      console.log(result);
      if (result) {
        this.followings = result.data;
      }
    },
    moveToFollowingProfile(email) {
      this.$router.push({ name: "Blog", params: { email: email } });
      this.closeNavbar();
    }
  },
  mounted() {
    this.fetchFollowList();
  }
};
</script>

<style scoped lang="scss">
@import "@/assets/common/Navbar.scss";
</style>
