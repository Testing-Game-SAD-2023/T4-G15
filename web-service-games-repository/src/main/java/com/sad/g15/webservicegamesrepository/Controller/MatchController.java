package com.sad.g15.webservicegamesrepository.Controller;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**-----------------------------------------addRound-----------------------------------------
     * Il parametro deve essere passato come un JSON Object:
     *
     * {
     *     "id" : 16,
     *     "rounds" : [{
     *         "id_robot" : "1"
     *     }
     *     ]
     * }
     *
     * Bisogna specificare ID del match per salvare il round (id di round sarà salvato in seguito), il
     * resto dei parametri è opzionale (come visibile sopra).
     * @param match
     * @return MatchHistory / Object
     * ------------------------------------------------------------------------------------------
     */
    @PutMapping("/updateMatch/addRound")
    public MatchHistory addRound(@RequestBody MatchHistory match){
        return facade.createRound(match);
    }


    /**
     * Metodo get Riceve sul path indicato l'id del match e ne ritorna uno solo
     * @param idMatch
     * @return single Match.
     */
    @GetMapping("/getSingleMatch/{idMatch}")
        public MatchHistory getMatchS(@PathVariable int idMatch){
            return facade.readSMatch(idMatch);
        }



    @GetMapping(path = "/getResultPlayer/{idPlayer}")
    @ResponseBody
    public List<Result> getResultPlayer(@PathVariable int idPlayer) {

        return facade.readResultByPlayerId(idPlayer);
    }
}




