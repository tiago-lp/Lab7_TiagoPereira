package loja;
/*115210912 - Tiago Lima Pereira: LAB 6 - Turma 3*/

import jogo.*;
import excecoes.*;

/**
 * Classe que auxilia a loja a criar subclasses do tipo Jogo, dessa forma, evitando o acoplamento
 * entre a loja e as subclasses de jogo.
 * 
 * @author Tiago Pereira
 *
 */
public class JogoFactory {
	
	/**
	 * Construtor vazio pois a classe nao possui atributos, apenas metodo.
	 */
	public JogoFactory(){
	}
	
	/**
	 * Instancia uma subclasse do tipo Jogo de acordo com o tipo informado como parametro.
	 * 
	 * @param nome
	 * 		Nome do jogo.
	 * @param preco
	 * 		Preco do jogo.
	 * @param tipo
	 * 		Define o tipo do jogo a ser instanciado.
	 * @return
	 * 		Retorna uma subclasse de Jogo.
	 * @throws Exception
	 * 		Exception para um tipo invalido.
	 */
	public Jogo criaJogo(String nome, double preco, String tipo) throws StringInvalidaException, PrecoInvalidoException{
		if(tipo == null || !(tipo.equalsIgnoreCase("RPG") || tipo.equalsIgnoreCase("Luta")
				|| tipo.equalsIgnoreCase("Plataforma"))){
			throw new StringInvalidaException("Tipo de jogo invalido.");
		}
		if(tipo.equalsIgnoreCase("RPG")){
			Jogo novo = new Rpg(nome, preco);
			return novo;
		}
		if(tipo.equalsIgnoreCase("Luta")){
			Jogo novo = new Luta(nome, preco);
			return novo;
		}
		if(tipo.equalsIgnoreCase("Plataforma")){
			Jogo novo = new Plataforma(nome, preco);
			return novo;
		}
		return null;
	}
}