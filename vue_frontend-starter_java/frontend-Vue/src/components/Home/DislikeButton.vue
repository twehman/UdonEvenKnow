<template>
  <div class="dislike-button">
    <b-button variant="dark" v-on:click="DislikeRestaurant">Dislike</b-button>
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