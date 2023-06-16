package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Robot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

public interface RobotRepository extends JpaRepository<Robot, Integer> {

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "insert into robot values (1, 'easy'),(2, 'normal'),(3,'hard')", nativeQuery = true)
    public void populate();
}
