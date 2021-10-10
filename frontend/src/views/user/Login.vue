<template>
  <div class="entire">
      <VueRealWave wavePosition="bottom" :waveLayerCount="4" :space="80" :waveGap="150" waveMode="uniform" waveBackgroundColor="#6AAFE6">
        <center><img src = "../../assets/images/logo2.png" alt="logo" width="150" height="150" ></center>
      </VueRealWave>

      <main class="box">
        <form>
          <div class="inputBox">
            <input type="text" v-model="email" placeholder="이메일" required/>
          </div>
          <div class="inputBox">
            <input type="password" v-model="password" placeholder="비밀번호" required/>
          </div>
          <div>
            <button class="button" v-on:click="login">로그인</button>
          </div>
          <div id='sign-up-wrap'>
            <div class="sign-up">아직 회원이 아니신가요? </div>
            <router-link class="sign-up" id='link' to="/user/register">회원가입</router-link>
          </div>
          
        </form>
      </main>

  </div>


</template>

<script>
import VueRealWave from 'vue-real-wave';
import axios from "axios";

const storage = localStorage;

export default {
  name: "Login",
  components:{VueRealWave},
  data: function () {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    login() {
      storage.setItem("jwt-token", "");
      axios
        .post('http://i5a105.p.ssafy.io:8080/account/login', {
            email: this.email,
            password: this.password
        })
        .then(res => {
          if(res.data.message)
          {
            this.$store.commit('SET_SNACKBAR', {
              text: "일치하는 회원 정보가 없습니다.",
              color: '#8EC0E4',
              location: 'bottom',
            })
            return;
          }
          else
          {
            this.$store.commit('SET_SNACKBAR', {
              text: "로그인 되었습니다.",
              color: '#8EC0E4',
              location: 'bottom',
            })
            storage.setItem("jwt-token", res.data.accessionToken);
            this.$router.replace('record')
          }

        })
        .catch(err => {
          this.$store.commit('SET_SNACKBAR', {
            text: "메일과 비밀번호를 확인하세요.",
            color: '#8EC0E4',
            location: 'bottom',
          })
        })
        .then(() => {
        })
    }
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.box {
  margin: 0; 
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: left;
  background-color: rgb(255, 255, 255, 0);
  width: 250px;
  border-radius: 0px;
  
}

.inputBox label {
  color: #fff;
}

.inputBox input {
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

.button {
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
  margin-bottom: 15px;
  margin-top: 10px;
  display: block;
  text-align: center;
  width : 100%;
}

.sign-up{
  font-size: 12px;
  display: inline;
}
#sign-up-wrap{
  margin-top: 25px;
  position: relative;
  float: right;
} 

#link{
  border-bottom: 1px solid #6AAFE6;
  color : #6AAFE6


}

.button:hover {
  opacity: 0.8;
}
</style>
