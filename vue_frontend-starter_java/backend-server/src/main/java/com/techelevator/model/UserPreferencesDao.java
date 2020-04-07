package com.techelevator.model;

public interface UserPreferencesDao {

    public UserPreferences saveUserPreferences(String userId, String firstName, String lastName, String addressOne, String addressTwo, String city, String State, String zipCode);

}