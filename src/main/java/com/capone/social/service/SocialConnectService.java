package com.capone.social.service;

import java.util.List;

import com.capone.social.model.Person;
/**
 * Interface class to implement features
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 *
 */
interface SocialConnectService {

	public String findPath(Integer src, Integer dest);
	public List<Person> getAllConnections(Integer fromId, Integer toId);
	public List<Person> getConnection(Integer userId);
	public List<Person> getMaxConnections();
	public List<Person> getMinConnections();

}
