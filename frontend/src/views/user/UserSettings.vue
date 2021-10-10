<template>
<div class="container">
    <div class="row pt-0">
        <div class="title col-lg-4 py-3 mb-0">
            <b-icon icon="chevron-left" @click="goBack" class="left mt-1" font-scale="0.999999"></b-icon>회원 정보 수정
        </div>
       
        <div class="col-lg-8 pb-4 mt-3">
            <label for="account-confirm-pass ml-3"> 😎 내 이미지 변경</label>
            <div class="text-center">
                <div>
                    <img v-if="img.previewImgUrl" :src="img.previewImgUrl" class="img"/>
                </div>
                <div class="d-flex mt-3">
                    <input class="px-0" type="file" id="input" ref="image" @change="previewFile" accept="image/*" required/>
                    <button id="dlBtn" class="btn btn-style-1 btn-primary px-0 btn-md" type="button" @click="change" ><span>사진 변경</span></button>
                    <button id="dlBtn" class="btn btn-style-1 btn-primary px-0 btn-md" type="button" @click="standimg" ><span>기본사진 적용</span></button>
                </div>
            </div>
            <form class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="account-fn">이름</label>
                        <input class="form-control" type="text" id="account-fn" :value="name" disabled="">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="account-ln">아이디</label>
                        <input class="form-control" type="text" id="account-ln" :value="id" disabled="">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="account-email">이메일</label>
                        <input class="form-control" type="email" id="account-email" :value="email" disabled="">
                    </div>
                </div>
                <!-- <div class="col-md-6">
                    <div class="form-group">
                        <label for="account-phone">가입날짜</label>
                        <input class="form-control" type="text" id="account-phone" :value="date" disabled="">
                    </div>
                </div> -->
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="account-pass">새 비밀번호</label>
                        <input class="form-control" type="password" id="account-pass">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="account-confirm-pass">새 비밀번호 확인</label>
                        <input class="form-control" type="password" id="account-confirm-pass">
                    </div>
                </div>

                <div class="col-12">
                    <hr class="mt-0 mb-3">
                    <div class="d-flex flex-wrap justify-content-between align-items-center">
                        <button id="delBtn" class="btn btn-style-1 btn-primary" type="button" @click="del" data-toast="" data-toast-position="topRight" data-toast-type="success" data-toast-icon="fe-icon-check-circle" data-toast-title="Success!" data-toast-message="Your profile updated successfuly."><span>계정 삭제</span></button>
                        <button id="saveBtn" class="btn btn-style-1 btn-primary" type="button" @click="save" data-toast="" data-toast-position="topRight" data-toast-type="success" data-toast-icon="fe-icon-check-circle" data-toast-title="Success!" data-toast-message="Your profile updated successfuly."><span>저장하기</span></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</template>
<style scoped>
.title {
    background-color: #6AAFE6;
    text-align: center;
    color: white;
    display: inline-block;
    font-size: 1.2rem;
    font-weight: 800;
}
.btn {
    height: 90%;
}

#delBtn {
    background-color: #CADBE9;
    color: #525F7F;
    border-color: #D4DFE6;
}

#saveBtn {
    background-color: #8EC0E4;
    color: #525F7F;
    border-color: #8EC0E4;
}

#i {
    background-color: #8EC0E4;
    color: #525F7F;
    border-color: #8EC0E4;
}

input[type=file] {
    width: 95%;
    border: none;
    margin-top: 3px;
}

#dlBtn1 {
    background-color: #8EC0E4;
    color: #525F7F;
    border-color: #8EC0E4;
    width: 170px;
}

#dlBtn2 {
    background-color: #CADBE9;
    color: #525F7F;
    border-color: #D4DFE6;
    width: 200px;
}
.img {
    width: 300px;
}
</style>
<script>
import axios from "axios";

const storage = localStorage;

export default {
  name: "usersetting",
  data: function () {
    return {

        id:"",
        name:"",
        date:"",
        email: '',
        password: '',
        user: {
            userImg:"",createDate: "", userEmail: "", userId: "", userName: "", userNo: "", userPw: "", userImg:"",
        },
        img: {
            selectFile: null,
            previewImgUrl: null, // 미리보기 이미지 URL
            isUploading: false, // 파일 업로드 체크
        }
    };
  },
  created(){
    axios.get('http://i5a105.p.ssafy.io:8080/account/userinfo',{
    params:{
        jwt: storage.getItem("jwt-token"),
    }
    }).then(res => {
        this.user = res.data;
        this.id = res.data.userId;
        this.name = res.data.userName;
        this.date = res.data.createDate.substring(0, 10);
        this.email = res.data.userEmail;
        this.password = res.data.userPw;

    }).catch(err=>{
        if(err.response.data == "expired")
        {
            this.$store.commit('SET_SNACKBAR', {
                text: "로그인 시간이 만료되었습니다.",
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
    del(){
        let confirm = prompt("삭제를 원하시면 [계정 삭제]를 입력해 주세요");
        if(confirm == "계정 삭제")
        {
            axios.delete('http://i5a105.p.ssafy.io:8080/feed/deleteAll',{
                params:{
                    id: this.id,
                }
            }).then(res => {
                

            }).catch(err=>{
                alert(err);
            });
            axios.delete('http://i5a105.p.ssafy.io:8080/account/delete',{
                params:{
                    id: this.id,
                }
            }).then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "계정이 삭제되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                storage.removeItem("jwt-token");
                this.$router.replace('/user/login');
            }).catch(err=>{
                //alert(err);
            });
        }
        else
        {
            this.$store.commit('SET_SNACKBAR', {
                text: "다시 한번 확인해주세요",
                color: '#8EC0E4',
                location: 'bottom',
            })
        }
        
    },
    save(){
        const newPw = document.getElementById('account-pass')
        const newPwConfirm = document.getElementById('account-confirm-pass')
        if ((newPw.value) != "" & (newPw.value == newPwConfirm.value)) {
            this.user.userPw = newPw.value
            axios.put('http://i5a105.p.ssafy.io:8080/account/modify', {
                user: this.user
            }).then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "비밀번호가 변경되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
            })
        } else {
            this.$store.commit('SET_SNACKBAR', {
                text: "다시 확인해 주세요.",
                color: '#8EC0E4',
                location: 'bottom',
            })
        }
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
        change(){
            let form = new FormData()
            let image = this.$refs['image'].files[0]

            form.append('image', image) // 여기 image 이름하고 서버쪽 param 이름 같아야함 ("image")

            axios.put('http://i5a105.p.ssafy.io:8080/account/userimg', form,{
                header: { 'Content-Type': 'multipart/form-data' },
                params:{
                    id: this.id,
                }
            })
            .then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "변경 되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                //this.$router.push('/user/challenge');
            })
            .catch(err => {
                console.log(err);
            });
        },
        standimg(){
            axios.get('http://i5a105.p.ssafy.io:8080/account/standimg',{
                params:{
                    id: this.id,
                }
            })
            .then(res => {
                this.$store.commit('SET_SNACKBAR', {
                    text: "변경 되었습니다.",
                    color: '#8EC0E4',
                    location: 'bottom',
                })
                //this.$router.push('/user/challenge');
            })
            .catch(err => {
                console.log(err);
            });
        }
  }
}
</script>