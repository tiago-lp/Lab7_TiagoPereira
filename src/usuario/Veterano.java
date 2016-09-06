package usuario;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

import jogo.Jogabilidade;
import jogo.Jogo;

/**
 * Classe Veterano que implementa o comportamento da interface TipodeUsuario.
 * @author Tiago Pereira
 *
 */
public class Veterano implements TipoDeUsuario {
	private static final double DESCONTO_VETERANO = 0.8;
	private static final int BONUS_VETERANO = 15;

	/**
	 * Construtor da classe.
	 */
	public Veterano() {
	}

	/**
	 * Implementa o compra jogo da interface.
	 * Retorna o preco do jogo com desconto para o tipo Veterano.
	 */
	@Override
	public double compraJogo(Jogo jogo) {
		double custo = jogo.getPreco() * DESCONTO_VETERANO;
		return custo;
	}

	/**
	 * Implementa o metodo recompensar da interface TipoDeUsuario.
	 * Retorna o inteiro referente a recompensa para o tipo Veterano.
	 */
	@Override
	public int recompensar(Jogo jogo){
		int recompensa = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.ONLINE)){
			recompensa += 10;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
			recompensa += 20;
		}
		return recompensa;
	}

	/**
	 * Implementa o metodo punir da interface TipoDeUsuario.
	 * Retorna o inteiro referente a punicao para o tipo Veterano.
	 */
	@Override
	public int punir(Jogo jogo) {
		int punicao = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
			punicao -= 20;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)){
			punicao -= 20;
		}
		return punicao;	
	}
	
	/**
	 * Implementa o metodo da interface.
	 * Retorna o bonus para usuario veterano.
	 */
	public int getBonusPorCompra(Jogo jogo){
		int parteInteira =(int) jogo.getPreco();
		int bonusXp =  parteInteira * BONUS_VETERANO;
		return bonusXp;
	}
	
	@Override
	public String toString() {
		return "Jogador Veterano: ";
	}

}
