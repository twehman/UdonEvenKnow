<template>
<b-container>
<div class="profile">
  <div id="profile" class="text-center">
    <b-form class="form-profile" @submit.prevent="profile">
      <h1 class="sign" align="center">Create Profile</h1>
       
      <b-form-input
        type="text"
        id="firstName"
        placeholder="FirstName"
        v-model="userPreferences.firstName"
        required
        autofocus
      />
      <b-form-input
        type="text"
        id="lastName"
        placeholder="LastName"
        v-model="userPreferences.lastName"
        required
      />
      <b-form-input
        type="text"
        id="address1"
        placeholder="Address 1"
        v-model="userPreferences.addressOne"
        required
      />
      <b-form-input
        type="text"
        id="address2"
        placeholder="Address 2"
        v-model="userPreferences.addressTwo"
        
      />
      
       <b-form-input
        type="text"
        id="city"
        placeholder="City"
        v-model="userPreferences.city"
        required
      />
       
       <b-form-select v-model="userPreferences.state" :options="states" name="state"></b-form-select>
     
       <b-form-input
        type="text"
        id="zipcode"
        placeholder="Zipcode"
        pattern="[0-9]{5}"
        oninvalid="setCustomValidity('Please enter valid zipcode.')" 
        oninput="setCustomValidity('')"
        
        v-model="userPreferences.zipCode"
        
      />
      <p align="center"><b-button variant="primary" type="submit">
        Submit
      </b-button></p>
    </b-form>
  </div>
  </div>
  </b-container>
</template>

<script>
import auth from '@/auth'
export default {
  name: 'profile',
  //user: auth.getUser(),
  data() {
    return { 
      userPreferences: {
        firstName: '',
        lastName: '',
        addressOne: '',
        addressTwo: '',
        city: '',
        state: '',
        zipCode: '',
      },
      states: [
        { value: null, text: 'Please select a state'},
        { value: 'AL', text: 'Alabama'},
        { value: 'AK', text: 'Alaska'},
        { value: 'AZ', text: 'Arizona'},
        { value: 'AR', text: 'Arkansas'},
        { value: 'CA', text: 'California'},
        { value: 'CO', text: 'Colorado'},
        { value: 'CT', text: 'Connecticut'},
        { value: 'DE', text: 'Delaware'},
        { value: 'DC', text: 'District of Columbia'},
        { value: 'FL', text: 'Florida'},
        { value: 'GA', text: 'Georgia'},
        { value: 'HI', text: 'Hawaii'},
        { value: 'ID', text: 'Idaho'},
        { value: 'IL', text: 'Illinois'},
        { value: 'IN', text: 'Indiana'},
        { value: 'IA', text: 'Iowa'},
        { value: 'KS', text: 'Kansas'},
        { value: 'KY', text: 'Kentucky'},
        { value: 'LA', text: 'Lousiana'},
        { value: 'ME', text: 'Maine'},
        { value: 'MD', text: 'Maryland'},
        { value: 'MA', text: 'Massachusetts'},
        { value: 'MI', text: 'Michigan'},
        { value: 'MN', text: 'Minnesota'},
        { value: 'MS', text: 'Mississippi'},
        { value: 'MO', text: 'Missouri'},
        { value: 'MT', text: 'Montana'},
        { value: 'NE', text: 'Nebraska'},
        { value: 'NV', text: 'Nevada'},
        { value: 'NH', text: 'New Hampshire'},
        { value: 'NJ', text: 'New Jersey'},
        { value: 'NM', text: 'New Mexico'},
        { value: 'NY', text: 'New York'},
        { value: 'NC', text: 'North Carolina'},
        { value: 'ND', text: 'North Dakota'},
        { value: 'OH', text: 'Ohio'},
        { value: 'OK', text: 'Oklahoma'},
        { value: 'OR', text: 'Oregon'},
        { value: 'PA', text: 'Pennsylvania'},
        { value: 'RI', text: 'Rhode Island'},
        { value: 'SC', text: 'South Carolina'},
        { value: 'SD', text: 'South Dakota'},
        { value: 'TN', text: 'Tennessee'},
        { value: 'TX', text: 'Texas'},
        { value: 'UT', text: 'Utah'},
        { value: 'VT', text: 'Vermont'},
        { value: 'VA', text: 'Virginia'},
        { value: 'WA', text: 'Washington'},
        { value: 'WV', text: 'West Virginia'},
        { value: 'WI', text: 'Wisconsin'},
        { value: 'WY', text: 'Wyoming'}
      ]
      
    }
  },
  methods: {
  
    profile() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/profile`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + auth.getToken(),
          User: auth.getUser()
        },
        body: JSON.stringify(this.userPreferences)
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/' });
          } 
        })

        .then((err) => console.error(err));
    },
  },
};
</script>

<style>
body {
        background-color: #F3EBF6;
        font-family: sans-serif;
    }
    
    .profile {
        background-color: #FFFFFF;
        width: 400px;
        height: 400px;
        margin: 7em auto;
        border-radius: 1.5em;
        box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
    }
    
    .sign {
        padding-top: 40px;
        color: #737CA1;
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
    
    .login {
        text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
        color: #E1BEE7;
        padding-top: 0px;
    }
    
    a {
        text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
        color: #E1BEE7;
        text-decoration: none
    }
    
    @media (max-width: 600px) {
        .main {
            border-radius: 0px;
        }
    }
</style>
