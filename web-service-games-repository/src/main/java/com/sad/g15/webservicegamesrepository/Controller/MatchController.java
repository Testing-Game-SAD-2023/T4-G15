package com.sad.g15.webservicegamesrepository.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;

@RestController

public class MatchController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private ServiceFacade facade;

    /**-----------------------------------------addMatch-----------------------------------------
     * Il parametro deve essere passato come un JSON body:
     * 
     * {
     * 		"idStudents": [value1, value2,...,valueN],
     * 		"scenario": "exampleScenario"
     * }
     * 
     * @return "Match added successfully"
     * ------------------------------------------------------------------------------------------
     */
    @PostMapping(value = "/addMatch", consumes = "application/json")
    public ResponseEntity<String> addMatch(@RequestBody JsonNode requestBody){
    	ArrayList<Integer> idStudents = new ArrayList<>();
    	for(JsonNode element : requestBody.get("idStudents")) {
            idStudents.add(element.asInt());
        }
    	
    	String scenario = requestBody.get("scenario").asText();
    	
    	facade.createMatch(idStudents, scenario);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Match added successfully");
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
    @GetMapping("/controller/{idMatch}")
        public MatchHistory getMatchS(@PathVariable int idMatch){
            return facade.readSMatch(idMatch);
        }

}
