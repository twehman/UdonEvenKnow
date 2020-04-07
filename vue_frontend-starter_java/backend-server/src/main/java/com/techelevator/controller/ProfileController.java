package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.JdbcUserPreferencesDao;
import com.techelevator.model.User;
import com.techelevator.model.UserPreferences;

@RestController
public class ProfileController {

	@Autowired
    private AuthProvider auth;
	
    @Autowired
    private JdbcUserPreferencesDao profileDao;
    
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public UserPreferences getUserPreference() {
    	User currUser = auth.getCurrentUser();
    	UserPreferences userPreferencesInfo = profileDao.getValidUserPreferencesWithId(currUser.getId());
    	if (userPreferencesInfo != null) {
    		return userPreferencesInfo;
    	}
		return userPreferencesInfo; 
}
    
    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String postUserPreference(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        }
        auth.register(user.getUsername(), user.getPassword(), user.getRole());
        return "{\"success\":true}";
    }
}
