package models;

import java.util.ArrayList;

public class Match {
	private int id;
	private ArrayList<Champions> blueTeam;
	private ArrayList<Champions> redTeam;
	
	public Match(int id, ArrayList<Champions> blueTeam, ArrayList<Champions> redTeam) {
		this.id = id;
		this.blueTeam = blueTeam;
		this.redTeam = redTeam;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Champions> getBlueTeam() {
		return blueTeam;
	}
	public ArrayList<Champions> getRedTeam() {
		return redTeam;
	}
}
