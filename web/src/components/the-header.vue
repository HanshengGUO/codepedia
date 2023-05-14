<template>
  <a-layout-header class="header">
    <div class="logo"/>
    <a class="login-menu" @click="showLoginModal">
      <span>Login</span>
    </a>
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">
        <router-link to="/">
          Content
        </router-link>
      </a-menu-item>
      <a-menu-item key="2">
        <router-link to="/admin/ebook">
          Admin
        </router-link>
      </a-menu-item>
      <a-menu-item key="3">
        <router-link to="/admin/user">
          User Center
        </router-link>
      </a-menu-item>
      <a-menu-item key="4">
        <router-link to="/admin/category">
          Category
        </router-link>
      </a-menu-item>
      <a-menu-item key="5">
        <router-link to="/about">
          about
        </router-link>
      </a-menu-item>
    </a-menu>
    <a-modal
        title="登录"
        v-model:visible="loginModalVisible"
        :confirm-loading="loginModalLoading"
        @ok="login"
    >
      <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="登录名">
          <a-input v-model:value="loginUser.loginName"/>
        </a-form-item>
        <a-form-item label="密码">
          <a-input v-model:value="loginUser.password" type="password"/>
        </a-form-item>
      </a-form>
    </a-modal>
  </a-layout-header>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';

export default defineComponent({
  name: 'the-header',

  setup() {
    const loginUser = ref({
      loginName: "test",
      password: "test"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("开始登录")
    };

    return {
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      loginUser,
      login
    }
  }
});
</script>

<style>
.login-menu {
  float: right;
  color: white;
}
</style>