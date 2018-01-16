<template>
  <div class="star-funRate">
     <label class="star-rating__star">{{caption}}</label>
     <label
     class="star-rating__star"
     v-for="funRate in ratings"
     :class="{ 'is-selected' : isSelected(funRate), 'is-disabled': disabled}"
     v-on:mouseover="star_over(funRate)"
     v-on:mouseout="star_out"
     v-on:click.prevent="set(funRate)">
     <input ref="input" class="star-funRate star-rating__checkbox" type="radio" v-bind:value="value" v-on:input="updateValue"/>
     <v-icon>{{icon}}</v-icon>
    </label>
  </div>
</template>

<script>
  export default {
    props: {
      value: {
        type: Number,
        default: 0
      },
      max: '',
      disabled: false,
      caption: '',
      icon: {
        type: String,
        default: 'star'
      }
    },
    data: function () {
      return {
        temp_value: null
      }
    },
    // watch: {
    //   'val': function (value) {
    //     this.$emit('input', value)
    //   }
    // },
    computed: {
      ratings: function () {
        if (!this.max) { return [1, 2, 3, 4, 5] }
        var numberArray = []
        for (var i = 1; this.max >= i; i++) {
          numberArray.push(i)
        }
        return numberArray
      }
    },
    methods: {
      isSelected: function (funRate) {
        var val = this.temp_value ? this.temp_value : this.value
        return ((val != null) && (val >= funRate))
      },
      updateValue: function (value) {
        this.$emit('input', value)
      },
      star_over: function (index) {
        if (this.disabled) {
          return
        }
        this.temp_value = index
      },
      star_out: function () {
        if (this.disabled) {
          return
        }
        this.temp_value = null
      },
      set: function (value) {
        if (this.disabled) {
          return
        }
        this.updateValue(value)
      }
    }
  }
</script>

<style>
  .star-funRate {
    display: inline-flex;
  }
  .star-funRate .star-rating__star {
    display: inline-block;
    padding: 2px;
    vertical-align: middle;
    line-height: 1;
    font-size: 1.1em;
    color: #ABABAB;
    transition: color .2s ease-out;
    cursor: pointer;
  }
  .star-funRate .star-rating__star .icon {
    color: #ABABAB;
    font-size: 1.1em;
  }
  .star-funRate input[type="radio"]{
    display: none;
  }
  .star-funRate .star-rating__star.is-selected {
    color: #FFD700;
  }
  .star-funRate .star-rating__star.is-selected .icon {
    color: #FFD700;
  }

</style>
