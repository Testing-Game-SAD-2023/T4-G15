package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Integer>{
	
	//Override services here

    @Query(value = "select id,result,robot_id from match_history_rounds m inner join round r ON r.id = m.rounds_id where m.match_history_id = ?1"
            , nativeQuery = true)
    public List<Round> findByMatchId(int id);

}
