package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import java.util.List;
import java.util.Optional;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Match;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;

public interface RepositoriesFacade {
	
	Object save(Object entity);
	
	Optional<Object> findById(Class<?> type, int id);
	
	Object getReferenceById(Class<?> entityType, int id);
	
	boolean existsById(Class<?> entityType, int id);
	
	void deleteById(Class<?> entityType, int id);

    void delete(Object entity);
    
    List<Match> findMatchByPlayer(int idPlayer);

	List<Result> readResultsByMatchId(int id);

	List<Result> readResultByPlayerId(int idPlayer);

	List<Round> findByMatchId(int id);

	int deleteTestCase(int idTestCase);

	/**
	 *
	 * @param type type of element to insert into db: 0 if player, 1 if robot
	 */
	void populate(int type);
}
