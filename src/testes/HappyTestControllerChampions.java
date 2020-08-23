package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.ControllerChampions;
import models.Champions;

class HappyTestControllerChampions {

	@Test
	void deveriaCriarCampeao() throws Exception {
		ControllerChampions c = new ControllerChampions();
		Champions c1 = c.criaChampion("Ahri", "Mid");
		assertNotNull(c1);
	}

	@Test
	void deveriaEditarCampeao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Jungle");
		
		boolean result = c.editarChampion(1, "Ahri", "Mid");
		assertTrue(result);
	}
	
	@Test
	void deveriaApagarCampeao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Jungle");
		
		boolean result = c.deleteChampion(1);
		assertTrue(result);
	}
	
	@Test
	void deveriaRetonarCampeao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		Champions champ = c.criaChampion("Ahri", "Jungle");
		
		Champions result = c.getChampion(1);
		assertEquals(result, champ);
	}
}
