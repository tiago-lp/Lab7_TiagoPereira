package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * Subclasse Plataforma do tipo Jogo.
 * 
 * @author Tiago Pereira
 *
 */
public class Plataforma extends Jogo {
	public final static int MAXIMUM_SCORE = 100000;
	public final static int TAXA_X2P = 20;
	
	/**
	 * Construtor de Jogo.
	 * 
	 * @param nome
	 * 		Nome do jogo.
	 * @param preco
	 * 		Preco do jogo.
	 * @throws StringInvalidaException
	 * 		Quando o nome eh nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco eh negativo.
	 */
	public Plataforma(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}

	/**
	 * Construtor do jogo que recebe um conjunto de jogabilidades alem do nome e preco.
	 * 
	 * @param nome
	 * 		Nome do jogo.
	 * @param preco
	 * 		Preco do jogo.
	 * @param jogabilidades
	 * 		Conjunto de jogabilidades.
	 * @throws StringInvalidaException
	 * 		Quando o nome eh nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco eh negativo.
	 */
	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}

	/**
	 * Comportamento especifico do tipo Plataforma do metodo registraJogada. 
	 * Metodo sobrescrito da classe abstrata Jogo. Definicoes no metodo da classe jogo.
	 */
	@Override
	public int registraJogada(int score, boolean venceu) throws ValorInvalidoException {
		setVezesJogadas(getVezesJogadas() + 1);
		if (score > this.getMaiorScore()) {
			setMaiorScore(score);
		} else if (score > MAXIMUM_SCORE) {
			setMaiorScore(MAXIMUM_SCORE);
		}
		if (venceu) {
			setVezesConcluidas(getvezesConcluidas() + 1);
			return TAXA_X2P;
		}
		return 0;
	}

	/**
	 * ToString
	 */
	public String toString() {
		String resultado = "+ " + getNome() + " - Plataforma:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}
