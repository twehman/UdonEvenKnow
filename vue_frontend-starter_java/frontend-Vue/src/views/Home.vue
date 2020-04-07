<template>
<div class="main">
  <div class="home">
    <h1 class="sign" align="center">Welcome to Restaurant Tinder</h1>
    <p align="center">You must be authenticated to see this.</p>
  </div>
  </div>
</template>

<script>
export default {
  name: 'home'
}
</script>

<style scoped>
body {
        background-color: #F3EBF6;
        font-family: sans-serif;
    }
    
    .main {
        background-color: #FFFFFF;
        width: 800px;
        height: 500px;
        margin: 7em auto;
        border-radius: 1.5em;
        box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
    }

    .sign {
        padding-top: 40px;
        color: #8C55AA;
        font-family: sans-serif;
        font-weight: bold;
        font-size: 50px;
    }
</style>
