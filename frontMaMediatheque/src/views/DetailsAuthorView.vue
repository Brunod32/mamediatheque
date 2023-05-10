<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
// ligne 5 et 6 permettent de générer la page de détails de l'auteur
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_AUTEUR = 'http://localhost:8080/api/auteur/';
const URL_LIVRE = 'http://localhost:8080/api/livre';

const author = ref({});
const booksList = ref({});

async function init() {
    const response = await axios.get(URL_AUTEUR + route.params.id);
    const authorFormatJson = response.data;
    author.value = authorFormatJson;
}

async function initBooksList() {
    const response = await axios.get(URL_LIVRE);
    const booksListFormatJson = response.data;
    booksList.value = booksListFormatJson;
}

async function deleteAuthor(idAuthorToDelete) {
    await axios.delete(URL_AUTEUR  + idAuthorToDelete);
    window.location.href = '/bibliotheque/auteurs';
    init();
}

onMounted(() => {
    init();
    initBooksList();
})

</script>

<template>
    <main>

        <h1>Détails de {{ author.firstname }} {{ author.lastname }}</h1>
        <div class="btnDiv">
            <button @click='deleteAuthor(author.id)' class="btn btn-danger badge"><i class="bi bi-trash3-fill"></i></button>&nbsp;
            <a :href='"/bibliotheque/modifierAuteur" + "/" + author.id' class="btn btn-info badge"><i class="bi bi-pen"></i></a>
        </div>
        <hr>

        <div class="btnBack">
            <a href="/bibliotheque/auteurs" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div class="authorDescription">
            <div>
                {{ author }}
                <!-- <h5>id:</h5>
                <p>{{ author.id }}</p> -->
                <h5>Nom:</h5>
                <p>{{ author.lastname }}</p>
                <h5>Prénom:</h5>
                <p> {{ author.firstname }}</p>
                <h5>Biographie:</h5>
                <p>{{ author.biography }}</p>
                <div>
                    <h5>Bibliographie</h5>
                    <ul v-for="books in booksList">
                        <!-- <template v-for="writer in books.writer"> -->
                            <!-- <li v-if="author.lastname == writer.lastname">{{ books.title }}</li> -->
                            <li v-if="author.id == books.writer.id">
                                <img class="imgBook" :src="books.bookCover" :title="books.title"  alt="Couverture de livre" />
                                <a :href="'/detailsLivre/' + books.id">{{ books.title }}</a>
                            </li>
                        <!-- </template> -->
                    </ul>
                </div>
            </div>
            
            <div>
                <template v-if="author.writerPicture != null">
                    <img class="imgAuthor" :src="author.writerPicture" :title="author.lastname"  alt="Photo de l'auteur" />
                </template>
                <template v-else>
                    <p>Image non disponible</p>
                </template>
            </div>    
        </div>
    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

.btnDiv {
    display: flex;
    justify-content: center;
}

.btnBack{
    margin: 20px 0;
}

i {
    font-size: 2rem;
}

.imgBook {
    width: 50px;
    height: auto;
}

li {
    list-style: none;
}

.authorDescription {
    display: flex;
    justify-content:space-around;
}

.imgAuthor {
    width: 200px;
    height: auto;
}
</style>
  
  