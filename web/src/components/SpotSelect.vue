<template>
  <v-select
    label="Spot"
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
  ></v-select>
</template>
<script type="text/javascript">
  export default {
    name: 'spot-select',
    components: {},
    props: {
      value: {
        type: Number,
        default: -1
      }
    },
    computed: {
      items () {
        return this.$store.state.spots
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
          (v) => !!v || 'Spot is required'
        ]
      }
    }
  }
</script>