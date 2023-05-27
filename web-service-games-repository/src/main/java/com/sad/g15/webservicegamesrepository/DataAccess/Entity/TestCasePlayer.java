package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "TestCasePlayer")
public class TestCasePlayer extends TestCase {

	public TestCasePlayer(int id, Long totalResult, Long compilingResult, Long coverageMNEResult,
			Long coverageMethodResult, Long coverageWMResult, Long coverageLineResult, Long coverageOutputResult,
			Long coverageBranchResult, Long coverageCBResult, Long coverageExceptionResult, int idTestClass,
			Player player, Round round) {
		super(id, totalResult, compilingResult, coverageMNEResult, coverageMethodResult, coverageWMResult,
				coverageLineResult, coverageOutputResult, coverageBranchResult, coverageCBResult,
				coverageExceptionResult, idTestClass);
		this.player = player;
		this.round = round;
	}

	public TestCasePlayer() {

	}

	@ManyToOne
	private Player player;

	@ManyToOne
	private Round round;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	@Override
	public String toString() {
		return "TestCasePlayer{" +
				"player=" + player +
				", round=" + round +
				'}';
	}
}
