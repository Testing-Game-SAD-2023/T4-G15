package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "TestCasePlayer")
public class TestCasePlayer extends TestCase {

	public TestCasePlayer(int id, Long totalResult, Long compilingResult, Long coverageMNEResult,
			Long coverageMethodResult, Long coverageWMResult, Long coverageLineResult, Long coverageOutputResult,
			Long coverageBranchResult, Long coverageCBResult, Long coverageExceptionResult, int idTestClass,
			Player player) {
		super(id, totalResult, compilingResult, coverageMNEResult, coverageMethodResult, coverageWMResult,
				coverageLineResult, coverageOutputResult, coverageBranchResult, coverageCBResult,
				coverageExceptionResult, idTestClass);
		this.player = player;
	}

	public TestCasePlayer() {

	}

	@ManyToOne
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "TestCasePlayer{" + "player=" + player + '}';
	}
}
