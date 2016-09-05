package usuario;

import java.util.HashSet;
import java.util.Set;

import excecoes.LojaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import excecoes.JogoInvalidoException;
import jogo.Jogo;

/**
 * Classe Usuario.
 * 
 * @author Tiago Pereira
 *
 */
public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int x2p;
	/**
	 * Usuario compoe um tipo de usuario que pode ser Noob ou Veterano.
	 * Tipo de usuario tem metodos iguais com compartamentos distintos.
	 * Identifica o tipo de usuario pelo seu status.
	 */
	private TipoDeUsuario status;

	/**
	 * Construtor de Usuario.
	 * 
	 * @param nome
	 * 		Nome do usuario.
	 * @param login
	 * 		Login do usuario.
	 * @throws StringInvalidaException
	 * 		Quando nome e/ou login sao nulos ou vazios.
	 */
	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		this.meusJogos = new HashSet<Jogo>();
		this.credito = 0;
		this.x2p = 0;
		this.status = new Noob();
	}

	/**
	 * Compra jogo. Metodo se comporta diferente de acordo com o status atual.
	 * 
	 * @param jogo
	 * 		Jogo a ser comprado.
	 * @throws LojaException
	 * 		Quando o valor do jogo ultrapassa o saldo do usuario.
	 */
	public void compraJogo(Jogo jogo) throws LojaException{
		double custo = status.compraJogo(jogo); // chamada polimorfica
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int bonusXpPorCompra = status.getBonusPorCompra(jogo); // chamada polimorfica
			setX2p(getX2p() + bonusXpPorCompra);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);
		}
	}
	
	/**
	 * Recompensa um usuario de acordo com o status atual.
	 * 
	 * @param jogoNome
	 * 		Nome do jogo.
	 * @param scoreObtido
	 * 		Score obtido na jogada.
	 * @param zerou
	 * 		Boolean indicando se o jogo foi concluido(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando o usuario nao possui o jogo.
	 */
	public void recompensar(String jogoNome, int scoreObtido, boolean zerou) throws LojaException{
		if(!contemJogo(jogoNome)){
			throw new JogoInvalidoException("Usuario nao possui o jogo.");
		}
		Jogo jogo = buscaJogo(jogoNome);
		int x2pJogada = jogo.registraJogada(scoreObtido, zerou); // chamada polimorfica
		int recompensa = status.recompensar(jogo); // chamada polimorfica
		setX2p(this.getX2p() + recompensa + x2pJogada);
	}
	
	/**
	 * Pune um usuario de acordo com o status atual.
	 * 
	 * @param jogoNome
	 * 		Nome do jogo.
	 * @param scoreObtido
	 * 		Score obtido na jogada.
	 * @param zerou
	 * 		Boolean indicando se o jogo foi concluido(true) ou nao(false).
	 * @throws LojaException
	 * 		Quando o usuario nao possui o jogo.
	 */
	public void punir(String jogoNome, int scoreObtido, boolean zerou) throws LojaException{
		if(!contemJogo(jogoNome)){
			throw new JogoInvalidoException("Usuario nao possui o jogo.");
		}
		Jogo jogo = buscaJogo(jogoNome);
		int x2pJogada = jogo.registraJogada(scoreObtido, zerou); // chamada polimorfica
		int punicao = status.punir(jogo); // chamada polimorfica
		setX2p(this.getX2p() + punicao + x2pJogada);
	}
	

	/**
	 * Adiciona credito ao saldo atual.
	 * 
	 * @param valor
	 * 		Valor a ser adicionado.
	 * @throws ValorInvalidoException
	 * 		Quando o valor eh negativo.
	 */
	public void adicionaDinheiro(double valor) throws ValorInvalidoException{
		if(valor < 0){
			throw new ValorInvalidoException("Valor que deseja adicionar ao saldo nao pode ser negativo.");
		}
		this.setCredito(this.credito + valor);
	}
	
	/**
	 * Verifica se o usuario possui um jogo.
	 * @param nomeJogo
	 * 		Jogo a ser buscado.
	 * @return
	 * 		Boolean indicando se o usuario possui o jogo(true) ou nao(false).
	 * @throws StringInvalidaException
	 * 		Quando o nome informado eh nulo ou vazio.
	 */
	public boolean contemJogo(String nomeJogo) throws StringInvalidaException {
		if(nomeJogo == null || nomeJogo.trim().isEmpty()){
			throw new StringInvalidaException("Nome do jogo nao pode ser nulo ou vazio.");
		}
		for(Jogo jogos : this.meusJogos){
			if(jogos.getNome().equalsIgnoreCase(nomeJogo)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Altera o status do Usuario para Veterano.
	 */
	public void upgrade(){
		this.status = new Veterano();
	}
	
	/**
	 * Altera o status do Usuario para Noob.
	 */
	public void downgrade(){
		this.status = new Noob();
	}

	/**
	 * Busca um jogo e o retorna.
	 * @param nomeJogo
	 * 		Nome do jogo a ser buscado.
	 * @return
	 * 		O jogo buscado ou null.
	 * @throws StringInvalidaException
	 * 		Quando o nome do jogo eh nulo ou vazio.
	 */
	public Jogo buscaJogo(String nomeJogo) throws StringInvalidaException{
		if(contemJogo(nomeJogo)){
			for(Jogo jogos : this.meusJogos){
				if(jogos.getNome().equalsIgnoreCase(nomeJogo)){
					return jogos;
				}
			}
		}
		return null;
	}
	
	/**
	 * Cadastra um jogo ao conjunto de jogos do Usuario.
	 * @param jogo
	 * 		Jogo a ser cadastrado.
	 * @throws JogoInvalidoException
	 * 		Quando o jogo eh nulo.
	 */
	public void cadastraJogo(Jogo jogo) throws JogoInvalidoException {
		if(jogo == null){
			throw new JogoInvalidoException("Jogo nao pode ser nulo.");
		}
		this.meusJogos.add(jogo);
	}

	/**
	 * Calcula o preco total de todos os jogos que o usuario possui.
	 * @return
	 * 		Double informando a soma do preco de todos os jogos do usuario.
	 */
	public double calculaPrecoTotal() {
		double total = 0;
		for(Jogo jogo : this.meusJogos){
			total += jogo.getPreco();
		}
		return total;
	}

	/**
	 * HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
	
	/**
	 * ToString
	 */
	public String toString(){
		String myString = status.toString() + this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - " + this.getX2p() + " x2p" + FIM_DE_LINHA;
		myString += super.toString();;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;
		for(Jogo jogo : getMeusJogos()){
			myString += jogo.toString() + FIM_DE_LINHA;
		}
		myString += "Total de preco dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------" + FIM_DE_LINHA;
		return myString;
	}

	/**
	 * Retorna o x2p atual do usuario.
	 * @return
	 * 		x2p.
	 */
	public int getX2p() {
		return this.x2p;
	}
	
	/**
	 * Altera o X2p do usuario.
	 * 
	 * @param novoValor
	 * 		Novo valor do x2p.
	 * @throws ValorInvalidoException
	 * 		Quando o valor eh negativo.
	 */
	public void setX2p(int novoValor) throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Valor do X2P nao pode ser negativo.");
		}
		this.x2p = novoValor;
	}

	/**
	 * Retorna o nome do usuario.
	 * @return
	 * 		Nome.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Altera o nome do usuario.
	 * @param nome
	 * 		Novo nome do usuario.
	 * @throws StringInvalidaException
	 * 		Quando nome eh nulo ou vazio.
	 */
	public void setNome(String nome) throws StringInvalidaException {
		if(nome == null || nome.trim().isEmpty()){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		this.nome = nome;
	}

	/**
	 * Retorna login do usuario.
	 * @return
	 * 		Login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Altera login do usuario.
	 * @param login
	 * 		Novo login do usuario.
	 * @throws StringInvalidaException
	 * 		Quando login eh nulo ou vazio.
	 */
	public void setLogin(String login) throws StringInvalidaException {
		if(login == null || login.trim().isEmpty()){
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}
		this.login = login;
	}

	/**
	 * Retorna credito do usuario.
	 * @return
	 * 		Credito.
	 */
	public double getCredito() {
		return this.credito;
	}
	
	/**
	 * Altera credito do usuario.
	 * @param novoValor
	 * 		Novo valor de credito do usuario.
	 * @throws ValorInvalidoException
	 * 		Quando o valor eh negativo.
	 */
	public void setCredito(double novoValor) throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Valor do saldo nao pode ser negativo.");
		}
		this.credito = novoValor;
	}
	
	/**
	 * Retorna o conjunto de jogos do usuario.
	 * @return
	 * 		Jogos do usuario.
	 */
	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	/**
	 * Altera o conjunto de jogos do usuario.
	 * @param meusJogos
	 * 		Novo conjunto de jogos do usuario.
	 */
	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}
}
