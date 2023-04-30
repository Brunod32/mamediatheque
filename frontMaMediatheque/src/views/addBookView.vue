<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_LIVRE = 'http://localhost:8080/api/livre';
const URL_AUTEUR = 'http://localhost:8080/api/auteur';

// Pour redirection
import { useRoute } from 'vue-router';
const router = useRoute();

const title = ref('');
const nbPages = ref('');
const releaseYear = ref('');
const synopsis = ref('');
const bookCover = ref('');
const listBooks = ref([]);
const idBookToUpdate = ref(0);

const writersList = ref([]);
const writer = ref('');

async function init() {
    const response = await axios.get(URL_LIVRE);
    const listBooksFormatJson = response.data;
    listBooks.value = listBooksFormatJson;
}

async function initWritersList() {
    const response = await axios.get(URL_AUTEUR);
    const writersListFormatJson = response.data;
    writersList.value = writersListFormatJson;
}

async function createBook() {
    const body = {
        title: title.value,
        nbPages: nbPages.value,
        releaseYear: releaseYear.value,
        synopsis: synopsis.value,
        writer: writer.value,
        bookCover: bookCover.value
    }

    //Si id livre est différent de 1, on l'ajoute
    if (idBookToUpdate.value > 0){
        body.id = idBookToUpdate.value;
    }
    
    await axios.post(URL_LIVRE, body);
    
    // Réinitialisation des champs de saisie
    title.value = nbPages.value = releaseYear.value = synopsis.value = writer.value = bookCover = '';
    init();
}

async function deleteBook(idBookToDelete) {
    await axios.delete(URL_LIVRE + '/' + idBookToDelete);
    idBookToDelete.value = 0;
    init();
}

function updateBook(bookToUpdate) {
    title.value = bookToUpdate.title
    nbPages.value = bookToUpdate.nbPages
    releaseYear.value = bookToUpdate.releaseYear
    synopsis.value = bookToUpdate.synopsis
    idBookToUpdate.value = bookToUpdate.id
    writer.value = bookToUpdate.writer
    bookCover.value = bookToUpdate.bookCover
}

function stopUpdate() {
    title.value = nbPages.value = releaseYear.value = synopsis.value = bookCover.value = '';
    idBookToUpdate.value = 0;
}

onMounted(() => {
    init();
    initWritersList();
})


</script>

<template>
    <main>
        <h1 class="my-3">Les livres</h1>
        <hr>
        <div class="btnBack">
            <a href="/bibliotheque/livres" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>
        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="title">Titre</label>
                        <input type="text" name="title" v-model="title">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="nbPages">Nombre de pages</label>
                        <input type="text" name="nbPages" v-model="nbPages">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="releaseYear">Année de sortie</label>
                        <input type="text" name="releaseYear" v-model="releaseYear">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="writer">Auteur</label>
                        <!-- Ajouter mulitple dans select si plusieurs auteurs pour un livre -->
                        <select name="writer" id="writer" v-model="writer">
                            <option v-for="writer in writersList" v-bind:value="writer">
                                {{ writer.firstname }} {{ writer.lastname }}
                            </option>
                        </select>
                    </div>

                    <div class="d-flex flex-column">
                        <label for="bookCover">Couverture</label>
                        <input type="text" name="bookCover" v-model="bookCover">
                    </div>

                </div>
                <div>
                    <div class="d-flex flex-column">
                        <label class="mt-2" for="synopsis">Synopsis</label>
                        <textarea name="synopsis" id="synopsis" cols="30" rows="3" v-model="synopsis"></textarea>
                    </div>
                </div>
            
            </div>
        </div>
        
        <div>
            <div class="mt-3">
                <button @click="createBook()" class="btn btn-primary">{{ idBookToUpdate == 0 ? 'Ajouter' : 'Modifier' }}</button>&nbsp;
                <button v-if="idBookToUpdate > 0" class="btn btn-danger" @click="stopUpdate()">Annuler la modification</button>
            </div>
        </div>

        <hr class="my-4">

        <section>
            <div class="mb-3" v-for="book in listBooks">
                <h3>{{ book.title }}</h3>
                <p>{{ book.nbPages }} pages, {{ book.releaseYear }}</p>
                <h5>Résumé</h5>
                <p>{{ book.synopsis }}</p>

                <button @click='deleteBook(book.id)' class="btn btn-danger badge"><i class="bi bi-trash3-fill"></i></button>&nbsp;
                <button @click='updateBook(book)' class="btn btn-info badge"><i class="bi bi-pen"></i></button>
            </div>
        </section>
        
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

input , textarea, select{
    border-radius: 10px;
    padding: 5px;
}
</style>
  
  