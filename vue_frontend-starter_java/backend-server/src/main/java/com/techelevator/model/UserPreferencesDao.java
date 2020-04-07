package com.techelevator.model;

import java.util.List;

public interface UserPreferencesDao {

    /**
     * Save a new user's preferences to the database. 
     *
     * @param usersId the user ID to give the new user a primary key
     * @param id the table ID to provide relatable key
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param addressOne the user's primary Address
     * @param addressTwo the user's secondary Address if applicable
     * @param city the user's city
     * @param state the user state
     * @param zipCode the user's Zip Code
     * @return the new user
     */
    public UserPreferences saveUserPreferences(long id, String firstName, String lastName, String addressOne, 
                                                     String addressTwo, String city, String State, int zipCode);

    public void changeUserPreferences(String firstName, String lastName, String addressOne, String addressTwo, 
                                                                   String city, String State, int zipCode);


     /**
     * Look for a user preferences with the given user ID. 
     * 
     * @param usersId the user name of the user we are checking
     * @return true if the user is found and their ID matches existing data?
     */  
    public UserPreferences getValidUserPreferencesWithId(long id);

    /**
     * Get all of the user's preferences from the database.
     * @return a List of user preference objects
     */
    public List<UserPreferences> getAllUserPreferences();
}