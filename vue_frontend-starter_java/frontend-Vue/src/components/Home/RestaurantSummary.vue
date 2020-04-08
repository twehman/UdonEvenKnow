<template>
  <div class="restaurant-summaries">
    <div class="summary" v-for="summaries in restaurants" :key="">
      <div class="featuredimage">
        <img :src="#">
      <div class="text-infor">
        <div class="text-container" id="text-info">
          <h3 class="name">{{summaries.name}}</h3>
          <h3 class="rating">
            <span class="rating-object">Rating:{{summaries.rating}}</span>
            
          </h3>
          <h3 class="location">
            <span class="location-object">Location:{{summaries.location}}</span>
            
          </h3>
          <h3>
            <span class="cuisine-object">Cuisine:{{summaries.cuisine}}</span>
            
          </h3>
          <h3>
            <span class="price-range">Price:{{summaries.price}}</span>
            
          </h3>
        </div>
      </div>
    </div>
   
   
  </div>
  </div>
</template>

<script>
import auth from '@/auth'

export default {
  name: "RestaurantSummary",
  props: {
    summaries: Object
  },
  components: {
    
  },
  computed: {
    
  
  },
  methods: {
    
  },
 
};
</script>

<style>

</style>