package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;

import com.sad.g15.webservicegamesrepository.Exceptions.RoundNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoundService {

	public RoundService(RepositoriesFacade facade, TestCaseService testCaseService) {
		this.facade = facade;
	}

	private final RepositoriesFacade facade;

	public Round readS(int round_id) {
		Optional<Object> roundFound = facade.findById(Round.class, round_id);
		if(roundFound.isPresent()) {
			return (Round) roundFound.get();
		} else {
			throw new EntityNotFoundException("Round not found");
		}
	}

	public List<Round> readM(Match match) {
		return facade.findByMatchId(match.getId());
	}

	public Round create(Round round) {
		return (Round) facade.save(round);
	}

	public void delete(Round round) {
		facade.delete(round);
	}

	public Round update(Round round) {
		return (Round) facade.save(round);
	}

	public void AddTestCasePlayer(Round round, TestCasePlayer testCasePlayer) {
		round.setTestCasePlayer(testCasePlayer);
	}

	public void AddTestCaseRobot(Round round, TestCaseRobot testCaseRobot) {
		round.setTestCaseRobot(testCaseRobot);
	}

	public Round readById(int id) {
		Round round = null;
		round = (Round) facade.findById(Round.class, id).orElse(null);

		if(round!=null) return round;
		else throw new NullPointerException();
	}

	public void deleteById(int idRound) {
		facade.deleteById(Round.class, idRound);
	}
	
	public void setRoundStartDate(Round round) {
		round.setStartDate(LocalDateTime.now());
	}
	
	public void setRoundEndDate(Round round, LocalDateTime end_date) {
		round.setEndDate(end_date);
	}
	
	public void setRoundRobot(Round round, Robot robota) {
		round.setRobot(robota);
	}
	
	public void setRoundTestCasesPlayer(Round round, List<TestCasePlayer> testCases) {
		round.setTestCasesPlayer(testCases);
	}
	
	public void setRoundTestCasesRobot(Round round, List<TestCaseRobot> testCases) {
		round.setTestCasesRobot(testCases);
	}
	
	public void setRoundSingleTestCasePlayer(Round round, TestCasePlayer testCasePlayer) {
		round.setTestCasePlayer(testCasePlayer);
	}
	
	public void setRoundSingleTestCaseRobot(Round round, TestCaseRobot testCaseRobot) {
		round.setTestCaseRobot(testCaseRobot);
	}

	public List<TestCase> getTestCases(Round round){

		List<TestCase> testCasesOut = new ArrayList<>();
		testCasesOut.addAll(round.getTestCasesPlayer());
		testCasesOut.addAll(round.getTestCasesRobot());
		if(testCasesOut.isEmpty()) return null;
		else return testCasesOut;
	}
	

}
