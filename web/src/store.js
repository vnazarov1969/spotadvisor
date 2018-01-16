  import Vue from 'vue'
  import Vuex from 'vuex'

  Vue.use(Vuex)
  // the root, initial state object
  const state = {
    profile: {
      userId: null,
      displayName: null,
      pictureUrl: null
    },
    cities: [],
    spot: {},
    spots: [
    ],
    spotStatuses: [
    ],
    stuffs: [
    ],
    workout: {},
    workouts: [
    ]
  }

  // define the possible mutations that can be applied to our state
  const mutations = {
    startDate (state, value) {
      state.workout.startTime = value
    },
    initWorkout (state) {
      state.workout = {
        id: null,
        spotId: null,
        stuffIds: [],
        startTime: null,
        end_time: null,
        notes: '',
        funRate: 0,
        conditionRate: 0
      }
    },
    initSpot (state) {
      state.spot = {
        id: null,
        city: {
          id: -1
        },
        name: null,
        nameLocal: null,
        latitude: null,
        longitude: null
      }
    },
    setSpot (state, id) {
      var spot = state.spots.find(obj => obj.id === id)
      if (spot != null) {
        state.spot = spot
      } else {
        this.commit('initSpot')
      }
    }
  }

  // actions
  const actions = {
    signin ({ commit }) {
      var baseUrl = Vue.axios.defaults.baseURL
      var fm = document.createElement('form')
      var scope = document.createElement('input')
      scope.value = 'user_posts'
      scope.name = 'scope'
      fm.appendChild(scope)
      fm.action = baseUrl + '/signin/facebook'
      fm.method = 'POST'
      document.body.appendChild(fm)
      fm.submit()
    },
    signout ({ commit }) {
      var baseUrl = Vue.axios.defaults.baseURL
      window.location.replace(baseUrl + '/signout')
    },
    fetchProfile ({ commit }) {
      Vue.axios.get('/api/profile')
      .then((response) => {
        console.log(response.data)
        this.state.profile = response.data
        if (this.state.profile) {
          this.dispatch('fetchCities')
          this.dispatch('fetchSpots')
          this.dispatch('fetchStuffs')
          this.dispatch('fetchWorkouts')
          this.commit('initWorkout')
        }
      })
    },
    fetchCities ({ commit }) {
      Vue.axios.get('/api/cities').then((response) => {
        console.log(response.data)
        this.state.cities = response.data
      })
    },
    fetchSpots ({ commit }) {
      Vue.axios.get('/api/spots').then((response) => {
        console.log(response.data)
        this.state.spots = response.data
      })
    },
    fetchStuffs ({ commit }) {
      Vue.axios.get('/api/stuffs').then((response) => {
        console.log(response.data)
        this.state.stuffs = response.data
      })
    },
    fetchWorkouts ({ commit }) {
      Vue.axios.get('/api/workouts').then((response) => {
        console.log(response.data)
        this.state.workouts = response.data
      })
    },
    addWorkout ({ commit }) {
      if (this.state.workout != null) {
        this.state.workout.id = null
      }
      this.dispatch('saveWorkout')
    },
    saveWorkout ({ commit }) {
      Vue.axios.post('/api/workouts', JSON.stringify(this.state.workout))
      .then((response) => {
        console.log(response)
        this.state.workout = response.data
        this.dispatch('fetchWorkouts')
      })
      .catch(function (error) {
        console.log(error)
      })
    },
    cancelWorkout ({ commit }) {
      this.commit('initWorkout')
    },
    saveSpot ({ commit }) {
      Vue.axios.post('/api/spots', JSON.stringify(this.state.spot))
      .then((response) => {
        console.log(response)
        this.state.spot = response.data
        this.dispatch('fetchSpots')
        this.dispatch('fetchSpotStatuses')
      })
      .catch(function (error) {
        console.log(error)
      })
    },
    fetchSpotStatuses ({ commit }) {
      Vue.axios.get('/api/spot_statuses').then((response) => {
        console.log(response.data)
        this.state.spotStatuses = response.data
      })
    }
  }

  // create the Vuex instance by combining the state and mutations objects
  // then export the Vuex store for use by our components
  export default new Vuex.Store({
    state,
    mutations,
    actions
  })
