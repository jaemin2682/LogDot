<template>
  <div class="viewer" :class="{ 'viewer--flexable': isPreview }">
    <div class="viewer__header">
      <div class="title-line">
        <h1 type="text" class="view-title" placeholder="제목" readonly>
          {{ isPreview ? previewData.title : articleData.title }}
        </h1>
        <UpdateModal :articleId="postId" ref="updateModal" />
        <button
          class="btn-more"
          @click="toggleUpdateModal"
          v-if="getUserInfo().email === articleData.writer"
          :disabled="isPreview"
        >
          ...
        </button>
      </div>
      <div class="article-info">
        <div class="user-info">
          <button @click="$router.push(`/${articleData.writer}`)">
            <img class="img-profile" :src="articleData.profile" />
            <span>{{ articleData.nickname }}</span>
          </button>
        </div>
        <span class="create-date">· {{ createDate }}</span>
      </div>

      <div class="viewer-tags">
        <button
          class="btn-tag"
          :key="idx"
          v-for="(tag, idx) in isPreview
            ? previewData.tagList
            : articleData.tag"
          @click="$router.push(`/main/search?tag=${tag}`)"
        >
          #{{ tag }}
        </button>
      </div>
      <div class="article-nav" v-if="!isPreview">
        <div ref="navContent" class="article-nav-content">
          <span>책갈피</span>
        </div>
      </div>
      <div class="introduction">
        <img :src="thumbnail" v-if="thumbnail !== ''" />
        <div ref="defaultThumbnail" class="default-thumbnail" v-else>
          {{ isPreview ? previewData.title : articleData.title }}
        </div>
      </div>
    </div>
    <editor-content
      ref="editorContent"
      class="editor__content"
      :editor="editor"
    />
    <template v-if="!isPreview">
      <div class="container-small-buttons">
        <div class="small-buttons">
          <button class="btn-like" @click="toggleLikeBtn">
            <svg
              class="icon-like"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
            >
              <path
                d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z"
              />
            </svg>
            <span>{{ like }}</span>
          </button>
          <button
            class="btn-share"
            @click="isShowShareModal = !isShowShareModal"
          >
            <svg viewBox="0 0 512 512.00578" xmlns="http://www.w3.org/2000/svg">
              <path
                d="m507.523438 148.890625-138.667969-144c-4.523438-4.691406-11.457031-6.164063-17.492188-3.734375-6.058593 2.453125-10.027343 
              8.320312-10.027343 14.847656v69.335938h-5.332032c-114.6875 0-208 93.3125-208 208v32c0 7.421875 5.226563 13.609375 12.457032 15.296875 1.175781.296875 
              2.347656.425781 3.519531.425781 6.039062 0 11.820312-3.542969 14.613281-9.109375 29.996094-60.011719 90.304688-97.28125 
              157.398438-97.28125h25.34375v69.332031c0 6.53125 3.96875 12.398438 10.027343 14.828125 5.996094 2.453125 12.96875.960938 
              17.492188-3.734375l138.667969-144c5.972656-6.207031 5.972656-15.976562 0-22.207031zm0 0"
              />
              <path
                d="m448.003906 512.003906h-384c-35.285156 0-63.99999975-28.710937-63.99999975-64v-298.664062c0-35.285156 28.71484375-64 63.99999975-64h64c11.796875 
              0 21.332032 9.535156 21.332032 21.332031s-9.535157 21.332031-21.332032 21.332031h-64c-11.777344 0-21.335937 9.558594-21.335937 21.335938v298.664062c0 
              11.777344 9.558593 21.335938 21.335937 21.335938h384c11.773438 0 21.332032-9.558594 21.332032-21.335938v-170.664062c0-11.796875 9.535156-21.335938 
              21.332031-21.335938 11.800781 0 21.335937 9.539063 21.335937 21.335938v170.664062c0 35.289063-28.714844 64-64 64zm0 0"
              />
            </svg>
          </button>
          <button class="btn-library" @click="toggleLibraryBtn">
            <svg
              class="icon-library"
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              viewBox="0 0 26 26"
            >
              <g>
                <g>
                  <path
                    d="M24.4,4l-1.9-2.2C22.2,1.3,21.6,1,21,1H5C4.4,1,3.8,1.3,3.5,1.7L1.6,4C1.2,4.4,1,5,1,5.7v16.7C1,23.8,2.2,25,3.7,25h18.7
	c1.5,0,2.7-1.2,2.7-2.7V5.7C25,5,24.8,4.4,24.4,4z M13,20.3L5.7,13h4.7v-2.7h5.3V13h4.7L13,20.3z M3.8,3.7l1.1-1.3h16l1.3,1.3H3.8z"
                  />
                </g>
              </g>
            </svg>
          </button>
        </div>
        <ShareModal v-if="isShowShareModal" :articleData="articleData" />
      </div>
      <BlogInfo :articleData="articleData" />
    </template>

    <template v-if="!isPreview">
      <Comment @reRender="reRender" :key="commentModuleKey" :postId="postId" />
    </template>
  </div>
</template>

<script>
import { Editor, EditorContent } from "tiptap";
import { mapGetters } from "vuex";
import axios from "axios";
import javascript from "highlight.js/lib/languages/javascript";
import css from "highlight.js/lib/languages/css";
import java from "highlight.js/lib/languages/java";
import json from "highlight.js/lib/languages/json";
import markdown from "highlight.js/lib/languages/markdown";
import python from "highlight.js/lib/languages/python";
import ruby from "highlight.js/lib/languages/ruby";
import swift from "highlight.js/lib/languages/swift";
import cpp from "highlight.js/lib/languages/cpp";
import cs from "highlight.js/lib/languages/cs";
import Comment from "./CommentModule.vue";
import BlogInfo from "./BlogInfo.vue";
import UpdateModal from "./updateModal.vue";
import ShareModal from "./shareModal.vue";

import {
  CodeBlockHighlight,
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History,
  Image
} from "tiptap-extensions";
export default {
  props: ["isPreview", "previewData"],
  components: {
    EditorContent,
    Comment,
    BlogInfo,
    UpdateModal,
    ShareModal
  },
  data() {
    return {
      editor: new Editor({
        isPreview: false,
        editable: false,
        extensions: [
          new CodeBlockHighlight({
            languages: {
              javascript,
              css,
              java,
              json,
              markdown,
              python,
              ruby,
              swift,
              cpp,
              cs
            }
          }),
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History(),
          new Image()
        ],

        content: "",
        onUpdate: ({ getHTML }) => {
          this.html = getHTML();
        }
      }),
      postId: "",
      createDate: "",
      thumbnail: "",
      like: 0,
      isLike: false,
      commentModuleKey: 0,
      articleData: {},
      isStored: false,
      isShowShareModal: false
    };
  },
  methods: {
    ...mapGetters({
      getUserInfo: "user/getUserInfo",
      getIsLogin: "user/getIsLogin"
    }),
    updatePreviewContent(content) {
      this.$refs.editorContent.editor.view.dom.innerHTML = content;
    },
    reRender() {
      this.commentModuleKey++;
    },
    toggleUpdateModal() {
      this.$refs.updateModal.toggle();
    },
    async getArticleData() {
      const articleId = (this.postId = this.$route.query.id);
      const email = this.getUserInfo().email;

      try {
        const res = await axios.get(
          `${this.$apiServer}/post/postDetail?email=${email}&id=${articleId}`
        );

        if (res.status === 200) {
          const articleData = res.data;
          this.articleData = articleData;
          this.createDate = articleData.createdate.split(" ")[0];
          this.editor.setContent(articleData.content);
          this.thumbnail = articleData.picture;
          this.like = articleData.good;
          this.isLike = articleData.isgood;
          this.isStored = articleData.isstore;
          this.setLikeBtn();
          this.setLibraryBtn();
        }
      } catch (error) {
        console.log(error);
      }
    },
    async getPreviewData() {
      // 글쓴이 정보
      try {
        const { data } = await axios.get(
          `${this.$apiServer}/auth/userInfo?email=${this.getUserInfo().email}`
        );
        this.articleData.nickname = data.nickname;
        this.articleData.profile = data.profile;
        this.articleData.score = data.score;
        this.articleData.writer = data.email;
      } catch (error) {
        console.log(error);
      }

      // 글쓴이 블로그 정보
      try {
        const { data } = await axios.get(
          `${this.$apiServer}/blog?email=${this.getUserInfo().email}`
        );

        this.articleData.blogName = data.name;
        this.articleData.blogDescription = data.description;
        this.articleData.blogPicture = data.picture;
      } catch (error) {
        console.log(error);
      }

      // 블로그 팔로워 수
      try {
        const { data } = await axios.get(
          `${this.$apiServer}/follow/cnt/${this.getUserInfo().email}`
        );

        this.articleData.followerNum = data;
      } catch (error) {
        console.log(error);
      }
      this.articleData.title = this.previewData.title;
      this.editor.setContent(this.previewData.content);
      this.articleData.tag = this.previewData.tagList;
      this.createDate = new Date().toLocaleDateString();
    },
    initNav() {
      if (this.isPreview) return;
      const navContent = this.$refs.navContent;
      document.addEventListener("scroll", function() {
        const yOffset = window.pageYOffset;
        if (yOffset > 0) {
          navContent.setAttribute("style", "position: fixed; top: 340px;");
        } else {
          navContent.removeAttribute("style");
        }
      });
      this.setNavContent();
    },
    setNavContent() {
      let id = window.setInterval(() => {
        // 컨텐츠 불러오는 속도를 기다려줘야 함
        if (this.articleData.content !== "") {
          window.clearInterval(id);
          const hList = document.querySelectorAll(
            ".editor__content h1, .editor__content h2, .editor__content h3"
          );

          hList.forEach(elem => {
            elem.id = elem.innerText;
          });

          this.setNavAnchor(hList);
        }
      }, 300);
    },
    setNavAnchor(tagList) {
      const navContent = this.$refs.navContent;
      tagList.forEach(elem => {
        const anchor = document.createElement("a");
        anchor.classList.add(elem.tagName);
        anchor.setAttribute("href", `#${elem.id}`);
        anchor.innerText = elem.innerText;
        navContent.appendChild(anchor);
      });
    },
    modifyAnchorDest() {
      window.addEventListener("hashchange", function() {
        window.scrollTo(window.scrollX, window.scrollY - 100);
      });
    },
    setDefaultThumbnail() {
      const thumbnail = this.$refs.defaultThumbnail;
      const R = Math.random() * 155 + 100;
      const G = Math.random() * 155 + 100;
      const B = Math.random() * 155 + 100;
      thumbnail.setAttribute(
        "style",
        `background-color: rgb(${R}, ${G}, ${B});`
      );
    },
    setLikeBtn() {
      const likeIcon = document.querySelector(".icon-like");
      if (this.isLike) {
        likeIcon.classList.add("fill-lightred");
      } else {
        likeIcon.classList.remove("fill-lightred");
      }
    },
    toggleLikeBtn() {
      if (this.getIsLogin() === false) {
        alert("게시물이 마음에 드시나요? 로그인하여 의견을 알려주세요.");
        return;
      }
      this.isLike = !this.isLike;
      if (this.isLike) {
        axios.put(
          `${this.$apiServer}/post/good?email=${this.getUserInfo().email}&id=${
            this.$route.query.id
          }`,
          {}
        );
        this.like++;
      } else {
        axios
          .put(
            `${this.$apiServer}/post/ungood?email=${
              this.getUserInfo().email
            }&id=${this.$route.query.id}`
          )
          .then(res => {
            console.log(res);
          })
          .catch(err => console.log(err));
        this.like--;
      }
      this.setLikeBtn();
    },
    setLibraryBtn() {
      const libraryIcon = document.querySelector(".icon-library");
      if (this.isStored) {
        libraryIcon.classList.add("fill-blue");
      } else {
        libraryIcon.classList.remove("fill-blue");
      }
    },
    toggleLibraryBtn() {
      this.isStored = !this.isStored;
      if (this.isStored) {
        axios
          .post(
            `${this.$apiServer}/post/storage?email=${
              this.getUserInfo().email
            }&postid=${this.$route.query.id}`
          )
          .then(() => alert("보관함에 등록되었습니다."))
          .catch((err) => console.log(err));
      } else {
        axios
          .delete(
            `${this.$apiServer}/post/storage?email=${
              this.getUserInfo().email
            }&postid=${this.$route.query.id}`
          )
          .then(res => console.log(res))
          .catch(err => console.log(err));
      }
      this.setLibraryBtn();
    }
  },
  beforeDestroy() {
    this.editor.destroy();
  },

  mounted() {
    this.setDefaultThumbnail();
    this.modifyAnchorDest();
    this.initNav();
  },
  async created() {
    if (this.isPreview) {
      await this.getPreviewData();
    } else {
      await this.getArticleData();
    }
  }
};
</script>

<style lang="scss" scope>
@import "@/assets/sass/main.scss";
@import "@/assets/sass/viewer.scss";
</style>
