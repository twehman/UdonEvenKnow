package com.techelevator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.JdbcUserPreferencesDao;
import com.techelevator.model.User;
import com.techelevator.model.UserPreferences;
import com.techelevator.zipcode.JdbcZipcodeDao;
import com.techelevator.zipcode.Zipcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AccountController
 */
@RestController
public class AccountController {
    @Autowired
    private AuthProvider auth;

    @Autowired
    private JwtTokenHandler tokenHandler;
    
    @Autowired 
    private JdbcZipcodeDao zipDao;
    
    @Autowired
    private JdbcUserPreferencesDao profileDao;
    
    

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
        if (auth.signIn(user.getUsername(), user.getPassword())) {
            User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(user.getUsername(), currentUser.getRole());
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
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
    
    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String postUserPreference(@Valid @RequestBody UserPreferences userpref, BindingResult result, HttpServletRequest request) throws UserCreationException {
       System.out.println(request.getHeader("Authorization"));
       System.out.println(auth.getCurrentUser().getId());
       System.out.println(auth.getCurrentUser().getUsername());
       //System.out.println(currUser.getUsername());
       System.out.println(userpref.getAddressOne());
       UserPreferences currUserPreferences = profileDao.saveUserPreferences(1, userpref.getFirstName(), userpref.getLastName(), userpref.getAddressOne(), userpref.getAddressTwo(), userpref.getCity(), userpref.getState(), userpref.getZipCode());
       return "{\"success\":true}";
    }
    
    @RequestMapping(path = "/restaurants", method=RequestMethod.GET)
    public Zipcode getLatAndLong() {
    	User currUser = auth.getCurrentUser();
    	Zipcode userZipInfo = zipDao.getLatandLongwithZip(profileDao.getValidUserPreferencesWithId(currUser.getId()).getZipCode());
    	if (userZipInfo != null) {
    		return userZipInfo;
    	}
    	else {
    		Zipcode noCode = new Zipcode();
    		noCode.setZip(99999);
    		return noCode;
    	}
    }

}