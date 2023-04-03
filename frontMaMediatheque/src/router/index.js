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
            path: '/bibliotheque/auteurs',
            name: 'authors',
            component: () => import('../views/AuthorsView.vue')
        },
        {
            path: '/bibliotheque/livres',
            name: 'books',
            component: () => import('../views/BooksView.vue')
        },
        {
            path: '/musique',
            name: 'music',
            component: () => import('../views/MusicView.vue')
        },
        {
            path: '/musique/groupes',
            name: 'bands',
            component: () => import('../views/BandsView.vue')
        },
        {
            path: '/musique/albums',
            name: 'albums',
            component: () => import('../views/AlbumsView.vue')
        }
    ]
})

export default router
