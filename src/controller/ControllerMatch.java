package controller;

import java.util.ArrayList;

import models.Champions;
import models.Match;

public class ControllerMatch {
	private ArrayList<Match> Matches = new ArrayList<Match>(); 

	public Match criaMatch(ArrayList<Champions> blueTeam, ArrayList<Champions> redTeam) throws Exception{
		if(blueTeam == null || redTeam == null) {
			throw new Exception("Existe pelo menos um time nulo.");
		}
		if(Matches.size() > 0) {
			Match lastMatch = this.getMatch(Matches.size());
			if(blueTeam.size() == 5 && redTeam.size() == 5) {
				Match match = new Match(lastMatch.getId() + 1, blueTeam, redTeam);
				Matches.add(match);
				return match;		
			}
			throw new Exception("Existe pelo menos um time incompleto.");				
		}else {
			if(blueTeam.size() == 5 && redTeam.size() == 5) {
				Match match = new Match(1, blueTeam, redTeam);
				Matches.add(match);
				return match;		
			}
			throw new Exception("Existe pelo menos um time incompleto.");
		}
	}
	
	public Match getMatch(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		for(int i = 0; i < Matches.size(); i++){
			if(Matches.get(i).getId() == id) {
				return Matches.get(i);
			}
		}
		
		throw new Exception("A partida não existe.");
	}

	public boolean deleteMatch(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		for(int i = 0; i < Matches.size(); i++){
			if(Matches.get(i).getId() == id) {
				Matches.remove(Matches.get(i));
				return true;
			}
		}
		
		throw new Exception("A partida não existe.");
	}

	public ArrayList<Match> getMatches() {
		return Matches;
	}
}
