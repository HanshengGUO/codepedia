import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../views/home.vue'
import About from "@/views/about.vue";
import AdminEbook from "@/views/admin/admin-ebook.vue";
import AdminCategory from "@/views/admin/admin-category.vue";
import AdminDoc from "@/views/admin/admin-doc.vue";
import Doc from "../views/doc.vue";
import AdminUser from "@/views/admin/admin-user.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/doc',
        name: 'Doc',
        component: Doc
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
        path: '/admin/user',
        name: 'AdminUser',
        component: AdminUser
    },
    {
        path: '/admin/category',
        name: 'AdminCategory',
        component: AdminCategory
    },
    {
        path: '/admin/doc',
        name: 'AdminDoc',
        component: AdminDoc
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
