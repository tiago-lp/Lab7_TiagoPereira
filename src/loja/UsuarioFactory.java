package loja;
/*115210912 - Tiago Lima Pereira: LAB 6 - Turma 3*/

import usuario.*;
import excecoes.*;
/**
 * Classe que auxilia a loja a criar Usuario com um status especifico.
 * Evitando o acoplamento entre a loja e as classes que implementam a interface TipoDeUsuario.
 * 
 * @author Tiago Pereira
 */
public class UsuarioFactory {
	
	/**
	 * Construtor da classe.
	 */
	public UsuarioFactory(){
	}


	/**
	 * Cria o usuario com um status especifico.
	 * @param nome
	 * 		Nome do usuario.
	 * @param login
	 * 		Login do usuario.
	 * @param tipo
	 * 		Tipo do status do usuario.
	 * @return
	 * 		O usuario criado.
	 * @throws LojaException
	 * 		Quando o tipo de usuario eh invalido.
	 */
	public Usuario criaUsuario(String nome, String login, String tipo) throws LojaException{
		if(!(tipo.equalsIgnoreCase("Noob") || tipo.equalsIgnoreCase("Veterano")) || tipo == null){
			throw new StringInvalidaException("Tipo de usuario invalido.");
		}
		Usuario novo = new Usuario(nome, login);
		if(tipo.equalsIgnoreCase("Veterano")){
			novo.upgrade();
		}
		return novo;
	}
}