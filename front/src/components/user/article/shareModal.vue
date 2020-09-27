<template>
  <div class="container-share">
    <div class="modal-share ">
      <div class="btn-share">
        <button class="btn" @click="sendKakao">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            id="Bold"
            enable-background="new 0 0 32 32"
            viewBox="0 0 32 32"
            class=""
          >
            <g>
              <path
                d="m26 32h-20c-3.314 0-6-2.686-6-6v-20c0-3.314 2.686-6 6-6h20c3.314 0 6 2.686 6 6v20c0 3.314-2.686 6-6 6z"
                fill="#e3f8fa"
                data-original="#E3F8FA"
                class=""
                style="fill:#FFE529"
                data-old_color="#e3f8fa"
              />
              <path
                d="m14.308 14.204-.461 1.301h.922z"
                fill="#8ce1eb"
                data-original="#8CE1EB"
                class=""
                style="fill:#552F00"
                data-old_color="#8ce1eb"
              />
              <path
                d="m16 8.667c-4.418 0-8 2.805-8 6.266 0 2.237 1.497 4.2 3.75 5.309-.866 2.966-.889 2.98-.742 3.066.184.108.423-.003 
                                3.571-2.208.461.065.936.099 1.421.099 4.418 0 8-2.805 8-6.266s-3.582-6.266-8-6.266zm-3.962 8.015c0 
                                .241-.207.438-.462.438s-.462-.196-.462-.438v-2.724h-.72c-.25 0-.453-.201-.453-.449 0-.247.203-.449.453-.449h2.363c.25 0 
                                .453.201.453.449 0 .247-.203.449-.453.449h-.72v2.724zm3.586.432c-.48 0-.391-.377-.613-.797h-1.407c-.22.417-.133.797-.613.797-.462 
                                0-.543-.281-.403-.714l1.104-2.887c.078-.22.314-.446.615-.453.301.007.538.233.616.453.729 2.261 1.769 3.602.701 3.601zm2.626-.061h-1.481c-.772 
                                0-.302-1.043-.442-3.534 0-.253.211-.458.471-.458s.471.206.471.458v2.694h.981c.244 0 .442.189.442.42 0 
                                .232-.198.42-.442.42zm3.613-.345c-.017.121-.081.229-.179.302-.637.481-1.107-.921-1.729-1.514l-.16.159v1c0 .253-.207.458-.462.459-.255 
                                0-.462-.206-.462-.458v-3.136c0-.253.207-.458.462-.458s.462.206.462.458v.985c.851-.639 1.323-1.78 1.861-1.246.528.524-.565.982-1.018 1.614 1.069 1.475 
                                1.272 1.511 1.225 1.835z"
                fill="#26c6da"
                data-original="#26C6DA"
                class="active-path"
                style="fill:#552F00"
                data-old_color="#26c6da"
              />
            </g>
          </svg>
          <span>카카오톡</span>
        </button>
        <ShareNetwork 
          class="btn"
          network="facebook"
          :url="url"
          :title="articleData.title"
          :description="articleData.summary"
          :hashtags="articleData.tag[0]"
        >
          <svg
            @click="incrementScore"
            version="1.1"
            id="Capa_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 455.73 455.73"
            style="enable-background:new 0 0 455.73 455.73;"
            xml:space="preserve"
          >
            <path
              style="fill:#3A559F;"
              d="M0,0v455.73h242.704V279.691h-59.33v-71.864h59.33v-60.353c0-43.893,35.582-79.475,79.475-79.475
                            h62.025v64.622h-44.382c-13.947,0-25.254,11.307-25.254,25.254v49.953h68.521l-9.47,71.864h-59.051V455.73H455.73V0H0z"
            />
          </svg>
          <span>페이스북</span>
        </ShareNetwork>
        <ShareNetwork
          class="btn"
          network="twitter"
          :url="url"
          :title="articleData.title"
          :hashtags="articleData.tag[0]"
        >
          <svg
            @click="incrementScore"
            version="1.1"
            id="Capa_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 455.731 455.731"
            style="enable-background:new 0 0 455.731 455.731;"
            xml:space="preserve"
          >
            <g>
              <rect
                x="0"
                y="0"
                style="fill:#50ABF1;"
                width="455.731"
                height="455.731"
              />
              <path
                style="fill:#FFFFFF;"
                d="M60.377,337.822c30.33,19.236,66.308,30.368,104.875,30.368c108.349,0,196.18-87.841,196.18-196.18
                            c0-2.705-0.057-5.39-0.161-8.067c3.919-3.084,28.157-22.511,34.098-35c0,0-19.683,8.18-38.947,10.107
                            c-0.038,0-0.085,0.009-0.123,0.009c0,0,0.038-0.019,0.104-0.066c1.775-1.186,26.591-18.079,29.951-38.207
                            c0,0-13.922,7.431-33.415,13.932c-3.227,1.072-6.605,2.126-10.088,3.103c-12.565-13.41-30.425-21.78-50.25-21.78
                            c-38.027,0-68.841,30.805-68.841,68.803c0,5.362,0.617,10.581,1.784,15.592c-5.314-0.218-86.237-4.755-141.289-71.423
                            c0,0-32.902,44.917,19.607,91.105c0,0-15.962-0.636-29.733-8.864c0,0-5.058,54.416,54.407,68.329c0,0-11.701,4.432-30.368,1.272
                            c0,0,10.439,43.968,63.271,48.077c0,0-41.777,37.74-101.081,28.885L60.377,337.822z"
              />
            </g>
          </svg>
          <span>트위터</span>
        </ShareNetwork>
        <ShareNetwork
          class="btn"
          network="line"
          :title="articleData.title"
          :description="articleData.summary"
          :url="url"
          :hashtags="articleData.tag[0]"
        >
          <svg
            @click="incrementScore"
            version="1.1"
            id="Capa_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 455.731 455.731"
            style="enable-background:new 0 0 455.731 455.731;"
            xml:space="preserve"
          >
            <g>
              <rect
                x="0"
                y="0"
                style="fill:#00C200;"
                width="455.731"
                height="455.731"
              />
              <g>
                <path
                  style="fill:#FFFFFF;"
                  d="M393.272,219.605c0.766-4.035,1.145-7.43,1.319-10.093c0.288-4.395-0.04-10.92-0.157-12.963
                                    c-4.048-70.408-77.096-126.503-166.616-126.503c-92.118,0-166.793,59.397-166.793,132.667c0,67.346,63.088,122.97,144.821,131.526
                                    c4.997,0.523,8.6,5.034,8.046,10.027l-3.48,31.322c-0.79,7.11,6.562,12.283,13.005,9.173
                                    c69.054-33.326,110.346-67.611,135.004-97.314c4.487-5.405,19.118-25.904,22.101-31.288
                                    C386.854,244.729,391.219,232.455,393.272,219.605z"
                />
                <path
                  style="fill:#00C500;"
                  d="M136.103,229.588v-55.882c0-4.712-3.82-8.532-8.532-8.532h0c-4.712,0-8.532,3.82-8.532,8.532
                                    v64.414c0,4.712,3.82,8.532,8.532,8.532h34.127c4.712,0,8.532-3.82,8.532-8.532v0c0-4.712-3.82-8.532-8.532-8.532H136.103z"
                />
                <path
                  style="fill:#00C500;"
                  d="M188.731,246.652h-3.73c-3.682,0-6.667-2.985-6.667-6.667v-68.144c0-3.682,2.985-6.667,6.667-6.667
                                    h3.73c3.682,0,6.667,2.985,6.667,6.667v68.144C195.398,243.667,192.413,246.652,188.731,246.652z"
                />
                <path
                  style="fill:#00C500;"
                  d="M257.678,173.706v39.351c0,0-34.073-44.443-34.593-45.027c-1.628-1.827-4.027-2.951-6.69-2.85
                                    c-4.641,0.176-8.2,4.232-8.2,8.876v64.063c0,4.712,3.82,8.532,8.532,8.532h0c4.712,0,8.532-3.82,8.532-8.532v-39.112
                                    c0,0,34.591,44.83,35.099,45.312c1.509,1.428,3.536,2.312,5.773,2.332c4.738,0.043,8.611-4.148,8.611-8.886v-64.059
                                    c0-4.712-3.82-8.532-8.532-8.532h0C261.498,165.175,257.678,168.994,257.678,173.706z"
                />
                <path
                  style="fill:#00C500;"
                  d="M338.729,173.706L338.729,173.706c0-4.712-3.82-8.532-8.532-8.532h-34.127
                                    c-4.712,0-8.532,3.82-8.532,8.532v64.414c0,4.712,3.82,8.532,8.532,8.532h34.127c4.712,0,8.532-3.82,8.532-8.532v0
                                    c0-4.712-3.82-8.532-8.532-8.532h-25.595v-15.144h25.595c4.712,0,8.532-3.82,8.532-8.532v0c0-4.712-3.82-8.532-8.532-8.532
                                    h-25.595v-15.144h25.595C334.909,182.238,338.729,178.418,338.729,173.706z"
                />
              </g>
            </g>
          </svg>
          <span>라인</span>
        </ShareNetwork>
      </div>
      <div class="url-share">
        <div id="url">{{ url }}</div>
        <button @click="copyurl">URL 복사</button>
      </div>
    </div>
  </div>
</template>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import axios from "axios";
import { mapMutations, mapGetters } from "vuex";
const SERVER_URL = "http://i3a604.p.ssafy.io:8081";
export default {
  props: {
    articleData: {
      type: Object
    }
  },
  data: function() {
    return {
      url: window.location.href,
      commentCnt: ""
    };
  },
  async created() {
    const res = await axios.get(
      `${this.$apiServer}/comment/allComment?email=${this.articleData.email}&postid=${this.articleData.id}`
    );
    this.commentCnt = res.data.length;
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    sendKakao() {
      Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: this.articleData.title,
          description: this.articleData.summary,
          imageUrl: this.articleData.picture,
          link: {
            mobileWebUrl: this.url,
            androidExecParams: "test"
          }
        },
        social: {
          likeCount: this.articleData.good,
          commentCount: this.commentCnt,
          viewCount: this.articleData.views
        },
        buttons: [
          {
            title: "글 보러가기",
            link: {
              mobileWebUrl: this.url
            }
          },
          {
            title: "홈페이지",
            link: {
              mobileWebUrl: "https://developers.kakao.com"
            }
          }
        ]
      });
      this.incrementScore();
    },
    copyurl() {
      var t = document.createElement("textarea");
      document.body.appendChild(t);
      t.value = this.url;
      t.select();
      document.execCommand("copy");
      document.body.removeChild(t);
      alert("주소가 복사되었습니다.\n원하는 곳에 붙여넣기(Ctrl+V)해주세요.");
    },
    incrementScore() {
      axios.post(`
      ${this.$apiServer}/post/share?email=${this.getUserInfo().email}
      `);
    }
  }
};
</script>

<style scoped lang="scss">
.container-share {
  position: relative;
  top: 40px;
  left: -250px;
  width: 0;
  height: 0;
  .modal-share {
    background-color: white;
    width: 250px;
    border: 1px solid #727272;
    padding: 13px 10px;
  }
}
.btn-share {
  display: flex;
  height: 70px;
  margin-bottom: 15px;
  .btn {
    display: grid;
    width: 50px;
    height: 50px;
    margin: 4px;
    svg {
      width: 50px;
      height: 50px;
      padding: 7px;
      cursor: pointer;
    }
    span {
      font-size: 10px;
      text-align: center;
    }
  }
}
.url-share {
  width: 220px;
  display: flex;
  margin: 5px;
  #url {
    font-size: 12px;
    width: 170px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    border: 1px solid #727272;
    padding: 0 9px;
    line-height: 23px;
    color: #727272;
    background-color: #f0f0f0;
    margin-right: 1px;
  }
  button {
    width: 70px;
    border: 1px solid #727272;
    font-size: 12px;
  }
}
</style>
