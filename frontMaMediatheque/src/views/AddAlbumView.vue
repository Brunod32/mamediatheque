<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_ALBUM = 'http://localhost:8080/api/album';
const URL_BAND = 'http://localhost:8080/api/groupe';

const name = ref('');
const releasedYears = ref('');
const albumCover = ref('');
const listAlbums = ref([]);
const band = ref('');
const bandsList = ref([]);

async function init() {
    const response = await axios.get(URL_ALBUM);
    const listAlbumsFormatJson = response.data;
    listAlbums.value = listAlbumsFormatJson;
}

async function initBandsList() {
    const response = await axios.get(URL_BAND);
    const bandsListFormatJson = response.data;
    bandsList.value = bandsListFormatJson;
}

async function createAlbum() {
    const body = {
        name: name.value,
        releasedYears: releasedYears.value,
        band: band.value,
        albumCover: albumCover.value,
    }
    
    await axios.post(URL_ALBUM, body);
    // Réinitialisation des champs de saisie
    name.value = releasedYears.value = band.value = albumCover.value = '';
    init();
}

onMounted(() => {
    init();
    initBandsList();
})

</script>

<template>
    <main>
        <h1>Ajout Album</h1>
        <hr>
        <div class="btnBack">
            <a href="/musique/albums" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div class="mt-3">
            <div class="d-flex flex-column mt-3">
                <label for="title">Titre</label>
                <input type="text" name="name" v-model="name">
            </div>
            <div class="d-flex flex-column mt-3">
                <label for="writer">Groupe</label>
                <!-- Ajouter mulitple dans select si plusieurs auteurs pour un livre -->
                <select name="band" id="band" v-model="band">
                    <option v-for="band in bandsList" v-bind:value="band">
                        {{ band.name }}
                    </option>
                </select>
            <div class="d-flex flex-column mt-3">
                <label for="nbPages">Année de sortie</label>
                <input type="text" name="releasedYears" v-model="releasedYears">
            </div>
            </div>
            <div class="d-flex flex-column mt-3">
                <label for="albumCover">Couverture</label>
                <input type="text" name="albumCover" v-model="albumCover">
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

.btnBack {
    margin: 20px 0;
}

.btnBack i {
    font-size: 2rem;
}

input, select {
    border-radius: 10px;
    padding: 5px;
}
</style>