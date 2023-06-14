package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Robot;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

@Service
public class RobotService {
    public RobotService(RepositoriesFacade facade) {
        this.facade = facade;
    }

    private final RepositoriesFacade facade;

    public Robot readById(int idRobot){
        return (Robot) facade.getReferenceById(Robot.class, idRobot);
    }
}
