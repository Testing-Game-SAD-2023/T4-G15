package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Integer> {

    //Repository of the Match History Class, every entity class has one (implement other Repos)

    @Query(value = "select * from result r where r.player_id = ?1", nativeQuery = true)
    public List<MatchHistory> findMatchByPlayer(int player_id);

    @Query(value = "select * from result r where r.match_id = ?1", nativeQuery = true)
    public  List<Result> readResultsByMatchId(int match_id);

    @Query(value = "SELECT r.id, r.result, r.player_id\n" +
            "FROM result r\n" +
            "WHERE r.player_id = ? \n" +
            "  AND r.id IN (\n" +
            "    SELECT mhr.results_id\n" +
            "    FROM match_history_results mhr\n" +
            "    WHERE mhr.match_history_id IN (\n" +
            "      SELECT mh.id\n" +
            "      FROM match_history mh\n" +
            "      WHERE mh.end_date IS NOT NULL\n" +
            "    )\n" +
            "  )", nativeQuery = true)
    public List<Result>  readResultByPlayerId(int player_id);

}
