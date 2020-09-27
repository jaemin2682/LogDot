<template>
  <div class="container-unregister hide">
    <div class="background-unregister" @click="closeModal"></div>
    <div class="modal-unregister">
      <div class="modal-head">
        <div class="logo" @click="moveHome">
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
      <div class="modal-body">
        <hr />
        <h3>회원 탈퇴 하시겠습니까?</h3>
        <span
          >회원 탈퇴를 하시면 모든 회원 정보와 게시물들은 삭제되며,<br />
          복구 할 수 없습니다.
        </span>
      </div>
      <div class="container-button">
        <button class="btn btn-primary" @click="unregister">탈퇴</button>
        <button class="btn btn-cancel" @click="closeModal">취소</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "UnregisterModal",
  methods: {
    ...mapActions({
      Unregister: "user/unregister"
    }),
    closeModal() {
      document.querySelector(".container-unregister").classList.add("hide");
    },
    unregister() {
      this.Unregister()
        .then(() => {
          this.closeModal();
          this.$router.push({ name: "Main" });
        })
        .catch(err => console.log(err));
    },
    moveHome() {
      this.closeModal();
      this.$router.push({ name: "Main" });
    }
  }
};
</script>

<style scoped lang="scss">
.container-unregister {
  position: fixed;
  z-index: 5;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0);
}

.background-unregister {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.4);
}

// modal

.modal-unregister {
  position: relative;
  width: 420px;
  height: 390px;
  background-color: white;
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  border-radius: 3%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.modal-head {
  position: absolute;
  top: 0;
  width: 100%;
  padding: 20px;
  .logo {
    cursor: pointer;
    margin: 10px 70px;
    display: flex;
    #logo-img {
      fill: #65b1fb;
      height: 80px;
      margin-right: 15px;
    }
    #logo-text {
      width: 140px;
      height: 80px;
    }
  }
}

.modal-body {
  padding-top: 0;
  position: absolute;
}
hr {
  border-bottom: none;
}

.container-button {
  position: absolute;
  bottom: 0;
  display: flex;
  width: 100%;
  padding: 20px;
}

h3 {
  margin: 50px 0px 30px 0px;
  color: #727272;
}

span {
  color: #727272;
  font-size: 0.8rem;
}
.hide {
  display: none;
}

.btn {
  width: 100%;
  font-size: 1rem;
  font-weight: 600;
}

.btn-cancel {
  color: #1a7cff;
}
</style>
