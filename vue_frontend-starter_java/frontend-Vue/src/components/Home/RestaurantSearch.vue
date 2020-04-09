<template>
<b-container>
<div>
  <!-- <b-form-checkbox-group
    id="item.cuisine.cuisine_name"
    v-model="selectedCuisines"
    :options="cuisines"
    name="item.cuisine.cuisine_name">
<b-form-checkbox v-for=“item in cuisines.cuisines" :value="selectedCuisines"></b-form-checkbox>
</b-form-checkbox-group> -->

    

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
  watch: {
      zipcode: function() {
          this.getItems()
      }
  },
  methods: {
      getItems() {
          let returnArray = []
          let params = new URLSearchParams({"lat" : this.$props.zipcode.latitude , "lon" : this.$props.zipcode.longitude});
    fetch(`https://developers.zomato.com/api/v2.1/cuisines?${params.toString()}`, {
              method: 'GET',
              headers: {
                Accept: 'application/json',
                'user-key': 'c1bbb3341d92fcff2ad26d1965e26008',
              }
        })
        .then((response) =>{
            return response.json()
        })
        .then((data) => {
            returnArray = data
            this.cuisines = data
        })
        .catch((err) => console.log(err))
      }
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
   /* let params = new URLSearchParams({"lat" : this.$props.zipcode.latitude , "lon" : this.$props.zipcode.longitude});
    fetch(`https://developers.zomato.com/api/v2.1/cuisines?${params.toString()}`, {
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
*/
}
}
</script>

<style>
#selectedCuisines {
    display: inline-flex;
    width: 20%;

}
</style>

