import Vue from 'vue'
import Router from 'vue-router'
import Full from '@/containers/Full'
import Frame from '@/containers/Frame'
import Hello from '@/components/Hello'
import Dashboard from '@/components/Dashboard'
import vDashboardFB from '@/components/DashboardFB'
import vTrackList from '@/components/TrackList'
import vSpotList from '@/components/SpotList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/_=_',
      redirect: '/'
    },
    {
      path: '/',
      redirect: '/dashboard',
      name: 'Home',
      component: Full,
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          component: Dashboard
        },
        {
          path: 'my_track',
          name: 'Track',
          component: vTrackList
        },
        {
          path: 'spots',
          name: 'Spots',
          component: vSpotList
        },
        {
          path: 'hello',
          name: 'Hello',
          component: Hello
        }
      ]
    },
    {
      path: '/frame',
      redirect: '/frame/track',
      name: 'Frame',
      component: Frame,
      children: [
        {
          path: 'track',
          name: 'Track',
          component: vDashboardFB
        },
        {
          path: 'spots',
          name: 'Spots',
          component: vSpotList
        }
      ]
    }
  ]
})
