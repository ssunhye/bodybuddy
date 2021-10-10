<template>
    <div class="joinChallenge">
        <div class="box text-center">
            <div class="left-0 py-3">
                <div class="row">
                    <div class="icon col-1">
                        <b-icon icon="chevron-left" @click="goBack" font-scale="0.999999"></b-icon>
                    </div>
                    <span class="title mb-4 col-10">챌린지 참여하기</span>
                </div>
                <div class="contents">
                    <form>
                        <div class="text-left info ml-4 mt-2">사진 📸</div>
                        <div class = "pt-3">
                            <div style="height:300px">
                                <img v-show="img.previewImgUrl" :src="img.previewImgUrl" class="img"/>
                            </div>
                            <input type="file" id="input" ref="image" @change="previewFile" accept="image/*" required/>
                        </div>
                        <span class="mb-4 col-10">사진을 등록하여 챌린지에 참여하세요!</span>
                        <div class = "px-5 pt-4">
                            <div class="register-button"><a class="btn" @click="joinChallenge">참여</a></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import axios from "axios";
const storage = localStorage;

export default {
    data() {
        return {
            imgloadchk:false,
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
            },
            chlgNo:"",
            isJoin:"",
        }
    },
    created() {
        this.chlgNo = this.$route.params.chlgNo;
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
    },
    methods: {
        joinChallenge() {
            let form = new FormData()
            let image = this.$refs['image'].files[0]

            form.append('image', image) // 여기 image 이름하고 서버쪽 param 이름 같아야함 ("image")
            axios.post('http://i5a105.p.ssafy.io:8080/challenge/detail', form, {
                header: { 'Content-Type': 'multipart/form-data' },
                params:{
                    userNo: this.user.no,
                    chlgNo: this.chlgNo,
                }
            })
            .then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "등록 되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                this.$router.push('/user/challenge');
            })
            .catch(err => {
                console.log(err);
            });
        },
        clickInputTag: function() {
            this.$refs['img'].click()
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