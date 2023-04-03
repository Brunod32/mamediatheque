<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
      <nav>
        <RouterLink to="/"><i class="bi bi-house-fill"></i></RouterLink>
        <RouterLink to="/bibliotheque"><i class="bi bi-book-half"></i></RouterLink>
        <RouterLink to="/musique"><i class="bi bi-music-note-list"></i></RouterLink>

      </nav>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  max-height: 100vh;
}

nav {
  display: flex;
  flex-direction: row;
  gap: 20px;
  width: 100%;
  font-size: 20px;
  text-align: center;
}

i {
  font-size: 2rem;
}

</style>
