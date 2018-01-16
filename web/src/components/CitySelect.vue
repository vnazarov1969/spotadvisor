<template>
  <v-select
    label="Area"
    v-model="item"
    :items="items"
    item-text="name"
    item-value="name"
    combobox
    single-line
    hide-details
    return-object
    autocomplete
    required
    :rules="rules"
    :disabled=readonly
  ></v-select>
</template>
<script type="text/javascript">
  export default {
    name: 'city-select',
    components: {},
    props: {
      value: {
        type: Number,
        default: -1
      },
      readonly: {
        type: Boolean,
        default: false
      }
    },
    computed: {
      items () {
        return this.$store.state.cities
      },
      item: {
        get () {
          return this.items
           ? this.items.find(obj => obj.id === this.value) : null
        },
        set (value) {
          if (typeof value === 'object') {
            this.$emit('input', value.id)
          }
        }
      },
      rules () {
        return [
          (v) => !!v || 'City is required'
        ]
      }
    }
  }
</script>