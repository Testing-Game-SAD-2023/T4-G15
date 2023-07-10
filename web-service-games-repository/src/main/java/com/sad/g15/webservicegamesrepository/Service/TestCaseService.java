package com.sad.g15.webservicegamesrepository.Service;

import com.sad.g15.webservicegamesrepository.DataAccess.Entity.Round;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestClass;
import com.sad.g15.webservicegamesrepository.DataAccess.Repository.RepositoriesFacade;
import com.sad.g15.webservicegamesrepository.DataAccess.Entity.TestCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {
    public TestCaseService(RepositoriesFacade facade) {
        this.facade = facade;
    }
    private final RepositoriesFacade facade;

    public TestCase readById(int id) throws Exception{
        return facade.getTestCaseById(id);
    }

    public TestCase create(TestCase testcase){
        return (TestCase) facade.save(testcase);
    }

    public void delete(TestCase testCase){
        facade.delete(testCase);
    }

    public int deleteTestCase(int idTestCase) {
        facade.deleteTestCaseRef(idTestCase);
        return facade.deleteTestCase(idTestCase);
    }

    public List<Integer> getTestCasesPlayerFromTestClass(int idTestClass) {
        return facade.getTestCasesPlayerFromTestClass(idTestClass);
    }

    public List<Integer> getTestCasesRobotFromTestClass(int idTestClass) {
        return facade.getTestCasesRobotFromTestClass(idTestClass);
    }

    // We may need to split this class into two to fulfill Player and Robot different TestCases needs
}
