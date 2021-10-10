<template>
  <card class="card-stats" :show-footer-line="true" style="height:130px">
    <b-row>
      <b-col cols="3" v-if="img">
        <slot name="img">
          <img :src="require('@/assets/images/' + img)" style="width:50px"/>
        </slot>
      </b-col>
      <b-col>
        <slot>
          <h5 class="card-title text-uppercase text-muted mb-0" v-if="title">{{title}}</h5>
          <span class="h2 font-weight-bold mb-0" v-if="subTitle">{{subTitle}}</span>
        </slot>
      </b-col>
    </b-row>
    <p class="mt-2 mb-0 text-sm">
      <slot name="footer">

      </slot>
    </p>
  </card>
</template>
<script>
  import Card from './Card.vue';

  export default {
    name: 'stats-card',
    components: {
      Card
    },
    props: {
      type: {
        type: String,
        default: 'primary'
      },
      img: String,
      title: String,
      subTitle: String,
    }
  };
</script>
<style></style>
