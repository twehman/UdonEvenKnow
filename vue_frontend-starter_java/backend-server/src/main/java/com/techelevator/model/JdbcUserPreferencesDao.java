package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JdbcUserPreferencesDao implements UserPreferencesDao {

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
    public UserPreferences getValidUserPreferencesWithId(long usersId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserPreferences> getAllUserPreferences() {
        // TODO Auto-generated method stub
        return null;
    }


    private UserPreferences mapRowToUser(SqlRowSet results) {
        UserPreferences preferences = new UserPreferences();
            preferences.setId(results.getLong("id"));
            preferences.setFirstName(results.getString("first_name"));
            preferences.setLastName(results.getString("last_name"));
            preferences.setAddressOne(addressOne);
            Username = Convert.ToString(reader["username"]),
            FirstName = Convert.ToString(reader["firstname"]),
            LastName = Convert.ToString(reader["lastname"]),
            AddressOne = Convert.ToString(reader["address1"]),
            AddressTwo = Convert.ToString(reader["address2"]),
            City = Convert.ToString(reader["city"]),
            State = Convert.ToString(reader["state"]),
            PostalCode = Convert.ToString(reader["postalcode"])

            

        }

   





}