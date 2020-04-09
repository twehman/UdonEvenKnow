<template>
<b-container>
<div>
  <!--B-FORM CODE THAT NEEDS TO IMPLEMENT THE CUISINE TYPES-
  <div v-for="item in cuisines.cuisines" id="selectedCuisines">
    <b-form-checkbox-group
    id="item.cuisine.cuisine_name"
    v-model="selectedCuisines"
    :options="cuisines"
    name="item.cuisine.cuisine_name"
    />
    </div>
    -->

    <!-- TOM'S CODE THAT WORKS -->
      <div v-for="item in cuisines.cuisines" id="selectedCuisines">
        <input type="checkbox" :id="item.cuisine.cuisine_name" :name="item.cuisine.cuisine_name" :value="item.cuisine.cuisine_id" v-model="selectedCuisines">
        <label :for="item.cuisine.cuisine_name"> {{item.cuisine.cuisine_name}}</label>
    </div>
</div>
  </div>
</b-container>
</template>

<script>
import auth from '@/auth'
export default {
  name: 'restaurant-search',
  props: {
      zipcode: Object
  },
  data() {
    return {
      cuisines: [],
      selectedCuisines: []
    };
  },
  methods: {

  },
  created() {
      /* console.log(this.zipcode.latitude)

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
    */
    fetch(`https://developers.zomato.com/api/v2.1/cuisines?lat=41.480881&lon=-81.80036`, {
              method: 'GET',
              headers: {
                Accept: 'application/json',
                'user-key': '4c1372de3bf074d7157807284b3d747f',
              }
        })
        .then((response) =>{
            return response.json()
        })
        .then((data) => {
            this.cuisines = data
        })
        .catch((err) => console.log(err))

}
}
</script>

<style>
#selectedCuisines {
    display: inline-flex;
    width: 20%;

}
</style>

