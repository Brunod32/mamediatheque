<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
// ligne 5 et 6 permettent de générer la page de détails de l'auteur
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_BOOK = 'http://localhost:8080/api/livre/';

const book = ref({});

async function init() {
    const response = await axios.get(URL_BOOK + route.params.id);
    const bookFormatJson = response.data;
    book.value = bookFormatJson;
}

onMounted(() => {
    init();
})

</script>

<template>
    <main>

        <h1>Détails du livre "{{ book.title }}"</h1>
            

        <div class="btnBack">
            <a href="/bibliotheque/livres" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div>
            <h5>Titre du livre:</h5>
            <p>{{ book.title }}</p>
            <h5>Année de sortie:</h5>
            <p> {{ book.releaseYear }}</p>
            <h5>Synopsis:</h5>
            <p>{{ book.synopsis }}</p>

        </div>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

.btnBack{
    margin: 20px 0;
}

i {
    font-size: 2rem;
}
</style>
  
  