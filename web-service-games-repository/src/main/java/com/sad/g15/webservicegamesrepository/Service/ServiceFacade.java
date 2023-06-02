package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
     * @param idPlayers, scenario
     * @return MatchHistory
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
     * @param idMatch
     * @return
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
     * @param match
     * @return match updated
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory createRound(MatchHistory match){

        //1. Salvo tutti i round che sto per aggiungere nel database e passo il loro riferimento al match 'contenitore'.

        MatchHistory dbmatch = mservice.readSById(match.getId());
        for (Round r: match.getRounds()) {
            Round rbuff = rservice.create(r);
            mservice.addRound(dbmatch,rbuff);
        }

        //2. Update del match

        return mservice.update(dbmatch);

    }
    
    
    /**
     * ------------------------------------------updateRound------------------------------------------------------------
     * La funzione riceve un valore idMatch, l'ID del match contente il Round, l'id del Round e i nuovi valori di result e idRobot. 
     * In base all'id fornito si preleva l'oggetto Match nel database corrispondente e si modifica il Round scelto prelevandolo dalla lista rounds.
     * @param idMatch, idRound, result, idRobot
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
     * @param match
     * @return match
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory createTestCasePlayer(MatchHistory match){

        MatchHistory dbmatch = mservice.readSById(match.getId());
        /*  Qui per ricavare l'id del round a cui stiamo aggiungendo il test case cosa facciamo:
            1.  Prendiamo la lista di round del match passato come JSON (il round sarà 1 perchè io posso aggiungere
                ad un solo round dei testcase con una sola chiamata createTestCasePlayer. Detto ciò il primo elemento
                di tale lista sarà il nostro round.
            2.  Prelevato Round dalla lista con findFirst(), con getId() ci prendiamo l'id e Preleviamo il Round 'vero'
                dal db.
        */
        Round dbround = rservice.readById(match.getRounds().stream().findFirst().get().getId());
        for (TestCasePlayer tp: match.getRounds().stream().findFirst().get().getTestCasesPlayer()) {
            TestCasePlayer tbuff = (TestCasePlayer) tservice.create(tp);
            Player pbuff = pservice.readById(tbuff.getPlayer().getId());

            //Forse non conviene utilizzare direttamente un metodo di entity?
            tbuff.setPlayer(pbuff);
            rservice.AddTestCasePlayer(dbround,tbuff);
        }
        rservice.update(dbround);
    return mservice.update(dbmatch);
    }

    /**
     *------------------------------------------createTestCaseRobot----------------------------------------------------
     * Fare riferimento al Class Diagram delle Entity. TestCase--->Round--->Match quindi occorre dato l'id in input
     * recuperare l'oggetto match con id corrispondente dal db, selezionare il round specificato in input e 'aggiungere'
     * il nuovo TestCaseRobot dopo averlo salvato nel db attraverso i metodi jpa repository.
     * @param match
     * @return match
     * -----------------------------------------------------------------------------------------------------------------
     */
    public  MatchHistory createTestCaseRobot(MatchHistory match){

        MatchHistory dbmatch = mservice.readSById(match.getId());

        Round dbround = rservice.readById(match.getRounds().stream().findFirst().get().getId());
        for (TestCaseRobot tr: match.getRounds().stream().findFirst().get().getTestCasesRobot()) {
            TestCaseRobot trbuff = (TestCaseRobot) tservice.create(tr);
            rservice.AddTestCaseRobot(dbround,trbuff);
        }
        rservice.update(dbround);
        return mservice.update(dbmatch);
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
}
