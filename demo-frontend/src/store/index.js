import Vue from 'vue'
import VueX from 'vuex'

Vue.use(VueX)

export default new VueX.Store({
    state: {
        token: sessionStorage.getItem("token"),
        user: JSON.parse(sessionStorage.getItem("user"))
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token
            /**
             * 使用sessionStorage
             * 關掉瀏覽器的時候會被清除掉
             * 和 localStorage 相比
             * 比較利於保證實時性
             */

            sessionStorage.setItem("token", token)
        },
        SET_USER: (state, user) => {
            state.user = user
            sessionStorage.setItem("user", JSON.stringify(user))
        },
        REMOVE_INFO : (state) => {
            state.token = ''
            state.user = {
   }
            sessionStorage.setItem("token", '')
            sessionStorage.setItem("user", JSON.stringify(''))
        }
    },
    getters: {
    },
    actions: {
    },
    modules: {
    }
})