package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoriesFacade {

	public RepositoriesFacade(MatchRepository matchRepository, PlayerRepository playerRepository,
							  RoundRepository roundRepository, TestCaseRepository testCaseRepository, ResultRepository resultRepository) {
		super();
		this.matchRepository = matchRepository;
		this.playerRepository = playerRepository;
		this.roundRepository = roundRepository;
		this.testCaseRepository = testCaseRepository;
		this.resultRepository = resultRepository;
	}

	@Autowired
	private MatchRepository matchRepository;
	private PlayerRepository playerRepository;
	private RoundRepository roundRepository;
	private TestCaseRepository testCaseRepository;
	private ResultRepository resultRepository;

	public MatchRepository getMatchHistoryRepository() {
		return matchRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	public RoundRepository getRoundRepository() {
		return roundRepository;
	}

	public TestCaseRepository getTestCaseRepository() {
		return testCaseRepository;
	}

	public ResultRepository getResultRepository() {
		return resultRepository;
	}


}
