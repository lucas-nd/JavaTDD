package controller;

import java.util.ArrayList;

import models.Champions;

public class ControllerChampions {
	private ArrayList<Champions> Champions = new ArrayList<Champions>();
	
	public Champions criaChampion(String nome, String funcao) throws Exception{
		if(nome == null || funcao == null) {
			throw new Exception("O nome ou a função do campeão são nulos.");
		}
		if("".equals(nome) || funcao.equals("")) {
			throw new Exception("O nome ou a função do campeão não foram informadas.");
		}
	
		if(Champions.size() > 0) {
			Champions lastChampion = this.getChampion(Champions.size());
			Champions c1 = new Champions(lastChampion.getId()+1, nome, funcao);
			Champions.add(c1);
				
			return c1;
		}else {
			Champions c1 = new Champions(1, nome, funcao);
			Champions.add(c1);
				
			return c1;
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
			
		for(int i = 0; i < Champions.size(); i++){
			if(Champions.get(i).getId() == id) {
				Champions.get(i).setNome(nome);
				Champions.get(i).setFuncao(funcao);
				return true;
			}
		}
			
		throw new Exception("O campeão não existe na base de dados.");
		
	}
	
	public boolean deleteChampion(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		for(int i = 0; i < Champions.size(); i++){
			if(Champions.get(i).getId() == id) {
				Champions.remove(Champions.get(i));
				return true;
			}
		}
			
		throw new Exception("O campeão não existe na base de dados.");
	}
	
	public Champions getChampion(int id) throws Exception{
		if(id <= 0) {
			throw new Exception("O Id é negativo!");
		}
		for(int i = 0; i < Champions.size(); i++){
			if(Champions.get(i).getId() == id) {
				return Champions.get(i);
			}
		}
		throw new Exception("O campeão não existe na base de dados.");
	}

	public ArrayList<Champions> getChampions() {
		return Champions;
	}
}
