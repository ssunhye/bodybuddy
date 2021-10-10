<template>
    <div class="ChallengeRegister">
        <div class="box text-center">
            <div class="left-0  py-3">
                <div class="title col-lg-4 py-3 mb-0">
                    <b-icon icon="chevron-left" @click="goBack" class="left mt-1" font-scale="0.999999"></b-icon>게시글 작성
                </div>
                <form class="mt-3">
                    <div>
                        <input ref="image" id="input" type="file" name="image" accept="image/*" placeholder="사진" required/>
                        <textarea style="height: 30%;" rows="1" v-model="feedTitle" placeholder="제목" required/>
                        <textarea rows="5" v-model="feedText" placeholder="내용" required/>
                    </div>
                    <div class = "px-5 pt-4">
                        <div class="register-button"><a class="btn" @click="register">등록</a></div>
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
  background-color: #6AAFE6;
  text-align: center;
  color: white;
  display: inline-block;
  font-size: 1.2rem;
  font-weight: 800;
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
    height: 150px;
    padding: 12px 20px;
    padding-top: 10px;
    margin-top: 5px;
    box-sizing: border-box;
    border: 2px solid #CADBE9;
    border-radius: 4px;
    background-color: white;
    resize: none;
}

input[type=file] {
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


</style>

<script>
import axios from "axios";

const storage = localStorage;

export default {
    data(){
        return{
            id:"",
            feedText:"",
            feedTitle: "",
        }
    },
    created(){
        axios.get('http://i5a105.p.ssafy.io:8080/account/userinfo',{
        params:{
            jwt: storage.getItem("jwt-token"),
        }
        }).then(res => {
            this.id = res.data.userId;
        }).catch(err=>{
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
        requestSaveChallenge(challenge) {
            this.$store.dispatch('SAVE_CHALLENGE', challenge);
        },
        register(){
            let form = new FormData()
            let image = this.$refs['image'].files[0]
            form.append('img', image)
        
            axios.post('http://i5a105.p.ssafy.io:8080/feed/create', form, {
                header: { 'Content-Type': 'multipart/form-data' },
                params:{
                    feedTitle: this.feedTitle,
                    feedText: this.feedText,
                    userId: this.id,
                }
            }).then( ({data}) => {
                this.$router.replace('/user/feed');
            })
            .catch( err => console.log(err))
        },
        clickInputTag: function() {
            this.$refs['image'].click()
        }
    }
}
</script>
