package com.sad.g15.webservicegamesrepository.Controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

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
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;

@RestController

public class MatchController {

	@Autowired
	private ServiceFacade facade;

	/**
	 * -----------------------------------------addMatch-----------------------------------------
	 * Il parametro deve essere passato come un JSON body:
	 * 
	 * { "idStudents": [value1, value2,...,valueN], "scenario": "exampleScenario" }
	 * 
	 * @return "Match added successfully"
	 *         ------------------------------------------------------------------------------------------
	 */
	@PostMapping(value = "/addMatch", consumes = "application/json")
	public ResponseEntity<String> addMatch(@RequestBody JsonNode requestBody) {

		ArrayList<Integer> idStudents = new ArrayList<>();

		for (JsonNode element : requestBody.get("idStudents")) {
			idStudents.add(element.asInt());
		}

		String scenario = requestBody.get("scenario").asText();

		facade.createMatch(idStudents, scenario);

		return ResponseEntity.status(HttpStatus.OK).body("Match added successfully");
	}

	/**
	 * -----------------------------------------addRound-----------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "id" : 16, "rounds" : [{ "id_robot" : "1" } ] }
	 *
	 * Bisogna specificare ID del match per salvare il round (id di round sarà
	 * salvato in seguito), il resto dei parametri è opzionale (come visibile
	 * sopra).
	 * 
	 * @param match
	 * @return MatchHistory / Object
	 *         ------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/addRound")
	public MatchHistory addRound(@RequestBody MatchHistory match) {
		return facade.createRound(match);
	}
	
	/**
	 * -----------------------------------------updateRound-----------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "idRound" : 16, "result" : "true", "idRobot" : 121 }
	 *
	 * Bisogna specificare ID del round, il nuovo risultato e il nuovo ID del Robot, l'ID
	 * del match viene invece indicato nell'URI
	 *  
	 * @param idMatch, requestBody 
	 * @return "Round updated successfully"
	 *         ------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/{idMatch}/updateRound")
	public ResponseEntity<String> updateRound(@PathVariable int idMatch, @RequestBody JsonNode requestBody) {
		MatchHistory match = facade.readSMatch(idMatch);
		int idRound = requestBody.get("idRound").asInt();
		boolean result = requestBody.get("result").asBoolean();
		int idRobot = requestBody.get("idRobot").asInt();
		facade.updateRound(match, idRound, result, idRobot);
		return ResponseEntity.status(HttpStatus.OK).body("Round updated successfully");
	}

	/**
	 * -----------------------------------------addTestCasePlayer-----------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "id" : 16, "rounds" : [{ "id" : "1" "testCasePlayer":[{ } } ] }
	 *
	 * Bisogna specificare ID del match, quello di Round e il player che ha creato
	 * il Test. Il resto dei dati è riguarda tutti i punteggi legati alle metriche
	 * di coverage.
	 * 
	 * @param match
	 * @return MatchHistory / Object
	 *         ------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/updateRound/addTestCasePlayer")
	public MatchHistory addTestcasePlayer(@RequestBody MatchHistory match) {
		return facade.createTestCasePlayer(match);
	}

	/**
	 * -----------------------------------------addTestCaseRobot-----------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "id" : 16, "rounds" : [{ "id" : "1" "testCasePlayer":[{ } } ] }
	 *
	 * @param match
	 * @return MatchHistory / Object
	 *         ------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/updateRound/addTestCaseRobot")
	public MatchHistory addTestcaseRobot(@RequestBody MatchHistory match) {
		return facade.createTestCaseRobot(match);
	}

	/**
	 * Metodo get Riceve sul path indicato l'id del match e ne ritorna uno solo
	 * 
	 * @param idMatch
	 * @return single Match.
	 */
	@GetMapping("/controller/{idMatch}")
	public MatchHistory getMatchS(@PathVariable int idMatch) {
		return facade.readSMatch(idMatch);
	}

}
