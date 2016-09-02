package usuario;

import jogo.Jogo;

/**
 * Interface que define os comportamentos para tipos distintos de Usuario.
 * 
 * @author Tiago Pereira.
 *
 */
public interface TipoDeUsuario {
	/**
	 * Compra jogo.
	 * @param jogo
	 * 		Jogo a ser comprado.
	 * @return
	 * 		Retorna o valor do jogo com o desconto especifico para o tipo que implementa a interface.
	 */
	double compraJogo(Jogo jogo);
	/**
	 * Recompensa o usuario.
	 * 
	 * @param jogo
	 * 		Jogo que o usuario jogou.
	 * @return
	 * 		Retorna o valor de x2p da recompensa.
	 */
	int recompensar(Jogo jogo);
	
	/**
	 * Pune o usuario.
	 * 
	 * @param jogo
	 * 		Jogo que o usuario jogou.
	 * @return
	 * 		Retorna o valor de x2p da punicao.
	 */
	int punir(Jogo jogo);
	
	/**
	 * Retorna o bonus por compra.
	 * @param jogo
	 * 		O jogo a ser usado para o calculo do bonus.
	 * @return
	 * 		Inteiro referente ao bonus pela compra.
	 */
	int getBonusPorCompra(Jogo jogo);
}
