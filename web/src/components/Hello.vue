<template>
  <v-container fluid>
    <v-slide-y-transition mode="out-in">
      <v-layout column align-center>
    <v-stuff-select v-model="ids"></v-stuff-select>
    <v-date-time-picker v-model="dateString" label='Date' icon='event'></v-date-time-picker>
    <v-date-time-picker v-model="dateString" timeMode label='since' icon='access_time'></v-date-time-picker>
    <v-flex xs11 sm5>
      <v-dialog
        persistent
        v-model="modal"
        lazy
        full-width
        width="290px"
      >
        <v-text-field
          slot="activator"
          label="Picker in dialog"
          v-model="date"
          prepend-icon="event"
           v-on:keypress.enter="modal=true"
          readonly
        ></v-text-field>
     <v-card>
        <v-card-title>
          <span class="headline">Define Stuff</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout  column wrap>
              <v-flex xs12 sm6>
                <v-select
                  label="Board"
                  required
                  :items="['65-84', '85-95', '96-110', '110-130','130+']"
                ></v-select>
              </v-flex>
              <v-flex xs12 sm6>
                <v-select
                  label="Sail"
                  required
                  :items="['3.7-4.2', '4.2-4.7', '4.8-5.5', '5.5-7.0','7.0-8.5','8.5+']"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="modal = false">Close</v-btn>
          <v-btn color="blue darken-1" flat @click.native="modal = false">Save</v-btn>
        </v-card-actions>
      </v-card>
       </v-dialog>
    </v-flex>      </v-layout>
    </v-slide-y-transition>
  </v-container>
</template>

<script type="text/javascript">
  import vShowRatings from './ShowRatings.vue'
  import vStuffSelect from './StuffSelect.vue'
  import vDateTimePicker from './DateTimePicker.vue'

  export default {
    name: 'hello',
    components: {vShowRatings, vStuffSelect, vDateTimePicker},
    data: () => ({
      modal: false,
      date: null,
      dateString: null,
      timeString: null,
      ids: [2]
    }),
    computed: {
      ratingNumber: {
        get () {
          return this.funRate * 1
        },
        set (value) {
          this.funRate = value.toString()
        }
      }
    },
    methods: {
      updateRating (value) {
        this.funRate = value.toString()
      }
    }
  }
</script>
