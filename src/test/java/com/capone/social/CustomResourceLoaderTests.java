/**
 * 
 */
package com.capone.social;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.capone.social.service.CustomResourceLoader;
/**
 * Unit test class to test reading Persons.txt and Relationships.txt and 
 * storing in Persons object. 
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SocialConnectBootStartApp.class)
public class CustomResourceLoaderTests {
	
	/**
	 * Autowired to CustomResourceLoader class
	 */
	@Autowired
	private CustomResourceLoader customResourceLoader;
	
	/**
	 * Test method to load the data from text files 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadGraph() throws Exception{
		assertTrue(customResourceLoader.retrieveGraphData().size()>0);
		assertNotNull(customResourceLoader.retrieveGraphData());
	}
	
	

}
