package loja;
/*115210912 - Tiago Lima Pereira: LAB 6 - Turma 3*/

import usuario.*;
import excecoes.*;
/**
 * Classe que auxilia a loja a criar subclasses do tipo Usuario, dessa forma, evitando o acoplamento
 * entre a loja e as subclasses de Usuario.
 * 
 * @author Tiago Pereira
 */
public class UsuarioFactory {
	
	/**
	 * Construtor vazio pois a classe nao possui atributos, apenas metodo.
	 */
	public UsuarioFactory(){
	}

	/**
	 * Instancia uma subclasse do tipo Jogo de acordo com o tipo informado como parametro.
	 * 
	 * @param nome
	 * 		Nome do Usuario.
	 * @param login
	 * 		Login do Usuario.
	 * @param tipo
	 * 		Define o tipo do usuario a ser instanciado.
	 * @return
	 * 		Retorna uma subclasse de Usuario.
	 * @throws Exception
	 * 		Exception para um tipo invalido.
	 */
	public Usuario criaUsuario(String nome, String login, String tipo) throws StringInvalidaException{
		if(!(tipo.equalsIgnoreCase("Noob") || tipo.equalsIgnoreCase("Veterano")) || tipo == null){
			throw new StringInvalidaException("Tipo de usuario invalido.");
		}
		
		if(tipo.equalsIgnoreCase("Noob")){
			Usuario novo = new Noob(nome, login);
			return novo;
		}
		if(tipo.equalsIgnoreCase("Veterano")){
			Usuario novo = new Veterano(nome, login);
			return novo;
		}
		return null;
	}
	
	/**
	 * Verifica se um usuario eh do tipo Noob.
	 * @param usuario
	 * 		Usuario a ser comparado.
	 * @return
	 * 		Um boolean indicando se o usuario eh Noob (true) ou nao (false).
	 */
	public boolean verificaNoob(Usuario usuario){
		if(usuario.getClass().getSimpleName().equalsIgnoreCase("Noob")){
			return true;
		}else{
			return false;
		}
	}
}