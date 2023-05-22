package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer>{

	//Override services here
}
