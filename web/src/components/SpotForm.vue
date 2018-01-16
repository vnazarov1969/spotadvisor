<template>
  <v-card>
    <v-toolbar color="white" light dense flat >
      <v-toolbar-title>{{title}} 
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon v-show="!edit" @click="edit = true">
        <v-icon>edit</v-icon>
      </v-btn>
      <v-btn icon @click="cancel">
        <v-icon>close</v-icon>
      </v-btn>
    </v-toolbar>

    <v-form v-model="valid" ref="form">
      <v-layout column mx-3>
        <v-flex>
          <v-text-field
            label="Name"
            v-model="spot.name"
            single-line
            hide-details
            required
            :readonly=!edit
          ></v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field
            label="Local Name"
            v-model="spot.nameLocal"
            single-line
            hide-details
            :readonly=!edit
          ></v-text-field>
        </v-flex>
        <v-flex>
          <v-city-select
          v-model="cityId"
          :readonly=!edit          
          ></v-city-select>
        </v-flex>
        <v-flex>
          <v-text-field
            label="Location"
            v-model="location"
            single-line
            hide-details
            required
            :readonly=!edit
          ></v-text-field>
        </v-flex>
        <v-flex>
          <v-card-actions>
            <!-- <v-btn round v-show="!edit" color="primary" dark small @click="edit = true">Edit</v-btn> -->
            <v-btn round v-show="edit" color="primary" dark small @click="save">Save</v-btn>
          </v-card-actions>
        </v-flex>
      </v-layout>
    </v-form>
  </v-card>
</template>
<script type="text/javascript">
  import vCitySelect from './CitySelect.vue'
  import vShowRatings from './ShowRatings.vue'
  import vTypeSelect from './TypeSelect.vue'
  export default {
    name: 'spot-form',
    components: {vShowRatings, vCitySelect, vTypeSelect},
    props: {
      vState: {}
    },
    data: () => ({
      edit: false,
      valid: null
    }),
    computed: {
      spot: {
        get () {
          return this.$store.state.spot
        },
        set (value) {
          this.$store.state.spot = value
        }
      },
      cityId: {
        get () {
          return this.spot.city != null ? this.spot.city.id : null
        },
        set (value) {
          this.spot.city.id = value
        }
      },
      location: {
        get () {
          return this.spot.latitude & this.spot.latitude ? this.spot.latitude + ',' + this.spot.longitude : ''
        }
      },
      set (value) {
        var location = value.split(',')
        this.spot.latitude = location[0]
        this.spot.longitude = location[1]
      },
      title () {
        return this.edit ? 'Spot changing' : 'Spot details'
      }
    },
    methods: {
      validate () {
        return this.$refs.form.validate()
      },
      save () {
        if (this.validate()) {
          this.$store.dispatch('saveSpot')
          this.edit = false
          this.$emit('close')
        }
      },
      cancel () {
        this.edit = false
        this.$emit('close')
      }
    }
  }
</script>
