package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TestClass {
    public TestClass(int id, String levelDifficulty, String path) {
        this.id = id;
        this.levelDifficulty = levelDifficulty;
        this.path = path;
    }

    public TestClass() {

    }

    @Id
    private int id;
    private String levelDifficulty;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelDifficulty() {
        return levelDifficulty;
    }

    public void setLevelDifficulty(String levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "id=" + id +
                ", levelDifficulty='" + levelDifficulty + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
