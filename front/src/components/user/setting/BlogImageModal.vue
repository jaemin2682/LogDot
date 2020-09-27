<template>
  <div class="modal" v-if="show">
    <div class="modal-content">
      <h1>배너 이미지 설정</h1>

      <div>
        <vue-dropzone
          ref="myVueDropzone"
          id="dropzone"
          @vdropzone-success="vfileUploaded"
          :options="dropzoneOptions"
        ></vue-dropzone>
      </div>

      <footer class="modal-footer">
        <button
          @click="$router.go()"
          class="success"
          :title="validImage ? '' : 'Image URL needs to be valid'"
          :disabled="!validImage"
        >
          이미지 추가
        </button>
        <button @click="show = false" class="danger">창 닫기</button>
      </footer>
    </div>
  </div>
</template>

<script>
import vue2Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  components: {
    vueDropzone: vue2Dropzone
  },
  data() {
    return {
      imageSrc: [],
      command: null,
      show: false,
      tab: 1,
      dropzoneOptions: {
        url: "https://httpbin.org/post",
        thumbnailWidth: 500,
        maxFilesize: 2,
        maxFiles: 1,
        dictDefaultMessage: "UPLOAD A FILE"
      }
    };
  },
  computed: {
    validImage() {
      let isValid = false;
      this.imageSrc.forEach(elem => {
        isValid = elem.match(/\.(jpeg|jpg|gif|png)$/) != null;
      });
      console.log(isValid);
      return isValid;
    }
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo"
    }),
    showModal() {
      this.show = true;
    },
    vfileExceeded(file) {
      if (file.size >= 2097152) {
        alert("2mb 미만의 사진을 업로드해 주세요.");
      }
    },
    async vfileUploaded(file) {
      let formData = new FormData();
      formData.append("filename", file);
      try {
        const res = await axios.put(
          `${this.$apiServer}/blog/upload?email=${this.getUserInfo().email}`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data"
            }
          }
        );
        if (res.status === 200) {
          this.imageSrc.push(res.data);
        }
      } catch (error) {
        console.log(error);
        return false;
      }
    },

    insertImage() {
      const data = {
        command: this.command,
        data: [
          //   {
          //     src: this.imageSrc
          //     // alt: "YOU CAN ADD ALT",
          //     // title: "YOU CAN ADD TITLE"
          //   }
        ]
      };
      this.imageSrc.forEach(elem => {
        data.data.push({ src: elem });
      });

      this.$emit("onConfirm", data);
      this.closeModal();
    },

    closeModal() {
      this.show = false;
      this.imageSrc = [];
      this.tab = 1;
    }
  }
};
</script>

<style lang="scss" scoped>
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  width: 90%;
  background-color: #fff;
  padding: 30px;
  border-radius: 8px;
  h1{
    margin-bottom: 20px;
  }
}

.modal-footer {
  margin-top: 20px;
}

label {
  display: block;
  margin: 0.25em 0;
}

button {
  font-family: inherit;
  font-size: 100%;
  padding: 0.5em 1em;
  color: white;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
  border: 1px solid #999;
  border: transparent;
  background-color: #e6e6e6;
  text-decoration: none;
  border-radius: 2px;
  cursor: pointer;
}

button.danger {
  background: rgb(202, 60, 60);
}

button.success {
  background: rgb(28, 184, 65);
}

button:disabled {
  opacity: 0.3;
}

button + button {
  margin-left: 10px;
}

.tab-header {
  display: flex;
  align-items: center;
  border-bottom: 1px solid #222;
}

.tab-header button {
  color: #222;
  background: none;
  border: 0;
  flex: 1;
  padding: 5px 10px;
  cursor: pointer;
}

.tab-header button.active {
  background-color: #222;
  color: #fff;
}
</style>
