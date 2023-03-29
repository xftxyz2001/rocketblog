import { createRouter, createWebHashHistory } from 'vue-router'

import admin from '@/views/admin'
import useradmin from '@/views/adminsub/useradmin'
import blogadmin from '@/views/adminsub/blogadmin'
import user from '@/views/user'
import front from '@/views/usersub/front'
import fronthomebody from '@/views/usersub/frontsub/fronthomebody'
import fronthotbody from '@/views/usersub/frontsub/fronthotbody'
import FrontHomeAllConcernMiddle from '@/views/usersub/frontsub/fronthomesub/middle/allconcernmiddle'
import FrontHomeFriendMiddle from "@/views/usersub/frontsub/fronthomesub/middle/friendmiddle.vue";
import FrontHomeLatestMiddle from "@/views/usersub/frontsub/fronthomesub/middle/latestmiddle.vue";
import FrontHomeMostLikeMiddle from "@/views/usersub/frontsub/fronthomesub/middle/mostlikemiddle.vue";
const routes = [

  {
    path: '/admin',
    name: 'admin',
    component: admin,
    children: [
      {
        path: 'useradmin',
        name: 'useradmin',
        component: useradmin
      },
      {
        path: 'blogadmin',
        name: 'blogadmin',
        component: blogadmin
      },
      {
        path: '',
        name: '',
        redirect: 'admin/useradmin'
      }
    ]


  },
  {

    path: '/user',
    name: 'user',
    component: user,
    children: [
      {
        path: '',
        name: '',
        redirect: { name: 'allconcern' }
      },
      {
        path: 'front',
        name: 'front',
        component: front,
        children: [
          {
            path: '',
            name: '',
            redirect: { name: 'allconcern' }
          },
          {
            path: 'home',
            name: 'home',
            component: fronthomebody,
            children: [
              {
                path: '',
                name: '',
                redirect: { name: 'allconcern' }
              },
              {
                path: 'allconcern',
                name: 'allconcern',
                component: FrontHomeAllConcernMiddle
              },
              {
                path: 'mostlike',
                name: 'mostlike',
                component: FrontHomeMostLikeMiddle
              },
              {
                name: 'latest',
                path: 'latest',
                component: FrontHomeLatestMiddle
              },
              {
                path: 'friend',
                name: 'friend',
                component: FrontHomeFriendMiddle
              }
            ]
          },
          {
            path: 'hot',
            name: 'hot',
            component: fronthotbody
          }
        ]
      }
    ]
  }
  ,
  {
    path: '',
    name: '',
    redirect: 'user'
  }
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
