<template>
<!--  <div id="nav">-->
<!--    <router-link to="/">Home</router-link> |-->
<!--    <router-link to="/about">About</router-link>-->
<!--  </div>-->
<!--  <router-view/>-->
  <a-layout>
    <the-header/>
    <router-view/>
    <a-layout-footer style="text-align: center">
      Codepedia ©2023 Created by Hansheng Guo
    </a-layout-footer>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import theHeader from "@/components/the-header.vue";

export default defineComponent({
  name: 'app',
  components: {
    theHeader,
  }
});
</script>

<style>
#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
