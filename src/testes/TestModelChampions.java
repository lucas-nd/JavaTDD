package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Champions;

class TestModelChampions {

	@Test
	void deveriaSetarId() {
		Champions c = new Champions(0, "Ahri", "Mid");
		int id = 1;
		c.setId(id);
		
		assertEquals(id, c.getId());
	}

	@Test
	void deveriaBuscarId() {
		int id = 1;
		Champions c = new Champions(id, "Ahri", "Mid");
		
		assertEquals(id, c.getId());
	}
	
	@Test
	void deveriaSetarNome() {
		Champions c = new Champions(1, "", "Mid");
		String nome = "Ahri";
		c.setNome(nome);
		
		assertEquals(nome, c.getNome());
	}

	@Test
	void deveriaBuscarNome() {
		String nome = "Ahri";
		Champions c = new Champions(1, nome, "Mid");
		
		assertEquals(nome, c.getNome());
	}
	
	@Test
	void deveriaSetarFuncao() {
		Champions c = new Champions(1, "Ahri", "");
		String funcao = "Mid";
		c.setFuncao(funcao);
		
		assertEquals(funcao, c.getFuncao());
	}

	@Test
	void deveriaBuscarFuncao() {
		String funcao = "Mid";
		Champions c = new Champions(1, "Ahri", funcao);
		
		assertEquals(funcao, c.getFuncao());
	}
}
