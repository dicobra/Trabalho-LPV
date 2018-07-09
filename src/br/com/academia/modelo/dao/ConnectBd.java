package br.com.academia.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe que efetua a conexao com o banco de dados
 * @author tivin
 *
 */
public class ConnectBd {
	/**
	 * Metodo que retorna uma conexao com o banco de dados
	 * @return conexao com o banco null caso ocorra falha
	 */
	public static Connection getConexao() {
		 Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/TpTOo","postgres","aluno");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
