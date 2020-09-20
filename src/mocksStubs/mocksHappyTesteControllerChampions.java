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

class mocksHappyTesteControllerChampions {
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
		Mockito.when(rs.getString(1)).thenReturn("1");
		Mockito.when(rs.getString(2)).thenReturn("Ahri");
		Mockito.when(rs.getString(3)).thenReturn("Mid");
	}

	@Test
	void deveriaCriarCampeao() throws Exception {
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		Champions c1 = c.criaChampion("Ahri", "Mid");
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertNotNull(c1);
	}

	@Test
	void deveriaEditarCampeao() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Jungle");
		
		boolean result = c.editarChampion(3, "Ahri", "Mid");
		
		Mockito.verify(ps, Mockito.times(2)).executeUpdate();//1 execute update do criaChampion e outro do editarChampions
		assertTrue(result);
	}
	
	@Test
	void deveriaApagarCampeao() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		c.criaChampion("Ahri", "Jungle");
		
		boolean result = c.deleteChampion(10);
		
		Mockito.verify(ps, Mockito.times(1)).executeQuery();
		assertTrue(result);
	}
	
	@Test
	void deveriaRetonarCampeao() throws Exception{
		this.setUp();
		ControllerChampions c = new ControllerChampions(conexao);
		Champions champ = c.criaChampion("Ahri", "Jungle");
		
		Mockito.verify(ps, Mockito.times(1)).executeQuery();
		Champions result = c.getChampion(134);
		assertEquals(result.getId(), champ.getId());
	}
}
