<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_ALBUM = 'http://localhost:8080/api/album';
const URL_BAND = 'http://localhost:8080/api/groupe';

const name = ref('');
const releasedYears = ref('');
const albumCover = ref('');
const listAlbums = ref([]);
const band = ref('');
const bandsList = ref([]);
const album = ref({});
const idAlbumToUpdate = ref(0);

async function init() {
    const response = await axios.get(URL_ALBUM + '/' + route.params.id);
    const listAlbumsFormatJson = response.data;
    album.value = listAlbumsFormatJson;
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

    // si l'id est différent de 0, on l'ajoute
    if (idAlbumToUpdate.value > 0){
        body.id = idAlbumToUpdate.value;
    }

    await axios.post(URL_ALBUM, body);
    // Réinitialisation des champs de saisie
    name.value = releasedYears.value = band.value = albumCover.value = '';
    init();
}

function updateAlbum(albumToUpdate) {
    name.value = albumToUpdate.name
    releasedYears.value = albumToUpdate.releasedYears
    albumCover.value = albumToUpdate.albumCover
    idAlbumToUpdate.value = albumToUpdate.id

    window.location.href = '/musique/albums';

    createAlbum();
}

onMounted(() => {
    init();
    initBandsList();
})

</script>

<template>
    <main>
        <h1>Modifier l'album {{ album.name }}</h1>
        <hr>
        <div class="btnBack">
            <a href="/musique/albums" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>


        <div class="d-flex flex-column mt-5">
            <div class="d-flex flex-column">
                <div class="d-flex gap-5">
                    <div class="d-flex flex-column">
                        <label for="title">Titre</label>
                        <input type="text" name="name" v-model="album.name">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="releasedYears">Année de sortie</label>
                        <input type="text" name="releasedYears" v-model="album.releasedYears">
                    </div>
                    <div class="d-flex flex-column">
                        <label for="band">Groupe</label>
                        <!-- Ajouter mulitple dans select si plusieurs auteurs pour un livre -->
                        <select name="band" id="band" v-model="band">
                            <option v-for="band in bandsList" v-bind:value="band">
                                {{ band.name }}
                            </option>
                        </select>
                    </div>

                    <div class="d-flex flex-column">
                        <label for="albumCover">Couverture</label>
                        <input type="text" name="albumCover" v-model="album.albumCover">
                    </div>
                </div>            
            </div>
        </div>

        <div class="mt-3">
            <button @click='updateAlbum(album)' class="btn btn-primary">Modifier</button>&nbsp;
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