<template>
<b-container>
  <div class="div">
  <h1 class="sign" align="center"><b-icon-heart-fill /> Favorite Restaurants</h1>
  <b-col align-h="start">
  <div class="details" v-for="details in restaurants"> <!-- just need to add v:bind here otherwise still able to loop -RR -->
     
  <b-card-group deck>
  <b-card style="max-width: 100%;">
    <b-card-title>
      <h1 class="name">
      {{details.restName}}</h1>
      </b-card-title>
      <hr>
      <b-card-text>
      <p class="det">
    <b-icon-map />
    Location:
        {{details.restLocation}}<br>
    <b-icon-clock /> 
    Hours:
        {{details.hours}}<br>
      <b-icon-star />
    Rating:
        {{details.rating}} out of 5<br>
      <b-icon-egg-fried />
    Cuisine:
        {{details.cuisine}}<br>
      <b-icon-wallet />
    Price-Range:
        {{details.priceRange}}<br>
      </p>
    </b-card-text>
     <template v-slot:footer>
        <small class="text-muted">Liked Restaurant</small>
      </template>
</b-card>
</b-card-group>

     <!-- </div>
     <img :src="details.imageUrl" />
       <h1 class="name">
        Name:
        {{details.restName}}
      </h1>
      <p class="det">
    <b-icon-map />
    Location:
        {{details.restLocation}}<br>
    <b-icon-clock /> 
    Hours:
        {{details.hours}}<br>
      </h3>
      <b-icon-star />
    Rating:
        {{details.rating}} out of 5<br>
      <b-icon-egg-fried />
    Cuisine:
        {{details.cuisine}}<br>
      </h3>
      <b-icon-wallet />
    Price-Range:
        {{details.priceRange}}<br>
      </p>-->

    </div>
  </b-col>
  </div>
</b-container>
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
.det  {
  color: #484848;
  text-align: left;
  padding: 0px 0px 0px 15px;
  font-size: 20px;
}

.sign {
        padding-top: 40px;
        padding-bottom: 5px;
        color: #252839;
        font-family: sans-serif;
        font-weight: bold;
        font-size: 50px;
        margin: 1px auto;
    }

.div {
  margin: 4em auto;
  border-radius: 1.5em;
  box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
}

.name {
  color: #000000;
  text-align: left;
  padding-left: 15px;
  font-size: 27px;
  font-weight: bold;
}

</style>