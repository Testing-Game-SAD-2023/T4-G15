package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "insert into player values (1,'Antonio'),(2,'Matteo'),(3,'Michele')", nativeQuery = true)
    public void populate();
}
