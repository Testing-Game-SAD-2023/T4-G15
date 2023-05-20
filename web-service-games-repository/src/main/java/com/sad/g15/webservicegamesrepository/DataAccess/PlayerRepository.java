package com.sad.g15.webservicegamesrepository.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.Entity.Player;

public interface PlayerRepository extends JpaRepository<Player , Integer>{

}
