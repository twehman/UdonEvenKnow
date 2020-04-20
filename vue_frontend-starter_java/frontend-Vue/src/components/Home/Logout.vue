<template>
  <div>
    <b-button id="logout" variant="danger" v-on:click="Logout">Logout</b-button>
  </div>
</template>

<script>
import auth from "@/auth";
export default {
  name: "logout",
  methods: {
    Logout() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/restaurants`, {
        method: "POST",
        headers: new Headers({
          Authorization: "Bearer " + auth.getToken()
        }),
        credentials: "same-origin"
      }).then(response => {
        if (response.ok) {
          auth.logout();
          this.$router.push({ path: "/login" });
        }
      });
    }
  }
};
</script>

<style>
div.like-container {
  display: inline-block;
}
</style>