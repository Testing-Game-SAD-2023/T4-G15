package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RobotRepository;
import com.sad.g15.webservicegamesrepository.Exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ServiceFacade {

	public ServiceFacade(MatchService mservice, RoundService rservice, ResultService reservice, PlayerService pservice,
			TestCaseService tservice, RobotService robotService, TestClassService tcService) {
		this.mservice = mservice;
		this.rservice = rservice;
		this.reservice = reservice;
		this.pservice = pservice;
		this.tservice = tservice;
		this.robotService = robotService;
		this.tcService = tcService;
	}

	private MatchService mservice;
	private RoundService rservice;
	private ResultService reservice;
	private PlayerService pservice;
	private TestCaseService tservice;
	private RobotService robotService;

	private TestClassService tcService;

	/**
	 * ---------------------------createMatch---------------------------------------------------------------------------
	 * La seguente funzione nasconde la complessità nel salvare un match. La match
	 * entity è contenitore di numerosi componenti che vengono istanziati e salvati
	 * in questa funzione attraverso procedure call di Service definiti all'interno
	 * dello stesso Layer Service. createMatch: create match (no id key in input) ->
	 * create round (no id key in input) -> for each idPlayer fornito crea result
	 * (no id key in input); alla fine attach round creato a match.
	 * 
	 * @param idPlayers,scenario
	 * @return match
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Match createMatch(ArrayList<Integer> idPlayers, String scenario, int idRobot)
			throws PlayerNotFoundException {

		List<Result> results = new ArrayList<>();

		// for each partecipante crea un result e salvalo
		for (Integer i : idPlayers) {

			Player player = pservice.readById(i);
			Result result = new Result();

			reservice.setResultPlayer(result, player);

			try {
				reservice.create(result);
			} catch (Exception e) {
				throw new PlayerNotFoundException("Player not in DB");
			}

			results.add(result);
		}

		Round round = new Round();
		rservice.setRoundStartDate(round);

		rservice.setRoundRobot(round, robotService.readById(idRobot));
		Round rsaved = rservice.create(round);

		Match match = new Match();

		mservice.setMatchScenario(match, scenario);
		mservice.setMatchStartDate(match);
		mservice.addRound(match, rsaved);
		mservice.addResults(match, results);

		Match msaved = mservice.create(match);
		return msaved;
	}

	/**
	 * -------------------------------------------readSMatch------------------------------------------------------------
	 * Legge dato in ingresso un id match, restituisce il riferimento per quel
	 * match.
	 * 
	 * @param idMatch
	 * @return match
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Match readSMatch(int idMatch) throws MatchNotFoundException {
		Match match = null;

		try {
			match = mservice.readSById(idMatch);
		} catch (Exception e){
			throw new MatchNotFoundException();
		}

		return match;
	}

	/**
	 * -------------------------------------------readSMatch------------------------------------------------------------
	 * Legge dato in ingresso un id round, restituisce il riferimento per quel
	 * round.
	 *
	 * @param idRound
	 * @return round
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Round readSRound(int idRound) throws RoundNotFoundException{
		Round round = null;

		try {
			round = rservice.readById(idRound);
		} catch (Exception e){
			throw new RoundNotFoundException("Round not found");
		}

		return round;
	}

	/**
	 * -------------------------------------------readSMatch------------------------------------------------------------
	 * Legge dato in ingresso un id TestCase, restituisce il riferimento per quel
	 * TestCase.
	 *
	 * @param idTestCase
	 * @return TestCase
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public TestCase readSTest(int idTestCase) throws TestNotFoundException{
		TestCase test = null;

		try {
			test = tservice.readById(idTestCase);
		} catch (Exception e){
			throw new TestNotFoundException("Test not found");
		}

		return test;
	}

	/**
	 * ------------------------------------------createRound------------------------------------------------------------
	 * La funzione riceve un oggetto match in input con ALMENO il campo id not null.
	 * In base all'id fornito si preleva l'oggetto Match nel database corrispondente
	 * e si aggiunge il Round/ i Round nel suo attribute lista rounds dopo averli
	 * salvati nel database alla tabella 'rounds'.
	 * 
	 * @param idMatch,round
	 * @return match updated with added round
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Match createRound(int idMatch, Round round) throws RobotNotFoundException, MatchNotFoundException {

		Match dbmatch = null;

		try {
			dbmatch = mservice.readSById(idMatch);
		} catch (Exception e) {
			throw new MatchNotFoundException();
		}

		Round rbuff = null;

		try {
			rbuff = rservice.create(round);
		} catch (Exception e) {
			throw new RobotNotFoundException("Robot not found");
		}

		rservice.setRoundStartDate(rbuff);

		mservice.addRound(dbmatch, rbuff);
		// 2. Update del match
		return mservice.update(dbmatch);

	}

	/**
	 * ------------------------------------------updateRound------------------------------------------------------------
	 * La funzione riceve un valore idMatch, l'ID del match contente il Round, l'id
	 * del Round e i nuovi valori di result e idRobot. In base all'id fornito si
	 * preleva l'oggetto Match nel database corrispondente e si modifica il Round
	 * scelto prelevandolo dalla lista rounds.
	 * 
	 * @param idMatch,idRound,result,idRobot
	 * @return round updated
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Round updateRound(int idMatch, int idRound, LocalDateTime end_date) throws MatchNotFoundException {

		Match match = null;

		try {
			match = mservice.readSById(idMatch);
		} catch (Exception e) {
			throw new MatchNotFoundException();
		}

		Predicate<? super Round> predicate = round -> round.getId() == idRound;
		Round round = rservice.readM(match).stream().filter(predicate).findFirst().orElse(null);

		rservice.setRoundEndDate(round, end_date);

		return rservice.update(round);
	}

	/**
	 * ------------------------------------------addTestCasePlayer----------------------------------------------------
	 * Fare riferimento al Class Diagram delle Entity. TestCase--->Round--->Match
	 * quindi occorre dato l'id in input recuperare l'oggetto match con id
	 * corrispondente dal db, selezionare il round specificato in input e
	 * 'aggiungere' il nuovo TestCasePlayer dopo averlo salvato nel db attraverso i
	 * metodi jpa repository.
	 * 
	 * @param idMatch,idRound,idPlayer,testCasePlayer
	 * @return round updated with added testCasePlayer
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Round addTestCasePlayer(int idMatch, int idRound, int idPlayer, TestCasePlayer testCasePlayer)
			throws MatchNotFoundException, RoundNotFoundException, PlayerNotFoundException, TestNotFoundException {

		// Usiamo l'id passato come parametro per prelevare il match dal db
		Match dbmatch = null;

		try {
			dbmatch = mservice.readSById(idMatch);
		} catch (Exception e) {
			throw new MatchNotFoundException();
		}

		Round dbround = null;
		// Verifichiamo se il round è nel match selezionato in input
		for (Round r : dbmatch.getRounds()) {
			if (r.getId() == idRound) {
				dbround = rservice.readById(idRound);
				break;
			}
		}
		if (dbround == null)
			throw new RoundNotFoundException("The given match does not contain the given round!");

		Player pbuff = pservice.readById(idPlayer);
		if (pbuff == null)
			throw new PlayerNotFoundException("The given player does not exist!");

		// Adesso verifichiamo se il player selezionato partecipa effettivamente al
		// match selezionato
		boolean playerIsInMatch = false;
		for (Result r : dbmatch.getResults()) {
			if (r.getPlayer().getId() == idPlayer) {
				playerIsInMatch = true;
				break;
			}
		}
		if (!playerIsInMatch)
			throw new PlayerNotFoundException("The given player does not partecipate in this match!");

		/*
		 * Al momento non è previsto un update testCase (Anche perchè non avrebbe molto
		 * senso) quindi occorre controllare se i campi dell'oggetto passato siano tutti
		 * NOT NULL, eccetto player che deve essere inserito dalla funzione a runtime.
		 */

		testCasePlayer.setPlayer(pbuff);


		TestClass testClassBuff = null;

		int test_class_id = testCasePlayer.getTestedClass().getId();
		try {
			testClassBuff = tcService.readById(test_class_id);
			testClassBuff.getId(); //refactor NullPointerException nel service
		} catch (EntityNotFoundException e){
			throw new TestNotFoundException("TestClass specified not found");
		}

		testCasePlayer.setTestedClass(testClassBuff);

		TestCasePlayer tbuff = (TestCasePlayer) tservice.create(testCasePlayer);
		rservice.AddTestCasePlayer(dbround, tbuff);

		return rservice.update(dbround);
	}

	/**
	 * ------------------------------------------createTestCaseRobot-----------------------------------------------------
	 * Fare riferimento al Class Diagram delle Entity. TestCase--->Round--->Match
	 * quindi occorre dato l'id in input recuperare l'oggetto match con id
	 * corrispondente dal db, selezionare il round specificato in input e
	 * 'aggiungere' il nuovo TestCaseRobot dopo averlo salvato nel db attraverso i
	 * metodi jpa repository.
	 * 
	 * @param idMatch,idRobot,testCaseRobot
	 * @return round updated with added testCaseRobot
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Round createTestCaseRobot(int idMatch, int idRound, TestCaseRobot testCaseRobot)
			throws MatchNotFoundException, RoundNotFoundException {

		// Usiamo l'id passato come parametro per prelevare il match dal db
		Match dbmatch = null;

		try {
			dbmatch = mservice.readSById(idMatch);
		} catch (Exception e) {
			throw new MatchNotFoundException();
		}

		Round dbround = null;
		/*
		 * Verifichiamo se il round è nel match selezionato in input. Questo ciclo serve
		 * nel caso in cui round non abbia id univoco ma abbia come chiave primaria la
		 * coppia idRound, idMatch. Nell'attuale versione (0.8) id di round è univoco
		 * quindi basterebbe un readById senza ciclo foreach. Al momento manteniamo il
		 * ciclo per non perdere di generalità.
		 */
		for (Round r : dbmatch.getRounds()) {
			if (r.getId() == idRound) {
				dbround = rservice.readById(idRound);
				break;
			}
		}
		if (dbround == null)
			throw new RoundNotFoundException("The given match does not contain the given round!");

		TestCaseRobot tbuff = (TestCaseRobot) tservice.create(testCaseRobot);
		rservice.AddTestCaseRobot(dbround, tbuff);

		return rservice.update(dbround);
	}

	/**
	 * ------------------------------------------readResultIdPlayer----------------------------------------------------
	 * Dato in input l'IdPlayer il metodo ritorna la lista dei risultati del player
	 * identificato.
	 * 
	 * @param idPlayer
	 * @return List<Result>
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public List<Result> readResultIdPlayer(int idPlayer) throws PlayerNotFoundException, ResultNotFoundException {
		try {
			pservice.readById(idPlayer);
		} catch (Exception e) {
			throw new PlayerNotFoundException("Player not found");
		}

		List<Result> results = reservice.readResultByIdPlayer(idPlayer);

		if (results != null)
			return results;
		else
			throw new ResultNotFoundException("No result available for this player, yet");
	}

	/**
	 * ---------------------------------------------updateMatch---------------------------------------------------------
	 * Aggiorna il match.
	 * 
	 * @param idMatch,match
	 * @param match
	 * @return match updated
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public Match updateMatch(int idMatch, Match match) throws MatchNotFoundException {
		Match dbmatch = null;

		try {
			dbmatch = mservice.readSById(idMatch);
		} catch (Exception e) {
			throw new MatchNotFoundException();
		}

		if (dbmatch.getId() != match.getId())
			return null;

		if (match.getEndDate() != null && !match.getEndDate().equals(dbmatch.getEndDate()))
			dbmatch.setEndDate(match.getEndDate());
		if (match.getScenario() != null && !match.getScenario().equals(dbmatch.getScenario()))
			dbmatch.setScenario(match.getScenario());

		boolean can_add = true;

		for (Result r : match.getResults()) {
			can_add = true;

			for (Result dbr : dbmatch.getResults()) {
				if (r.getId() == dbr.getId()) {
					can_add = false;

					if (r.getPlayer() != null && !r.getPlayer().equals(dbr.getPlayer()))
						dbr.setPlayer(r.getPlayer());
					if (r.getOutcome() != null && !r.getOutcome().equals(dbr.getOutcome()))
						dbr.setOutcome(r.getOutcome());

					break;
				}
			}

			if (can_add)
				mservice.addResult(dbmatch, r);
		}

		return mservice.update(dbmatch);
	}

	public boolean deleteRoundById(int idRound) throws RoundNotFoundException {
		try {
			rservice.readById(idRound);
		} catch (Exception e) {
			throw new RoundNotFoundException("Round not found");
		}

		return rservice.deleteById(idRound);
	}

	public boolean deleteMatchById(int idMatch) throws MatchNotFoundException {
		try {
			Match mToDelete = mservice.readSById(idMatch);
			for (Round round : mToDelete.getRounds()) {
				this.deleteRoundById(round.getId());
			}
		} catch (Exception e) {
			throw new MatchNotFoundException("Match not found");
		}

		return mservice.deleteById(idMatch);
	}

	/**
	 * ---------------------------------------------readMTestCases------------------------------------------------------
	 * Dati idMatch e idRound in output, facendo i dovuti controlli, la seguente
	 * funzione restituisce tutti i TestCase Robot e PLayer associati a quello
	 * specifico Round dello specifico Match, non facendo alcuna distinzione tra
	 * Test Case con campi ancora nulli (TestCase ancora in atto nel gioco) o non
	 * nulli (TestCase terminati).
	 * 
	 * @param idMatch
	 * @param idRound
	 * @return List<TestCase> testCases
	 *         -----------------------------------------------------------------------------------------------------------------
	 */
	public List<TestCase> readMTestCases(int idMatch, int idRound)
			throws MatchNotFoundException, RoundNotFoundException, TestNotFoundException {

		Match mfodd = null;

		// se non lo trova lancia l'eccezione match non trovato
		try {
			mfodd = mservice.readSById(idMatch);
		} catch (Exception e) {
			throw new MatchNotFoundException();
		}

		List<Round> rfodds = rservice.readM(mfodd);

		Round rfound = null;

		/*
		 * stesso discorso della funzione createTestCasePlayer: per non perdere di
		 * generalità vediamo tra i round associati al match trovato, quale round tra
		 * tutti quelli selezionati ha id che corrisponde all'idRound dato in input.
		 */

		for (Round r : rfodds) {
			if (r.getId() == idRound) {
				rfound = rservice.readById(r.getId());
			}
		}

		// se non lo trova lancia eccezione round non trovato per quel match(rfound
		// rimane nullo)
		if (rfound == null)
			throw new RoundNotFoundException("Round not found for Match given in input");

		// se non ha testCase (array list in return vuoto allora lancia eccezione)

		List<TestCase> testCasesOut = rservice.getTestCases(rfound);

		if (testCasesOut == null)
			throw new TestNotFoundException("No Test associated to given Round");

		return testCasesOut;
	}


	public List<Integer> readMTestCasesFromTestClass(int idTestClass) throws TestNotFoundException{
		List<Integer> out = new ArrayList<Integer>();
		out.addAll(tservice.getTestCasesPlayerFromTestClass(idTestClass));
		out.addAll(tservice.getTestCasesRobotFromTestClass(idTestClass));

		if(out.size()==0) throw new TestNotFoundException("No TestCases associated with the given TestClass");
		else return out;
	}

	public boolean deleteTestCaseById(int idTestCase) throws TestNotFoundException {
		if(tservice.deleteTestCase(idTestCase)==0) throw new TestNotFoundException("Test not found");
		else return true;
	}

	public void populate(){
		pservice.populate();
		robotService.populate();
	}
}
