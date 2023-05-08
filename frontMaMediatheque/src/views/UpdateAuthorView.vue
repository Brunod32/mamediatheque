<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_AUTEUR = 'http://localhost:8080/api/auteur';

const firstname = ref('');
const lastname = ref('');
const biography = ref('');
const author = ref({});
const idAuthorToUpdate = ref(0);

async function init() {
    // Permet de récupérer la liste des auteurs en BDD
    const response = await axios.get(URL_AUTEUR + '/' +route.params.id);
    const authorFormatJson = response.data;
    author.value = authorFormatJson;
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

function updateAuthor(authorToUpdate){
    firstname.value = authorToUpdate.firstname
    lastname.value = authorToUpdate.lastname
    biography.value = authorToUpdate.biography
    idAuthorToUpdate.value = authorToUpdate.id

    window.location.href = '/bibliotheque/auteurs';

    createAuthor();
    stopUpdate();
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
        <h1>Modifier l'auteur {{ author.firstname }} {{ author.lastname }}</h1>
    <hr>

    <div class="btnBack">
            <a href="/bibliotheque/auteurs" class="btnBack"><i class="bi bi-chevron-left"></i></a>
    </div>
    <div class="d-flex flex-column mt-5">
        <div class="d-flex flex-column">
            <div class="d-flex gap-5">
                <div class="d-flex flex-column">
                    <label for="lastname">Nom</label>
                    <input type="text" name="lastname" v-model="author.lastname">
                </div>
                <div class="d-flex flex-column">
                    <label for="firstname">Prénom</label>
                    <input type="text" name="firstname" v-model="author.firstname">
                </div>
            </div>
            <div class="d-flex flex-column mt-3">
                <label for="biography">Biographie</label>
                <textarea name="biography" id="biography" cols="30" rows="3" v-model="author.biography"></textarea>
            </div>
        </div>
        
        <div>
            <div class="mt-3">
                <div class="mt-3">
                <!-- <button @click="createAuthor()" class="btn btn-primary">{{ idAuthorToUpdate == 0 ? 'Ajouter' : 'Modifier' }}</button>&nbsp; -->
                <button @click='updateAuthor(author)' class="btn btn-primary">Modifier</button>&nbsp;
                <!-- <button v-if="idAuthorToUpdate > 0" class="btn btn-danger" @click="stopUpdate()">Annuler la modification</button> -->
            </div>
            </div>
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