<template>
<b-container>
<div class="home">
  <div id="register" class="text-center">
    <b-form class="form-register" @submit.prevent="register">
      <p><h1 class="sign" align="center">Create Account</h1></p>
      <b-alert show variant="danger" v-if="registrationErrors" align="center">
        There were problems registering this user.
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
        <b-icon icon="shield-lock"></b-icon></b-input-group-prepend>
        <b-form-input
        type="password"
        id="password"
        placeholder="Password"
        v-model="user.password"
        required
      /></b-input-group></p>
      <p>
        <b-input-group>
        <b-input-group-prepend is-text>
        <b-icon icon="shield-lock"></b-icon></b-input-group-prepend>
        <b-form-input
        type="password"
        id="confirmPassword"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      /></b-input-group></p>
      <p align="center"><b-button variant="outline-primary" type="submit">
        <b-icon-check-box /> Create Account
      </b-button></p>
      <p align="center"><router-link :to="{ name: 'login' }">
        Have an account?
      </router-link></p>
    </b-form>
  </div>
  </div>
  </b-container>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/login', query: { registration: 'success' } });
          } else {
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
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
        color:#252839;
        font-family: sans-serif;
        font-weight: bold;
        font-size: 23px;
    }
    
    form.form1 {
        padding-top: 40px;
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
