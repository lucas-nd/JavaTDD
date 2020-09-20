package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.ControllerMatch;
import models.Champions;
import models.Match;
import utils.CriaChampions;

class HappyTestControllerMatch {
//Defini alguns parametros dos testes como hardcode já que é pouco provável prever o estado exato do BD
//no momento dos testes, no mais, peço que caso ocorra algum problema a professora considere modifical 
//alguns desses valores hardcoded.
	@Test
	void deveriaCriarUmaPartida() throws Exception {
		CriaChampions c = new CriaChampions();
		ControllerMatch m = new ControllerMatch();
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		Match result = m.criaMatch(blueTeam, redTeam);
		
		assertNotNull(result);
	}

	@Test
	void deveriaRetornarUmaPartida() throws Exception {
		ControllerMatch m = new ControllerMatch();
		CriaChampions c = new CriaChampions();

		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		m.criaMatch(blueTeam, redTeam);
		Match result = m.getMatch(2);
		
		assertNotNull(result);
	}
	
	@Test
	void deveriaApagarUmaPartida() throws Exception {
		CriaChampions c = new CriaChampions();
		ControllerMatch m = new ControllerMatch();
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		m.criaMatch(blueTeam, redTeam);
		boolean result = m.deleteMatch(3);
		
		assertTrue(result);
	}
}
