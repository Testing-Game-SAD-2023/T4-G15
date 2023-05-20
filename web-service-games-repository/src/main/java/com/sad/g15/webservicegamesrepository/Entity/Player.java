package com.sad.g15.webservicegamesrepository.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {

	public Player(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	@Id
	private int id;

	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", username=" + username + "]";
	}

}
