<template>
  <div class="container-repassword hide">
    <div class="background-repassword"></div>
    <div class="modal-repassword">
      <div class="left-repassword"></div>
      <div class="right-repassword">
        <!-- <button class="btn-close" @click="closeLogin">✖</button> -->
        <div class="title">
          <h1>비밀번호 재설정</h1>
        </div>
        <div class="modal-body">
          <p>가입 시 등록한 이메일 주소를 입력해주세요.</p>
          <p>이메일로 비밀번호를 재설정 할 수 있는 링크를</p>
          <p>보내드립니다.</p>
        </div>
        <form class="container-repassword-form">
          <div class="container-input">
            <input
              required
              type="email"
              placeholder="이메일 주소를 입력하세요."
              v-model="email"
            />
            <p class="msg-error hide">가입된 이메일이 아닙니다.</p>
          </div>
          <button class="btn btn-repassword" type="button" @click="sendMail">
            비밀번호 재설정 메일 보내기
          </button>
        </form>
        <div class="repassword-footer">
          <a class="link-back" href="javascript:void(0)" @click="moveToLogin"
            >뒤로가기</a
          >
          <a class="link-close" href="javascript:void(0)" @click="closeModal"
            >닫기</a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      email: ""
    };
  },
  methods: {
    closeModal() {
      document.querySelector(".container-repassword").classList.add("hide");
    },
    sendMail() {
      axios
        .get(
          `http://i3a604.p.ssafy.io:8081/api/auth/passwordEmail?email=${this.email}`
        )
        .then(res => {
          console.log(res);
          document.querySelector(".msg-error").classList.add("hide");
          alert("메일 발송 완료");
        })
        .catch(err => {
          console.log(err);
          document.querySelector(".msg-error").classList.remove("hide");
        });
    },
    moveToLogin() {
      document.querySelector(".container-login").classList.remove("hide");
      this.closeModal();
    }
  }
};
</script>

<style scoped lang="scss">
/** base */

$minimumWidth: 950px;

.container-repassword {
  position: fixed;
  z-index: 99999;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0);
}

.background-repassword {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-repassword {
  display: flex;
  position: fixed;
  width: 400px;
  height: 520px;
  background-color: rgb(252, 252, 252);
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  @media (min-width: $minimumWidth) {
    width: 780px;
  }
  .title {
    text-align: left;
    margin-bottom: 24px;
    h1 {
      margin-bottom: 12px;
    }
  }
  .left-repassword {
    display: none;
    width: 45%;
    background-image: url(https://cdn.pixabay.com/photo/2015/09/04/23/28/wordpress-923188_960_720.jpg);
    background-repeat: no-repeat;
    background-size: cover;
    @media (min-width: $minimumWidth) {
      display: block;
    }
  }
  .right-repassword {
    display: flex;
    flex-direction: column;
    padding: 24px;
    width: 100%;
    @media (min-width: $minimumWidth) {
      width: 55%;
    }
    .modal-body {
      margin: 40px 0;
      p {
        text-align: left;
        margin: 12px 0;
      }
    }
    /** repassword form */
    .container-repassword-form {
      display: flex;
      height: 100%;
      flex-direction: column;
      justify-content: space-around;
      align-content: flex-start;
    }
    .container-input {
      .msg-error {
        text-align: left;
        color: red;
        font-size: 0.8em;
      }
    }
    input {
      background-color: rgb(0, 0, 0, 0) !important;
      border: 1px solid #cccccc;
      border-radius: 5px;
      padding: 8px 5px;
      width: 100%;
      margin-bottom: 5px;
    }
    input[type="password"] {
      margin-bottom: 24px;
    }
    input:focus {
      outline: none !important;
      border: 1px solid cadetblue;
      box-shadow: 0 0 2px cadetblue;
    }

    .btn-repassword {
      width: 100%;
      padding: 8px 10px;
      color: white;
      border-radius: 5px;
      background-color: #6da7ff;
      font-weight: 500;
      font-size: 0.9rem;
    }
    .btn-repassword:hover {
      background-color: rgb(219, 219, 219);
    }
    .btn-close {
      position: absolute;
      right: 20px;
      top: 10px;
      opacity: 0.8;
    }
    .repassword-footer {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      justify-content: baseline;
      align-items: flex-end;
      width: 100%;
      margin-top: 12px;
      a:hover {
        opacity: 0.7;
      }
      .link-back {
        text-decoration: none;
        color: #ff5651;
      }
      .link-close {
        text-decoration: none;
        color: dodgerblue;
      }
    }
  }
}

hr {
  margin: 25px 0px;
}

.hide {
  display: none;
}
</style>
