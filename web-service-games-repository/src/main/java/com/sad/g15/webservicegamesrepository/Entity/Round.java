package com.sad.g15.webservicegamesrepository.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Round {

	public Round(int id, Boolean result) {
		super();
		this.id = id;
		this.result = result;
	}

	@Id
	private int id;
	
	private Boolean result;
	
	private int robotId;

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

	@Override
	public String toString() {
		return "Round [id=" + id + ", result=" + result + "]";
	}

}
