<template>
  <v-container fluid pa-2>
    <v-layout row wrap>
      <v-flex d-flex pa-2 sm6>
        <!-- <v-track-list list-height="16rem"/> -->
        <v-track-list/>
      </v-flex>
      <v-flex pa-2 sm3>
        <v-card>
          <v-spot-list :vState="vState"/>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import vTrackList from './TrackList.vue'
  import vSpotList from './SpotList.vue'

  export default {
    components: {vTrackList, vSpotList},
    data: () => ({
      show: false
    }),
    computed: {
      vState () {
        return {
          scope: 'Active'
        }
      }
    }
  }
</script>
