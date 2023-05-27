package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchHistoryService {

	public MatchHistoryService(RepositoriesFacade facade, RoundService roundService) {
		this.facade = facade;
		this.roundService = roundService;
	}

	private final RepositoriesFacade facade;
	private RoundService roundService;

	//A MatchHistory e Round ho dato la responsabilità di creare anche gli oggetti in loro contenuti.
	public MatchHistory create(MatchHistory match) {
		for (Round r: match.getRounds()) {
			roundService.create(r);
		}
		return facade.getMatchHistoryRepository().save(match);
	}

	public Optional<MatchHistory> readS(MatchHistory match) {
		return facade.getMatchHistoryRepository().findById(match.getId());
	}

	public List<MatchHistory> readM(Player player) {
		return facade.getResultRepository().findMatchByPlayer(player.getId());
	}


	/**
	 * Metodo utilizzato per la ricerca di un match tramite ID.
	 * @param idMatch
	 * @return single Match
	 */
	public MatchHistory readSById(int idMatch){
		return facade.getMatchHistoryRepository().findById(idMatch).orElse(null);
	}

	public void delete(MatchHistory match) {
		facade.getMatchHistoryRepository().delete(match);
	}

	public MatchHistory update(MatchHistory match) {

		//occorre fare un for each dove si eliminano tutti i round prima di eliminare il match in sè.
		//stessa cosa per quanto riguarda round e TestCase.

		for (Round r: match.getRounds()) {
			roundService.delete(r);
		}
		facade.getMatchHistoryRepository().deleteById(match.getId());
		return create(match);
	}

	public void addRound(MatchHistory match, Round round) {

		match.setRound(round);
	}
}
