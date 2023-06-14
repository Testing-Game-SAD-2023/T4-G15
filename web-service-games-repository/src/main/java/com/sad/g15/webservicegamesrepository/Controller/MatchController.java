package com.sad.g15.webservicegamesrepository.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import com.sad.g15.webservicegamesrepository.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Match;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;
import org.springframework.web.server.ResponseStatusException;

@RestController

public class MatchController {

	@Autowired
	private ServiceFacade facade;

	/**
	 * -----------------------------------------addMatch----------------------------------------------------------------
	 * Il parametro deve essere passato come un JSON body:
	 * 
	 * { "idPlayers": [value1, value2,...,valueN], "scenario": "exampleScenario", "idRobot": 1 }
	 *
	 * @param requestBody
	 * @return "Match added successfully"
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@PostMapping(value = "/addMatch", consumes = "application/json")
	public ResponseEntity<String> addMatch(@RequestBody JsonNode requestBody) {

		ArrayList<Integer> idPlayers = new ArrayList<>();

		for (JsonNode element : requestBody.get("idPlayers")) {
			idPlayers.add(element.asInt());
		}

		String scenario = requestBody.get("scenario").asText();

		int idRobot = requestBody.get("idRobot").asInt();

		Match matchsaved = null;
		try {
			matchsaved = facade.createMatch(idPlayers, scenario, idRobot);
		} catch (PlayerNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		return ResponseEntity.status(HttpStatus.OK).body("Match added successfully with id:" + matchsaved.getId());
	}

	/**
	 * -----------------------------------------addRound----------------------------------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * {
	 *     "robot": {
	 *         "id":1
	 *     }
	 * }
	 *
	 * Bisogna specificare ID del match per salvare il round (id di round sarà
	 * salvato in seguito), il robot_id deve essere passato all'interno del JSON Object, il resto dei parametri è
	 * opzionale (come visibile sopra).
	 * 
	 * @param idMatch,round
	 * @return Match / Object
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@PutMapping(value = "/updateMatch/{idMatch}/addRound", consumes = "application/json")
	public ResponseEntity<String> addRound(@PathVariable int idMatch, @RequestBody Round round) {

		Match matchAddedRound = null;
		try {
			matchAddedRound = facade.createRound(idMatch, round);
		} catch (RobotNotFoundException | MatchNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		if(matchAddedRound!=null) return ResponseEntity.status(HttpStatus.OK).body("Round added to the specified Match with id" + matchAddedRound.getId());
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not add Round");
	}
	
	/**
	 * -----------------------------------------updateRound-------------------------------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "idRound" : 16, "end_date": "2023-06-02T21:00:00"}
	 *
	 * Bisogna specificare ID del round, l'end_date, l'ID
	 * del match viene invece indicato nell'URI
	 *  
	 * @param idMatch,requestBody
	 * @return "Round updated successfully"
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/{idMatch}/updateRound")
	public ResponseEntity<String> updateRound(@PathVariable int idMatch, @RequestBody JsonNode requestBody) {
	
		int idRound = requestBody.get("idRound").asInt();
		String endDate = requestBody.get("end_date").asText();

		LocalDateTime end_date = LocalDateTime.parse(endDate);

		try {
			facade.updateRound(idMatch, idRound, end_date);
		} catch (MatchNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Round updated successfully");
	}

	/**
	 * -----------------------------------------updateMatch-------------------------------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * {
	 *     "id": 1,
	 *     "scenario": "scenario",
	 *     "endDate": "2023-06-02T21:00:00",
	 *     "results": [
	 *         {
	 *             "id": 1,
	 *             "outcome": "sconfitta"
	 *         }
	 *     ]
	 * }
	 *
	 * Bisogna specificare ID del match nell'URI, nel JSON i parametri che si vogliono modificare come
	 * scenario, endDate e results
	 *
	 * @param idMatch, match
	 * @return "Match updated successfully"
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@PutMapping(value = "/updateMatch/{idMatch}", consumes = "application/json")
	public ResponseEntity<String> updateMatch(@PathVariable int idMatch, @RequestBody Match match) {

		Match updated_match = null;
		try {
			updated_match = facade.updateMatch(idMatch, match);
		} catch (MatchNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		if(updated_match!=null) return ResponseEntity.status(HttpStatus.OK).body("Match updated successfully");
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request - Could not update Match");
	}

	/**
	 * -----------------------------------------addTestCasePlayer-------------------------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "totalResult" : 12568, "compilingResult" : 1212, ecc...} <---- per gli altri campi vedere TestCasePlayer entity
	 *
	 * Bisogna specificare ID del match, quello di Round e il player che ha creato
	 * il Test. Il resto dei dati riguarda i punteggi di coverage e viene passato come JSON Object.
	 * Gli altri parametri come PathVariables.
	 * 
	 * @param idMatch,idRound,idPlayer,testCasePlayer
	 * @return Match / Object
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/{idMatch}/updateRound/{idRound}/addTestCasePlayer/{idPlayer}")
	public ResponseEntity<String> addTestcasePlayer(@PathVariable int idMatch, @PathVariable int idRound,
										  @PathVariable int idPlayer, @RequestBody TestCasePlayer testCasePlayer) {

		Round updatedRound = null;
		try {
			updatedRound = facade.addTestCasePlayer(idMatch, idRound, idPlayer, testCasePlayer);
		} catch (MatchNotFoundException | RoundNotFoundException | PlayerNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		if(updatedRound!=null) return ResponseEntity.status(HttpStatus.OK).body("TestCasePlayer added to the " +
				"specified round");
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request - Could not add TestCasePlayer");
	}

	/**
	 * -----------------------------------------addTestCaseRobot--------------------------------------------------------
	 * Il parametro deve essere passato come un JSON Object:
	 *
	 * { "totalResult" : 12568, "compilingResult" : 1212, ecc...}
	 *
	 * Bisogna specificare ID del match e quello del Round a cui aggiungere il test case.
	 * Il resto dei dati riguarda i punteggi di coverage e viene passato come JSON Object.
	 * Gli ID come PathVariables.
	 *
	 * @param idMatch,idRound,testCaseRobot
	 * @return Match / Object
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/updateMatch/{idMatch}/updateRound/{idRound}/addTestCaseRobot")
	public ResponseEntity<String> addTestcaseRobot(@PathVariable int idMatch, @PathVariable int idRound,
										 @RequestBody TestCaseRobot testCaseRobot) {

		Round updatedRound = null;
		try {
			updatedRound = facade.createTestCaseRobot(idMatch, idRound, testCaseRobot);
		} catch (MatchNotFoundException | RoundNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		if(updatedRound!=null) return ResponseEntity.status(HttpStatus.OK).body("TestCaseRobot added to the " +
				"specified round");
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request - Could not add TestCaseRobot");
	}

	/**
	 * -----------------------------------------getMatchbyId------------------------------------------------------------
	 * Metodo get Riceve sul path indicato un id e restituisce il match con l'id indicato.
	 * 
	 * @param idMatch
	 * @return single Match.
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/getSingleMatch/{idMatch}")
	public Match getMatchS(@PathVariable int idMatch) {
		try {
			return facade.readSMatch(idMatch);
		} catch (MatchNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}


	/**
	 * -----------------------------------------getResultByIdPlayer-----------------------------------------------------
	 * Il seguente metodo mostra, dato l'id di un determinato giocatore, il risultato di tutte le partite
	 * che ha giocato.
	 *
	 * @param idPlayer
	 * @return List<Result>
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/getMatchesByIdPlayer/{idPlayer}")
	public List<Result> getMatchesByIdPlayer(@PathVariable int idPlayer){
		try {
			return facade.readResultIdPlayer(idPlayer);
		} catch (PlayerNotFoundException | ResultNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	/**
	 * -----------------------------------------deleteRound-----------------------------------------------------
	 * Il seguente metodo elimina un round dato il suo id.
	 * @param idRound
	 * @return ResponseEntity
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	@DeleteMapping("/deleteRound/{idRound}")
	public ResponseEntity<String> deleteRound(@PathVariable int idRound) {
		boolean deleted = false;
		try {
			deleted = facade.deleteRoundById(idRound);
		} catch (RoundNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		if (deleted) return ResponseEntity.status(HttpStatus.OK).body("Round deleted successfully");
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	}

}
