<template>
  <div class="restaurant-details">
    <div class="details" v-for="details in restaurants"> <!-- just need to add v:bind here otherwise still able to loop -RR -->
     <!--<div class="featuredimage">
        <img :src= 'details.restuarant.featured_image'>
     </div>-->
     
      <h3 class="name">
        <span class="name-object">Name:</span>
        {{details.restName}}
        <img :src="details.imageUrl" />
      </h3>
      <h3 class="location">
        <span class="location-object">Location:</span>
        {{details.restLocation}}
      </h3>
       <h3 class="hours">
        <span class="hours-object">Hours:</span>
        {{details.hours}}
      </h3>
      <h3 class="rating">
        <span class="rating-object">Rating:</span>
        {{details.rating}} out of 5
      </h3>
      <h3 class="cuisine">
        <span class="cuisine-object">Cuisine:</span>
        {{details.cuisine}}
      </h3>
      <h3 class="price">
        <span class="price-object">Price-Range:</span>
        {{details.price}} 
      </h3>

    </div>
  </div>

</template>


<script>
import auth from '@/auth'
export default {
    data() {
        return {
            restaurants: []
        }
    },
    created() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/favorites`, {
              method: 'GET',
              headers: new Headers({
                Authorization: 'Bearer ' + auth.getToken(),
              }),
              credentials: 'same-origin',
            })
            .then((response) => {
              return response.json()
            })
            .then((data) => {
                data.forEach((item) => {
                    this.restaurants.push(item)
                })
            })
    }
}
</script>

<style scoped>

</style>