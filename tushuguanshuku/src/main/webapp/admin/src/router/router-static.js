import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import jieyue from '@/views/modules/jieyue/list'
    import news from '@/views/modules/news/list'
    import tushu from '@/views/modules/tushu/list'
    import tushuChuruInout from '@/views/modules/tushuChuruInout/list'
    import tushuChuruInoutList from '@/views/modules/tushuChuruInoutList/list'
    import yonghu from '@/views/modules/yonghu/list'
    import dictionaryBanji from '@/views/modules/dictionaryBanji/list'
    import dictionaryChubanshe from '@/views/modules/dictionaryChubanshe/list'
    import dictionaryJieyue from '@/views/modules/dictionaryJieyue/list'
    import dictionaryNews from '@/views/modules/dictionaryNews/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryTushu from '@/views/modules/dictionaryTushu/list'
    import dictionaryTushuChuruInout from '@/views/modules/dictionaryTushuChuruInout/list'
    import dictionaryXibie from '@/views/modules/dictionaryXibie/list'
    import jieyueAdd from '@/views/modules/jieyue/add-or-update'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryBanji',
        name: '班级',
        component: dictionaryBanji
    }
    ,{
        path: '/dictionaryChubanshe',
        name: '出版社',
        component: dictionaryChubanshe
    }
    ,{
        path: '/dictionaryJieyue',
        name: '借阅状态',
        component: dictionaryJieyue
    }
    ,{
        path: '/dictionaryNews',
        name: '公告类型',
        component: dictionaryNews
    }
    ,{
        path: '/dictionarySex',
        name: '性别',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryTushu',
        name: '图书类型',
        component: dictionaryTushu
    }
    ,{
        path: '/dictionaryTushuChuruInout',
        name: '出入库类型',
        component: dictionaryTushuChuruInout
    }
    ,{
        path: '/dictionaryXibie',
        name: '系别',
        component: dictionaryXibie
    }

    ,{
        path: '/jieyueAdd',
        name: '借阅图书',
        component: jieyueAdd
    }

    ,{
        path: '/dictionary',
        name: '字典表',
        component: dictionary
      }
    ,{
        path: '/jieyue',
        name: '借阅记录',
        component: jieyue
      }
    ,{
        path: '/news',
        name: '公告信息',
        component: news
      }
    ,{
        path: '/tushu',
        name: '图书',
        component: tushu
      }
    ,{
        path: '/tushuChuruInout',
        name: '出入库',
        component: tushuChuruInout
      }
    ,{
        path: '/tushuChuruInoutList',
        name: '出入库详情',
        component: tushuChuruInoutList
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
