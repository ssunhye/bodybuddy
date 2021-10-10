<template>
  <div class="feed newsfeed">
    <div class="title col-lg-4 py-3 mb-0">
      <b-icon icon="chevron-left" @click="goBack" class="left mt-1" font-scale="0.999999"></b-icon>뉴스피드
    </div>
    
    <div class="mt-3">
      <FeedItem 
        v-for="(item,idx) in reverse" :key="idx"
        :id="item.userId"
        :url="item.feedImg"
        :text="item.feedText"
        :like="item.feedLike"
        :com="item.feedCom"
        :date="item.feedDate"
        :title="item.feedTitle">
      </FeedItem>
    </div>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px">
        목록의 끝입니다 :)
      </div>
    </infinite-loading>
  </div>
</template>



<script>
import { mapState } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "../../components/feed/FeedItem.vue";

import axios from "axios";

// const api = '';
const storage = localStorage;

export default {
  components: {
    InfiniteLoading,
    FeedItem,
  },

  props: ["keyword"],
 data() {
    return {
      //limit : 0,
      list: [],
      feedinfo:[],
      reverse:[],
      page: 0,
      user: {
        no:"",
        id:"",
        name:"",
        email: "",
      },
    };
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
      }).catch(err=>{
          console.log(err);
          if(err.response.data == "expired")
          {
              this.$store.commit('SET_SNACKBAR', {
                text: "로그아웃 되었습니다.",
                color: '#8EC0E4',
                location: 'bottom',
              })
              storage.removeItem("jwt-token");
              this.$router.replace('/user/login');
          }
      });
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    infiniteHandler($state) {
      setTimeout(1000);
      axios.get('http://i5a105.p.ssafy.io:8080/feed/list/' + this.page,
        ).then( data  => {

        if(data.data.totalPages == this.page){
          $state.complete();
        }
        else
        {
          setTimeout(()=>{
            
            for(let i=0; i<5; i++)
            {
              if(data.data.content[i])
                this.feedinfo.push(data.data.content[i]);
            }
            this.reverse = [...this.feedinfo];
            this.page++;
            $state.loaded();
          }, 700)
        }
      }).catch(err=>{
        console.log(err);
      });
    },
  },
};

</script>
<style>
#imgBtn {
  width : 50px;
  border-radius: 70px;
  position : fixed;
  right: 20px;
  bottom: 20px;
}
.title {
  background-color: #6AAFE6;
  text-align: center;
  color: white;
  display: inline-block;
  font-size: 1.2rem;
  font-weight: 800;
}
</style>