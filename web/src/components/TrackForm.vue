<template>
    <v-form v-model="valid" ref="form">
      <v-layout column mx-3 mb-3>
<!--         <v-flex>
          <v-layout row wrap >
            <v-flex xs3>
            <v-type-select v-model="workout.spotId"></v-type-select>
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs8>
            <v-spot-select v-model="workout.spotId"></v-spot-select>
            </v-flex>
          </v-layout>
        </v-flex>
 -->
        <v-flex>
            <v-spot-select v-model="workout.spotId"></v-spot-select>
        </v-flex>
        <v-flex>
          <v-stuff-select v-model="workout.stuffIds"></v-stuff-select>
        </v-flex>
        <v-flex mt-3>
          <v-layout row wrap >
            <v-show-ratings max="3" caption="Wind:" v-model="conditionRate"></v-show-ratings>
            <v-spacer></v-spacer>
            <v-show-ratings max="3" caption="Fun:" icon='thumb_up' v-model="funRate" ></v-show-ratings>
          </v-layout>
        </v-flex>
        <v-flex>
          <v-card-actions>
            <v-btn round v-show="vState.isNew" color="primary" dark small @click="start">Start</v-btn>
            <v-btn round v-show="vState.isFinish" color="primary" dark small @click="finish">Finish</v-btn>
            <v-btn round v-show="vState.isSave" color="primary" dark small @click="save">Save</v-btn>
            <v-btn round v-show="vState.isSave" color="light" small @click="cancel">Cancel</v-btn>
            <v-spacer></v-spacer>
            <v-btn icon @click.native="show = !show">
              <v-icon>{{ show ? 'keyboard_arrow_down' : 'keyboard_arrow_up' }}</v-icon>
            </v-btn>
          </v-card-actions>
        </v-flex>
        <v-flex>
        <v-slide-y-transition>
          <v-layout column>
            <v-flex>
              <v-text-field
                multi-line
                rows=2
                label="Notes"
                v-model="workout.notes"
                v-show="show"
                single-line
                hide-details
              ></v-text-field>
            </v-flex>
            <v-flex>
              <v-layout row wrap >
                <v-flex xs4 sm3>
                  <v-date-time-picker v-model="startTime" v-show="show" label='date' icon='event'></v-date-time-picker>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs4 sm3>
                  <v-date-time-picker v-model="startTime" v-show="show" timeMode label='since' icon='access_time'></v-date-time-picker>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs4 sm3>
                  <v-date-time-picker v-model="workout.endTime" v-show="show" timeMode label='to' icon='access_time'></v-date-time-picker>
                </v-flex>
              </v-layout>
            </v-flex>
          </v-layout>
        </v-slide-y-transition>
        </v-flex>
    </v-layout>
  </v-form>
</template>
<script type="text/javascript">
  import vShowRatings from './ShowRatings.vue'
  import vStuffSelect from './StuffSelect.vue'
  import moment from 'moment'
  import vSpotSelect from './SpotSelect.vue'
  import vTypeSelect from './TypeSelect.vue'
  import vDateTimePicker from './DateTimePicker.vue'
  export default {
    name: 'track-form',
    components: {vShowRatings, vStuffSelect, vSpotSelect, vDateTimePicker, vTypeSelect},
    props: {
      vState: {}
    },
    data: () => ({
      show: false,
      valid: null
    }),
    computed: {
      workout: {
        get () {
          return this.$store.state.workout
        },
        set (value) {
          this.$store.state.workout = value
        }
      },
      startTime: {
        get () {
          return this.workout.startTime
        },
        set (value) {
          this.workout.startTime = value
          if (this.workout.endTime != null) {
            var start = moment(value)
            var end = moment(this.workout.endTime)
            end.set({'year': start.year(), 'month': start.month(), 'date': start.date()})
            this.workout.endTime = end.format()
          }
        }
      },
      funRate: {
        get () {
          return this.workout.funRate ? this.workout.funRate * 1 : 0
        },
        set (value) {
          this.workout.funRate = value ? value.toString() : null
        }
      },
      conditionRate: {
        get () {
          return this.workout.conditionRate
        },
        set (value) {
          this.workout.conditionRate = value
        }
      }
    },
    methods: {
      validate () {
        return this.$refs.form.validate()
      },
      start () {
        if (this.validate()) {
          this.$store.dispatch('addWorkout')
        }
      },
      save () {
        if (this.validate()) {
          this.$store.dispatch('saveWorkout')
        }
      },
      finish () {
        if (this.validate()) {
          this.workout.endTime = moment().format()
          this.$store.dispatch('saveWorkout')
        }
      },
      cancel () {
        alert('Cancel changes?')
        this.$store.dispatch('cancelWorkout')
      }
    }
  }
</script>
