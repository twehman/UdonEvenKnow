<template>
  <div id="app">
    <header>
    <ul class="nav">
      <li><router-link to="/">Home</router-link></li>
      <li><router-link to="/login">Login</router-link></li>
      <li><router-link to="/register">Register</router-link></li>
    </ul>
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
.nav {
  padding:14px;
  margin:0;
  flex:1;
  list-style-type: none;
}
.nav li {
  display: inline;
  padding-right: 20px;
}
.nav li a, .nav li a:visited {
  text-decoration: none;
  color: rgb(56, 56, 146);
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
