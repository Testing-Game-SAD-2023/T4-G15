package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Match;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Robot;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCasePlayer;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCaseRobot;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
		return (Round) facade.getReferenceById(Round.class, id);
	}

	public boolean deleteById(int idRound) {

		if (facade.existsById(Round.class, idRound)) {
			facade.deleteById(Round.class, idRound);
			return true;
		}

		return false;

	}
	
	public void setRoundStartDate(Round round) {
		round.setStartDate(LocalDateTime.now());
	}
	
	public void setRoundEndDate(Round round) {
		round.setEndDate(LocalDateTime.now());
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
	

}
