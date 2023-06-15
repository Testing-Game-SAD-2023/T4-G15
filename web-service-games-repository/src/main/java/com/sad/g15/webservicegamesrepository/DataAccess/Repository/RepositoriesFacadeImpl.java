package com.sad.g15.webservicegamesrepository.DataAccess.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;

@Repository
public class RepositoriesFacadeImpl implements RepositoriesFacade{

	public RepositoriesFacadeImpl(MatchRepository matchRepository, PlayerRepository playerRepository,
                                  RoundRepository roundRepository, TestCaseRepository testCaseRepository, ResultRepository resultRepository, RobotRepository robotRepository) {
		super();
		this.matchRepository = matchRepository;
		this.playerRepository = playerRepository;
		this.roundRepository = roundRepository;
		this.testCaseRepository = testCaseRepository;
		this.resultRepository = resultRepository;
        this.robotRepository = robotRepository;
    }

	@Autowired
	private final MatchRepository matchRepository;
	private final PlayerRepository playerRepository;
	private final RoundRepository roundRepository;
	private final TestCaseRepository testCaseRepository;
	private final ResultRepository resultRepository;
    private final RobotRepository robotRepository;

	@Override
	public Object save(Object entity) {
		if (entity instanceof Match) {
			return matchRepository.save((Match) entity);
		} else if (entity instanceof Player) {
			return playerRepository.save((Player) entity);
		} else if (entity instanceof Round) {
			return roundRepository.save((Round) entity);
		} else if (entity instanceof TestCase) {
			return testCaseRepository.save((TestCase) entity);
		} else if (entity instanceof Result) {
			return resultRepository.save((Result) entity);
		} else if (entity instanceof Robot) {
            return robotRepository.save((Robot) entity);
        }
		
		throw new IllegalArgumentException("Invalid entity type: " + entity.getClass().getName());
	}
	
	@Override
	public Optional<Object> findById(Class<?> type, int id) {
		if (type.equals(Match.class)) {
			return matchRepository.findById(id).map(Function.identity());
		} else if (type.equals(Player.class)) {
            return playerRepository.findById(id).map(Function.identity());
        } else if (type.equals(Result.class)) {
            return resultRepository.findById(id).map(Function.identity());
        } else if (type.equals(Round.class)) {
            return roundRepository.findById(id).map(Function.identity());
        } else if (type.equals(TestCase.class)) {
            return testCaseRepository.findById(id).map(Function.identity());
        } else if (type.equals(Robot.class)) {
            return robotRepository.findById(id).map(Function.identity());
        }
		
        return Optional.empty();
	}
	
	@Override
	public Object getReferenceById(Class<?> entityType, int id) {
        if (entityType.equals(Match.class)) {
            return matchRepository.getReferenceById(id);
        } else if (entityType.equals(Player.class)) {
            return playerRepository.getReferenceById(id);
        } else if (entityType.equals(Result.class)) {
            return resultRepository.getReferenceById(id);
        } else if (entityType.equals(Round.class)) {
            return roundRepository.getReferenceById(id);
        } else if (entityType.equals(TestCase.class)) {
            return testCaseRepository.getReferenceById(id);
        } else if (entityType.equals(Robot.class)) {
            return robotRepository.getReferenceById(id);
        }
        
        return null;
    }
	
	@Override
	public boolean existsById(Class<?> entityType, int id) {
        if (entityType.equals(Match.class)) {
            return matchRepository.existsById(id);
        } else if (entityType.equals(Player.class)) {
            return playerRepository.existsById(id);
        } else if (entityType.equals(Result.class)) {
            return resultRepository.existsById(id);
        } else if (entityType.equals(Round.class)) {
            return roundRepository.existsById(id);
        } else if (entityType.equals(TestCase.class)) {
            return testCaseRepository.existsById(id);
        } else if (entityType.equals(Robot.class)) {
            return robotRepository.existsById(id);
        }

        return false;
    }
	
	@Override
	public void deleteById(Class<?> entityType, int id) {
        if (entityType.equals(Match.class)) {
            matchRepository.deleteById(id);
        } else if (entityType.equals(Player.class)) {
            playerRepository.deleteById(id);
        } else if (entityType.equals(Result.class)) {
            resultRepository.deleteById(id);
        } else if (entityType.equals(Round.class)) {
            roundRepository.deleteById(id);
        } else if (entityType.equals(TestCase.class)) {
            testCaseRepository.deleteById(id);
        } else if (entityType.equals(Robot.class)) {
            robotRepository.deleteById(id);
        }
    }

	@Override
    public void delete(Object entity) {
        if (entity instanceof Match) {
            matchRepository.delete((Match) entity);
        } else if (entity instanceof Player) {
            playerRepository.delete((Player) entity);
        } else if (entity instanceof Result) {
            resultRepository.delete((Result) entity);
        } else if (entity instanceof Round) {
            roundRepository.delete((Round) entity);
        } else if (entity instanceof TestCase) {
            testCaseRepository.delete((TestCase) entity);
        } else if (entity instanceof Robot) {
            robotRepository.delete((Robot) entity);
        }
    }

	@Override
	public List<Match> findMatchByPlayer(int idPlayer) {
		return resultRepository.findMatchByPlayer(idPlayer);
	}

	@Override
	public List<Result> readResultsByMatchId(int id) {
		return resultRepository.readResultsByMatchId(id);
	}

	@Override
	public List<Result> readResultByPlayerId(int idPlayer) {
		return resultRepository.readResultByPlayerId(idPlayer);
	}

	@Override
	public List<Round> findByMatchId(int id) {
		return roundRepository.findByMatchId(id);
	}

    @Override
    public int deleteTestCase(int idTestCase) {
        int i=0,j=0;

        i=testCaseRepository.deleteTestCasePlayer(idTestCase);
        j=testCaseRepository.deleteTestCaseRobot(idTestCase);

        return i+j;
    }

}
