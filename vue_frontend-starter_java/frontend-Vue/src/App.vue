<template>
  <div id="app">
    <header>
    <b-nav tabs align="center">
      <b-nav-item><router-link to="/">Home</router-link></b-nav-item>
      <b-nav-item><router-link to="/register">Register</router-link></b-nav-item>
      <b-nav-item><router-link to="/profile">Profile</router-link></b-nav-item>
      <b-nav-item><router-link to="/restaurants">Restaurants</router-link></b-nav-item>
      <b-nav-item><router-link to="/details">Details</router-link></b-nav-item>
      <b-nav-item><router-link to="/Favorites">Favorites</router-link></b-nav-item>
      <b-nav-item v-if="!isAuthenticated"><router-link to="/login">Login</router-link></b-nav-item>
      <b-nav-item v-if="isAuthenticated" @click="logout">Logout</b-nav-item>
    </b-nav>
    </header>
    <router-view/>
  </div>

</template>
<script>
import auth from '@/auth';
import { bus } from '@/main';
export default {
  data() {
    return {
      isAuthenticated: auth.getUser() != null,
    };
  },
  methods: {
    logout() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/logout`, {
              method: 'POST',
              headers: new Headers({
                Authorization: 'Bearer ' + auth.getToken(),
              }),
              credentials: 'same-origin',
            })
        .then((response) => {
            if(response.ok) {
                auth.logout()
                this.isAuthenticated = false
                this.$router.push({ path: '/login' });
            }
        })
  }
  },
  created() {
    bus.$on('log-in', (data) => {
      this.isAuthenticated = true
    })
  },
};
</script>
<style>
header {
  height: 50px;
  width: 100%;
  display: flex;
  flex-direction: row;
  background-color: rgb(247, 247, 247);

}

.nav li a, .nav li a:visited {
  text-decoration: none;
  color: #00171F;
  text-transform: uppercase;
  font-size: 13px;
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1px;
  padding: 10px 20px;
  outline: none;
  text-align: center;
  font-family: sans-serif;
}
.nav li a:hover {
  text-decoration: underline;
}
</style>
