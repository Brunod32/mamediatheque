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
    const response = await axios.get(URL_ALBUM + '/' + route.params.id);
    const albumsListFormatJson = response.data;
    albumsList.value = albumsListFormatJson;
}


onMounted(() => {
    init();
    initAlbumsList();
})

</script>

<template>
    <main>

        <h1>Détails de {{ band.name }}</h1>

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
                <ul>
                    <li>{{ albumsList.name }}</li>
                </ul>
            </div>
            

        </div>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

.btnBack{
    margin: 20px 0;
}

i {
    font-size: 2rem;
}
</style>
  
  