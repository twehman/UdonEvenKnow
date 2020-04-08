package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;
@Component
public class JdbcUserPreferencesDao implements UserPreferencesDao {
	
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcUserPreferencesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserPreferences saveUserPreferences(long id, String firstName, String lastName,
    		String addressOne, String addressTwo, String city, String state, int zipCode) {
    	UserPreferences userPref = new UserPreferences();
    	userPref.setId(id);
    	userPref.setFirstName(firstName);
    	userPref.setLastName(lastName);
    	userPref.setAddressOne(addressOne);
    	userPref.setAddressTwo(addressTwo);
    	userPref.setCity(city);
    	userPref.setState(state);
    	userPref.setZipCode(zipCode);
        String sqlQuery = "INSERT INTO users_data (id, first_name, last_name, address_one, address_two, city, state, zip_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery, id, firstName, lastName, addressOne, addressTwo, city, state, zipCode);
        return userPref;
    }

    @Override
    public UserPreferences changeUserPreferences(long id, String firstName, String lastName, String addressOne, String addressTwo,
            String city, String state, int zipCode) {
    	UserPreferences userPref = new UserPreferences();
    	userPref.setId(id);
    	userPref.setFirstName(firstName);
    	userPref.setLastName(lastName);
    	userPref.setAddressOne(addressOne);
    	userPref.setAddressTwo(addressTwo);
    	userPref.setCity(city);
    	userPref.setState(state);
    	userPref.setZipCode(zipCode);
        String sqlQuery = "UPDATE user_data (first_name, last_name, address_one, address_two, city, state, zip_code) VALUES( ?, ?, ?, ?, ?, ?, ?) WHERE id = ?";
        jdbcTemplate.update(sqlQuery, firstName, lastName, addressOne, addressTwo, city, state, zipCode, id);
        return userPref;
    }

    @Override
    public UserPreferences getValidUserPreferencesWithId(long id) {
        String sqlSearchForUserPreferences = "SELECT * FROM users_data WHERE id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUserPreferences, id);
        UserPreferences userPref = new UserPreferences();
        
        if (results.next()) {
            userPref = mapRowToUserPreferences(results);
        }
        return userPref;
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

	@Override
	public boolean userHasPreferencesEntry(long id) {
		String sqlQuery = "SELECT COUNT(*) from users_data where id=?";
		Integer count =  jdbcTemplate.queryForObject(sqlQuery, Integer.class, id);
		return (count != null && count > 0);
	}


   





}