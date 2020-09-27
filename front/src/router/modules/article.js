export default [
  {
    path: "edit",
    name: "ArticleEdit",
    component: () => import("@/views/user/ArticleEdit.vue")
  },
  {
    path: "view",
    name: "ArticleView",
    component: () => import("@/views/user/ArticleView.vue")
  }
];
