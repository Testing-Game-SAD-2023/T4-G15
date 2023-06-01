package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;



@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "match")

public class Result {

	public Result(int id, boolean result,Player player, MatchHistory match) {
		super();
		this.id = id;
		this.result = result;
		this.player = player;
		this.match = match;
	}

	public Result() {

	}

	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne
	private MatchHistory match;

	@Id
	@SequenceGenerator(name = "result_sequence", sequenceName = "result_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_sequence")
	@Column(name = "id", updatable = false)
	private int id;

	@JsonIgnore
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