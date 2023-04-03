<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_AUTEUR = 'http://localhost:8080/api/auteur';

const firstname = ref('');
const lastname = ref('');
const biography = ref('');
const listAuthors = ref([]);

async function init() {
    // Permet de récupérer la liste des auteurs en BDD
    const response = await axios.get(URL_AUTEUR);
    const listAuthorsFormatJson = response.data;
    listAuthors.value = listAuthorsFormatJson;
}
onMounted(() => {
    console.log('onMounted : je vais appeler la méthode init()')
    init();
})

</script>

<template>
    <main>
        <h1>Bibliothèque</h1>
        <nav>
            <RouterLink to="/bibliotheque/auteurs">Les auteurs</RouterLink>
            <RouterLink to="/bibliotheque/livres">Les livres</RouterLink>
        </nav>

        <h2>Liste des auteurs</h2>

        <ul v-for="author in listAuthors">
            <li>
                {{ author.firstname }} {{ author.lastname }}
            </li>
        </ul>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}
</style>
  
  