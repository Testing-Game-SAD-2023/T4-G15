package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestClass;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import com.sad.g15.webservicegamesrepository.Exceptions.TestNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TestClassService {

    private final RepositoriesFacade facade;

    public TestClassService(RepositoriesFacade facade) {
        this.facade = facade;
    }

    public TestClass readById(int idTestClass) throws TestNotFoundException{
        TestClass testClass = (TestClass) facade.findById(TestClass.class, idTestClass).orElse(null);
        if(testClass!=null) return testClass;
        else throw new NullPointerException();
    }

    public void populate(){
        facade.populate(2);
    }
}
