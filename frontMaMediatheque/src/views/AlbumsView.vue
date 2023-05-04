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
        <h1>Les albums</h1>
        <hr>
        <div class="d-flex">
            <div class="btnBack">
                <a :href="'/musique'" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            </div>
        </div>
        
        <div>
            <div class="d-flex mb-5">
                <h2 class="mt-2">Liste des albums</h2>
                <a :href="'/musique/ajouterAlbum'" title="Ajouter un album" class="mx-5"><i class="bi bi-plus-circle addAlbum"></i></a>
            </div>
            <ul v-for="album in listAlbums">
                <li>
                    {{ album.name }}
                    <a :href="'/detailsAlbum/' + album.id">Détails</a>
                </li>
            </ul>
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

.addAlbum {
    font-size: 2rem;
}
</style>
  
  