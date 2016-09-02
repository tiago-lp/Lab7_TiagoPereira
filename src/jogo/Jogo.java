package jogo;

import java.util.HashSet;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * Super classe abstrata Jogo.
 * 
 * @author Tiago Pereira
 *
 */
public abstract class Jogo {
	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private double preco;
	private int vezesJogadas;
	private int vezesConcluidas;
	private int maiorScore;
	private Set<Jogabilidade> jogabilidades;

	/**
	 * Construtor
	 * 
	 * @param nome
	 * 		Nome do jogo
	 * @param preco
	 * 		Preco do jogo
	 * @throws StringInvalidaException
	 * 		Quando o nome eh nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco eh negativo.
	 */
	public Jogo(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo.");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = new HashSet<Jogabilidade>();
	}

	/**
	 * Construtor do jogo que recebe um conjunto de jogabilidades alem do nome e preco.
	 * 
	 * @param nome
	 * 		Nome do jogo.
	 * @param preco
	 * 		Preco do jogo.
	 * @param jogabilidades
	 * 		Conjunto de jogabilidades que o jogo possui.
	 * @throws StringInvalidaException
	 * 		Quando o nome eh nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco eh negativo.
	 */
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

	/**
	 * Metodo abstrato que registra uma jogada.
	 * 
	 * @param score
	 * 		Score obtido na jogada.
	 * @param venceu
	 * 		Boolean indicando se o jogo foi zerado (true) ou nao(false).
	 * @return
	 * 		Um inteiro indicando o x2p obtido com a jogada.
	 * @throws ValorInvalidoException
	 * 		Quando o valor do Score eh negativo.
	 */
	public abstract int registraJogada(int score, boolean venceu)throws ValorInvalidoException;

	public double getPreco() {
		return this.preco;
	}
	
	/**
	 * Altera o preco do jogo.
	 * 
	 * @param novoPreco
	 * 		Novo preco que o jogo vai possuir.
	 * @throws PrecoInvalidoException
	 * 		Quando o preco eh negativo.
	 */
	public void setPreco(double novoPreco)throws PrecoInvalidoException{
		if (novoPreco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}
		this.preco = novoPreco;
	}

	/**
	 * Retorna o nome do jogo.
	 * @return
	 * 		Nome do jogo.
	 */
	public String getNome(){
		return this.nome;
	}
	
	/**
	 * Altera o nome do jogo.
	 * 
	 * @param novoNome
	 * 		Novo nome do jogo.
	 * @throws StringInvalidaException
	 * 		Quando nome eh nulo ou vazio.
	 */
	public void setNome(String novoNome)throws StringInvalidaException {
		if (novoNome == null || novoNome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		this.nome = novoNome;
	}

	/**
	 * Retorna o maior Score do jogo.
	 * @return
	 * 		Maior score.
	 */
	public int getMaiorScore() {
		return this.maiorScore;
	}
	
	/**
	 * Altera o maior score do jogo.
	 * @param novoScore
	 * 		Novo maior score.
	 * @throws ValorInvalidoException
	 * 		Quando o novo score eh negativo.
	 */
	public void setMaiorScore(int novoScore) throws ValorInvalidoException {
		if(novoScore < 0){
			throw new ValorInvalidoException("Score nao pode ser negativo.");
		}
		this.maiorScore = novoScore;
	}

	/**
	 * Retorna a quantidade de vezes que o jogo foi concluida.
	 * @return
	 * 		Vezes concluidas.
	 */
	public int getvezesConcluidas() {
		return this.vezesConcluidas;
	}

	/**
	 * Altera a quantidade de vezes que o jogo foi concluido.
	 * @param novaQuantidade
	 * 		Nova quantidade de vezes que o jogo foi concluido.
	 * @throws ValorInvalidoException
	 * 		Quando a nova quantidade de vezes concluidas eh negativa.
	 */
	public void setVezesConcluidas(int novaQuantidade) throws ValorInvalidoException {
		if(novaQuantidade < 0){
			throw new ValorInvalidoException("Vezes zeradas nao pode ser negativo.");
		}
		this.vezesConcluidas = novaQuantidade;
	}

	/**
	 * Retorna a quantidade de vezes que o jogo foi jogado.
	 * @return
	 * 		Vezes jogadas.
	 */
	public int getVezesJogadas() {
		return this.vezesJogadas;
	}

	/**
	 * Altera a quantidade de vezes que o jogo foi jogado.
	 * @param novaQuantidade
	 * 		Nova quantidade de vezes que o jogo foi jogado.
	 * @throws ValorInvalidoException
	 * 		Quando a nova quantidade de vezes jogada eh negativa.
	 */
	public void setVezesJogadas(int novaQuantidade) throws ValorInvalidoException {
		if(novaQuantidade < 0){
			throw new ValorInvalidoException("Vezes jogadas nao pode ser negativo.");
		}
		this.vezesJogadas = novaQuantidade;
	}
	
	/**
	 * Retorna o conjunto de jogabilidades do jogo.
	 * @return
	 * 		Jogabilidades.
	 */
	public Set<Jogabilidade> getJogabilidades(){
		return this.jogabilidades;
	}
	
	/**
	 * HashCode
	 */
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

	/**
	 * Equals
	 */
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
	
	/**
	 * ToString
	 */
	@Override
	public String toString() {
		String resultado = "==> Jogou " + getVezesJogadas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Zerou " + getvezesConcluidas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Maior Score: " + getMaiorScore() + FIM_DE_LINHA;
		return resultado;
	}
}
