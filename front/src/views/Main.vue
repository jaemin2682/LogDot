<template>
  <div class="main">
    <NavbarMini />
    <router-view />
  </div>
</template>

<script>
import NavbarMini from "@/components/common/Navbar-mini.vue";

export default {
  name: "Main",
  components: {
    NavbarMini
  }
};
</script>
