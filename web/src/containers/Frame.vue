<template>
  <v-container fluid>
    <v-content>
      <router-view></router-view>
    </v-content>
    <v-footer fixed app>
      <span>&copy; 2017 SpotAdvisor</span>
    </v-footer>
  </v-container>
</template>

<script>
  export default {
    name: 'frame',
    data () {
      return {}
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
      this.$store.dispatch('fetchProfile')
    },
    mounted () {
    },
    methods: {
      signin () {
        this.$store.dispatch('signin')
      },
      signout () {
        this.$store.dispatch('signout')
      }
    }
  }
</script>
<style>
  .container.fluid {
    padding: 0;
  }
</style>

