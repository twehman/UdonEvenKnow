<template>
  <div class="restaurant-details">
    <div class="details" v-for="details in currentRestaurant"> <!-- just need to add v:bind here otherwise still able to loop -RR -->
     <!--<div class="featuredimage">
        <img :src= 'details.restuarant.featured_image'>
     </div>-->
     
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
        <span class="price-object">Price-Range:</span>
        {{price}}  
      </h3>

    </div>
   <!-- <div id="buttons">
        <b-button href="#" variant="success">Like</b-button>  <!--Buttons not connected to components yet, stay tuned -RR 
        <br/>
        <b-button href="#" variant="danger">Dislike</b-button> <!--Buttons not connected to components yet, stay tuned -RR 
   </div> -->

    <div id="buttons">
       <b-button @click="dislikeRestaurant" />
       <like-button v-on:Like="likeRestaurant" v-if:="details.restaurant"/>
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
import { bus } from "../../main.js";

export default { 
    name: "RestaurantDetails",
    props: {
        choices: Object,
        details: Object
    },
    components: {
        DislikeButton,
        LikeButton
    },
    computed: {
       dollarPrice: function() {  // trying to convert pricerange into $-$$ -RR 
            let dollarsigns = "";
            
                for (
                    let i = 0; i < this.details.restaurant.price_range; i++) {
          dollarsigns += "$";
        }
      
      return dollarsigns;
    }, 
      price: function(){    // trying to convert price_range into $ --- ex: 2 == $$, 3==$$$   --RR
        let dollar = '$';
        let a = parseInt("details.restaurant.price_range"); // supposed to convert string price_range into number --RR
        return dollar.repeat(a);  // for some reason this only works when you hardcode ie replace a with 2 --RR
      },
       getImage() { 
      let thisRestaurant = this.details.restaurant;
      if (
        thisRestaurant.featured_image
      ) {
        return thisRestaurant.featured_image;
      }     
      return thisRestaurant.featured_image;
    }  
  },
  

  data() {
    return {
            restaurantNumber: 0,
            restaurants: [],
            testArray: [],
            currentRestaurant: [],
            errorArray: "Out of Restaurants"
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
      try {
        const payload = {
          "RestaurantId": this.details.restaurant.restaurant.id,
           "RestaurantName": this.details.restaurant.name,
           "RestaurantImage": this.details.restaurant.featured_image,
           "RestaurantPriceRange": this.details.restaurant.restaurant.price_range
        };
        const url = `${process.env.VUE_APP_REMOTE_API}/Favorites`;
        const response = fetch(url, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + auth.getToken()
          },
          body: JSON.stringify(payload)
        });
        if (response.status === 400) {
          this.error = "NahB ruh";
        } else {
          if (this.details.restaurant.length < 1) {
            return this.emptyArray;
          }
          if (this.restaurantNumber < this.details.restaurant.length - 1) {
            this.restaurantNumber = this.restaurantNumber + 1;
          } else {
            this.restaurantNumber = 0;
          }
        }
      } catch (error) {
        this.error = "no beuno!!!!";
      }
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
