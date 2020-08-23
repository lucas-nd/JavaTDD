package utils;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import controller.ControllerChampions;
import controller.ControllerMatch;
import models.Champions;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CriaChampions c = new CriaChampions();
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		ArrayList<Champions> blueTeam1 = new ArrayList<Champions>();
		ArrayList<Champions> redTeam1 = new ArrayList<Champions>();
		
		ControllerMatch controll = new ControllerMatch();
		ControllerChampions ch = new ControllerChampions();
		

		
		try {
			ch.criaChampion(null, null);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
