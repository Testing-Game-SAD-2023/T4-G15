package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Robot {
    @Id
    private int id;

    private String difficulty;
}
