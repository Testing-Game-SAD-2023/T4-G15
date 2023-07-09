package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Integer>{
	
	//Override services here

    @Query(value = "select id, robot_id, start_date, end_date from match_history_rounds m inner join round r ON r.id = m.rounds_id where m.match_history_id = ?1"
            , nativeQuery = true)
    public List<Round> findByMatchId(int id);

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "delete from match_history_rounds mhr where mhr.rounds_id = ?1", nativeQuery = true)
    void deleteAssociation(int id);
}
