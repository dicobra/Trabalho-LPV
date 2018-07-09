package br.com.academia.chart;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.academia.MetodosAleatorios;
import br.com.academia.modelo.AtividadeFisica;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
/**
 * Classe responsavel por gerar series para serem consumidos
 * pelos metodos da classe FabricaDeGraficos
 * @author tivin
 *
 */
public class SeriesGraficos {

	/**
	 * a partir de uma lista de atividade fisica atribui valores a um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoDuracao(List<AtividadeFisica> laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		int hr,minuto;
		double segundo;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			hr=0;
			minuto=0;
			segundo=0D;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					hr += Integer.parseInt(af.getDuracao().split(":")[0]);
					minuto += Integer.parseInt(af.getDuracao().split(":")[1]);

					StringBuilder seg = new StringBuilder("0.");
					seg.append(af.getDuracao().split(":")[2]);
					segundo += Double.parseDouble(seg.toString());
					System.out.println(hr);
					System.out.println(minuto);
					System.out.println(segundo);
				}
			}
			Double tempoTotal = new Double(hr*60);
			tempoTotal+=minuto;
			tempoTotal+=segundo;
			series.getData().add(new Data<String, Number>(sdf.format(calendar.getTime()),tempoTotal));
		}
		series.setName("Duração");
		return series;
	}//fim graficoduração

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param tipo tipo de grafico a sser impresso 1 coluna 2 linha
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoDistancias(List<AtividadeFisica> laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Double distancia;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			distancia=0D;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0)
					distancia+=af.getDistancia();
			}
			series.getData().add(new Data<String, Number>(sdf.format(calendar.getTime()),distancia));
		}
		series.setName("Distancia Percorrida");
		return series;
	}//fim distanciaGrafico

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param tipo tipo de grafico a sser impresso 1 coluna 2 linha
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoKcal (List<AtividadeFisica> laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Integer kCal;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			kCal=0;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0)
					kCal+=af.getkCal();
			}
			series.getData().add(new Data<String, Number>(sdf.format(calendar.getTime()),kCal));
		}
		series.setName("Kcal perdidas");
		return series;
	}//fim kCalGrafico

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @param tipo tipo de grafico a sser impresso 1 coluna 2 linha
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoPassos(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Long passos = 0l;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			passos=0l;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					passos = Long.sum(passos, af.getPassos());
				}
			}
			series.getData().add(new Data<String, Number>(sdf.format(calendar.getTime()),passos));
		}
		series.setName("Passos Dados");
		return series;
	}//fim passosGrafico
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoVelocidadeMedia(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Double velMedia;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			velMedia=0d;
			List<AtividadeFisica> lafc = MetodosAleatorios.listaAtividadeComplexa(laf);
			for (AtividadeFisica afc : lafc) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(afc.getData().getTime()))==0)
					if(afc.getVelMedia()!=null)
						velMedia+=afc.getVelMedia();
			}
			series.getData().add(new Data<String, Number>(sdf.format(calendar.getTime()),velMedia));
		}
		series.setName("Velocidade Media");
		return series;
	}//fim velocidadeMediaGrafico
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoRitmoMedio(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Double ritmoMedio,segundos;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			ritmoMedio=0d;
			segundos=0d;
			List<AtividadeFisica> lafc = MetodosAleatorios.listaAtividadeComplexa(laf);
			for (AtividadeFisica afc : lafc) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(afc.getData().getTime()))==0)
					if(afc.getRitmoMedio()!=null){
						ritmoMedio+=Double.parseDouble(afc.getRitmoMedio().split("'")[0]);
						segundos+=Double.parseDouble(afc.getRitmoMedio().split("'")[1].substring(0, 1));
					}
			}
			if(segundos>60){
				ritmoMedio+=segundos/60;
				StringBuilder sb = new StringBuilder("0.");
				sb.append(segundos%60);
				ritmoMedio+=Double.parseDouble(sb.toString());
			}else
				ritmoMedio+=segundos;
			series.getData().add(new Data<String, Number>(sdf.format(calendar.getTime()),ritmoMedio));
		}
		series.setName("Ritmo Medio");
		return series;
	}//fim ritmo mediografico
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoDistanciaMedia(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Double distancia;
		int i=0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			distancia=0D;
			i=0;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					distancia+=af.getDistancia();
					i++;
				}
			}
			DecimalFormat df = new DecimalFormat("###,##0.00");
			distancia = Double.parseDouble(df.format(distancia/i).replace(".", "").replace(",", "."));
			series.getData().add(new Data<String,Number>(sdf.format(calendar.getTime()),distancia));
		}
		series.setName("Distancia Media");
		return series;
	}//fim distanciamedia
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um series
	 * e o retorna esse series para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return series
	 */
	protected static XYChart.Series<String, Number> seriesGraficoMediaKcal (List<AtividadeFisica> laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		Double kCal;
		int i=0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			kCal=0D;
			i=0;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					kCal+=af.getkCal();
					i++;
				}
			}
			DecimalFormat df = new DecimalFormat("###,##0.00");
			kCal = Double.parseDouble(df.format(kCal/i).replace(".", "").replace(",", "."));
			series.getData().add(new Data<String,Number>(sdf.format(calendar.getTime()), kCal));
		}
		series.setName("Media Kcal Perdidas");
		return series;
	}//fim kCalGrafico

}
