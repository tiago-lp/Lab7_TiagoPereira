package jogo;

import java.util.HashSet;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public abstract class Jogo {
	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private double preco;
	private int vezesJogadas;
	private int vezesConcluidas;
	private int maiorScore;
	private Set<Jogabilidade> jogabilidades;

	public Jogo(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = new HashSet<Jogabilidade>();
	}

	public Jogo(String nome, double preco, Set<Jogabilidade> jogabilidades)
			throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = jogabilidades;
	}

	public abstract int registraJogada(int score, boolean venceu)throws ValorInvalidoException;

	public double getPreco() {
		return this.preco;
	}
	
	public void setPreco(double novoPreco)throws PrecoInvalidoException{
		if (novoPreco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}
		this.preco = novoPreco;
	}

	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String novoNome)throws StringInvalidaException {
		if (novoNome == null || novoNome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		this.nome = novoNome;
	}

	public int getMaiorScore() {
		return this.maiorScore;
	}

	public void setMaiorScore(int novoScore) throws ValorInvalidoException {
		if(novoScore < 0){
			throw new ValorInvalidoException("Score nao pode ser negativo.");
		}
		this.maiorScore = novoScore;
	}

	public int getvezesConcluidas() {
		return this.vezesConcluidas;
	}

	public void setVezesConcluidas(int novaQuantidade) throws ValorInvalidoException {
		if(novaQuantidade < 0){
			throw new ValorInvalidoException("Vezes zeradas nao pode ser negativo.");
		}
		this.vezesConcluidas = novaQuantidade;
	}

	public int getVezesJogadas() {
		return this.vezesJogadas;
	}

	public void setVezesJogadas(int novaQuantidade) throws ValorInvalidoException {
		if(novaQuantidade < 0){
			throw new ValorInvalidoException("Vezes jogadas nao pode ser negativo.");
		}
		this.vezesJogadas = novaQuantidade;
	}
	
	public Set<Jogabilidade> getJogabilidades(){
		return this.jogabilidades;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Jogo){
			Jogo temp = (Jogo) obj;
			if(this.nome.equalsIgnoreCase(temp.getNome()) && this.preco == temp.getPreco()){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		String resultado = "==> Jogou " + getVezesJogadas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Zerou " + getvezesConcluidas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Maior Score: " + getMaiorScore() + FIM_DE_LINHA;
		return resultado;
	}
}
