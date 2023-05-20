package com.sad.g15.webservicegamesrepository.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.Entity.Round;

public interface RoundRepository extends JpaRepository<Round, Integer>{
	
	//Override services here


}
