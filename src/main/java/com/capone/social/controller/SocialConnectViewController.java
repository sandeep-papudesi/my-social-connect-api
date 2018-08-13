package com.capone.social.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SocialConnectViewController - Default controller redirects to index.html page 
 * to handle user requests
 * The will allow users to retrieve 
 * i> Retrieve users with highest direct connections 
 * ii> Retrieve users with lowest direct connections 
 * iii> Identify total number of direct connections for a specific user 
 * iv> Identify total number of direct connections between users
 * v> Find path between two users
 * 
 * @author Sandeep Papudesi
 * @version 1.0
*/

@Controller
public class SocialConnectViewController {
   
	private String appMode;
    private static final Logger LOGGER = LogManager.getLogger(SocialConnectViewController.class);
    
    /**
     * Retrieves application mode from application.properties
     * 
     * @param environment
     */
    @Autowired
    public SocialConnectViewController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }
    
    /**
     * Default mapping. Redirects user to index.html. 
     *  
     * @param model Populates model values to render 
     * @return
     */
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Sandeep Papudesi");
        model.addAttribute("mode", appMode);
        LOGGER.info("Redirecting to index.html page...");
        return "index";
    }
}