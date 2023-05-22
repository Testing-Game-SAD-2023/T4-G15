package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sad.g15.webservicegamesrepository.DataAccess.Repository.Entity.TestCase;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer>{

	//Override services here
}
