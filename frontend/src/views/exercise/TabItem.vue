<template>
  <button @click="$emit('input', id)" :class="[active, 'tab']">
    {{ label }}
  </button>
</template>

<script>
export default {
  props: {
    id: Number,
    label: String,
    value: Number
  },
  computed: {
    active() {
      return this.value === this.id ? 'active' : false
    }
  }
}
</script>

<style scoped>
.tab {
  border-radius: 5px;
  background: rgb(255,255,255,0);
  color: #000000;
  line-height: 24px;
  margin-left: 12px;
  margin-right: 12px;
  padding-left: 12px;
  padding-right: 12px;
  
}
.tab:hover {
  background: #6AAFE6;
  transform: scale(1.2);
  color: #ffffff;
}
.active {
  background: #6AAFE6;
  transform: scale(1.2);
  color: #ffffff;
}
</style>