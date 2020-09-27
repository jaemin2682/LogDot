<template>
  <div class="container-agreement hide">
    <div class="background-agreement" @click="closeModal"></div>
    <div class="modal-agreement">
      <div class="left-agreement"></div>
      <div class="right-agreement">
        <div class="title">
          <h3>개인정보 수집 및 이용 방침</h3>
        </div>
        <div class="modal-body">
          1. 수집하는 개인정보 항목<br />
          - 필수정보의 수집 : 회원가입 시 이용자의 이메일 주소, 비밀번호를
          수집하고 있습니다.<br />
          서비스 이용과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수
          있습니다.<br />
          o IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록,
          기기정보<br />
          <br />
          2. 개인정보의 수집<br />
          • 이용목적<br />
          1) 회원으로 가입한 이용자를 식별하고 가입의사 확인, 불량회원의 부정한
          이용을 방지하기 위하여 사용합니다.<br />
          2) 이용자에게 로그도트의 다양한 서비스를 제공하고 서비스 이용 과정에서
          이용자의 문의사항이나 불만을 처리하고 공지사항 등을 전달하기 위해
          사용합니다.<br />
          3) 이용자와 약속한 서비스를 제공하기 위해 사용됩니다.<br />
          4) 신규 서비스가 개발되거나 이벤트 행사 시 참여기회를 알리기 위한 정보
          전달 및 마케팅 및 광고 등에도 사용됩니다.<br />
          5) 맞춤형 서비스 제공 및 신규 서비스 개발 등에 참고하기 위하여 이를
          익명화 하여, 이용패턴과 접속빈도 분석 및 서비스 이용에 대한 통계에서도
          사용됩니다.<br />
          <br />
          3. 개인정보의 보유 • 이용기간<br />
          로그도트는 이용자의 개인정보를 회원가입을 하는 시점부터 서비스를
          제공하는 기간 동안에만 제한적으로 이용하고 있습니다. 이용자가
          회원탈퇴를 요청하거나 제공한 개인정보의 수집 및 이용에 대한 동의를
          철회하는 경우, 또는 수집 및 이용목적이 달성되거나 보유 및 이용기간이
          종료한 경우 해당 이용자의 개인정보는 지체 없이 파기 됩니다. 그리고
          관계법령의 규정에 따라 아래와 같이 관계 법령에서 정한 일정한 기간 동안
          회원정보를 보관합니다.<br />
          1) 서비스 이용 관련 개인정보 (로그인기록)/보존 근거 :
          통신비밀보호법/보존 기간 : 3개월<br />
          2) 표시/광고에 관한 기록/보존 근거 : 전자상거래 등에서의 소비자보호에
          관한 법률/보존 기간 : 6개월
        </div>

        <div class="agreement-footer">
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
export default {
  methods: {
    closeModal() {
      document.querySelector(".container-agreement").classList.add("hide");
    },
    moveToLogin() {
      document.querySelector(".container-join").classList.remove("hide");
      this.closeModal();
    }
  }
};
</script>

<style scoped lang="scss">
/** base */

$minimumWidth: 950px;

.container-agreement {
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

.background-agreement {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-agreement {
  display: flex;
  position: fixed;
  width: 400px;
  height: 520px;
  background-color: rgb(252, 252, 252);
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  @media (min-width: $minimumWidth) {
    width: 840px;
  }
  .title {
    text-align: left;
    margin-bottom: 24px;
    h1 {
      margin-bottom: 12px;
    }
  }
  .left-agreement {
    display: none;
    width: 42%;
    background-image: url(https://cdn.pixabay.com/photo/2015/09/04/23/28/wordpress-923188_960_720.jpg);
    background-repeat: no-repeat;
    background-size: cover;
    @media (min-width: $minimumWidth) {
      display: block;
    }
  }
  .right-agreement {
    display: flex;
    flex-direction: column;
    padding: 24px;
    width: 100%;
    @media (min-width: $minimumWidth) {
      width: 58%;
    }
    .modal-body {
      padding: 12px 12px;
      overflow-y: scroll;
      border: 1px solid #c0c0c0;
      text-align: left;
      font-size: 0.8em;
      ::-webkit-scrollbar-track {
        background: white;
      }
    }
    /** agreement form */
    .container-agreement-form {
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

    .btn-agreement {
      width: 100%;
      padding: 8px 10px;
      color: white;
      border-radius: 5px;
      background-color: #6da7ff;
      font-weight: 500;
      font-size: 0.9rem;
    }
    .btn-agreement:hover {
      background-color: rgb(219, 219, 219);
    }
    .btn-close {
      position: absolute;
      right: 20px;
      top: 10px;
      opacity: 0.8;
    }
    .agreement-footer {
      bottom: 0;
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
