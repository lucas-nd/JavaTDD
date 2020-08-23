package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.ControllerMatch;
import models.Champions;
import utils.CriaChampions;

class SadTestControllerMatch {

	@Test
	void naoDeveriaCriarPartidaDoisTimesIncompletos() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		ArrayList<Champions> redTeam = new ArrayList<Champions>();
	
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, redTeam);
		});
		
		assertEquals("Existe pelo menos um time incompleto.", e.getMessage());
	}

	@Test
	void naoDeveriaCriarPartidaTimeAzulIncompleto() throws Exception {
		ControllerMatch m = new ControllerMatch();
		CriaChampions c = new CriaChampions();
		
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		ArrayList<Champions> redTeam = c.getRedTeam();
	
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, redTeam);
		});
		
		assertEquals("Existe pelo menos um time incompleto.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaTimeVermelhoIncompleto() throws Exception {
		ControllerMatch m = new ControllerMatch();
		CriaChampions c = new CriaChampions();
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = new ArrayList<Champions>();
	
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, redTeam);
		});
		
		assertEquals("Existe pelo menos um time incompleto.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaDoisTimesInexistentes() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(null, null);
		});
		
		assertEquals("Existe pelo menos um time nulo.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaTimeAzulInexistente() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		CriaChampions c = new CriaChampions();
		ArrayList<Champions> redTeam = c.getBlueTeam();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(null, redTeam);
		});
		
		assertEquals("Existe pelo menos um time nulo.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaTimeVermelhoInexistente() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		CriaChampions c = new CriaChampions();
		ArrayList<Champions> blueTeam = c.getBlueTeam();

		
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, null);
		});
		
		assertEquals("Existe pelo menos um time nulo.", e.getMessage());
	}

	@Test
	void naoDeveriaRetornarUmaPartidaNaoExistePartida() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.getMatch(1);
		});
		
		assertEquals("A partida não existe.", e.getMessage());
	}
	
	@Test
	void naoDeveriaRetornarUmaPartidaIdNegativo() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.getMatch(-1);
		});
		
		assertEquals("O Id é negativo!", e.getMessage());
	}
	
	@Test 
	void naoDeveriaApagarUmaPartidaNaoExistePartida() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.deleteMatch(1);
		});
		
		assertEquals("A partida não existe.", e.getMessage());
	}
	
	@Test 
	void naoDeveriaApagarUmaPartidaIdNegativo() throws Exception {
		ControllerMatch m = new ControllerMatch();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.deleteMatch(-1);
		});
		
		assertEquals("O Id é negativo!", e.getMessage());
	}
	
}
