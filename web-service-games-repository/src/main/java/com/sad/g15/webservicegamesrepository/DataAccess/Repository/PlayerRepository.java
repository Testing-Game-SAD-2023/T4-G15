package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
	//Override services here


}
