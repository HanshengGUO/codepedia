<template>
  <a-layout-header class="header">
    <div class="logo">Codepedia</div>
    <a-popconfirm
        title="确认退出登录?"
        ok-text="是"
        cancel-text="否"
        @confirm="logout()"
    >
      <a class="login-menu" v-show="user.id">
        <span>   退出登录</span>
      </a>
    </a-popconfirm>
    <a class="login-menu" v-show="user.id">
      <span>您好：{{ user.name }}</span>
    </a>
    <a class="login-menu" v-show="!user.id" @click="showLoginModal">
      <span>登录</span>
    </a>
    <a-menu
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">
        <router-link to="/">
          Content
        </router-link>
      </a-menu-item>
      <a-menu-item key="2" :disabled="!user.id">
        <router-link to="/admin/ebook">
          Ebook Admin
        </router-link>
      </a-menu-item>
      <a-menu-item key="3" :disabled="!user.id">
        <router-link to="/admin/user">
          User Center
        </router-link>
      </a-menu-item>
      <a-menu-item key="4" :disabled="!user.id">
        <router-link to="/admin/category">
          Category Admin
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
import {computed, defineComponent, ref} from 'vue';
import axios from 'axios';
import {message} from 'ant-design-vue';
import store from "@/store";

declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({
  name: 'the-header',

  setup() {
    // 登录后保存
    const user = computed(() => store.state.user);

    // 用来登录
    const loginUser = ref({
      loginName: "test1",
      password: "11111111"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("开始登录");
      loginModalLoading.value = true;
      loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      axios.post('/user/login', loginUser.value).then((response) => {
        loginModalLoading.value = false;
        const data = response.data;
        if (data.success) {
          loginModalVisible.value = false;
          message.success("登录成功！");
          store.commit("setUser", data.content);
        } else {
          message.error(data.message);
        }
      });
    };

    // 退出登录
    const logout = () => {
      console.log("退出登录开始");
      axios.get('/user/logout/' + user.value.token).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("退出登录成功！");
          store.commit("setUser", {});
        } else {
          message.error(data.message);
        }
      });
    };

    return {
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      loginUser,
      login,
      user,
      logout,
    }
  }
});
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Golos+Text&family=Kanit&display=swap');

.login-menu {
  float: right;
  color: white;
  padding-left: 10px;
}

.logo {
  width: 160px;
  height: 31px;
  /*background: rgba(255, 255, 255, 0.2);*/
  margin: 0px 0px 0px 20px;
  float: left;
  color: white;
  font-size: 24px;
  font-family: 'Golos Text', sans-serif;
}
</style>