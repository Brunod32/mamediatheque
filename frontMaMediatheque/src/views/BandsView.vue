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
        <h1>Les groupes</h1>
        <hr>
        <div class="d-flex">
            <div class="btnBack">
                <a :href="'/musique'" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            </div>
        </div>

        <div>
            <div class="d-flex mb-5">
                <h2 class="mt-2">Liste des groupes</h2>
                <a :href="'/musique/ajouterGroupe'" title="Ajouter un groupe" class="mx-5"><i class="bi bi-plus-circle addBand"></i></a>
            </div>
            <ul v-for="band in listBands">
                <li>
                    {{ band.name }}
                    <a :href="'/detailsBand/' + band.id">Détails</a>
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

.addBand {
    font-size: 2rem;
}
</style>
  
  