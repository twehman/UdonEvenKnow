package com.techelevator.model;

public class UserPreferences {



    // Declared Variables
    public String userId;
    public String firstName;
    public String lastName;
    public String addressOne;
    public String addressTwo;
    public String city;
    public String state;
    public String zipCode;

    
    
    // The users ID. 
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    // The user's first name.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
   
    // The user's last name.
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressOne() {
        return addressOne;
    }
   
    // The user's address.
    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }
   
    // The user's second address if applicable.
    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    // the user's city.
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    // The user's state.
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    // The user's zipcode.
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

   







}