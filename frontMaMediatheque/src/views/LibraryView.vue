<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_AUTEUR = 'http://localhost:8080/api/auteur';
const URL_LIVRE = 'http://localhost:8080/api/livre';

const firstname = ref('');
const lastname = ref('');
const biography = ref('');
const listAuthors = ref([]);
const listBooks = ref([]);

async function init() {
    // Permet de récupérer la liste des auteurs en BDD
    const response = await axios.get(URL_AUTEUR);
    const listAuthorsFormatJson = response.data;
    listAuthors.value = listAuthorsFormatJson;

    // Permet de récupérer la liste des Livres en BDD
    const responseBook = await axios.get(URL_LIVRE);
    const listBooksFormatJson = responseBook.data;
    listBooks.value = listBooksFormatJson;
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
            <RouterLink to="/bibliotheque/auteurs" class="navItem">Les auteurs</RouterLink>
            <RouterLink to="/bibliotheque/livres" class="navItem">Les livres</RouterLink>
        </nav>

        <h2>Liste des auteurs</h2>

        <ul v-for="author in listAuthors">
            <li>
                {{ author.firstname }} {{ author.lastname }}
                <a :href="'/detailsAuteur/' + author.id">Détails</a>
            </li>
        </ul>

        <h2>Liste des Livres</h2>

        <ul v-for="book in listBooks">
            <li>
                {{ book.title }}
                <a :href="'/detailsLivre/' + book.id">Détails</a>
            </li>
        </ul>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

nav{
    font-size: large;
    padding-bottom: 30px;
    display: flex;
    justify-content: space-around;

}

.navItem:hover {
    font-weight: bold;
}
</style>
  
  