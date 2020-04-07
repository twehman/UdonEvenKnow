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
      fetch(`${process.env.VUE_APP_REMOTE_API}/home`)
      .then((response) => {
          return response.json
      })
      .then((userZipInfo)=> {
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
