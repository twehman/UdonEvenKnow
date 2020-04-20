package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.favorites.Favorites;
import com.techelevator.favorites.JdbcFavoritesDao;
import com.techelevator.model.JdbcUserPreferencesDao;
import com.techelevator.model.User;
import com.techelevator.model.UserPreferences;
import com.techelevator.zipcode.JdbcZipcodeDao;
import com.techelevator.zipcode.Zipcode;

@RestController
public class ProfileController {

	@Autowired
    private AuthProvider auth;
	
    @Autowired
    private JwtTokenHandler tokenHandler;
	
    @Autowired
    private JdbcUserPreferencesDao profileDao;
    
    @Autowired
    private JdbcZipcodeDao zipDao;
    
    @Autowired
    private JdbcFavoritesDao favoritesDao;
    
    
    
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String getUserPreference() {
    	User currUser = auth.getCurrentUser();
    	System.out.println(currUser.getId());
    	UserPreferences userPreferencesInfo = profileDao.getValidUserPreferencesWithId(currUser.getId());
    	if (profileDao.userHasPreferencesEntry(auth.getCurrentUser().getId()) == true) {
    		return "{\"hasPreferences\":true}";
    	}
    	else {
    		return "{\"hasPreferences\":false}"; 
    	}
}
    
    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String postUserPreference(@Valid @RequestBody UserPreferences userpref, BindingResult result, HttpServletRequest request) throws UserCreationException {
       User currUser = auth.getCurrentUser();
       if (profileDao.userHasPreferencesEntry(auth.getCurrentUser().getId()) == true) {
          UserPreferences currUserPreferences = profileDao.changeUserPreferences(currUser.getId(), userpref.getFirstName(), userpref.getLastName(), userpref.getAddressOne(), userpref.getAddressTwo(), userpref.getCity(), userpref.getState(), userpref.getZipCode());
       }
       else {
    	   UserPreferences currUserPreferences = profileDao.saveUserPreferences(currUser.getId(), userpref.getFirstName(), userpref.getLastName(), userpref.getAddressOne(), userpref.getAddressTwo(), userpref.getCity(), userpref.getState(), userpref.getZipCode());
       }
       return "{\"success\":true}";
    }
    
    @RequestMapping(path = "/restaurants", method=RequestMethod.GET)
    public Zipcode getLatAndLong() {
    	User currUser = auth.getCurrentUser();
    	Zipcode userZipInfo = zipDao.getLatandLongwithZip(profileDao.getValidUserPreferencesWithId(currUser.getId()).getZipCode());
    	return userZipInfo;
    }
    
    @RequestMapping(path = "/favorites", method = RequestMethod.GET)
    public List<Favorites> favoritesList() {
    	User currUser = auth.getCurrentUser();
    	List<Favorites> favsList = favoritesDao.retrieveListsByUserId(currUser.getId());
    	return favsList;
    }
    
    @RequestMapping(path = "/favorites", method=RequestMethod.POST)
    public String saveFavorite(@Valid @RequestBody Favorites newFavorite) {
    	User currUser = auth.getCurrentUser();
    	newFavorite.setUserId(currUser.getId());
    	favoritesDao.createNewFavorite(newFavorite);
    	return "{\"success\":true}";
    }
    
    @RequestMapping(path = "/userDetails", method=RequestMethod.GET)
    public UserPreferences getDetails() {
    	User currUser = auth.getCurrentUser();
    	return profileDao.getValidUserPreferencesWithId(currUser.getId());
    }
}
