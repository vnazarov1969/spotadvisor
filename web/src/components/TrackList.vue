<template>
  <v-container fluid pa-0>
    <v-card>
      <v-toolbar color="white" light dense flat >
        <v-toolbar-title>{{vState.title}}
<!--           <a href="#" style="margin:3px">windsurfing</a>
          in
          <a href="#" style="margin:3px">Saint-Petersburg</a>
 -->
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>more_vert</v-icon>
        </v-btn>
      </v-toolbar>

      <v-track-form :vState="vState"></v-track-form>
   </v-card>
   <!-- <v-card :height="listHeight" style="overflow-y:auto"> -->
   <v-card :height="listHeight" style="overflow-y:auto; min-height:16rem">
     <v-list three-line dense>
      <template v-for="item in this.workouts">
        <v-subheader v-if="item.header" v-text="item.header"></v-subheader>
        <v-divider v-else-if="item.divider" v-bind:inset="item.inset"></v-divider>
        <v-list-tile avatar v-else v-bind:key="item.id" @click="setWorkout(item)">
<!--           <v-list-tile-avatar>
            <img v-bind:src="item.avatar">
          </v-list-tile-avatar>
 -->
           <v-list-tile-content>
            <v-list-tile-title>Windsurfing at <b>{{item.spotName}}</b> </v-list-tile-title>
            <v-list-tile-sub-title>{{item.notes}}</v-list-tile-sub-title>
            <v-list-tile-sub-title v-if="item.notes == ''"><p></p></v-list-tile-sub-title>
            <v-show-ratings max="3" :value="item.conditionRate" disabled=true ></v-show-ratings>
          </v-list-tile-content>
          <v-list-tile-action>
          <v-list-tile-action-text>{{formatDate(item.startTime)}}</v-list-tile-action-text>
          <v-list-tile-action-text>{{formatDuration(item)}}</v-list-tile-action-text>
          <v-show-ratings max="3" icon='thumb_up' :value="item.funRate" disabled=true ></v-show-ratings>
          </v-list-tile-action>
        </v-list-tile>
      </template>
    </v-list>
   </v-card>

  </v-container>
</template>

<script>
  import vTrackForm from './TrackForm.vue'
  import vShowRatings from './ShowRatings.vue'
  import moment from 'moment'

  export default {
    name: 'track-list',
    components: {vTrackForm, vShowRatings},
    props: {
      listHeight: {}
    },
    data: () => ({
    }),
    computed: {
      workouts () {
        return this.$store.state.workouts.sort(function (a, b) {
          return Date.parse(b.startTime) - Date.parse(a.startTime)
        })
      },
      vState () {
        var workout = this.$store.state.workout
        var isNew = ((workout == null) || (workout.id == null) || (workout.id === 0))
        var isFinish = ((workout != null) && (workout.id != null) && (workout.endTime == null))
        var isSave = ((workout != null) &&
         (workout.id != null) &&
         (workout.id !== 0) &&
         (workout.endTime != null))
        var title = isNew ? 'Start new track' : isFinish ? 'Finish track' : 'Track at ' + this.formatDate(workout.startTime)
        return {
          title: title,
          isNew: isNew,
          isFinish: isFinish,
          isSave: isSave
        }
      }
      // ,
      // workoutTypes () {
      //   return ['windsurfing', 'kitesurfing', 'swimming', 'diving']
      // }
      // ,
      // listHeight () {
      //   return '16rem'
      // }
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
      formatNotes (notes) {
        if (!notes) {
          return '<p></p>'
        } else {
          return notes
        }
      },
      setWorkout (item) {
        this.$store.state.workout = item
      }
    }
  }
</script>
