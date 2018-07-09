package br.com.academia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.academia.modelo.AtividadeFisica;

public class MetodosAleatorios {

	/**
	 * Converte uma lista de atividades fisicas em uma lista de
	 * um unico tipo de atividade fisica
	 * @param laf lista com todas as atividades fisicas a verificar
	 * @param nomeEx nome to tipo de atividade que se deseja a nova lista
	 * @return uma lista de atividades fisicas apenas com as atividades
	 * do tipo informado no parametro
	 */
	public static List<AtividadeFisica> listaAtiviNome(List<AtividadeFisica>laf,String nomeEx){
		List<AtividadeFisica> listaUnicaAtividade = new ArrayList<AtividadeFisica>();
		for (AtividadeFisica af : laf) {
			if(nomeEx.compareToIgnoreCase(af.getNomeEx())==0)
				if(!buscaAtividadeNome(listaUnicaAtividade,af.getNomeEx()))
					listaUnicaAtividade.add(af);
		}
		for (AtividadeFisica atividadeFisica : listaUnicaAtividade) {
			System.out.println("atvt : "+atividadeFisica.getNomeEx());
		}
		return listaUnicaAtividade;
	}

	/**
	 * Busca uma atividade fisica em uma lista de atividades
	 * @param laf lista de atividades fisicas
	 * @param nomeEx nome da atividade fisica a ser verificada
	 * @return true caso encontre false caso contrario
	 */
	private static boolean buscaAtividadeNome(List<AtividadeFisica> laf, String nomeEx) {
		for (AtividadeFisica af : laf) {
			if (nomeEx.compareToIgnoreCase(af.getNomeEx())==0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Converte uma string data para uma data do tipo Calendar
	 * @param data string da data a ser convertida
	 * @return Um Calendar contendo a data informada no parametro
	 */
	public static Calendar convertStrCalendar(String data){
		Calendar dt = Calendar.getInstance();
		if(data.indexOf("/")!=-1)
			dt.set(Integer.parseInt(data.split("/")[2]),
					Integer.parseInt(data.split("/")[1])-1,
					Integer.parseInt(data.split("/")[0]));
		if(data.indexOf("-")!=-1)
			dt.set(Integer.parseInt(data.split("-")[2]),
					Integer.parseInt(data.split("-")[1])-1,
					Integer.parseInt(data.split("-")[0]));
		return dt;
	}



	/**
	 * Verifica Se uma determinada data esta existe em uma lista de datas
	 * @param datas lista de datas a ser percorrida
	 * @param data data a ser verificada
	 * @return true caso encontre false caso contrario
	 */
	public static boolean buscaData(List<Calendar> datas, Calendar data){
		SimpleDateFormat sdfdata = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfdate = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar date : datas) {
			if(sdfdata.format(data.getTime()).compareToIgnoreCase(sdfdate.format(date.getTime()))==0){
				return true;
			}
		}
		return false;
	}

	/**
	 * Converte uma lista de atividade fisica em uma lista de Calendar
	 * com todas as datas existentes na lista de atividades fisicas
	 * @param laf lista de atividades fisicas
	 * @return lista de calendar com todas as datas contidadas na lista de atividades
	 */
	public static List<Calendar> todasDatas(List<AtividadeFisica> laf){
		List<Calendar> datas = new ArrayList<>();
		for (AtividadeFisica af : laf) {
			if(!buscaData(datas,af.getData())){
				datas.add(af.getData());
			}
		}
		return datas;
	}


	/**
	 * a partir de uma lista de atividades fisicas retorna uma nova lista
	 * de ativiade fisica apenas com atividades que contenham todos os
	 * campos de uma atividade preenchidos.
	 * @param laf lista de atividade fisica
	 * @return lista de atividade fisica apenas com atividades que tenham
	 * todos os seu dados preenchidos
	 */
	public static List<AtividadeFisica> listaAtividadeComplexa(List<AtividadeFisica> laf){
		List<AtividadeFisica> lafc = new ArrayList<AtividadeFisica>();
		for (AtividadeFisica atividadeFisica : laf) {
			if(atividadeFisica.getRitmoMedio()!=null)
				lafc.add(atividadeFisica);
		}
		return lafc;
	}

	/**
	 * a partir de uma lista de atividade fisica retorna uma lista
	 * de string com o note de todas as atividades contidas
	 * @param laf lista de ativiade fisica
	 * @return lista de string com o nome de todas as atividades da lista de atividade fisica
	 */
	public static List<String> listaNomesDeAtividades(List<AtividadeFisica> laf){
		List<String> listaNomesAtividades = new ArrayList<>();
		listaNomesAtividades.add("Todos os Exercicios");
		for (AtividadeFisica af : laf) {
			if(!buscaNomeAtividade(listaNomesAtividades, af.getNomeEx()))
				listaNomesAtividades.add(af.getNomeEx());
		}
		return listaNomesAtividades;
	}

	/**
	 * verifica se um nome existe em uma list de nome do tipo string
	 * @param listaNomes do tipo string
	 * @param nomeAtividade nome a ser pesquisado
	 * @return true caso encontre o nome false caso contrario
	 */
	public static boolean buscaNomeAtividade(List<String>listaNomes, String nomeAtividade) {
		for (String nomeEx : listaNomes) {
			if(nomeAtividade.compareToIgnoreCase(nomeEx)==0)
				return true;
		}
		return false;
	}
	/**
	 * Verifica se ha pelo menos um exercicio completo na lista caso teha
	 * retorna true caso contrario false
	 * @param laf
	 * @return
	 */
	public static boolean verificaTodosExercicios(List<AtividadeFisica> laf){
		for (AtividadeFisica af : laf) {
			if(af.getRitmos()!=null){
				return true;
			}
		}
		return false;
	}//fim verifica todos exercicios


}
