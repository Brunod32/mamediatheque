<template>
    <main>
        <h1>Gestionnaire de médias</h1>
        <hr>
        <div class="imgHome">
            <a href="/bibliotheque/" title="Consulter la bibliothèque"><img src="img/books.png" class="img" alt="Image d'une bibliothèque"></a>
            <a href="/musique/" title="Consulter la CDthèque"><img src="img/guitar-amplifier.png" class="img" alt="Image d'un amplificateur"></a>
            
        </div>
    </main>
</template>


<style scoped>
h1 {
    text-align: center;
    margin: 3rem;
}

.img {
    width: 400px;
    height: auto;
    margin-top: 2rem;
}

.imgHome {
    display: flex;
    justify-content: space-around;
}

</style>

