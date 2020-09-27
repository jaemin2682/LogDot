export default [
  {
    path: "blog",
    component: () => import("@/views/user/setting/Blog.vue")
  },
  {
    path: "account",
    component: () => import("@/views/user/setting/Account.vue")
  },
  {
    path: "alarm",
    component: () => import("@/views/user/setting/Alarm.vue")
  }
];
