package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import jakarta.persistence.*;

@MappedSuperclass
public class TestCase {

	public TestCase(int id, Long totalResult, Long compilingResult, Long coverageMNEResult, Long coverageMethodResult, Long coverageWMResult, Long coverageLineResult, Long coverageOutputResult, Long coverageBranchResult, Long coverageCBResult, Long coverageExceptionResult, int idTestClass, String pathSourceCode, String pathReport, TestClass testedClass) {
		this.id = id;
		this.totalResult = totalResult;
		this.compilingResult = compilingResult;
		this.coverageMNEResult = coverageMNEResult;
		this.coverageMethodResult = coverageMethodResult;
		this.coverageWMResult = coverageWMResult;
		this.coverageLineResult = coverageLineResult;
		this.coverageOutputResult = coverageOutputResult;
		this.coverageBranchResult = coverageBranchResult;
		this.coverageCBResult = coverageCBResult;
		this.coverageExceptionResult = coverageExceptionResult;
		this.pathSourceCode = pathSourceCode;
		this.pathReport = pathReport;
		this.testedClass = testedClass;
	}

	@Id
	@SequenceGenerator(name = "testCase_sequence", sequenceName = "testCase_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testCase_sequence")
	private int id;

	private Long totalResult;
	private Long compilingResult;
	private Long coverageMNEResult;
	private Long coverageMethodResult;
	private Long coverageWMResult;
	private Long coverageLineResult;
	private Long coverageOutputResult;
	private Long coverageBranchResult;
	private Long coverageCBResult;
	private Long coverageExceptionResult;

	private String pathSourceCode;
	private String pathReport;

	@ManyToOne(cascade = CascadeType.ALL)
	private TestClass testedClass;

	public TestCase() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Long totalResult) {
		this.totalResult = totalResult;
	}

	public Long getCompilingResult() {
		return compilingResult;
	}

	public void setCompilingResult(Long compilingResult) {
		this.compilingResult = compilingResult;
	}

	public Long getCoverageMNEResult() {
		return coverageMNEResult;
	}

	public void setCoverageMNEResult(Long coverageMNEResult) {
		this.coverageMNEResult = coverageMNEResult;
	}

	public Long getCoverageMethodResult() {
		return coverageMethodResult;
	}

	public void setCoverageMethodResult(Long coverageMethodResult) {
		this.coverageMethodResult = coverageMethodResult;
	}

	public Long getCoverageWMResult() {
		return coverageWMResult;
	}

	public void setCoverageWMResult(Long coverageWMResult) {
		this.coverageWMResult = coverageWMResult;
	}

	public Long getCoverageLineResult() {
		return coverageLineResult;
	}

	public void setCoverageLineResult(Long coverageLineResult) {
		this.coverageLineResult = coverageLineResult;
	}

	public Long getCoverageOutputResult() {
		return coverageOutputResult;
	}

	public void setCoverageOutputResult(Long coverageOutputResult) {
		this.coverageOutputResult = coverageOutputResult;
	}

	public Long getCoverageBranchResult() {
		return coverageBranchResult;
	}

	public void setCoverageBranchResult(Long coverageBranchResult) {
		this.coverageBranchResult = coverageBranchResult;
	}

	public Long getCoverageCBResult() {
		return coverageCBResult;
	}

	public void setCoverageCBResult(Long coverageCBResult) {
		this.coverageCBResult = coverageCBResult;
	}

	public Long getCoverageExceptionResult() {
		return coverageExceptionResult;
	}

	public void setCoverageExceptionResult(Long coverageExceptionResult) {
		this.coverageExceptionResult = coverageExceptionResult;
	}

	public String getPathSourceCode() {
		return pathSourceCode;
	}

	public void setPathSourceCode(String pathSourceCode) {
		this.pathSourceCode = pathSourceCode;
	}

	public String getPathReport() {
		return pathReport;
	}

	public void setPathReport(String pathReport) {
		this.pathReport = pathReport;
	}

	public TestClass getTestedClass() {
		return testedClass;
	}

	public void setTestedClass(TestClass testedClass) {
		this.testedClass = testedClass;
	}

	@Override
	public String toString() {
		return "TestCase{" +
				"id=" + id +
				", totalResult=" + totalResult +
				", compilingResult=" + compilingResult +
				", coverageMNEResult=" + coverageMNEResult +
				", coverageMethodResult=" + coverageMethodResult +
				", coverageWMResult=" + coverageWMResult +
				", coverageLineResult=" + coverageLineResult +
				", coverageOutputResult=" + coverageOutputResult +
				", coverageBranchResult=" + coverageBranchResult +
				", coverageCBResult=" + coverageCBResult +
				", coverageExceptionResult=" + coverageExceptionResult +
				", pathSourceCode='" + pathSourceCode + '\'' +
				", pathReport='" + pathReport + '\'' +
				", testedClass=" + testedClass +
				'}';
	}
}
