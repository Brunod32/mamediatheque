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
</style>
  
  