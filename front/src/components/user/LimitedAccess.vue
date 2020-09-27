<template>
  <div class="container-requirelogin">
    <h3>로그인하세요</h3>
    <p>로그인하고 더 많은 서비스를 이용해보세요!</p>
    <button class="btn-login" @click="openLogin">
      로그인
    </button>
  </div>
</template>

<script>
export default {
  name: "LimitedAccess",
  methods: {
    openLogin() {
      document.querySelector(".container-login").classList.remove("hide");
    }
  }
};
</script>

<style scoped lang="scss">
.container-requirelogin {
  position: fixed;
  top: 0;
  background-color: rgb(241, 244, 247);
  min-width: 100%;
  min-height: 100%;
  text-align: left;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.btn-login {
  padding: 4px 16px;
  border: 1px solid dodgerblue;
  border-radius: 5%;
  color: dodgerblue;
  font-weight: 600;
  margin: 10px 10px;
}

.hide {
  display: none;
}
</style>
