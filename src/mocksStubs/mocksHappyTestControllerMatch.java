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
import models.Match;
import utils.CriaChampions;

class mocksHappyTestControllerMatch {
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
		Champions teste = new Champions(1, "Ahri", "Mid");
		MockitoAnnotations.initMocks(this);
		Mockito.when(conexao.AbrirConnection()).thenReturn(con);
		Mockito.when(con.prepareStatement(Mockito.any(String.class))).thenReturn(ps);
		Mockito.when(ps.executeUpdate()).thenReturn(1);
		Mockito.when(ps.executeQuery()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getString(Mockito.anyInt())).thenReturn("1");
		Mockito.when(champions.getChampion(Mockito.anyInt())).thenReturn(teste);
	}
	
	@Test
	void deveriaCriarUmaPartida() throws Exception {
		this.setUp();
		
		CriaChampions c = new CriaChampions();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		Match result = m.criaMatch(blueTeam, redTeam);
		
		Mockito.verify(ps, Mockito.times(1)).executeUpdate();
		assertNotNull(result);
	}

	@Test
	void deveriaRetornarUmaPartida() throws Exception {
		this.setUp();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		CriaChampions c = new CriaChampions();

		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		Match match = m.criaMatch(blueTeam, redTeam);
		
		assertNotNull(m.getMatch(14));
		Mockito.verify(ps, Mockito.times(2)).executeQuery();
	}
	
	@Test
	void deveriaApagarUmaPartida() throws Exception {
		this.setUp();
		CriaChampions c = new CriaChampions();
		ControllerMatch m = new ControllerMatch(conexao, champions);
		
		ArrayList<Champions> blueTeam = c.getBlueTeam();
		ArrayList<Champions> redTeam = c.getRedTeam();
		
		m.criaMatch(blueTeam, redTeam);
		boolean result = m.deleteMatch(1);
		
		assertTrue(result);
	}
}
