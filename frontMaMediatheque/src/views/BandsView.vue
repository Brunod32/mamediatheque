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
        <div class="title">
            <a :href="'/musique'" class="btnBack"><i class="bi bi-chevron-left"></i></a>
            <h1>Les groupes</h1>
            <a :href="'/musique/ajouterGroupe'" title="Ajouter un groupe" class="mx-5"><i class="bi bi-plus-circle addBand"></i></a>
        </div>
        <hr>
        <!-- <div class="btnBack">
        </div> -->

        <!-- <div class="bandsListDiv">
            <div class="">
                <div class="col-md-3" v-for="band in listBands">
                    <div class="mb-3">
                        <img class="imgBand" :src="band.bandPicture" :title="band.name"  alt="Photo du groupe" />
                        <div class="card-body">
                            <a :href="'/detailsBand/' + band.id"><h5 class="text-center">{{ band.name }} - {{ band.country }}</h5></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->

        <ul class="bandsListDiv">
            <li v-for="band in listBands">
                <a :href="'/detailsBand/' + band.id"><h5>{{ band.name }} - {{ band.country }}</h5></a>
                <img class="imgBand" :src="band.bandPicture" :title="band.name"  alt="Photo du groupe" />
            </li>
        </ul>

        
        
    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}

.title {
    display: flex;
    justify-content: space-around;
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
    width: 200px;
}

li {
    list-style: none;
    margin-bottom: 20px;
    margin-left: 20px;
}

.bandsListDiv {
    display: flex;
    justify-content: center;
}

@media screen and (max-width: 1200px) {
    .bandsListDiv {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
}
</style>
  
  