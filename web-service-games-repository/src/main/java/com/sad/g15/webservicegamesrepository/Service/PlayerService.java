package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    public PlayerService(RepositoriesFacade facade) {
        this.facade = facade;
    }
    private final RepositoriesFacade facade;

    public Player create(Player player){
        return facade.getPlayerRepository().save(player);
    }

    public Optional<Player> read(Player player) {
        return facade.getPlayerRepository().findById(player.getId()).stream().findFirst();
    }

    public Player readById(int id){
        return facade.getPlayerRepository().getReferenceById(id);
    }
}
