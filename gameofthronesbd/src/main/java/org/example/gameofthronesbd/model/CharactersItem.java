package org.example.gameofthronesbd.model;

import java.io.Serializable;

public class CharactersItem implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String title;
	private String family;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	@Override
	public String toString() {
		return id +  " " + firstName + " " + lastName + " " + fullName + " " + title + " " + family;
	}
}