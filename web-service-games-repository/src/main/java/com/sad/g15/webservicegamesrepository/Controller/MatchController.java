package com.sad.g15.webservicegamesrepository.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.MatchHistory;
import com.sad.g15.webservicegamesrepository.Service.MatchHistoryService;
import com.sad.g15.webservicegamesrepository.Service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
