<template>
  <div id="app">
    <header>
      <b-nav tabs align="center">
        <b-nav-item>
          <router-link to="/">Home</router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/favorites">Favorites</router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/profile">Update User Profile</router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/register">Register</router-link>
        </b-nav-item>
        <b-nav-item v-if="!isAuthenticated">
          <router-link to="/login">Login</router-link>
        </b-nav-item>
        <b-nav-item v-if="isAuthenticated" @click="logout">Logout</b-nav-item>
      </b-nav>
    </header>

    <div class="footer">© Team EL Expression</div>
    <router-view />
  </div>
</template>
<script>
import auth from "@/auth";
import { bus } from "@/main";
export default {
  data() {
    return {
      isAuthenticated: auth.getUser() != null
    };
  },
  methods: {
    logout() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/logout`, {
        method: "POST",
        headers: new Headers({
          Authorization: "Bearer " + auth.getToken()
        }),
        credentials: "same-origin"
      }).then(response => {
        if (response.ok) {
          auth.logout();
          this.isAuthenticated = false;
          this.$router.push({ path: "/login" });
        }
      });
    }
  },
  created() {
    bus.$on("log-in", data => {
      this.isAuthenticated = true;
    });
  }
};
</script>
<style>
header {
  height: 50px;
  width: 100%;
  display: flex;
  flex-direction: row;
  background-color: #e8f1f2;
}

.nav li a,
.nav li a:visited {
  text-decoration: none;
  color: #00171f;
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

.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: #e8f1f2;
  color: #00171f;
  text-align: center;
  font-size: 14px;
  font-family: "Courier New", Courier, monospace;
}
</style>
