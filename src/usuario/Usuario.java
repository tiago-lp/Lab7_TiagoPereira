package usuario;

import java.util.HashSet;
import java.util.Set;

import excecoes.LojaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import excecoes.JogoInvalidoException;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private TipoDeUsuario status;

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
		this.status = new Noob();
	}

	public void compraJogo(Jogo jogo) throws LojaException{
		double custo = status.compraJogo(jogo);
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * status.getBonus();
			setX2p(getX2p() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);
		}
	}
	
	public void recompensar(String jogoNome, int scoreObtido, boolean zerou) throws LojaException{
		
	}
	
	public void punir(String jogoNome, int scoreObtido, boolean zerou) throws LojaException{
		
	}
	

	public void adicionaDinheiro(double valor) throws ValorInvalidoException{
		if(valor < 0){
			throw new ValorInvalidoException("Valor que deseja adicionar ao saldo nao pode ser negativo.");
		}
		this.setCredito(this.credito + valor);
	}

	public void registradaJogada(String nomeJogo, int score, boolean venceu) throws LojaException {
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new LojaException();
		}
		setX2p(getX2p() + jogo.registraJogada(score, venceu));
	}
	
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
	
	public void cadastraJogo(Jogo jogo) throws JogoInvalidoException {
		if(jogo == null){
			throw new JogoInvalidoException("Jogo nao pode ser nulo.");
		}
		this.meusJogos.add(jogo);
	}

	public double calculaPrecoTotal() {
		double total = 0;
		for(Jogo jogo : this.meusJogos){
			total += jogo.getPreco();
		}
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
	
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

	public void setX2p(int novoValor) throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Valor do X2P nao pode ser negativo.");
		}
		this.x2p = novoValor;
	}

	public int getX2p() {
		return this.x2p;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws StringInvalidaException {
		if(nome == null || nome.trim().isEmpty()){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws StringInvalidaException {
		if(login == null || login.trim().isEmpty()){
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}
		this.login = login;
	}

	public void setCredito(double novoValor) throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Valor do saldo nao pode ser negativo.");
		}
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}
	
	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}
}
