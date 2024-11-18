package org.example.gameofthronesbd.model;

import java.io.Serializable;

public class CharactersItem implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String title;
	private String family;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getFullName(){
		return fullName;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getFamily(){
		return family;
	}

	@Override
	public String toString() {
		return id +  " " + firstName + " " + lastName + " " + fullName + " " + title + " " + family;
	}
}