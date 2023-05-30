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

    /**-----------------------------------------addTestCasePlayer-----------------------------------------
     * Il parametro deve essere passato come un JSON Object:
     *
     * {
     *     "id" : 16,
     *     "rounds" : [{
     *         "id" : "1"
     *         "testCasePlayer":[{
     *         }
     *     }
     *     ]
     * }
     *
     * Bisogna specificare ID del match, quello di Round e il player che ha creato il Test. Il resto dei dati è
     * riguarda tutti i punteggi legati alle metriche di coverage.
     * @param match
     * @return MatchHistory / Object
     * ------------------------------------------------------------------------------------------
     */
    @PutMapping("/updateMatch/updateRound/addTestCasePlayer")
    public MatchHistory addTestcasePlayer(@RequestBody MatchHistory match){
        return facade.createTestCasePlayer(match);
    }

    /**-----------------------------------------addTestCaseRobot-----------------------------------------
     * Il parametro deve essere passato come un JSON Object:
     *
     * {
     *     "id" : 16,
     *     "rounds" : [{
     *         "id" : "1"
     *         "testCasePlayer":[{
     *         }
     *     }
     *     ]
     * }
     *
     * @param match
     * @return MatchHistory / Object
     * ------------------------------------------------------------------------------------------
     */
    @PutMapping("/updateMatch/updateRound/addTestCaseRobot")
    public MatchHistory addTestcaseRobot(@RequestBody MatchHistory match){
        return facade.createTestCaseRobot(match);
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
