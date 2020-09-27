<template>
  <div ref="navbarUser" class="container-navbar-userinfo hide">
    <div class="make-absolute">
      <header>
        <div class="profile-image">
          <img
            :src="
              getUserInfo().profile === null
                ? 'https://cdns.iconmonstr.com/wp-content/assets/preview/2012/240/iconmonstr-user-20.png'
                : getUserInfo().profile
            "
            alt=""
          />
        </div>
        <div class="userinfo">
          <span>{{ getUserInfo().nickname }} </span>
          <span>{{ getUserInfo().email }} </span>
        </div>
      </header>
      <main>
        <button @click="$router.push(`/${getUserInfo().email}`)">
          <svg
            width="24"
            height="24"
            viewBox="0 0 46 23.5"
            fill="#727272"
            xmlns="http://www.w3.org/2000/svg"
            fill-rule="evenodd"
            clip-rule="evenodd"
          >
            <polygon points="7.63 23.5 0 23.5 0 0 2.14 0 2.14 21.67 7.63 21.67 7.63 23.5 7.63 23.5"/><path d="M22.63,35.08a1.68,1.68,0,0,1-.47,1.2,1.8,1.8,0,0,1-1.21.47H14.22a1.61,1.61,0,0,1-1.67-1.55.24.24,0,0,1,0-.12V14.92a1.63,1.63,0,0,1,.45-1.2,1.77,1.77,0,0,1,1.2-.47h6.7a1.67,1.67,0,0,1,1.2.47,1.47,1.47,0,0,1,.47,1.2Zm-2.1-.16V15.08H14.64V34.92Z" transform="translate(-2 -13.25)"/><path d="M36.15,35.08a1.68,1.68,0,0,1-.47,1.2,1.8,1.8,0,0,1-1.21.47H28.21a1.57,1.57,0,0,1-1.67-1.43V15A1.65,1.65,0,0,1,27,13.77a1.79,1.79,0,0,1,1.2-.47h6.3a1.71,1.71,0,0,1,1.21.47A1.73,1.73,0,0,1,36.15,15v5.5H34V15.13H28.58V35H34V26.83H31.45V25.05h4.7Z" transform="translate(-2 -13.25)"/><polygon points="46 23.5 43.66 23.5 43.66 20.89 46 20.89 46 23.5 46 23.5"/>
          </svg>
          내 블로그
        </button>
        <button @click="$router.push('/setting/blog')">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
            fill="#727272"
          >
            <path
              d="M22.2,14.2c0.1-0.4,0.1-0.8,0.1-1.2c0-0.4,0-0.8-0.1-1.2l2.6-2C25,9.6,25.1,9.2,24.9,9l-2.5-4.3c-0.2-0.3-0.5-0.4-0.8-0.3
	l-3.1,1.2c-0.6-0.5-1.3-0.9-2.1-1.2l-0.5-3.3c-0.1-0.3-0.3-0.5-0.6-0.5h-4.9c-0.3,0-0.6,0.2-0.6,0.5L9.5,4.5C8.7,4.8,8,5.2,7.4,5.7
	L4.3,4.4C4,4.3,3.7,4.4,3.6,4.7L1.1,9C0.9,9.2,1,9.6,1.3,9.8l2.6,2c-0.1,0.4-0.1,0.8-0.1,1.2s0,0.8,0.1,1.2l-2.6,2
	C1,16.4,0.9,16.8,1.1,17l2.5,4.3c0.2,0.3,0.5,0.4,0.8,0.3l3.1-1.2c0.6,0.5,1.3,0.9,2.1,1.2l0.5,3.3c0.1,0.3,0.3,0.5,0.6,0.5h4.9
	c0.3,0,0.6-0.2,0.6-0.5l0.5-3.3c0.8-0.3,1.5-0.7,2.1-1.2l3.1,1.2c0.3,0.1,0.6,0,0.8-0.3l2.5-4.3c0.2-0.3,0.1-0.6-0.2-0.8L22.2,14.2z
	 M13,17.6c-2.5,0-4.6-2.1-4.6-4.6s2.1-4.6,4.6-4.6s4.6,2.1,4.6,4.6S15.5,17.6,13,17.6z"
            /></svg
          >계정 설정
        </button>
        <button @click="logout">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 35 35"
            fill="#727272"
          >
            <path d="M33.19,37.17A8,8,0,0,1,33,39.7a4,4,0,0,1-4.06,2.75c-5.72.06-11.44.08-17.16,0C9,42.4,7.5,40.68,7.5,37.89V24.67h4.12q0,6.21,0,12.45c0,.82.25,1.11,1.08,1.11,5.12,0,10.26,0,15.39,0,.77,0,1.07-.29,1-1.07s0-1.33,0-2,.25-.92.85-.91H30C33.19,34,33.2,34,33.19,37.17Z" transform="translate(-7.5 -7.5)"/><path d="M11.62,24.67H7.5V12.23c0-3.06,1.58-4.68,4.64-4.71,2.45,0,4.89,0,7.33,0h8.79c3.46,0,4.93,1.49,4.93,5,0,.77,0,1.55,0,2.32s-.29.91-.93,1c-3.11.25-3.14.26-3.17-2.9,0-.85-.31-1.09-1.11-1.09q-7.64,0-15.28,0c-.79,0-1.11.22-1.11,1.08Q11.61,18.77,11.62,24.67Z" transform="translate(-7.5 -7.5)"/><path d="M28.13,28.58c-1.69,0-3.38,0-5.06,0-.69,0-1-.24-1-1,0-1.71,0-3.42,0-5.13,0-.89.36-1.08,1.13-1.07,3.22,0,6.45,0,9.68,0,.85,0,1.19-.23,1.14-1.13s0-2.07,0-3.11c0-.2-.08-.46.17-.56s.33.12.45.24c2.54,2.56,5.07,5.13,7.62,7.69.35.35.25.6-.06.91l-7.48,7.56c-.15.15-.29.39-.51.32S34,33,34,32.74c0-1.1,0-2.2,0-3.3,0-.63-.22-.87-.85-.86-1.68,0-3.37,0-5.06,0Z" transform="translate(-7.5 -7.5)"/>
          </svg>
          로그아웃
        </button>
      </main>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  data: function() {
    return {
      isShow: false
    };
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    ...mapMutations({
      setLogout: "user/setLogout"
    }),
    logout() {
      if (confirm("로그아웃 하시겠습니까?")) {
        this.setLogout();
        location.href = "/";
      }
    },
    toggle() {
      const navbarUser = document.querySelector(".container-navbar-userinfo");
      if (navbarUser.classList.contains("hide")) {
        navbarUser.classList.remove("hide");
      } else {
        navbarUser.classList.add("hide");
      }
    }
  },
  mounted() {
    const navbarUser = document.querySelector(".container-navbar-userinfo");
    window.addEventListener("click", function(e) {
      if (
        !navbarUser.contains(e.target) &&
        !e.target.classList.contains("banner-image-edit")
      ) {
        navbarUser.classList.add("hide");
      }
    });
  }
};
</script>

<style lang="scss" scoped>
.container-navbar-userinfo {
  position: relative;
  width: 0px;
  height: 0px;
  left: -180px;
  top: 5px;
  &.hide {
    display: none;
  }
  .make-absolute {
    display: flex;
    flex-direction: column;
    position: absolute;
    background-color: white;
    border: 1px solid rgb(233, 233, 233);
    width: 240px;
    header {
      display: flex;
      border-bottom: 1px rgb(233, 233, 233) solid;
      .profile-image {
        display: flex;
        justify-content: center;
        align-items: center;
        background-image: none !important;
        img {
          width: 36px;
          height: 36px;
          border-radius: 50%;
          margin: 10px;
        }
      }
      .userinfo {
        display: flex;
        flex-direction: column;
        justify-content: space-evenly;
        align-items: flex-start;
        span:nth-child(2) {
          color: rgb(179, 179, 179);
          font-size: 0.9em;
        }
      }
    }
    main {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
      button {
        display: flex;
        align-items: center;
        color: #727272;
        width: 100%;
        padding: 12px 15px;
        &:hover {
          background-color: aliceblue;
        }
        svg {
          width: 24px;
          height: 24px;
          margin-right: 15px;
        }
      }
    }
  }
}
</style>
