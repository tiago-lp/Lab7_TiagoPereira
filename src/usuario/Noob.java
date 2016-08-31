package usuario;

import excecoes.LojaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Noob extends Usuario {
	public static final double DESCONTO_NOOB = 0.9;

	public Noob(String nome, String login) throws StringInvalidaException, ValorInvalidoException{
		super(nome, login);
		setX2p(0);
	}

	@Override
	public void compraJogo(Jogo jogo) throws LojaException{
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 10;
			setX2p(getX2p() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}

	}

	@Override
	public String toString() {
		String myString = "Jogador Noob: " + this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - " + this.getX2p() + " x2p" + FIM_DE_LINHA;
		myString += super.toString();
		return myString;
	}

}