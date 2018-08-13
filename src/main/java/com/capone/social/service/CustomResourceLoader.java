package com.capone.social.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.capone.social.model.Person;

/**
 * CustomResourceLoader - This service class reads Persons.txt,
 * Relationships.txt and associates Id, Name and List of connections in Person
 * object.
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 * 
 */
@Component
public class CustomResourceLoader {

	private Map<Integer, Person> graphData = new HashMap<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomResourceLoader.class.getName());
	private static final String PERSONS_FILE = "classpath:/data/Persons.txt";
	private static final String RELATIONSHIPS_FILE = "classpath:/data/Relationships.txt";

	/**
	 * Autowired to ResourceLoader to retrieve file path dynamically
	 */
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * loadGraph() - Method to read Persons.txt and Relationships.txt file and store
	 * it in a Map object with Key as Id and value as Person object (Id, Name and
	 * List of connections)
	 */
	public void loadGraph() {
		LOGGER.info("Entering loadGraph");

		File personsFile = null;
		File relationShipFile = null;
		try {
			personsFile = new File(resourceLoader.getResource(PERSONS_FILE).getURI());
			relationShipFile = new File(resourceLoader.getResource(RELATIONSHIPS_FILE).getURI());
		} catch (IOException ioExp) {
			LOGGER.error("File IO Error ::", ioExp);
		}

		/**
		 * Read Persons.txt, create Person object and store it in the Map
		 */
		try (Scanner scanner = new Scanner(personsFile)) {
			while (scanner.hasNextLine()) {
				Person p = new Person(scanner.nextInt(), scanner.next());
				graphData.put(p.getId(), p);
			}
		} catch (IOException e) {
			LOGGER.error("Error while reading file.", e);
		}

		/**
		 * Read Relationship file, tokenize the Id and connections and update Persons
		 * object in the Map
		 */
		try (Scanner scanner = new Scanner(relationShipFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ":");
				while (tokenizer.hasMoreTokens()) {
					String idStr = tokenizer.nextToken().trim();
					String[] relations = tokenizer.nextToken().split(",");

					Integer userId = Integer.valueOf(idStr);
					Person p = graphData.get(userId);
					List<Integer> connections = p.getConnections();
					// Remove self referenced connections
					connections.remove(userId);
					for (String relation : relations) {
						Integer relationId = Integer.valueOf(relation.trim());
						// Do not add any self referenced connections
						if (!userId.equals(relationId)) {
							connections.add(relationId);
						}

					}
					String personObj = p.toString();
					LOGGER.info(personObj);
				}
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("Error while reading file.", e);
		}

	}

	/**
	 * Method to retrieve data to access from other classes
	 * 
	 * @return - Map object with key as Id and Value as Person object
	 */
	public Map<Integer, Person> retrieveGraphData() {
		loadGraph();
		return this.graphData;
	}

	/**
	 * Returns size of the graphData
	 * 
	 * @return - returns size of the map.
	 */
	public int size() {
		return this.graphData.size();
	}
}
