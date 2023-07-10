package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    public PlayerService(RepositoriesFacade facade) {
        this.facade = facade;
    }
    private final RepositoriesFacade facade;

    public Player create(Player player){
        return (Player) facade.save(player);
    }

    public Player readById(int id){
        Player player = (Player) facade.findById(Player.class, id).orElse(null);
        if(player!=null) return player;
        else throw new NullPointerException();
    }

    public void populate(){
        facade.populate(0);
    }
}
