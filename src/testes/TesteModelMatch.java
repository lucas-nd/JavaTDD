package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import models.Champions;
import models.Match;

class TesteModelMatch {

	@Test
	void deveriaSetarId() {
		ArrayList<Champions> Team = new ArrayList<Champions>();
		Match m = new Match(0, Team, Team);
		int id = 1;
		m.setId(id);
		
		assertEquals(id, m.getId());
	}

	@Test
	void deveriaBuscarId() {
		ArrayList<Champions> Team = new ArrayList<Champions>();
		int id = 1;

		Match m = new Match(id, Team, Team);
		
		assertEquals(id, m.getId());
	}

	@Test
	void deveriaBuscarBlueTeam() {
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		ArrayList<Champions> redTeam = new ArrayList<Champions>();

		Match m = new Match(1, blueTeam, redTeam);
		
		assertEquals(blueTeam, m.getBlueTeam());
	}
	
	@Test
	void deveriaBuscarRedTeam() {
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		ArrayList<Champions> redTeam = new ArrayList<Champions>();

		Match m = new Match(1, blueTeam, redTeam);
		
		assertEquals(redTeam, m.getRedTeam());
	}
}
