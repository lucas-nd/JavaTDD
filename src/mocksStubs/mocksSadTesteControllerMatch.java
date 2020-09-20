package mocksStubs;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import connection.Conexao;
import controller.ControllerChampions;
import controller.ControllerMatch;
import models.Champions;
import utils.CriaChampions;

class mocksSadTesteControllerMatch {
	@Mock 
	private Conexao conexao = Mockito.mock(Conexao.class);
	
	@Mock
	private Connection con = Mockito.mock(Connection.class);
	
	@Mock
	private PreparedStatement ps = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private ResultSet rs = Mockito.mock(ResultSet.class);

	@Mock
	private ControllerChampions champions = Mockito.mock(ControllerChampions.class);
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		Champions teste = new Champions(1, "Ahri", "Mid");

		Mockito.when(conexao.AbrirConnection()).thenReturn(con);
		Mockito.when(con.prepareStatement(Mockito.any(String.class))).thenReturn(ps);
		
		Mockito.when(ps.executeUpdate()).thenReturn(1);
		Mockito.when(ps.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getString(Mockito.anyInt())).thenReturn("1");
		
		Mockito.when(champions.getChampion(Mockito.anyInt())).thenReturn(teste);

	}
	
	@Before
	public void secondSetUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		Champions teste = new Champions(1, "Ahri", "Mid");

		Mockito.when(conexao.AbrirConnection()).thenReturn(con);
		Mockito.when(con.prepareStatement(Mockito.any(String.class))).thenReturn(ps);
		
		Mockito.when(ps.executeUpdate()).thenReturn(0);
		Mockito.when(ps.executeQuery()).thenReturn(null);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getString(Mockito.anyInt())).thenReturn("1");
		
		Mockito.when(champions.getChampion(Mockito.anyInt())).thenReturn(teste);

	}
	
	@Test
	void naoDeveriaCriarPartidaDoisTimesIncompletos() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		ArrayList<Champions> redTeam = new ArrayList<Champions>();
	
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, redTeam);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("Existe pelo menos um time incompleto.", e.getMessage());
	}

	@Test
	void naoDeveriaCriarPartidaTimeAzulIncompleto() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		CriaChampions c = new CriaChampions();
		
		ArrayList<Champions> blueTeam = new ArrayList<Champions>();
		ArrayList<Champions> redTeam = c.getRedTeam();
	
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, redTeam);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("Existe pelo menos um time incompleto.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaTimeVermelhoIncompleto() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		CriaChampions c = new CriaChampions();
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = new ArrayList<Champions>();
	
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, redTeam);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("Existe pelo menos um time incompleto.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaDoisTimesInexistentes() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(null, null);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("Existe pelo menos um time nulo.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaTimeAzulInexistente() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		CriaChampions c = new CriaChampions();
		ArrayList<Champions> redTeam = c.getBlueTeam();
		
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(null, redTeam);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("Existe pelo menos um time nulo.", e.getMessage());
	}
	
	@Test
	void naoDeveriaCriarPartidaTimeVermelhoInexistente() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		CriaChampions c = new CriaChampions();
		ArrayList<Champions> blueTeam = c.getBlueTeam();

		
		Exception e = assertThrows(Exception.class, ()->{
			m.criaMatch(blueTeam, null);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("Existe pelo menos um time nulo.", e.getMessage());
	}

	@Test
	void naoDeveriaRetornarUmaPartidaNaoExistePartida() throws Exception {
		this.secondSetUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		Exception e = assertThrows(Exception.class, ()->{
			m.getMatch(1);
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeQuery();
		assertEquals("A partida não existe.", e.getMessage());
	}
	
	@Test
	void naoDeveriaRetornarUmaPartidaIdNegativo() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		Exception e = assertThrows(Exception.class, ()->{
			m.getMatch(-1);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O Id é negativo!", e.getMessage());
	}
	
	@Test 
	void naoDeveriaApagarUmaPartidaNaoExistePartida() throws Exception {
		this.secondSetUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		Exception e = assertThrows(Exception.class, ()->{
			m.deleteMatch(1);
		});
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertEquals("A partida não existe.", e.getMessage());
	}
	
	@Test 
	void naoDeveriaApagarUmaPartidaIdNegativo() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		Exception e = assertThrows(Exception.class, ()->{
			m.deleteMatch(-1);
		});
		
		Mockito.verify(ps, Mockito.times(0)).executeUpdate();
		assertEquals("O Id é negativo!", e.getMessage());
	}
}
