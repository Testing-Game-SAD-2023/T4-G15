package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestClass;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

public interface TestClassRepository extends JpaRepository<TestClass, Integer> {

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "insert into test_class values (1, 'easy', 'path/path')", nativeQuery = true)
    public void populate();

}
