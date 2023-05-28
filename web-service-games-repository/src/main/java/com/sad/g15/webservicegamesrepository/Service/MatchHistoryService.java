package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchHistoryService {

	public MatchHistoryService(RepositoriesFacade facade, RoundService roundService, ResultService resultService) {
		this.facade = facade;
		this.roundService = roundService;
		this.resultService = resultService;
	}

	private final RepositoriesFacade facade;
	private RoundService roundService;
	private ResultService resultService;

	//A MatchHistory e Round ho dato la responsabilità di creare anche gli oggetti in loro contenuti.
	public MatchHistory create(MatchHistory match) {

		//Generano errore perchè l'oggetto Match non ha in sè, almeno in partenza, la lista di Rounds e Results dal DB.
		/*for (Round r: match.getRounds()) {
			roundService.create(r);
		}

		for (Result r:match.getResults()) {
			resultService.create(r);
		}*/
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

		//occorre fare un for each dove si eliminano tutti i round prima di eliminare il match in sè.
		//stessa cosa per quanto riguarda round e TestCase.

		/*for (Round r: deleteMatch.getRounds()) {
			roundService.delete(r);
		}
		*/
		//Adesso elimino anche i result associati al match

		List<Result> results = resultService.readResultsByMatch(match);
		for (Result r:results) {
			resultService.delete(r);
		}
		facade.getMatchHistoryRepository().deleteById(match.getId());
	}

	public MatchHistory update(MatchHistory match) {
		return create(match);
	}

	public void addRound(MatchHistory match, Round round) {

		match.setRound(round);
	}
}
