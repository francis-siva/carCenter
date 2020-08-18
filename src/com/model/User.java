package com.model;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4970580436194594127L;
	
	private int id;
	private String name;
	/* THINK LATER about EnumSet entry field instead of the following: */
	private final String[] PROFILE = {"Buyer", "Dealer"};
	private String profileType = "Buyer";
	/* THINK LATER */
	
	private String email;
	private String password;
	
	public User() {}
	public User(int id, String name, String profileType, String email, String password) {
		this.id = id;
		this.name = name;
		this.setProfileType(profileType);
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileType() {
		return this.profileType;
	}

	/**
	 * profileType Input setting is depending on final String[] PROFILE.<br />
	 * If profileType parameter's value is in PROFILE array,<br />
	 * then User object will take the new profileType's value.
	 * @param profileType
	 */
	public void setProfileType(String profileType) {
		for(String profile : this.PROFILE) {
			if(profileType.equals(profile)) {
				this.profileType = profileType;
				break;
			}
		}
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "User n°: " + this.id + "\n name: " + this.name + "\n profileType: " + this.profileType + "\n email: " + this.email;
	}
}
