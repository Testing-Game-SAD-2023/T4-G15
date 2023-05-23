package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "TestCaseRobot")
public class TestCaseRobot extends TestCase {

	public TestCaseRobot(int id, Long totalResult, Long compilingResult, Long coverageMNEResult,
			Long coverageMethodResult, Long coverageWMResult, Long coverageLineResult, Long coverageOutputResult,
			Long coverageBranchResult, Long coverageCBResult, Long coverageExceptionResult, int idTestClass,
			Round round) {
		super(id, totalResult, compilingResult, coverageMNEResult, coverageMethodResult, coverageWMResult,
				coverageLineResult, coverageOutputResult, coverageBranchResult, coverageCBResult,
				coverageExceptionResult, idTestClass);
		this.round = round;
	}

	public TestCaseRobot() {
		super();
	}

	@ManyToOne
	private Round round;

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	@Override
	public String toString() {
		return "TestCaseRobot [round=" + round + "]";
	}
}
