package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class PlayerCollection {


    @Id
    private Long id;

    @OneToMany(orphanRemoval = true)
    private List<Player> players;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
