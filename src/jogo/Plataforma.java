package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public class Plataforma extends Jogo {
	public final static int MAXIMUM_SCORE = 100000;
	public final static int TAXA_X2P = 20;
	public Plataforma(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}

	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}

	@Override
	public int registraJogada(int score, boolean venceu) throws ValorInvalidoException {
		setVezesJogadas(getVezesJogadas() + 1);
		if (score > this.getMaiorScore()) {
			setMaiorScore(score);
		} else if (score > MAXIMUM_SCORE) {
			setMaiorScore(MAXIMUM_SCORE);
		}
		if (venceu) {
			setVezesConcluidas(getvezesConcluidas() + 1);
			return TAXA_X2P;
		}
		return 0;
	}

	public String toString() {
		String resultado = "+ " + getNome() + " - Plataforma:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}
