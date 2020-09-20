package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Conexao;
import models.Champions;

public class ControllerChampions {	
	private Conexao conexao;
	private Connection con;
	
	public ControllerChampions() throws Exception {
		this.conexao = new Conexao();
		this.con = conexao.AbrirConnection();
	}
	
	public ControllerChampions(Conexao conexao) throws Exception{
		this.conexao = conexao;
		this.con = conexao.AbrirConnection();
	}
	
	public Champions criaChampion(String nome, String funcao) throws Exception{
		if(nome == null || funcao == null) {
			throw new Exception("O nome ou a função do campeão são nulos.");
		}
		if("".equals(nome) || funcao.equals("")) {
			throw new Exception("O nome ou a função do campeão não foram informadas.");
		}
		
		String sql = "INSERT INTO champions (nome, funcao) VALUES (?, ?)";
		
		String sql2 = "SELECT MAX(id) from champions";

		try{
			PreparedStatement ps  = this.con.prepareStatement(sql);
				
			ps.setString(1, nome);
			ps.setString(2, funcao);
			
			int result = ps.executeUpdate();
			
			PreparedStatement ps2 = this.con.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			rs.next();
			
			Champions c1 = new Champions(Integer.parseInt(rs.getString(1)), nome, funcao);
						
			if(result > 0){
                return c1;
            }else{
            	throw new Exception("Erro ao inserir no banco de dados.");
            }
		}catch(Exception e) {
			throw new Exception("Erro ao inserir no banco de dados.");
		}
	}
	
	public boolean editarChampion(int id, String nome, String funcao) throws Exception{
		if(nome == null || funcao == null) {
			throw new Exception("O nome ou a função do campeão são nulos.");
		}
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		if(nome.equals("") || funcao.equals("")) {
			throw new Exception("O nome ou a função do campeão não foram informadas.");
		}
		
		String sql = "UPDATE champions SET nome = ?, funcao =? WHERE id = ?";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			
			ps.setInt(3, id);
			ps.setString(1, nome);
			ps.setString(2, funcao);
			
			int result = ps.executeUpdate();
			
			if(result > 0){
				return true;
            }else{
            	throw new Exception("O campeão não existe na base de dados.");
            }
		}catch(Exception e) {
			throw new Exception("O campeão não existe na base de dados.");
		}
	}

	public boolean deleteChampion(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		
		String sql = "DELETE FROM champions WHERE id = ?";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			
			if(ps.executeUpdate() > 0){
				return true;
            }else{
            	throw new Exception("O campeão não existe na base de dados.");
            }
		}catch(Exception e) {
			throw new Exception("O campeão não existe na base de dados.");
		}
	}

	public Champions getChampion(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		
		String sql = "SELECT * FROM champions WHERE id = ?";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			if(rs != null) {
				Champions c1 = new Champions(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3));
				
				return c1;
			}
			
			throw new Exception("O campeão não existe na base de dados.");
		}catch(Exception e) {
			throw new Exception("O campeão não existe na base de dados.");
		}
	}

	public ArrayList<Champions> getChampions() throws Exception{
		ArrayList<Champions> Champions = new ArrayList<Champions>();
		
		String sql = "SELECT * FROM champions";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Champions c1 = new Champions(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3));
				Champions.add(c1);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return Champions;
	}
}
