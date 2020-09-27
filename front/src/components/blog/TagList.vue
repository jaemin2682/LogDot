<template>
  <div class="container-tag-navigation">
    <p class="list-title">Tags</p>
    <div class="list-items">
      <p class="list-item">
        <button class="btn-tag-all" @click="selectAll()">전체보기 ({{ totalcnt }})</button>
      </p>
      <p class="list-item" v-for="tag in tagData" :key="tag.id" @click="selectTag(tag[0])">
        <button>{{ tag[0] }} ({{ tag[1] }})</button>
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  name: "TagList",
  props: {
    email: String
  },
  data() {
    return {
      tagData: [],
      totalcnt: null
    };
  },
  methods: {
    fetchTags() {
      axios
        .get(`${SERVER_URL}/api/post/mycategory/taglist?email=${this.email}`)
        .then(res => {
          this.tagData = res.data;
        })
        .catch(err => console.log(err));
    },
    fetchTotalCnt() {
      axios
        .get(`${SERVER_URL}/api/post/mycategory/totcnt?email=${this.email}`)
        .then(res => {
          this.totalcnt = res.data;
        })
        .catch(err => console.log(err));
    },
    selectTag(tag) {
      this.$emit("select-tag", tag);
      this.changeMenu();
    },
    selectAll() {
      this.$emit("select-all");
      this.changeMenu();
    },
    changeMenu() {
      this.$emit("click-article");
      document.querySelector(".btn-article").style.borderBottom = "3px solid #727272";
      document.querySelector(".btn-Info").style.borderBottom = "none";
    }
  },
  mounted() {
    this.fetchTags();
    this.fetchTotalCnt();
  }
};
</script>

<style scoped lang="scss">
.container-tag-navigation {
  width: 180px;
  margin-top: 270px;
  margin-right: 24px;
  color: #727272;
  float: left;
  .list-title {
    margin: 12px 0px;
    padding-bottom: 12px;
    border-bottom: 2px solid rgb(202, 202, 202);
    text-align: right;
  }
  hr {
    width: 180px;
    margin: 0px;
  }
  .list-items {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-end;
    margin: 12px 0px;
    button {
      color: #727272;
      font-weight: 500;
      font-size: 1rem;
      &:hover {
        opacity: 0.7;
      }
      &:focus {
        color: dodgerblue;
      }
      
    }
    .btn-tag-all {
      font-weight: 550;
    }
  }
  p {
    text-align: left;
    margin: 3px 0px;
  }
}
</style>
