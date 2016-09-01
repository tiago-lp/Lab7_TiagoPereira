package facade;

import loja.LojaController;
import easyaccept.EasyAccept;
import excecoes.LojaException;

public class LojaFacade {
	
	LojaController loja;
	
	public LojaFacade(){
		this.loja = new LojaController();
	}
	
	public void criaUsuario(String nome, String login, String tipo){
		try{
			loja.criaUsuario(nome, login, tipo);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void adicionaCredito(String login, double valor){
		try{
			loja.adicionaCredito(login, valor);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	public double confereCredito(String login){
		try{
			return loja.confereCredito(login);
		}catch(LojaException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		try{
			loja.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void upgrade(String login){
		try {
			loja.upgrade(login);
		} catch (LojaException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void punir(String login, String jogoNome, int scoreObtido, boolean zerou){
		try{
			loja.punir(login, jogoNome, scoreObtido, zerou);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void recompensar(String login, String jogoNome, int scoreObtido, boolean zerou){
		try{
			loja.recompensar(login, jogoNome, scoreObtido, zerou);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	public int getX2p(String login){
		try{
			return loja.getX2p(login);
		}catch(LojaException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public static void main(String[] args) {
		args = new String[] { "facade.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",  "acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}
}
