<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_BAND = 'http://localhost:8080/api/groupe';

const listBands = ref([]);
const name = ref('');
const country = ref('');
const creationYear = ref('');
const bandPicture = ref('');
const kindOfMetal = ref('');

async function init() {
    const response = await axios.get(URL_BAND);
    const listBandsFormatJson = response.data;
    listBands.value = listBandsFormatJson;
}

async function createBand() {
    const body = {
        name: name.value,
        country: country.value,
        creationYear: creationYear.value,
        bandPicture: bandPicture.value,
        kindOfMetal: kindOfMetal.value,
    }

    await axios.post(URL_BAND, body);

    // Reinitialisation des champs de saisie
    name.value = country.value = creationYear.value = bandPicture.value = kindOfMetal.value ='';
    init();
}

onMounted(() => {
    init();
})

</script>

<template>
    <main>
        <h1 class="my-3">Ajout un groupe</h1>
        <hr>
        <div class="btnBack">
            <a href="/musique/groupes" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="lastname">Nom</label>
                        <input type="text" name="name" v-model="name">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="kindOfMetal">Style</label>
                        <input type="text" name="kindOfMetal" v-model="kindOfMetal">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="firstname">Pays</label>
                        <input type="text" name="country" v-model="country">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="firstname">Année de formation</label>
                        <input type="text" name="creationYear" v-model="creationYear">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="bandPicture">Image</label>
                        <input type="text" name="bandPicture" v-model="bandPicture">
                    </div>
                </div>                
            </div>
            
            <div>
                <div class="mt-3">
                    <button @click="createBand()" class="btn btn-primary">Ajouter</button>
                </div>
            </div>           
        </div>



    </main>
</template>

<style scoped>
h1 {
    text-align: center;
}

.btnBack {
    margin: 20px 0;
}

.btnBack i {
    font-size: 2rem;
}

input {
    border-radius: 10px;
    padding: 5px;
}

</style>