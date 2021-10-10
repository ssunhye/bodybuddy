<template>
  <div id="app">
    <router-view></router-view>
    <SnackBar></SnackBar>
  </div>
</template>

<script>
import "./components/css/style.scss";
import SnackBar from './components/SnackBar';

export default {
  components: { SnackBar },
  name: 'App',
};
</script>
