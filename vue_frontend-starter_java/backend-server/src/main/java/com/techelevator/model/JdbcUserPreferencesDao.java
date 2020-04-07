package com.techelevator.model;

import java.util.List;

public class JdbcUserPreferencesDao implements UserPreferencesDao {

    @Override
    public UserPreferences saveUserPreferences(long usersId, int id, String firstName, String lastName,
            String addressOne, String addressTwo, String city, String State, int zipCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void changeUserPreferences(String firstName, String lastName, String addressOne, String addressTwo,
            String city, String State, int zipCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserPreferences getValidUserPreferencesWithId(long usersId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserPreferences> getAllUserPreferences() {
        // TODO Auto-generated method stub
        return null;
    }

}