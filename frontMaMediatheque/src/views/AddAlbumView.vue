<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_ALBUM = 'http://localhost:8080/api/album';
const URL_BAND = 'http://localhost:8080/api/groupe';

const name = ref('');
const releasedYears = ref('');
const listAlbums = ref([]);
const band = ref('');
const bandsList = ref([]);

async function init() {
    const response = await axios.get(URL_ALBUM);
    const listAlbumsFormatJson = response.data;
    listAlbums.value = listAlbumsFormatJson;
}

async function iniBandsList() {
    const response = await axios.get(URL_BAND);
    const bandsListFormatJson = response.data;
    bandsList.value = bandsListFormatJson;
}

async function createAlbum() {
    const body = {
        name: name.value,
        releasedYears: releasedYears.value,
        band: band.value,
    }

    await axios.post(URL_ALBUM, body);
    
    // Réinitialisation des champs de saisie
    name.value = releasedYears.value = band.value = '';
    init();
}

onMounted(() => {
    init();
    iniBandsList()
})

</script>

<template>
    <main>
        <h1>Ajout Album</h1>
        <hr>
        <div class="btnBack">
            <a href="/musique/groupes" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>


        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="title">Titre</label>
                        <input type="text" name="name" v-model="name">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="nbPages">Année de sortie</label>
                        <input type="text" name="releasedYears" v-model="releasedYears">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="writer">Groupe</label>
                        <!-- Ajouter mulitple dans select si plusieurs auteurs pour un livre -->
                        <select name="band" id="band" v-model="band">
                            <option v-for="band in bandsList" v-bind:value="band">
                                {{ band.name }}
                            </option>
                        </select>
                    </div>
                </div>            
            </div>
        </div>

        <div class="mt-3">
            <button @click="createAlbum()" class="btn btn-primary">Ajouter</button>&nbsp;
            
        </div>

    </main>
</template>

<style scoped>
h1 {
    text-align: center;
}
</style>