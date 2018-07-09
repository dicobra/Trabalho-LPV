package br.com.academia.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.academia.modelo.Cliente;
public class ParsingCliente{

	/**
	 * analisa uma string e caso ela contenha so dados de
	 * um cliente fisica retorna um objeto Cliente
	 * @param dados string com dados a serem analisados
	 * @return objeto Cliente 
	 */
	public Cliente parseDadosCliente(String dados) {
		String[] linhas = dados.split("\n");
		List<String> valores = listaDeValores(linhas);
		String campo = new String();
		Cliente cl = new Cliente();
		//atriui ao cliente seu nome
		campo = valores.get(1);
		cl.setNome(campo);
		//atribui sexo ao cliete
		campo = valores.get(2);
		cl.setSexo(campo);
		//atribui altura ao cliente
		campo = valores.get(3);
		campo = campo.replace(",", ".");
		cl.setAltura(Float.parseFloat(campo.substring(0, campo.length()-2)));
		//atribui peso ao cliente
		campo = valores.get(4);
		campo = campo.replace(",", ".");
		cl.setPeso(Float.parseFloat(campo.substring(0,campo.length()-3)));
		//atribui data de nascimento ao cliente
		campo = valores.get(5);
		cl.setDtNasc(campo);
		//atribui e-mail ao cliente
		campo = valores.get(6);
		cl.setEmail(campo);
		return cl;
	}//fim lerdadoscliente
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
				//sb.append(": [\\p{L}\\d.,/’:”'\"@ ]+");
				sb.append(": [\\p{L}\\d._,/@ ]+");
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
