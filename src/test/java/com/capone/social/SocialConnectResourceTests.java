/**
 * 
 */
package com.capone.social;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test class to test REST services
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SocialConnectBootStartApp.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocialConnectResourceTests {
	
	/*
	 * Autowired to REST template
	 */
	@Autowired
	private TestRestTemplate restTemplate;
	
	/**
	 * REST Method to test Highest connections
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMaxConnections() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/socialconnect/max", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("id");
		assertThat(entity.getBody()).doesNotContain("error");
	}
	/**
	 * REST Method to test lowest connections
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMinConnections() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/socialconnect/min", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("id");
		assertThat(entity.getBody()).doesNotContain("error");
	}
	
	/**
	 * Method to retrieve connection based on Id
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetConnection() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/socialconnect/4", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("id");
		assertThat(entity.getBody()).contains("1");
	}
	
	/**
	 * Method to test common connections between two users
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllConnections() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/socialconnect/1/2", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("id");
		assertThat(entity.getBody()).contains("name");
		assertThat(entity.getBody()).contains("1");
		assertThat(entity.getBody()).contains("2");
		assertThat(entity.getBody()).doesNotContain("error");
	}
	
	/**
	 * Method to find shortest path between two users
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindPath() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/socialconnect/findpath/4/62", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).doesNotContain("error");
	}
}
