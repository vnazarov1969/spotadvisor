<template>
  <v-container fluid>
    <v-navigation-drawer
      fixed
      :mini-variant="miniVariant"
      :clipped="clipped"
      v-model="drawer"
      persistent
      app
    >
      <v-list>
        <v-list-tile
          value="true"
          v-for="(item, i) in menus"
          :key="i"
          exact
          :to="item.route"
        >
          <v-list-tile-action>
            <v-icon light v-html="item.icon"></v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title v-text="item.title"></v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar fixed color="primary" dark app :clipped-left="clipped" dense flat>
      <v-toolbar-side-icon @click.stop="drawer = !drawer" ></v-toolbar-side-icon>
      <v-toolbar-title v-text="title"></v-toolbar-title>
      <v-spacer></v-spacer>
      <!-- {{userName}} -->
      <v-tooltip bottom color="info">
        <span>{{userName}}</span>
        <v-btn
          icon
          @click.stop="profile == null ? signin() : signout()"
          slot="activator"
        >
        <v-avatar v-show="profile" size="2.2rem">
          <img :src="pictureUrl">
        </v-avatar>
        <v-icon v-show="!profile">person_outline</v-icon>
        </v-btn>
      </v-tooltip>
    </v-toolbar>
    <v-content>
      <router-view></router-view>
    </v-content>
    <v-footer :fixed="fixed" app>
      <span>&copy; 2017 SpotAdvisor</span>
    </v-footer>
  </v-container>
</template>

<script>
  export default {
    name: 'full',
    data () {
      return {
        clipped: true,
        drawer: false,
        fixed: true,
        menus: [
          {
            icon: 'bubble_chart',
            title: 'Hello',
            route: '/hello'
          },
          {
            icon: 'bubble_chart',
            title: 'Dashboard',
            route: '/dashboard'
          },
          {
            icon: 'bubble_chart',
            title: 'My Track',
            route: '/my_track'
          },
          {
            icon: 'bubble_chart',
            title: 'Spots',
            route: '/spots'
          }
        ],
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
