<template>
  <div id="outerdiv">
    <div class="container-password">
      <div></div>
      <div>
        <input
          class="input-password"
          type="password"
          v-model="userInfo.password"
          placeholder="비밀번호를 입력하세요"
        />
      </div>
      <div class="more-arrow" @click="login"></div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "LoginPassword",
  data() {
    return {
      userInfo: {
        email: this.$route.params.email,
        password: ""
      }
    };
  },
  methods: {
    ...mapActions({
      Login: "user/login"
    }),
    login() {
      this.Login(this.userInfo)
        .then(() => {
          alert("환영합니다.");
          this.$router.push({ name: "Home" });
        })
        .catch(err => console.log(err));
    }
  }
};
</script>

<style scoped lang="scss">
#outerdiv {
  height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
}

.container-password {
  margin: 0;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 80px;
}

// 화살표

$background: whitesmoke;
.more-arrow {
  transform: rotate(270deg);
  background-color: transparent;
  border: transparent;
  border-radius: 50%;
  width: 80px;
  height: 80px;
  position: relative;

  &:after,
  &:before {
    content: "";
    width: 0;
    height: 0;
    border-style: solid;
    border-color: transparent;
    border-left-width: 40px;
    border-right-width: 40px;
    border-top-width: 30px;
    position: absolute;
    left: 10px;
  }
  &:before {
    border-top-color: darken($background, 25%);
    top: 30px;
  }
  &:after {
    border-top-color: white;
    top: 26px;
  }
  &:hover {
    // background: darken($background, 5%);
    // border-color: darken($background, 15%);
    &:before {
      border-top-color: darken($background, 50%);
    }
    // &:after {
    //   border-top-color: darken($background, 5%);
    // }
  }
}

// 입력창

.input-password {
  background-image: linear-gradient(lightgrey, lightgrey);
  background-position: bottom 5px center;
  background-size: calc(100% - 10px) 1px;
  background-repeat: no-repeat;
  width: 500px;
  border: 0;
  padding: 10px;
  padding-bottom: 15px;
  justify-self: center;
  font-size: 25px;
}

input:focus {
  outline: none;
}
</style>
