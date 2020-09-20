package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import connection.Conexao;
import controller.ControllerChampions;
import models.Champions;

class HappyTestControllerChampions {
//Defini alguns parametros dos testes como hardcode já que é pouco provável prever o estado exato do BD
//no momento dos testes, no mais, peço que caso ocorra algum problema a professora considere modifical 
//alguns desses valores hardcoded.
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
		
		boolean result = c.editarChampion(1, "Yone", "Mid");
		assertTrue(result);
	}
	
	@Test
	void deveriaApagarCampeao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Jungle");
		
		boolean result = c.deleteChampion(2);
		assertTrue(result);
	}
	
	@Test
	void deveriaRetonarCampeao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Jungle");
		
		Champions result = c.getChampion(3);
		assertNotNull(result);
	}
}
