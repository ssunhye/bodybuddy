<template>

  <div>
      <VueRealWave wavePosition="bottom" :waveLayerCount="4" :space="80" :waveGap="150" waveMode="uniform" waveBackgroundColor="#6AAFE6">
        <center><img src = "../../assets/images/logo2.png" alt="logo" width="150" height="150" ></center>
      </VueRealWave>


      <main class="box">

        <form>
          <h3 class='register-text'>회원가입</h3>
          <div class="inputBox">
            <input type="text" @mouseup="change" v-model="userId" placeholder="id" @blur="idcheking" required/>
            <span class="badge badge-success mt-1" v-if="idchksuc">가입 가능한 아이디 입니다.</span>
            <span class="badge badge-danger mt-1" v-if="idchkfail">이미 사용중인 아이디 입니다.</span>
          </div>
          <div class="inputBox">
            <input type="text" v-model="userEmail" placeholder="email" required/>
          </div>
          <div class="inputBox">
            <input type="text" v-model="userName" placeholder="이름" required/>
          </div>
          <div class="inputBox">
            <input type="password" v-model="userPassword" placeholder="password" required class="mb-0 pb-0"/>
            <span class="badge mb-2">문자와 숫자를 포함하여 8자 이상</span>
          </div>
          <div>
            <button class="button"  v-on:click="register">가입하기</button>
          </div>
        </form>
    </main>
    
    <footer>
    </footer>
  </div>


</template>

<script>
import VueRealWave from 'vue-real-wave';
import axios from "axios";

const storage = localStorage;

export default {
  name: "Register",
  components:{VueRealWave},
  data: function () {
    return {
      userEmail: '',
      userId: '',
      userName: '',
      userPassword: '',
      idchksuc:false,
      idchkfail:false,
      idchk:true,

    };
  },
  methods: {
    async register() {
      if(this.idchksuc)
      {
          await axios
          .post('http://i5a105.p.ssafy.io:8080/account/signup', {
            userEmail: this.userEmail,
            userId: this.userId,
            userName: this.userName,
            userPw: this.userPassword,
            userImg: "https://bodybuddy.s3.ap-northeast-2.amazonaws.com/user/stand.jpg",
          })
          .then(res => {
            this.$store.commit('SET_SNACKBAR', {
              text: "회원가입 되었습니다.",
              color: '#8EC0E4',
              location: 'bottom',
            })
            //this.$router.replace('login')
          })
          .catch(err => {
            console.log(err);
          });
          storage.setItem("jwt-token", "");
          await axios
          .post('http://i5a105.p.ssafy.io:8080/account/login', {
              email: this.userEmail,
              password: this.userPassword
          })
          .then(res => {
            storage.setItem("jwt-token", res.data.accessionToken);
            this.$router.replace('record')
          })
          .catch(err => {
            this.$store.commit('SET_SNACKBAR', {
              text: "올바르게 입력해 주세요",
              color: '#8EC0E4',
              location: 'bottom',
            })
          })
          .then(() => {
          })
  }
      else
      {
        this.$store.commit('SET_SNACKBAR', {
          text: "아이디를 확인해 주세요.",
          color: '#8EC0E4',
          location: 'bottom',
        })
      }
      
    },
    idcheking(){
      axios
        .get('http://i5a105.p.ssafy.io:8080/account/idchk?id=' + this.userId)
        .then(res => {
          if(res.data)
          {
            this.idchksuc = true;
            this.idchk = false;
          }
          else
          {
            this.idchkfail = true;
            this.idchk = false;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    change(){
      this.idchk = true;
      this.idchksuc = false;
      this.idchkfail = false;
    },
  }
}
</script>

<style>
* {
  box-sizing: border-box;
}

.register-text{
  font-family: sans-serif;
  margin-bottom: 30px;
}

.box {
  margin: 0; 
  position: absolute;
  top: 61%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: left;
  background-color: rgb(255, 255, 255, 0);
  width: 250px;
  border-radius: 0px;
}

.box .inputBox label {
  color: #fff;
}

.box .inputBox input {
  font-family: sans-serif;
  background: transparent;
  border: none;
  border-bottom: 1px solid rgb(85, 84, 84);
  color: rgb(12, 11, 11);
  font-size: 14px;
  letter-spacing: 2px;
  margin-bottom: 20px;
  outline: none;
  padding: 10px 0;
  width: 100%;
  text-overflow: ellipsis;
}

.button{
  font-family: sans-serif;
  background: linear-gradient(to right, #98c8f0);
  font-size: 11px;
  border: none;
  color: rgb(255, 255, 255);
  cursor: pointer;
  font-weight: 600;
  padding: 10px 20px;
  letter-spacing: 2px;
  outline: none;
  margin: 10px 10px 2px 0;
  display: block;
  text-align: center;
  width : 100%;
}

.button:hover {
  opacity: 0.8;
}
.desc {
  font-size: 3px;
  font: dimgrey;
}

</style>
