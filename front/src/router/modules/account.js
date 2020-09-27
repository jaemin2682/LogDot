export default [
  {
    path: "setNickname/:email/:token",
    name: "setNickname",
    component: () => import("@/views/user/SetNickname.vue"),
    meta: {
      header: 0,
    },
  },
  {
    path: "setNickname/:email",
    name: "setNicknameSocial",
    component: () => import("@/views/user/SetNickname.vue"),
    meta: {
      header: 0,
    },
  },
  {
    path: "resetpassword/:email",
    name: "resetPassword",
    component: () => import("@/views/user/RepasswordLink.vue"),
    meta: {
      header: 0,
    },
  },
  {
    path: "loginPassword/:email",
    name: "LoginPassword",
    component: () => import("@/views/user/LoginPassword.vue"),
    meta: {
      header: 0,
    },
  },
];
