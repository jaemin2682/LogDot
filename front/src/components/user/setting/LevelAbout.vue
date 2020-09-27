!<template>
  <div class="container-levelabout hide">
    <div class="background-levelabout"></div>
    <div class="modal-levelabout">
      <div class="modal-head">
        <h3>포인트 제도</h3>
      </div>
      <div class="modal-body">
        <div class="container-points">
          <div>
            <h4>포스팅</h4>
            <h4>댓글</h4>
            <h4>게시물 공유</h4>
            <h4>좋아요</h4>
          </div>
          <div>
            <h4>100점</h4>
            <h4>4점</h4>
            <h4>4점</h4>
            <h4>2점</h4>
          </div>
        </div>
        <br />
        <p>※포인트 1일 활동 포인트 최대치 150</p>
      </div>
      <div class="modal-head">
        <h3>레벨업 기준</h3>
      </div>
      <div class="modal-body">
        <div class="container-points">
          <div class="container-levelicon">
            <LevelIcon :score="100" />
            <LevelIcon :score="500" />
            <LevelIcon :score="2000" />
            <LevelIcon :score="4000" />
            <LevelIcon :score="8000" />
            <LevelIcon :score="11000" />
          </div>
          <div class="levelup">
            <h4>초기 레벨</h4>
            <h4>200점 달성</h4>
            <h4>1000점 달성</h4>
            <h4>3000점 달성</h4>
            <h4>6000점 달성</h4>
            <h4>10000점 달성</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LevelIcon from "@/components/user/LevelIcon.vue";

export default {
  components: {
    LevelIcon,
  },
};
</script>

<style scoped lang="scss">
.container-levelabout {
  position: fixed;
  z-index: 3;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: flex-end;
  background-color: rgba(0, 0, 0, 0);
  .background-levelabout {
    position: fixed;
    width: 100vw;
    height: 100vh;
  }
  .modal-levelabout {
    filter: opacity(95%);
    margin: 0;
    border-radius: 5px;
    overflow: hidden;
    width: 280px;
    height: 430px;
    background: white;
    box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
    position: fixed;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    .modal-head {
      text-align: left;
      padding: 4px 12px;
      width: 100%;
      color: white;
      background-color: #65b1fb;
    }
    .modal-body {
      padding: 12px;
      margin-bottom: 20px;
    }
    .container-points {
      display: flex;
      flex-direction: row;
      margin: 12px 12px 0 12px;
      div {
        margin-right: 40px;
      }
      h4 {
        color: #727272;
        font-weight: 500;
        text-align: left;
      }
    }
    p {
      font-size: 0.8em;
      color: #ff6d6d;
    }
    .container-levelicon {
      margin: 0 !important;
    }
  }
}

.hide {
  display: none;
}

.container-arrow {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

#arrowIcon {
  width: 12px;
  height: 12px;
  fill: #65b1fb;
  display: block;
}
.levelup {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
