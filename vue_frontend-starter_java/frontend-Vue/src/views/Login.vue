<template>
<b-container>
<div class="home">
    <b-form class="form-signin" @submit.prevent="login">
      <h1 class="sign" align="center">Please Sign In</h1>
      <b-alert show variant="danger" v-if="invalidCredentials" align="center">
        Invalid username and password!
      </b-alert>
      <b-alert show variant="success" v-if="this.$route.query.registration" align="center"> 
        Thank you for registering, please sign in.
      </b-alert>
      <p>
        <b-input-group>
        <b-input-group-prepend is-text>
        <b-icon icon="person"></b-icon>
      </b-input-group-prepend>
        <b-form-input
        type="text"
        id="username"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /></b-input-group></p>

      <p>
        <b-input-group>
        <b-input-group-prepend is-text>
        <b-icon icon="shield-lock"></b-icon>
      </b-input-group-prepend>
        <b-form-input
        type="password"
        id="password"
        placeholder="Password"
        v-model="user.password"
        required
      /></b-input-group></p>
      <p align="center"><b-button variant="outline-primary" type="submit"><b-icon-check-box /> Sign in</b-button></p>
      <p align="center"><router-link :to="{ name: 'register' }">Need an account?</router-link></p>
      
    </b-form>
  </div>
</b-container>
</template>

<script>
import auth from '../auth';
import { bus } from '../main';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
      hasPreferences: false
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            bus.$emit("log-in", "success")
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            fetch(`${process.env.VUE_APP_REMOTE_API}/profile`, {
              method: 'GET',
              headers: new Headers({
                Authorization: 'Bearer ' + auth.getToken(),
              }),
              credentials: 'same-origin',
            })
            .then((response) => {
              return response.json()
            })
            .then((data) => {
              this.hasPreferences = data.hasPreferences
              if(this.hasPreferences) {
                this.$router.push({ path: '/' })
              }
              else {
                this.$router.push({ path: '/profile' })
              }
            })
          }
        })
        .catch((err) => console.error(err));
    },
  },
};
</script>

<style>
    .home {
        background-color: #FFFFFF;
        width: 400px;
        height: 400px;
        margin: 4em auto;
        border-radius: 1.5em;
        box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
    }
    
    .sign {
        padding-top: 40px;
        color: #252839;
        font-family: sans-serif;
        font-weight: bold;
        font-size: 23px;
    }
   
    .un:focus, .pass:focus {
        border: 2px solid rgba(0, 0, 0, 0.18) !important;
        
    }
    
    @media (max-width: 600px) {
        .main {
            border-radius: 0px;
        }
    }
</style>
