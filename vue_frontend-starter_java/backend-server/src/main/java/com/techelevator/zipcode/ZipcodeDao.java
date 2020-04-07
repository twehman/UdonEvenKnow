package com.techelevator.zipcode;

import java.io.File;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ZipcodeDao {
	
	public Zipcode getLatandLongwithZip(int zipcode);
	
	public Zipcode mapRowSetToZipCode(SqlRowSet results);
	

}
