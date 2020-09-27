<template>
  <div>
    <div id="outerdiv">
      <div class="container-nickname">
        <div></div>
        <div>
          <input
            class="input-nickname"
            v-model="nickname"
            v-if="nickname == ''"
            placeholder="닉네임을 입력하세요."
          />
          <input
            class="input-nickname input-nickname-active"
            v-model="nickname"
            v-else
            placeholder="닉네임을 입력하세요."
          />
        </div>
      </div>
      <div class="container-button">
        <svg
          class="arrowIcon arrowActive"
          data-name="arrowIcon"
          xmlns="http://www.w3.org/2000/svg"
          v-if="nickname"
          viewBox="0 0 52.21 100"
          @click="setNickname"
        >
          <path
            d="M82.09,56.73a2.22,2.22,0,0,1-.63,1.61L33.6,106.19a2.23,2.23,0,0,1-3-.06,2.21,2.21,0,0,1-.06-3.13L76.76,56.76,30.46,10.45a2.22,2.22,0,0,1,1.66-3.7,2.21,2.21,0,0,1,1.54.62L81.45,55.16A2.22,2.22,0,0,1,82.09,56.73Z"
            transform="translate(-29.88 -6.75)"
          />
          <path
            d="M82.09,56.73a2.22,2.22,0,0,1-.63,1.61L33.6,106.19a2.23,2.23,0,0,1-3-.06,2.21,2.21,0,0,1-.06-3.13L76.76,56.76,30.46,10.45a2.22,2.22,0,0,1,1.66-3.7,2.21,2.21,0,0,1,1.54.62L81.45,55.16A2.22,2.22,0,0,1,82.09,56.73Z"
            transform="translate(-29.88 -6.75)"
          />
          <path
            d="M82.09,56.73a2.22,2.22,0,0,1-.63,1.61L33.6,106.19a2.23,2.23,0,0,1-3-.06,2.21,2.21,0,0,1-.06-3.13L76.76,56.76,30.46,10.45a2.22,2.22,0,0,1,1.66-3.7,2.21,2.21,0,0,1,1.54.62L81.45,55.16A2.22,2.22,0,0,1,82.09,56.73Z"
            transform="translate(-29.88 -6.75)"
          />
        </svg>
        <svg
          class="arrowIcon arrowDisabled"
          data-name="arrowIcon"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 52.21 100"
          v-if="nickname == ''"
        >
          <path
            d="M82.09,56.73a2.22,2.22,0,0,1-.63,1.61L33.6,106.19a2.23,2.23,0,0,1-3-.06,2.21,2.21,0,0,1-.06-3.13L76.76,56.76,30.46,10.45a2.22,2.22,0,0,1,1.66-3.7,2.21,2.21,0,0,1,1.54.62L81.45,55.16A2.22,2.22,0,0,1,82.09,56.73Z"
            transform="translate(-29.88 -6.75)"
          />
          <path
            d="M82.09,56.73a2.22,2.22,0,0,1-.63,1.61L33.6,106.19a2.23,2.23,0,0,1-3-.06,2.21,2.21,0,0,1-.06-3.13L76.76,56.76,30.46,10.45a2.22,2.22,0,0,1,1.66-3.7,2.21,2.21,0,0,1,1.54.62L81.45,55.16A2.22,2.22,0,0,1,82.09,56.73Z"
            transform="translate(-29.88 -6.75)"
          />
          <path
            d="M82.09,56.73a2.22,2.22,0,0,1-.63,1.61L33.6,106.19a2.23,2.23,0,0,1-3-.06,2.21,2.21,0,0,1-.06-3.13L76.76,56.76,30.46,10.45a2.22,2.22,0,0,1,1.66-3.7,2.21,2.21,0,0,1,1.54.62L81.45,55.16A2.22,2.22,0,0,1,82.09,56.73Z"
            transform="translate(-29.88 -6.75)"
          />
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  data() {
    return {
      email: this.$route.params.email,
      token: this.$route.params.token,
      nickname: ""
    };
  },
  methods: {
    ...mapActions({
      ReceiveToken: "user/receiveToken",
      FetchUserInfo: "user/fetchUserInfo"
    }),
    ...mapGetters({
      getIsLogin: "user/getIsLogin",
      getEmail: "user/getEmail"
    }),
    setNickname() {
      axios
        .get(`${SERVER_URL}/api/auth/nicknameUpdate`, {
          params: {
            email: this.email,
            nickname: this.nickname
          }
        })
        .then(() => {
          this.fetchUserInfo();
          alert("회원가입이 완료되었습니다.");
          this.$router.push({ name: "Main" });
        });
    },
    async fetchUserInfo() {
      await this.FetchUserInfo(this.getEmail());
    }
  },
  created() {
    this.ReceiveToken({ token: this.token, email: this.email })
      .then(() => console.log(this.getIsLogin()))
      .catch(err => console.log(err));
  }
};
</script>

<style scoped lang="scss">
#outerdiv {
  height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
  background-color: rgb(255, 255, 255);
}

.container-nickname {
  margin: 0;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 80px;
}

.container-button {
  height: 100vh;
  width: 45%;
  display: flex;
  justify-content: center;
  align-items: center;
}

// 화살표
.arrowIcon {
  width: 10%;
  height: 10%;
}
.arrowActive {
  fill: #65b1fb;
}
.arrowDisabled {
  fill: #e9f1ff;
}

// 입력창

.input-nickname {
  background-image: linear-gradient(#e9f1ff, #e9f1ff);
  // background-image: linear-gradient(lightgrey, lightgrey);
  background-position: bottom 5px center;
  background-size: calc(100% - 10px) 2px;
  background-repeat: no-repeat;
  width: 500px;
  border: 0;
  padding: 10px;
  padding-bottom: 15px;
  justify-self: center;
  font-size: 25px;
}

.input-nickname-active {
  background-image: linear-gradient(#65b1fb, #65b1fb);
}

input:focus {
  outline: none;
}
</style>
