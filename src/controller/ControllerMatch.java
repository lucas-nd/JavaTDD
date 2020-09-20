package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.Conexao;
import models.Champions;
import models.Match;

public class ControllerMatch {
	private ArrayList<Match> Matches;
	
	private Conexao conexao;
	private Connection con;
	private ControllerChampions c;
	
	public ControllerMatch() throws Exception {
		this.Matches = new ArrayList<Match>();
		this.conexao = new Conexao();
		this.con = conexao.AbrirConnection();
		this.c = new ControllerChampions();
	}
	
	public ControllerMatch(Conexao conexao, ControllerChampions c) throws Exception{
		this.Matches = new ArrayList<Match>();
		this.conexao = conexao;
		this.con = conexao.AbrirConnection();
		this.c = c;
	}

	public Match criaMatch(ArrayList<Champions> blueTeam, ArrayList<Champions> redTeam) throws Exception{
		if(blueTeam == null || redTeam == null) {
			throw new Exception("Existe pelo menos um time nulo.");
		}
		
		String sql = "INSERT INTO matches (top_id_blue, jg_id_blue, mid_id_blue, adc_id_blue, supp_id_blue, top_id_red, jg_id_red, mid_id_red, adc_id_red, supp_id_red) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String sql2 = "SELECT MAX(id) from matches";
		
		try{				
			PreparedStatement ps  = this.con.prepareStatement(sql);
				
			ps.setInt(1, blueTeam.get(0).getId());
			ps.setInt(2, blueTeam.get(1).getId());
			ps.setInt(3, blueTeam.get(2).getId());
			ps.setInt(4, blueTeam.get(3).getId());
			ps.setInt(5, blueTeam.get(4).getId());
			
			ps.setInt(6, redTeam.get(0).getId());
			ps.setInt(7, redTeam.get(1).getId());
			ps.setInt(8, redTeam.get(2).getId());
			ps.setInt(9, redTeam.get(3).getId());
			ps.setInt(10, redTeam.get(4).getId());
				
			ps.executeUpdate();
			
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			rs.next();

			Match match = new Match(Integer.parseInt(rs.getString(1)), blueTeam, redTeam);
			Matches.add(match);
			return match;	
		}catch(Exception e) {
			throw new Exception("Existe pelo menos um time incompleto.");
		}
	}
	
	public Match getMatch(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		
		String sql = "SELECT * FROM matches WHERE id = ?";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			ArrayList<Champions> blueTeam = new ArrayList<Champions>();
			ArrayList<Champions> redTeam = new ArrayList<Champions>();
			
			blueTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(2))));
			blueTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(3))));
			blueTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(4))));
			blueTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(5))));
			blueTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(6))));
			
			redTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(7))));
			redTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(8))));
			redTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(9))));
			redTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(10))));
			redTeam.add(this.c.getChampion(Integer.parseInt(rs.getString(11))));
			
			Match m = new Match(Integer.parseInt(rs.getString(1)), blueTeam, redTeam);
			
			return m;
		}catch(Exception e) {
			throw new Exception("A partida não existe.");
		}
	}

	public boolean deleteMatch(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		
		String sql = "DELETE FROM matches WHERE id = ?";
		
		try {		
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			if(ps.executeUpdate() > 0) {
				return true;				
			}else {
				throw new Exception("A partida não existe.");
			}
		}catch(Exception e) {
			throw new Exception("A partida não existe.");
		}
	}

	public ArrayList<Match> getMatches() throws Exception{
		ArrayList<Match> Matches = new ArrayList<Match>();
		
		String sql = "SELECT * FROM matches";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ArrayList<Champions> blueTeam = new ArrayList<Champions>();
				ArrayList<Champions> redTeam = new ArrayList<Champions>();
				
				Champions c1 = c.getChampion(Integer.parseInt(rs.getString(2)));
				blueTeam.add(c1);
				Champions c2 = c.getChampion(Integer.parseInt(rs.getString(3)));
				blueTeam.add(c2);
				Champions c3 = c.getChampion(Integer.parseInt(rs.getString(4)));
				blueTeam.add(c3);
				Champions c4 = c.getChampion(Integer.parseInt(rs.getString(5)));
				blueTeam.add(c4);
				Champions c5 = c.getChampion(Integer.parseInt(rs.getString(6)));
				blueTeam.add(c5);
				
				Champions c6 = c.getChampion(Integer.parseInt(rs.getString(7)));
				redTeam.add(c6);
				Champions c7 = c.getChampion(Integer.parseInt(rs.getString(8)));
				redTeam.add(c7);
				Champions c8 = c.getChampion(Integer.parseInt(rs.getString(9)));
				redTeam.add(c8);
				Champions c9 = c.getChampion(Integer.parseInt(rs.getString(10)));
				redTeam.add(c9);
				Champions c10 = c.getChampion(Integer.parseInt(rs.getString(11)));
				redTeam.add(c10);
				
				Match m = new Match(Integer.parseInt(rs.getString(1)), blueTeam, redTeam);
				Matches.add(m);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return Matches;
	}
}
