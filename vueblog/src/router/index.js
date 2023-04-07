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
import EditBlog from "@/views/usersub/editblog"
import HotLatestMiddle from "@/views/usersub/frontsub/fronthotsub/middle/hotlatestmiddle"
import HotMostLikeMiddle from "@/views/usersub/frontsub/fronthotsub/middle/hotmostlikemiddle"
import frontmessage from '@/views/usersub/frontsub/frontmessage'
import frontperson from '@/views/usersub/frontsub/frontperson'
import blogdetail from '@/views/usersub/blogdetail'
import personuserinfo from "@/views/usersub/frontsub/frontpersonsub/userinfo"
import personaccount from "@/views/usersub/frontsub/frontpersonsub/account"
import personblog from "@/views/usersub/frontsub/frontpersonsub/blog"
import personcollect from "@/views/usersub/frontsub/frontpersonsub/collect"
import personfensi from "@/views/usersub/frontsub/frontpersonsub/fensi"
import personguanzhu from "@/views/usersub/frontsub/frontpersonsub/guanzhu"
import messagedetail from "@/views/usersub/frontsub/frontmessagedetail"
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
        redirect: { name: 'hotlatest' }
      },
      {
        path: 'editblog',
        name: 'editblog',
        component: EditBlog
      },
      {
        path: 'blogdetail/:userid/:blogid',
        name: 'blogdetail',
        component: blogdetail
      },
      {
        path: 'front',
        name: 'front',
        component: front,
        children: [
          {
            path: '',
            name: '',
            redirect: { name: 'hotlatest' }
          },
          {
            path: 'home',
            name: 'home',
            component: fronthomebody,
            children: [
              {
                path: '',
                name: '',
                redirect: { name: 'hotlatest' }
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
            component: fronthotbody,
            children: [{
              path: '',
              name: '',
              redirect: { name: 'hotlatest' }
            },
            {
              path: 'latest',
              name: 'hotlatest',
              component: HotLatestMiddle
            }, {
              path: 'mostlike',
              name: 'hotmostlike',
              component: HotMostLikeMiddle
            }]


          },
          {
            path: 'message',
            name: 'message',
            component: frontmessage,
            children: [
              {
                path: 'messagedetail/:userid',
                name: 'messagedetail',
                component: messagedetail
              }
            ]
          },
          {
            path: 'person',
            name: 'person',
            component: frontperson,
            children: [{
              path: '',
              name: '',
              redirect: { name: 'personuserinfo' }
            },
            {
              path: 'personuserinfo',
              name: 'personuserinfo',
              component: personuserinfo,
            },
            {
              path: 'personguanzhu ',
              name: 'personguanzhu ',
              component: personguanzhu
            }, {
              path: 'personfensi',
              name: 'personfensi',
              component: personfensi
            }, {
              path: "personcollect",
              name: 'personcollect',
              component: personcollect
            },
            {
              path: "personblog",
              name: 'personblog',
              component: personblog
            },
            {
              path: "personaccount",
              name: 'personaccount',
              component: personaccount
            }
            ]
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
// router.beforeEach(function (to, from, next) {
//   console.log(1);
//   if (to.path === "user/front/hot/latest") {
//     next()

//   }
//   else {
//     const token = localStorage.getItem('token')
//     if (token) {
//       next()
//     }
//     else { next('user/front/hot/latest') }
//   }
// })
export default router
