package com.capone.social.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capone.social.model.Person;
import com.capone.social.service.SocialConnectServiceImpl;

/**
 * SocialConnectResource - This is a REST controller to handle user requests
 * This controller will allow users to
 * 
 * i. Retrieve users with highest direct connections ii. Retrieve users with
 * lowest direct connections iii. Identify total number of direct connections
 * for a specific user iv. Identify total number of direct connections between
 * users v. Find path between two users
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 * 
 */
@RestController
@RequestMapping(value = "/socialconnect", method = RequestMethod.GET)
public class SocialConnectResource {

	/**
	 * Autowired to service class
	 */
	@Autowired
	private SocialConnectServiceImpl socialConnectServiceImpl;

	/**
	 * Method to retrieve highest connections. <b>Request Mapping URL</b> -
	 * <u>http://{server-name}:{port-number}/socialconnect/max</u>
	 * 
	 * @return - returns a list of Person objects @
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/max")
	public List<Person> getMaxConnections() {
		return socialConnectServiceImpl.getMaxConnections();
	}

	/**
	 * Method to retrieve lowest connections. <b>Request Mapping URL</b> -
	 * <i>http://{server-name}:{port-number}/socialconnect/min</i>
	 * 
	 * @return - returns a list of Person objects
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/min")
	public List<Person> getMinConnections() {
		return socialConnectServiceImpl.getMinConnections();
	}

	/**
	 * Method to retrieve connection based on User ID. <b>Request Mapping URL</b> -
	 * <u>http://{server-name}:{port-number}/socialconnect/{id}</u>
	 * 
	 * @param id - User Id to retrieve connectionsS
	 * @return - returns a list of Person object
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public List<Person> getConnection(@PathVariable Integer id) {
		return socialConnectServiceImpl.getConnection(id);
	}

	/**
	 * Method to find path between two users <b>Request Mapping URL</b> -
	 * <u>http://{server-name}:{port-number}/socialconnect/{source}/{destination}</u>
	 * 
	 * @param source - Start node of the User
	 * @param destination   - End node of the User
	 * @return - returns a list of Person object
	 */
	@RequestMapping(method = RequestMethod.GET, value = "findpath/{source}/{destination}")
	public List<String> findPath(@PathVariable Integer source, @PathVariable Integer destination) {
		List<String> pathDataList = new ArrayList<>();
		pathDataList.add(socialConnectServiceImpl.findPath(source, destination));
		return pathDataList;
	}

	/**
	 * Method to find all common connections between two users <b>Request Mapping
	 * URL</b> - <u>http://{server-name}:{port-number}/{fromId}/{toId}</u>
	 * 
	 * @param fromId - Start node of the User
	 * @param toId   - End node of the User
	 * @return - returns a list of Person object
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{fromId}/{toId}")
	public List<Person> getAllConnections(@PathVariable Integer fromId, @PathVariable Integer toId) {
		return socialConnectServiceImpl.getAllConnections(fromId, toId);
	}
}
