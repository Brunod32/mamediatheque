import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/bibliotheque',
      name: 'library',
      component: () => import('../views/LibraryView.vue')
    },
    {
      path: '/musique',
      name: 'music',
      component: () => import('../views/MusicView.vue')
    }
  ]
})

export default router
