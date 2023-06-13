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

    public Player read(Player player) {
    	Optional<Object> playerFound = facade.findById(Player.class, player.getId());
		if(playerFound.isPresent()) {
			return (Player) playerFound.get();
		} else {
			throw new EntityNotFoundException("Player not found");
		}
    }

    public Player readById(int id){
        return (Player) facade.getReferenceById(Player.class, id);
    }
}
