<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_ALBUM = 'http://localhost:8080/api/album';

const listAlbums = ref([]);

async function init() {
    const response = await axios.get(URL_ALBUM);
    const listAlbumsFormatJson = response.data;
    listAlbums.value = listAlbumsFormatJson;
}

onMounted(() => {
    init();
})
</script>

<template>
    <main>
        <div class="title">
            <a :href="'/musique'" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            <h1>Les albums</h1>
            <a :href="'/musique/ajouterAlbum'" title="Ajouter un album" class="mx-5"><i class="bi bi-plus-circle addAlbum"></i></a>
        </div>
        <hr>
        <ul class="albumsListDiv">
            <li v-for="album in listAlbums">
                <img class="imgAlbum" :src="album.albumCover" :title="album.name"  alt="Couverture de l'album" />
                <div>{{ album.name }}</div>
                <a :href="'/detailsAlbum/' + album.id">Détails</a>
            </li>
        </ul>
    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

.btnBack {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
}

.btnBack i {
    font-size: 2rem;
}

.title {
    display: flex;
    justify-content: space-around;
}

.addAlbum {
    font-size: 2rem;
}

.imgAlbum {
    width: 200px;
    height: auto;
}

li {
    list-style: none;
    margin-bottom: 20px;
}

.albumsListDiv {
    /* text-align: center */
    display: flex;
    justify-content: space-around;
    margin-top: 40px;
}

@media screen and (max-width:1000px) {
    .albumsListDiv {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
}
</style>
  
  