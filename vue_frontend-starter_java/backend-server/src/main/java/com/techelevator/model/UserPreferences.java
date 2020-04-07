package com.techelevator.model;

public class UserPreferences {



    // Declared Variables
    public long users_Id;
    public int id;
    public String first_name;
    public String last_name;
    public String addressOne;
    public String addressTwo;
    public String city;
    public String state;
    public int zip_code;

    
    
    // The users ID. 
    public long getUserId() {
        return users_Id;
    }

    public void setUserId(long users_Id) {
        this.users_Id = users_Id;
    }
    // The table Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    // The user's first name.
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
   
    // The user's last name.
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
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
        return zip_code;
    }

    public void setZipCode(int zip_code) {
        this.zip_code = zip_code;
    }

   

   







}