<template>
    <div class="RecordRegister">
        <div class="box text-center">
            <div class="left-0 py-3">
                <div class="row">
                    <div class="icon col-1">
                        <b-icon icon="chevron-left" @click="goBack" font-scale="0.999999"></b-icon>
                    </div>
                    <span class="title mb-4 col-10">기록</span>
                </div>
                <form>
                    <div>
                        
                        <textarea rows="5" v-model="inbody.weight" placeholder="체중 (kg)" required/>
                        <textarea rows="5" v-model="inbody.fat" placeholder="체지방률 (%)" required/>
                        <textarea rows="5" v-model="inbody.muscle" placeholder="근육량 (kg)" required/>
                    </div>
                    <div class="text-left info ml-4 mt-3">날짜</div>
                        <input type="date" v-model="inbody.date" placeholder="시작 날짜" required/>
                    <div class="text-left info ml-4 mt-3">사진</div>
                        <img v-if="img.previewImgUrl" :src="img.previewImgUrl" class="img"/>
                            <input type="file" ref="selectFile" @change="previewFile" name="image" accept="image/*" required/>
                    <div class = "px-5 pt-4">
                        <div class="register-button"><a class="btn" @click="insertRecord">저장</a></div>
                    </div>
                </form>
                
            </div>
        </div>
    </div>
</template>
<style scoped>
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
.icon {
    display: inline-block;
    margin-left: 0.3em;
}
.register-button .btn {
    color: white;
    width: 100%;
    height: 100%;
    left: 0;
    background-color: #6AAFE6;
    top: -0.5rem;
} 
textarea {
    width: 86%;
    height: 50px;
    padding: 12px 20px;
    padding-top: 10px;
    margin-top: 5px;
    box-sizing: border-box;
    border: 2px solid #CADBE9;
    border-radius: 4px;
    background-color: white;
    resize: none;
}

input[type=text] {
    background-color: #d8e9f7;
    width: 86%;
    border: none;
    border-bottom: 2px solid #CADBE9;
}

input[type=date] {
    width: 86%;
    border: none;
    border-bottom: 2px solid #CADBE9;
}
.info {
    font-size: 0.9rem;
    font-weight: 100;
}
.img {
    width: 300px;
}


</style>

<script>
import axios from "axios";
import { BIcon } from 'bootstrap-vue'

const storage = localStorage;

export default {
  components: { BIcon },
    data() {
        return {
            inbody: {
                weight: "",
                fat: "",
                muscle: "",
                date: ""
            },
            user: {
                no:"",
                id:"",
                name:"",
                email: '',
            },
            img: {
                selectFile: null,
                previewImgUrl: null, // 미리보기 이미지 URL
                isUploading: false, // 파일 업로드 체크
            }
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
        });
  },
    methods: {
        insertRecord() {
            axios.post('http://i5a105.p.ssafy.io:8080/inbody?userNo=' + this.user.no, {
                weight: this.inbody.weight,
                fat: this.inbody.fat,
                muscle: this.inbody.muscle,
                bmi: '0',
                score: '100'
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
            if (0 < this.$refs.selectFile.files.length) {
            // 0 번째 파일을 가져 온다.
                this.img.selectFile = this.$refs.selectFile.files[0]
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
    },
}
</script>
