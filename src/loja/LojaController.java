package loja;

import java.util.Set;
import java.util.HashSet;
import usuario.Usuario;
import jogo.Jogo;
import excecoes.*;

/**
 * Classe LojaController.
 * 
 * @author Tiago Pereira
 *
 */
public class LojaController {
	public static final String FIM_DE_LINHA = System.lineSeparator();

	private Set<Usuario> usuarios;
	private Set<Jogo> jogos;
	private UsuarioFactory fabricaUsuario;
	private JogoFactory fabricaJogo;
	
	/**
	 * Construtor da Loja.
	 */
	public LojaController(){
		usuarios = new HashSet<Usuario>();
		jogos = new HashSet<Jogo>();
		fabricaUsuario = new UsuarioFactory();
		fabricaJogo = new JogoFactory();
		
	}
	
	/**
	 * Cria e adiciona um Usuario na loja.
	 * 
	 * @param nome
	 * 		Nome do Usuario a ser criado.
	 * @param login
	 * 		Login do Usuario a ser criado.
	 * @param tipo
	 * 		Tipo de Usuario a ser criado.
	 * @return
	 * 		Boolean indicando se foi cadastrado(True) ou nao(False).
	 * @throws LojaException
	 * 		Lanca uma possivel excecao da Factory de Usuario.
	 */
	public boolean criaUsuario(String nome, String login, String tipo) throws LojaException{
		Usuario novo = fabricaUsuario.criaUsuario(nome, login, tipo);
		return this.usuarios.add(novo);
	}
	
	/**
	 * Remove um Usuario da loja.
	 * 
	 * @param login
	 * 		Login do usuario a ser removido.
	 * @return
	 * 		Boolean indicando se o usuario foi removido(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando o usuario nao esta cadastrado na loja.
	 */
	public boolean removeUsuario(String login) throws LojaException{
		boolean contemUsuario = this.contemUsuario(login);
		if(!contemUsuario){
			throw new UsuarioInvalidoException("Usuario nao existe na loja.");
		}
		Usuario usuario = this.getUsuario(login);
		return this.usuarios.remove(usuario);
	}
	
	/**
	 * Cria e adiciona um Jogo na Loja.
	 * 
	 * @param nome
	 * 		Nome do jogo a ser criado.
	 * @param preco
	 * 		Preco do jogo a ser criado.
	 * @param tipo
	 * 		Tipo do jogo a ser criado.
	 * @return
	 * 		Boolean indicando se foi adicionado(true) ou nao(false).
	 * @throws LojaException
	 * 		Lanca uma possivel excecao da Factory de Jogo.
	 */
	public boolean adicionaJogo(String nome,double preco, String tipo)throws LojaException{
		Jogo novo = fabricaJogo.criaJogo(nome, preco, tipo);
		return this.jogos.add(novo);
	}
	
	/**
	 * Remove um Jogo da loja.
	 * 
	 * @param nome
	 * 		nome do jogo a ser removido.
	 * @return
	 * 		Boolean indicando se o jogo foi removido(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando o jogo nao esta cadastrado na loja.
	 * 
	 */
	public boolean removeJogo(String nome) throws LojaException{
		boolean contemJogo = this.contemJogo(nome);
		if(!contemJogo){
			throw new JogoInvalidoException("Jogo nao existe na loja.");
		}
		Jogo jogo = this.getJogo(nome);
		return this.jogos.remove(jogo);
	}
	
	/**
	 * Retorna um usuario especifico
	 * 
	 * @param login
	 * 		login do usuario a ser buscado.
	 * @return
	 * 		Um Usuario.
	 * @throws StringInvalidaException
	 * 		Quando o login eh nulo ou vazio.
	 */
	public Usuario getUsuario(String login) throws StringInvalidaException {
		if(contemUsuario(login)){
			for(Usuario usuario : this.usuarios){
				if(usuario.getLogin().equals(login)){
					return usuario;
				}
			}
		}
		return null;
	}
	
	/**
	 * Verifica se um usuario existe na loja.
	 * 
	 * @param login
	 * 		login do usuario a ser buscado.
	 * @return
	 * 		Boolean indicando se o usuario existe (true) ou nao (false).
	 * @throws StringInvalidaException
	 * 		Quando o login eh nulo ou vazio.
	 */
	public boolean contemUsuario(String login) throws StringInvalidaException {
		loginException(login);
		for(Usuario usuario: this.usuarios){
			if(usuario.getLogin().equals(login)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna um Jogo especifico.
	 * 
	 * @param nome
	 * 		Nome do jogo a ser buscado.
	 * @return
	 * 		Um Jogo.
	 * @throws StringInvalidaException
	 * 		Quando o nome eh nulo ou vazio.
	 */
	public Jogo getJogo(String nome) throws StringInvalidaException {
		if(contemJogo(nome)){
			for(Jogo jogo : this.jogos){
				if(jogo.getNome().equalsIgnoreCase(nome)){
					return jogo;
				}
			}
		}
		return null;
	}
	
	/**
	 * Verifica se um jogo existe na loja.
	 * 
	 * @param nome
	 * 		Nome do jogo a ser buscado.
	 * @return
	 * 		Boolean indicando se o usuario existe (true) ou nao (false).
	 * @throws StringInvalidaException
	 * 		Quando o nome eh nulo ou vazio.
	 */
	public boolean contemJogo(String nome) throws StringInvalidaException {
		nomeException(nome);
		for(Jogo jogo : this.jogos){
			if(jogo.getNome().equalsIgnoreCase(nome)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adiciona credito ao saldo de um usuario.
	 * 
	 * @param login
	 * 		Login do usuario ao qual o saldo ira ser adicionado.
	 * @param valor
	 * 		Valor que ira ser adicionado ao saldo do usuario.
	 * @return
	 * 		Boolean indicando se o saldo foi adicionado(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando: 1. Login eh nulo ou vazio;
	 * 				2. valor eh menor que zero.
	 */
	public void adicionaCredito(String login, double valor) throws LojaException {
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario invalido.");
		}
		Usuario user = getUsuario(login);
		user.adicionaDinheiro(valor);
		
	}
	
	/**
	 * Confere o credito de um determinado usuario.
	 * @param login
	 * 		Login do usuario a ser verificado o credito.
	 * @return
	 * 		Valor do credito do usuario.
	 * @throws LojaException
	 * 		Quando o usuario eh invalido.
	 */
	public double confereCredito(String login) throws LojaException{
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario invalido.");
		}
		Usuario user = getUsuario(login);
		double credito = user.getCredito();
		return credito;
	}
	
	/**
	 * Cria e vende um jogo a um determinado usuario.
	 * @param jogoNome
	 * 		Nome do jogo a ser criado.
	 * @param preco
	 * 		Preco do jogo a ser criado.
	 * @param jogabilidades
	 * 		Jogabilidades do jogo a ser criado.
	 * @param tipo
	 * 		Tipo do jogo a ser criado.
	 * @param loginUser
	 * 		Login do usuario que o jogo ira ser vendido.
	 * @throws LojaException
	 * 		Lanca uma possivel excecao de String ou Valor do construtor do Jogo.
	 */
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String tipo, String loginUser)throws LojaException{
		if(contemUsuario(loginUser) && contemJogo(jogoNome)){
			vendeJogo(loginUser, jogoNome);
		}
		else if(contemUsuario(loginUser)){
			Usuario usuario = getUsuario(loginUser);
			Jogo jogo = this.fabricaJogo.criaJogo(jogoNome, preco, jogabilidades, tipo);
			usuario.compraJogo(jogo);
		}
	}
	
	/**
	 * Vende um jogo ja existente a um determinado usuario.
	 * @param loginUser
	 * 		Login do usuario a quem o jogo sera vendido.
	 * @param jogoNome
	 * 		Nome do jogo a ser vendido.
	 * @throws LojaException
	 * 		Quando o jogo nao existe na loja.
	 */
	public void vendeJogo(String loginUser, String jogoNome) throws LojaException{
		if(contemUsuario(loginUser) && contemJogo(jogoNome)){
			Usuario usuario = getUsuario(loginUser);
			Jogo jogo = getJogo(jogoNome);
			usuario.compraJogo(jogo);
		}
	}
	
	/**
	 * Pune um determinado usuario em uma jogada.
	 * @param login
	 * 		Login do usuario a ser punido.
	 * @param jogoNome
	 * 		Nome do jogo jogado.
	 * @param scoreObtido
	 * 		Score obtido na jogada.
	 * @param zerou
	 * 		Boolean indicando se o usuario concluiu o jogo(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando o usuario nao existe.
	 */
	public void punir(String login, String jogoNome, int scoreObtido, boolean zerou) throws LojaException {
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario inexistente.");
		}
		Usuario user = getUsuario(login);
		user.punir(jogoNome, scoreObtido, zerou);
	}
	
	/**
	 * Recompensa um determinado usuario em uma jogada.
	 * @param login
	 * 		Login do usuario a ser recompensado.
	 * @param jogoNome
	 * 		Nome do jogo jogado.
	 * @param scoreObtido
	 * 		Score obtido na jogada.
	 * @param zerou
	 * 		Boolean indicando se o usuario concluiu o jogo(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando o usuario nao existe.
	 */
	public void recompensar(String login, String jogoNome, int scoreObtido, boolean zerou) throws LojaException {
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario inexistente.");
		}
		Usuario user = getUsuario(login);
		user.recompensar(jogoNome, scoreObtido, zerou);	
	}
	
	/**
	 * Retorna o x2p de um determinado usuario.
	 * @param login
	 * 		Login do usuario.
	 * @return
	 * 		X2p do usuario.
	 * @throws LojaException
	 * 		Quando o usuario nao existe.
	 */
	public int getX2p(String login) throws LojaException{
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario Inexistente.");
		}
		Usuario user = getUsuario(login);
		return user.getX2p();
	}
	
	/**
	 * Torna o status de um usuario noob em veterano.
	 * @param login
	 * 		Login do usuario a ter o status alterado.
	 * @throws LojaException
	 * 		Quando o usuario nao existe ou quando o x2p eh insuficiente.
	 */
	public void upgrade(String login) throws LojaException{
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario inexistente.");
		}

		Usuario user = getUsuario(login);
		
		if(user.getX2p() < 1000){
			throw new UpgradeInvalidoException("X2p insuficiente para fazer upgrade.");
		}
		user.upgrade();
	}
	
	/**
	 * Torna o status de um usuario veterano em noob.
	 * @param login
	 * 		Login do usuario a ter o status alterado.
	 * @throws LojaException
	 * 		Quando o usuario nao existe.
	 */
	public void downgrade(String login) throws LojaException{
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario inexistente.");
		}
		Usuario user = getUsuario(login);
		
		if(user.getX2p() < 1000){
			user.downgrade();
		}
	}

	/**
	 * HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}

	/**
	 * Equals
	 */
	@Override
	public boolean equals(Object objeto) {
		if(objeto instanceof LojaController){
			LojaController outraLoja = new LojaController();
			if(outraLoja.getUsuarios().equals(this.usuarios)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * ToString
	 */
	public String toString(){
		String saida = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for(Usuario usuario : this.usuarios){
			saida += usuario.toString() + FIM_DE_LINHA;
		}
		return saida;
	}	
	
	/**
	 * @return
	 * 		O Conjunto de Usuarios da loja.
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @return
	 * 		O conjunto de jogos da loja.
	 */
	public Set<Jogo> getJogos() {
		return jogos;
	}
	

	/**
	 * Lanca excecao para login.
	 * 
	 * @param login
	 * 		login a ser verificado.
	 * @throws StringInvalidaException
	 * 		Quando o login eh nulo ou vazio.
	 */
	private void loginException(String login) throws StringInvalidaException {
		if(login == null || login.trim().isEmpty()){
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}
	}
	
	/**
	 * Lanca excecao para nome.
	 * 
	 * @param nome
	 * 		Nome a ser verificado.
	 * @throws StringInvalidaException
	 * 		Quando nome eh nulo ou vazio.
	 */
	private void nomeException(String nome) throws StringInvalidaException {
		if(nome == null || nome.trim().isEmpty()){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
	}
}
