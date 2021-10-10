<template>
  <div class="feed-item">
    <div class="top">
      <!-- <div class="profile-image" :style="{'background-image': 'url(https://bodybuddy.s3.ap-northeast-2.amazonaws.com/feed/2021-08-12T06:58:46.053ssafyNew Wireframe 1.png)'}"></div> -->
      <div class="user-info">
        <div class="user-name mb-1">
          <button>{{id}}</button>
        </div>
        <h5 class="right mr-3">{{date}}</h5>
      </div>
      
    </div>
    <div class="feed-card">
      <div class="img" :style="{'background-image': 'url('+convert+')'}"></div>
    </div>
    <div class="content">
        <h4>{{title}}</h4>
        <p>{{text}}</p>
    </div>
  </div>
</template>

<script>
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";
const moment = require('moment');

export default {
  data: () => {
    return {
      defaultImage, 
      defaultProfile,
    };
  },
  props:["id", "url", "text", "like", "com", "date", "title"],
  computed:{
    convert(){
      console.log(this.url);
      return this.url;
    },
    dateconvert(){
      return this.date.substr(0, 10);
    },
    calDate(){
      return 
    }
  },
  methods:{
    
  },
  mounted() {
    this.date = moment(this.date).format('YYYY년 MM월 DD일 HH시 mm분');
  }
};
</script>
