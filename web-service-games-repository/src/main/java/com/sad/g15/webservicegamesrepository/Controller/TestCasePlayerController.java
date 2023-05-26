package com.sad.g15.webservicegamesrepository.Controller;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCasePlayer;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestCasePlayerController {
    @Autowired
    private ServiceFacade facade;

    @PostMapping(value = "/addTestPlayer", consumes = "application/json")
    public TestCasePlayer addMatch(@RequestBody TestCasePlayer testCasePlayer){
        return facade.createTestP(testCasePlayer);
    }
}
