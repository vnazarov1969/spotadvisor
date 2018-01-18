// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'

import App from './App'
import router from './router'

Vue.use(VueAxios, axios)
// Dev
if (window.location.host.match(/localhost:8080/i)) {
  Vue.axios.defaults.baseURL = 'https://localhost:8443'
}
// Dev end
Vue.axios.defaults.headers.post['Content-Type'] = 'application/json; charset=utf-8'
Vue.axios.defaults.withCredentials = true
Vue.use(Vuetify)
Vue.config.productionTip = false

console.log('hours, utcHours', (new Date()).getTimezoneOffset())

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
