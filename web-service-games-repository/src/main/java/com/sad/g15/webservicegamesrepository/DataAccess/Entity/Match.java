package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


//This Class is also named StoricoPartita in the doc Entity Class Diagram
@Entity(name = "Match")
@Table(name = "matchHistory") // unique constraint go in here
public class Match {

	public Match(int id, String scenario, LocalDateTime startDate, LocalDateTime endDate, List<Round> rounds,
				 List<Result> results) {
		super();
		this.id = id;
		this.scenario = scenario;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rounds = rounds;
		this.results = results;
	}

	@Id
	@SequenceGenerator(name = "matchHistory_sequence", sequenceName = "matchHistory_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matchHistory_sequence")
	@Column(name = "id", updatable = false)
	private int id;
	private String scenario;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Round> rounds = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Result> results = new ArrayList<>(); //Bisogna avere un riferimento ai risultati

	public Match() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	
	public void setRound(Round round) {
		this.rounds.add(round);
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	
	public void setResult(Result result) {
		this.results.add(result);
	}

	@Override
	public String toString() {
		return "Match{" +
				"id=" + id +
				", scenario='" + scenario + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", rounds=" + rounds +
				", results=" + results +
				'}';
	}
}