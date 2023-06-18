package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Match;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Player;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    public ResultService(RepositoriesFacade facade) {
        this.facade = facade;
    }
    private final RepositoriesFacade facade;

    public Result create(Result result){
        return (Result) facade.save(result);
    }

    public Result update(Result result){
        return (Result) facade.save(result);
    }

    public void delete(Result result){
        facade.delete(result);
    }

    public List<Result> readResultsByMatch(Match match){
        return facade.readResultsByMatchId(match.getId());
    }

    public List<Result> readResultByIdPlayer(int idPlayer){
      return   facade.readResultByPlayerId(idPlayer);
    }
    
    public void setResultPlayer(Result result, Player player) {
    	result.setPlayer(player);
    }
    
    public void setResultScore(Result result, long scoreMatch) {
    	result.setScoreMatch(scoreMatch);
    }
    
    public void setResultOutcome(Result result, String outcome) {
    	result.setOutcome(outcome);
    }

}
