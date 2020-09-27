<template>
  <div id="outerdiv">
    <div>
      <label for="password">비밀번호 (영문, 숫자 8~20자) </label>
      <input
        v-model="userInfo.password"
        required
        minlength="8"
        maxlength="20"
        id="password"
        type="password"
        name="password"
        @keyup="passwordEqualCheck"
        placeholder="비밀번호를 입력해주세요"
        pattern="^((?=\S*?[A-Za-z0-9]).{0,})\S$"
        title="영문 숫자 혼합 8자 이상"
      />
    </div>
    <br />

    <label for="password-confirm">비밀번호 확인</label>
    <input
      v-model="passwordConfirm"
      required
      id="password-confirm"
      type="password"
      name="password-confirm"
      @keyup="passwordEqualCheck"
      placeholder="비밀번호를 한번 더 입력해주세요"
    />
    <br />

    <div class="msg msg-password-confirm">
      비밀번호가 일치하지 않습니다.
    </div>
    <button @click="reset">
      확인
    </button>
  </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  name: "RepasswordLink",
  data() {
    return {
      userInfo: {
        email: this.$route.params.email,
        password: ""
      },
      passwordConfirm: "",
      dom: {
        passwordConfirmErrMsg: ""
      }
    };
  },
  methods: {
    passwordEqualCheck() {
      if (!(this.userInfo.password === this.passwordConfirm)) {
        this.dom.passwordConfirmErrMsg.classList.add("show");
        return false;
      }
      this.dom.passwordConfirmErrMsg.classList.remove("show");
      return true;
    },
    reset() {
      axios
        .post(`${SERVER_URL}/api/auth/resetPassword`, this.userInfo)
        .then(res => {
          console.log(res);
          alert("비밀번호가 변경되었습니다.");
          this.$router.push({ name: "Home" });
        })
        .catch(err => console.log(err));
    }
  },
  mounted() {
    this.dom.passwordConfirmErrMsg = document.querySelector(
      ".msg-password-confirm"
    );
  }
};
</script>

<style scoped>
#outerdiv {
  height: 100vh;
  display: flex;
  align-items: center;
}

.msg {
  display: none;
  font-size: 0.9em;
  margin-left: 5px;
  color: crimson;
}

.show {
  display: block;
}
</style>
