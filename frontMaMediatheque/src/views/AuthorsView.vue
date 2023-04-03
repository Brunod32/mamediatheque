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

async function createAuthor(){
    const body= {
        firstname: firstname.value,
        lastname: lastname.value,
        biography: biography.value,
    }

    await axios.post(URL_AUTEUR, body);

    firstname.value = '';
    lastname.value = '';
    biography.value = '';
    init();
}

async function deleteAuthor(idAuthorToDelete) {
    await axios.delete(URL_AUTEUR + '/' + idAuthorToDelete);
    init();
}


onMounted(() => {
    init();
})

</script>

<template>
    <main>
        <h1>Les auteurs</h1>

        <div class="d-flex flex-column">
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
                <div class="d-flex flex-column">
                    <label for="biography">Biographie</label>
                    <textarea name="biography" id="biography" cols="30" rows="3"  v-model="biography"></textarea>
                </div>
            </div>
            
            <div>
                <div class="mt-3">
                    <button @click="createAuthor()" class="btn btn-primary">Valider</button>
                </div>
            </div>

           
        </div>


        <section class="mt-5">
            <div class="mb-3" v-for="author in listAuthors">
                <h3>{{ author.firstname }} {{ author.lastname }}</h3>
                <p>{{ author.biography }}</p>
                <button @click='deleteAuthor(author.id)' class="btn btn-danger badge">X</button>
            </div>

        </section>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}
</style>
  
  