package usuario;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

import jogo.Jogabilidade;
import jogo.Jogo;

/**
 * Classe Noob que implementa o comportamento da interface TipodeUsuario.
 * @author Tiago Pereira
 *
 */
public class Noob implements TipoDeUsuario {
	private static final double DESCONTO_NOOB = 0.9;
	private static final int BONUS_NOOB = 10;
	
	/**
	 * Construtor da classe.
	 */
	public Noob(){
	}

	/**
	 * Implementa o compra jogo da interface.
	 * Retorna o preco do jogo com desconto para o tipo Noob.
	 */
	@Override
	public double compraJogo(Jogo jogo){
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		return custo;
	}
	
	/**
	 * Implementa o metodo recompensar da interface TipoDeUsuario.
	 * Retorna o inteiro referente a recompensa para o tipo Noob.
	 */
	@Override
	public int recompensar(Jogo jogo){
		int recompensa = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)){
			recompensa += 30;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.MULTIPLAYER)){
			recompensa += 10;
		}
		return recompensa;
		
	}

	/**
	 * Implementa o metodo punir da interface TipoDeUsuario.
	 * Retorna o inteiro referente a punicao para o tipo Noob.
	 */
	@Override
	public int punir(Jogo jogo){
		int punicao = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.ONLINE)){
			punicao -= 10;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
			punicao -= 20;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
			punicao -= 50;
		}
		return punicao;
	}
	

	/**
	 * Implementa o metodo da interface.
	 * Retorna o bonus para usuario noob.
	 */
	public int getBonusPorCompra(Jogo jogo){
		int parteInteira =(int) jogo.getPreco();
		int bonusXp =  parteInteira * BONUS_NOOB;
		return bonusXp;
	}
	
	@Override
	public String toString() {
		return "Jogador Noob: ";
	}

}