<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_BAND = 'http://localhost:8080/api/groupe';

const listBands = ref([]);

async function init() {
    const response = await axios.get(URL_BAND);
    const listBandsFormatJson = response.data;
    listBands.value = listBandsFormatJson;
}

onMounted(() => {
    init()
})
</script>

<template>
    <main>
        <h1>Musique</h1>
        <nav>
            <RouterLink to="/musique/groupes">Les groupes</RouterLink>
            <RouterLink to="/musique/albums">Les albums</RouterLink>
        </nav>
        <hr>

    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

nav{
    font-size: large;
    padding-bottom: 30px;
    display: flex;
    justify-content: space-around;
}

.imgBand {
    width: 200px;
    height: auto;
}

.addBand {
    font-size: 2rem;
}
</style>
  
  