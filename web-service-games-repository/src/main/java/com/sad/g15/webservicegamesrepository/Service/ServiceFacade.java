package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ServiceFacade {

    //Le funzioni nel facade forse dovrebbero essere nella propria classe service il facade richiamare solo
    //la funzione implementata. Indagare.

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

    public TestCasePlayer createTestP (TestCasePlayer testCasePlayer){

        TestCasePlayer tsaved = (TestCasePlayer) (TestCasePlayer) tservice.create(testCasePlayer);
        Round round = new Round();
        round = rservice.readById(testCasePlayer.getPlayer().getId());
        round.setTestCasePlayer(tsaved);
        return tsaved;
    }
}
