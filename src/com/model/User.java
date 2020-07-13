package com.model;

public class User {
	private String name;
	private Profil profilType;
	
	public User(String name, Profil profilType) {
		this.name = name;
		this.profilType = profilType;
	}

	public enum Profil {
		B("Buyer"), D("Dealer");
		
		private String profilType;
		
		Profil(String profilType) {
			this.profilType = profilType;
		}
		
		public String toString() {
			return this.profilType;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profil getProfilType() {
		return profilType;
	}

	public void setProfilType(Profil profilType) {
		this.profilType = profilType;
	}

	
}
