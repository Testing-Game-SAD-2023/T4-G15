package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestClass;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import org.springframework.stereotype.Service;

@Service
public class TestClassService {

    private final RepositoriesFacade facade;

    public TestClassService(RepositoriesFacade facade) {
        this.facade = facade;
    }

    public TestClass readById(int idTestClass){
        return (TestClass) facade.getReferenceById(TestClass.class, idTestClass);
    }
}
