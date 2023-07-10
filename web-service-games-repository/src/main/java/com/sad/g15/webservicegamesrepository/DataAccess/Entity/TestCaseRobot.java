package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "TestCaseRobot")
public class TestCaseRobot extends TestCase {

	public TestCaseRobot() {
		super();
	}
	
}
