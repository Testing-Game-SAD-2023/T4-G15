package com.sad.g15.webservicegamesrepository.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistance.Entity;
import javax.persistance.Id;
import javax.persistance.GeneratedValue;
import javax.persistance.GeneratedType;




//This Class is also named StoricoPartita
@Entity
public class MatchHistory {

	@Id
	@GeneratedValue
    private String id;
    private String scenario;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean result;

    public MatchHistory(String id, String scenario, LocalDateTime startDate, LocalDateTime endDate, Boolean result) {
        this.id = id;
        this.scenario = scenario;
        this.startdate = startDate;
        this.enddate = endDate;
        this.result = result;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public LocalDateTime getStartDate() {
        return startdate;
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

    @Override
    public String toString() {
        return "MatchHistory{" +
                "id='" + id + '\'' +
                ", scenario='" + scenario + '\'' +
                ", startdate=" + startDate +
                ", enddate=" + endDate +
                ", result=" + result +
                '}';
    }
}