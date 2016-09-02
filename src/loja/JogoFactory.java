package loja;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

import jogo.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import excecoes.*;

/**
 * Classe que auxilia a loja a criar subclasses do tipo Jogo, dessa forma, evitando o acoplamento
 * entre a loja e as subclasses de jogo.
 * 
 * @author Tiago Pereira
 *
 */
public class JogoFactory {
	
	private HashMap<String, Jogabilidade> mapJogabildades;

	/**
	 * Construtor da classe.
	 */
	public JogoFactory(){
		this.initializeMap();
	}
	
	/**
	 * Instancia uma subclasse do tipo Jogo de acordo com o tipo informado como parametro.
	 * @param nome
	 * 		Nome do jogo.
	 * @param preco
	 * 		Preco do jogo.
	 * @param tipo
	 * 		Tipo do jogo a ser instanciado.
	 * @return
	 * 		O jogo criado.
	 * @throws StringInvalidaException
	 * 		Quando o tipo do jogo eh invalido.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco do jogo eh negativo.
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
	
	/**
	 * Instancia uma subclasse do tipo Jogo de acordo com o tipo informado como parametro.
	 * @param nome
	 * 		Nome do jogo.
	 * @param preco
	 * 		Preco do jogo.
	 * @param jogabilidades
	 * 		Conjunto de jogabilidades que o jogo possui.
	 * @param tipo
	 * 		Tipo do jogo a ser instanciado.
	 * @return
	 * 		O jogo criado.
	 * @throws StringInvalidaException
	 * 		Quando o tipo do jogo eh invalido.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco eh negativo.
	 */
	public Jogo criaJogo(String nome, double preco, String jogabilidades, String tipo)
			throws StringInvalidaException, PrecoInvalidoException {
		if(tipo == null || !(tipo.equalsIgnoreCase("RPG") || tipo.equalsIgnoreCase("Luta")
				|| tipo.equalsIgnoreCase("Plataforma"))){
			throw new StringInvalidaException("Tipo de jogo invalido.");
		}
		if(tipo.equalsIgnoreCase("RPG")){
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo novo = new Rpg(nome, preco, tiposJogabilidades);
			return novo;
		}
		if(tipo.equalsIgnoreCase("Luta")){
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo novo = new Luta(nome, preco, tiposJogabilidades);
			return novo;
		}
		if(tipo.equalsIgnoreCase("Plataforma")){
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo novo = new Plataforma(nome, preco, tiposJogabilidades);
			return novo;
		}
		return null;
		
	}
	
	/**
	 * Cria o conjunto de jogabilidades do jogo.
	 * @param names1
	 * 		Nomes das jogabilidades
	 * @return
	 * 		O conjunto de jogabilidades.
	 */
	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(" ");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}

		return jogabilidades;

	}
	
	/**
	 * Inicializa o conjunto de jogabilidades.
	 */
	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);
	}
}