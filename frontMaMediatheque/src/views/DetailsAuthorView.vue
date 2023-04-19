<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
// ligne 5 et 6 permettent de générer la page de détails de l'auteur
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_AUTEUR = 'http://localhost:8080/api/auteur/';

const author = ref({});

async function init() {
    const response = await axios.get(URL_AUTEUR + route.params.id);
    const authorFormatJson = response.data;
    author.value = authorFormatJson;
}

onMounted(() => {
    init();
})

</script>

<template>
    <main>

        <h1>Détails de {{ author.firstname }} {{ author.lastname }}</h1>

        <div class="btnBack">
            <a href="/bibliotheque/auteurs" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div>
            <h5>Nom:</h5>
            <p>{{ author.lastname }}</p>
            <h5>Prénom:</h5>
            <p> {{ author.firstname }}</p>
            <h5>Biographie:</h5>
            <p>{{ author.biography }}</p>

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
  
  