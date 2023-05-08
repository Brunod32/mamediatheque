<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_BAND = 'http://localhost:8080/api/groupe';

const name = ref('');
const country = ref('');
const creationYear = ref('');
const bandPicture = ref('');
const band = ref({});
const idBandToUpdate = ref(0);

async function init() {
    const response = await axios.get(URL_BAND + '/' + route.params.id);
    const bandFormatJson = response.data;
    band.value = bandFormatJson;
}

async function createBand() {
    const body = {
        name: name.value,
        country: country.value,
        creationYear: creationYear.value,
        bandPicture: bandPicture.value,
    }

    // si l'id est différent de 0, on l'ajoute
    if (idBandToUpdate.value > 0){
        body.id = idBandToUpdate.value;
    }

    await axios.post(URL_BAND, body);

    // Reinitialisation des champs de saisie
    name.value = country.value = creationYear.value = bandPicture.value = '';
    init();
}

function updateBand(bandToUpdate) {
    name.value = bandToUpdate.name
    country.value = bandToUpdate.country
    creationYear.value = bandToUpdate.creationYear
    bandPicture.value = bandToUpdate.bandPicture
    idBandToUpdate.value = bandToUpdate.id

    window.location.href = '/musique/groupes';

    createBand();
}

onMounted(() => {
    init();
})

</script>

<template>
    <main>
        <h1 class="my-3">Modification de {{ band.name }}</h1>
        <hr>
        <div class="btnBack">
            <a href="/musique/groupes" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="lastname">Nom</label>
                        <input type="text" name="name" v-model="band.name">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="firstname">Pays</label>
                        <input type="text" name="country" v-model="band.country">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="firstname">Année de formation</label>
                        <input type="text" name="creationYear" v-model="band.creationYear">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="bandPicture">Image</label>
                        <input type="text" name="bandPicture" v-model="band.bandPicture">
                    </div>
                </div>                
            </div>
            
            <div>
                <div class="mt-3">
                    <button @click="updateBand(band)" class="btn btn-primary">Modifier</button>
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