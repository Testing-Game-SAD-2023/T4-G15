package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import org.springframework.stereotype.Repository;

@Repository
public class RepositoriesFacade {

	public RepositoriesFacade(MatchHistoryRepository matchHistoryRepository, PlayerRepository playerRepository,
			RoundRepository roundRepository, TestCaseRepository testCaseRepository, ResultRepository resultRepository) {
		super();
		this.matchHistoryRepository = matchHistoryRepository;
		this.playerRepository = playerRepository;
		this.roundRepository = roundRepository;
		this.testCaseRepository = testCaseRepository;
		this.resultRepository = resultRepository;
	}

	private MatchHistoryRepository matchHistoryRepository;

	private PlayerRepository playerRepository;

	private RoundRepository roundRepository;

	private TestCaseRepository testCaseRepository;

	private ResultRepository resultRepository;

	// Insert Methods Here

	public MatchHistoryRepository getMatchHistoryRepository() {
		return matchHistoryRepository;
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


	// Override services here

}
