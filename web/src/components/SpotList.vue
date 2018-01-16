<template>
  <v-container fluid pa-0>
    <v-card>
      <v-toolbar color="white" light dense flat >
        <v-toolbar-title>Spots 
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-menu open-on-hover left min-width='20rem'>
          <v-btn icon slot="activator" light>
            <v-icon>more_vert</v-icon>
          </v-btn>
          <v-list dense>
            <template v-for="item in this.menus">
            <v-divider v-if="item.divider"></v-divider>
            <v-list-tile v-else :key="item.title" @click="runAction(item)">
              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
            </v-list-tile>
            </template>
          </v-list>
        </v-menu>
      </v-toolbar>
      <v-dialog v-model="dialog">
        <v-spot-form v-on:close="dialog = false"></v-spot-form>
      </v-dialog> 
      <v-list three-line dense>
        <template v-for="item in this.spots">
          <v-subheader v-if="item.header" v-text="item.header"></v-subheader>
          <v-divider v-else-if="item.divider" v-bind:inset="item.inset"></v-divider>
          <v-list-tile v-else v-bind:key="item.id" @click="openSpot(item.id)">
             <v-list-tile-content>
              <v-list-tile-title><b>{{item.displayName}}</b></v-list-tile-title>
              <v-list-tile-sub-title><p></p></v-list-tile-sub-title>
              <v-show-ratings max="3" icon='star' :value="item.avgCondition" disabled=true ></v-show-ratings>
            </v-list-tile-content>
            <v-list-tile-action>
            <v-list-tile-action-text>{{formatDate(item.lastWorkout)}}</v-list-tile-action-text>
            <v-show-ratings max="3" icon='person' :value="item.avgPeople" disabled=true ></v-show-ratings>
            <v-show-ratings max="3" icon='thumb_up' :value="item.avgFun  * 1" disabled=true ></v-show-ratings>
            </v-list-tile-action>
          </v-list-tile>
        </template>
      </v-list>
    </v-card>

  </v-container>
</template>

<script>
  import vShowRatings from './ShowRatings.vue'
  import vSpotForm from './SpotForm.vue'

  import moment from 'moment'
  const ADD_SPOT_ACTION = 'ADD_SPOT'

  export default {
    name: 'spot-list',
    components: {vShowRatings, vSpotForm},
    props: {
      listHeight: {}
    },
    data: () => ({
      dialog: false,
      menus: [
        {title: 'Search...'},
        {divider: true},
        {title: 'Add spot', action: ADD_SPOT_ACTION}
      ]
    }),
    mounted () {
      this.$store.dispatch('fetchSpotStatuses')
    },
    computed: {
      spots () {
        var arr = this.$store.state.spotStatuses.slice()
        arr.push({header: 'Today', lastWorkout: moment({hour: 24}), displayName: ''})
        arr.push({header: 'Later', lastWorkout: moment({hour: 0}), displayName: ''})
        return arr.sort(function (a, b) {
          if (moment(b.lastWorkout).isSame(moment(), 'day') || moment(a.lastWorkout).isSame(moment(), 'day')) {
            return (moment(b.lastWorkout ? b.lastWorkout : 0) - moment(a.lastWorkout ? a.lastWorkout : 0)) || a.displayName.localeCompare(b.displayName)
          } else {
            return a.displayName.localeCompare(b.displayName)
          }
        })
      }
    },
    methods: {
      formatDate (dateTime) {
        var date = new Date(dateTime)
        var limit = new Date()
        limit.setDate(limit.getDate() - 7)
        if (limit < date) {
          return date.toLocaleString(navigator.languages, {weekday: 'short', hour: '2-digit', minute: '2-digit'})
        }
        return date.toLocaleString(navigator.languages, {month: 'long', day: 'numeric'})
      },
      formatDuration (item) {
        var hours = (item.endTime && item.startTime) ? moment(item.endTime).diff(moment(item.startTime), 'hours') : -1
        return hours >= 0 ? hours + 'h' : 'not finished'
      },
      openSpot (id) {
        this.$store.commit('setSpot', id)
        this.dialog = true
      },
      addSpot () {
        this.openSpot(0)
      },
      runAction (item) {
        switch (item.action) {
          case ADD_SPOT_ACTION:
            this.addSpot()
            break
        }
      }
    }
  }
</script>
