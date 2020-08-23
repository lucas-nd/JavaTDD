package utils;

import java.util.ArrayList;

import models.Champions;

public class CriaChampions {
	public ArrayList<Champions> getRedTeam() {
		ArrayList<Champions> redTeam = new ArrayList<Champions>();
		
		Champions c1 = new Champions(1, "Gnar", "Top");
		Champions c2 = new Champions(2, "Lee Sin", "Jungle");
		Champions c3 = new Champions(3, "Ahri", "Mid");
		Champions c4 = new Champions(4, "Caitlyn", "Ad-Carry");
		Champions c5 = new Champions(5, "Sona", "Support");
		
		redTeam.add(c1);
		redTeam.add(c2);
		redTeam.add(c3);
		redTeam.add(c4);
		redTeam.add(c5);
		
		return redTeam;
	}
	
	public ArrayList<Champions> getBlueTeam() {
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		
		Champions c1 = new Champions(1, "Gnar", "Top");
		Champions c2 = new Champions(2, "Lee Sin", "Jungle");
		Champions c3 = new Champions(3, "Ahri", "Mid");
		Champions c4 = new Champions(4, "Caitlyn", "Ad-Carry");
		Champions c5 = new Champions(5, "Sona", "Support");
		
		blueTeam.add(c1);
		blueTeam.add(c2);
		blueTeam.add(c3);
		blueTeam.add(c4);
		blueTeam.add(c5);
		
		return blueTeam;
	}
}
