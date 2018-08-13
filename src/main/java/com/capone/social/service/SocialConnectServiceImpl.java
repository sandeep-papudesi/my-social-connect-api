package com.capone.social.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.social.model.Person;
/**
 * SocialConnectServiceImpl - Implements business logic to retrieve data 
 * from map and process below scenarios
 *
 *   i> Retrieve users with highest direct connections 
 *  ii> Retrieve users with lowest direct connections 
 * iii> Identify total number of direct connections for a specific user 
 *  iv> Identify total number of direct connections between users
 *   v> Find path between two users
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 * 
 */
@Service
public class SocialConnectServiceImpl implements SocialConnectService{
	
	private static final Logger LOGGER = LogManager.getLogger(SocialConnectServiceImpl.class);
	
	/**
	 * Autowired to Resourceloader class
	 */
	@Autowired
	private CustomResourceLoader customResourceLoader;
	
	/**
	 * getMaxConnections() - Method to retrieve users with highest connections. 
	 * 
	 * @return - returns a list of Person objects
	 * @
	 */
	public List<Person> getMaxConnections() {
		LOGGER.info("Method to retrieve highest connections - Start");
		Map<Integer, Person> graphData = customResourceLoader.retrieveGraphData();
		List<Person> userInfoList = new ArrayList<>();
		Integer maxCons = 0; 

		for (Map.Entry<Integer, Person> graphEntry : graphData.entrySet()) {
			List<Integer> connections = graphEntry.getValue().getConnections();
			Integer userId = graphEntry.getValue().getId();
			String userName = graphEntry.getValue().getName();
			Integer totalCons = connections.size();
			
			/*
			 * Retrieve total number of connections for each user, 
			 * clear the list and create Person object with highest connections
			 */
			if (totalCons > maxCons) {
				maxCons = totalCons;
				userInfoList.clear();
				Person personInfo = new Person(userId, userName, connections);
				userInfoList.add(personInfo);
			} else if (totalCons.equals(maxCons)) {
				maxCons = totalCons;
				Person personInfo = new Person(userId, userName, connections);
				userInfoList.add(personInfo);
			}
		}
		return userInfoList;
	}
	
	/**
	 * getMinConnections() - Method to retrieve users lowest connections. 
	 * 
	 * @return - returns a list of Person objects
	 */
	public List<Person> getMinConnections() {
		LOGGER.info("Method to retrieve lowest connections - Start");
		Map<Integer, Person> graphData = customResourceLoader.retrieveGraphData();
		List<Person> userInfoList = new ArrayList<>();
		Integer minCons = 0;

		for (Map.Entry<Integer, Person> graphEntry : graphData.entrySet()) {
			List<Integer> connections = graphEntry.getValue().getConnections();
			Integer userId = graphEntry.getValue().getId();
			String userName = graphEntry.getValue().getName();
			Integer totalCons = connections.size();
			//Set to total Cons to execute for first time
			if (minCons == 0) {
				minCons = totalCons;
			}
			
			/*
			 * Retrieve total number of connections for each user, 
			 * clear the list and create Person object with lowest connections
			 */
			if (totalCons < minCons) {
				minCons = totalCons;
				userInfoList.clear();
				Person personInfo = new Person(userId, userName, connections);
				userInfoList.add(personInfo);
			} else if (totalCons.equals(minCons)) {
				minCons = totalCons;
				Person personInfo = new Person(userId, userName, connections);
				userInfoList.add(personInfo);
			} 
		}
		return userInfoList;
	}
	
	
	
	/**
	 * getConnection(id) - Method to retrieve total connections based on User ID. 
	 * 
	 * @return - returns a list of Person object
	 */
	public List<Person> getConnection(Integer userId) {
		LOGGER.info("Retrieving connections based on UserID - Start");
		List<Person> userInfoList = new ArrayList<>();
		Map<Integer, Person> graphData = customResourceLoader.retrieveGraphData();
		userInfoList.add(graphData.get(userId));
		return userInfoList;
	}
	
	/**
	 * getAllConnections(fromId,toId) - Method to find all common connections between two users
	 * 
	 * @param fromId - Start node of the User 
	 * @param toId - End node of the User
	 * @return - returns a list of Person object
	 */
	public List<Person> getAllConnections(Integer fromId, Integer toId) {
		LOGGER.info("Method to retrieve connections between users - Start");
		Map<Integer, Person> graphData = customResourceLoader.retrieveGraphData();
		Person fromUser = graphData.get(fromId);
		Person toUser = graphData.get(toId);
		List<Integer> fromIdConnections = fromUser.getConnections();
		List<Integer> toIdConnections = toUser.getConnections();
		
		//Find common connections
		fromIdConnections.retainAll(toIdConnections);
		List<Person> userInfoList = new ArrayList<>();
		Person fromUserInfo = new Person(fromUser.getId(), fromUser.getName(), fromIdConnections);
		Person toUserInfo = new Person(toUser.getId(), toUser.getName(), fromIdConnections);
		userInfoList.add(fromUserInfo);
		userInfoList.add(toUserInfo);
		return userInfoList;
	}
	
	/**
	 * findPath() - Method to find path between two users to connect
	 * 
	 * @param source - Start node of the User 
	 * @param destination - End node of the User
	 * @return - returns a list of Person object
	 */
	public String findPath(Integer src, Integer dest) {
		Map<Integer, Person> graphData = customResourceLoader.retrieveGraphData();
		boolean[] visited = new boolean[graphData.size() + 1];
		Integer[] parent = new Integer[graphData.size() + 1];
		Queue<Integer> queue = new ArrayDeque<>();

		visited[src] = true;
		queue.offer(src);
		while (!queue.isEmpty()) {
			Integer id = queue.poll();
			if (id.equals(dest)) {
				return getPath(dest, parent);
			}

			List<Integer> connections = graphData.get(id).getConnections();
			for (Integer connection : connections) {
				if (!visited[connection]) {
					visited[connection] = true;
					queue.offer(connection);
					parent[connection] = id;
				}
			}//end for
		}//end while
		return null;
	}
	

	/**
	 * Method to rearrange the elements into stack to display
	 * in correct order
	 * 
	 * @param dest
	 * @param parent
	 * @return
	 */
	private String getPath(Integer dest, Integer[] parent) {
		StringBuilder path = new StringBuilder();
		Deque<Integer> stack = new ArrayDeque<>();
		Integer tempNode = dest;
		
		while (tempNode != null) {
			stack.push(tempNode);
			tempNode = parent[tempNode];
		}

		while (!stack.isEmpty()) {
			path.append(" ==> "+stack.pop() );
		}
		return path.toString();
	}
}
