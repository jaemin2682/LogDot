import Vue from "vue";
import VueRouter from "vue-router";
import mainPageRouter from "@/router/modules/main.js";
import settingPageRouter from "@/router/modules/setting.js";
import accountPageRouter from "@/router/modules/account.js";
import articlePageRouter from "@/router/modules/article.js";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      redirect: "/main",
    },
    {
      path: "/main",
      redirect: "/main/home",
      name: "Main",
      component: () => import("@/views/Main.vue"),
      children: mainPageRouter,
    },
    {
      path: "/setting",
      redirect: "/setting/blog",
      name: "Setting",
      component: () => import("@/views/Setting.vue"),
      children: settingPageRouter,
    },
    {
      path: "/account",
      redirect: "/main/home",
      name: "Account",
      component: () => import("@/views/Main.vue"),
      children: accountPageRouter,
    },
    {
      path: "/article",
      redirect: "/article/edit",
      name: "Article",
      component: () => import("@/views/Article.vue"),
      children: articlePageRouter,
    },
    {
      path: "/:email",
      name: "Blog",
      component: () => import("@/views/blog/Blog.vue"),
    },
  ],
  // 스크롤 맨 위로
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
});

export default router;
