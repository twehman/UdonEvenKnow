<template>
<div>
<p>{{this.zipcode.latitude}} </p>
</div>
</template>

<script>
import auth from '@/auth'
export default {
  name: 'restaurants',
  data() {
    return {
      zipcode: {
        zip: '',
        latitude: '',
        longitude: '',
      },
      userinf: [],
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
          if(userZipcode.zip === 99999) {
              this.$router.push({ path: '/login'})
          }
      })
      .catch((err) => console.log(err))

  }
};
</script>

<style>
</style>
