<template>
  <div style="background:white" class="container-account">
    <header>
      <div class="profile-image">
        <button class="banner-image-edit" @click="openProfilePic">
          수정하기
        </button>
      </div>
    </header>
    <main>
      <div class="p-nickname" @click="enableNickname()">
        <span>닉네임</span>
        <p class="nickname">{{ getUserInfo().nickname }}</p>
      </div>
      <div class="edit-nickname hide">
        <div class="background" @click="disableNickname()"></div>
        <span>닉네임</span>
        <div class="container-inputNickname">
          <input class="input-nickname" v-model="nickname" />
          <button class="btn btn-edit" @click="updateNickname">수정하기</button>
        </div>
        <hr />
      </div>
      <div class="level-icon">
        <span class="level-title"
          >레벨 / 경험치<button
            class="btn-aboutlevel"
            @mouseenter="showLevelAbout"
            @mouseleave="hideLevelAbout"
          >
            ❔
          </button></span
        >

        <div class="level-icon-contents">
          <div class="score">
            다음 레벨까지
            <span> {{ totalScore - getUserInfo().score }}</span> 점 남았습니다!
          </div>
          <div class="levelicon-and-bar">
            <LevelIcon :score="getUserInfo().score" />
            <span>{{ levelPercentage }}%</span>
            <div class="meter">
              <span class="meter-gage"></span>
            </div>
          </div>
        </div>
      </div>
      <!-- <div class="level-icon">
        <span>연동된 SNS계정</span>
        <div class="container-btn-social">
          <button class="btn-circle btn-google"></button>
          <button class="btn-circle btn-facebook"></button>
          <button class="btn-circle btn-github"></button>
        </div>
      </div> -->
      <div class="level-icon">
        <span class="span-password" @click="enablePassword()"
          >비밀번호 바꾸기</span
        >
        <div class="edit-password hide">
          <div class="background" @click="disablePassword()"></div>
          <div class="container-inputPassword">
            <input
              class="input-password"
              v-model="password"
              type="password"
              placeholder="현재 비밀번호"
            />
            <input
              class="input-password"
              v-model="newpassword"
              type="password"
              @keyup="passwordEqualCheck"
              placeholder="새 비밀번호"
            />
            <input
              class="input-password"
              v-model="newpasswordConfirm"
              type="password"
              @keyup="passwordEqualCheck"
              placeholder="새 비밀번호 확인"
            />
            <div class="msg msg-password-confirm hide">
              비밀번호가 일치하지 않습니다.
            </div>
          </div>
          <button class="btn btn-pw" @click="updatePassword()">변경하기</button>
        </div>
      </div>
      <div class="container-unregister">
        <span class="span-unregister" @click="openUnregisterModal()"
          >회원 탈퇴</span
        >
      </div>
    </main>
    <!-- 블러효과 용 -->
    <svg version="1.1" xmlns="http://www.w3.org/2000/svg">
      <filter id="blur">
        <feGaussianBlur stdDeviation="20" />
      </filter>
    </svg>
  </div>
</template>

<script>
import LevelIcon from "@/components/user/LevelIcon.vue";

import { mapActions, mapGetters } from "vuex";

export default {
  components: {
    LevelIcon,
  },
  data() {
    return {
      email: this.getUserInfo().email,
      nickname: this.getUserInfo().nickname,
      password: "",
      newpassword: "",
      newpasswordConfirm: "",
      dom: {
        passwordConfirmErrMsg: "",
      },
      file: "",
      profileURL: "",
      totalScore: null,
      showModal: false,
      levelPercentage: null,
    };
  },
  methods: {
    ...mapActions({
      fetchUserInfo: "user/fetchUserInfo",
      UpdateNickname: "user/updateNickname",
      UpdatePassword: "user/updatePassword",
      Unregister: "user/unregister",
      UploadFile: "user/uploadFile",
    }),
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
      getEmail: "user/getEmail",
      getIsLogin: "user/getIsLogin",
    }),
    enableNickname() {
      document.querySelector(".p-nickname").classList.add("hide");
      document.querySelector(".edit-nickname").classList.remove("hide");
      this.nickname = this.getUserInfo().nickname;
    },
    disableNickname() {
      document.querySelector(".p-nickname").classList.remove("hide");
      document.querySelector(".edit-nickname").classList.add("hide");
    },
    updateNickname() {
      this.UpdateNickname({ email: this.email, nickname: this.nickname });
      this.$router.go();
    },
    enablePassword() {
      document.querySelector(".edit-password").classList.remove("hide");
    },
    disablePassword() {
      document.querySelector(".edit-password").classList.add("hide");
    },
    updatePassword() {
      this.UpdatePassword({
        email: this.email,
        password: this.password,
        newpassword: this.newpassword,
      })
        .then(() => {
          alert("비밀번호가 변경되었습니다.");
          this.$router.go();
        })
        .catch((err) => console.log(err));
    },
    passwordEqualCheck() {
      if (!(this.newpassword === this.newpasswordConfirm)) {
        this.dom.passwordConfirmErrMsg.classList.remove("hide");
        return false;
      }
      this.dom.passwordConfirmErrMsg.classList.add("hide");
      return true;
    },
    openUnregisterModal() {
      document.querySelector(".container-unregister").classList.remove("hide");
    },
    openProfilePic() {
      document.querySelector(".container-profilepic").classList.remove("hide");
    },
    showLevelAbout() {
      this.showModal = true;
      document.querySelector(".container-levelabout").classList.remove("hide");
    },
    hideLevelAbout() {
      this.showModal = false;
      document.querySelector(".container-levelabout").classList.add("hide");
    },
  },
  mounted() {
    this.dom.passwordConfirmErrMsg = document.querySelector(
      ".msg-password-confirm"
    );
    this.profileURL = this.getUserInfo().profile;
    if (this.profileURL) {
      this.$el.querySelector("header").style.backgroundImage = `url('${
        this.getUserInfo().profile
      }')`;
      this.$el.querySelector(".profile-image").style.backgroundImage = `url('${
        this.getUserInfo().profile
      }')`;
    }
    if (this.getUserInfo().score < 200) {
      this.totalScore = 200;
    } else if (this.getUserInfo().score < 1000) {
      this.totalScore = 1000;
    } else if (this.getUserInfo().score < 3000) {
      this.totalScore = 3000;
    } else if (this.getUserInfo().score < 6000) {
      this.totalScore = 6000;
    } else if (this.getUserInfo().score < 10000) {
      this.totalScore = 10000;
    }
    document.querySelector(".meter-gage").style.width = `${(this.getUserInfo()
      .score /
      this.totalScore) *
      100}%`;
    this.levelPercentage = Math.ceil(
      (this.getUserInfo().score / this.totalScore) * 100
    );
    var levelAbout = document.querySelector(".modal-levelabout");
    var hoverButton = document.querySelector(".btn-aboutlevel");
    var viewportOffset = hoverButton.getBoundingClientRect();
    var top = viewportOffset.top;
    var left = viewportOffset.left;
    levelAbout.style.top = `${top - 430}px`;
    levelAbout.style.left = `${left + 30}px`;
    console.log(top);
    console.log(left);
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/user/setting/Account.scss";
</style>
