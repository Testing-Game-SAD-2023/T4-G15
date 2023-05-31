package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "Round")
@Table(name = "round")
public class Round {

	public Round(int id, Boolean result, int robotId, List<TestCasePlayer> testCasesPlayer,
			List<TestCaseRobot> testCasesRobot) {
		super();
		this.id = id;
		this.result = result;
		this.robotId = robotId;
		this.testCasesPlayer = testCasesPlayer;
		this.testCasesRobot = testCasesRobot;
	}

	@Id
	@SequenceGenerator(name = "round_sequence", sequenceName = "round_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_sequence")
	private int id;
	private Boolean result;
	private int robotId; // aggregation 1 to 1 converted into attribute.

	@OneToMany
	private List<TestCasePlayer> testCasesPlayer = new ArrayList<>();

	@OneToMany
	private List<TestCaseRobot> testCasesRobot = new ArrayList<>();

	public Round() {

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

	public List<TestCasePlayer> getTestCasesPlayer() {
		return testCasesPlayer;
	}

	public void setTestCasePlayer(TestCasePlayer testCasePlayer){
		this.testCasesPlayer.add(testCasePlayer);
	}

	public void setTestCasesPlayer(List<TestCasePlayer> testCasesPlayer) {
		this.testCasesPlayer = testCasesPlayer;
	}

	public List<TestCaseRobot> getTestCasesRobot() {
		return testCasesRobot;
	}

	public void setTestCasesRobot(List<TestCaseRobot> testCasesRobot) {
		this.testCasesRobot = testCasesRobot;
	}
	public void setTestCaseRobot(TestCaseRobot testCaseRobot){
		this.testCasesRobot.add(testCaseRobot);
	}

	@Override
	public String toString() {
		return "Round [id=" + id + ", result=" + result + ", robotId=" + robotId + ", testCasesPlayer="
				+ testCasesPlayer + ", testCasesRobot=" + testCasesRobot + "]";
	}
}
