package com.sad.g15.webservicegamesrepository.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sad.g15.webservicegamesrepository.Entity.MatchHistory;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory,String> {

    //Repository of the Match History Class, every entity class has one (implement other Repos)

}
