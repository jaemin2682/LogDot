<template>
  <div class="container-emailsent hide">
    <div class="background-emailsent"></div>
    <div class="modal-emailsent">
      <div class="left-emailsent"></div>
      <div class="right-emailsent">
        <!-- <button class="btn-close" @click="closeLogin">✖</button> -->
        <div class="title">
          <h1>이메일 발송</h1>
        </div>
        <div class="modal-emailsent-body">
          <div>회원가입이 완료 되었습니다.</div>
          <div>
            계정 활성화를 위한 메일이
            <span class="span-red">가입하신 이메일주소</span>로 발송되었습니다.
          </div>
          <div>계정을 활성화 해주세요.</div>
        </div>
        <div class="emailsent-footer">
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
  name: "EmailModal",
  methods: {
    closeModal() {
      document.querySelector(".container-emailsent").classList.add("hide");
    }
  }
};
</script>

<style scoped lang="scss">
/** base */

$minimumWidth: 950px;

.container-emailsent {
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

.background-emailsent {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-emailsent {
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
  .modal-emailsent-body {
    .span-red {
      color: red;
    }
    div {
      text-align: left;
      margin: 12px 0;
    }
  }
  .left-emailsent {
    display: none;
    width: 42%;
    background-image: url(https://cdn.pixabay.com/photo/2015/09/04/23/28/wordpress-923188_960_720.jpg);
    background-repeat: no-repeat;
    background-size: cover;
    @media (min-width: $minimumWidth) {
      display: block;
    }
  }
  .right-emailsent {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 24px;
    width: 100%;
    @media (min-width: $minimumWidth) {
      width: 58%;
    }

    .btn-close {
      position: absolute;
      right: 20px;
      top: 10px;
      opacity: 0.8;
    }

    .emailsent-footer {
      display: flex;
      flex-direction: column;
      justify-content: right;
      justify-content: baseline;
      align-items: flex-end;
      width: 100%;
      margin-top: 12px;
      a:hover {
        opacity: 0.7;
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
