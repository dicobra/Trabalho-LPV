package br.com.academia.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.MetodosAleatorios;
import br.com.academia.modelo.AtividadeFisica;
/**
 * Classe reponsavel por manipular os dados de uma
 * atividade fisica entre o sistema e o banco de dados
 * @author tivin
 *
 */
public class AtividadeDAO {

	/**
	 * Metodo booleano que persiste uma atividade no banco de dados
	 *
	 * @param atividade a ser persistida no banco
	 * @param email email do cliente ao qual a atividade se refere
	 * @param con Connection realizada com o banco
	 * @return <code>true</code>caso usuario seja persistido ou
	 * <code>false<code> caso ocorra algum erro
	 * @throws SQLException
	 */
	public static boolean insertAtivit(AtividadeFisica atividade, String email, Connection con) throws SQLException {
		String sql = (atividade.getVelMedia()!=null) ? "insert into atividade (nomeat,email,dataat,tempo,duracao,distancia,kcal,passos,"
				+ "velocidade_media,velocidade_max,ritmo_medio,ritmo_max,menor_elev,maior_elev,ritmos)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" :
				"insert into atividade (nomeat,email,dataat,tempo,duracao,distancia,kcal,passos) values (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.setString(1, atividade.getNomeEx());
		stmt.setString(2, email);
		stmt.setDate(3,new Date(atividade.getData().getTimeInMillis()));
		stmt.setString(4, atividade.getTempo());
		stmt.setString(5, atividade.getDuracao());
		stmt.setDouble(6, atividade.getDistancia());
		stmt.setInt(7, atividade.getkCal());
		stmt.setLong(8, atividade.getPassos());
		if(atividade.getRitmoMedio()!=null){
			stmt.setDouble(9, atividade.getVelMedia());
			stmt.setDouble(10, atividade.getVelMax());
			stmt.setString(11, atividade.getRitmoMedio());
			stmt.setString(12, atividade.getRitmoMax());
			stmt.setLong(13, atividade.getMenorElev());
			stmt.setLong(14, atividade.getMaiorElev());
			stmt.setString(15, atividade.getRitmos());
		}
		stmt.execute();
		stmt.close();
		return true;
	}//fim insert ativit

	/**
	 * Metodo que busca no banco dedados uma lista de atividades fisicas
	 * referente ao email informado como parametro
	 * @param email filtro para busca no banco de dados
	 * @param con Connection realizada com o banco
	 * @return <code>List</code> de AtividadesFisica
	 */
	public static List<AtividadeFisica> buscaListaAtividade(String email,Connection con){
		List<AtividadeFisica> atividades = new ArrayList<AtividadeFisica>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from atividade where upper(email) = '"
					+email.toUpperCase()+ "'");
			ResultSet rs = stmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while(rs.next()){
				AtividadeFisica at = new AtividadeFisica();
				at.setNomeEx(rs.getString("nomeat"));
				at.setData(MetodosAleatorios.convertStrCalendar(sdf.format(rs.getDate("dataat"))));
				at.setTempo(rs.getString("tempo"));
				at.setDuracao(rs.getString("duracao"));
				at.setDistancia(rs.getDouble("distancia"));
				at.setkCal(rs.getInt("kcal"));
				at.setPassos(rs.getLong("passos"));
				if(rs.getDouble("velocidade_media")!=0){
					at.setRitmoMedio(rs.getString("ritmo_medio"));
					at.setRitmoMax(rs.getString("ritmo_max"));
					at.setRitmos(rs.getString("ritmos"));
					at.setVelMedia(rs.getDouble("velocidade_media"));
					at.setVelMax(rs.getDouble("velocidade_max"));
					at.setMenorElev(rs.getLong("menor_elev"));
					at.setMaiorElev(rs.getLong("maior_elev"));
				}
				atividades.add(at);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return atividades;
	}//fim busca atividade
	/**
	 * Metodo que busca no banco de dados uma lista de atividades fisicas
	 * referente ao email informado como parametro pelo periodo informado pelas datas
	 * @param email filtro para busca no banco de dados
	 * @param con Connection realizada com o banco
	 * @return <code>List</code> de AtividadesFisica
	 */
	public static List<AtividadeFisica> buscaListaAtividadeData(String email,String dataIn,String dataFim,Connection con){
		List<AtividadeFisica> atividades = new ArrayList<AtividadeFisica>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from atividade where "
					+ "email = '"+email+"' and dataat between '"+dataIn+"' and '"+dataFim+"'");

			ResultSet rs = stmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while(rs.next()){
				AtividadeFisica at = new AtividadeFisica();
				at.setNomeEx(rs.getString("nomeat"));
				at.setData(MetodosAleatorios.convertStrCalendar(sdf.format(rs.getDate("dataat"))));
				at.setTempo(rs.getString("tempo"));
				at.setDuracao(rs.getString("duracao"));
				at.setDistancia(rs.getDouble("distancia"));
				at.setkCal(rs.getInt("kcal"));
				at.setPassos(rs.getLong("passos"));
				if(rs.getDouble("velocidade_media")!=0){
					at.setRitmoMedio(rs.getString("ritmo_medio"));
					at.setRitmoMax(rs.getString("ritmo_max"));
					at.setRitmos(rs.getString("ritmos"));
					at.setVelMedia(rs.getDouble("velocidade_media"));
					at.setVelMax(rs.getDouble("velocidade_max"));
					at.setMenorElev(rs.getLong("menor_elev"));
					at.setMaiorElev(rs.getLong("maior_elev"));
				}
				atividades.add(at);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return atividades;
	}//fim busca atividadedata
}
