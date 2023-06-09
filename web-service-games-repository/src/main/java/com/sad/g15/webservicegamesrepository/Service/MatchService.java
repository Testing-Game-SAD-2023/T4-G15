package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Match;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

	public MatchService(RepositoriesFacade facade, RoundService roundService, ResultService resultService) {
		this.facade = facade;
		this.roundService = roundService;
		this.resultService = resultService;
	}

	private final RepositoriesFacade facade;
	private RoundService roundService;
	private ResultService resultService;

	//A Match e Round ho dato la responsabilità di creare anche gli oggetti in loro contenuti.
	public Match create(Match match) {
		return facade.getMatchHistoryRepository().save(match);
	}

	public Optional<Match> readS(Match match) {
		return facade.getMatchHistoryRepository().findById(match.getId());
	}

	public List<Match> readM(Player player) {
		return facade.getResultRepository().findMatchByPlayer(player.getId());
	}


	/**
	 * Metodo utilizzato per la ricerca di un match tramite ID.
	 * @param idMatch
	 * @return single Match
	 */
	public Match readSById(int idMatch){

		return facade.getMatchHistoryRepository().findById(idMatch).orElse(null);
	}

	public void delete(Match match) {
		facade.getMatchHistoryRepository().deleteById(match.getId());
	}

	public Match update(Match match) {
		return create(match);
	}

	public void addRound(Match match, Round round) {
		match.setRound(round);
	}
	
	public void addResult(Match match, Result result) {
		match.setResult(result);	
	}
}
