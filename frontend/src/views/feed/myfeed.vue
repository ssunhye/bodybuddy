<template>
<div>
    <div class="cover">
        <div class="title col-lg-4 py-3 mb-0">
            <b-icon icon="chevron-left" @click="goBack" class="left mt-1" font-scale="0.999999"></b-icon>My feed
        </div>
        <!-- Profile widget -->
        <div class="profile-image">
            <div class="box">
                <img class="profile" :src="userImg">
            </div>
        </div>
        <div class="user-info mb-5 text-white text-center">
            <h4 class="mt-0">{{ userName }}  | {{ userId }}</h4>
            <router-link :to="{name: 'UserSettings'}">
                <a class="mt-4 btn btn-block">Edit Profile</a>
            </router-link>
            
        </div>
    </div>
    <div class="px-4">
        <!-- 피드 돌면서 출력 -->
        <div 
            class="d-flex"
            v-for="(feed, idx) in feedList" v-bind:key = 'idx'
            >
            <div class="col-5 feed-box">
                <img 
                    class="feed-img"
                    :src = feed.feedImg
                >
            </div>
            <div class="feed-detail ml-2 mt-3">
                <h3>{{ feed.feedTitle }}</h3>
                <small>{{ feedDate[idx] }}</small>
                <p>{{ feed.feedText }}</p>
            </div>
        </div>
       <hr>

    </div>
    <router-link to="/user/feed/register">
      <img id='imgBtn' src="../../assets/images/plusBtn.png">
    </router-link>
</div>
</template>
<script>
import axios from "axios";
import InfiniteLoading from 'vue-infinite-loading';
import ChallengeItem from "../../components/Challenge/ChallengeItem.vue";

const moment = require('moment');
function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
 }

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
            page: 0,
            number: 0,
            userName: "",
            userId: "",
            userNo: "",
            userImg:"",
            feedList: [],
            feedDate: [],
        };
    },
    mounted() {
        axios.get('http://i5a105.p.ssafy.io:8080/account/userinfo',{
            params:{
                jwt: storage.getItem("jwt-token"),
            }
        }).then(res => {
            
            this.userName = res.data.userName;
            this.userId = res.data.userId;
            this.userNo = res.data.userNo;
            this.userImg = res.data.userImg;

            axios.get('http://i5a105.p.ssafy.io:8080/feed/{userId}', {
                params:{
                    userId: this.userId,
                }
            }).then(res => {
                this.feedList = res.data
                for(let i=0; i<res.data.length; i++) {
                    this.feedDate.push(timeForToday(res.data[i].feedDate));
                }
            })
            
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
    }
    
    /*methods: {
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
                    this.page ++;
                    $state.loaded();
                    console.log(res.data)
                    console.log(this.number)
                    },700)
                    
                }
            })
            .catch(err => {
                console.log(err);
            })
        }
    }*/
}
</script>
<style scoped>
.box {
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
}
.feed-box {
    width: 150px;
    height: 150px; 
    overflow: hidden;
}
.feed-img {
    padding-top: 1rem;
    width: 150px;
    height: 150px; 
    margin: auto;
}
.profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
    margin: auto;
}
.profile-image {
    padding-top: 1rem;
    width: 150px;
    height: 150px; 
    margin: auto;
}
.cover {
    background: #8EC0E4;
    background-size: cover;
    background-repeat: no-repeat;
    text-align: center;
}

p {
    font-size: 11pt;
}
.user-info {
    margin-top:2.5rem;
}

body {
    background: #D4DFE6;
    background: linear-gradient(to right, #CADBE9, #8EC0E4);
    min-height: 100vh;
    overflow-x: hidden
}

.btn {
    background-color: #D4DFE6;
    color: dimgrey;
    height: 90%;
}
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
