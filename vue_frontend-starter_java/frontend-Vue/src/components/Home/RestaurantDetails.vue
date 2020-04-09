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
            <span class="dollar-sign">{{dollarprice}}</span>
          </h3>
        </div>
      </div>
    </div>
   <div id="buttons">
       <dislike-button v-on:Dislike="dislikeRestaurant" v-if:="details.restaurants"/>
       <like-button v-on:Like="likeRestaurant" v-if:="details.restaurants"/>
   </div>
   
  </div>
  </div>
</template>

<!-- Let's implement B-cards for the restaurant details? I will change once everything works -SC
<div>
  <b-card
    title="Card Title"
    img-src="https://picsum.photos/600/300/?image=25"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"
  >
    <b-card-text>
      Some quick example text to build on the card title and make up the bulk of the card's content.
    </b-card-text>

    <b-button href="#" variant="primary">Go somewhere</b-button>
  </b-card>
</div>
-->

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
    computed: {
        dollarprice: function() {
            let dollarsigns = "";
            if (this.details && this.details.restaurants) {
                for (
                    let i = 0; i < this.details.restaurants[this.restaurantNumber].restaurant.price_range; i++) {
          dollarsigns += "$";
        }
      }
      return dollarsigns;
    },

  data() {
        return {
            restaurants: [],
            apiUrl: ""
        }
    },
 
  
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