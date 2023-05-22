package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.DataAccess.Repository.Entity.Player;

public interface PlayerRepository extends JpaRepository<Player , Integer>{
	
	//Override services here


}
