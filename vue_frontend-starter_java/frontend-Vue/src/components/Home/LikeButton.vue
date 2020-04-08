<template>
  <div class="like-container">
    <button id="like" class="btn btn-primary" v-on:click="LikeRestaurant">Like</button>
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