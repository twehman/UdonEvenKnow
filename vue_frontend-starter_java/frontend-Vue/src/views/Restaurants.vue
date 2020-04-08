<template>
<div>
<p>{{this.latitude}} </p>
</div>
</template>

<script>
export default {
  name: 'restaurants',
  data() {
    return {
      zipcode: {
        zip: '',
        latitude: '',
        longitude: '',
      },
    };
  },
  methods: {

  },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/profile`, {
              method: 'GET',
              headers: new Headers({
                Authorization: 'Bearer ' + auth.getToken(),
              }),
              credentials: 'same-origin',
            })
            .then((response) => {
          return response.json
      })
      .then((userZipInfo)=> {
          if(userZipInfo.zip === 99999) {
              this.$router.push({ path: '/login'})
          }
          this.zip = userZipInfo.zip
          this.latitude = userZipInfo.latitude
          this.longitude = userZipInfo.longitude
      })
      .catch((err) => console.log(err))

  }
};
</script>

<style>
</style>
