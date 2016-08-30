package usuario;

import java.util.HashSet;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public abstract class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int x2p;

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
	}

	public abstract void compraJogo(Jogo jogo) throws Exception;
	

	public void adicionaDinheiro(double valor) throws ValorInvalidoException{
		if(valor < 0){
			throw new ValorInvalidoException("Valor que deseja adicionar ao saldo nao pode ser negativo.");
		}
		this.setCredito(this.credito + valor);
	}
	
	public void setX2p(int novoValor) {
		this.x2p = novoValor;
	}

	public int getX2p() {
		return this.x2p;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	public void registradaJogada(String nomeJogo, int score, boolean venceu) throws Exception {
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new Exception();
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

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	public double calculaPrecoTotal() {
		double total = 0;
		for(Jogo jogo : this.meusJogos){
			total += jogo.getPreco();
		}
		return total;
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
}
