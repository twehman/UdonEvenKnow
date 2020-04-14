<template>
  <div class="restaurant-details">
    <div class="details" v-for="details in currentRestaurant"> <!-- just need to add v:bind here otherwise still able to loop -RR -->
      <h3 class="name">
        <span class="name-object">Name:</span>
        {{details.restaurant.name}}
        <img :src="details.restaurant.featured_image" />
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
      <h3 class="price">
        <span class="price-object" >Price-Range:</span>
        <span class="price object" v-for="i in details.restaurant.price_range">$</span>
      </h3>
    </div>
    <div id="buttons">
       <b-button @click="dislikeRestaurant" variant="danger">Dislike</b-button>
       <b-button @click="likeRestaurant" variant="success">Like</b-button>
   </div>
  </div>
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
import { bus } from "../../main.js";

export default { 
    name: "RestaurantDetails",
    props: {
        choices: Array,
        details: Object
    },
    components: {
        DislikeButton,
        LikeButton
    },
    computed: {
    },
  

  data() {
    return {
            restaurantNumber: 0,
            restaurants: [],
            testArray: [],
            currentRestaurant: [],
            errorArray: "No More Restaurants"
        }
    },
  
  watch: {
    details: function(shuffled) {
      let shuffArray = shuffled.restaurants;
      this.shuffle(shuffArray);
    }
  },

  methods: {
   nextRestaurant() {
      if(this.details.restaurant.length < 1 ) {
          return this.errorArray;
      }
      if(this.restaurantNumber < this.details.restaurant.length - 1) {
        this.restauantNumber = this.restaurantNumber + 1;
      } else{
        this.restaurantNumber = 0;
      }
    },
    dislikeRestaurant() {
      this.currentRestaurant.shift()
      this.currentRestaurant.push(this.testArray.shift())
    },
    likeRestaurant() {
      console.log('in like method')
      console.log(`${this.currentRestaurant[0].restaurant.name}`)
        let payload = {
          restName: this.currentRestaurant[0].restaurant.name,
          restLocation: this.currentRestaurant[0].restaurant.location.address,
          imageUrl: this.currentRestaurant[0].restaurant.featured_image,
          priceRange: this.currentRestaurant[0].restaurant.price_range,
          hours: this.currentRestaurant[0].restaurant.timings,
          cuisine: this.currentRestaurant[0].restaurant.cuisine,
        };
        const url = `${process.env.VUE_APP_REMOTE_API}/favorites`;
              fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + auth.getToken(),
          User: auth.getUser()
        },
        body: JSON.stringify(payload)
      })
        .then((response) => {
          if (response.ok) {
          this.currentRestaurant.shift()
          this.currentRestaurant.push(this.testArray.shift())
        } 
        })
       .catch ((error) => {
        console.log(error)
      })
    },
    shuffle(a) {
      var j, x, i;
      for (i = a.length - 1; i > 0; i--) {
        j= Math.floor(Math.random() * (i + 1));
        x = a[i];
        a[i] = a[j];
        a[j] = x;
      }
      return a;
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
            console.log('hi')
            this.currentRestaurant.push(this.testArray.shift())
            })
        .catch((err) => console.log(err))

}
};
</script>

<style>

</style>
