<template>
  <challenge-item-ig class="card-stats" :show-footer-line="true">
    <b-row>
      <b-col class="col-7 pr-0">
        <slot>
          <span class="h2 font-weight-bold mb-0" v-if="subTitle">{{subTitle}}</span>
          <span class="h5 font-weight-bold mb-0" v-if="subTitle"> 챌린지</span>
          <h5 class="card-title text-uppercase text-muted mb-0 mt-1" v-if="startDate">{{startDate}} ~ {{endDate}}</h5>
          <h5 class="text-muted right mb-0" v-if="!isOpen">참여 기간이 아닙니다.</h5>
        </slot>

        <p class="mt-4 mb-0 text-sm">
          <span class="text-danger mr-2" v-if="joined">🧡<strong>{{joined}}</strong>명 참가!</span>
          <span class="text-info ml-2 right" v-if="isOpen"><strong>{{diffDate}}</strong></span>
          <slot name="footer">
          </slot>
        </p>
      </b-col>
      <b-col>
        <slot>
          <div class="right">
            <img :src = img 
              height="100px"
              width="100px"
              object-fit: cover>
          </div>
        </slot>
      </b-col>
    </b-row>

  </challenge-item-ig>
</template>
<script>
  import ChallengeItemIg from './ChallengeItemIg.vue';
  const moment = require('moment'); 
  
  export default {
    name: 'challenge-item',
    components: {
      ChallengeItemIg
    },
    data() {
      return {
        diffDate: String,
        isOpen: Boolean,
      }
    },
    props: {
      type: {
        type: String,
        default: 'primary'
      },
      joined: null,
      subTitle: String,
      img: String,
      startDate: String,
      endDate: String,
    },
    mounted() {
      const start = moment(this.startDate);
      const end = moment(this.endDate);
      this.diffDate = 'D-'+end.diff(moment(), 'days');
      this.isOpen = moment().isBetween(start, end);
    }
  };
</script>
<style></style>
