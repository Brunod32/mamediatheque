<template>
    <main>
        <div>
            <h1>Gestionnaire de médias</h1>
        </div>
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
}
.imgHome {
    display: flex;
    justify-content: space-around;
}

.img {
    width: 400px;
    height: auto;
    margin-top: 2rem;
}

@media screen and (max-width:1000px) {
.imgHome {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.img {
    width: 300px;
    height: auto;
    margin-top: 2rem;
}

}
</style>

