package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


//This Class is also named StoricoPartita in the doc Entity Class Diagram
@Entity (name = "MatchHistory")
@Table (name = "matchHistory")                 //unique constraint go in here
public class MatchHistory {

	@Id
    @SequenceGenerator(
            name = "matchHistory_sequence",
            sequenceName = "matchHistory_sequence",
            allocationSize = 1
    )
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "matchHistory_sequence"
    )
    @Column(name = "id", updatable = false)
    private int id;
    private String scenario;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean result;

    @OneToMany(orphanRemoval = true)
    private List<Round> rounds;

    public MatchHistory(int id, String scenario, LocalDateTime startDate, LocalDateTime endDate, Boolean result, List<Round> rounds) {
        this.id = id;
        this.scenario = scenario;
        this.startDate = startDate;
        this.endDate = endDate;
        this.result = result;
        this.rounds = new ArrayList<Round>();
    }

    public MatchHistory() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScenario() {
        return scenario;
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

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "MatchHistory{" +
                "id=" + id +
                ", scenario='" + scenario + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", result=" + result +
                ", rounds=" + rounds +
                '}';
    }
}