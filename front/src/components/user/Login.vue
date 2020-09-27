<template>
  <div class="container-login hide">
    <div class="background-login" @click="closeLogin"></div>
    <div class="modal-login">
      <div class="left-login"></div>
      <div class="right-login">
        <button class="btn-close" @click="closeLogin">✖</button>
        <div class="title">
          <h1>LOG . IN</h1>
          <span>로그도트 서비스를 이용하시려면 로그인 해 주세요.</span>
        </div>

        <form class="container-login-form" @submit.prevent="login">
          <label for="email">이메일 주소</label>
          <input
            required
            name="email"
            type="email"
            placeholder="이메일 주소를 입력해 주세요"
            v-model="userInfo.email"
            autocomplete="email"
          />
          <div class="password-label">
            <label for="password">비밀번호</label>
          </div>
          <input
            required
            name="password"
            type="password"
            minlength="8"
            placeholder="비밀번호를 입력해 주세요"
            v-model="userInfo.password"
            autocomplete="current-password"
          />
          <button class="btn btn-login" type="submit">로그인</button>
        </form>
        <div class="social-login">
          <div class="social-title">
            <span>소셜 계정으로 로그인</span>
          </div>
          <div class="social-body">
            <a
              href="http://i3a604.p.ssafy.io:8081/oauth2/authorization/github"
              class=" btn btn-github"
            >
              <svg
                class="logo-github"
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                viewBox="0 0 24 24"
              >
                <path
                  d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"
                />
              </svg>
            </a>
            <a
              href="http://i3a604.p.ssafy.io:8081/oauth2/authorization/google"
              class=" btn btn-google"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                version="1.1"
                viewBox="0 0 512 512"
                style="enable-background:new 0 0 512 512;"
                xml:space="preserve"
                width="50px"
                height="50px"
              >
                <g>
                  <circle
                    style="fill:#FFFFFF"
                    cx="256"
                    cy="256"
                    r="256"
                    data-original="#273B7A"
                    class="active-path"
                    data-old_color="#273B7A"
                  />
                  <path
                    style="fill:#FFFFFF"
                    d="M506.239,310.165L349.717,153.641l-130.493,10.416l-66.658,60.85l8.554,132.367l148.202,149.147  C407.63,485.591,485.086,408.35,506.239,310.165z"
                    data-original="#121149"
                    class=""
                    data-old_color="#121149"
                  />
                  <path
                    style="fill:#4285F4;"
                    d="M261.584,232.469v49.055h76.717c-10.88,35.116-43.608,60.623-82.298,60.623  c-47.578,0-86.149-38.569-86.149-86.149c-0.002-47.576,38.567-86.147,86.145-86.147c22.235,0,42.436,8.497,57.72,22.326  l35.997-38.538c-24.693-22.619-57.589-36.431-93.717-36.431c-76.652,0-138.792,62.138-138.792,138.792S179.347,394.792,256,394.792  S394.792,332.653,394.792,256c0-8.03-0.741-15.879-2.06-23.531H261.584z"
                    data-original="#4285F4"
                    class=""
                  />
                  <path
                    style="fill:#EA4335;"
                    d="M256,169.853c22.235,0,42.436,8.497,57.72,22.326l35.997-38.538  c-24.693-22.621-57.589-36.433-93.717-36.433c-53.157,0-99.311,29.896-122.625,73.778l42.356,33.921  C188.213,192.717,219.401,169.853,256,169.853z"
                    data-original="#EA4335"
                    class=""
                  />
                  <path
                    style="fill:#FBBC05;"
                    d="M169.855,256c0-10.978,2.132-21.437,5.877-31.092l-42.356-33.921  c-10.305,19.397-16.167,41.517-16.167,65.014c0,23.605,5.911,45.825,16.305,65.286l42.105-34.504  C171.951,277.209,169.855,266.861,169.855,256z"
                    data-original="#FBBC05"
                  />
                  <path
                    style="fill:#34A853"
                    d="M303.895,327.547c-13.702,9.192-30.153,14.601-47.892,14.601c-36.719,0-67.986-23.012-80.386-55.367  l-42.105,34.504c23.362,43.735,69.441,73.506,122.487,73.506c34.451,0,65.943-12.579,90.207-33.358L303.895,327.547z"
                    data-original="#34A853"
                    class=""
                    data-old_color="#34A853"
                  />
                  <path
                    style="fill:#4285F4;"
                    d="M394.792,256c0-8.03-0.741-15.879-2.06-23.531H261.584v49.055h76.717  c-5.891,19.016-18.225,35.166-34.406,46.023l42.312,33.887C375.932,335.977,394.792,298.203,394.792,256z"
                    data-original="#4285F4"
                    class=""
                  />
                </g>
              </svg>
            </a>
            <a
              href="http://i3a604.p.ssafy.io:8081/oauth2/authorization/naver"
              class=" btn btn-naver"
            >
              <svg 
              enable-background="new 0 0 512 512" 
              height="50px" 
              id="Layer_1" 
              version="1.1" 
              viewBox="0 0 512 512" 
              width="50px" 
              xml:space="preserve" 
              xmlns="http://www.w3.org/2000/svg" 
              xmlns:xlink="http://www.w3.org/1999/xlink"><g>
                <circle cx="255.999" cy="256" r="246.455"  style="fill:#00C500"/></g>
                <g><path d="M124.152,139.41h91.746c0,0,83.332,125.871,85.793,129.246c2.52,3.41,2.812,0,2.812,0   
                c-3.844-19.477-8.016-28.219-8.016-59.379V139.41h91.359v233.18h-91.359c0,0-81.621-119.156-84.082-122.449   
                c-2.473-3.316-2.801,0-2.801,0c3.141,16.078,5.918,18.762,5.918,46.688v75.762h-91.371V139.41L124.152,139.41z" fill="#FFFFFF"/></g>
              </svg>
            </a>
          </div>
        </div>
        <div class="login-footer">
          <a
            class="link-repassword"
            href="javascript:void(0)"
            @click="moveToRepassword"
            >비밀번호를 잊으셨나요?</a
          >
          <a class="link-join" href="javascript:void(0)" @click="moveToJoin"
            >계정 만들기</a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      userInfo: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapGetters({
      getIsLogin: "user/getIsLogin",
      getEmail: "user/getEmail",
      getProfile: "user/getProfile",
    }),
    ...mapActions({
      Login: "user/login",
      fetchUserInfo: "user/fetchUserInfo",
    }),
    closeLogin() {
      this.email = "";
      this.password = "";
      document.querySelector(".container-login").classList.add("hide");
    },
    moveToJoin() {
      this.closeLogin();
      document.querySelector(".container-join").classList.remove("hide");
    },
    moveToRepassword() {
      this.closeLogin();
      document.querySelector(".container-repassword").classList.remove("hide");
    },
    async login() {
      try {
        const res = await axios.get(
          `${this.$apiServer}/auth/userInfo?email=${this.userInfo.email}`
        );
        if (res.data === "") {
          alert("등록되지 않은 이메일 입니다.");
          return;
        } else if (res.data.usercertification === 0) {
          alert("이메일 인증이 완료되지 않았습니다.");
          return;
        }
      } catch (error) {
        console.log(error);
      }

      try {
        const result = await this.Login(this.userInfo);
        if (result) {
          await this.fetchUserInfo(this.getEmail());
          this.$router.go();
        } else {
          alert("비밀번호가 틀립니다.");
        }
      } catch (error) {
        console.log(error);
      }
    },
    // socialLogin(platform){
    //   document.querySelector(".container-login").classList.add("hide");
    //     axios.get(
    //         `${SERVER_URL}/oauth2/authorize/${platform}`
    //     )
    // }
  },
};
</script>

<style scoped lang="scss">
/** base */

$minimumWidth: 950px;

.container-login {
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

.background-login {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-login {
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
  .left-login {
    display: none;
    width: 45%;
    background-image: url(https://cdn.pixabay.com/photo/2015/09/04/23/28/wordpress-923188_960_720.jpg);
    background-repeat: no-repeat;
    background-size: cover;
    @media (min-width: $minimumWidth) {
      display: block;
    }
  }
  .right-login {
    display: flex;
    flex-direction: column;
    padding: 24px;
    width: 100%;
    @media (min-width: $minimumWidth) {
      width: 55%;
    }
    /** login form */
    .container-login-form {
      display: flex;
      height: 100%;
      flex-direction: column;
      justify-content: center;
      align-content: flex-start;
    }
    label {
      text-align: left;
      margin-bottom: 8px;
    }

    input {
      background-color: rgb(0, 0, 0, 0) !important;
      border: 1px solid #cccccc;
      border-radius: 5px;
      padding: 8px 5px;
      margin-bottom: 16px;
    }
    input[type="password"] {
      margin-bottom: 24px;
    }
    input:focus {
      outline: none !important;
      border: 1px solid cadetblue;
      box-shadow: 0 0 2px cadetblue;
    }
    .password-label {
      display: flex;
      align-items: baseline;
      .repassword {
        margin-left: auto;
        font-size: 0.8em;
      }
    }

    /** social login form */
    .social-login {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
      width: 100%;
      .social-body {
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        width: 100%;
        margin: 16px 0px;
        .btn {
          display: flex;
          justify-content: center;
          align-items: center;
          border-radius: 50%;
          border: none;
          width: 50px;
          height: 50px;
        }
        .btn-github {
          background-color: #383538;
        }
        .btn-github:hover {
          background-color: #000000;
        }
        .logo-github {
          float: left;
          fill: white;
        }
        .btn-facebook {
          background-color: #2b4683;
        }
        .btn-facebook:hover {
          background-color: #1743ac;
        }
        .logo-facebook {
          float: left;
          fill: white;
        }
      }
    }

    .btn-login {
      width: 100%;
      padding: 8px 10px;
      color: white;
      border-radius: 5px;
      background-color: #6da7ff;
      font-weight: 500;
      font-size: 0.9rem;
    }
    .btn-login:hover {
      background-color: rgb(219, 219, 219);
    }
    .btn-close {
      position: absolute;
      right: 20px;
      top: 10px;
      opacity: 0.8;
    }
    .join-link {
      margin-top: 10px;
      text-align: center;
      font-size: 0.9em;
    }

    .login-footer {
      display: flex;
      justify-content: space-between;
      justify-content: baseline;
      margin-top: 12px;
      a:hover {
        opacity: 0.7;
      }
      .link-repassword {
        text-decoration: none;
        color: #ff5651;
      }
      .link-join {
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
