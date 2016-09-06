package facade;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

import loja.LojaController;
import easyaccept.EasyAccept;
import excecoes.LojaException;

/**
 * Classe facade.
 * Facade da loja.
 * 
 * @author Tiago Pereira
 *
 */
public class LojaFacade {
	
	LojaController loja;
	
	/**
	 * Construtor da classe.
	 */
	public LojaFacade(){
		this.loja = new LojaController();
	}
	
	/**
	 * Cria um usuario.
	 * @param nome
	 * 		Nome do usuario.
	 * @param login
	 * 		Login do usuario.
	 * @param tipo
	 * 		Tipo de usuario.
	 */
	public void criaUsuario(String nome, String login, String tipo){
		try{
			loja.criaUsuario(nome, login, tipo);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Adiciona credito para um usuario.
	 * @param login
	 * 		Login do usuario.
	 * @param valor
	 * 		Valor do credito a ser adicionado.
	 */
	public void adicionaCredito(String login, double valor){
		try{
			loja.adicionaCredito(login, valor);
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Confere o credito de um usuario.
	 * @param login
	 * 		Login do usuario.
	 * @return
	 * 		Credito do usuario.
	 */
	public double confereCredito(String login){
		try{
			return loja.confereCredito(login);
		}catch(LojaException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Cria e vende um jogo a um usuario.
	 * @param jogoNome
	 * 		Nome do jogo a ser criado.
	 * @param preco
	 * 		Preco do jogo a ser criado.
	 * @param jogabilidades
	 * 		Jogabilidades do jogo.
	 * @param estiloJogo
	 * 		Estilo do jogo.
	 * @param loginUser
	 * 		Login do usuario a quem o jogo sera vendido.
	 */
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		try{
			loja.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser); // chamada polimorfica
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Faz upgrade de um usuario, de noob para veterano.
	 * @param login
	 * 		Login do usuario.
	 */
	public void upgrade(String login){
		try {
			loja.upgrade(login);
		} catch (LojaException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Pune um usuario em uma jogada.
	 * @param login
	 * 		Login do usuario.
	 * @param jogoNome
	 * 		Nome do jogo jogado.
	 * @param scoreObtido
	 * 		Score obtido na jogada.
	 * @param zerou
	 * 		Boolean indicando se o usuario zerou o jogo.
	 */
	public void punir(String login, String jogoNome, int scoreObtido, boolean zerou){
		try{
			loja.punir(login, jogoNome, scoreObtido, zerou); // chamada polimorfica
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Recompensa um usuario em uma jogada.
	 * @param login
	 * 		Login do usuario.
	 * @param jogoNome
	 * 		Nome do jogo jogado.
	 * @param scoreObtido
	 * 		Score obtido na jogada.
	 * @param zerou
	 * 		Boolean indicando se o usuario zerou o jogo.
	 */
	public void recompensar(String login, String jogoNome, int scoreObtido, boolean zerou){
		try{
			loja.recompensar(login, jogoNome, scoreObtido, zerou); // chamada polimorfica
		}catch(LojaException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Retorna o x2p de um usuario.
	 * @param login
	 * 		Login do usuario.
	 * @return
	 * 		O x2p do usuario.
	 */
	public int getX2p(String login){
		try{
			return loja.getX2p(login);
		}catch(LojaException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	/**
	 * ToString
	 */
	public String toString(){
		return loja.toString();
	}
	
	/**
	 * Testes EasyAccept
	 * @param args
	 */
	public static void main(String[] args) {
		args = new String[] { "facade.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",  "acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}
}
