<template>
<div>
    <div v-for="(item, $index) in list" :key="$index">
  <!-- Hacker News item loop -->
    </div>

    <infinite-loading @infinite="infiniteHandler" spinner='waveDots'></infinite-loading>
</div>
</template>

<script>
import axios from 'axios';
import InfiniteLoading from 'vue-infinite-loading';

const api = '//hn.algolia.com/api/v1/search_by_date?tags=story';

export default {
  component: {
      InfiniteLoading
  },
  data() {
    return {
      page: 1,
      list: [],
    };
  },
  methods: {
    infiniteHandler($state) {
      axios.get(api, {
        params: {
          page: this.page,
        },
      }).then(({ data }) => {
        if (data.hits.length) {
          this.page += 1;
          this.list.push(...data.hits);
          $state.loaded();
        } else {
          $state.complete();
        }
      });
    },
  },
};
</script>
