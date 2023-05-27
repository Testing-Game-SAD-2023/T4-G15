package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public MatchHistory readSMatch(int idMatch){
     return mservice.readSById(idMatch);
    }
}
