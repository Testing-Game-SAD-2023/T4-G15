package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCase;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {
    public TestCaseService(RepositoriesFacade facade) {
        this.facade = facade;
    }
    private final RepositoriesFacade facade;

    public TestCase create(TestCase testcase){
        return facade.getTestCaseRepository().save(testcase);
    }

    public void delete(TestCase testCase){
        facade.getTestCaseRepository().delete(testCase);
    }

    // We may need to split this class into two to fullfill Player and Robot different TestCases needs
}
