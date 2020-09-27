<template>
  <div class="container-navbar-mini" v-if="$route.meta.header !== 0">
    <div class="item-list">
      <div class="item">
        <router-link tag="button" class="btn-icon" to="/" id="btn-home">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
            class="mini-menu-icon"
          >
            <path
              d="M10.6,23.2V16h4.8v7.2h6v-9.6H25L13,2.8L1,13.6h3.6v9.6H10.6z"
            />
          </svg>
          <div class="mini-menu-text">홈</div>
        </router-link>
      </div>
      <div class="item">
        <router-link
          tag="button"
          class="btn-icon"
          to="/main/follow"
          id="btn-follow"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
            class="mini-menu-icon"
          >
            <g>
              <polygon points="7.7,10.8 6.4,10.9 4.2,9 8.6,8.7 	" />
              <polygon points="19.8,15.8 21,21.3 17.7,19.3 17.4,17.9 	" />
              <polygon
                points="25,9 22.3,11.4 15.1,10.8 12.9,5.6 14.6,1.5 17.5,8.4 	"
              />
              <polygon
                points="19.4,14.3 16.1,17.2 16.4,18.5 17.8,24.5 11.4,20.6 5,24.5 6.7,17.2 1,12.3 7.3,11.7 8.5,11.6 9.8,8.6 11.4,4.7 
		12.3,6.9 14.3,11.6 21.3,12.2 21.8,12.3 	"
              />
            </g>
          </svg>
          <div class="mini-menu-text">팔로우</div>
        </router-link>
      </div>
      <div class="item">
        <router-link
          tag="button"
          class="btn-icon"
          to="/main/trending"
          id="btn-trending"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
            class="mini-menu-icon"
          >
            <path
              d="M9.7,25c0,0-0.1,0-0.1,0c-0.8-0.3-1.6-0.6-2.3-1.1c-1.6-1.1-2.8-2.6-3.3-4.5c-0.3-1.5-0.2-3,0.2-4.5
	c0.4-1.7,1.3-3.1,2.4-4.4c0.9-1.1,1.9-2,2.7-3.1c0.6-0.7,1.1-1.5,1.4-2.4c0.4-1.1,0.3-2.3,0-3.4c-0.1-0.5,0.1-0.7,0.6-0.6
	c1.2,0.2,2.2,0.7,3.1,1.5c1.3,1,2.1,2.3,2.1,4c0,0.8,0,1.7,0,2.5c0,0.4,0,0.7,0.2,1.1c0.1,0.2,0.2,0.4,0.4,0.4
	c0.4,0.1,1.1-0.1,1.2-0.7c0.1-0.4,0.1-0.8,0.1-1.2c0-0.1,0-0.3,0-0.4c0.1-0.3,0.4-0.4,0.7-0.2c0.1,0.1,0.1,0.1,0.2,0.2
	c0.8,1.2,1.5,2.5,2,3.9c0.6,1.6,0.9,3.2,0.9,4.9c0,0.8,0,1.7-0.2,2.5c-0.8,2.7-2.7,4.3-5.2,5.3c-0.2,0.1-0.4,0-0.5-0.1
	c-0.1-0.2-0.1-0.4,0.1-0.5c0.2-0.2,0.3-0.4,0.5-0.6c1.2-1.6,1.1-3.3-0.4-4.7c-0.4-0.3-0.7-0.7-1.1-1c-0.8-0.8-1.4-1.8-1.7-2.9
	c0-0.2-0.1-0.4-0.1-0.6c-0.1,0-0.1,0.1-0.2,0.2c-0.3,0.4-0.5,0.8-0.8,1.2c-0.7,0.9-1.5,1.7-2.2,2.5c-0.9,1.1-1.2,2.4-1,3.9
	c0.1,0.7,0.4,1.4,0.8,2C10.2,24.6,10.1,24.8,9.7,25z"
            />
          </svg>
          <div class="mini-menu-text">인기</div>
        </router-link>
      </div>
      <div class="item">
        <router-link
          tag="button"
          class="btn-icon"
          to="/main/history"
          id="btn-history"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
            class="mini-menu-icon"
          >
            <path
              d="M14.7,2.7C9,2.7,4.4,7.3,4.4,13H1l4.4,4.4l0.1,0.2l4.6-4.6H6.7c0-4.4,3.6-8,8-8s8,3.6,8,8s-3.6,8-8,8
	c-2.2,0-4.2-0.9-5.6-2.4l-1.6,1.6c1.9,1.9,4.4,3,7.3,3C20.4,23.3,25,18.7,25,13S20.4,2.7,14.7,2.7z M13.6,8.4v5.7l4.9,2.9l0.8-1.4
	l-4-2.4V8.4H13.6z"
            />
          </svg>
          <div class="mini-menu-text">방문 기록</div>
        </router-link>
      </div>
      <div class="item">
        <router-link
          tag="button"
          class="btn-icon"
          to="/main/library"
          id="btn-library"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
            class="mini-menu-icon"
          >
            <g>
              <g>
                <path
                  d="M10.3,3.2v1.7c-1-0.4-2.1-0.6-3.2-0.6C4.9,4.3,2.7,5.1,1,6.8l4.1,4.1h1.4v1.4c1.1,1.1,2.4,1.6,3.8,1.7v2.8H6.6v3.7
			c0,1.4,1.1,2.5,2.5,2.5h12.3c2,0,3.7-1.6,3.7-3.7v-16H10.3z M8.9,11V8.4H6.1L4.8,7.1c0.7-0.3,1.5-0.4,2.2-0.4
			c1.6,0,3.2,0.6,4.3,1.8l1.7,1.7l-0.2,0.2c-0.6,0.6-1.5,1-2.4,1C10,11.5,9.4,11.3,8.9,11z M22.5,19.1c0,0.7-0.6,1.2-1.2,1.2
			c-0.7,0-1.2-0.6-1.2-1.2v-2.5h-7.4v-3.2c0.7-0.3,1.4-0.7,1.9-1.3l0.2-0.2l3.5,3.5h1.7v-1.7l-7.4-7.3V5.6h9.8
			C22.5,5.6,22.5,19.1,22.5,19.1z"
                />
              </g>
            </g>
          </svg>

          <div class="mini-menu-text">보관함</div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      menuIcons: "",
      menuTexts: ""
    };
  },
  mounted() {
    /* this.menuIcons = document.querySelectorAll(".mini-menu-icon");
    this.menuTexts = document.querySelectorAll(".mini-menu-text");

    const url = document.URL.split("/");
    const curPage = url[url.length - 1];
    const id = `#btn-${curPage}`;
    const btn = document.querySelector(id);
    const e = { currentTarget: btn };
    console.log(btn);
    this.changeColor(e);*/
  },
  methods: {
    changeColor(e) {
      const curSvg = e.currentTarget.querySelector("svg");
      const curText = e.currentTarget.querySelector("div");
      this.menuIcons.forEach(icon => {
        icon.classList.remove("btn-selected");
      });
      this.menuTexts.forEach(text => {
        text.classList.remove("btn-selected");
      });

      curSvg.classList.add("btn-selected");
      curText.classList.add("btn-selected");
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";

.container-navbar-mini {
  position: fixed;
  flex-direction: row;
  bottom: 0;
  left: 0;
  width: 100vw;
  height: 62px;
  background-color: white;
  z-index: 1;
}
.item-list {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  width: auto;
  height: 100%;
  svg {
    width: 22px;
    height: 22px;
    fill: #555555;
  }
}
.item {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 74px;
  margin: 4px 0px;
  .btn-icon {
    width: 100%;
    height: 100%;
  }
  div {
    margin-top: 5px;
    font-size: 0.8em;
    color: rgb(129, 129, 129);
  }
  &:hover {
    background: rgb(218, 218, 218);
  }
}

@media (min-width: $threeArticles) {
  .container-navbar-mini {
    position: fixed;
    top: 56px;
    left: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 72px;
    height: 100%;
  }
  .item-list {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    width: 100%;
    margin: 4px 0px;
  }
}

.btn-selected {
  fill: #65b1fb !important;
  color: #65b1fb !important;
}
</style>
