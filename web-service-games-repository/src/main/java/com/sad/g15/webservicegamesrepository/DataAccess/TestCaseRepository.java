package com.sad.g15.webservicegamesrepository.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.Entity.TestCase;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer>{

}
