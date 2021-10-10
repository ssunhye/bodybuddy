<template>
  <div class="record">
    <div class="left-0">
      <!-- <div class="title col-lg-4 py-3 mb-4">운동 기록</div> -->
      <!-- <span class="title mb-4 col-10">운동 기록</span> -->
      <div class="tabs ml-4">
        <br>
        <TabItem
          v-for="item in list"
          v-bind="item" :key="item.id"
          v-model="currentId"/>
      </div>
      <div class="contents">
        <transition>
          <section class="item pb-0" :key="currentId">
            <!-- 파워리프팅 -->
            <div v-show="currentId===1" class="menu">
              <div class="p-1 pb-3">
                <b-row>
                  <!-- squat -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="스쿼트"
                      type="gradient-green"
                      v-bind:sub-title= powerlifting.squat
                      img="squat.png"
                      class="mb-3">
                      <template v-slot:footer>
                        <hr class="mt-0 mb-3">
                        <span>{{powerlifting.date}}</span>
                        <span class="text-success right" v-if='powerlifting.squatChange'><strong>{{powerlifting.dateChange}} {{powerlifting.squatChange}}</strong></span>
                        <span class="right" v-else><strong>기록이 없습니다</strong></span>
                      </template>
                    </stats-card>
                  </b-col>
                  <!-- benchpress -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="벤치프레스"
                      type="gradient-red"
                      v-bind:subTitle= powerlifting.benchpress
                      img="benchpress.jpg"
                      class="mb-3">
                        <template v-slot:footer>
                          <hr class="mt-0 mb-3">
                          <span>{{powerlifting.date}}</span>
                          <span class="text-success right" v-if='powerlifting.benchpressChange'><strong>{{powerlifting.dateChange}} {{powerlifting.benchpressChange}}</strong></span>
                          <span class="right" v-else><strong>기록이 없습니다</strong></span>
                        </template>
                    </stats-card>
                  </b-col>
                  <!-- deadlift -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="데드리프트"
                      type="gradient-orange"
                      v-bind:sub-title= powerlifting.deadlift
                      img="deadlift.png"
                      class="mb-3">
                      <template v-slot:footer>
                        <hr class="mt-0 mb-3">
                        <span>{{powerlifting.date}}</span>
                        <span class="text-success right" v-if='powerlifting.deadliftChange'><strong>{{powerlifting.dateChange}} {{powerlifting.deadliftChange}}</strong></span>
                        <span class="right" v-else><strong>기록이 없습니다</strong></span>
                      </template>
                    </stats-card>
                  </b-col>
                </b-row>
              </div> 
              <!--Charts-->
              <div class= "chart p-1 pb-3">
                <!-- <span class="title mb-2 ml-2 change">변화</span> -->
                <div class="">
                  <b-row>
                    <b-col xl="8" class="mb-5 mb-xl-0">
                      <carousel :per-page="1" :autoplay="true" :loop="true" :navigationEnabled="false">
                        <slide style="width:100px">
                          <card type="secondary" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">스쿼트</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="powerliftingChart.arrSquat.length > 0"
                              :chartData="powerliftingChart.arrSquat"
                              :height="350"
                              label="squat"
                            >
                            </line-chart>
                          </card>
                        </slide>
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">벤치프레스</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="powerliftingChart.arrBenchpress.length > 0"
                              :chartData="powerliftingChart.arrBenchpress"
                              :height="350"
                              label="benchpress"
                            >
                            </line-chart>
                          </card>
                        </slide>
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">데드리프트</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="powerliftingChart.arrDeadlift.length > 0"
                              :chartData="powerliftingChart.arrDeadlift"
                              :height="350"
                              label="deadlift"
                            >
                            </line-chart>
                          </card>
                        </slide>
                      </carousel>
                    </b-col>
                  </b-row>
                </div>
              </div>
            </div>
            <!-- 인바디 -->
            <div v-show="currentId===2">
              <div class="p-1 pb-3">
                <b-row>
                  <!-- SCORE -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="점수"
                      type="gradient-green"
                      v-bind:sub-title= inbody.score
                      img="score.png"
                      class="mb-3">
                      <template v-slot:footer>
                        <hr class="mt-0 mb-3">
                        <span>{{inbody.date}}</span>
                        <span class="text-success right" v-if = 'inbody.scoreChange'><strong>{{inbody.dateChange}} {{inbody.scoreChange}}</strong></span>
                        <span class="right" v-else><strong>기록이 없습니다</strong></span>
                      </template>
                    </stats-card>
                  </b-col>
                  <!-- 체중 -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="체중"
                      type="gradient-red"
                      v-bind:subTitle= inbody.weight
                      img="weight.jpg"
                      class="mb-3">
                        <template v-slot:footer>
                          <hr class="mt-0 mb-3">
                          <span>{{inbody.date}}</span>
                          <span class="text-success right" v-if = 'inbody.weightChange'><strong>{{inbody.dateChange}} {{inbody.weightChange}}</strong></span>
                          <span class="right" v-else><strong>기록이 없습니다</strong></span>
                        </template>
                    </stats-card>
                  </b-col>
                  <!-- 체지방률 -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="체지방률"
                      type="gradient-orange"
                      v-bind:sub-title= inbody.fat
                      img="bodyfat.jpg"
                      class="mb-3">
                      <template v-slot:footer>
                        <hr class="mt-0 mb-3">
                        <span>{{inbody.date}}</span>
                        <span class="text-success right" v-if = 'inbody.fatChange'><strong>{{inbody.dateChange}} {{inbody.fatChange}}</strong></span>
                        <span class="right" v-else><strong>기록이 없습니다</strong></span>
                      </template>
                    </stats-card>
                  </b-col>
                  <!-- 근육량 -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="근육량"
                      type="gradient-green"
                      v-bind:sub-title= inbody.muscle
                      img="muscle.png"
                      class="mb-3">
                      <template v-slot:footer>
                        <hr class="mt-0 mb-3">
                        <span>{{inbody.date}}</span>
                        <span class="text-success right" v-if = 'inbody.muscleChange'><strong>{{inbody.dateChange}} {{inbody.muscleChange}}</strong></span>
                        <span class="right" v-else><strong>기록이 없습니다</strong></span>
                      </template>
                    </stats-card>
                  </b-col>
                  <!-- BMI -->
                  <b-col xl="3" md="6">
                    <stats-card
                      title="BMI"
                      type="gradient-green"
                      v-bind:sub-title= inbody.bmi
                      img="bmi.jpg"
                      class="mb-3">
                      <template v-slot:footer>
                        <hr class="mt-0 mb-3">
                        <span>{{inbody.date}}</span>
                        <span class="text-success right" v-if = 'inbody.muscleChange'><strong>{{inbody.dateChange}} {{inbody.bmiChange}}</strong></span>
                        <span class="right" v-else><strong>기록이 없습니다</strong></span>
                      </template>
                    </stats-card>
                  </b-col>
                </b-row>
              </div> 
              <!--Charts-->
              <div class= "chart p-1 pb-3">
                <!-- <span class="title mb-2 ml-2">변화</span> -->
                <div class="">
                  <b-row>
                    <b-col xl="8" class="mb-5 mb-xl-0">
                      <carousel :per-page="1" :autoplay="true" :loop="true" :navigationEnabled="false">
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">점수</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="inbodyChart.arrScore.length > 0"
                              :chartData="inbodyChart.arrScore"
                              :height="350"
                              label="score"
                            >
                            </line-chart>
                          </card>
                        </slide>
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">체중</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="inbodyChart.arrWeight.length > 0"
                              :chartData="inbodyChart.arrWeight"
                              :height="350"
                              label="weight"
                            >
                            </line-chart>
                          </card>
                        </slide>
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">체지방률</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="inbodyChart.arrFat.length > 0"
                              :chartData="inbodyChart.arrFat"
                              :height="350"
                              label="fat"
                            >
                            </line-chart>
                          </card>
                        </slide>
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">근육량</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="inbodyChart.arrMuscle.length > 0"
                              :chartData="inbodyChart.arrMuscle"
                              :height="350"
                              label="muscle"
                            >
                            </line-chart>
                          </card>
                        </slide>
                        <slide style="width:100px">
                          <card type="default" header-classes="bg-transparent">
                            <b-row align-v="center" slot="header">
                              <b-col>
                                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                                <h5 class="h3 text-black mb-0">BMI</h5>
                              </b-col>
                            </b-row>
                            <line-chart
                              v-if="inbodyChart.arrBMI.length > 0"
                              :chartData="inbodyChart.arrBMI"
                              :height="350"
                              label="BMI"
                            >
                            </line-chart>
                          </card>
                        </slide>
                      </carousel>
                    </b-col>
                  </b-row>
                </div>
              </div>
            </div>

            <!-- 눈바디   -->
            <div v-show="currentId===3">
              <swiper class="swiper" :options="swiperOption"> 
                <swiper-slide v-for = '(data, idx) in eyebody.dataArray' v-bind:key = 'idx' >
                  <img :src ='data.img' id="eyebody-img">
                </swiper-slide>
                <div class="swiper-pagination" slot="pagination"></div>
                <div class="swiper-button-prev" slot="button-prev"></div> 
                <div class="swiper-button-next" slot="button-next"></div>
              </swiper> 
            </div>
          </section>
        </transition>
      </div>
    </div>
    <router-link to="/user/exercise/record">
      <img id='imgBtn' src="../../assets/images/plusBtn.png">
    </router-link>
  </div>
  
</template>



<script>
import LineChart from "@/components/Charts/LineChart";
import axios from "axios";
import StatsCard from "../../components/Cards/StatsCard.vue";
import { Carousel, Slide } from 'vue-carousel';
import TabItem from '../exercise/TabItem.vue'
// import BaseProgress from "@/components/BaseProgress";
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';

const storage = localStorage;
const moment = require('moment'); 

export default {
  name: "App",
  components: {
    LineChart,
    StatsCard,
    Carousel,
    Slide,
    TabItem,
    //BaseProgress,
    Swiper, SwiperSlide
  },

  data() {
    return {
      swiperOption: { 
        slidesPerView: 1, 
        spaceBetween: 30, 
        loop: true, 
        pagination: { 
          el: '.swiper-pagination', 
          clickable: true },
          navigation:{ 
            nextEl: '.swiper-button-next', 
            prevEl: '.swiper-button-prev' }
      },
      user: {
        no:"",
        id:"",
        name:"",
        email: '',
      },
      powerlifting: {
        squat:"",
        benchpress:"",
        deadlift:"",
        date:"",
        squatChange: null,
        benchpressChange: null,
        deadliftChange : null,
        dateChange: null,
      },
      inbody: {
        weight:"",
        fat:"",
        muscle:"",
        bmi:"",
        score:"",
        date:"",
        weightChange : null,
        fatChange : null,
        muscleChange : null,
        bmiChange: null,
        scoreChange: null,
        dateChange: null,
      },
      img: {
        selectFile: null,
        previewImgUrl: null, // 미리보기 이미지 URL
        isUploading: false, // 파일 업로드 체크
      },
      eyebody: {
        dataArray: []
      },
      inbodyChart: {
        arrWeight: [],
        arrFat: [],
        arrMuscle: [],
        arrScore: [],
        arrBMI: [],
      },
      powerliftingChart: {
        arrSquat: [],
        arrBenchpress: [],
        arrDeadlift: [],
      },
      currentId: 1,
      list: [
          { id: 1, label: '파워리프팅'},
          { id: 2, label: '인바디'},
          { id: 3, label: '눈바디'}
      ],
      
      elapsedTime: 0,
      timer: undefined,
      urls : null,
    };
  },

  methods: {
    uploadImage: function () {
      // 이미지 업로드
      let form = new FormData();
      let image = this.$refs["image"].files[0];

      form.append("image", image);

      axios
        .post("/upload", form, {
          header: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          this.images = data;
        })
        .catch((err) => console.log(err));
    },
    clickInputTag: function () {
      this.$refs["image"].click();
    },
    
    // 타이머
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    stop() {
      clearInterval(this.timer);
    },
    reset() {
      this.elapsedTime = 0;
    },
    inbodyInfo() {

    }
  },

  async mounted() {
    // 유저 정보
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
      console.log(err);
    });

    // 인바디
    await axios.get('http://i5a105.p.ssafy.io:8080/inbody/{userNo}',{
      params: {
        userNo : this.user.no
      }
    }).then(res => {
      this.inbody.weight = String(res.data[res.data.length-1].weight)+'kg';
      this.inbody.fat = String(res.data[res.data.length-1].fat+'%');
      this.inbody.muscle = String(res.data[res.data.length-1].muscle+'kg');
      this.inbody.bmi = String(res.data[res.data.length-1].bmi);
      this.inbody.score = String(res.data[res.data.length-1].score+'점');
      this.inbody.date = String(res.data[res.data.length-1].date+'일');

      this.inbody.weightChange = '대비 '+String((res.data[res.data.length-1].weight-res.data[res.data.length-2].weight).toFixed(2))+'kg';
      this.inbody.fatChange = '대비 '+String((res.data[res.data.length-1].fat-res.data[res.data.length-2].fat).toFixed(2))+'%';
      this.inbody.muscleChange = '대비 '+String((res.data[res.data.length-1].muscle-res.data[res.data.length-2].muscle).toFixed(2))+'kg';
      this.inbody.bmiChange = '대비 '+String((res.data[res.data.length-1].bmi-res.data[res.data.length-2].bmi).toFixed(2));
      this.inbody.scoreChange = '대비 '+String(res.data[res.data.length-1].score-res.data[res.data.length-2].score)+'점';
      
      const before = moment(res.data[res.data.length-2].date);
      const now = moment(res.data[res.data.length-1].date);
      this.inbody.dateChange = now.diff(before, 'days') + '일 전 ';

      res.data.forEach(d => {
        const date = moment(d.date, "YYYYMMDD").format("MM/DD");
        const {
          weight,
          fat, 
          muscle,
          bmi,
          score,
        } = d;
        
        this.inbodyChart.arrWeight.push({date, total: weight});
        this.inbodyChart.arrFat.push({date, total: fat});
        this.inbodyChart.arrMuscle.push({date, total: muscle});
        this.inbodyChart.arrBMI.push({date, total: bmi});
        this.inbodyChart.arrScore.push({date, total: score});
      })
    }).catch(err => {
      console.log(err);
    });

  // 파워리프팅
  await axios.get('http://i5a105.p.ssafy.io:8080/powerlifting/{userNo}',{
      params: {
        userNo : this.user.no
      }
    }).then(res => {
        this.powerlifting.squat = String(res.data[res.data.length-1].squat)+'kg';
        this.powerlifting.benchpress = String(res.data[res.data.length-1].benchpress+'kg');
        this.powerlifting.deadlift = String(res.data[res.data.length-1].deadlift+'kg');
        this.powerlifting.date = String(res.data[res.data.length-1].date+'일');

        this.powerlifting.squatChange = '대비 '+String((res.data[res.data.length-1].squat-res.data[res.data.length-2].squat).toFixed(2))+'kg';
        this.powerlifting.benchpressChange = '대비 '+String((res.data[res.data.length-1].benchpress-res.data[res.data.length-2].benchpress).toFixed(2))+'kg';
        this.powerlifting.deadliftChange = '대비 '+String((res.data[res.data.length-1].deadlift-res.data[res.data.length-2].deadlift).toFixed(2))+'kg';
        
        const before = moment(res.data[res.data.length-2].date);
        const now = moment(res.data[res.data.length-1].date);
        this.powerlifting.dateChange = now.diff(before, 'days') + '일 전 ';

        res.data.forEach(d => {
          const date = moment(d.date, "YYYYMMDD").format("MM/DD");
          const {
            squat,
            benchpress, 
            deadlift,
          } = d;
          
          this.powerliftingChart.arrSquat.push({date, total: squat});
          this.powerliftingChart.arrBenchpress.push({date, total: benchpress});
          this.powerliftingChart.arrDeadlift.push({date, total: deadlift});
        })
      }).catch(err => {
        console.log(err);
      })
    //눈바디
    await axios.get('http://i5a105.p.ssafy.io:8080/eyebody/{userNo}',{
      params: {
        userNo: this.user.no
      }
    }).then(res => {
      this.eyebody.dataArray = res.data
    })
    },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
  },
};
</script>
<style lang='scss' scoped>
.el-table .cell {
  padding-left: 0px;
  padding-right: 0px;
}
@import '../../assets/css/base.scss';

#imgBtn {
  width : 50px;
  border-radius: 70px;
  position : fixed;
  right: 20px;
  bottom: 20px;
  z-index: 1;
}
.box {
  background-color: white;
  border-radius: 1rem;
  margin: 1rem;
}
// .title {
//   display: inline-block;
//   font-size: 1.2rem;
//   text-align: center;
//   color: #6AAFE6;
// }
.chart {
  height: 580px;
}
.change {
  color: rgb(70, 71, 71);
}
.all-button .btn{
  color: white;
  width: 100%;
  height: 100%;
  left: 0;
  background-color: #6AAFE6;
  top: -0.5rem;
}
.VueCarousel-navigation-button {
  display: none;
}
.contents {
  overflow: hidden;
}
.item {
  box-sizing: border-box;
  padding: 10px;
  width: 100%;
  transition: all 0.8s ease;
}
/* 트랜지션 전용 스타일 */
.v-leave-active {
  position: absolute;
}
.v-enter {
  transform: translateX(-100%);
}
.v-leave-to {
  transform: translateX(100%);
}
.tabs {
    padding-bottom: 5px;
    text-align: left;
    margin-left: 10px;
    margin-right: 10px;
}
.swiper{
  margin-top: 50px;
}
#eyebody-img{
  width: 95%;
  object-fit: contain;
}
</style>