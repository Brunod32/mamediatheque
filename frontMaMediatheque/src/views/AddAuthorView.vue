<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_AUTEUR = 'http://localhost:8080/api/auteur';

const firstname = ref('');
const lastname = ref('');
const biography = ref('');
const listAuthors = ref([]);
const idAuthorToUpdate = ref(0);

async function init() {
    // Permet de récupérer la liste des auteurs en BDD
    const response = await axios.get(URL_AUTEUR);
    const listAuthorsFormatJson = response.data;
    listAuthors.value = listAuthorsFormatJson;
}

async function createAuthor(){
    const body= {
        firstname: firstname.value,
        lastname: lastname.value,
        biography: biography.value,
    }

    // si l'id est différent de 0, on l'ajoute
    if (idAuthorToUpdate.value > 0){
        body.id = idAuthorToUpdate.value;
    }

    await axios.post(URL_AUTEUR, body);

    // Reinitialisation des champs de saisie
    firstname.value = '';
    lastname.value = '';
    biography.value = '';
    init();
}

async function deleteAuthor(idAuthorToDelete) {
    await axios.delete(URL_AUTEUR + '/' + idAuthorToDelete);
    idAuthorToUpdate.value = 0;
    init();
}

function updateAuthor(authorToUpdate){
    firstname.value = authorToUpdate.firstname
    lastname.value = authorToUpdate.lastname
    biography.value = authorToUpdate.biography
    idAuthorToUpdate.value = authorToUpdate.id
}

function stopUpdate() {
    firstname.value = lastname.value = biography.value = '';
    idAuthorToUpdate.value = 0;
}

onMounted(() => {
    init();
})

</script>

<template>
    <main>
        <h1 class="my-3">Les auteurs</h1>
        <hr>
        <div class="btnBack">
            <a href="/bibliotheque/auteurs" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>
        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="lastname">Nom</label>
                        <input type="text" name="lastname" v-model="lastname">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="firstname">Prénom</label>
                        <input type="text" name="firstname" v-model="firstname">
                    </div>
                </div>
                <div class="d-flex flex-column mt-3">
                    <label for="biography">Biographie</label>
                    <textarea name="biography" id="biography" cols="30" rows="3" v-model="biography"></textarea>
                </div>
            </div>
            
            <div>
                <div class="mt-3">
                    <button @click="createAuthor()" class="btn btn-primary">{{ idAuthorToUpdate == 0 ? 'Ajouter' : 'Modifier' }}</button>&nbsp;
                    <button v-if="idAuthorToUpdate > 0" class="btn btn-danger" @click="stopUpdate()">Annuler la modification</button>
                </div>
            </div>           
        </div>
        <hr class="my-4">
        <section>
            <div class="mb-3" v-for="author in listAuthors">
                <h3>{{ author.firstname }} {{ author.lastname }}</h3>
                <p>{{ author.biography }}</p>
                <button @click='deleteAuthor(author.id)' class="btn btn-danger badge"><i class="bi bi-trash3-fill"></i></button>&nbsp;
                <button @click='updateAuthor(author)' class="btn btn-info badge"><i class="bi bi-pen"></i></button>
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

input , textarea{
    border-radius: 10px;
    padding: 5px;
}
</style>
  
  