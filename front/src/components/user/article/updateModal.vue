<template>
  <div ref="updateModal" class="container-update-modal hide">
    <div ref="updateModal" class="update-modal">
      <button class="btn-update" @click="updatePost">
        <span>글 수정</span>
      </button>
      <button class="btn-delete" @click="deletePost">
        <span>글 삭제</span>
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["articleId"],
  data: function() {
    return {
      isShow: true
    };
  },
  methods: {
    async deletePost() {
      if (confirm("정말 삭제 하시겠습니까?")) {
        try {
          await axios.delete(`${this.$apiServer}/post?id=${this.articleId}`);
          location.href = "/";
        } catch (error) {
          console.log(error);
        }
      }
    },
    updatePost() {
      this.$router.push({
        name: "ArticleEdit",
        params: { targetId: this.articleId }
      });
    },
    toggle() {
      this.$refs.updateModal.classList.toggle("hide");
    }
  },
  mounted() {
    const updateModal = this.$refs.updateModal;
    window.addEventListener("click", function(e) {
      if (
        !updateModal.contains(e.target) &&
        !e.target.classList.contains("btn-more")
      ) {
        updateModal.classList.add("hide");
      }
    });
  }
};
</script>

<style lang="scss" scoped>
.container-update-modal {
  position: relative;
  top: 14px;
  left: 30px;
  width: 0;
  height: 0;
  .update-modal {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: absolute;
    background-color: white;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.19), 0 1px 1px rgba(0, 0, 0, 0.23);
    width: 110px;
    height: 70px;
    border-radius: 2px;
    border: 1px solid #c0c0c0;
    button {
      font-weight: 500;
      color: #727272;
      padding: 7.5px 10px;
      width: 100%;

      &:hover {
        span {
          color: #c0c0c0;
        }
      }
    }
  }
}
.hide {
  display: none;
}
</style>
