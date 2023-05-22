package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import java.util.ArrayList;
import java.util.List;

public class TestCaseCollection {

    private List<TestCase> testCases;

    public TestCaseCollection(List<TestCase> testCases) {
        this.testCases = new ArrayList<TestCase>();
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        return "TestCaseCollection{" +
                "testCases=" + testCases +
                '}';
    }
}
