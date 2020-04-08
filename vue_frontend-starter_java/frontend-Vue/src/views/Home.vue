<template>
<div class="main">
    <h1 class="sign" align="center">Welcome to Restaurant Tinder</h1>
<div class="pref">
  Restaurant search, will make prettier
</div>
<div class="pref">
Restaurant details, will make prettier
</div>
<div class="pref">
</div>
  </div>
  
</template>

<script>
import Buttons from '@/components/Home/Buttons.vue';

export default {
  name: 'home',
  components: {
    Buttons
}
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
        height: 800px;
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

    .pref {
      background-color: #D3D3D3;
      text-align: center;
      box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
      margin-top: 20px;
    }

</style>
