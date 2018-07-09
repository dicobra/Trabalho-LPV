package br.com.academia.modelo;

import java.util.Calendar;
import java.util.Comparator;
/**
 * Classe AtividadesFisica, classe responsavel por manipular dados
 * de uma atividade fisica no sistema. Alem dos metodos padrao
 * Esta classe implementa a interface <code>EntradaRetorno</code>
 * para leitura dos dados completos da classe
 * e retorno desses dados em forma de relatorio
 * @author tivin
 *
 */
public class AtividadeFisica{
	private String nomeEx, tempo, duracao, ritmoMedio, ritmoMax, ritmos;
	private Double  distancia, velMedia, velMax;
	private Long passos, menorElev, maiorElev;
	private Calendar data;
	private int kCal;
	/**
	 * Ctor padrao apenas para contrução da classe.
	 */
	public AtividadeFisica() {

	}

	/*
	 * Metodos para manipulação dos dados na classe
	 */


	public String getRitmoMedio() {
		return ritmoMedio;
	}

	public void setRitmoMedio(String ritmoMedio) {
		this.ritmoMedio = ritmoMedio;
	}

	public String getRitmoMax() {
		return ritmoMax;
	}

	public void setRitmoMax(String ritmoMax) {
		this.ritmoMax = ritmoMax;
	}

	public String getRitmos() {
		return ritmos;
	}

	public void setRitmos(String ritmos) {
		this.ritmos = ritmos;
	}

	public Double getVelMedia() {
		return velMedia;
	}

	public void setVelMedia(Double velMedia) {
		this.velMedia = velMedia;
	}

	public Double getVelMax() {
		return velMax;
	}

	public void setVelMax(Double velMax) {
		this.velMax = velMax;
	}

	public Long getMenorElev() {
		return menorElev;
	}

	public void setMenorElev(Long menorElev) {
		this.menorElev = menorElev;
	}

	public Long getMaiorElev() {
		return maiorElev;
	}

	public void setMaiorElev(Long maiorElev) {
		this.maiorElev = maiorElev;
	}

	public String getNomeEx() {
		return nomeEx;
	}

	public void setNomeEx(String nomeEx) {
		this.nomeEx = nomeEx;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Long getPassos() {
		return passos;
	}

	public void setPassos(Long passos) {
		this.passos = passos;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getkCal() {
		return kCal;
	}

	public void setkCal(int kCal) {
		this.kCal = kCal;
	}




	@Override
	public String toString() {
		return String.format(
				"AtividadeFisica: %s, Tempo: %s, Duracao: %s, Ritmo Medio: %s, Ritmo Max: %s, Ritmos: %s, Distancia: %s, Vel. Media: %s, Vel. Max: %s, Passos: %s, Menor Elev.: %s, Maior Elev.: %s, Data: %s, Kcal: %s",
				nomeEx, tempo, duracao, ritmoMedio, ritmoMax, ritmos, distancia, velMedia, velMax, passos, menorElev,
				maiorElev, data, kCal);
	}

	/**
	 * Classe interna para implementar o metodo sort e ordenar
	 * atividades fisicas por data
	 * @author tivin
	 *
	 */
	public static class OrdenarPorData implements Comparator<AtividadeFisica>{
		@Override
		public int compare(AtividadeFisica af1, AtividadeFisica af2) {
			Calendar data1 = af1.getData(),
				   data2 = af2.getData();
			String tempo1 = af1.getTempo(),
				   tempo2 = af2.getTempo();
			if(data1.compareTo(data2)==0){
				if(tempo1.compareToIgnoreCase(tempo2)<0)
					return -1;
				if(tempo1.compareToIgnoreCase(tempo2)>0)
					return 1;
			}
			return data1.compareTo(data2);
		}
	}//fim classe ordenarpordata

}
