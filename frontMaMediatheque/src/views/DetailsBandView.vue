<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_BAND = 'http://localhost:8080/api/groupe/';
const URL_ALBUM = 'http://localhost:8080/api/album';

const band = ref({});
const albumsList = ref({});

async function init() {
    const response = await axios.get(URL_BAND + route.params.id);
    const bandFormatJson = response.data;
    band.value = bandFormatJson;
}

async function initAlbumsList() {
    const response = await axios.get(URL_ALBUM);
    const albumsListFormatJson = response.data;
    albumsList.value = albumsListFormatJson;
}

async function deleteBand(idBandToDelete) {
    await axios.delete(URL_BAND + idBandToDelete);
    window.location.href = '/musique/groupes';
    init();
}

onMounted(() => {
    init();
    initAlbumsList();
    console.log(albumsList.id)
})

</script>

<template>
    <main>

        <h1>Détails de {{ band.name }}</h1>
        <div class="btnDiv">
            <button @click='deleteBand(band.id)' class="btn btn-danger badge"><i class="bi bi-trash3-fill"></i></button>&nbsp;
        </div>
        <hr>

        <div class="btnBack">
            <a href="/musique/groupes" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>

        <div>
            <h5>id:</h5>
            <p>{{ band.id }}</p>
            <h5>Pays:</h5>
            <p>{{ band.country }}</p>
            
            <div>
                <h5>Discographie</h5>
                <ul v-for="albums in albumsList">
                    <li v-if="band.id == albums.band.id">
                        {{ albums.name }}
                    </li>
                </ul>
                
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
</style>
  
  