import Vue from 'vue'
import Vuex from 'vuex'
import common from './common'

Vue.use(Vuex)

const state = {
    isUser: false,
}

export default new Vuex.Store({
    modules: {
        common,
    }
})