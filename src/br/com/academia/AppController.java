package br.com.academia;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.academia.chart.FabricaDeGraficos;
import br.com.academia.file.ArquivoTexto;
import br.com.academia.modelo.AtividadeFisica;
import br.com.academia.modelo.Cliente;
import br.com.academia.modelo.dao.AtividadeDAO;
import br.com.academia.modelo.dao.ClienteDAO;
import br.com.academia.modelo.dao.ConnectBd;
import br.com.academia.parsing.ParsingAtividade;
import br.com.academia.parsing.ParsingCliente;
import br.com.academia.relatorios.Relatorio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class AppController {

	public Connection con = ConnectBd.getConexao();
	private List<AtividadeFisica> ativis = new ArrayList<>();

	@FXML
	private ComboBox<String> cbAtividades;
	@FXML
	private RadioButton rbTodasDatas, rbData, rbRitmoMedio, rbPassos, rbDistanciaPercorrida,
						rbDuracaoEx, rbKcalPerdidas, rbVelMedia, rbDistanciaMedia, rbMediaKcal, rbCollun, rbLine;
    @FXML
    private Label lbAltura, lbSexo, lbDtNas, lbPeso, lbNome, lbEmail, lbMaiorDuracao ,lbDist,
    			  lbKcal, lbVelMax, lbNPassos;

    @FXML
    private DatePicker dpInicio,dpFim;

    @FXML Pagination pgTabela;

    @FXML private TableColumn<Cliente, String> tcNome;
    @FXML private TableColumn<Cliente, String> tcDtNasc;
    @FXML private TableColumn<Cliente, String> tcSexo;
    @FXML private TableColumn<Cliente, String> tcEmail;
    @FXML private TableColumn<Cliente, Float> tcPeso;
    @FXML private TableColumn<Cliente, Float> tcAltura;
    @FXML private TableView<Cliente> tvClientes;

	@FXML public TextField tfNome;
	@FXML public TextArea txRelatorio;

	@FXML
	private void initialize(){
		//atribui a cada coluna o campo ela ira buscar no objeto informado
		this.tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		this.tcDtNasc.setCellValueFactory(new PropertyValueFactory<>("dtNasc"));
		this.tcSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
		this.tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		this.tcPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
		this.tcAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		this.tvClientes.setEditable(false);
		pgTabela.setPageCount(numeroPaginas());
		pgTabela.setPageFactory((Integer pageIndex) -> createPage(pageIndex));

	}

	private int numeroPaginas(){
		return ClienteDAO.buscaCliente(this.tfNome.getText(),con).size()/4+1;
	}

	/**
	 * Metodo void para popular com os dados recuperados do banco de dados
	 * a tabela de clientes durante uma pesquisa
	 */
/*	private TableView<Cliente> populaTabelaCliente(int fromIndex, int toIndex){
		//atribiu a tabela de cliente que ela sera editavel
		this.tvClientes.setEditable(true);
		//popula a tabela com dados buscados no banco a partir do metodo buscaCliente
		this.tvClientes.setItems(FXCollections.observableList(ClienteDAO.buscaCliente("",con)));
		this.tvClientes.setEditable(false);
		return this.tvClientes;
	}//metodo popula tabela
*/
	private Node createPage(int pageIndex){
		int fromIndex =pageIndex * 4;
		int toIndex = Math.min(fromIndex +4, tamanhodaLista());
		this.tvClientes.setItems(FXCollections.observableList(ClienteDAO.buscaCliente(this.tfNome.getText(),con).subList(fromIndex, toIndex)));
		return this.tvClientes;
	}//create page

	private int tamanhodaLista(){
		return ClienteDAO.buscaCliente(this.tfNome.getText(),con).size();
	}

	/**
	 * Metodo void que exibe em um <code>TextView</code> os dados
	 * do cliente selecionado na <code>TableView</code> de clientes
	 */
	@FXML
	public void relatorioSelected(){
		this.cbAtividades.setItems(FXCollections.observableList(new ArrayList<String>()));

		Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
		this.txRelatorio.setText("");
		this.lbMaiorDuracao.setText("");
		this.lbDist.setText("");
		this.lbKcal.setText("");
		this.lbNPassos.setText("");
		this.lbVelMax.setText("");

		if(cliente!=null){
			setDadosCliente(cliente);

			if(this.rbData.isSelected()){
				LocalDate dtIn = this.dpInicio.getValue(),
						dtFim = this.dpFim.getValue();
				if(dtFim !=null || dtIn!=null)
					ativis = AtividadeDAO.buscaListaAtividadeData(cliente.getEmail(), dtIn.toString(),
					dtFim.toString(), con);
			}else
				ativis = AtividadeDAO.buscaListaAtividade(cliente.getEmail(), con);
			ativis.sort(new AtividadeFisica.OrdenarPorData());
			if(!ativis.isEmpty()){
				setRelatorioAtividades(ativis);
				for (AtividadeFisica atividadesFisica : ativis) {
					this.txRelatorio.appendText(Relatorio.relatorioafc(atividadesFisica));
				}
				cbAtividades.setItems(FXCollections.observableList(MetodosAleatorios.listaNomesDeAtividades(ativis)));
				cbAtividades.setPromptText("Escolha a Atividade");
				this.txRelatorio.setEditable(false);
			}else
				this.txRelatorio.setText("Nenhum exercicio encontrado nessa data!!!");
		}//cliente not null
	}//getrelaorioselected


	private void setRelatorioAtividades(List<AtividadeFisica> ativis) {
		this.lbMaiorDuracao.setText(Relatorio.maiorDuracaoExercicio(ativis));
		this.lbDist.setText(Relatorio.maiorDistanciaPercorrida(ativis));
		this.lbKcal.setText(Relatorio.maiorKcal(ativis));
		this.lbNPassos.setText(Relatorio.maiorNPassos(ativis));
		this.lbVelMax.setText(Relatorio.maiorVel(ativis));
	}

	private void setDadosCliente(Cliente cliente) {
		this.lbNome.setText(cliente.getNome());
		this.lbAltura.setText(cliente.getAltura().toString());
		this.lbDtNas.setText(cliente.getDtNasc());
		this.lbEmail.setText(cliente.getEmail());
		this.lbPeso.setText(cliente.getPeso().toString());
		this.lbSexo.setText(cliente.getSexo());
	}

	/**
	 * Metodo void que importa multiplos arquivos
	 * para o sistema e armazena os dados do arquivo
	 * no sistema
	 * @param AtividadeFisica
	 */
	@FXML
	public void importFiles(){
		/*JFileChooser chooser = new JFileChooser();
	    // Possibilita a seleção de vários arquivos
	    chooser.setMultiSelectionEnabled(true);
	    // Apresenta a caixa de diálogo
	    chooser.showOpenDialog(null);
	    // Retorna os arquivos selecionados. Este método retorna vazio se
	    // o modo de múltipla seleção de arquivos não estiver ativada.
	    File[] files = chooser.getSelectedFiles();*/
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Importar arquivos");
		List<File> files = chooser.showOpenMultipleDialog(null);
		if(files!=null && !files.isEmpty())
		    for (File file : files) {
		    	ArquivoTexto ar = new ArquivoTexto();
		    	try {
					ar.abrir(file.getAbsolutePath());
					String dados = ar.ler();
					Cliente cl = new ParsingCliente().parseDadosCliente(dados);
					AtividadeFisica af = new ParsingAtividade().parseDadosAF(dados);

					if(cl!=null && af!=null){
						ClienteDAO.insertClient(cl,con);
						AtividadeDAO.insertAtivit(af, cl.getEmail(),con);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Arquivo "+ file.getAbsolutePath() +" invalido ou ja inserido!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}

		    }
	}//importfile

	@FXML
	public void radioButtonbuscaPeriodo(){
		this.rbTodasDatas.setSelected(false);
		this.dpInicio.setDisable(false);
		this.dpFim.setDisable(false);
	}

	@FXML
	public void radioButtonbuscaTodasDatas(){
		this.rbData.setSelected(false);
		this.dpInicio.setDisable(true);
		this.dpFim.setDisable(true);
	}

	@FXML
	public void visualisarGrafico(){
		String graficoEscolhido = cbAtividades.getValue();
		if(graficoEscolhido!=null){
			if(graficoEscolhido.compareToIgnoreCase("Todos os Exercicios")==0){
				if(rbDuracaoEx.isSelected())
					FabricaDeGraficos.graficoDuracao(ativis,graficoEscolhido);
				if(rbDistanciaPercorrida.isSelected()){
					if(rbCollun.isSelected())
						FabricaDeGraficos.graficoDistancia(ativis, 1,graficoEscolhido);
					else
						FabricaDeGraficos.graficoDistancia(ativis, 2,graficoEscolhido);
				}
				if(rbKcalPerdidas.isSelected()){
					if(rbCollun.isSelected())
						FabricaDeGraficos.graficoKcal(ativis, 1,graficoEscolhido);
					else
						FabricaDeGraficos.graficoKcal(ativis, 2,graficoEscolhido);
				}
				if(rbPassos.isSelected()){
					if(rbCollun.isSelected())
						FabricaDeGraficos.graficoPassos(ativis, 1,graficoEscolhido);
					else
						FabricaDeGraficos.graficoPassos(ativis, 2,graficoEscolhido);
				}
				if(rbVelMedia.isSelected())
					FabricaDeGraficos.graficoVelMedia(ativis,graficoEscolhido);
				if(rbRitmoMedio.isSelected())
					FabricaDeGraficos.graficoRitmoMedio(ativis,graficoEscolhido);
				if(rbMediaKcal.isSelected())
					FabricaDeGraficos.graficoMediaKcalPerdidas(ativis,graficoEscolhido);
				if(rbDistanciaMedia.isSelected())
					FabricaDeGraficos.graficoMediaDistancia(ativis,graficoEscolhido);
			}else{
				List<AtividadeFisica>listaUnicaAtividade = MetodosAleatorios.listaAtiviNome(ativis, graficoEscolhido);
				if(rbDuracaoEx.isSelected())
					FabricaDeGraficos.graficoDuracao(listaUnicaAtividade,graficoEscolhido);
				if(rbDistanciaPercorrida.isSelected()){
					if(rbCollun.isSelected())
						FabricaDeGraficos.graficoDistancia(listaUnicaAtividade, 1,graficoEscolhido);
					else
						FabricaDeGraficos.graficoDistancia(listaUnicaAtividade, 2,graficoEscolhido);
				}
				if(rbKcalPerdidas.isSelected()){
					if(rbCollun.isSelected())
						FabricaDeGraficos.graficoKcal(listaUnicaAtividade, 1,graficoEscolhido);
					else
						FabricaDeGraficos.graficoKcal(listaUnicaAtividade, 2,graficoEscolhido);
				}
				if(rbPassos.isSelected()){
					if(rbCollun.isSelected())
						FabricaDeGraficos.graficoPassos(listaUnicaAtividade, 1,graficoEscolhido);
					else
					FabricaDeGraficos.graficoPassos(listaUnicaAtividade, 2,graficoEscolhido);
				}
				if(rbVelMedia.isSelected())
					FabricaDeGraficos.graficoVelMedia(listaUnicaAtividade,graficoEscolhido);
				if(rbRitmoMedio.isSelected())
					FabricaDeGraficos.graficoRitmoMedio(listaUnicaAtividade,graficoEscolhido);
				if(rbMediaKcal.isSelected())
					FabricaDeGraficos.graficoMediaKcalPerdidas(listaUnicaAtividade,graficoEscolhido);
				if(rbDistanciaMedia.isSelected())
					FabricaDeGraficos.graficoMediaDistancia(listaUnicaAtividade,graficoEscolhido);
			}
		}
	}
	@FXML
	public void activeRbTipo(){
		rbCollun.setDisable(false);
		rbLine.setDisable(false);
	}
	@FXML
	public void desactiveRbTipo(){
		rbCollun.setDisable(true);
		rbLine.setDisable(true);
	}

	@FXML
	public void escolhaGrafico(){

		String atividadeEscolhida = cbAtividades.getValue();
		if(atividadeEscolhida!=null){
			if(atividadeEscolhida.compareToIgnoreCase("todos os exercicios")==0 && MetodosAleatorios.verificaTodosExercicios(ativis)){
				rbRitmoMedio.setDisable(false);
				rbVelMedia.setDisable(false);
			}else{
				rbRitmoMedio.setDisable(true);
				rbVelMedia.setDisable(true);
				for (AtividadeFisica af : ativis) {
					if(atividadeEscolhida.compareToIgnoreCase(af.getNomeEx())==0){
						if(af.getRitmoMax()==null||af.getRitmoMax().compareToIgnoreCase("")==0){
							rbRitmoMedio.setDisable(true);
							rbVelMedia.setDisable(true);
							rbDuracaoEx.setSelected(true);
						}else{
							rbRitmoMedio.setDisable(false);
							rbVelMedia.setDisable(false);
						}
					}//comparaatividade
				}//for atividade
			}//else exercicio
		}//ef exercicio
	}//fim metodo escolha grafico




}//class appcontroler
