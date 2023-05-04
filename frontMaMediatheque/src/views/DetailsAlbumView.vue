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


onMounted(() => {
    init();
})

</script>

<template>
    <main>

        <h1>Détails de "{{ album.name }}"</h1>
            
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
                
                <h5>Année de sortie:</h5>
                <p> {{ album.releasedYears }}</p>
                
            </div>
            <!-- <div>
                <img class="imgBook" :src="book.bookCover" :title="book.title"  alt="Couverture de livre" />
            </div> -->
        </div>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
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

.imgBook {
    width: 200px;
    height: auto;
}
</style>
  
  