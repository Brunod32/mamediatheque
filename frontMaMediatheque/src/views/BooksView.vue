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
        <div class="title">
            <a href="/bibliotheque" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            <h1 class="my-3">Les livres</h1>
            <a :href="'/bibliotheque/ajouterLivre'" title="Ajouter un livre"><i class="bi bi-plus-circle addBook"></i></a>
        </div>
        <hr>
        <!-- <div class="btnBack">
            <a href="/bibliotheque" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            <a :href="'/bibliotheque/ajouterLivre'" title="Ajouter un livre" class="mx-5"><i class="bi bi-plus-circle addBook"></i></a>
        </div> -->
 

        <ul class="booksListDiv">
            <li v-for="book in listBooks">
                <img class="imgBook" :src="book.bookCover" :title="book.title"  alt="Couverture de livre" />
                <div>{{ book.title }}</div>
                <a :href="'/detailsLivre/' + book.id">Détails</a>
            </li>
        </ul>

        
    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

.title {
    display: flex;
    justify-content: space-around;
}

i {
    font-size: 1rem;
}

.btnBack {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
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

.imgBook {
    width: 100px;
    height: auto;
}

li {
    list-style: none;
    margin-bottom: 20px;
}

.booksListDiv {
    /* text-align: center */
    display: flex;
    justify-content: space-around;
    margin-top: 40px;
}

@media screen and (max-width:1000px) {
    .booksListDiv {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
}
</style>
  
  