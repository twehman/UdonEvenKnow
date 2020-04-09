<template>
  <div class="like-container">
    <b-button id="like" variant="danger" v-on:click="LikeRestaurant">Like</b-button>
  </div>
</template>

<script>
export default {
  name: "LikeButton",
  methods: {
    LikeRestaurant() {
      this.$emit("Like");
    }
  }
};
</script>

<style>
div.like-container {
  display: inline-block;
}
</style>