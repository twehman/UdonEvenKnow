<template>
<b-container fluid>
  <b-col>
  <div class="back">
   <div class="details" v-for="details in currentRestaurant"> 

  <b-card
    :img-src="details.restaurant.featured_image"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 100%;"
    class="mb-2"
  >
  <b-card-title>
      <h1 class="name"><b-link class="link" :href="details.restaurant.url" target="_blank">{{details.restaurant.name}}</b-link></h1>
  </b-card-title>
  <hr>
  <b-card-text>
  <p class="det">
    <b-icon-map />
    Location:
        {{details.restaurant.location.address}}<br>
        <b-icon-clock />
    Hours:
        {{details.restaurant.timings}}<br>
        <b-icon-star />
    Rating:
        {{details.restaurant.user_rating.aggregate_rating}} out of 5 <br>
        <b-icon-egg-fried />
    Cuisine:
        {{details.restaurant.cuisines}}<br>
        <b-icon-wallet />
    Price-Range:
        <span class="price object" v-for="i in details.restaurant.price_range">$</span><br>
      </p>
  </b-card-text>
  <div id="buttons">
    <b-button-group>
       <b-button @click="dislikeRestaurant" variant="danger">Dislike</b-button>
       <b-button @click="likeRestaurant" variant="success">Like</b-button>
    </b-button-group>
  </div>
  <template v-slot:footer>
    You make miso happy!
</template>
  </b-card>
  
   </div>
  </div>
</b-col>
</b-container>
</template>

<script>
import auth from '@/auth';
import { bus } from "../../main.js";

export default { 
    name: "RestaurantDetails",
    props: {
        choices: Array,
        details: Object
    },
    components: {
        
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
    },
    choices: function() {
          fetch(`https://developers.zomato.com/api/v2.1/search?lat=${this.details.latitude}&lon=${this.details.longitude}&radius=&radius=10000&cuisines=${this.choices.toString()}`, {
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
            this.currentRestaurant = []
            this.testArray = []
            this.restaurants = data
            data.restaurants.forEach((item) => {
              this.testArray.push(item)
            })
            console.log('hi')
            this.currentRestaurant.push(this.testArray.shift())
            })
        .catch((err) => console.log(err))
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
      let dollarSymbols = ""
      for (let i = 0; i < this.currentRestaurant[0].restaurant.price_range; i++ ) {
        dollarSymbols += "$"
      }
      console.log(`${this.currentRestaurant[0].restaurant.name}`)
        let payload = {
          restName: this.currentRestaurant[0].restaurant.name,
          restLocation: this.currentRestaurant[0].restaurant.location.address,
          imageUrl: this.currentRestaurant[0].restaurant.featured_image,
          priceRange: dollarSymbols,
          hours: this.currentRestaurant[0].restaurant.timings,
          cuisine: this.currentRestaurant[0].restaurant.cuisines,
          rating: this.currentRestaurant[0].restaurant.user_rating.aggregate_rating
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
    

}
};
</script>

<style>
.det  {
  color: #484848;
  text-align: left;
  padding: 0px 0px 0px 15px;
  font-size: 20px;
}

.back {
  background-color: #FFFFFF;
  margin: 1em 1em 1em 1em;
}

.name {
  color: #000000;
  text-align: left;
  padding-left: 15px;
  font-size: 27px;
  font-weight: bold;
}

 .link {
   color: #000000;
}

.link:hover {
    color: #000000;
}
</style>
