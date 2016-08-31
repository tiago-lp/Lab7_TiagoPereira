package loja;

import java.util.Set;
import java.util.HashSet;
import usuario.Usuario;
import jogo.Jogo;
import excecoes.*;

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
	 */
	public boolean adicionaUsuario(String nome, String login, String tipo) throws LojaException{
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
	 * @throws SteamException
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
	 * @throws SteamException
	 * 		Quando o jogo nao esta cadastrado na loja.
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
	 * @throws StringException
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
	 * @throws StringException
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
	 * @throws StringException
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
	 * @throws StringException
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
	 * Adiciona dinheiro ao saldo de um usuario.
	 * 
	 * @param login
	 * 		Login do usuario ao qual o saldo ira ser adicionado.
	 * @param valor
	 * 		Valor que ira ser adicionado ao saldo do usuario.
	 * @return
	 * 		Boolean indicando se o saldo foi adicionado(true) ou nao(false).
	 * @throws SteamException
	 * 		Quando: 1. Login eh nulo ou vazio;
	 * 				2. valor eh menor que zero.
	 */
	public boolean adicionaDinheiro(String login, double valor) throws LojaException {
		if(contemUsuario(login)){
			Usuario user = getUsuario(login);
			user.adicionaDinheiro(valor);
			return true;
		}else{
			return false;
		}
	}
	
	
	public void vendeJogo(String loginUser, String jogoNome, double preco, String jogabilidades, String tipo)throws LojaException{
		if(contemUsuario(loginUser) && contemJogo(jogoNome)){
			vendeJogo(loginUser, jogoNome);
		}
		else if(contemUsuario(loginUser)){
			Usuario usuario = getUsuario(loginUser);
			Jogo jogo = this.fabricaJogo.criaJogo(jogoNome, preco, jogabilidades, tipo);
			usuario.compraJogo(jogo);
		}
	}
	
	public void vendeJogo(String loginUser, String jogoNome) throws LojaException{
		if(contemUsuario(loginUser) && contemJogo(jogoNome)){
			Usuario usuario = getUsuario(loginUser);
			Jogo jogo = getJogo(jogoNome);
			usuario.compraJogo(jogo);
		}
	}
	
	/**
	 * Faz o upgrade de um Usuario Noob para Veterano caso ele seja Noob e tenha x2p maior ou igual a 1000.
	 * @param login
	 * 		Login do usuario que ira realizar upgrade.
	 * @return
	 * 		Boolean indicando se o usuario fez upgrade(true) ou nao(false).
	 * @throws SteamException
	 * 		Quando o usuario nao existe; ja eh veterano ou nao possui x2p para realizar o upgrade.
	 */
	public boolean upgrade(String login) throws LojaException{
		if(!contemUsuario(login)){
			throw new UsuarioInvalidoException("Usuario inexistente.");
		}
		Usuario noob = getUsuario(login);
		
		if(!fabricaUsuario.verificaNoob(noob)){
			throw new UsuarioInvalidoException("Usuario ja eh veterano.");
		}
		
		if(noob.getX2p() < 1000){
			throw new ValorInvalidoException("X2p insuficiente para fazer upgrade.");
		}
		
		String nome = noob.getNome();
		String userlogin = noob.getLogin();
		Set<Jogo> jogos = noob.getMeusJogos();
		double saldo = noob.getCredito();
		int x2p = noob.getX2p();
		
		Usuario veterano = fabricaUsuario.criaUsuario(nome, userlogin, "veterano");
		veterano.setMeusJogos(jogos);
		veterano.setCredito(saldo);
		veterano.setX2p(x2p);
		
		this.usuarios.remove(noob);
		
		return this.usuarios.add(veterano);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}

	@Override
	/**
	 * Equals da loja. Uma loja eh igual a outra se possuirem os mesmos usuarios cadastrados.
	 */
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
	 * @throws StringException
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
	 * @throws StringException
	 * 		Quando nome eh nulo ou vazio.
	 */
	private void nomeException(String nome) throws StringInvalidaException {
		if(nome == null || nome.trim().isEmpty()){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
	}
}
