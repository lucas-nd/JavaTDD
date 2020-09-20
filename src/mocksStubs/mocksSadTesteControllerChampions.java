package mocksStubs;

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

class mocksSadTesteControllerChampions {
	@Mock 
	private Conexao conexao = Mockito.mock(Conexao.class);
	
	@Mock
	private Connection con = Mockito.mock(Connection.class);
	
	@Mock
	private PreparedStatement ps = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private ResultSet rs = Mockito.mock(ResultSet.class);
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		Mockito.when(conexao.AbrirConnection()).thenReturn(con);
		Mockito.when(con.prepareStatement(Mockito.any(String.class))).thenReturn(ps);
		
		Mockito.when(ps.executeUpdate()).thenReturn(1);
		Mockito.when(ps.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getString(Mockito.anyInt())).thenReturn("1");
	}
	
	@Before
	public void secondSetUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		Mockito.when(conexao.AbrirConnection()).thenReturn(con);
		Mockito.when(con.prepareStatement(Mockito.any(String.class))).thenReturn(ps);
		
		Mockito.when(ps.executeUpdate()).thenReturn(0);
		Mockito.when(ps.executeQuery()).thenReturn(null);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getString(Mockito.anyInt())).thenReturn("1");
	}
	
	@Test
	void naoDeveriaCriarCampeaoSemNome() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("", "Mid");
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoSemFuncao() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("Ahri", "");
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoSemNomeEFuncao() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("", "");
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoNomeNulo() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion(null, "Mid");
		});
		
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoFuncaoNulo() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion("Ahri", null);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarCampeaoNomeEFuncaoNulo() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.criaChampion(null, null);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void naoDeveriaEditarCampeaoIdNaoExiste() throws Exception{
		this.secondSetUp();
		ControllerChampions c = new ControllerChampions(conexao);
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "Ahri", "mid");
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O campeão não existe na base de dados.", e.getMessage());
	}
	
	void naoDeveriaEditarCampeaoIdNegativo() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(-1, "Ahri", "mid");
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O Id é negativo!", e.getMessage());
	}

	@Test
	void nãoDeveriaEditarCampeaoSemNome() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "", "mid");
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoSemFuncao() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "Lee Sin", "");
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoSemNomeEFuncao() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "", "");
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O nome ou a função do campeão não foram informadas.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoNomeNulo() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, null, "mid");
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoFuncaoNulo() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, "Lee Sin", null);
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaEditarCampeaoNomeEFuncaoNulo() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Mid");
		Exception e = assertThrows(Exception.class, ()->{
			c.editarChampion(1, null, null);
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("O nome ou a função do campeão são nulos.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaApagarCampeaoIdNaoExiste() throws Exception{
		this.secondSetUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.deleteChampion(103);
		});
		
		assertEquals("O campeão não existe na base de dados.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaApagarCampeaoIdNegativo() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.deleteChampion(-1);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O Id é negativo!", e.getMessage());
	}
	
	@Test
	void nãoDeveriaRetonarCampeaoIdNaoExiste() throws Exception{
		this.secondSetUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.getChampion(1);
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeQuery();
		assertEquals("O campeão não existe na base de dados.", e.getMessage());
	}
	
	@Test
	void nãoDeveriaRetonarCampeaoIdNegativo() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		
		Exception e = assertThrows(Exception.class, ()->{
			c.getChampion(-1);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeQuery();
		assertEquals("O Id é negativo!", e.getMessage());
	}
}
