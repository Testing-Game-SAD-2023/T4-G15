package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Result;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    public ResultService(RepositoriesFacade facade) {
        this.facade = facade;
    }
    private final RepositoriesFacade facade;

    public Result create(Result result){
        return facade.getResultRepository().save(result);
    }

    public Result update(Result result){
        facade.getResultRepository().deleteById(result.getId());
        return facade.getResultRepository().save(result);
    }

    public void delete(Result result){
        facade.getResultRepository().delete(result);
    }

}
