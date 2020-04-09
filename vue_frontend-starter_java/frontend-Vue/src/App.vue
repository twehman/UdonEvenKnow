<template>
  <div id="app">
    <header>
    <b-nav tabs align="center">
      <b-nav-item><router-link to="/">Home</router-link></b-nav-item>
      <b-nav-item><router-link to="/login">Login</router-link></b-nav-item>
      <b-nav-item><router-link to="/register">Register</router-link></b-nav-item>
      <b-nav-item><router-link to="/profile">Profile</router-link></b-nav-item>
      <b-nav-item><router-link to="/restaurants">Restaurants</router-link></b-nav-item>
    </b-nav>
    </header>
    <router-view/>
  </div>
</template>

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
  color: #151B54;
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
