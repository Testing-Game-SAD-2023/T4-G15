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

}