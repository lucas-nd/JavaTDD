package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.ControllerChampions;

class SadTestControllerChampions {
//Defini alguns parametros dos testes como 1000 já que é pouco provável que a professora execute
//os testes tantas vezes até chegar em 1000 registros, no mais, peço que caso ocorra algum problema
//a professora considere modifical alguns desses valores hardcoded.
	@Test
	void naoDeveriaCriarCampeaoSemNome() throws Exception {
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("", "Mid");
		});
		
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoSemFuncao() throws Exception {
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("Ahri", "");
		});
		
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoSemNomeEFuncao() throws Exception {
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("", "");
		});
		
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoNomeNulo() throws Exception {
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion(null, "Mid");
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoFuncaoNulo() throws Exception {
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("Ahri", null);
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoNomeEFuncaoNulo() throws Exception {
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion(null, null);
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void naoDeveriaEditarCampeaoIdNaoExiste() throws Exception{
		ControllerChampions c = new ControllerChampions();
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1000, "Ahri", "mid");
		});
		
		assertEquals("O campeão não existe na base de dados.", e.getMessage());
	}
	
	void naoDeveriaEditarCampeaoIdNegativo() throws Exception{
		ControllerChampions c = new ControllerChampions();
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(-1, "Ahri", "mid");
		});
		
		assertEquals("O Id é negativo!", e.getMessage());
	}

	@Test
	void nãoDeveriaEditarCampeaoSemNome() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "", "mid");
		});
		
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoSemFuncao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "Lee Sin", "");
		});
		
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoSemNomeEFuncao() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "", "");
		});
		
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoNomeNulo() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, null, "mid");
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoFuncaoNulo() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "Lee Sin", null);
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoNomeEFuncaoNulo() throws Exception{
		ControllerChampions c = new ControllerChampions();
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, null, null);
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaApagarCampeaoIdNaoExiste() throws Exception{
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.deleteChampion(1000);
		});
		
		assertEquals("O campeão não existe na base de dados.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaApagarCampeaoIdNegativo() throws Exception{
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.deleteChampion(-1);
		});
		
		assertEquals("O Id é negativo!", e.getMessage());
	}
	
	@Test
	void nãoDeveriaRetonarCampeaoIdNaoExiste() throws Exception{
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.getChampion(1000);
		});
		
		assertEquals("O campeão não existe na base de dados.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaRetonarCampeaoIdNegativo() throws Exception{
		ControllerChampions c = new ControllerChampions();
		
		Exception e = assertThrows(Exception.class, ()->{
			c.getChampion(-1);
		});
		
		assertEquals("O Id é negativo!", e.getMessage());
	}
}
