package usuario;

import excecoes.LojaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Veterano extends Usuario {
	public static final double DESCONTO_VETERANO = 0.8;

	public Veterano(String nome, String login) throws StringInvalidaException, ValorInvalidoException {
		super(nome, login);
		setX2p(1000);
	}

	@Override
	public void compraJogo(Jogo jogo) throws LojaException {
		double custo = jogo.getPreco() * DESCONTO_VETERANO;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 15;
			setX2p(getX2p() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}
	}

	@Override
	public String toString() {
		String myString = "Jogador Veterano: " + this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - " + this.getX2p() + " x2p" + FIM_DE_LINHA;
		myString += super.toString();
		return myString;
	}

}
