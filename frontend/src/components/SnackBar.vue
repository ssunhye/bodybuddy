<template>
    <v-snackbar
        :bottom="snackbar.location === 'bottom'"
        :color="snackbar.color"
        :timeout="timeout"
        :top="snackbar.location === 'top'"
        v-model="snackbar.open" class="p-4">
        <div class="text-center m-0">{{snackbar.text}}</div>
    </v-snackbar>
</template>

<script>
    export default {
        name: "SnackBar",
        data() {
            return {
                timeout: 1300,
            }
        },
        computed: {
            snackbar() {
                return this.$store.state.common.snackbar;
            }
        },
    }
</script>

<style scoped>
</style>
