package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ServiceFacade {

    public ServiceFacade(MatchHistoryService mservice, RoundService rservice, ResultService reservice,
                         PlayerService pservice) {
        this.mservice = mservice;
        this.rservice = rservice;
        this.reservice = reservice;
        this.pservice = pservice;
    }

    private MatchHistoryService mservice;
    private RoundService rservice;
    private ResultService reservice;
    private PlayerService pservice;

    /**
     * ---------------------------createMatch---------------------------------------------------------------------------
     * La seguente funzione nasconde la complessità nel salvare un match.
     * La match entity è contenitore di numerosi componenti che vengono istanziati e salvati
     * in questa funzione attraverso procedure call di Service definiti all'interno dello stesso Layer Service.
     * createMatch: create match (no id key in input) -> create round (no id key in input) -> for each idPlayer fornito
     * crea result (no id key in input); alla fine attach round creato a match.
     * @param idPlayers
     * @return MatchHistory
     * -----------------------------------------------------------------------------------------------------------------
     */
    public MatchHistory createMatch(ArrayList<Integer> idPlayers){

        Round round = new Round();
        Round rsaved = rservice.create(round);

        MatchHistory match = new MatchHistory();
        mservice.addRound(match,rsaved);
        MatchHistory msaved =  mservice.create(match);

        //for each partecipante crea un result e salvalo
        for (Integer i:idPlayers) {

            Player player = pservice.readById(i);
            Result result = new Result();

            //Get and Set are in the Data Access Layer. Services could use them without violating layer dependencies.
            result.setPlayer(player);   //Could define an attachPlayer in Result service if set violates dependencies.
            result.setMatch(msaved);    //same thing.
            reservice.create(result);
            
        }
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
}
