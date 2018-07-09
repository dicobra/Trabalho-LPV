package br.com.academia.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.modelo.Cliente;
/**
 * Classe reponsavel por manipular os dados de um
 * Cliente entre o sistema e o banco de dados
 * @author tivin
 *
 */
public class ClienteDAO {

	/**
	 * Verifica se um derterminado cliente ja esta cadastrado no banco de dados
	 * @param cliente cliente a ser consultado
	 * @param con conexao com o banco de dados
	 * @return true caso cliente esteja no banco de dados
	 * false caso contrario
	 */
	public static boolean verificaCliente(Cliente cliente,Connection con){
		try {
			PreparedStatement stmt = con.prepareStatement("select * from cliente where upper(email) = '"
					+cliente.getEmail().toUpperCase()+ "'");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Insere um cliente no banco de dados
	 * @param cliente Cliente a ser inserido
	 * @param con Connection realizada com o banco
	 * @return true caso cliente seja inserido no banco de dados
	 * false caso contrario
	 * @throws SQLException
	 */
	public static boolean insertClient(Cliente cliente,Connection con) throws SQLException{
		if(verificaCliente(cliente, con)){
			return false;
		}else{
			String sql = "insert into cliente (nome,sexo,altura,peso,dtnasc,email) values (?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSexo());
			stmt.setFloat(3, cliente.getAltura());
			stmt.setFloat(4, cliente.getPeso());
			stmt.setString(5, cliente.getDtNasc());
			stmt.setString(6, cliente.getEmail());
			stmt.execute();
			stmt.close();

		}
		return true;
	}//fim insert cliente

	/**
	 * Busca uma lista de clientes a partir de um nome informado
	 * @param nome a ser procurado no banco de dados
	 * @param con Connection realizada com o banco
	 * @return List de Cliente com os clientes que tenham no mesmo
	 * nome ou parte do nome informado
	 */
	public static List<Cliente> buscaCliente(String nome,Connection con){
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from cliente where upper(nome) like '%"
					+nome.toUpperCase()+ "%'");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Cliente cl = new Cliente();
				cl.setAltura(rs.getFloat("altura"));
				cl.setDtNasc(rs.getString("dtNasc"));
				cl.setEmail(rs.getString("email"));
				cl.setNome(rs.getString("nome"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setSexo(rs.getString("sexo"));
				clientes.add(cl);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	return clientes;
	}//fim busca cliente

	/**
	 * busca todos os cliene no banco de dados
	 * @param con Connection realizada com o banco
	 * @return lista com todos os cliente do banco de dados
	 */
	public static List<Cliente> selectCliente(Connection con){
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from cliente");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Cliente cl = new Cliente();
				cl.setAltura(rs.getFloat("altura"));
				cl.setDtNasc(rs.getString("dtNasc"));
				cl.setEmail(rs.getString("email"));
				cl.setNome(rs.getString("nome"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setSexo(rs.getString("sexo"));
				clientes.add(cl);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return clientes;
	}
}
