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

        <!-- <div>
            <div class="d-flex mb-5">
                <h2 class="mt-2">Liste des groupes</h2>
                <a :href="'/musique/ajouterGroupe'" title="Ajouter un groupe" class="mx-5"><i class="bi bi-plus-circle addBand"></i></a>
            </div>
        </div> -->

        <!-- <div class="row">
            <div class="col-md-3" v-for="band in listBands">
                <div class="mb-3" style="width: 18rem;">
                    <img class="imgBand" :src="band.bandPicture" :title="band.name"  alt="Photo du groupe" />
                    <div class="card-body">
                        <a :href="'/detailsBand/' + band.id"><h5 class="card-title">{{ band.name }} - {{ band.country }}</h5></a>
                    </div>
                </div>
            </div>
        </div> -->
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
  
  