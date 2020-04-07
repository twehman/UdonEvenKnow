package com.techelevator.model;

public class UserPreferences {



    // Declared Variables
    public long usersId;
    public long id;
    public String firstName;
    public String lastName;
    public String addressOne;
    public String addressTwo;
    public String city;
    public String state;
    public int zipCode;
    public boolean userPreferencesExist;

    
    
    // The users ID. 
    public long getUserId() {
        return usersId;
    }

    public void setUserId(long usersId) {
        this.usersId = usersId;
    }
    // The table Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    
    // The user's Zip Code.
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isUserPreferencesExist() {
        return userPreferencesExist;
    }

    public void setUserPreferencesExist(boolean userPreferencesExist) {
        this.userPreferencesExist = userPreferencesExist;
    }

   

   







}