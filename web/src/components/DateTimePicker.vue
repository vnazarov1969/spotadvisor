<template>
 <v-menu
  lazy
  :close-on-content-click="false"
  v-model="menu"
  transition="scale-transition"
  offset-y
  full-width
  :nudge-right="40"
  max-width="290px"
  min-width="290px"
  >
    <v-text-field
      slot="activator"
      :label="label"
      :value="!timeMode?formattedDate:formattedTime"
      :prepend-icon="icon"
      readonly
      single-line
      hide-details
    ></v-text-field>
    <v-date-picker v-model="date" v-show="!timeMode">
      <template slot-scope="{ save, cancel }">
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn flat color="primary" @click="cancel">Cancel</v-btn>
          <v-btn flat color="primary" @click="save">OK</v-btn>
        </v-card-actions>
      </template>
    </v-date-picker>
    <v-time-picker v-model="time" v-show="timeMode">
      <template slot-scope="{ save, cancel }">
        <v-card-actions>
        <v-spacer></v-spacer>
          <v-btn flat color="primary" @click="cancel">Cancel</v-btn>
          <v-btn flat color="primary" @click="save">OK</v-btn>
        </v-card-actions>
      </template>
    </v-time-picker>
  </v-menu>
</div>
</template>

<script>
  import moment from 'moment'
  export default {
    props: {
      value: {
        type: String,
        default: null
      },
      label: String,
      icon: String,
      timeMode: Boolean
    },
    data: function () {
      return {
        menu: null
      }
    },
    computed: {
      date: {
        get () {
          var date = this.value ? moment.parseZone(this.value).local() : moment()
          return date.format('YYYY-MM-DD')
        },
        set (value) {
          if (value == null) {
            return
          }
          var date = this.value ? moment.parseZone(this.value).local() : moment()
          const [year, month, day] = value.split('-')
          date.set({'year': year, 'month': month - 1, 'date': day})
          this.updateValue(date.format())
        }
      },
      time: {
        get () {
          var date = this.value ? moment.parseZone(this.value).local() : moment()
          return date.format('hh:mma')
        },
        set (value) {
          if (value == null) {
            return
          }
          var date = this.value ? moment.parseZone(this.value).local() : moment()
          var time = moment(value, 'hh:mma')
          date.set({'hour': time.hour(), 'minute': time.minute()})
          this.updateValue(date.format())
        }
      },
      formattedDate () {
        return this.value ? moment.parseZone(this.value).toDate().toLocaleDateString(navigator.languages, {month: 'short', day: 'numeric'}) : null
      },
      formattedTime () {
        return this.value ? moment.parseZone(this.value).toDate().toLocaleTimeString(navigator.languages, {hour: '2-digit', minute: '2-digit'}) : null
      }
    },
    methods: {
      updateValue: function (value) {
        console.log('update 1', 'this.value', this.value, 'value: ', value)
        if ((new Date(value)).getTime() !== (new Date(this.value).getTime())) {
          this.$emit('input', value)
        }
      }
    }
  }
</script>

<style>
  
</style>