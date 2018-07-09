package br.com.academia.modelo;

/**
 * Classe Cliente, classe responsavel por manipular dados
 * de um cliente no sistema.
 * @author tivin
 *
 */
public class Cliente{
	private String nome,dtNasc,sexo,email;
	private Float peso,altura;
	/**
	 * Ctor padrao apenas para contrução da classe.
	 */
	public Cliente() {
	}

	/*
	 * Metodos para manipulação dos dados na classe
	 */

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Float getAltura() {
		return altura;
	}
	public void setAltura(Float altura) {
		this.altura = altura;
	}
	/*
	 * Fim Metodos para manipulação dos dados na classe
	 */

	/**
	 * Metodo <code>toString</code> retorna uma string com todos os dados da classe.
	 * @return <code>String</code> com todos os dados da classe.
	 */
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", dtNasc=" + dtNasc + ", sexo=" + sexo + ", email=" + email + ", peso=" + peso
				+ ", altura=" + altura + "]";
	}
}
