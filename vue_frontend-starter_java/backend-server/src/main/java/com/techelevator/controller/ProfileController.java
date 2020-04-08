package com.techelevator.controller;

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
import com.techelevator.model.JdbcUserPreferencesDao;
import com.techelevator.model.User;
import com.techelevator.model.UserPreferences;

@RestController
public class ProfileController {

	@Autowired
    private AuthProvider auth;
	
    @Autowired
    private JwtTokenHandler tokenHandler;
	
    @Autowired
    private JdbcUserPreferencesDao profileDao;
    
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public UserPreferences getUserPreference() {
    	User currUser = auth.getCurrentUser();
    	System.out.println(currUser.getId());
    	UserPreferences userPreferencesInfo = profileDao.getValidUserPreferencesWithId(currUser.getId());
    	if (userPreferencesInfo != null) {
    		return userPreferencesInfo;
    	}
		return userPreferencesInfo; 
}
    
    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String postUserPreference(@Valid @RequestBody UserPreferences userpref, BindingResult result, HttpServletRequest request) throws UserCreationException {
       System.out.println(request.getHeader("Authorization"));
       User currUser = auth.getCurrentUser();
       System.out.println(auth.getCurrentUser().getId());
       System.out.println(auth.getCurrentUser().getUsername());
       System.out.println(userpref.getFirstName());
       //System.out.println(currUser.getUsername());
       System.out.println(userpref.getAddressOne());
       UserPreferences currUserPreferences = profileDao.saveUserPreferences(currUser.getId(), userpref.getFirstName(), userpref.getLastName(), userpref.getAddressOne(), userpref.getAddressTwo(), userpref.getCity(), userpref.getState(), userpref.getZipCode());
       return "{\"success\":true}";
    }
}
