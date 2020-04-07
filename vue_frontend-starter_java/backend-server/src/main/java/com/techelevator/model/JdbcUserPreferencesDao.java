package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JdbcUserPreferencesDao implements UserPreferencesDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public UserPreferences saveUserPreferences(long id, String firstName, String lastName,
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
    public UserPreferences getValidUserPreferencesWithId(long id) {
        String sqlSearchForUserPreferences = "SELECT * FROM users_data WHERE id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUserPreferences, id);
        if (results.next()) {
            
        }
        return null;
    }

    @Override
    public List<UserPreferences> getAllUserPreferences() {
        List<UserPreferences> allUserPreferences = new ArrayList<UserPreferences>();
        String sqlSelectAllUserPreferences = "SELECT * FROM users_data";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUserPreferences);

        while(results.next()) {
            UserPreferences preferences = mapRowToUserPreferences(results);
            allUserPreferences.add(preferences);
        }
        return allUserPreferences;
    }


    private UserPreferences mapRowToUserPreferences(SqlRowSet results) {
        UserPreferences preferences = new UserPreferences();
            preferences.setId(results.getLong("id"));
            preferences.setFirstName(results.getString("first_name"));
            preferences.setLastName(results.getString("last_name"));
            preferences.setAddressOne(results.getString("address_one"));
            preferences.setAddressTwo(results.getString("address_two"));
            preferences.setCity(results.getString("city"));
            preferences.setState(results.getString("state"));
            preferences.setZipCode(results.getInt("zip_code"));
            return preferences;
            
            

        }

   





}