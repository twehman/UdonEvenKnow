<template>
<RestaurantSearch></RestaurantSearch>
</template>

<script>
import auth from '@/auth'
import RestaurantSearch from '@/components/Home/RestaurantSearch.vue'
export default {
  name: 'restaurants',
  components: {
      RestaurantSearch
  },
  data() {
    return {
      zipcode: {
        zip: '',
        latitude: '',
        longitude: '',
      },
      cuisines: []
    };
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
    fetch('https://developers.zomato.com/api/v2.1/cuisines?lat=41.480881&lon=-81.80036', {
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
            this.cuisines = data
        })
        .catch((err) => console.log(err))

}
}
</script>

<style>
</style>

