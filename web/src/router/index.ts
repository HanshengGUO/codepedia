import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../views/home.vue'
import About from "@/views/about.vue";
import AdminEbook from "@/views/admin/admin-ebook.vue";
import AdminCategory from "@/views/admin/admin-category.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        // 懒加载，在本项目中不适用
        // component: () => import(/* webpackChunkName: "about" */ '../views/admin-ebook.vue')
        component: About,
    },
    {
        path: '/admin/ebook',
        name: 'AdminEbook',
        component: AdminEbook
    },
    {
        path: '/admin/category',
        name: 'AdminCategory',
        component: AdminCategory
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
