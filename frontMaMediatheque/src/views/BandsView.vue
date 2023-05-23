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
        <div class="btnBack">
            <a :href="'/musique'" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            <a :href="'/musique/ajouterGroupe'" title="Ajouter un groupe" class="mx-5"><i class="bi bi-plus-circle addBand"></i></a>
        </div>

        <div class="row">
            <div class="col-md-3" v-for="band in listBands">
                <div class="mb-3">
                    <img class="imgBand" :src="band.bandPicture" :title="band.name"  alt="Photo du groupe" />
                    <div class="card-body">
                        <a :href="'/detailsBand/' + band.id"><h5 class="text-center">{{ band.name }} - {{ band.country }}</h5></a>
                    </div>
                </div>
            </div>
        </div>

        
        
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

.addBand {
    font-size: 2rem;
}

.imgBand {
    height: 200px;
}
</style>
  
  