<template>
  <div class="challengeList">
    <div class="title col-lg-4 py-3 mb-0">도전 중인 챌린지</div>
    <div class="left-0 py-2">
      <div class="contents">
        <div class="p-1 pb-3">
          <b-row>
            <b-col xl="3" md="6" v-for="(data, idx) in myDataArray" v-bind:key="idx" @click="detail(data.chlgNo)">
              <challenge-item 
                v-bind:joined= data.likeCnt
                type="gradient-red"
                v-bind:subTitle= data.title
                v-bind:img= data.img
                v-bind:startDate = data.startDate
                v-bind:endDate = data.endDate
                class="mb-3">
                <template v-slot:footer>
                </template>
              </challenge-item>
            </b-col>
          </b-row>
        </div>
      </div>
    </div>
    <div class="title col-lg-4 py-3 mb-0">전체 챌린지</div>
    <div class="left-0 py-2">
      <div class="contents">
        <div class="p-1 pb-3">
          <b-row>
            <b-col xl="3" md="6" v-for="(data, idx) in dataArray" v-bind:key="idx" @click="detail(data.chlgNo)">
              <challenge-item 
                v-bind:joined= data.likeCnt
                type="gradient-red"
                v-bind:subTitle= data.title
                v-bind:img= data.img
                v-bind:startDate = data.startDate
                v-bind:endDate = data.endDate
                class="mb-3">
                <template v-slot:footer>
                </template>
              </challenge-item>
            </b-col>
          </b-row>
        </div>
      </div>
    </div>
    <infinite-loading @infinite='infiniteHandler' spinner='waveDots'>
      <div 
        slot='no-more'
        style="color:rgb(102,102,102); font-size: 14px; padding:10px 0px;">
        목록의 끝입니다
      </div>
    </infinite-loading>
    <router-link to="/user/challenge/register">
      <img id='imgBtn' src="../../assets/images/plusBtn.png">
    </router-link>
  </div>
</template>

<script>
import axios from "axios";
import InfiniteLoading from 'vue-infinite-loading';
import ChallengeItem from "../../components/Challenge/ChallengeItem.vue";

const storage = localStorage;

export default {
  name: "Home",
  components: {
    InfiniteLoading,
    ChallengeItem
  },
  data() {
    return {
      dataArray: [],
      myDataArray: [],
      page: 0,
      number: 0,
      user: {
        no:"",
        id:"",
        name:"",
        email: '',
      },
    };
  },
  async mounted() {
    await axios.get('http://i5a105.p.ssafy.io:8080/account/userinfo',{
      params:{
        jwt: storage.getItem("jwt-token"),
      }
    }).then(res => {
      this.user.no = res.data.userNo;
      this.user.id = res.data.userId;
      this.user.name = res.data.userName;
      this.user.email = res.data.userEmail;
    }).catch(err=>{
      if(err.response.data == "expired"){
        this.$store.commit('SET_SNACKBAR', {
          text: "로그아웃 되었습니다.",
          color: '#8EC0E4',
          location: 'bottom',
        })
        storage.removeItem("jwt-token");
        this.$router.replace('/user/login');
      }
    });

    await axios
      .get('http://i5a105.p.ssafy.io:8080/challenge/user/' + this.user.no)
      .then(res => {
        for(let i=0; i<res.data.length; i++) {
          this.myDataArray.push(res.data[i]);
        }
      })
      .catch(err => {
        console.log(err);
      })
  },
  methods: {
    infiniteHandler($state){
      setTimeout(1000);
      axios
        .get('http://i5a105.p.ssafy.io:8080/challenge/list/' + this.page)
        .then(res => {
          if(res.data.content.length == 0){
            $state.complete();
          }
          else{
            setTimeout(() => {
              for(let i=0; i<10; i++)
              {
                if(res.data.content[i])
                  this.dataArray.push(res.data.content[i]);
              }
              this.page++;
              $state.loaded();
            },700)
            
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    detail(chlgNo) {
      this.$router.push({ name: 'challengeDetail', params: { chlgNo: chlgNo }});
    }
  },
}
</script>

<style>
#imgBtn {
  width : 50px;
  border-radius: 70px;
  position : fixed;
  right: 20px;
  bottom: 20px;
}
.box {
  background-color: white;
  border-radius: 1rem;
  margin: 1rem;
}
.title {
  background-color: #6AAFE6;
  text-align: center;
  color: white;
  display: inline-block;
  font-size: 1.2rem;
  font-weight: 800;
}
.contents {
  overflow: hidden;
}
</style>