package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoriesFacade  {

	public RepositoriesFacade(MatchHistoryRepository matchHistoryRepository, PlayerRepository playerRepository,
			RoundRepository roundRepository, TestCaseRepository testCaseRepository, ResultRepository resultRepository) {
		super();
		this.matchHistoryRepository = matchHistoryRepository;
		this.playerRepository = playerRepository;
		this.roundRepository = roundRepository;
		this.testCaseRepository = testCaseRepository;
		this.resultRepository = resultRepository;
	}

	@Autowired
	private MatchHistoryRepository matchHistoryRepository;
	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private RoundRepository roundRepository;
	@Autowired
	private TestCaseRepository testCaseRepository;
	@Autowired
	private ResultRepository resultRepository;

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




}
