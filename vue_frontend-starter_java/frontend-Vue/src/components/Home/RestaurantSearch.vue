<template>
  <b-container>
    <b-form-checkbox-group
      class="custom-control-inline"
      id="item.cuisine.cuisine_name"
      v-model="selectedCuisines"
      :options="cuisines"
      name="cuisine.cuisine_name"
      value-field="cuisine.cuisine_id"
      text-field="cuisine.cuisine_name"
      buttons
      button-variant="outline-light"
      size="sm"
    ></b-form-checkbox-group>
    <b-form-group>
      <b-button variant="outline-dark" v-on:click="$emit('user-choices', selectedCuisines)">
        <b-icon-search />Search for Restaurants
      </b-button>
    </b-form-group>
  </b-container>
</template>

<script>
import auth from "@/auth";
export default {
  name: "restaurant-search",
  props: {
    zipcode: Object
  },
  data() {
    return {
      cuisines: [],
      selectedCuisines: []
    };
  },
  watch: {
    zipcode: function() {
      this.getItems();
    }
  },
  methods: {
    getItems() {
      let params = new URLSearchParams({
        lat: this.$props.zipcode.latitude,
        lon: this.$props.zipcode.longitude
      });
      fetch(
        `https://developers.zomato.com/api/v2.1/cuisines?${params.toString()}`,
        {
          method: "GET",
          headers: {
            Accept: "application/json",
            "user-key": "c1bbb3341d92fcff2ad26d1965e26008"
          }
        }
      )
        .then(response => {
          return response.json();
        })
        .then(data => {
          data.cuisines.forEach(item => {
            this.cuisines.push(item);
          });
        })
        .catch(err => console.log(err));
    },
    sendCuisines() {
      console.log("sending");
      console.log(this.selectedCuisines);
      this.$emit("user-choices", this.selectedCuisines);
    }
  },
  created() {
  }
};
</script>

<style>
#selectedCuisines {
  display: inline-flex;
  width: 20%;
}

.custom-control-inline {
  display: inline-flex;
  flex-wrap: wrap;
  vertical-align: bottom;
  margin-right: 5px;
  margin-left: 5px;
}
</style>

