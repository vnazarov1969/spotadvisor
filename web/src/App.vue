<template>
  <v-app light>
    <router-view></router-view>
  </v-app>
</template>

<script>
  import vFull from './containers/Full'
  export default {
    components: {vFull},
    data () {
      return {
        clipped: true,
        drawer: false,
        fixed: true,
        // items: [
        //   {
        //     icon: 'bubble_chart',
        //     title: 'Home',
        //     route: '/'
        //   },
        //   {
        //     icon: 'bubble_chart',
        //     title: 'Dashboard',
        //     route: '/dashboard'
        //   }
        // ],
        miniVariant: false,
        title: 'SpotAdvisor'
      }
    },
    computed: {
      profile () {
        return this.$store.state.profile && this.$store.state.profile.userId ? this.$store.state.profile : null
      },
      userName () {
        return this.profile ? this.profile.displayName : 'Connect with Facebook'
      },
      pictureUrl () {
        return this.profile ? this.profile.pictureUrl : ''
      },
      baseUrl () {
        return this.$http.defaults.baseURL
      }

    },
    created () {
    },
    mounted () {
    },
    methods: {
    }
  }
</script>
