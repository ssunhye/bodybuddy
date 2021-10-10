<template>
    <div class="exerciseRecord">
        <div class="box text-center">
            <div class="left-0 py-3">
                <div class="row">
                    <div class="icon col-1">
                        <b-icon icon="chevron-left" @click="goBack" font-scale="0.999999"></b-icon>
                    </div>
                    <span class="title mb-4 col-10">운동 기록하기</span>
                </div>
                <div class="tabs">
                    <TabItem
                        v-for="item in list"
                        v-bind="item" :key="item.id"
                        v-model="currentId"/>
                </div>
                <div class="contents">
                    <transition>
                        <section class="item" :key="currentId">
                            <div v-show="currentId===1" class="record">
                                <form class="form mt-3">
                                    <div class="text-left info ml-4 mt-2">3대 기록 ✏️</div>
                                    <div class = "pt-3">
                                        <input type="number" v-model="powerlifting.squat" placeholder="스쿼트 (kg)" required/>
                                        <input type="number" v-model="powerlifting.benchpress" placeholder="벤치프레스 (kg)" required/>
                                        <input type="number" v-model="powerlifting.deadlift" placeholder="데드리프트 (kg)" required/>
                                    </div>
                                    <div class="text-left info ml-4 mt-4">운동 날짜 🏋️‍♀️</div>
                                    <input type="date" v-model="powerlifting.date" required/>
                                    <div class = "px-5 pt-4">
                                        <div class="register-button"><a class="btn" @click="insertPowerlifting">저장</a></div>
                                    </div>
                                </form>
                            </div>
                            <div v-show="currentId===2">
                                <form>
                                    <div class="text-left info ml-4 mt-2 mb-2">사진으로 등록하기 📸</div>
                                    <span class = "pt-3">
                                        <input @change="ocr" ref="img" id="input" type="file" name="img" accept="image/*" placeholder="사진" :disabled="imgloadchk"/>
                                    </span>
                                    <div class="text-left info ml-4 mt-1">인바디 기록 ✏️</div>
                                    <div class = "pt-3">
                                        <input type="number" v-model="inbody.weight" placeholder="체중 (kg)" required/>
                                        <input type="number" v-model="inbody.fat" placeholder="체지방률 (%)" required/>
                                        <input type="number" v-model="inbody.muscle" placeholder="근육량 (kg)" required/>
                                        <input type="number" v-model="inbody.bmi" placeholder="BMI" required/>
                                        <input type="number" v-model="inbody.score" placeholder="SCORE" required/>
                                    </div>
                                    <div class="text-left info ml-4 mt-3">측정 날짜 🏋️‍♀️</div>
                                        <input type="date" v-model="inbody.date" required/>
                                    <div class = "px-5 pt-4">
                                        <div class="register-button"><a class="btn" @click="insertInbody">저장</a></div>
                                    </div>
                                </form>
                            </div>
                            <div v-show="currentId===3">
                                <form>
                                    <div class="text-left info ml-4 mt-2">사진 📸</div>
                                    <div class = "pt-3">
                                        <div style="height:280px">
                                            <img v-show="img.previewImgUrl" :src="img.previewImgUrl" class="img"/>
                                        </div>
                                        <input type="file" id="input" ref="image" @change="previewFile" accept="image/*" required/>
                                    </div>
                                    <div class="text-left info ml-4 mt-0">촬영 날짜 🏋️‍♀️</div>
                                        <input type="date" v-model="eyebody.date" required/>
                                    <div class = "px-5 pt-4">
                                        <div class="register-button"><a class="btn" @click="insertEyebody">저장</a></div>
                                    </div>
                                </form>
                            </div>
                        </section>
                    </transition>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import axios from "axios";
import TabItem from './TabItem.vue'
const storage = localStorage;

export default {
  components: { TabItem },
    data() {
        return {
            imgloadchk:false,
            currentId: 1,
            list: [
                { id: 1, label: '파워리프팅'},
                { id: 2, label: '인바디'},
                { id: 3, label: '눈바디'}
            ],
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
            },
            inbody: {
                weight:"",
                fat:"",
                muscle:"",
                bmi:"",
                score:"",
                date:""
            },
            img: {
                selectFile: null,
                previewImgUrl: null, // 미리보기 이미지 URL
                isUploading: false, // 파일 업로드 체크
            },
            eyebody: {
                date:""
            }
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
    computed: {
        current() {
            return this.list.find(el => el.id === this.currentId) || {}
        },
    },
    methods: {
        insertInbody() {
            axios.post('http://i5a105.p.ssafy.io:8080/inbody?userNo=' + this.user.no,{
                    weight: this.inbody.weight,
                    fat: this.inbody.fat,
                    muscle: this.inbody.muscle,
                    bmi: this.inbody.bmi,
                    score: this.inbody.score,
                    date: this.inbody.date
            })
            .then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "등록 되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                this.$router.push('/user/record');
            })
            .catch(err => {
                console.log(err);
            });
        },
        ocr(){
            this.imgloadchk = true;
            let form = new FormData()
            let image = this.$refs['img'].files[0]
            
            form.append('img', image)
    
            axios.post('http://i5a105.p.ssafy.io:8080/inbody/ocr', form, {
                header: { 'Content-Type': 'multipart/form-data' },
            }).then( res => {
                this.imgloadchk = false;
            })
            .catch( err => console.log(err))
        },
        clickInputTag: function() {
            this.$refs['img'].click()
        }
        ,
        insertPowerlifting() {
            axios.post('http://i5a105.p.ssafy.io:8080/powerlifting', {
                squat: this.powerlifting.squat,
                benchpress: this.powerlifting.benchpress,
                deadlift: this.powerlifting.deadlift,
                date: this.powerlifting.date,
                userNo: this.user.no,
            })
            .then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "등록 되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                this.$router.push('/user/record');
            })
            .catch(err => {
                console.log(err);
            });
        },
        insertEyebody() {
            let form = new FormData()
            let image = this.$refs['image'].files[0]


            form.append('image', image) // 여기 image 이름하고 서버쪽 param 이름 같아야함 ("image")

            axios.post('http://i5a105.p.ssafy.io:8080/eyebody', form,{
                header: { 'Content-Type': 'multipart/form-data' },
                params:{
                    date: this.eyebody.date,
                    userNo: this.user.no,
                }
            })
            .then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "등록 되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                this.$router.push('/user/record');
            })
            .catch(err => {
                console.log(err);
            });
        },
        goBack() {
            this.$router.go(-1);
        },
        previewFile() {
            // 선택된 파일이 있는가?
            if (0 < this.$refs.image.files.length) {
            // 0 번째 파일을 가져 온다.
                this.img.selectFile = this.$refs.image.files[0]
                // 마지막 . 위치를 찾고 + 1 하여 확장자 명을 가져온다.
                let fileExt = this.img.selectFile.name.substring(
                    this.img.selectFile.name.lastIndexOf(".") + 1
                )
                // 소문자로 변환
                fileExt = fileExt.toLowerCase()
                // 이미지 확장자 체크, 1메가 바이트 이하 인지 체크
                if (
                    ["jpeg", "png", "gif", "bmp", "jpg"].includes(fileExt) &&
                    this.img.selectFile.size <= 1048576
                    ) {
                        // FileReader 를 활용하여 파일을 읽는다
                        var reader = new FileReader()
                        reader.onload = e => {
                            // base64
                            this.img.previewImgUrl = e.target.result
                        }
                        reader.readAsDataURL(this.img.selectFile)
                    } else if (this.img.selectFile.size <= 1048576) {
                        // 이미지외 파일
                        this.img.previewImgUrl = null
                    } else {
                        this.$store.commit('SET_SNACKBAR', {
                            text: "파일을 다시 선택해 주세요.",
                            color: '#8EC0E4',
                            location: 'bottom',
                        })
                        this.img.selectFile = null
                        this.img.previewImgUrl = null
                }
            } else {
                // 파일을 선택하지 않았을때
                this.img.selectFile = null
                this.img.previewImgUrl = null
            }
        },
    }
}
</script>

<style scoped>
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
.box {
  background-color: white;
  border-radius: 1rem;
  margin: 1rem;
}
.title {
  display: inline-block;
  font-size: 1.2rem;
  font-weight: 800;
}
.tabs {
    padding-bottom: 5px;
    text-align: left;
    margin-left: 10px;
    margin-right: 10px;
}
.img {
    width: 300px;
    height: 290px;
}
.register-button .btn {
    color: white;
    width: 100%;
    height: 100%;
    left: 0;
    background-color: #6AAFE6;
    top: -0.5rem;
}
input[type=number] {
    width: 86%;
    border: 2px solid #CADBE9;
}
input[type=date] {
    width: 86%;
    border: none;
    border-bottom: 2px solid #CADBE9;
}
input[type=file] {
    width: 95%;
    border: none;
}
.info {
    font-size: 0.9rem;
    font-weight: 100;
}
.icon {
    display: inline-block;
    margin-left: 0.3em;
    border-style: none;
}
</style>
