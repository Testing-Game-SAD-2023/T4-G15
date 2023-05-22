package com.sad.g15.webservicegamesrepository.DataAccess.Repository.Entity;

import jakarta.persistence.*;

@Entity (name = "Round")
@Table (name = "round")
public class Round {

	@Id
	@SequenceGenerator(
			name = "round_sequence",
			sequenceName = "round_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "round_sequence"
	)
	private int id;
	private Boolean result;
	private int robotId;		//aggregation 1 to 1 converted into attribute.
	private TestCaseCollection testCases;

	public Round(int id, Boolean result, int robotId, TestCaseCollection testCases) {
		this.id = id;
		this.result = result;
		this.robotId = robotId;
		this.testCases = testCases;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getRobotId() {
		return robotId;
	}

	public void setRobotId(int robotId) {
		this.robotId = robotId;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public TestCaseCollection getTestCases() {
		return testCases;
	}

	public void setTestCases(TestCaseCollection testCases) {
		this.testCases = testCases;
	}

	@Override
	public String toString() {
		return "Round{" +
				"id=" + id +
				", result=" + result +
				", robotId=" + robotId +
				", testCases=" + testCases +
				'}';
	}
}
