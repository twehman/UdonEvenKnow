<template>
  <div class="dislike-button">
    <button class="btn btn-danger" v-on:click="DislikeRestaurant">Dislike</button>
  </div>
</template>

<script>
export default {
  name: "DislikeButton",
  methods: {
    DislkeRestaurant() {
      this.$emit("Dislike");
    }
  }
};
</script>

<style>
div.like-container {
  display: inline-block;
}
</style>