package br.com.academia.parsing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.academia.modelo.AtividadeFisica;

public class ParsingAtividade {

	/**
	 * analisa uma string e caso ela contenha so dados de
	 * uma atividade fisica retorna um objeto atividade Fisica	 * 
	 * @param dados string com dados a serem analisados
	 * @return objeto atividade fisica
	 */
	public AtividadeFisica parseDadosAF(String dados) {
		String[] linhas = dados.split("\n");
		List<String> valores = listaDeValores(linhas);
		String campo = new String();
		AtividadeFisica af = new AtividadeFisica();
		//atribui nome ao exercicio executado
		campo = valores.get(0);
		af.setNomeEx(campo);
		//atribui data ao exercicio
		campo = valores.get(7);
		Calendar dataEx = Calendar.getInstance();
		dataEx.set(Integer.parseInt(campo.split("/")[2]), Integer.parseInt(campo.split("/")[1])-1,
					Integer.parseInt(campo.split("/")[0]));
		af.setData(dataEx);
		//atribui tempo ao exercicio
		campo = valores.get(8);
		af.setTempo(campo);
		//atribui duração ao exercicio
		campo = valores.get(9);
		af.setDuracao(campo);
		//atribui distancia ao exercicio
		campo = valores.get(10);
		campo = campo.replace(".", "").replace(",", ".");
		af.setDistancia(Double.parseDouble(campo.substring(0, campo.length()-3)));
		//atribui calorias perdidas no exercicio
		campo = valores.get(11);
		campo = campo.replace(".", "");
		af.setkCal(Integer.parseInt(campo.substring(0,campo.length()-5)));
		//atribui quantos passos foram dados no exericio
		campo = valores.get(12);
		campo = campo.replace(".", "");
		af.setPassos(Long.parseLong(campo));
		if(dados.indexOf("Ritmo")!=-1){
			//atribui velocidade media ao exercicio executado
			campo = valores.get(13);
			campo = campo.replace(".", "").replace(",", ".");
			af.setVelMedia(Double.parseDouble(campo.substring(0, campo.length()-5)));
			//atribui velocidade maxima ao exercicio executado
			campo = valores.get(14);
			campo = campo.replace(".", "").replace(",", ".");
			af.setVelMax(Double.parseDouble(campo.substring(0, campo.length()-5)));
			//atribui ritmo medio ao exercicio
			campo = valores.get(15);
			af.setRitmoMedio(campo.substring(0,campo.length()-4));
			//atribui ritmo medio ao exercicio
			campo = valores.get(16);
			af.setRitmoMax(campo.substring(0,campo.length()-4));
			//atribui menor elevaçao ao exercicio
			campo = valores.get(17);
			campo = campo.replace(".", "");
			af.setMenorElev(Long.parseLong(campo.substring(0, campo.length()-2)));
			//atribui maior elevaçao ao exercicio
			campo = valores.get(18);
			campo = campo.replace(".", "");
			af.setMaiorElev(Long.parseLong(campo.substring(0, campo.length()-2)));
			//atribiu todos os ritmos ao exercicio
			campo = dados.substring(dados.indexOf("1 Km: "),dados.length());
			af.setRitmos(campo);
		}
		return af;
	}

	/**
	 * recebe um array de linhas e apartir dos valores lidos
	 * retorna um list de string com os valores lidos em cada
	 * linha pelo padrao informado na expressao regular
	 * @param linhas
	 * @return
	 */
	public static List<String> listaDeValores(String[] linhas){
		List<String>valores = new ArrayList<String>();
		Pattern pattern;
		String campo = new String();
		for (String string : linhas) {
			if(!string.isEmpty()){
				campo = string.split(": ")[0];
				StringBuilder sb = new StringBuilder(campo.toString());
				sb.append(": [\\p{L}\\d.,/’:”'\"@\\- ]+");
				pattern = Pattern.compile(sb.toString());
				Matcher matcher = pattern.matcher(string);
				while (matcher.find()) {
					valores.add(matcher.group().split(": ")[1]);
				}
			}
		}
		return valores;
	}//fim listadevalores
}
