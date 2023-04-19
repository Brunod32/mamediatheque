<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const URL_LIVRE = 'http://localhost:8080/api/livre';

async function init() {
    const response = await axios.get(URL_LIVRE);
    const listBooksFormatJson = response.data;
    listBooks.value = listBooksFormatJson;
}

onMounted(() => {
    init();
})


</script>

<template>
    <main>
        <h1>Les livres</h1>

        
    </main>
</template>
  
  
<style scoped>
h1 {
    text-align: center;
}
</style>
  
  