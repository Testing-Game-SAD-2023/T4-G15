package com.sad.g15.webservicegamesrepository.DataAccess.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "Round")
@Table(name = "round")
public class Round {

	public Round(int id, int robotId, List<TestCasePlayer> testCasesPlayer, List<TestCaseRobot> testCasesRobot, LocalDateTime startDate, LocalDateTime endDate, Robot robot) {
		this.id = id;
		this.robotId = robotId;
		this.testCasesPlayer = testCasesPlayer;
		this.testCasesRobot = testCasesRobot;
		this.startDate = startDate;
		this.endDate = endDate;
		this.robot = robot;
	}

	@Id
	@SequenceGenerator(name = "round_sequence", sequenceName = "round_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_sequence")
	private int id;
	private int robotId; // aggregation 1 to 1 converted into attribute.

	@OneToMany
	private List<TestCasePlayer> testCasesPlayer = new ArrayList<>();

	@OneToMany
	private List<TestCaseRobot> testCasesRobot = new ArrayList<>();

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	@OneToOne
	private Robot robot;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	@Override
	public String toString() {
		return "Round{" +
				"id=" + id +
				", robotId=" + robotId +
				", testCasesPlayer=" + testCasesPlayer +
				", testCasesRobot=" + testCasesRobot +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", robot=" + robot +
				'}';
	}
}
