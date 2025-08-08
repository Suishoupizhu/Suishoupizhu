import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '../store/auth'

const Login = () => import('../views/Login.vue')
const Dashboard = () => import('../views/Dashboard.vue')
const Products = () => import('../views/Products.vue')
const Categories = () => import('../views/Categories.vue')

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: Dashboard },
    { path: '/login', component: Login },
    { path: '/products', component: Products, meta: { requiresAuth: true } },
    { path: '/categories', component: Categories, meta: { requiresAuth: true } },
  ]
})

router.beforeEach((to, _from, next) => {
  const store = useAuthStore()
  if (to.meta.requiresAuth && !store.token) {
    next('/login')
  } else {
    next()
  }
})

export default router