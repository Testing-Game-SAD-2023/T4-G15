package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.*;

@Entity
public class Result {

	public Result(int id, Player player, String result) {
		super();
		this.id = id;
		this.player = player;
		this.result = result;
	}

	public Result() {

	}

	@Id
	@SequenceGenerator(name = "result_sequence", sequenceName = "result_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_sequence")
	@Column(name = "id", updatable = false)
	private int id;



	@OneToOne
	private Player player;

	private String result;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", player=" + player + ", result=" + result + "]";
	}
}
