<template>
    <div class="ChallengeDetail">
        <div class="title col-lg-4 py-3 mb-0">
            <b-icon icon="chevron-left" @click="goBack" class="left mt-1" font-scale="0.999999"></b-icon>{{challenge.title}}</div>
        <div class="box">
            <div class="left-0  py-3 px-3">
                <div class="img">
                    <img :src = challenge.img width="300px" height="300">
                </div>
                <div class="ml-2 mt-3 mr-2">{{ challenge.content }}</div>
                <hr class="mt-3 mb-3">
                <div class="info">
                    <div>
                        <h5 class="card-title text-uppercase text-muted ml-2 mb-2" v-if="challenge.startDate" style="display:inline-block">{{challenge.startDate}} ~ {{challenge.endDate}}</h5>
                        <span class="ml-2 mr-2 right red"><strong>{{ challenge.diffDate }}</strong> 🏃‍♂️</span>
                    </div>
                    <span class="ml-2 mb-4 left"><strong>🧡{{ challenge.likeCnt }}명 참여 중</strong></span>
                </div>
                <div id="joinBtn" class="join-button p-1 mt-4" v-if="challenge.isOpen" v-on:click="joinChallenge()"><a class="btn">참여하기</a></div>
                <div id="joinBtn" class="join-button p-1" v-if="!challenge.isOpen"><a class="btn disabled">참여 기간이 아닙니다.</a></div>
            </div>
        </div>

        <!-- ranking -->
        <div class="rank px-4 px-lg-5 py-4 row justify-content-center">
            <div class="col">
                <div class="card">
                    <img v-if="challengers[0]" class="card-img" :src=challengers[0].img alt="..." />
                    <img v-else class="card-img" src="https://dummyimage.com/400x400/D4DFE6/4f4f4f&text=your+challenge" alt="..." />
                    <div class="card-body">
                        <h5 class="name fw-bolder text-center mt-1" v-if="challengers[0]">🥇 {{ challengers[0].user.userId }}</h5>
                        <h5 class="name fw-bolder text-center" v-else>명예의 전당이<br>비어있습니다🙄</h5>
                        <!-- 좋아요 -->
                        <div v-if="challengers[0]" class="d-flex justify-content-between mx-3">
                            <div style="font-size: 75%;">{{ challengers[0].score }}명이 좋아합니다.</div>
                            <button v-if="isJoin[0]" class="m-0 p-0" v-on:click="clickHeartDown(user.no, challengers[0].chlgDetailNo, 0)">🧡</button>
                            <b-icon v-if="!isJoin[0]" icon="heart" v-on:click="clickHeartUp(user.no, challengers[0].chlgDetailNo, 0)" class="m-0 p-0" font-scale="1.3"></b-icon>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img v-if="challengers[1]" class="card-img" :src=challengers[1].img alt="..." />
                    <img v-else class="card-img" src="https://dummyimage.com/400x400/D4DFE6/4f4f4f&text=your+challenge" alt="..." />
                    <div class="card-body">
                        <h5 class="name fw-bolder text-center" v-if="challengers[1]">🥈 {{ challengers[1].user.userId }}</h5>
                        <h5 class="name fw-bolder text-center" v-else>명예의 전당이<br>비어있습니다🙄</h5>
                        <!-- 좋아요 -->
                        <div v-if="challengers[1]" class="d-flex justify-content-between mx-3">
                            <div style="font-size: 75%;">{{ challengers[1].score }}명이 좋아합니다.</div>
                            <button v-if="isJoin[1]" class="m-0 p-0" v-on:click="clickHeartDown(user.no, challengers[1].chlgDetailNo, 1)">🧡</button>
                            <b-icon v-if="!isJoin[1]" icon="heart" v-on:click="clickHeartUp(user.no, challengers[1].chlgDetailNo, 1)" class="m-0 p-0" font-scale="1.3"></b-icon>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img v-if="challengers[2]" class="card-img" :src=challengers[2].img alt="..." />
                    <img v-else class="card-img" src="https://dummyimage.com/400x400/D4DFE6/4f4f4f&text=your+challenge" alt="..." />
                    <div class="card-body">
                        <h5 class="name fw-bolder text-center" v-if="challengers[2]">🥉 {{ challengers[2].user.userId }}</h5>
                        <h5 class="name fw-bolder text-center" v-else>명예의 전당이<br>비어있습니다🙄</h5>
                        <!-- 좋아요 -->
                        <div v-if="challengers[2]" class="d-flex justify-content-between mx-3">
                            <div style="font-size: 75%;">{{ challengers[2].score }}명이 좋아합니다.</div>
                            <button v-if="isJoin[2]" class="m-0 p-0" v-on:click="clickHeartDown(user.no, challengers[2].chlgDetailNo, 2)">🧡</button>
                            <b-icon v-if="!isJoin[2]" icon="heart" v-on:click="clickHeartUp(user.no, challengers[2].chlgDetailNo, 2)" class="m-0 p-0" font-scale="1.3"></b-icon>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="post container">
            <div class="row py-4 justify-content-center px-0">
                
                <!-- v-for로 db 돌면서 사진 이름 보여주기-->
                <div class="col-4 px-1" v-for="(challenger, idx) in challengers" v-bind:key = 'idx'>

                    <div class="card pb-2 mb-3" v-if="idx > 2">
                        <div class="img-box">
                            <img class="challenger-img" :src=challenger.img alt="..." />
                        </div>
                        <div class="card-body">
                            <h5 class="name fw-bolder text-center">{{ challenger.user.userId }}</h5>
                            <!-- 좋아요 -->
                            <div class="d-flex justify-content-between mx-3">
                                <!-- 좋아요 리스트에 있는 chlgDetailNo이면 빨간색으로, 아니면 검정색으로 -->
                                <div id="heart-count-1" style="font-size: 80%;">{{ challenger.score }}명이 좋아합니다.</div>
                                <button v-if="isJoin[idx]" class="m-0 p-0" v-on:click="clickHeartDown(user.no, challenger.chlgDetailNo, idx)">🧡</button>
                                <b-icon v-if="!isJoin[idx]" icon="heart" v-on:click="clickHeartUp(user.no, challenger.chlgDetailNo, idx)" class="m-0 p-0" font-scale="1.3"></b-icon>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>


<script src="https://kit.fontawesome.com/caed28bd65.js" crossorigin="anonymous"></script>
<script>
import axios from "axios";
import InfiniteLoading from 'vue-infinite-loading'

const moment = require('moment'); 
const storage = localStorage;

export default {
    name: "ChallengeDetail",
    components: {
        InfiniteLoading
    },
    data() {
        return {
            user: {
                no:"",
                id:"",
                name:"",
                email: "",
            },
            challenge: {
                chlgNo:"",
                title:"",
                userNo:"",
                content:"",
                likeCnt:"",
                img:"",
                startDate:"",
                endDate:"",
                diffDate:"",
                isOpen:"",
            },
            challengers: [],
            votes:[],
            isJoin: [],

        };
    },
    created() {
        this.challenge.chlgNo = this.$route.params.chlgNo;
    },
    computed: { 
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        joinChallenge() {
            this.$router.push({ name: 'joinChallenge', params: { chlgNo: this.challenge.chlgNo }});
        },
        clickHeartUp(userNo, chlgDetailNo, idx) {
            axios.post('http://i5a105.p.ssafy.io:8080/vote/up', {
                chlgDetailNo: chlgDetailNo,
                userNo: userNo
            })
            .then(res => {
                this.isJoin[idx] = true;
                this.challengers[idx].score = res.data;
            })
        },
        clickHeartDown(userNo, chlgDetailNo, idx) {
            axios.delete('http://i5a105.p.ssafy.io:8080/vote/down', {
                params: {
                    chlgDetailNo: chlgDetailNo,
                    userNo: userNo
                }
            })
            .then(res => {
                this.isJoin[idx] = false;
                this.challengers[idx].score = res.data;
            })
        }
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

        await axios.get('http://i5a105.p.ssafy.io:8080/challenge/' + this.challenge.chlgNo)
            .then(res => {
                this.challenge.title = res.data.title;
                this.challenge.userNo = res.data.userNo;
                this.challenge.content = res.data.content;
                this.challenge.likeCnt = res.data.likeCnt;
                this.challenge.img = res.data.img;
                this.challenge.startDate = res.data.startDate;
                this.challenge.endDate = res.data.endDate;
            })
            .catch(err => {
                console.log(err);
            })

        const start = moment(this.challenge.startDate);
        const end = moment(this.challenge.endDate);
        this.challenge.diffDate = 'D-'+end.diff(moment(), 'days');
        this.challenge.isOpen = moment().isBetween(start, end);

        await axios.get('http://i5a105.p.ssafy.io:8080/challenge/detail/' + this.challenge.chlgNo)
            .then(res => {
                for(let i=0; i<res.data.length; i++) {
                    this.challengers.push(res.data[i]);
                }
            })
            .catch(err => {
                console.log(err);
            })

        await axios.get('http://i5a105.p.ssafy.io:8080/vote', {
            params:{
                chlgNo: this.challenge.chlgNo,
                userNo: this.user.no,
            }
        })
        .then(res => {
            for(let i=0; i<res.data.length; i++) {
                this.votes.push(res.data[i].chlgDetailNo);
            }
            for (let i=0; i<this.challengers.length; i++) {
                this.isJoin.push(this.votes.includes(this.challengers[i].chlgDetailNo));
            }
        })
        .catch(err => {
            console.log(err);
        });
    },
}
</script>
<style scoped>
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
.info {
    font-size: 0.8rem;
    font-weight: 100;
}
.join-button .btn{
    color: white;
    width: 100%;
    height: 100%;
    left: 0;
    background-color: #6AAFE6;
    top: -0.5rem;
} 
.text {
    font-size: 0.8rem;
}
.rank {
    background-color: #8EC0E4;
}
.card {
    /* position: relative;
    display: flex;
    flex-direction: column; */
    min-width: 0;
    background-color: #fff;
    height: 210px;
    padding: 0;
}
.img-box {
    height: 150px; 
    width: 100%;
    /* border-radius: 0%; */
    overflow: hidden;
}
.challenger-img {
    /* width: 140px; */
    width: 100%;
    height: 100%; 
}
.card-img {
    width: 100%;
    height: 150px;
}

.card-body {
    top: -0.5rem;
    font-size: 0.7rem;
    padding-bottom: 0.3rem;
    padding: 0;
}
.col {
    padding: 0.2rem;
}
.name {
    margin: 0.2rem;
}
button {
    top: -1.3rem;
    font-size: 0.9rem;
    padding: 0;
}
.img {
    text-align: center;
}
.icon {
    display: inline-block;
    margin-left: 0.3em;
    border-style: none;
}
</style>