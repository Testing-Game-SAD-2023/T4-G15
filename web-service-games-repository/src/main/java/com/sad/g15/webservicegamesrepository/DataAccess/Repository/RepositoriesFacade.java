package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

public class RepositoriesFacade {
	
	public RepositoriesFacade(MatchHistoryRepository matchHistoryRepository, PlayerRepository playerRepository,
			RoundRepository roundRepository, TestCaseRepository testCaseRepository) {
		super();
		this.matchHistoryRepository = matchHistoryRepository;
		this.playerRepository = playerRepository;
		this.roundRepository = roundRepository;
		this.testCaseRepository = testCaseRepository;
	}

	private MatchHistoryRepository matchHistoryRepository;
	
	private PlayerRepository playerRepository;
	
	private RoundRepository roundRepository;
	
	private TestCaseRepository testCaseRepository;
	
	//Insert Methods Here
	
	//Override services here

	
	
	
	

}
