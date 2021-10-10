import { Line, mixins } from 'vue-chartjs';
import globalOptionsMixin from "@/components/Charts/globalOptionsMixin";
export default {
  name: 'line-chart',
  extends: Line,
  mixins: [mixins.reactiveProp, globalOptionsMixin],
  props: {
    label: {
      type: String
    },
    chartData: {
      type: Array
    },
    options: {
      type: Object
    },
    chartColors: {
      type: Object
    }
  },
  data() {
    return {
      ctx: null
    };
  },
  mounted() {
    this.$watch(
      'chartData',
      (newVal, oldVal) => {
        if (!oldVal) {
          const dates = this.chartData.map(d => d.date);
          const totals = this.chartData.map(d => d.total);
          this.renderChart({
            labels: dates,
            datasets: [
              {
                label: this.label,
                data: totals,
              }
            ]
          });
        }
      },
      { immediate: true }
    );
  }
};
