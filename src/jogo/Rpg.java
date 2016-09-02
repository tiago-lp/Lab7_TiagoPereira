package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * Subclasse Rpg do tipo Jogo.
 * 
 * @author Tiago Pereira
 *
 */
public class Rpg extends Jogo{
	public final static int TAXA_X2P = 10;
	
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
	public Rpg(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
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
	public Rpg (String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}

	/**
	 * Comportamento especifico do tipo Rpg do metodo registraJogada. 
	 * Metodo sobrescrito da classe abstrata Jogo. Definicoes no metodo da classe jogo.
	 */
	@Override
	public int registraJogada(int score, boolean venceu) throws ValorInvalidoException {
		setVezesJogadas(getVezesJogadas()+ 1);
		if(score > this.getMaiorScore()){
			setMaiorScore(score);
		}
		if(venceu){
			setVezesConcluidas(getvezesConcluidas() + 1);
			
		}
		return TAXA_X2P;
	}
	
	/**
	 * ToString
	 */
	public String toString() {
		String resultado = "+ " + getNome() + " - RPG:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}
