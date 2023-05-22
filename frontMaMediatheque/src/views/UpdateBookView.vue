<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_LIVRE = 'http://localhost:8080/api/livre';
const URL_AUTEUR = 'http://localhost:8080/api/auteur';

import { useRoute } from 'vue-router';
const route = useRoute();

const title = ref('');
const nbPages = ref('');
const releaseYear = ref('');
const synopsis = ref('');
const bookCover = ref('');
const book = ref({});
const idBookToUpdate = ref(0);

const writersList = ref([]);
const writer = ref('');

async function init() {
    const response = await axios.get(URL_LIVRE + '/'+ route.params.id);
    const listBooksFormatJson = response.data;
    book.value = listBooksFormatJson;
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

function updateBook(bookToUpdate) {
    title.value = bookToUpdate.title
    nbPages.value = bookToUpdate.nbPages
    releaseYear.value = bookToUpdate.releaseYear
    synopsis.value = bookToUpdate.synopsis
    idBookToUpdate.value = bookToUpdate.id
    writer.value = bookToUpdate.writer
    bookCover.value = bookToUpdate.bookCover

    window.location.href = '/bibliotheque/livres';

    createBook();
    stopUpdate();
}

// function stopUpdate() {
//     title.value = nbPages.value = releaseYear.value = synopsis.value = writer.value = bookCover.value = '';
//     idBookToUpdate.value = 0;
// }

onMounted(() => {
    init();
    initWritersList();
})
</script>

<template>
    <main>
        <h1>Modifier le livre {{ book.title }}</h1>

        <hr>
        <div class="btnBack">
            <a href="/bibliotheque/livres" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>
        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="title">Titre</label>
                        <input type="text" name="title" v-model="book.title">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="nbPages">Nombre de pages</label>
                        <input type="text" name="nbPages" v-model="book.nbPages">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="releaseYear">Année de sortie</label>
                        <input type="text" name="releaseYear" v-model="book.releaseYear">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="writer">Auteur</label>
                        <select name="writer" id="writer" v-model="writer">
                            <option v-for="writer in writersList" v-bind:value="writer">
                                {{ writer.firstname }} {{ writer.lastname }}
                            </option>
                        </select>
                    </div>

                    <div class="d-flex flex-column">
                        <label for="bookCover">Couverture</label>
                        <input type="text" name="bookCover" v-model="book.bookCover">
                    </div>

                </div>
                <div>
                    <div class="d-flex flex-column">
                        <label class="mt-2" for="synopsis">Synopsis</label>
                        <textarea name="synopsis" id="synopsis" cols="30" rows="3" v-model="book.synopsis"></textarea>
                    </div>
                </div>
            
            </div>
        </div>
        
        <div>
            <div class="mt-3">
                <button @click='updateBook(book)' class="btn btn-primary">Modifier</button>&nbsp;
            </div>
        </div>

    </main>
</template>

<style>
h1 {
    text-align: center;
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