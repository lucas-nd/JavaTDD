package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	public Connection AbrirConnection() throws Exception {
		String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "snm";
        String url = "jdbc:postgresql://localhost:5435/SummonersRiftDB";

        try {
            Class.forName(driver);
            Connection con = null;

            con = (Connection) DriverManager.getConnection(url, user, senha);

            return con;
        } catch (ClassNotFoundException ex) {
        	throw new Exception("Algum problema com o driver");
        } catch (SQLException e) {
        	throw new Exception("Algum problema com a url, user ou senha");
        }
	}
}
