package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.ControllerChampions;
import models.Champions;


public class main {

	public static void main(String[] args) throws Exception {
		CriaChampions c = new CriaChampions();
		System.out.println(c.getBlueTeam());
	}
}
