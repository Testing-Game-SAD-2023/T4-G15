package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.*;

@Entity
public class Result {

	public Result(int id, long scoreMatch, Player player, String outcome) {
		this.id = id;
		this.scoreMatch = scoreMatch;
		this.player = player;
		this.outcome = outcome;
	}

	public Result() {

	}

	@Id
	@SequenceGenerator(name = "result_sequence", sequenceName = "result_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_sequence")
	@Column(name = "id", updatable = false)
	private int id;

	private long scoreMatch;

	@OneToOne
	private Player player;

	private String outcome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String result) {
		this.outcome = result;
	}

	public long getScoreMatch() {
		return scoreMatch;
	}

	public void setScoreMatch(long scoreMatch) {
		this.scoreMatch = scoreMatch;
	}

	@Override
	public String toString() {
		return "Result{" +
				"id=" + id +
				", scoreMatch=" + scoreMatch +
				", player=" + player +
				", outcome='" + outcome + '\'' +
				'}';
	}
}
