<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_LIVRE = 'http://localhost:8080/api/livre';

const listBooks = ref([]);

async function init() {
    const response = await axios.get(URL_LIVRE);
    const listBooksFormatJson = response.data;
    listBooks.value = listBooksFormatJson;
}

onMounted(() => {
    init();
})


</script>

<template>
    <main>
        <h1 class="my-3">Les livres</h1>
        <hr>
        <div class="btnBack">
            <a href="/bibliotheque" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>
 
        <div>
            <div class="d-flex mb-5">
                <h2 class="mt-2">Liste des Livres</h2>
                <a :href="'/bibliotheque/ajouterLivre'" title="Ajouter un livre" class="mx-5"><i class="bi bi-plus-circle addBook"></i></a>
            </div>
            <ul v-for="book in listBooks">
                <li>
                    {{ book.title }}
                    <a :href="'/detailsLivre/' + book.id">Détails</a>
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

.btnBack {
    margin: 20px 0;
}

.btnBack i {
    font-size: 2rem;
}

.addBook {
    font-size: 2rem;
}

input , textarea{
    border-radius: 10px;
    padding: 5px;
}
</style>
  
  