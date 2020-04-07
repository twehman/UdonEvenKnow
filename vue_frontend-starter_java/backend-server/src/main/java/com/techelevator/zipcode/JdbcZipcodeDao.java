package com.techelevator.zipcode;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.authentication.PasswordHasher;

public class JdbcZipcodeDao implements ZipcodeDao {
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    public JdbcZipcodeDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public Zipcode getLatandLongwithZip(int zipcode) {
		Zipcode userZipcode = new Zipcode();
		String sqlQuery = "SELECT * FROM zip_codes WHERE zip = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, zipcode);
		
		while(results.next()) {
			userZipcode = mapRowSetToZipCode(results);
		}
		
		return userZipcode;
	}

	@Override
	public Zipcode mapRowSetToZipCode(SqlRowSet results) {
		Zipcode returnedZipcode = new Zipcode();
		returnedZipcode.setLatitude(results.getDouble("latitude"));
		returnedZipcode.setLongitude(results.getDouble("longitude"));
		returnedZipcode.setZip(results.getInt("zip"));
		return null;
	}

}
