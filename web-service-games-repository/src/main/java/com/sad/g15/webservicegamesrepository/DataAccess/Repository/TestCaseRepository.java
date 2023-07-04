package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCase;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCasePlayer;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCaseRobot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer>{

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "delete from test_case_player tcp where tcp.id = ?1", nativeQuery = true)
    public Integer deleteTestCasePlayer(int idTestCase);

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "delete from test_case_robot tcr where tcr.id = ?1", nativeQuery = true)
    public Integer deleteTestCaseRobot(int idTestCase);

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "delete from round_test_cases_player r where r.test_cases_player_id = ?1", nativeQuery = true)
    void deleteTestCasePlayerRef(int idTestCase);

    @Transactional
    @Modifying(clearAutomatically=true)
    @Nullable
    @Query(value = "delete from round_test_cases_robot r where r.test_cases_robot_id = ?1", nativeQuery = true)
    void deleteTestCaseRobotRef(int idTestCase);

    @Query(value = "select * from test_case_player t where t.id = ?1", nativeQuery = true)
    public TestCasePlayer getTestCasePlayerById(int id);

    @Query(value = "select * from test_case_robot t where t.id = ?1", nativeQuery = true)
    public TestCaseRobot getTestCaseRobotById(int id);

    @Query(value = "select id from test_case_robot t where t.tested_class_id = ?1", nativeQuery = true)
    public List<Integer> getTestCasesRobotFromTestClass(int idTestClass);

    @Query(value = "select id from test_case_player t where t.tested_class_id = ?1", nativeQuery = true)
    public List<Integer> getTestCasesPlayerFromTestClass(int idTestClass);
}
