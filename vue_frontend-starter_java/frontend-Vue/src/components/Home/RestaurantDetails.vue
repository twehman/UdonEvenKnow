<template>
  <div class="restaurant-details">
    <div class="details" v-if="details.restaurants" :key="restaurantNumber">
      <div class="featuredimage">
        <img :src="getImage">
      <div class="text-infor">
        <div class="text-container" id="text-info">
          <h3 class="name">{{details.restaurants[restaurantNumber].restaurant.name}}</h3>
          <h3 class="rating">
            <span class="rating-object">Rating:</span>
            {{details.restaurants[restaurantNumber].restaurant.user_rating.aggregate_rating}} out of 5
          </h3>
          <h3 class="location">
            <span class="location-object">Location:</span>
            {{details.location[restaurantNumber].restaurant.location}} 
          </h3>
          <h3>
            <span class="cuisine-object">Cuisine:</span>
            {{details.cuisine[restaurantNumber].restaurant.cuisine}}  
          </h3>
          <h3>
            <span class="price-range">Price:</span>
            {{details.price[restaurantNumber].restaurant.price}}
          </h3>
        </div>
      </div>
    </div>
   
   
  </div>
  </div>
</template>

<script>
import auth from '@/auth';
import DislikeButton from "@/components/Home/DislikeButton.vue";
import LikeButton from "@/components/Home/LikeButton.vue";

export default {
    name: "RestaurantDetails",
    props: {
        details: Object
    },
    components: {
        DislikeButton,
        LikeButton
    },
    

  data() {
        return {
            restaurants: [],
            apiUrl: ""
        }
    },
 
  computed: {
    
  
  },
  methods: {
    
  },
      created(){
fetch(this.apiUrl)
.then(response=>{
  return response.json();
}).then(data=>{
  this.groceries= data
}).catch(err=>{
  console.log(err);
})
}
 
};
</script>

<style>

</style>