package com.sad.g15.webservicegamesrepository.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchHistory {

    private String id;
    private String scenario;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private Boolean result;

    public MatchHistory(String id) {
        this.id = id;
        this.scenario = scenario;
        this.startdate = startdate;
        this.enddate = enddate;
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

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public LocalDateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
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
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", result=" + result +
                '}';
    }
}