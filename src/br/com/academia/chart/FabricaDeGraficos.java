package br.com.academia.chart;
import java.util.List;

import br.com.academia.modelo.AtividadeFisica;
import br.com.academia.relatorios.Relatorio;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe com metodos responsaveis por gerar graficos
 * apartir de uma lista de exercios consumindo dados
 * gerados pela classe DataSetGraficos
 * @author tivin
 *
 */
public class FabricaDeGraficos {
	/**
	 * Cria uma tela com elementos GUI para exibição do grafico de dados
	 * e um relatorio de dados abaixo
	 * @param nodeGrafico Swing Node contendo o node do grafico a plotado na tela
	 * @param vbDados VBox com os dados do relatorio a ser plotado na tela
	 * @param nomeEx
	 */
	private static void gerarTelaGrafico(Chart grafico,VBox vbDados,String nomeEx){
		VBox vBox = new VBox();
		try {
			Stage primaryStage = new Stage();
			primaryStage.setTitle(nomeEx);
			AnchorPane root = new AnchorPane();
			vBox.getChildren().addAll(grafico,vbDados);
			root.getChildren().add(vBox);
			Scene scene = new Scene(root,800,500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * gera um grafico de duração de exercicios
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de execicio
	 */
	public static void graficoDuracao(List<AtividadeFisica>laf,String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoDuracao(laf);
		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<>(eixoX, eixoY);
		barChart.getData().add(series);
		barChart.setTitle(nomeEx);
		gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}

	/**
	 * gera um grafico com duração de exercicios
	 * @param laf lista de atividade fisica
	 * @param tipo 1 grafico coluna tipo 2 grafico linha
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoDistancia(List<AtividadeFisica>laf, int tipo, String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoDistancias(laf);

		if(tipo==1){
			final CategoryAxis eixoX = new CategoryAxis();
			final NumberAxis eixoY = new NumberAxis();
			BarChart<String, Number> barChart = new BarChart<String, Number>(eixoX,eixoY);
			barChart.setTitle(nomeEx);
			barChart.getData().add(series);
			gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
		}
		if(tipo==2){
			final CategoryAxis eixoX = new CategoryAxis();
			final NumberAxis eixoY = new NumberAxis();
			LineChart<String, Number> lineChart = new LineChart<String, Number>(eixoX,eixoY);
			lineChart.setTitle(nomeEx);
			lineChart.getData().add(series);
			gerarTelaGrafico(lineChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
		}
	}

	/**
	 * gera um grafico com quantidade de calorias perdidas
	 * @param laf lista de atividade fisica
	 * @param tipo 1 grafico coluna tipo 2 grafico linha
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoKcal(List<AtividadeFisica>laf, int tipo, String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoKcal(laf);

		if(tipo==1){
			final CategoryAxis eixoX = new CategoryAxis();
			final NumberAxis eixoY = new NumberAxis();
			BarChart<String, Number> barChart = new BarChart<String, Number>(eixoX,eixoY);
			barChart.setTitle(nomeEx);
			barChart.getData().add(series);
			gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
		}
		if(tipo==2){
			final CategoryAxis eixoX = new CategoryAxis();
			final NumberAxis eixoY = new NumberAxis();
			LineChart<String, Number> lineChart = new LineChart<String, Number>(eixoX,eixoY);
			lineChart.setTitle(nomeEx);
			lineChart.getData().add(series);
			gerarTelaGrafico(lineChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
		}
	}
	/**
	 * gera um grafico com quantidade de passos dados
	 * @param laf lista de atividade fisica
	 * @param tipo 1 grafico coluna tipo 2 grafico linha
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoPassos(List<AtividadeFisica>laf, int tipo, String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoPassos(laf);

		if(tipo==1){
			final CategoryAxis eixoX = new CategoryAxis();
			final NumberAxis eixoY = new NumberAxis();
			BarChart<String, Number> barChart = new BarChart<String, Number>(eixoX,eixoY);
			barChart.setTitle(nomeEx);
			barChart.getData().add(series);
			gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
		}
		if(tipo==2){
			final CategoryAxis eixoX = new CategoryAxis();
			final NumberAxis eixoY = new NumberAxis();
			LineChart<String, Number> lineChart = new LineChart<String, Number>(eixoX,eixoY);
			lineChart.setTitle(nomeEx);
			lineChart.getData().add(series);
			gerarTelaGrafico(lineChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
		}
	}
	/**
	 * gera um grafico com velociade media
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoVelMedia(List<AtividadeFisica>laf,String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoVelocidadeMedia(laf);

		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<>(eixoX, eixoY);
		barChart.getData().add(series);
		barChart.setTitle(nomeEx);
		gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}

	/**
	 * gera um grafico com ritmo medio
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoRitmoMedio(List<AtividadeFisica>laf, String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoRitmoMedio(laf);
		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<String, Number>(eixoX,eixoY);
		barChart.getData().add(series);
		barChart.setTitle(nomeEx);
		gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf),nomeEx);
	}
	/**
	 * gera um grafico com media de calorias perdidas
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoMediaKcalPerdidas(List<AtividadeFisica> laf, String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoMediaKcal(laf);

		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<String, Number>(eixoX,eixoY);
		barChart.getData().add(series);
		barChart.setTitle(nomeEx);
		gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}
	/**
	 * gera um grafico com media de distancia percorrida
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoMediaDistancia(List<AtividadeFisica>laf, String nomeEx) {
		XYChart.Series<String, Number> series = SeriesGraficos.seriesGraficoDistanciaMedia(laf);
		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<String, Number>(eixoX,eixoY);
		barChart.getData().add(series);
		barChart.setTitle(nomeEx);
		gerarTelaGrafico(barChart,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}


}