<template>
  <v-select
    label="Stuff"
    v-bind:items="stuffs"
    v-model="stuffObjects"
    item-text="title"
    item-value="title"
    multiple
    chips
    max-height="auto"
    autocomplete
    return-object
    single-line
    hide-details
  >
    <template slot="selection" slot-scope="data">
      <v-chip
        @input="data.parent.selectItem(data.item)"
        :selected="data.selected"
        class="chip--select-multi"
        :key="JSON.stringify(data.item)"
        small
      >
        {{ data.item.title}}
      </v-chip>
    </template>
    <template slot="item" slot-scope="data">
      <template v-if="typeof data.item !== 'object'">
        <v-list-tile-content>{{data.item}}</v-list-tile-content>
      </template>
      <template v-else>
          <v-list-tile-action>
            <v-checkbox :inputValue="data.parent.selectedItems.indexOf(data.item) >= 0"></v-checkbox>
          </v-list-tile-action>
          <v-list-tile-content>
          <v-list-tile-title> {{data.item.title}} </v-list-tile-title>
        </v-list-tile-content>
      </template>
    </template>
  </v-select>
</template>
<script type="text/javascript">
  export default {
    name: 'stuff-select',
    components: {},
    props: {
      value: {
        type: Array,
        default: () => []
      }
    },
    data: () => ({}),
    computed: {
      stuffs () {
        const templates = {1: 'Board ', 2: 'Sail '}
        return this.$store.state.stuffs.map(function (obj) {
          obj.title = templates[obj.stuff_type_id] + obj.name
          return obj
        })
      },
      stuffObjects: {
        get () {
          return this.value ? this.value.map(id =>
            this.stuffs.find(obj => obj.id === id)) : []
        },
        set (value) {
          var result = value.map(obj => obj.id)
          this.$emit('input', result)
        }
      }
    },
    methods: {
      isActive (data) {
        return true
      }
    }
  }
</script>