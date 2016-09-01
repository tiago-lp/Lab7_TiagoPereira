package usuario;

import java.util.HashSet;
import java.util.Set;

import excecoes.LojaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Noob implements TipoDeUsuario {
	public static final double DESCONTO_NOOB = 0.9;
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private int x2p;
	private int bonus;
	
	public Noob(){
		this.x2p = 0;
		this.bonus = 10;
	}

	@Override
	public double compraJogo(Jogo jogo) throws LojaException{
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		return custo;
	}
	
	public int getX2p(){
		return this.x2p;
	}
	
	public void setX2p(int x2p) throws ValorInvalidoException{
		if(x2p < 0){
			throw new ValorInvalidoException("X2p nao pode ser negativo.");
		}
		this.x2p = x2p;
	}
	
	public int getBonus(){
		return this.bonus;
	}
	
	@Override
	public String toString() {
		String myString = "Jogador Noob: ";
		return myString;
	}

	@Override
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou)
			throws LojaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void punir(String nomeJogo, int scoreObtido, boolean zerou)
			throws LojaException {
		// TODO Auto-generated method stub
		
	}

}