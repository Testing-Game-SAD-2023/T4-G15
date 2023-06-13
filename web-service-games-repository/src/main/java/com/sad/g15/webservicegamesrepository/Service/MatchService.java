package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Match;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

	public MatchService(RepositoriesFacade facade, RoundService roundService, ResultService resultService) {
		this.facade = facade;
	}

	private final RepositoriesFacade facade;

	//A Match e Round ho dato la responsabilità di creare anche gli oggetti in loro contenuti.
	public Match create(Match match) {
		return (Match) facade.save(match);
	}

	public Match readS(Match match) {
		Optional<Object> matchFound = facade.findById(Match.class, match.getId());
		if(matchFound.isPresent()) {
			return (Match) matchFound.get();
		} else {
			throw new EntityNotFoundException("Match not found");
		}
	}

	public List<Match> readM(Player player) {
		return facade.findMatchByPlayer(player.getId());
	}


	/**
	 * Metodo utilizzato per la ricerca di un match tramite ID.
	 * @param idMatch
	 * @return single Match
	 */
	public Match readSById(int idMatch){

		return (Match) facade.findById(Match.class, idMatch).orElse(null);
	}

	public void delete(Match match) {
		facade.deleteById(Match.class, match.getId());
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
	
	public void addResults(Match match, List<Result> results) {
		match.setResults(results);
	}
	
	public void setMatchScenario(Match match, String scenario) {
		match.setScenario(scenario);
	}
	
	public void setMatchStartDate(Match match) {
        match.setStartDate(LocalDateTime.now());
	}
	
	public void setMatchEndDate(Match match, LocalDateTime endDate) {
		match.setEndDate(endDate);
	}
}
