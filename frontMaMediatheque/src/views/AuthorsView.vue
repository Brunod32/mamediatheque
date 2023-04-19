<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_AUTEUR = 'http://localhost:8080/api/auteur';

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
        <h1 class="my-3">Les auteurs</h1>
        <hr>
        <div class="d-flex">
            <div class="btnBack">
                <a :href="'/bibliotheque'" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            </div>
        </div>

        <div>
            <div class="d-flex mb-5">
                <h2 class="mt-2">Liste des auteurs</h2>
                <a :href="'/bibliotheque/ajouterAuteur'" title="Ajouter un auteur" class="mx-5"><i class="bi bi-plus-circle addAuthor"></i></a>
            </div>
            <ul v-for="author in listAuthors">
                <li>
                    {{ author.firstname }} {{ author.lastname }}
                    <a :href="'/detailsAuteur/' + author.id">Détails</a>
                </li>
            </ul>
        </div>
    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

i {
    font-size: 1rem;
}

.addAuthor {
    font-size: 2rem;
}

.btnBack {
    margin: 20px 0;
}

.btnBack i {
    font-size: 2rem;
}

input , textarea{
    border-radius: 10px;
    padding: 5px;
}
</style>
  
  