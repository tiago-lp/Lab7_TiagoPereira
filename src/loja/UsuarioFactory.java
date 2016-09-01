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