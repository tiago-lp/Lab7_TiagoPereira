package facade;

import loja.LojaController;
import excecoes.LojaException;

public class LojaFacade {
	
	LojaController loja;
	
	public LojaFacade(){
		this.loja = new LojaController();
	}
	
	public void adicionaUsuario(String nome, String login, String tipo){
		try{
			loja.adicionaUsuario(nome, login, tipo);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
}
