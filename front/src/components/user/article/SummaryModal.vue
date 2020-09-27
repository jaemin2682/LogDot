<template>
  <div class="summary-modal" v-if="show">
    <div class="overlay"></div>
    <div class="summary-content">
      <h5>글 미리보기</h5>
      <main>
        <button class="thumbnail">
          <svg
            class="svg-thumbnail"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
          >
            <title>paginate-filter-picture-alternate</title>
            <circle cx="9.75" cy="6.247" r="2.25" />
            <path
              d="M16.916,8.71A1.027,1.027,0,0,0,16,8.158a1.007,1.007,0,0,0-.892.586L13.55,12.178a.249.249,0,0,1-.422.053l-.82-1.024a1,1,0,0,0-.813-.376,1.007,1.007,0,0,0-.787.426L7.59,15.71A.5.5,0,0,0,8,16.5H20a.5.5,0,0,0,.425-.237.5.5,0,0,0,.022-.486Z"
            />
            <path
              d="M22,0H5.5a2,2,0,0,0-2,2V18.5a2,2,0,0,0,2,2H22a2,2,0,0,0,2-2V2A2,2,0,0,0,22,0Zm-.145,18.354a.5.5,0,0,1-.354.146H6a.5.5,0,0,1-.5-.5V2.5A.5.5,0,0,1,6,2H21.5a.5.5,0,0,1,.5.5V18A.5.5,0,0,1,21.855,18.351Z"
            />
            <path
              d="M19.5,22H2.5a.5.5,0,0,1-.5-.5V4.5a1,1,0,0,0-2,0V22a2,2,0,0,0,2,2H19.5a1,1,0,0,0,0-2Z"
            />
          </svg>
          <img ref="thumbImg" :src="thumbSrc" alt class="thumb-img" />
          <button class="btn-thumbnail">
            <input type="file" ref="myFiles" @change="getThumbImage" />
          </button>
        </button>
        <h5>{{ articleData.title }}</h5>
        <textarea
          :maxlength="maxSummary"
          class="content-article"
          v-model="articleData.summary"
          placeholder="게시물을 간단하게 소개해 주세요."
        ></textarea>
        <span class="char-limit"
          >{{ articleData.summary.length }}/{{ maxSummary }}</span
        >
      </main>
      <footer>
        <button class="btn btn-cancel" @click="closeModal">취소</button>
        <button class="btn btn-complete" @click="submit">작성완료</button>
      </footer>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["isEdit"],
  data: function() {
    return {
      show: false,
      thumbSrc: "",
      maxSummary: 100,
      articleData: ""
    };
  },
  methods: {
    showModal(articleData) {
      this.articleData = articleData;
      this.show = true;
      if (articleData.picture !== "") {
        this.thumbSrc = articleData.picture;
      }
    },
    closeModal() {
      this.show = false;
    },
    getThumbImage() {
      const file = this.$refs.myFiles.files[0];

      if (file.size >= 307200) {
        alert("300kb 미만의 사진을 사용해주세요.");
        return;
      }
      if (FileReader && file) {
        const fr = new FileReader();
        fr.onload = function() {
          document.querySelector(".thumb-img").src = fr.result;
        };
        fr.readAsDataURL(file);
      }
    },
    async createPost() {
      try {
        const result = await this.sendImg();
        if (result) {
          axios
            .post(`${this.$apiServer}/post?${this.articleData.tagString}`, {
              content: this.articleData.content,
              picture: this.articleData.picture,
              summary: this.articleData.summary,
              title: this.articleData.title,
              writer: this.articleData.writer
            })
            .then(() => {
              alert("글작성이 완료되었습니다.");
              location.href = "/";
            })
            .catch(err => {
              console.log(err);
            });
        }
      } catch (error) {
        console.log(error);
      }
    },
    async updatePost() {
      try {
        const result = await this.sendImg();
        if (result) {
          axios
            .put(`${this.$apiServer}/post?${this.articleData.tagString}`, {
              content: this.articleData.content,
              picture: this.articleData.picture,
              summary: this.articleData.summary,
              title: this.articleData.title,
              writer: this.articleData.writer,
              id: this.articleData.id,
              good: this.articleData.good,
              views: this.articleData.views
            })
            .then(() => {
              alert("글수정이 완료되었습니다.");
              location.href = "/";
            })
            .catch(err => {
              console.log(err);
            });
        }
      } catch (error) {
        console.log(error);
      }
    },
    async submit() {
      if (this.isEdit) {
        this.updatePost();
      } else {
        this.createPost();
      }
    },
    async sendImg() {
      const imgFile = this.$refs.myFiles.files[0];
      if (imgFile === undefined) return true;
      let formData = new FormData();
      formData.append("filename", imgFile);
      try {
        const res = await axios.put(
          `${this.$apiServer}/post/upload`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data"
            }
          }
        );

        if (res.status === 200) {
          this.articleData.picture = res.data;
          return true;
        }
        return false;
      } catch (error) {
        console.log(error);
        return false;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/_variables.scss";

.summary-modal {
  position: fixed;
  left: 0;
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 100vw;
  min-height: 100vh;
  z-index: 10000;
}

.overlay {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.2);
  min-width: 100vw;
  min-height: 100vh;
}

.summary-content {
  position: absolute;
  background-color: $bgColor;
  width: 350px;
  padding: 15px 25px;
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  border-radius: 5px;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.thumbnail {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 300px;
  height: 160px;
  margin-bottom: 30px;
  background-color: rgba(216, 216, 216, 0.7);
  border-radius: 5px;
  svg {
    position: absolute;
    width: 120px;
    height: 120px;
    fill: rgb(247, 247, 247);
  }

  .hide {
    display: none;
  }

  img {
    width: 300px;
    height: 160px;
    border-radius: 5px;
    z-index: 1;
  }
}

.btn-thumbnail {
  position: absolute;
  width: 300px;
  height: 160px;
  background-color: black;
  opacity: 0;
  color: white;
  font-size: 2em;
  font-weight: 900;
  z-index: 2;
  &:hover {
    opacity: 0.5;
  }
  input {
    width: 100%;
    height: 100%;
    cursor: pointer;
    opacity: 0;
    border-radius: 5px;
  }
}
h5 {
  width: 100%;
  font-weight: 700;
  text-align: left;
  margin-bottom: 10px;
}
.content-article {
  width: 100%;
  height: 160px;
  padding: 10px;
  text-align: left;
  background-color: white;
  resize: none;
  box-shadow: rgba(0, 0, 0, 0.03) 0px 0px 4px 0px;
  line-height: 1.5;
  font-size: 0.8rem;
  border-width: initial;
  border-style: none;
  border-color: initial;
  border-radius: 5px;
  outline: none;
  padding: 0.75rem 1rem;
}
.char-limit {
  width: 100%;
  text-align: right;
  font-size: 0.7em;
  color: rgb(172, 172, 172);
  margin-top: 4px;
  margin-right: 10px;
}

footer {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}

.btn {
  padding: 8px 10px;
  &:hover {
    opacity: 0.7;
  }
}

.btn-cancel {
  color: #727272;
}
.btn-complete {
  color: dodgerblue;
  margin-left: 10px;
}
</style>
