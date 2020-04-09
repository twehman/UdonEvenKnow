<template>
  <div class="restaurant-details">
    <div class="details" v-for="details in testArray">
      <h3 class="name">
        <span class="name-object">Name:</span>
        {{details.restaurant.name}}
      </h3>
      <h3 class="location">
        <span class="location-object">Location:</span>
        {{details.restaurant.location.address}}
      </h3>
       <h3 class="hours">
        <span class="hours-object">Hours:</span>
        {{details.restaurant.timings}}
      </h3>
      <h3 class="rating">
        <span class="rating-object">Rating:</span>
        {{details.restaurant.user_rating.aggregate_rating}} out of 5
      </h3>
      <h3 class="cuisine">
        <span class="cuisine-object">Cuisine:</span>
        {{details.restaurant.cuisines}}
      </h3>
    </div>
    <div id="buttons">
    <dislike-button v-on:Dislike="dislikeRestaurant" v-if:="details.restaurants"/>
    <like-button v-on:Like="likeRestaurant" v-if:="details.restaurants"/>
   </div>
  </div>
  
  
  <!--
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
-->
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
    computed: {
        dollarprice: function() {
            let dollarsigns = "";
            if (this.details && this.details.restaurants) {
                for (
                    let i = 0; i < this.details.restaurants.price_range; i++) {
          dollarsigns += "$";
        }
      }
      return dollarsigns;
    }, /*
       getImage() {
      let thisRestaurant = this.details.restaurants[this.restaurantNumber].restaurant;
      if (
        thisRestaurant.featured_image
      ) {
        return thisRestaurant.featured_image;
      } 
      return "https://via.placeholder.com/1200x464";
    } */
  },
  

  data() {
        return {
            restaurantNumber: 0,
            restaurants: [],
            testArray: []
        }
    },
 
  
  
  methods: {
    
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
    fetch(`https://developers.zomato.com/api/v2.1/search?lat=41.480881&lon=-81.80036`, {
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
            this.restaurants = data
            data.restaurants.forEach((item) => {
              this.testArray.push(item)
            })
            })
        .catch((err) => console.log(err))

}
};
</script>

<style>

</style>
