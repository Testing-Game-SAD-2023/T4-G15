package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Robot {
    @Id
    private int id;

    private String difficulty;

    public Robot(int id, String difficulty) {
        this.id = id;
        this.difficulty = difficulty;
    }

    public Robot() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
