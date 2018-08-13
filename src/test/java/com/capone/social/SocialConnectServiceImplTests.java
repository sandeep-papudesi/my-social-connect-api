package com.capone.social;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.capone.social.model.Person;
import com.capone.social.service.SocialConnectServiceImpl;

/**
 * Unit test class to test Business logic 
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SocialConnectBootStartApp.class)
public class SocialConnectServiceImplTests {
	
	/**
	 * Autowired to SocialConnectServiceImpl
	 */
	@Autowired
	private SocialConnectServiceImpl socialConnectServiceImpl;
	
	/**
	 * Service method to find shortest path between two users
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindPath() throws Exception {
		String path = socialConnectServiceImpl.findPath(4, 62);
		assertNotNull("Path between two nodes:",path);
	}
	
	/**
	 * Service method to get users with maximum connections 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMaxConnections() throws Exception {
		List<Person> maxConList = socialConnectServiceImpl.getMaxConnections();
		assertNotNull("Users with highest connections:",maxConList);
		assertTrue(maxConList.size()>0);
	}
	
	/**
	 * Service method to get users with lowest connections
	 *  
	 * @throws Exception
	 */
	@Test
	public void testGetMinConnections() throws Exception {
		List<Person> minConList = socialConnectServiceImpl.getMinConnections();
		assertNotNull("Users with lowest connections:",minConList);
		assertTrue(minConList.size()>0);
	}
	
	/**
	 * Service method to  get list of connections based on User ID
	 *  
	 * @throws Exception
	 */
	@Test
	public void testGetConnection() throws Exception {
		List<Person> conList = socialConnectServiceImpl.getConnection(4);
		assertNotNull("Connection Details for the user:",conList);
		assertTrue(conList.size()>0);
	}
	
	/**
	 * Service method to retrieve common connections between users
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAllConnections() throws Exception {
		List<Person> userConList = socialConnectServiceImpl.getAllConnections(4, 62);
		assertNotNull("Common connections between users:",userConList);
		assertTrue(userConList.size()>0);
	}
}
