<template>
  <div class="container-blog">
    <ImgModal ref="imgModal" />
    <header v-bind:style="{ backgroundImage: 'url(' + bannerImg + ')' }">
      <button class="banner-image-edit" @click="openImgModal">수정하기</button>
    </header>
    <main>
      <span class="rem2">블로그 이름</span>
      <textarea
        class="blog-title"
        v-model="title"
        placeholder="블로그 이름을 정해보세요."
      ></textarea>
      <span class="rem2">블로그 소개</span>
      <textarea
        class="blog-description"
        placeholder="블로그 소개를 해보세요."
        v-model="description"
      ></textarea>
      <div class="buttons">
        <button class="btn-submit" @click="updateBlogInfo">수정하기</button>
      </div>
    </main>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
import ImgModal from "@/components/user/setting/BlogImageModal.vue";

export default {
  data: function() {
    return {
      title: "",
      description: "",
      bannerImg: "",
    };
  },
  components: {
    ImgModal,
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
    }),
    async updateBlogInfo() {
      console.log(this.description);
      console.log(this.getUserInfo().email);
      console.log(this.title);
      try {
        await axios.post(`${this.$apiServer}/blog`, {
          description: this.description,
          email: this.getUserInfo().email,
          name: this.title,
        });
        alert("수정이 완료되었습니다.");
      } catch (error) {
        console.log(error);
      }
    },
    openImgModal() {
      this.$refs.imgModal.showModal();
    },
  },
  async created() {
    try {
      const res = await axios.get(
        `${this.$apiServer}/blog?email=${this.getUserInfo().email}`
      );
      console.dir(res.data);
      this.title = res.data.name;
      this.description = res.data.description;
      this.bannerImg = res.data.picture;
    } catch (error) {
      console.log(error);
    }
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/user/setting/Blog.scss";
</style>
