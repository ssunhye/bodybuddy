<template>
  <div class="wrapper">
    <notifications></notifications>
    <side-bar>
      <template slot="links">
        <sidebar-item
          :link="{
            name: '운동기록',
            path: '/user/record',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
        </sidebar-item>

        <sidebar-item
            :link="{
              name: '챌린지',
              path: '/user/challenge',
              icon: 'ni ni-paper-diploma text-orange'
              }"
            >
        </sidebar-item>

        <sidebar-item
            :link="{
              name: '소식',
              path: '/user/feed',
              icon: 'ni ni-world-2 text-green'
              }"
            >
        </sidebar-item>

        <sidebar-item
              :link="{
                name: '내 정보 ',
                path: '/user/myfeed',
                icon: 'ni ni-single-02 text-yellow'
                }">
        </sidebar-item>
        <button @click="logout" style="margin-top: 10px"> 로그아웃 </button>

      </template>

    </side-bar>
    <div class="main-content">
      <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
    </div>
  </div>
</template>
<script>
  /* eslint-disable no-new */
  import PerfectScrollbar from 'perfect-scrollbar';
  import 'perfect-scrollbar/css/perfect-scrollbar.css';

  function hasElement(className) {
    return document.getElementsByClassName(className).length > 0;
  }

  function initScrollbar(className) {
    if (hasElement(className)) {
      new PerfectScrollbar(`.${className}`);
    } else {
      // try to init it later in case this component is loaded async
      setTimeout(() => {
        initScrollbar(className);
      }, 100);
    }
  }

  import ContentFooter from './ContentFooter.vue';
  import { FadeTransition } from 'vue2-transitions';

  export default {
    components: {
      ContentFooter,
      FadeTransition
    },
    methods: {
      initScrollbar() {
        let isWindows = navigator.platform.startsWith('Win');
        if (isWindows) {
          initScrollbar('sidenav');
        }
      },
      logout(){
        localStorage.removeItem("jwt-token");
        this.$router.replace('login')
        //여기에 더해 서버에서 access token, refresh token 파기 해야할듯(일단 보류)
      }
    },
    mounted() {
      this.initScrollbar()
    }
  };
</script>
<style lang="scss">
</style>
