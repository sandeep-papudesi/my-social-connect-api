/**
 * 
 */
package com.capone.social.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Model object to populate user information
 * 
 * @author Sandeep Papudesi
 * @version 1.0
 */
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private List<Integer> connections = new ArrayList<>();

	public Person(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Person(Integer id, String name, List<Integer> connections) {
		this.id = id;
		this.name = name;
		this.connections = connections;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getConnections() {
		return connections;
	}

	public void setConnections(List<Integer> connections) {
		this.connections = connections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(id).append(", name=").append(name).append(", connections=")
				.append(connections).append("]");
		return builder.toString();
	}
}
