package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchHistoryService {

	public MatchHistoryService(RepositoriesFacade facade) {
		this.facade = facade;
	}

	private final RepositoriesFacade facade;
	private RoundService roundService;

	public MatchHistory create(MatchHistory match) {

		return facade.getMatchHistoryRepository().save(match);
	}

	public Optional<MatchHistory> readS(MatchHistory match) {
		return facade.getMatchHistoryRepository().findById(match.getId());
	}

	public List<MatchHistory> readM(Player player) {
		return facade.getResultRepository().findMatchByPlayer(player.getId());
	}

	public void delete(MatchHistory match) {
		facade.getMatchHistoryRepository().delete(match);
	}

	public MatchHistory update(MatchHistory match) {
		facade.getMatchHistoryRepository().deleteById(match.getId());
		return facade.getMatchHistoryRepository().save(match);
	}

	public void addRound(MatchHistory match, Round round) {

		match.setRound(round);
	}
}
