package com.sad.g15.webservicegamesrepository.Controller;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

public class MatchController {
    @Autowired
    private ServiceFacade facade;

    /**-----------------------------------------addMatch-----------------------------------------
     * Il parametro deve essere passato come un JSON Array ["value1","value2",...,"valueN"].
     * @param idStudents
     * @return MatchHistory / Object
     * ------------------------------------------------------------------------------------------
     */
    @PostMapping(value = "/addMatch", consumes = "application/json")
    public MatchHistory addMatch(@RequestBody ArrayList<Integer> idStudents){
        return facade.createMatch(idStudents);
    }

    @PutMapping("updateMatch/addRound")
    public MatchHistory addRound(@RequestBody MatchHistory match){
        return facade.createRound(match);
    }


    /**
     * Metodo get Riceve sul path indicato l'id del match e ne ritorna uno solo
     * @param idMatch
     * @return single Match.
     */
    @GetMapping("/controller/{idMatch}")
        public MatchHistory getMatchS(@PathVariable int idMatch){
            return facade.readSMatch(idMatch);
        }

}
