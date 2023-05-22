package com.sad.g15.webservicegamesrepository.DataAccess.Repository.Entity;

import jakarta.persistence.*;

/*	ATTENTION!
	Our application should not add players in the DB. The table mapped to this entity should be already populated with
	all the players registered to the service, but for testing we need to populate this table ourselves so @SequenceGen
	and @GenValue are specified anyway.
*/

@Entity (name = "Player")
@Table (name = "player")
public class Player {

	@Id
	@SequenceGenerator(
			name = "player_sequence",
			sequenceName = "player_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "player_sequence"
	)
	private int id;
	private String username;

	public Player(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

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
		return "Player{" +
				"id=" + id +
				", username='" + username + '\'' +
				'}';
	}
}
