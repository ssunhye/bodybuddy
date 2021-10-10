<template>
    <nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
        <div class="container-fluid">

            <!--Toggler-->
            <navbar-toggle-button @click.native="showSidebar">
                
            </navbar-toggle-button>
            <router-link class="navbar-brand" to="/user/record">
                <img src="../../assets/images/logo.png" class="navbar-brand-img" alt="...">
            </router-link>

            <slot name="mobile-right">
                <ul class="nav align-items-center d-md-none">
                    <base-dropdown class="nav-item" menu-on-right tag="li" title-tag="a">
                        <a slot="title-container" class="nav-link" href="#" role="button">
                            <div class="media align-items-center">
                              <span class="avatar avatar-sm rounded-circle">
                                <img alt="Image placeholder" :src="user.img">
                              </span>
                            </div>
                        </a>

                        <div class=" dropdown-header noti-title">
                            <h2 class="text-overflow m-0" style="display:inline;"><strong>{{user.name}} </strong></h2><h4 style="display:inline;">님 안녕하세요 😀</h4>
                        </div>
                        <router-link to="/user/myfeed" class="dropdown-item">
                            <i class="ni ni-single-02"></i>
                            <span>My profile</span>
                        </router-link>
                        <!-- <router-link to="/user/usersetting" class="dropdown-item">
                            <i class="ni ni-settings-gear-65"></i>
                            <span>Settings</span>
                        </router-link> -->
                        <router-link to="/user/record" class="dropdown-item">
                            <i class="ni ni-calendar-grid-58"></i>
                            <span>Activity</span>
                        </router-link>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item" @click="logout">
                            <i class="ni ni-user-run"></i>
                            <span>Logout</span>
                        </a>
                    </base-dropdown>
                </ul>
            </slot>
            <slot></slot>
            <div v-show="$sidebar.showSidebar" class="navbar-collapse collapse show" id="sidenav-collapse-main">

                <div class="navbar-collapse-header d-md-none">
                    <div class="row">
                        <div class="col-6 collapse-brand">
                            <router-link to="/user/record">
                                <img src="../../assets/images/logo.png">
                            </router-link>
                        </div>
                        <div class="col-6 collapse-close">
                            <navbar-toggle-button @click.native="closeSidebar"></navbar-toggle-button>
                        </div>
                    </div>
                </div>

                <ul class="navbar-nav">
                    <slot name="links">
                    </slot>
                </ul>
                
            </div>
            </div>
    </nav>
</template>
<script>
  import axios from "axios";
  import NavbarToggleButton from '@/components/NavbarToggleButton'
  const storage = localStorage;

  export default {
    name: 'sidebar',
    components: {
      NavbarToggleButton
    },
    data() {
      return {
        user: {
          no:"",
          id:"",
          name:"",
          email: '',
          img:'',
        },
      }
    },
    props: {
      autoClose: {
        type: Boolean,
        default: true,
        description: 'Whether sidebar should autoclose on mobile when clicking an item'
      }
    },
    mounted() {
      axios.get('http://i5a105.p.ssafy.io:8080/account/userinfo',{
        params:{
          jwt: storage.getItem("jwt-token"),
        }
      }).then(res => {
        this.user.no = res.data.userNo;
        this.user.id = res.data.userId;
        this.user.name = res.data.userName;
        this.user.email = res.data.userEmail;
        this.user.img = res.data.userImg;
      }).catch(err=>{
        console.log(err);
      });
    },
    provide() {
      return {
        autoClose: this.autoClose
      };
    },
    methods: {
      closeSidebar() {
        this.$sidebar.displaySidebar(false)
      },
      showSidebar() {
        this.$sidebar.displaySidebar(true)
      },
      logout(){
        localStorage.removeItem("jwt-token");
        this.$router.replace('login')
        //여기에 더해 서버에서 access token, refresh token 파기 해야할듯(일단 보류)
      }
    },
    beforeDestroy() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.showSidebar = false;
      }
    }
  };
</script>
