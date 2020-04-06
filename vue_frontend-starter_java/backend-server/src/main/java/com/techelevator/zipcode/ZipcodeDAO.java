package com.techelevator.zipcode;

import java.io.File;

public interface ZipcodeDAO {
	
	public Zipcode getLatandLongwithZip(int zipcode);
	
	public void generateZipcodeDatabase(File zipcodeDataset);
	
	public void mapLinetoZipcode(String[] splitFileLine);

}
