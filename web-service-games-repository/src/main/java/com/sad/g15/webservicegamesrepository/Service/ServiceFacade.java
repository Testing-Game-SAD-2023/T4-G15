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

        MatchHistory match = new MatchHistory();
        MatchHistory msaved =  mservice.create(match);

        Round round = new Round();
        Round rsaved = rservice.create(round);

        //for each partecipante crea un result e salvalo
        for (Integer i:idPlayers) {

            Player player = pservice.readById(i);
            Result result = new Result();
            result.setPlayer(player);
            result.setMatch(msaved);
            reservice.create(result);
            
        }

        rservice.attachRound(rsaved, msaved);
        return msaved;
    }
}
