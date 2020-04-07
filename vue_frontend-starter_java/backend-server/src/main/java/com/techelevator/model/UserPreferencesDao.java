package com.techelevator.model;

import java.util.List;

public interface UserPreferencesDao {

    /**
     * Save a new user's preferences to the database. 
     *
     * @param users_Id the user ID to give the new user a primary key
     * @param id the table ID to provide relatable key
     * @param first_name the user's first name
     * @param last_name the user's last name
     * @param addressOne the user's primary Address
     * @param addressTwo the user's secondary Address if applicable
     * @param city the user's city
     * @param state the user state
     * @param zip_code the user's Zip Code
     * @return the new user
     */
    public UserPreferences saveUserPreferences(long users_Id, int id, String first_name, String last_name, String addressOne, 
                                                     String addressTwo, String city, String State, int zip_code);

    public void changeUserPreferences(String first_name, String last_name, String addressOne, String addressTwo, 
                                                                   String city, String State, int zip_code);


     /**
     * Look for a user preferences with the given user ID. 
     * 
     * @param users_ID the user name of the user we are checking
     * @return true if the user is found and their ID matches existing data?
     */  
    public UserPreferences getValidUserPreferencesWithId(long users_Id);

    /**
     * Get all of the user's preferences from the database.
     * @return a List of user preference objects
     */
    public List<UserPreferences> getAllUserPreferences();
}