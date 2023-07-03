import { createRouter, createWebHistory } from 'vue-router'
import Homepage from '../views/Homepage.vue'
import Detail from '../views/Detail.vue'
import Compare from '../views/Compare.vue'
import Favorites from '../views/Favorites.vue'
import Manage from '../views/Manage.vue'

const routes = [
    {
        path: '/',
        name: '首页',
        component: Homepage
    },
    {
        path: '/car/:id',
        name: '车辆详情',
        component: Detail,
        props: true
    },
    {
        path: '/compare',
        name: '车辆比较',
        component: Compare
    },
    {
        path: '/favorites/:id',
        name: '收藏夹',
        component: Favorites,
        props: true
    },
    {
        path: '/manage',
        name: '车辆管理',
        component: Manage
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

router.afterEach((to) => {
    document.title = to.name?.toString() || ''
})

export default router