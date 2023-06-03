package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ServiceFacade {

    public ServiceFacade(MatchHistoryService mservice, RoundService rservice, ResultService reservice,
                         PlayerService pservice, TestCaseService tservice) {
        this.mservice = mservice;
        this.rservice = rservice;
        this.reservice = reservice;
        this.pservice = pservice;
        this.tservice = tservice;
    }

    private MatchHistoryService mservice;
    private RoundService rservice;
    private ResultService reservice;
    private PlayerService pservice;
    private TestCaseService tservice;

    /**
     * ---------------------------createMatch---------------------------------------------------------------------------
     * La seguente funzione nasconde la complessità nel salvare un match.
     * La match entity è contenitore di numerosi componenti che vengono istanziati e salvati
     * in questa funzione attraverso procedure call di Service definiti all'interno dello stesso Layer Service.
     * createMatch: create match (no id key in input) -> create round (no id key in input) -> for each idPlayer fornito
     * crea result (no id key in input); alla fine attach round creato a match.
     * @param idPlayers,scenario
     * @return match
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory createMatch(ArrayList<Integer> idPlayers, String scenario){

        Round round = new Round();
        Round rsaved = rservice.create(round);

        MatchHistory match = new MatchHistory();
        match.setScenario(scenario);
        match.setStartDate(LocalDateTime.now());
        mservice.addRound(match,rsaved);
        

        //for each partecipante crea un result e salvalo
        for (Integer i:idPlayers) {

            Player player = pservice.readById(i);
            Result result = new Result();

            //Get and Set are in the Data Access Layer. Services could use them without violating layer dependencies.
            result.setPlayer(player);   //Could define an attachPlayer in Result service if set violates dependencies.
            reservice.create(result);
            mservice.addResult(match, result);

        }
        MatchHistory msaved =  mservice.create(match);
        return msaved;
    }

    /**
     * -------------------------------------------readSMatch------------------------------------------------------------
     * Legge dato in ingresso un id match, restituisce il riferimento per quel match.
     * @param idMatch
     * @return match
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory readSMatch(int idMatch){
     return mservice.readSById(idMatch);
    }

    /**
     * ------------------------------------------createRound------------------------------------------------------------
     * La funzione riceve un oggetto match in input con ALMENO il campo id not null. In base all'id fornito si preleva
     * l'oggetto Match nel database corrispondente e si aggiunge il Round/ i Round nel suo attribute lista rounds
     * dopo averli salvati nel database alla tabella 'rounds'.
     * @param idMatch,round
     * @return match updated with added round
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory createRound(int idMatch, Round round){

        MatchHistory dbmatch = mservice.readSById(idMatch);
        if(dbmatch == null)
            throw new RuntimeException("The given match does not exist!");

        //Facendo l'ipotesi che un robot con id 0 non esista (default value per id)
        if(round.getRobotId() != 0) {
            Round rbuff = rservice.create(round);
            mservice.addRound(dbmatch, rbuff);
            //2. Update del match
            return mservice.update(dbmatch);
        }
        else {
            throw new RuntimeException("Robot id not specified inside the round JSON Object");
        }
    }
    
    
    /**
     * ------------------------------------------updateRound------------------------------------------------------------
     * La funzione riceve un valore idMatch, l'ID del match contente il Round, l'id del Round e i nuovi valori di result
     * e idRobot. In base all'id fornito si preleva l'oggetto Match nel database corrispondente e si modifica il Round
     * scelto prelevandolo dalla lista rounds.
     * @param idMatch,idRound,result,idRobot
     * @return round updated
     * -----------------------------------------------------------------------------------------------------------------
     */
    public Round updateRound(int idMatch, int idRound, int idRobot) {
    	
    	MatchHistory match = mservice.readSById(idMatch);
    	
    	Predicate<? super Round> predicate = round -> round.getId() == idRound;
		Round round = rservice.readM(match).stream().filter(predicate).findFirst().orElse(null);
		
		round.setRobotId(idRobot);
		return rservice.update(round);
    }

    /**
     *------------------------------------------createTestCasePlayer----------------------------------------------------
     * Fare riferimento al Class Diagram delle Entity. TestCase--->Round--->Match quindi occorre dato l'id in input
     * recuperare l'oggetto match con id corrispondente dal db, selezionare il round specificato in input e 'aggiungere'
     * il nuovo TestCasePlayer dopo averlo salvato nel db attraverso i metodi jpa repository.
     * @param idMatch,idRound,idPlayer,testCasePlayer
     * @return round updated with added testCasePlayer
     * -----------------------------------------------------------------------------------------------------------------
     */
    public Round createTestCasePlayer(int idMatch, int idRound, int idPlayer, TestCasePlayer testCasePlayer) {

        //Usiamo l'id passato come parametro per prelevare il match dal db
        MatchHistory dbmatch = mservice.readSById(idMatch);
        if(dbmatch == null)
            throw new RuntimeException("The given match does not exist!");

        Round dbround = null;
        //Verifichiamo se il round è nel match selezionato in input
        for (Round r : dbmatch.getRounds()) {
            if (r.getId() == idRound) {
                dbround = rservice.readById(idRound);
                break;
            }
        }
        if (dbround == null)
            throw new RuntimeException("The given match does not contain the given round!");

        Player pbuff = pservice.readById(idPlayer);
        if(pbuff == null)
            throw new RuntimeException("The given player does not exist!");

        //Adesso verifichiamo se il player selezionato partecipa effettivamente al match selezionato
        boolean playerIsInMatch = false;
        for (Result r: dbmatch.getResults()) {
            if(r.getPlayer().getId() == idPlayer){
                playerIsInMatch = true;
                break;
            }
        }
        if(playerIsInMatch != true)
            throw new RuntimeException("The given player does not partecipate in this match!");

        /*Al momento non è previsto un update testCase (Anche perchè non avrebbe molto senso) quindi occorre
          controllare se i campi dell'oggetto passato siano tutti NOT NULL, eccetto player che deve essere inserito
          dalla funzione a runtime.*/

        testCasePlayer.setPlayer(pbuff);
        TestCasePlayer tbuff = (TestCasePlayer) tservice.create(testCasePlayer);
        rservice.AddTestCasePlayer(dbround, tbuff);

        return rservice.update(dbround);
    }

    /**
     *------------------------------------------createTestCaseRobot-----------------------------------------------------
     * Fare riferimento al Class Diagram delle Entity. TestCase--->Round--->Match quindi occorre dato l'id in input
     * recuperare l'oggetto match con id corrispondente dal db, selezionare il round specificato in input e 'aggiungere'
     * il nuovo TestCaseRobot dopo averlo salvato nel db attraverso i metodi jpa repository.
     * @param idMatch,idRobot,testCaseRobot
     * @return round updated with added testCaseRobot
     * -----------------------------------------------------------------------------------------------------------------
     */
    public  Round createTestCaseRobot(int idMatch, int idRound, TestCaseRobot testCaseRobot){

        //Usiamo l'id passato come parametro per prelevare il match dal db
        MatchHistory dbmatch = mservice.readSById(idMatch);
        if(dbmatch == null)
            throw new RuntimeException("The given match does not exist!");

        Round dbround = null;
        /*Verifichiamo se il round è nel match selezionato in input.
          Questo ciclo serve nel caso in cui round non abbia id univoco ma abbia come chiave primaria la coppia
          idRound, idMatch. Nell'attuale versione (0.8) id di round è univoco quindi basterebbe un readById senza ciclo
          foreach. Al momento manteniamo il ciclo per non perdere di generalità.
        */
        for (Round r : dbmatch.getRounds()) {
            if (r.getId() == idRound) {
                dbround = rservice.readById(idRound);
                break;
            }
        }
        if (dbround == null)
            throw new RuntimeException("The given match does not contain the given round!");

        TestCaseRobot tbuff = (TestCaseRobot) tservice.create(testCaseRobot);
        rservice.AddTestCaseRobot(dbround, tbuff);

        return rservice.update(dbround);
    }

    /**
     *------------------------------------------readResultIdPlayer----------------------------------------------------
     * Dato in input l'IdPlayer il metodo ritorna la lista dei risultati del player identificato.
     * @param idPlayer
     * @return List<Result>
     * -----------------------------------------------------------------------------------------------------------------
     */
    public List<Result> readResultIdPlayer(int idPlayer){
        return reservice.readResultByIdPlayer(idPlayer);
    }

    /**---------------------------------------------updateMatch---------------------------------------------------------
     * Aggiorna il match.
     * @param idMatch,match
     * @param match
     * @return match updated
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory updateMatch(int idMatch, MatchHistory match) {
        MatchHistory dbmatch = mservice.readSById(idMatch);

        if(dbmatch.getId()!=match.getId()) return null;

        if(match.getEndDate()!=null && !match.getEndDate().equals(dbmatch.getEndDate())) dbmatch.setEndDate(match.getEndDate());
        if(match.getScenario()!=null && !match.getScenario().equals(dbmatch.getScenario())) dbmatch.setScenario(match.getScenario());

        boolean can_add = true;

        for(Result r : match.getResults()){
            can_add = true;

            for(Result dbr : dbmatch.getResults()){
                if(r.getId()==dbr.getId()){
                    can_add=false;

                    if(r.getPlayer()!=null && !r.getPlayer().equals(dbr.getPlayer())) dbr.setPlayer(r.getPlayer());
                    if(r.getResult()!=null && !r.getResult().equals(dbr.getResult())) dbr.setResult(r.getResult());

                    break;
                }
            }

            if(can_add) mservice.addResult(dbmatch, r);
        }

        return mservice.update(dbmatch);
    }

	public boolean deleteRoundById(int idRound) {
		return rservice.deleteById(idRound);
	}
}
