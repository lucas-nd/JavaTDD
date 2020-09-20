package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ControllerChampions;
import controller.ControllerMatch;
import models.Champions;
import models.Match;

public class StartView {
	ControllerChampions c;
	ControllerMatch m = new ControllerMatch();
	
	public StartView() throws Exception {
		this.c = new ControllerChampions();
	}
	
	public void ListarChamps() throws Exception {
		ArrayList<Champions> Champions = this.c.getChampions();
		
		System.out.println("Lista de Campeões:");
		
		for(int i = 0; i < Champions.size(); i++) {
			System.out.println("-------------------------------");
			System.out.println("ID:" + Champions.get(i).getId());
			System.out.println("Nome:" + Champions.get(i).getNome());
			System.out.println("Função:" + Champions.get(i).getFuncao());
		}
	}
	
	public void AdicionarChamp() throws Exception{		
		try {
			Scanner s1 = new Scanner(System.in);
			Scanner s2 = new Scanner(System.in);
			
			System.out.println("Informe os dados para o novo campeão:");
			System.out.println("Nome:");
			String nome = s1.nextLine();
			System.out.println("Função:");
			String funcao = s2.nextLine();
			
			this.c.criaChampion(nome, funcao);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void EditarChamp() throws Exception{
		try {
			Scanner s1 = new Scanner(System.in);
			Scanner s2 = new Scanner(System.in);
			Scanner s3 = new Scanner(System.in);
			
			System.out.println("Informe os dados para mudar no campeão:");
			System.out.println("Id:");
			int id = s3.nextInt();
			System.out.println("Nome:");
			String nome = s1.nextLine();
			System.out.println("Função:");
			String funcao = s2.nextLine();
			
			c.editarChampion(id, nome, funcao);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ApagarChamp() throws Exception{
		try {
			Scanner s3 = new Scanner(System.in);
			
			System.out.println("Informe qual o ID do campeão que será apagado:");
			int id = s3.nextInt();
			
			c.deleteChampion(id);			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ListarMatches() throws Exception {
		ArrayList<Match> Matches = m.getMatches();
		
		System.out.println("-------------------------------");
		System.out.println("Lista de Partidas:");
		
		for(int i = 0; i < Matches.size(); i++) {
			System.out.println("ID:" + Matches.get(i).getId());
			System.out.println("Time azul:");
			for(int j = 0; j < Matches.get(i).getBlueTeam().size(); j++) {
				System.out.println("Player"+ (j+1) + ": " + Matches.get(i).getBlueTeam().get(j).getNome());
			}
			System.out.println("Time Vermelho:");
			for(int j = 0; j < Matches.get(i).getRedTeam().size(); j++) {
				System.out.println("Player"+ (j+1) + ": " + Matches.get(i).getRedTeam().get(j).getNome());
			}
		}
	}
	
	public void AdicionarMatch() throws Exception{
		try {
			ArrayList<Champions> blueTeam = new ArrayList<Champions>();
			ArrayList<Champions> redTeam = new ArrayList<Champions>();
			
			Scanner s1 = new Scanner(System.in);
			
			System.out.println("Vamos montar o time azul");
			for(int i = 0; i < 5; i++) {
				System.out.println("Informe o ID do " + (i +1) + " campeão: ");
				int id = s1.nextInt();
				blueTeam.add(c.getChampion(id));
			}
			
			System.out.println("Vamos montar o time vermelho");
			for(int i = 0; i < 5; i++) {
				System.out.println("Informe o ID do " + (i +1) + " campeão: ");
				int id = s1.nextInt();
				redTeam.add(c.getChampion(id));
			}
			
			m.criaMatch(blueTeam, redTeam);		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ApagarMatch() throws Exception{
		try {
			Scanner s3 = new Scanner(System.in);
			
			System.out.println("Informe qual o ID da partida que será apagada:");
			int id = s3.nextInt();
			
			m.deleteMatch(id);
						
		}catch(Exception e) {
			System.out.println("Os dados foram informados de forma incorreta, tente novamente.");
		}
	}
	
	public void Start() throws Exception {
		System.out.println("Olá, seja bem vindo a central Summoners Rift!");
		System.out.println("Você já pode começar a fazer algo!\n");
		System.out.println("Opções relacionadas aos campeões:");
		System.out.println("1) Listar campeões");
		System.out.println("2) Criar campeões");
		System.out.println("3) Editar campeões");
		System.out.println("4) Apagar campeões\n");
		System.out.println("Opções relacionadas as partidas:");
		System.out.println("5) Listar partidas");
		System.out.println("6) Criar partidas");
		System.out.println("7) Apagar Partidas");
		
		Scanner s = new Scanner(System.in);
		
		while(true) {
				System.out.println("Digite sua opção:");
				int op = s.nextInt();
				
				if(op == 1) {
					ListarChamps();
				}else if(op == 2) {
					AdicionarChamp();
				}else if(op == 3) {
					EditarChamp();
				}else if(op == 4) {
					ApagarChamp();
				}else if(op == 5) {
					ListarMatches();
				}else if(op == 6) {
					AdicionarMatch();
				}else if(op == 7) {
					ApagarMatch();
				}else {
					break;
				}
		}			
	}
}
