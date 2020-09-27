<template>
  <div class="container__about">
    <h1><span class="element--yellow">파인애플 팀</span> 팀원 소개 🍍</h1>
    <div class="about__block">
      <div class="element__people">
        <img
          src="https://images.unsplash.com/photo-1554224311-beee415c201f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
          alt=""
        />
        <div class="people__userinfo">
          <span class="userinfo__name">김현대</span>
          <span class="userinfo__role">팀장</span>
          <hr />
          <span class="userinfo__description"
            >개발빼고 다 잘하는 기능인 😎
          </span>
        </div>
      </div>
      <div class="element__people">
        <img
          src="https://images.unsplash.com/photo-1536677075446-821da10993a6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
          alt=""
        />
        <div class="people__userinfo">
          <span class="userinfo__name">고유진</span>
          <span class="userinfo__role">백엔드 개발자</span>
          <hr />
          <span class="userinfo__description"
            >백&프론트 다 하는 든든한 개발자 😏
          </span>
        </div>
      </div>
      <div class="element__people">
        <img
          src="https://images.unsplash.com/photo-1543164888-462c8603cbd9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
          alt=""
        />
        <div class="people__userinfo">
          <span class="userinfo__name">김나연</span>
          <span class="userinfo__role">프론트 개발자</span>
          <hr />
          <span class="userinfo__description"
            >백엔드가 하고싶은 프론트 개발자 😥
          </span>
        </div>
      </div>

      <div class="element__people">
        <img
          src="https://images.unsplash.com/photo-1519003300449-424ad0405076?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
          alt=""
        />
        <div class="people__userinfo">
          <span class="userinfo__name">오재민</span>
          <span class="userinfo__role">백엔드 개발자</span>
          <hr />
          <span class="userinfo__description">기면증 환자 😴</span>
        </div>
      </div>
      <div class="element__people">
        <img
          src="https://images.unsplash.com/photo-1564815556761-17e4fbea35e9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
          alt=""
        />
        <div class="people__userinfo">
          <span class="userinfo__name">정무영</span>
          <span class="userinfo__role">프론트 개발자</span>
          <hr />
          <span class="userinfo__description"
            >눈치 보여서 개발하는 사람 🙄
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container__about {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  background-color: #fafafa;
  min-width: 100vw;
  min-height: 100vh;
  padding-top: 56px;
  h1 {
    margin: 50px 0px;
    .element--yellow {
      color: rgb(255, 255, 117);
      text-shadow: 1px 1px 2px peru;
    }
  }
  .about__block {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    width: 100%;
    height: 500px;
    padding-left: 60px;
    .element__people {
      display: flex;
      align-items: center;
      width: 580px;
      height: 200px;
      border-radius: 10px;
      padding: 15px;
      margin: 20px;
      box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.19), 0 1px 1px rgba(0, 0, 0, 0.23);
      img {
        width: 150px;
        height: 150px;
        object-fit: cover;
        border-radius: 50%;
        margin-right: 20px;
      }
      .people__userinfo {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        width: 100%;
        .userinfo__name {
          font-size: 1.1em;
          margin-bottom: 5px;
          font-weight: 900;
        }
        hr {
          border: 0;
          clear: both;
          display: block;
          width: 100%;
          background-color: #ebebeb;
          height: 1px;
        }
        .userinfo__role {
          color: rgb(177, 177, 177);
          font-weight: 500;
        }
        .userinfo__description {
          color: rgb(177, 177, 177);
          text-align: left;
        }
      }
    }
  }
}
</style>
