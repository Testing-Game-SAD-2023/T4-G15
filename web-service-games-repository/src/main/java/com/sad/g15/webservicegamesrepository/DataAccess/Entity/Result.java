package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Result {

	public Result(int id, Player player, boolean result, MatchHistory match) {
		super();
		this.id = id;
		this.player = player;
		this.result = result;
		this.match = match;
	}
	
	public Result() {
		
	}

	@ManyToOne
	private MatchHistory match;

	@Id
	private int id;

	@OneToOne
	private Player player;

	private boolean result;

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

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public MatchHistory getMatch() {
		return match;
	}

	public void setMatch(MatchHistory match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Result [match=" + match + ", id=" + id + ", player=" + player + ", result=" + result + "]";
	}

}
