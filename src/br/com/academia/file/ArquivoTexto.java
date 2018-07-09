package br.com.academia.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
/**
 * Fornece varios metodos para manipular um arquivo texto em disco.
 * <p>
 * @version 0.1
 */
public class ArquivoTexto {
	private Scanner inputScanner; // O conteudo do arquivo texto sera lido usando um objeto Scanner.
	private FileInputStream fileInputStream; // Representa o arquivo texto como um arquivo de bytes.
	private Formatter fileOutputFormatter; // O conteudo do arquivo texto ser� escrito usando um objeto Formatter.

	/**
	 * Abre um arquivo texto armazenado em disco somente para leitura. A escrita nao e permitida.
	 * @param nomeArquivo nome do arquivo a ser aberto.
	 * @throws FileNotFoundException se o nome do arquivo nao for encontrado.
	 */
	  public void abrir(String nomeArquivo) throws FileNotFoundException {
		  // Abre um arquivo de bytes para realizar a entrada de dados.
		  fileInputStream = new FileInputStream(nomeArquivo);

		  // O arquivo sera lido como um arquivo de texto puro usando a classe java.util.Scanner.
		  inputScanner = new Scanner(fileInputStream);
	  }

	  /**
	   * Cria um arquivo texto em disco. Se o arquivo ja existe o seu conteudo sera apagado.
	   * @param nomeArquivo nome do arquivo a ser criado.
	   * @throws FileNotFoundException se o nome do arquivo nao for encontrado.
	   */
	  public void criar(String nomeArquivo) throws FileNotFoundException {
		  // Cria um arquivo texto em disco.
		  fileOutputFormatter = new Formatter(nomeArquivo);
	  }

	  /**
	   * Escreve no arquivo texto o conteudo do objeto <code>String</code> armazenado no
       * parametro conteudo.
	   *
	   * @param conteudo conteudo a ser escrito no arquivo texto.
	   */
	  public void escrever(String conteudo) {
		  // Escreve o conte�do no arquivo texto.
		  fileOutputFormatter.format("%s\n", conteudo);
	  }

	  /**
	   * Le o conteudo completo do arquivo texto.
	   * As excecoes disparadas pelo metodo <code>ler</code> tem o seu ponto de disparo nos
	   * metodos <code>hasNextLine()</code> e <code>nextLine()</code>
	   * da classe <code>java.util.Scanner</code>.
	   * @return um <code>String</code> com o conteudo lido do arquivo texto.
	   * @throws IllegalStateException ocorre se o arquivo estiver fechado.
	   */
	  public String ler() throws IllegalStateException {
		  String conteudo = "";

		  // Le o conteudo completo do arquivo.
		  while (inputScanner.hasNextLine())
			  conteudo += inputScanner.nextLine() + "\n";

		  return conteudo;
	  }

	  /**
	   * Fecha os arquivos que foram criados para manipulaçao do arquivo texto.
	   *
	   * @throws IOException se ocorrer algum erro de E/S ao tentar fechar o arquivo.
	   */
	  public void fechar() throws IOException {
		  if (fileInputStream != null) fileInputStream.close();
		  if (inputScanner != null) inputScanner.close();
		  if (fileOutputFormatter != null) fileOutputFormatter.close();
	  } // fecharArquivo()
} // class ArquivoTexto
