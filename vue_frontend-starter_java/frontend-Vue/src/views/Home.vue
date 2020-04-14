<template>
<b-container fluid>
<div class="main">
    <h1 class="sign" align="center">Welcome to Restaurant Tinder</h1>
<div class="pref">
    <p><h2 class="h2" align="left"> Select your preferred cuisine types:</h2></p>
  <restaurant-search v-on:user-choices="passUserChoices" v-bind:zipcode="zipcode"></restaurant-search>
</div>
<div class="rest">
  <p><h2 class="h2" align="left"> Here are some recommended restaurants:</h2></p>
<restaurant-details v-bind:details="zipcode" v-bind:choices="cuisineIds"></restaurant-details>
</div>
  </div>
  </b-container>
</template>

<script>
import RestaurantSearch from "@/components/Home/RestaurantSearch.vue";
import RestaurantDetails from "@/components/Home/RestaurantDetails.vue";
import auth from '@/auth'

export default {
  name: 'home',
  components: {
    RestaurantSearch,
    RestaurantDetails
},

data() {
  return {
    zipcode: {
        zip: '',
        latitude: '',
        longitude: '',
      },
    details: [],
    cuisineIds: []
  };
},
    methods: {
      passUserChoices(event) {
        this.cuisineIds = []
        event.forEach((item)=> {
          this.cuisineIds.push(item)
        })
      }
        }, 
  created() {
    fetch(`${process.env.VUE_APP_REMOTE_API}/restaurants`, {
              method: 'GET',
              headers: new Headers({
                Authorization: 'Bearer ' + auth.getToken(),
              }),
              credentials: 'same-origin',
            })
            .then((response) => {
          return response.json()
        })
      .then((userZipcode)=> {
          this.zipcode = userZipcode
      })
      .catch((err) => console.log(err))
  }
  };
</script>

<style scoped>
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
        color: #003459;
        font-family: sans-serif;
        font-weight: bold;
        font-size: 50px;
    }

    .pref {
      background-color: #8EA8C3;
      text-align: center;
      box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
      margin-top: 20px;
    }

    .rest {
      background-color: #8EA8C3;
      text-align: center;
      box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
      margin-top: 20px;
    }

    .h2 {
        padding-top: 10px;
        padding-left: 10px;
        color: #FFFFFF;
        font-family: sans-serif;
        font-weight: bold;
        font-size: 20px;
}
</style>
