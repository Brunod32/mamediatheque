<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
const route = useRoute();
const URL_ALBUM = 'http://localhost:8080/api/album';

const album = ref({});
async function init() {
    const response = await axios.get(URL_ALBUM + '/' + route.params.id);
    const albumFormatJson = response.data;
    album.value = albumFormatJson;
}

async function deleteAlbum(idAlbumToDelete) {
    await axios.delete(URL_ALBUM + '/' + idAlbumToDelete);
    window.location.href = '/musique/albums';
    init();
}

onMounted(() => {
    init();
})

</script>

<template>
    <main>

        <h1>Détails de "{{ album.name }}"</h1>
        <div class="btnDiv">
            <button @click='deleteAlbum(album.id)' class="btn btn-danger badge"><i class="bi bi-trash3-fill"></i></button>&nbsp;
        </div>
        <hr>

        <div class="btnsDiv">
            <!-- <button @click='deleteBook(book.id)' class="btn btn-danger badge"><i class="bi bi-trash3-fill"></i></button>&nbsp; -->
            <!-- <button @click='updateBook(book)' class="btn btn-info badge"><i class="bi bi-pen"></i></button> -->
            <!-- <a :href="'/bibliotheque/ajouterLivre/'">Modifier</a> -->
        </div>

        <div class="btnBack">
            <a href="/musique/albums" class="btnBack"><i class="bi bi-chevron-left"></i></a>
        </div>
        <div class="albumDescription">
            <div>
                <h5>Titre de l'album:</h5>
                <p>{{ album.name }}</p>
                <h5>Groupe:</h5>
                <!-- <p>{{ album.band?.name }}</p> -->
                <p><a :href="'/detailsBand/' + album.band?.id">{{ album.band?.name }}</a></p>
                <h5>Année de sortie:</h5>
                <p> {{ album.releasedYears }}</p>
            </div>
            <div>
                <img class="imgAlbum" :src="album.albumCover" :title="album.name"  alt="Couverture de l'album" />
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

.btnsDiv {
    display: flex;
    justify-content: center;
}

.btnBack{
    margin: 20px 0;
}

i {
    font-size: 2rem;
}

.albumDescription {
    display: flex;
    justify-content:space-around
}

.imgAlbum {
    width: 200px;
    height: auto;
}
</style>
  
  