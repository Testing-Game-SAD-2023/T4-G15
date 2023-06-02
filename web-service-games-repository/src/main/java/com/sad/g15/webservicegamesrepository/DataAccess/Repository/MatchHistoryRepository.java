package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory,Integer> {

    //Repository of the Match History Class, every entity class has one (implement other Repos)

}
