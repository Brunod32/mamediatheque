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

        <div class="bandDescription">
            <div>
                <!-- <h5>id:</h5>
                <p>{{ band.id }}</p> -->
                <h5>Pays:</h5>
                <p>{{ band.country }}</p>
                
                <div>
                    <h5>Discographie</h5>
                    <ul v-for="albums in albumsList">
                        <li v-if="band.id == albums.band.id">
                            <img class="imgAlbum" :src="albums.albumCover" :title="albums.name"  alt="Couverture de l'album" />
                            <a :href="'/detailsAlbum/' + albums.id">{{ albums.name }}</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div>
                <template v-if="band.bandPicture != null">
                    <img class="imgBand" :src="band.bandPicture" :title="band.name"  alt="Photo du groupe" />
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

.bandDescription {
    display: flex;
    justify-content:space-around
}

.imgBand {
    width: 200px;
    height: auto;
}

.imgAlbum {
    width: 50px;
    height:auto;
}

li {
    list-style: none;
}
</style>
  
  