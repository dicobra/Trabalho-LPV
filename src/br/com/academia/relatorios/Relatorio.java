package br.com.academia.relatorios;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;

import br.com.academia.MetodosAleatorios;
import br.com.academia.modelo.AtividadeFisica;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * Classe responsavel por gerar relatorios para o sistema
 * @author tivin
 *
 */
public class Relatorio {

	/**
	 * analisa uma lista de atividade fisica e retorna a maior duraçao
	 * @param laf lista de atividade fisica
	 * @return string com a maior duração
	 */
	public static String maiorDuracaoExercicio(List<AtividadeFisica> laf){
		String maiorDuracao = laf.get(0).getDuracao();
		for (AtividadeFisica af : laf) {
			if(maiorDuracao.compareToIgnoreCase(af.getDuracao())<0){
				maiorDuracao = af.getDuracao();
			}
		}
		return maiorDuracao;
	}
	/**
	 * analisa uma lista de atividade fisica e retorna a maior distancia
	 * @param laf lista de atividade fisica
	 * @return string com a maior distancia
	 */
	public static String maiorDistanciaPercorrida(List<AtividadeFisica> laf){
		String maior;
		Double maiorDist=laf.get(0).getDistancia();
		for (AtividadeFisica af : laf) {
			if(maiorDist.compareTo(af.getDistancia())<0)
				maiorDist = af.getDistancia();
		}
		maior = Double.toString(maiorDist);
		return maior;
	}
	/**
	 * analisa uma lista de atividade fisica e retorna a maior
	 * quantidade de calorias perdidas
	 * @param laf lista de atividade fisica
	 * @return string com a maior quantidade de calorias perdidas
	 */
	public static String maiorKcal(List<AtividadeFisica> laf){
		String maiorKcal;
		int kCal = laf.get(0).getkCal();
			for (AtividadeFisica af : laf) {
				if(kCal<af.getkCal())
					kCal=af.getkCal();
			}
		maiorKcal = Integer.toString(kCal);
		return maiorKcal;
	}
	/**
	 * analisa uma lista de atividade fisica e retorna o maior numero
	 * de passos dados
	 * @param laf lista de atividade fisica
	 * @return string com a maior numero de passos
	 */
	public static String maiorNPassos(List<AtividadeFisica>laf) {
		String maiorPassos;
		Long nPass=null;
		nPass = laf.get(0).getPassos();
		for (AtividadeFisica af : laf) {
			if(nPass.compareTo(af.getPassos())<0)
				nPass = af.getPassos();
		}
		maiorPassos = Long.toString(nPass);
		return maiorPassos;
	}//fim maiorPassos
	/**
	 * analisa uma lista de atividade fisica e retorna a maior velocidade
	 * @param laf lista de atividade fisica
	 * @return string com a maior velocidade
	 */
	public static String maiorVel(List<AtividadeFisica>laf) {
		Double maiorVel=null;
		String maior;
		List<AtividadeFisica> lafc = MetodosAleatorios.listaAtividadeComplexa(laf);
		for (AtividadeFisica afc : lafc) {
			if(afc==lafc.get(0))
				maiorVel=afc.getVelMax();
			if(maiorVel.compareTo(afc.getVelMax())<0)
				maiorVel = afc.getVelMax();
		}
		if(maiorVel==null)
			return "Nao Computado!";
		else{
			maior = Double.toString(maiorVel);
		}
		return maior;
	}//fim maiorVel

	/**
	 * retorna os dados da classe em uma<code>String</code>
	 * em forma de um relatorio.
	 * @return <code>String</code> com os dados da classe em forma de um relatorio.
	 */
	public static String relatorioafc(AtividadeFisica af) {
		StringBuilder sb = new StringBuilder("------ Detalhes do exercício ");
		sb.append(af.getNomeEx());
		sb.append(" ------");
		sb.append("\nData: ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sb.append(sdf.format(af.getData().getTime()));
		sb.append("\nTempo: ");
		sb.append(af.getTempo());
		sb.append("\nDuração: ");
		sb.append(af.getDuracao());
		sb.append("\nDistância: ");
		sb.append(af.getDistancia());
		sb.append(" Km");
		sb.append("\nCalorias perdidas: ");
		sb.append(af.getkCal());
		sb.append("\nPassos: ");
		sb.append(af.getPassos());
		if(af.getRitmoMedio()!=null){
			sb.append("\nVelocidade média: ");
			sb.append(af.getVelMedia());
			sb.append(" Km/h");
			sb.append("\nVelocidade máxima: ");
			sb.append(af.getVelMax());
			sb.append(" Km/h");
			sb.append("\nRitmo médio: ");
			sb.append(af.getRitmoMedio());
			sb.append("\nRitmo máximo: ");
			sb.append(af.getRitmoMax());
			sb.append("\nMenor elevação: ");
			sb.append(af.getMenorElev());
			sb.append("\nMaior elevação: ");
			sb.append(af.getMaiorElev());
			sb.append("\n------ Ritmo ------\n");
			sb.append(af.getRitmos());
			sb.append("\n");
		}else
			sb.append("\n\n");
		return sb.toString();
	}//fim relatorio

	private static HBox totalPassos(List<AtividadeFisica> laf){
		HBox box = new HBox();
		SwingNode nodeCampo = new SwingNode(),
				nodeValor = new SwingNode();
		Long passos = 0l;
		for (AtividadeFisica af : laf) {
			passos+=af.getPassos();
		}
		nodeCampo.setContent(new JLabel("Total De passos: "));
		nodeValor.setContent(new JLabel(passos.toString()));
		box.getChildren().addAll(nodeCampo,nodeValor);
		return box;
	}
	private static HBox totalKcalPerdidas(List<AtividadeFisica> laf){
		HBox box = new HBox();
		SwingNode nodeCampo = new SwingNode(),
				nodeValor = new SwingNode();
		Integer kCal = 0;
		for (AtividadeFisica af : laf) {
			kCal+=af.getkCal();
		}
		nodeCampo.setContent(new JLabel("Total De Calorias Perdidas: "));
		nodeValor.setContent(new JLabel(kCal.toString()));
		box.getChildren().addAll(nodeCampo,nodeValor);
		return box;
	}

	private static HBox mediaKcalPerdidas(List<AtividadeFisica> laf){
		HBox box = new HBox();
		SwingNode nodeCampo = new SwingNode(),
				nodeValor = new SwingNode();
		Integer kCal = 0;
		int i=0;
		for (AtividadeFisica af : laf) {
			kCal+=af.getkCal();
			i++;
		}
		kCal = kCal/i;
		nodeCampo.setContent(new JLabel("Media de Calorias perdidas: "));
		nodeValor.setContent(new JLabel(kCal.toString()));
		box.getChildren().addAll(nodeCampo,nodeValor);
		return box;
	}

	private static HBox distanciaMedia(List<AtividadeFisica> laf){
		HBox box = new HBox();
		SwingNode nodeCampo = new SwingNode(),
				nodeValor = new SwingNode();
		Double distanciaMedia = 0D;
		int i=0;
		for (AtividadeFisica af : laf) {
			distanciaMedia+=af.getDistancia();
			i++;
		}
		distanciaMedia = distanciaMedia/i;
		DecimalFormat df = new DecimalFormat("###,##0.00");
		nodeCampo.setContent(new JLabel("Distância Media: "));
		nodeValor.setContent(new JLabel(df.format(distanciaMedia)));
		box.getChildren().addAll(nodeCampo,nodeValor);
		return box;
	}

	private static HBox distanciaTotal(List<AtividadeFisica> laf){
		HBox box = new HBox();
		SwingNode nodeCampo = new SwingNode(),
				nodeValor = new SwingNode();
		Double distanciaMedia = 0D;
		for (AtividadeFisica af : laf) {
			distanciaMedia+=af.getDistancia();
		}
		DecimalFormat df = new DecimalFormat("###,##0.00");
		nodeCampo.setContent(new JLabel("Distância total: "));
		nodeValor.setContent(new JLabel(df.format(distanciaMedia)));
		box.getChildren().addAll(nodeCampo,nodeValor);
		return box;
	}

	public static VBox relatorioDadosDeExercicios(List<AtividadeFisica> laf){
		VBox vBox = new VBox();
		vBox.getChildren().addAll(totalPassos(laf),distanciaMedia(laf),distanciaTotal(laf),mediaKcalPerdidas(laf),totalKcalPerdidas(laf));
		return vBox;
	}

}
